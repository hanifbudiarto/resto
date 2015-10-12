/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package company.pos.report;

import company.pos.database.MysqlConnect;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.exception.DRException;

/**
 *
 * @author Muhammad Hanif B
 */
public class Report {
    public static void main (String[] args) {
        MysqlConnect conn = MysqlConnect.getDbCon();
        JasperReportBuilder report = DynamicReports.report();
        try {
            report
                    .columns(
                            Columns.column("ID Pengguna", "username", DataTypes.stringType())
                    )
                    .title (
                            Components.text("Simple Report")
                    )
                    .pageFooter(Components.pageXofY())
                    .setDataSource(conn.query("select username from pengguna", null))
                    .show();
        } catch (SQLException | DRException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
