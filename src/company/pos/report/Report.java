/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package company.pos.report;

import company.pos.database.MysqlConnect;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperExcelApiXlsExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsExporterBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import static net.sf.dynamicreports.report.builder.DynamicReports.export;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Muhammad Hanif B
 */
public class Report {
    public static void main (String[] args) throws SQLException, DRException, FileNotFoundException {
        MysqlConnect conn = MysqlConnect.getDbCon();
        JasperReportBuilder mainreport = DynamicReports.report();
        JasperReportBuilder report1 = DynamicReports.report();
        JasperReportBuilder report2 = DynamicReports.report();
        report1
                .columns(
                        Columns.column("ID Pengguna", "username", DataTypes.stringType())
                )
                .title (
                        Components.text("Simple Report 1")
                )                
                .pageFooter(Components.pageXofY())
                .setDataSource(conn.query("select username from pengguna", null));

        report2
                .columns(
                        Columns.column("ID Pengguna", "username", DataTypes.stringType())
                )
                .title (
                        Components.text("Simple Report 2")
                )
                .pageFooter(Components.pageXofY())
                .setDataSource(conn.query("select username from pengguna", null));
        
        MultiPageListBuilder builder = Components.multiPageList();
        builder.newPage();
        builder.add(Components.subreport(report2));
        builder.newPage();
        report1.title(builder);
        
        JasperViewer viewer = new JasperViewer(report1.toJasperPrint());
        viewer.setTitle("Cetak");
        viewer.setVisible(true);

        
        
    }
}
