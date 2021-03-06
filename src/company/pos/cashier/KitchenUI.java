package company.pos.cashier;

import company.pos.database.MysqlConnect;
import company.pos.util.FrameUtil;
import company.pos.util.TableUtil;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
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
public class KitchenUI extends javax.swing.JPanel {

    /**
     * CreaKitchenUI new form KitchenUI
     */
    
    private final DefaultTableModel dtm;
    private boolean isCalculated = false;
    
    public KitchenUI() {
        initComponents();
        dtm = (DefaultTableModel) tblBelanja.getModel();
        
        this.initTblBelanja();
        this.initDatePicker();
    }
        
    private void initTblBelanja () {
        ((DefaultCellEditor) tblBelanja.getDefaultEditor(Object.class)).setClickCountToStart(1);
        tblBelanja.setAutoCreateRowSorter(false);
        tblBelanja.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {                   
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Hapus baris ini?","Peringatan",dialogButton);
                    if(dialogResult == 0){
                        DefaultTableModel model = (DefaultTableModel)tblBelanja.getModel();
                        model.removeRow(row);    
                        JFrame frame = (JFrame) SwingUtilities.getRoot(table);
                        KitchenUI panel = (KitchenUI) frame.getContentPane();
                        panel.calculateTotal();
                    }
                }
            }            
                       
        });   
    }
    
    private void initDatePicker () {
        dpTanggal.setDate(new Date());
        dpTanggal.setFormats(new String[] { "dd-MM-yyyy" });
        dpTanggal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String date = formatter.format(dpTanggal.getDate());
                try {
                    dpTanggal.setDate(formatter.parse(date));
                } catch (ParseException ex) {
                    Logger.getLogger(OrderUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    
    // checking 
    private boolean calculateTotal () {
        BigInteger totalAll = BigInteger.ZERO;
        int tabLen = tblBelanja.getColumnCount();
        int rowCount = tblBelanja.getRowCount();
        for (int i=0; i<rowCount; i++){
//            if (tblBelanja.getValueAt(i, 0).toString()) return false;
            if(tblBelanja.getValueAt(i, 0)== null || 
                    tblBelanja.getValueAt(i, 1) == null ||
                    tblBelanja.getValueAt(i, 2) == null ||
                    tblBelanja.getValueAt(i, 3) == null) return false;
            
            try {
                String priceStr = tblBelanja.getValueAt(i, tabLen-3).toString();
                String qtyStr = tblBelanja.getValueAt(i, tabLen-2).toString();
                BigInteger price = new BigInteger(priceStr);
                BigInteger qty = new BigInteger(qtyStr);
                BigInteger total = price.multiply(qty);
                totalAll = totalAll.add(total);
                tblBelanja.setValueAt(total.toString(), i, tabLen-1);
                this.isCalculated = true;
            } catch (Exception ex) {
                tblBelanja.setValueAt(null, i, tabLen-1);
                return false;
            }          
        }
        lblTotalAll.setText(totalAll.toString());
        return true;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnMinimize = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEditMode = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        dpTanggal = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBelanja = new javax.swing.JTable();
        lblTotalAll = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCalculate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnMinimize.setBackground(new java.awt.Color(255, 255, 204));
        btnMinimize.setFocusPainted(false);
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(255, 204, 204));
        btnExit.setFocusPainted(false);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Belanja"));

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/home.png"))); // NOI18N
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEditMode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete.png"))); // NOI18N
        btnEditMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditModeActionPerformed(evt);
            }
        });

        jLabel1.setText("Tanggal");

        jLabel2.setText("Tabel Belanja");

        tblBelanja.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Barang", "Satuan", "Harga", "Jumlah", "Total"
            }
        ));
        tblBelanja.setCellSelectionEnabled(true);
        tblBelanja.setRowHeight(30);
        jScrollPane1.setViewportView(tblBelanja);

        lblTotalAll.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalAll.setText("-");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Total Rp. ");

        btnCalculate.setText("Hitung Biaya Total");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });

        btnSave.setText("Simpan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("MENU UTAMA");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("TAMBAH");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("HAPUS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalAll)
                                .addGap(14, 14, 14)
                                .addComponent(btnCalculate))
                            .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(dpTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel7))
                                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnEditMode, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel8)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dpTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalAll)
                    .addComponent(jLabel3)
                    .addComponent(btnCalculate))
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 743, Short.MAX_VALUE)
                        .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        FrameUtil.getCurrentFrame().setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        FrameUtil.changeUI(new CashierUI(), (JFrame) SwingUtilities.getWindowAncestor(this));
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        TableCellEditor editor = tblBelanja.getCellEditor();
        if (editor != null) {
            editor.stopCellEditing();
        }
        String[] arr = new String[]{null, null, null, null, null};
        dtm.addRow(arr);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (tblBelanja.getRowCount()>0){
            if (!this.calculateTotal()) {JOptionPane.showMessageDialog(this, "Pengisian Tidak Benar!"); }
            else {
                TableCellEditor editor = tblBelanja.getCellEditor();
                if (editor != null) {
                  editor.stopCellEditing();
                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");        
                String date = formatter.format(dpTanggal.getDate());
                TableUtil tblUtil = new TableUtil(tblBelanja);        
                int orderid = new Kitchen().insertLogistic(tblUtil.getTableData(), date, lblTotalAll.getText());
                if (orderid>=0) {   
                    this.showOrderPdf(orderid);
                    dtm.setRowCount(0);
                    tblBelanja.setModel(dtm);
                    JOptionPane.showMessageDialog(this, "Berhasil!");             
                }
                else JOptionPane.showMessageDialog(this, "Gagal!");
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void showOrderPdf (int orderid) {
        try {
            MysqlConnect conn = MysqlConnect.getDbCon();
            JasperReportBuilder report = DynamicReports.report();
            try {
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
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
                String date = formatter.format(dpTanggal.getDate());
                
                ResultSet result = conn.query("select pd.nama_barang, pd.satuan, pd.harga, pd.jumlah, pd.harga*pd.jumlah as total, date_format(p.timestamp,\"%d %M %y %H:%i:%s\") as waktu from pembelian_detail pd\n" +
"left join pembelian p on pd.pembelian_id = p.pembelian_id where p.pembelian_id = "+orderid, null);
                
                TextColumnBuilder<Long> totalCol = Columns.column("Total", "total", DataTypes.longType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
                
                result.next();
                String datetime = result.getString("waktu");
                result.previous();
                
                report
                        .setColumnTitleStyle(columnTitleStyle)
                        .highlightDetailEvenRows()
                        .setSubtotalStyle(boldCenteredStyle)
                        .columns(
                                rowNumberColumn,
                                Columns.column("Nama Barang", "nama_barang", DataTypes.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                Columns.column("Satuan", "satuan", DataTypes.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                Columns.column("Jumlah", "jumlah", DataTypes.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                Columns.column("Harga/Satuan", "harga", DataTypes.longType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                totalCol
                        )
                        .title (
                                Components.horizontalList()
                                .add(
                                    Components.image(getClass().getResource("/resources/icon.png")).setFixedDimension(49, 40),
                                    Components.text("Belanja Tanggal "+date).setStyle(boldCenteredStyle)
                                )
                        )
                        .summary(Components.text(datetime))
                        .pageFooter(Components.pageXofY().setStyle(boldCenteredStyle))
                        .subtotalsAtSummary(DynamicReports.sbt.sum(totalCol))
                        .setDataSource(result);
               
                JasperViewer viewer = new JasperViewer(report.toJasperPrint(), false);
                viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
                viewer.setTitle("Cetak Laporan Belanja");
                viewer.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DRException ex) {
            Logger.getLogger(OrderUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
        
            TableCellEditor editor = tblBelanja.getCellEditor();
            if (editor != null) {
              editor.stopCellEditing();
            }
            if (!this.calculateTotal()) {JOptionPane.showMessageDialog(this, "Pengisian Tidak Benar!"); }
            if (this.isCalculated) {
            btnSave.setEnabled(true);}

    }//GEN-LAST:event_btnCalculateActionPerformed

    private void btnEditModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditModeActionPerformed
        btnHome.setEnabled(!btnHome.isEnabled());
        btnAdd.setEnabled(!btnAdd.isEnabled());
        btnSave.setEnabled(!btnSave.isEnabled());
        btnCalculate.setEnabled(!btnCalculate.isEnabled());
        dpTanggal.setEnabled(!dpTanggal.isEnabled());
        
        TableCellEditor editor = tblBelanja.getCellEditor();
        if (editor != null) {
          editor.stopCellEditing();
        }
        
        if (tblBelanja.getCellSelectionEnabled()) {
            tblBelanja.setCellSelectionEnabled(false);
            tblBelanja.setRowSelectionAllowed(true);  
        } else {
            tblBelanja.setCellSelectionEnabled(true);
        }      
        ((DefaultCellEditor) tblBelanja.getDefaultEditor(Object.class)).setClickCountToStart(tblBelanja.getCellSelectionEnabled()? 1 : 1000);
    }//GEN-LAST:event_btnEditModeActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnEditMode;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton btnSave;
    private org.jdesktop.swingx.JXDatePicker dpTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTotalAll;
    private javax.swing.JTable tblBelanja;
    // End of variables declaration//GEN-END:variables
}
