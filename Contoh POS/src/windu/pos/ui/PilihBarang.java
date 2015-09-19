/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PilihBarang.java
 *
 * Created on May 27, 2011, 1:27:08 PM
 */
package windu.pos.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import windu.pos.entity.Barang;
import windu.pos.entity.PenjualanBarang;
import windu.pos.util.NumberHelper;

/**
 *
 * @author WinduPurnomo
 */
public class PilihBarang extends javax.swing.JFrame {
    private Penjualan formPenjualan;
    List<Barang> lb = new ArrayList<Barang>();
    
    public PilihBarang(Penjualan penjualan) {
        initComponents();
        tbl.getTableHeader().setFont( new Font("Calibri" , Font.BOLD, 14 ));
        this.formPenjualan = penjualan;
        txt.requestFocusInWindow();
        
        KeyStroke keyEsc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        KeyStroke keyF1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false);
        KeyStroke keyF2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false);
        
        panel.registerKeyboardAction(actionEsc, keyEsc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        panel.registerKeyboardAction(actionPilih, keyF1, JComponent.WHEN_IN_FOCUSED_WINDOW);
        panel.registerKeyboardAction(actionCari, keyF2, JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
    
    private void pilihItem(){
        PenjualanBarang pb = new PenjualanBarang();
        pb.setBarang(lb.get(tbl.getSelectedRow()));
        formPenjualan.setEnabled(true);
        this.setVisible(false);
        new JumlahItem(formPenjualan, pb).setVisible(true);
    }
    
    private void setTable(){
        String[][] conTbl = new String[lb.size()][3];
        for (int i = 0; i < lb.size(); i++){
            conTbl[i][0] = lb.get(i).getNama();
            conTbl[i][1] = String.valueOf(lb.get(i).getJumlah());
            conTbl[i][2] = NumberHelper.thousandSparator(lb.get(i).getHargaJual());
        }
        String[] title = {"Nama", "Jumlah", "Harga"};
        DefaultTableModel dtm = new DefaultTableModel(conTbl, title);
        tbl.setModel(dtm);
        txt.requestFocusInWindow();
    }
    
    ActionListener actionEsc = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            escEvent(actionEvent);
        }
    };
    
    ActionListener actionPilih = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            pilihItem();
        }
    };
    
    ActionListener actionCari = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            txt.requestFocusInWindow();
        }
    };
    
    private void escEvent(ActionEvent actionEvent) {
        formPenjualan.setEnabled(true);
        formPenjualan.setVisible(true);
        this.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        txt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Barang");
        setResizable(false);
        setUndecorated(true);

        panel.setBackground(new java.awt.Color(204, 204, 0));

        txt.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActionPerformed(evt);
            }
        });
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeyReleased(evt);
            }
        });

        tbl.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Jumlah", "Harga"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 10));
        jLabel4.setText("Pilih [F1]   ;   Cari [F2]  ;  Gagalkan [Esc]");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                            .addGap(100, 100, 100))
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-395)/2, (screenSize.height-471)/2, 395, 471);
    }// </editor-fold>//GEN-END:initComponents

    private void txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyReleased
        lb = new windu.pos.DaoImpl.Barang().selectByPartNama(txt.getText());
        setTable();
    }//GEN-LAST:event_txtKeyReleased

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        pilihItem();
    }//GEN-LAST:event_tblMouseClicked

    private void txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActionPerformed
        tbl.requestFocusInWindow();
    }//GEN-LAST:event_txtActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txt;
    // End of variables declaration//GEN-END:variables
}
