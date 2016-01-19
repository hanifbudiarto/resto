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
public class BelanjaReport {

    private final String dateFrom, dateTo;
    
    public BelanjaReport (String from, String to) {
        this.dateFrom = from;
        this.dateTo = to;
    }
    
    public void create() throws SQLException, DRException, ParseException {
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

        
        String sqlReport = "select pd.nama_barang, pd.satuan, pd.harga, sum(jumlah) as jumlah, sum(jumlah)*pd.harga as total from pembelian_detail pd\n" +
            "where pd.pembelian_id in (\n" +
            "select p.pembelian_id from pembelian p where (p.pembelian_tanggal BETWEEN '"+dateFrom+"' AND '"+dateTo+"'))\n" +
            "group by pd.nama_barang, pd.harga";
        
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newFormat = new SimpleDateFormat("dd MMMM yyyy");  
        
        MysqlConnect conn = MysqlConnect.getDbCon();
        
        // -----------------------------------------------------------Summary Report------//
        JasperReportBuilder report = DynamicReports.report();
        report
            .setColumnTitleStyle(columnTitleStyle)
            .highlightDetailEvenRows()
            .setSubtotalStyle(boldCenteredStyle)
            .columns(
                rowNumberColumn,
                Columns.column("Nama Barang", "nama_barang", DataTypes.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                Columns.column("Satuan", "satuan", DataTypes.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                Columns.column("Harga", "harga", DataTypes.longType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                Columns.column("Jumlah", "jumlah", DataTypes.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                totalCol
            )
            .title(
                Components.horizontalList()
                .add(
                    Components.image(getClass().getResource("/resources/icon.png")).setFixedDimension(49, 40),
                    Components.text("Laporan Belanja Tanggal "+newFormat.format(oldFormat.parse(dateFrom))+" - "+newFormat.format(oldFormat.parse(dateTo))).setStyle(boldCenteredStyle)
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
            String dateBelanja = detailBelanja.getString("pembelian_tanggal");
            String idBelanja = detailBelanja.getString("pembelian_id");
            String query = "select nama_barang, satuan, harga, jumlah, harga*jumlah as total from pembelian_detail pd where pd.pembelian_id = "+idBelanja;
            
            detailedReport
                .setColumnTitleStyle(columnTitleStyle)
                .highlightDetailEvenRows()
                .setSubtotalStyle(boldCenteredStyle)
                .columns(
                    rowNumberColumn,
                    Columns.column("Nama Barang", "nama_barang", DataTypes.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                    Columns.column("Satuan", "satuan", DataTypes.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                    Columns.column("Harga", "harga", DataTypes.longType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                    Columns.column("Jumlah", "jumlah", DataTypes.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                    totalCol
                )
                .title(                    
                    Components.horizontalList()
                    .add(
                        Components.image(getClass().getResource("/resources/icon.png")).setFixedDimension(49, 40),
                        Components.text("Detail Laporan Belanja Tanggal "+newFormat.format(oldFormat.parse(dateBelanja))+"\n").setStyle(boldCenteredStyle)
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
        viewer.setTitle("Cetak Laporan Belanja");
        viewer.setVisible(true);
    }
    
    private ResultSet getAllTransactionBetween () throws SQLException {
        String sql = "select p.pembelian_id, p.pembelian_tanggal from pembelian p where (p.pembelian_tanggal BETWEEN '"+dateFrom+"' AND '"+dateTo+"') order by p.pembelian_tanggal";
        MysqlConnect conn = MysqlConnect.getDbCon();
        ResultSet result = conn.query(sql, null);        
        return result;
    }

}
