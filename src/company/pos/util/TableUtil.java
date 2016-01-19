package company.pos.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
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
    
    public static DefaultTableModel buildTableModel(ResultSet rs, final boolean isEditable)
        throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnLabel(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames){@Override
        
        public boolean isCellEditable (int row, int column){ return isEditable; }};
    }
    
}
