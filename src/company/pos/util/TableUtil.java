/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package company.pos.util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Muhammad Hanif B
 */
public class TableUtil {
    private final JTable jtable;
    public TableUtil (JTable jtable) {
        this.jtable = jtable;
    }
    
    public Object[][] getTableData () {
        DefaultTableModel dtm = (DefaultTableModel) this.jtable.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++) {
            for (int j = 0 ; j < nCol ; j++) {
                tableData[i][j] = dtm.getValueAt(i,j);
            }
        }
        return tableData;
    }
    
}
