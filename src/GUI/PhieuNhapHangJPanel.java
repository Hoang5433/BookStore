/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.PhieuNhapDTO;
import GUI.PhieuNhapHang.ChiTietPhieuNhapJPanel;
import GUI.PhieuNhapHang.TaoPhieuNhapJPanel;
import GUI.Sweet.SweetComboBox;
import GUI.Table.RenderTable;
import GUI.Table.setColorForCell;
import static GUI.TongQuanJPanel.customTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import quanlycuahangsach.DateTime;

/**
 *
 * @author admin
 */
public class PhieuNhapHangJPanel extends javax.swing.JPanel {
    Object[] columnNames ={"Mã phiếu nhập","Mã nhà cung cấp","Ngày nhập","Tổng tiền","Nhân viên tạo phiếu"};
    DefaultTableModel listTableModel;
    ArrayList<PhieuNhapDTO> MangTiemKiem;
    public static PhieuNhapDTO currentPhieuNhap;
    /**
     * Creates new form PhieuNhapHang
     */
    public void setShadow(){
        pnlTblDonHangChuaXacNhan.setShadow(1);
    }        
    public void ShowTable(JTable table,Object[] columnName){
        
        table.setDefaultRenderer(Object.class, new RenderTable());
        
        Object columnNames[] = columnName;
        listTableModel = new DefaultTableModel(columnNames,0);
        quanlycuahangsach.quanlycuahangsach.PhieuNhapBUS.PhieuNhapList.forEach(phieunhap->{
            Vector row = new Vector();
            row.add(phieunhap.getMaPhieuNhap());
            row.add(phieunhap.getMaNhaCungCap());
            row.add(DateTime.TimestampToDateString(phieunhap.getNgayNhap(),1));
            row.add(phieunhap.getTongTien());
            row.add(phieunhap.getMaNhanVien());
            listTableModel.addRow(row);
        });     
        table.setModel(listTableModel);      
    } 
    public  void search(){
//        if(MangTiemKiem.isEmpty() && (txtMaPhieuNhap.getText().equals("")&&txtNhanVienTaoPhieu.getText().equals(""))){ fetchAll(); return;}
            
        listTableModel.setRowCount(0);
        MangTiemKiem.forEach( PhieuNhap ->{
            Vector row = new Vector();
            row.add((PhieuNhap).getMaPhieuNhap());
            row.add((PhieuNhap).getMaNhaCungCap());           
            row.add((PhieuNhap).getNgayNhap());
            row.add((PhieuNhap).getTongTien());
            row.add((PhieuNhap).getMaNhanVien());
            listTableModel.addRow(row);
        });
        tblPhieuNhapHang.setModel(listTableModel);
    }
        private void getAdvancedSearch(){
        HashMap<String,String> advancedSearch = new HashMap<>();
        advancedSearch.put("MaPhieuNhap",txtMaPhieuNhap.getText());
        advancedSearch.put("MaNhanVien",txtNhanVienTaoPhieu.getText());
        MangTiemKiem = quanlycuahangsach.quanlycuahangsach.PhieuNhapBUS.advancedSearch(advancedSearch);
        search();
        
    }
    public PhieuNhapHangJPanel() {
        initComponents();
        customTable(tblPhieuNhapHang,scpPhieuNhapHang);
        ShowTable(tblPhieuNhapHang,columnNames);
        setShadow();
        pnlTaoPhieuNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ((DefaultTableCellRenderer) tblPhieuNhapHang.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTblDonHangChuaXacNhan = new GUI.Rounded();
        scpPhieuNhapHang = new javax.swing.JScrollPane();
        tblPhieuNhapHang = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }
        };
        pnlMaPhieuNhap = new GUI.Rounded();
        txtMaPhieuNhap = new javax.swing.JTextField();
        pnlNhanVienTaoPhieu = new GUI.Rounded();
        txtNhanVienTaoPhieu = new javax.swing.JTextField();
        pnlTaoPhieuNhap = new GUI.Rounded();
        lblTaoPhieuNhap = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 52, 102));
        jLabel1.setText("Nhân viên tạo phiếu:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        jLabel3.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 52, 102));
        jLabel3.setText("Mã phiếu nhập:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 30));

        pnlTblDonHangChuaXacNhan.setBackground(new java.awt.Color(238, 243, 247));
        pnlTblDonHangChuaXacNhan.setForeground(new java.awt.Color(238, 243, 247));

        tblPhieuNhapHang.setAutoCreateRowSorter(true);
        tblPhieuNhapHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblPhieuNhapHang.setForeground(new java.awt.Color(24, 24, 24));
        tblPhieuNhapHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblPhieuNhapHang.setFocusable(false);
        tblPhieuNhapHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblPhieuNhapHang.setRowHeight(25);
        tblPhieuNhapHang.setSelectionBackground(new java.awt.Color(152, 210, 248));
        tblPhieuNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapHangMouseClicked(evt);
            }
        });
        scpPhieuNhapHang.setViewportView(tblPhieuNhapHang);

        javax.swing.GroupLayout pnlTblDonHangChuaXacNhanLayout = new javax.swing.GroupLayout(pnlTblDonHangChuaXacNhan);
        pnlTblDonHangChuaXacNhan.setLayout(pnlTblDonHangChuaXacNhanLayout);
        pnlTblDonHangChuaXacNhanLayout.setHorizontalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpPhieuNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTblDonHangChuaXacNhanLayout.setVerticalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpPhieuNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTblDonHangChuaXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 223, 973, 460));

        pnlMaPhieuNhap.setBackground(new java.awt.Color(238, 243, 247));
        pnlMaPhieuNhap.setForeground(new java.awt.Color(202, 229, 246));
        pnlMaPhieuNhap.setFocusable(false);

        txtMaPhieuNhap.setBackground(new java.awt.Color(202, 229, 246));
        txtMaPhieuNhap.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMaPhieuNhap.setForeground(new java.awt.Color(192, 192, 192));
        txtMaPhieuNhap.setBorder(null);
        txtMaPhieuNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaPhieuNhapKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaPhieuNhapKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlMaPhieuNhapLayout = new javax.swing.GroupLayout(pnlMaPhieuNhap);
        pnlMaPhieuNhap.setLayout(pnlMaPhieuNhapLayout);
        pnlMaPhieuNhapLayout.setHorizontalGroup(
            pnlMaPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaPhieuNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlMaPhieuNhapLayout.setVerticalGroup(
            pnlMaPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMaPhieuNhapLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlMaPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        pnlNhanVienTaoPhieu.setBackground(new java.awt.Color(238, 243, 247));
        pnlNhanVienTaoPhieu.setForeground(new java.awt.Color(202, 229, 246));
        pnlNhanVienTaoPhieu.setFocusable(false);

        txtNhanVienTaoPhieu.setBackground(new java.awt.Color(202, 229, 246));
        txtNhanVienTaoPhieu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNhanVienTaoPhieu.setForeground(new java.awt.Color(192, 192, 192));
        txtNhanVienTaoPhieu.setBorder(null);
        txtNhanVienTaoPhieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNhanVienTaoPhieuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNhanVienTaoPhieuKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlNhanVienTaoPhieuLayout = new javax.swing.GroupLayout(pnlNhanVienTaoPhieu);
        pnlNhanVienTaoPhieu.setLayout(pnlNhanVienTaoPhieuLayout);
        pnlNhanVienTaoPhieuLayout.setHorizontalGroup(
            pnlNhanVienTaoPhieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienTaoPhieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNhanVienTaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlNhanVienTaoPhieuLayout.setVerticalGroup(
            pnlNhanVienTaoPhieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVienTaoPhieuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNhanVienTaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlNhanVienTaoPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        pnlTaoPhieuNhap.setBackground(new java.awt.Color(238, 243, 247));
        pnlTaoPhieuNhap.setForeground(new java.awt.Color(0, 146, 242));
        pnlTaoPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTaoPhieuNhapMouseClicked(evt);
            }
        });

        lblTaoPhieuNhap.setBackground(new java.awt.Color(0, 146, 242));
        lblTaoPhieuNhap.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTaoPhieuNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblTaoPhieuNhap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTaoPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        lblTaoPhieuNhap.setText("Tạo phiếu nhập");

        javax.swing.GroupLayout pnlTaoPhieuNhapLayout = new javax.swing.GroupLayout(pnlTaoPhieuNhap);
        pnlTaoPhieuNhap.setLayout(pnlTaoPhieuNhapLayout);
        pnlTaoPhieuNhapLayout.setHorizontalGroup(
            pnlTaoPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaoPhieuNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaoPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTaoPhieuNhapLayout.setVerticalGroup(
            pnlTaoPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTaoPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        add(pnlTaoPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 180, 160, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void tblPhieuNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapHangMouseClicked
        int column = tblPhieuNhapHang.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblPhieuNhapHang.getRowHeight();
        if(row < tblPhieuNhapHang.getRowCount() && row >= 0 && column < tblPhieuNhapHang.getColumnCount() && column >= 0){
            String maphieunhap = tblPhieuNhapHang.getValueAt(row, 0).toString();
            currentPhieuNhap = quanlycuahangsach.quanlycuahangsach.PhieuNhapBUS.getByMaPhieuNhap(maphieunhap);
            for(int i=0;i<tblPhieuNhapHang.getColumnCount();i++){
                
                this.removeAll();
                this.setLayout(new BorderLayout());
                this.add(new ChiTietPhieuNhapJPanel(currentPhieuNhap));
                this.validate();
                this.repaint();
            }
        }
    }//GEN-LAST:event_tblPhieuNhapHangMouseClicked

    private void pnlTaoPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTaoPhieuNhapMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(new TaoPhieuNhapJPanel());
            this.validate();
            this.repaint();
        }
    }//GEN-LAST:event_pnlTaoPhieuNhapMouseClicked

    private void txtNhanVienTaoPhieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNhanVienTaoPhieuKeyPressed
       getAdvancedSearch();
    }//GEN-LAST:event_txtNhanVienTaoPhieuKeyPressed

    private void txtNhanVienTaoPhieuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNhanVienTaoPhieuKeyReleased
       getAdvancedSearch();
    }//GEN-LAST:event_txtNhanVienTaoPhieuKeyReleased

    private void txtMaPhieuNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaPhieuNhapKeyPressed
       getAdvancedSearch();
    }//GEN-LAST:event_txtMaPhieuNhapKeyPressed

    private void txtMaPhieuNhapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaPhieuNhapKeyReleased
       getAdvancedSearch();
    }//GEN-LAST:event_txtMaPhieuNhapKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblTaoPhieuNhap;
    private GUI.Rounded pnlMaPhieuNhap;
    private GUI.Rounded pnlNhanVienTaoPhieu;
    private GUI.Rounded pnlTaoPhieuNhap;
    private GUI.Rounded pnlTblDonHangChuaXacNhan;
    private javax.swing.JScrollPane scpPhieuNhapHang;
    private static javax.swing.JTable tblPhieuNhapHang;
    private javax.swing.JTextField txtMaPhieuNhap;
    private javax.swing.JTextField txtNhanVienTaoPhieu;
    // End of variables declaration//GEN-END:variables
}
