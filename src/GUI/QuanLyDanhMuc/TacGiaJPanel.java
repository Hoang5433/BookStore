/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.QuanLyDanhMuc;

import DTO.TacGiaDTO;
import GUI.QuanLyDanhMuc.Popup.SuaJFrame;
import GUI.QuanLyDanhMucJPanel;
import GUI.Sweet.SweetFileChooser;
import GUI.SweetAlert.SweetAlert;
import GUI.Table.RenderTable;
import GUI.Table.setIconForCell;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import IO.Excel.TacGiaExcel;
import IO.Excel.NhaXuatBanExcel;
import IO.Excel.TacGiaExcel;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class TacGiaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form TheLoaiJPanel
     */
    public TacGiaDTO currentTacGia = new TacGiaDTO();
    static DefaultTableModel listTableModel;
    public void setIcon(){
        
        for(int j = 0 ; j<tblTacGia.getRowCount();j++){
                tblTacGia.getColumnModel().getColumn(2).setCellRenderer(new setIconForCell(tblTacGia,j,2));   
        }
    }     
    public void setShadow(){
        pnlTheLoai.setShadow(1);
        pnlThemTheLoai.setShadow(1);
    }
    public void ShowTable(JTable table,Object[] columnName){
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAll();
        setIcon();
        lblMaTacGia.setText(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getLatest());
    }    
    public static void fetchAll(){
        String edit = "Sửa";
        listTableModel.setRowCount(0);
        quanlycuahangsach.quanlycuahangsach.TacGiaBUS.TacGiaList.forEach(TacGia ->{
            Vector row = new Vector();
            row.add(TacGia.getMaTacGia());
            row.add(TacGia.getTenTacGia());
            row.add(edit);
            listTableModel.addRow(row);
        });
        tblTacGia.setModel(listTableModel);
    }
    Object[] columnNames ={"Mã tác giả","Tên tác giả",""};
    public TacGiaJPanel() {
        initComponents();
        customTable(tblTacGia,scpTacGia);
        ShowTable(tblTacGia,columnNames);
        
        setJTableColumnsWidth(tblTacGia,958,25,65,10);
        setShadow();
        
        pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        pnlThemTheLoai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlQuayVe = new GUI.Rounded();
        lblQuayVe = new javax.swing.JLabel();
        pnlThemTheLoai = new GUI.Rounded();
        lblTitleMaDonHang = new javax.swing.JLabel();
        lblTitleHoTen = new javax.swing.JLabel();
        lblMaTacGia = new javax.swing.JLabel();
        pnlThem = new GUI.Rounded();
        lblThem = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTacGia = new javax.swing.JTextField();
        pnlXuatDuLieu = new GUI.Rounded();
        lblXuatHoaDon1 = new javax.swing.JLabel();
        pnlNhapDuLieu = new GUI.Rounded();
        lblXuatHoaDon = new javax.swing.JLabel();
        pnlTheLoai = new GUI.Rounded();
        scpTacGia = new javax.swing.JScrollPane();
        tblTacGia = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        lblDonHang = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlQuayVe.setBackground(new java.awt.Color(238, 243, 247));
        pnlQuayVe.setForeground(new java.awt.Color(0, 146, 242));
        pnlQuayVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlQuayVeMouseClicked(evt);
            }
        });

        lblQuayVe.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblQuayVe.setForeground(new java.awt.Color(255, 255, 255));
        lblQuayVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        lblQuayVe.setText("  Quay về");

        javax.swing.GroupLayout pnlQuayVeLayout = new javax.swing.GroupLayout(pnlQuayVe);
        pnlQuayVe.setLayout(pnlQuayVeLayout);
        pnlQuayVeLayout.setHorizontalGroup(
            pnlQuayVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuayVeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuayVe, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlQuayVeLayout.setVerticalGroup(
            pnlQuayVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQuayVe, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        add(pnlQuayVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        pnlThemTheLoai.setBackground(new java.awt.Color(238, 243, 247));
        pnlThemTheLoai.setForeground(new java.awt.Color(238, 243, 247));

        lblTitleMaDonHang.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleMaDonHang.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaDonHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaDonHang.setText("Mã tác giả:");

        lblTitleHoTen.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleHoTen.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleHoTen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleHoTen.setText("Tác giả:");

        lblMaTacGia.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblMaTacGia.setForeground(new java.awt.Color(0, 146, 242));
        lblMaTacGia.setText("12345");

        pnlThem.setBackground(new java.awt.Color(255, 255, 255));
        pnlThem.setForeground(new java.awt.Color(0, 146, 242));
        pnlThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThemMouseClicked(evt);
            }
        });

        lblThem.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblThem.setForeground(new java.awt.Color(255, 255, 255));
        lblThem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThem.setText("Thêm");

        javax.swing.GroupLayout pnlThemLayout = new javax.swing.GroupLayout(pnlThem);
        pnlThem.setLayout(pnlThemLayout);
        pnlThemLayout.setHorizontalGroup(
            pnlThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblThem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlThemLayout.setVerticalGroup(
            pnlThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThem, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 52, 102));
        jLabel3.setText("Thêm tác giả");

        txtTacGia.setBackground(new java.awt.Color(202, 229, 246));
        txtTacGia.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtTacGia.setForeground(new java.awt.Color(24, 24, 24));
        txtTacGia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTacGia.setBorder(null);

        pnlXuatDuLieu.setBackground(new java.awt.Color(238, 243, 247));
        pnlXuatDuLieu.setForeground(new java.awt.Color(0, 146, 242));
        pnlXuatDuLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXuatDuLieuMouseClicked(evt);
            }
        });

        lblXuatHoaDon1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblXuatHoaDon1.setForeground(new java.awt.Color(255, 255, 255));
        lblXuatHoaDon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXuatHoaDon1.setText("Xuất dữ liệu");

        javax.swing.GroupLayout pnlXuatDuLieuLayout = new javax.swing.GroupLayout(pnlXuatDuLieu);
        pnlXuatDuLieu.setLayout(pnlXuatDuLieuLayout);
        pnlXuatDuLieuLayout.setHorizontalGroup(
            pnlXuatDuLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXuatDuLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXuatHoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlXuatDuLieuLayout.setVerticalGroup(
            pnlXuatDuLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXuatHoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        pnlNhapDuLieu.setBackground(new java.awt.Color(238, 243, 247));
        pnlNhapDuLieu.setForeground(new java.awt.Color(0, 146, 242));
        pnlNhapDuLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNhapDuLieuMouseClicked(evt);
            }
        });

        lblXuatHoaDon.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblXuatHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblXuatHoaDon.setText("Nhập dữ liệu");

        javax.swing.GroupLayout pnlNhapDuLieuLayout = new javax.swing.GroupLayout(pnlNhapDuLieu);
        pnlNhapDuLieu.setLayout(pnlNhapDuLieuLayout);
        pnlNhapDuLieuLayout.setHorizontalGroup(
            pnlNhapDuLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhapDuLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXuatHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlNhapDuLieuLayout.setVerticalGroup(
            pnlNhapDuLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXuatHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlThemTheLoaiLayout = new javax.swing.GroupLayout(pnlThemTheLoai);
        pnlThemTheLoai.setLayout(pnlThemTheLoaiLayout);
        pnlThemTheLoaiLayout.setHorizontalGroup(
            pnlThemTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemTheLoaiLayout.createSequentialGroup()
                .addGap(0, 67, Short.MAX_VALUE)
                .addGroup(pnlThemTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleMaDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleHoTen))
                .addGap(18, 18, 18)
                .addGroup(pnlThemTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThemTheLoaiLayout.createSequentialGroup()
                        .addComponent(lblMaTacGia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 625, Short.MAX_VALUE)
                        .addComponent(pnlThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThemTheLoaiLayout.createSequentialGroup()
                        .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlXuatDuLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(pnlNhapDuLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
            .addGroup(pnlThemTheLoaiLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThemTheLoaiLayout.setVerticalGroup(
            pnlThemTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemTheLoaiLayout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThemTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleMaDonHang)
                    .addComponent(lblMaTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThemTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleHoTen)
                    .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
            .addGroup(pnlThemTheLoaiLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlThemTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlXuatDuLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlNhapDuLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(pnlThemTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 973, 140));

        pnlTheLoai.setBackground(new java.awt.Color(238, 243, 247));
        pnlTheLoai.setForeground(new java.awt.Color(238, 243, 247));
        pnlTheLoai.setPreferredSize(new java.awt.Dimension(920, 750));

        scpTacGia.setForeground(new java.awt.Color(255, 255, 255));

        tblTacGia.setAutoCreateRowSorter(true);
        tblTacGia.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblTacGia.setForeground(new java.awt.Color(18, 18, 18));
        tblTacGia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblTacGia.setFocusable(false);
        tblTacGia.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblTacGia.setRowHeight(25);
        tblTacGia.setSelectionBackground(new java.awt.Color(152, 210, 248));
        tblTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTacGiaMouseClicked(evt);
            }
        });
        scpTacGia.setViewportView(tblTacGia);

        lblDonHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDonHang.setForeground(new java.awt.Color(0, 52, 102));
        lblDonHang.setText("Danh sách tác giả");

        javax.swing.GroupLayout pnlTheLoaiLayout = new javax.swing.GroupLayout(pnlTheLoai);
        pnlTheLoai.setLayout(pnlTheLoaiLayout);
        pnlTheLoaiLayout.setHorizontalGroup(
            pnlTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTheLoaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                    .addGroup(pnlTheLoaiLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblDonHang)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlTheLoaiLayout.setVerticalGroup(
            pnlTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTheLoaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDonHang)
                .addGap(18, 18, 18)
                .addComponent(scpTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 970, 420));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlQuayVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVeMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(new QuanLyDanhMucJPanel());
            this.validate();
            this.repaint();
        }// TODO add your handling code here:
    }//GEN-LAST:event_pnlQuayVeMouseClicked

    private void pnlThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThemMouseClicked
        if(SwingUtilities.isLeftMouseButton(evt))
            add();
    }//GEN-LAST:event_pnlThemMouseClicked

    public void setCurrentTacGia(){
        currentTacGia.setMaTacGia(lblMaTacGia.getText());
        currentTacGia.setTenTacGia(txtTacGia.getText());
    }
    private boolean isFill(){
    if(txtTacGia.getText().equals("")) return false;

    return true;
    }
    public void add(){
        if(GUI.SweetAlert.SweetAlert.showSweetOption(new javax.swing.JFrame(), "Xác nhận", "Thêm mới?",0)==1){
        if(!isFill()){GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Không được bỏ trống",1); return;}
        setCurrentTacGia();
        if(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.add(currentTacGia))
        {
        fetchAll();
        GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Thành công","Thêm mới thành công",1);
        lblMaTacGia.setText(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getLatest());
        fillBlank();
        }
        }
    }
    public void fillBlank(){
        txtTacGia.setText("");
    } 
    private void tblTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTacGiaMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
        int column = tblTacGia.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblTacGia.getRowHeight();

        if(row < tblTacGia.getRowCount() && row >= 0 && column < tblTacGia.getColumnCount() && column >= 0){
            currentTacGia = quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getByMaTacGia(tblTacGia.getModel().getValueAt(row,0).toString());
        }
        if(tblTacGia.getValueAt(row,column).toString().equalsIgnoreCase("Sửa")){ 
                           new SuaJFrame(1,currentTacGia).setVisible(true);

            }  
        }
    }//GEN-LAST:event_tblTacGiaMouseClicked

    private void pnlXuatDuLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXuatDuLieuMouseClicked
        try {
            String path = SweetFileChooser.ExcelFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            TacGiaExcel.ExportExcelFile(path, quanlycuahangsach.quanlycuahangsach.TacGiaBUS.TacGiaList);
            SweetAlert.showSweetAlert(new JFrame(), "Thành Công", "Xuất dữ liệu thành công", 0);
        } catch (IOException ex) {
            Logger.getLogger(TacGiaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlXuatDuLieuMouseClicked

    private void pnlNhapDuLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNhapDuLieuMouseClicked
       try {
            String path = SweetFileChooser.ExcelFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            TacGiaExcel.ImportExcelFile(path, 2).forEach(tacgia->{
               if(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.add(tacgia)==false);
               tacgia.setMaTacGia(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getLatest());
               quanlycuahangsach.quanlycuahangsach.TacGiaBUS.add(tacgia);
            });
            SweetAlert.showSweetAlert(new JFrame(), "Thành Công", "Nhập dữ liệu thành công", 0);
        } catch (IOException ex) {
            Logger.getLogger(TacGiaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       fetchAll();
    }//GEN-LAST:event_pnlNhapDuLieuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblMaTacGia;
    private javax.swing.JLabel lblQuayVe;
    private javax.swing.JLabel lblThem;
    private javax.swing.JLabel lblTitleHoTen;
    private javax.swing.JLabel lblTitleMaDonHang;
    private javax.swing.JLabel lblXuatHoaDon;
    private javax.swing.JLabel lblXuatHoaDon1;
    private GUI.Rounded pnlNhapDuLieu;
    private GUI.Rounded pnlQuayVe;
    private GUI.Rounded pnlTheLoai;
    private GUI.Rounded pnlThem;
    private GUI.Rounded pnlThemTheLoai;
    private GUI.Rounded pnlXuatDuLieu;
    private javax.swing.JScrollPane scpTacGia;
    private static javax.swing.JTable tblTacGia;
    private javax.swing.JTextField txtTacGia;
    // End of variables declaration//GEN-END:variables
}
