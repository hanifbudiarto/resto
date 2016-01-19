package company.pos.report;

import company.pos.database.MysqlConnect;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Muhammad Hanif B
 */
public class PenjualanReport {
    
    private final String dateFrom, dateTo;
    
    public PenjualanReport (String from, String to) {
        this.dateFrom = from;
        this.dateTo = to;
    }
    
    public void create () throws ParseException, SQLException, DRException {
        StyleBuilder boldStyle = DynamicReports.stl.style().bold();
        StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
        StyleBuilder columnTitleStyle = DynamicReports.stl.style(boldCenteredStyle)
                .setBorder(DynamicReports.stl.pen1Point())
                .setBackgroundColor(Color.LIGHT_GRAY);

        TextColumnBuilder<Integer> rowNumberColumn = DynamicReports.col.reportRowNumberColumn("No. ")
                .setFixedColumns(2)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);

        TextColumnBuilder<Long> totalCol = Columns.column("Total", "total", DataTypes.longType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);

        String sqlReport = "select a.menu, m.harga, a.jumlah, a.jumlah*m.harga as total from (\n" +
            "select pd.menu, sum(jumlah) as jumlah from penjualan_detail pd where pd.penjualan_id in (\n" +
            "select p.penjualan_id from penjualan p where (p.penjualan_tanggal between '"+dateFrom+"' and '"+dateTo+"'))\n" +
            "group by pd.menu ) a\n" +
            "left join menu m on a.menu = m.nama order by a.menu";
        
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newFormat = new SimpleDateFormat("dd MMMM yyyy"); 
        
        MysqlConnect conn = MysqlConnect.getDbCon();
        
        JasperReportBuilder report = DynamicReports.report();
        report
            .setColumnTitleStyle(columnTitleStyle)
            .highlightDetailEvenRows()
            .setSubtotalStyle(boldCenteredStyle)
            .columns(
                rowNumberColumn,
                Columns.column("Menu", "menu", DataTypes.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),                
                Columns.column("Harga", "harga", DataTypes.longType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                Columns.column("Jumlah", "jumlah", DataTypes.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                totalCol
            )
            .title(
                Components.horizontalList()
                    .add(
                        Components.image(getClass().getResource("/resources/icon.png")).setFixedDimension(49, 40),
                        Components.text("Laporan Penjualan Tanggal "+newFormat.format(oldFormat.parse(dateFrom))+" - "+newFormat.format(oldFormat.parse(dateTo))).setStyle(boldCenteredStyle)
                    ) 
            )
            .pageFooter(Components.pageXofY().setStyle(boldCenteredStyle))
            .subtotalsAtSummary(DynamicReports.sbt.sum(totalCol))
            .setDataSource(conn.query(sqlReport, null));
        
        
        
         // --------------------------------------------------Detail Report-------------//
        MultiPageListBuilder builder = Components.multiPageList();
        builder.newPage();
        
        ResultSet detailBelanja = this.getAllTransactionBetween();
        while (detailBelanja.next()) {
            JasperReportBuilder detailedReport = DynamicReports.report();
            String datePenjualan = detailBelanja.getString("penjualan_tanggal");
            String idPenjualan = detailBelanja.getString("penjualan_id");
            String meja = detailBelanja.getString("meja");
            String query = "select pd.menu, m.harga, pd.jumlah, pd.jumlah*m.harga as total from\n" +
                "penjualan_detail pd left join menu m\n" +
                "on m.nama=pd.menu where pd.penjualan_id="+idPenjualan;
            
            detailedReport
                .setColumnTitleStyle(columnTitleStyle)
                .highlightDetailEvenRows()
                .setSubtotalStyle(boldCenteredStyle)
                .columns(
                    rowNumberColumn,
                    Columns.column("Nama Barang", "menu", DataTypes.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),                    
                    Columns.column("Harga", "harga", DataTypes.longType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                    Columns.column("Jumlah", "jumlah", DataTypes.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                    totalCol
                )
                .title(
                    Components.horizontalList()
                    .add(
                        Components.image(getClass().getResource("/resources/icon.png")).setFixedDimension(49, 40),
                        Components.text("Detail Laporan Penjualan\n Tanggal "+newFormat.format(oldFormat.parse(datePenjualan))+"\n Meja "+meja).setStyle(boldCenteredStyle)
                    )                    
                )                
                .subtotalsAtSummary(DynamicReports.sbt.sum(totalCol))
                .setDataSource(conn.query(query, null));
            
            builder.add(Components.subreport(detailedReport));
            builder.newPage();
        }
        report.summary(builder);
        
        JasperViewer viewer = new JasperViewer(report.toJasperPrint(), false);
        viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewer.setTitle("Cetak Laporan Penjualan");
        viewer.setVisible(true);
    }
    
    private ResultSet getAllTransactionBetween () throws SQLException {
        String sql = "select p.penjualan_id, p.penjualan_tanggal, p.meja from penjualan p where (p.penjualan_tanggal BETWEEN '"+dateFrom+"' AND '"+dateTo+"') order by p.penjualan_tanggal, p.meja";
        MysqlConnect conn = MysqlConnect.getDbCon();
        ResultSet result = conn.query(sql, null);        
        return result;
    }
}
