/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package company.pos.cashier;

import company.pos.admin.MyMenu;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Muhammad Hanif B
 */
public class OrderUI extends javax.swing.JPanel {

    /**
     * Creates new form NewOrderUI
     */
    
    private DefaultTableModel modelOrderTable;
    
    public OrderUI() {
        initComponents();
        this.initCBMenu();
        this.initDatePicker();
        this.initOrderTable();
        
        btnSave.setEnabled(tblOrder.getRowCount()>0);
    }
    
    private void initOrderTable () {        
        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Menu", "Jumlah"
            }
        ){@Override
        public boolean isCellEditable(int row, int column){return false;}});

        
        tblOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                System.err.println("row "+row);
                if (me.getClickCount() == 2) {
                    Object selectedObject = (Object) table.getModel().getValueAt(row, 0);
                    System.out.println(selectedObject.toString());
                    
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Hapus pesanan "+selectedObject.toString()+" ?","Peringatan",dialogButton);
                    if(dialogResult == 0){
                        modelOrderTable = (DefaultTableModel)tblOrder.getModel();
                        modelOrderTable.removeRow(row);
                        
                        btnSave.setEnabled(tblOrder.getRowCount()>0);
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
    
    private void initCBMenu () { 
        // Set autocomplete on combobox MyMenu
        AutoCompleteDecorator.decorate(cbMenu);
        try {
            this.populateMenu();
        } catch (SQLException ex) {
            Logger.getLogger(OrderUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void populateMenu () throws SQLException {
        ResultSet rset = new MyMenu().getAllMenu();
        DefaultComboBoxModel<String> model  = new DefaultComboBoxModel<>();
        while (rset.next()) {
            model.addElement(rset.getString(1));
        }
        cbMenu.setModel(model);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnMinimize = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        tfMeja = new javax.swing.JTextField();
        cbMenu = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfQty = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        taCatatan = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dpTanggal = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnMinimize.setBackground(new java.awt.Color(255, 255, 204));
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(255, 204, 204));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesanan Baru"));

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/company/pos/images/home.png"))); // NOI18N
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Menu", "Jumlah"
            }
        ){public boolean isCellEditable(int row, int column){return false;}});
        tblOrder.setFocusable(false);
        tblOrder.setRowHeight(30);
        tblOrder.getTableHeader().setResizingAllowed(false);
        tblOrder.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblOrder);

        jLabel1.setText("Nomor Meja");

        btnSave.setBackground(new java.awt.Color(204, 255, 204));
        btnSave.setText("Simpan Pesanan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnAdd.setText("Tambah");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel5.setText("Jumlah yang dipesan");

        jLabel7.setText("Tabel Pesanan");

        taCatatan.setColumns(20);
        taCatatan.setRows(5);
        jScrollPane2.setViewportView(taCatatan);

        jLabel4.setText("Cari Menu");

        jLabel2.setText("Tanggal");

        dpTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpTanggalActionPerformed(evt);
            }
        });

        jLabel3.setText("Catatan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(dpTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(tfMeja, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(cbMenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tfQty)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(207, 207, 207))
                                    .addComponent(jScrollPane2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dpTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfMeja, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(7, 7, 7)
                        .addComponent(cbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfQty, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 687, Short.MAX_VALUE)
                        .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!tfQty.getText().isEmpty()) {
            try { Integer.parseInt(tfQty.getText()); 
                String []  newRow = new String [] {
                       cbMenu.getSelectedItem().toString(), tfQty.getText()
                };
                modelOrderTable = (DefaultTableModel)tblOrder.getModel();
                modelOrderTable.addRow(newRow);      
                tfQty.setText("");
                btnSave.setEnabled(tblOrder.getRowCount()>0);
            }
            catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Isikan jumlah yang dipesan dengan angka saja!"); }   
        } else  {JOptionPane.showMessageDialog(this, "Isikan jumlah yang dipesan!");}
    }//GEN-LAST:event_btnAddActionPerformed

    private void dpTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dpTanggalActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (tfMeja.getText().isEmpty()) { 
            JOptionPane.showMessageDialog(this, "Isikan nomor meja!");            
        }
        else if (dpTanggal.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Isikan tanggal dengan benar!");
        }   
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");        
            String date = formatter.format(dpTanggal.getDate());
            System.out.println(date);
        
            TableUtil tblUtil = new TableUtil(tblOrder);        
            int order = new Order().insertOrder(tblUtil.getTableData(), date, tfMeja.getText(), taCatatan.getText());
            if (order >= 0) {                          
                this.showOrderPdf(order, tfMeja.getText(), taCatatan.getText());
                tfMeja.setText("");
                taCatatan.setText("");
                modelOrderTable = (DefaultTableModel)tblOrder.getModel();
                modelOrderTable.setRowCount(0);
                tblOrder.setModel(modelOrderTable);
                JOptionPane.showMessageDialog(this, "Berhasil!");
            }
            else JOptionPane.showMessageDialog(this, "Gagal!");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void showOrderPdf (int orderid, String tableNum, String note) {
        try {
            MysqlConnect conn = MysqlConnect.getDbCon();
            JasperReportBuilder report = DynamicReports.report();
            try {
                StyleBuilder boldStyle = DynamicReports.stl.style().bold();
                StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle)
                        .setHorizontalAlignment(HorizontalAlignment.CENTER);
                StyleBuilder columnTitleStyle = DynamicReports.stl.style(boldCenteredStyle)
                        .setBorder(DynamicReports.stl.pen1Point())
                        .setBackgroundColor(Color.LIGHT_GRAY);
                
//                TextColumnBuilder<Integer> rowNumberColumn = DynamicReports.col.reportRowNumberColumn("No. ")
//                        .setFixedColumns(2)
//                        .setHorizontalAlignment(HorizontalAlignment.CENTER);
                
                TextColumnBuilder<String> categoryCol = Columns.column("Kategori",       "nama",      DataTypes.stringType()).setStyle(boldStyle);
                ResultSet result = conn.query("select mk.nama, pd.menu, sum(pd.jumlah) as jumlah from penjualan_detail pd left join menu m on m.nama =\n" +
"pd.menu left join menu_kategori mk on m.kategori_id = mk.kategori_id " +
" where pd.penjualan_id ="+orderid+" group by pd.menu order by mk.nama \n", null);
                
                report
                        .setColumnTitleStyle(columnTitleStyle)
                        .highlightDetailEvenRows()
                        .columns(
//                                rowNumberColumn,
                                categoryCol,
                                Columns.column("Menu yang dipesan", "menu", DataTypes.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                Columns.column("Jumlah", "jumlah", DataTypes.longType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                        )
                        .groupBy(categoryCol)
                        .title (
                                Components.text("Pesanan Meja "+tableNum).setStyle(boldCenteredStyle)
                        )
                        .pageFooter(Components.pageXofY().setStyle(boldCenteredStyle))
                        .summary(Components.text("Catatan : \n"+note))
                        .setDataSource(result);
               
                JasperViewer viewer = new JasperViewer(report.toJasperPrint(), false);
                viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
                viewer.setTitle("Cetak Pesanan");
                viewer.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DRException ex) {
            Logger.getLogger(OrderUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        FrameUtil.getCurrentFrame().setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
         FrameUtil.changeUI(new CashierUI(), (JFrame) SwingUtilities.getWindowAncestor(this));
    }//GEN-LAST:event_btnHomeActionPerformed
//        if (!tfMeja.getText().isEmpty()) {
//            ResultSet rset = new Order().showOrderByTable(tfMeja.getText());
//            DefaultTableModel dtm = null;
//            try {
//                dtm = TableUtil.buildTableModel(rset);
//            } catch (SQLException ex) {
//                Logger.getLogger(OrderUI.class.getName()).log(Level.SEVERE, null, ex);
//            }      
//            tblOrder.setModel(dtm);            
//        }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cbMenu;
    private org.jdesktop.swingx.JXDatePicker dpTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea taCatatan;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextField tfMeja;
    private javax.swing.JTextField tfQty;
    // End of variables declaration//GEN-END:variables
}
