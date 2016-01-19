package company.pos.report;

import company.pos.database.MysqlConnect;
import java.awt.Color;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
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
public class RankingPelayanReport {
    
    private final String dateFrom, dateTo;
    
    public RankingPelayanReport (String from, String to) {
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

        
        String sqlReport = "select pelayan.nama, penjualan.pelayan as panggilan, count(pelayan) as jumlah from penjualan left join pelayan on penjualan.pelayan = pelayan.panggilan where penjualan.pelayan is not null \n" +
"and penjualan.penjualan_tanggal between '"+dateFrom+"' and '"+dateTo+"' group by penjualan.pelayan order by jumlah desc";
        
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newFormat = new SimpleDateFormat("dd MMMM yyyy");  
        
        MysqlConnect conn = MysqlConnect.getDbCon();
        
        // -----------------------------------------------------------Summary Report------//
        JasperReportBuilder report = DynamicReports.report();
        report
            .setColumnTitleStyle(columnTitleStyle)
            .highlightDetailEvenRows()
            .columns(
                rowNumberColumn,
                Columns.column("Nama", "nama", DataTypes.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                Columns.column("Panggilan", "panggilan", DataTypes.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),                
                Columns.column("Jumlah", "jumlah", DataTypes.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
            )
            .title(
                Components.horizontalList()
                    .add(
                        Components.image(getClass().getResource("/resources/icon.png")).setFixedDimension(49, 40),
                        Components.text("Ranking Jumlah Pelayanan Terbanyak\nTanggal "+newFormat.format(oldFormat.parse(dateFrom))+" - "+newFormat.format(oldFormat.parse(dateTo))).setStyle(boldCenteredStyle)
                    )                 
            )
            .pageFooter(Components.pageXofY().setStyle(boldCenteredStyle))
            .setDataSource(conn.query(sqlReport, null));
       
        
        JasperViewer viewer = new JasperViewer(report.toJasperPrint(), false);
        viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewer.setTitle("Cetak Ranking Pelayan");
        viewer.setVisible(true);
    }
}
