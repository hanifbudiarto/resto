/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package company.pos.cashier;

import company.pos.util.FrameUtil;
import company.pos.util.TableUtil;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Muhammad Hanif B
 */
public class KitchenUI extends javax.swing.JPanel {

    /**
     * CreaKitchenUI new form KitchenUI
     */
    
    private final DefaultTableModel dtm;
    
    public KitchenUI() {
        initComponents();
        dtm = (DefaultTableModel) tblBelanja.getModel();
        tblBelanja.setAutoCreateRowSorter(false);
        this.initTblBelanja();
        this.initDatePicker();
    }
        
    private void initTblBelanja () {
        ((DefaultCellEditor) tblBelanja.getDefaultEditor(Object.class)).setClickCountToStart(1);
        tblBelanja.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                System.err.println("row "+row);
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

    private void calculateTotal () {
        BigInteger totalAll = BigInteger.ZERO;
        int tabLen = tblBelanja.getColumnCount();
        int rowCount = tblBelanja.getRowCount();
        for (int i=0; i<rowCount; i++){
            if (tblBelanja.getValueAt(i, tabLen-3).toString().isEmpty() ||
                    tblBelanja.getValueAt(i, tabLen-2).toString().isEmpty()) {
                tblBelanja.setValueAt(null, i, tabLen-1);
                break;
            }
            
            BigInteger price = new BigInteger(tblBelanja.getValueAt(i, tabLen-3).toString());
            BigInteger qty = new BigInteger(tblBelanja.getValueAt(i, tabLen-2).toString());
            BigInteger total = price.multiply(qty);
            totalAll = totalAll.add(total);
            tblBelanja.setValueAt(total.toString(), i, tabLen-1);
        }
        lblTotalAll.setText(totalAll.toString());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dpTanggal = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBelanja = new javax.swing.JTable();
        lblTotalAll = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCalculate = new javax.swing.JButton();
        btnEditMode = new javax.swing.JButton();

        tblBelanja.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Barang", "Satuan", "Harga", "Jumlah", "Total"
            }
        ));
        tblBelanja.setCellSelectionEnabled(true);
        tblBelanja.setRowHeight(30);
        jScrollPane1.setViewportView(tblBelanja);

        lblTotalAll.setText("jLabel1");

        btnExit.setBackground(new java.awt.Color(255, 204, 204));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnMinimize.setBackground(new java.awt.Color(255, 255, 204));
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/company/pos/images/home.png"))); // NOI18N
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnAdd.setText("jButton1");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSave.setText("jButton1");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCalculate.setText("jButton1");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });

        btnEditMode.setText("jButton1");
        btnEditMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditModeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditMode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dpTanggal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTotalAll)
                                .addGap(56, 56, 56)
                                .addComponent(btnCalculate)))
                        .addContainerGap(15, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dpTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalAll)
                    .addComponent(btnCalculate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnSave))
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
        TableCellEditor editor = tblBelanja.getCellEditor();
        if (editor != null) {
          editor.stopCellEditing();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");        
        String date = formatter.format(dpTanggal.getDate());
        System.out.println(date);

        TableUtil tblUtil = new TableUtil(tblBelanja);        
        boolean order = new Kitchen().insertLogistic(tblUtil.getTableData(), date);
        if (order) {            
            JOptionPane.showMessageDialog(this, "Berhasil!");                
        }
        else JOptionPane.showMessageDialog(this, "Gagal!");
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
        TableCellEditor editor = tblBelanja.getCellEditor();
        if (editor != null) {
          editor.stopCellEditing();
        }
        this.calculateTotal();
    }//GEN-LAST:event_btnCalculateActionPerformed

    private void btnEditModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditModeActionPerformed
        TableCellEditor editor = tblBelanja.getCellEditor();
        if (editor != null) {
          editor.stopCellEditing();
        }
        tblBelanja.setCellSelectionEnabled(!tblBelanja.getCellSelectionEnabled());
        tblBelanja.setRowSelectionAllowed(!tblBelanja.getRowSelectionAllowed());
        ((DefaultCellEditor) tblBelanja.getDefaultEditor(Object.class)).setClickCountToStart(tblBelanja.getRowSelectionAllowed() ? 1000 : 1);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalAll;
    private javax.swing.JTable tblBelanja;
    // End of variables declaration//GEN-END:variables
}
