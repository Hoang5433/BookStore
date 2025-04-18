/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.PhieuNhapHang;

import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.PhieuNhapHangJPanel;
import static GUI.PhieuNhapHangJPanel.currentPhieuNhap;
import GUI.Sweet.SweetFileChooser;
import GUI.SweetAlert.SweetAlert;
import GUI.Table.RenderTable;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import IO.PDF.HoaDonNhapHangPDF;
import com.itextpdf.text.DocumentException;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import static quanlycuahangsach.Currency.changeCurrency;
import quanlycuahangsach.DateTime;

/**
 *
 * @author admin
 */
public class ChiTietPhieuNhapJPanel extends javax.swing.JPanel {
    Object[] columnNames ={"Mã sản phẩm","Tên sản phẩm","Đơn giá","Số lượng","Tác giả","Nhà xuất bản","Thể loại","Tạm tính"};
    static DefaultTableModel listTableModel;
    public static PhieuNhapDTO currentPhieuNhap;
    public static SanPhamDTO currentSanPham;
    public static ArrayList<ChiTietPhieuNhapDTO> DanhSachChiTietPhieuNhap;
    public void setShadow(){
        pnlThongTin.setShadow(1);
    }  
    public void ShowTable(JTable table,Object[] columnName){
        
        table.setDefaultRenderer(Object.class, new RenderTable());
        
        Object columnNames[] = columnName;
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setModel(listTableModel);  
    }     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTin = new GUI.Rounded();
        lblThongTinHoaDon = new javax.swing.JLabel();
        scpPhieuNhapHang = new javax.swing.JScrollPane();
        tblPhieuNhapHang = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        lblThongTinHoaDon1 = new javax.swing.JLabel();
        pnlThongTinPhieuNhap = new javax.swing.JPanel();
        lblTitleMaNhaCungCap = new javax.swing.JLabel();
        lblTitleNhanVienTaoPhieu = new javax.swing.JLabel();
        lblTitleNgayNhap = new javax.swing.JLabel();
        lblNgayNhap = new javax.swing.JLabel();
        lblMaNhaCungCap = new javax.swing.JLabel();
        lblTitleMaPhieuNhao = new javax.swing.JLabel();
        lblMaPhieuNhap = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblTitleTongTien = new javax.swing.JLabel();
        lblNhanVienTaoPhieu = new javax.swing.JLabel();
        pnlXuatPhieuNhap = new GUI.Rounded();
        lblXuatPhieuNhap = new javax.swing.JLabel();
        pnlQuayVe = new GUI.Rounded();
        lblQuayVe = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongTin.setBackground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setForeground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(920, 750));
        pnlThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThongTinHoaDon.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblThongTinHoaDon.setForeground(new java.awt.Color(0, 146, 242));
        lblThongTinHoaDon.setText("Thông tin phiếu nhập hàng");
        pnlThongTin.add(lblThongTinHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 14, -1, -1));

        scpPhieuNhapHang.setForeground(new java.awt.Color(255, 255, 255));

        tblPhieuNhapHang.setAutoCreateRowSorter(true);
        tblPhieuNhapHang.setForeground(new java.awt.Color(24, 24, 24));
        tblPhieuNhapHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblPhieuNhapHang.setFocusable(false);
        tblPhieuNhapHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblPhieuNhapHang.setRowHeight(25);
        tblPhieuNhapHang.setSelectionBackground(new java.awt.Color(152, 210, 248));
        scpPhieuNhapHang.setViewportView(tblPhieuNhapHang);

        pnlThongTin.add(scpPhieuNhapHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 240, 970, 294));

        lblThongTinHoaDon1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblThongTinHoaDon1.setForeground(new java.awt.Color(0, 146, 242));
        lblThongTinHoaDon1.setText("Chi tiết phiếu nhập hàng");
        pnlThongTin.add(lblThongTinHoaDon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 206, -1, -1));

        pnlThongTinPhieuNhap.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongTinPhieuNhap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleMaNhaCungCap.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleMaNhaCungCap.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaNhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaNhaCungCap.setText("Mã nhà cung cấp:");
        pnlThongTinPhieuNhap.add(lblTitleMaNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        lblTitleNhanVienTaoPhieu.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleNhanVienTaoPhieu.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleNhanVienTaoPhieu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleNhanVienTaoPhieu.setText("Người tạo phiếu:");
        pnlThongTinPhieuNhap.add(lblTitleNhanVienTaoPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        lblTitleNgayNhap.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleNgayNhap.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleNgayNhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleNgayNhap.setText("Ngày nhập:");
        pnlThongTinPhieuNhap.add(lblTitleNgayNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        lblNgayNhap.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblNgayNhap.setForeground(new java.awt.Color(24, 24, 24));
        lblNgayNhap.setText("01/05/2000");
        pnlThongTinPhieuNhap.add(lblNgayNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        lblMaNhaCungCap.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblMaNhaCungCap.setForeground(new java.awt.Color(24, 24, 24));
        lblMaNhaCungCap.setText("12345");
        pnlThongTinPhieuNhap.add(lblMaNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        lblTitleMaPhieuNhao.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleMaPhieuNhao.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaPhieuNhao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao.setText("Mã phiếu nhập:");
        pnlThongTinPhieuNhap.add(lblTitleMaPhieuNhao, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 100, -1));

        lblMaPhieuNhap.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblMaPhieuNhap.setForeground(new java.awt.Color(24, 24, 24));
        lblMaPhieuNhap.setText("12345");
        pnlThongTinPhieuNhap.add(lblMaPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        lblTongTien.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(210, 48, 44));
        lblTongTien.setText("123456789đ");
        pnlThongTinPhieuNhap.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        lblTitleTongTien.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleTongTien.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleTongTien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleTongTien.setText("Tổng tiền:");
        pnlThongTinPhieuNhap.add(lblTitleTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        lblNhanVienTaoPhieu.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblNhanVienTaoPhieu.setForeground(new java.awt.Color(24, 24, 24));
        lblNhanVienTaoPhieu.setText("NV001");
        pnlThongTinPhieuNhap.add(lblNhanVienTaoPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 450, -1));

        pnlXuatPhieuNhap.setBackground(new java.awt.Color(238, 243, 247));
        pnlXuatPhieuNhap.setForeground(new java.awt.Color(0, 146, 242));
        pnlXuatPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXuatPhieuNhapMouseClicked(evt);
            }
        });

        lblXuatPhieuNhap.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblXuatPhieuNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblXuatPhieuNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXuatPhieuNhap.setText("Xuất phiếu nhập");
        lblXuatPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXuatPhieuNhapMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlXuatPhieuNhapLayout = new javax.swing.GroupLayout(pnlXuatPhieuNhap);
        pnlXuatPhieuNhap.setLayout(pnlXuatPhieuNhapLayout);
        pnlXuatPhieuNhapLayout.setHorizontalGroup(
            pnlXuatPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXuatPhieuNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXuatPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlXuatPhieuNhapLayout.setVerticalGroup(
            pnlXuatPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXuatPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        pnlThongTinPhieuNhap.add(pnlXuatPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, -1, -1));

        pnlThongTin.add(pnlThongTinPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 960, 158));

        add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1000, 580));

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
    }// </editor-fold>//GEN-END:initComponents
    private void setData(){
        lblMaPhieuNhap.setText(currentPhieuNhap.getMaPhieuNhap());
        lblMaNhaCungCap.setText(currentPhieuNhap.getMaNhaCungCap());
        lblNgayNhap.setText(DateTime.TimestampToDateString(currentPhieuNhap.getNgayNhap(),1));
        lblTongTien.setText(changeCurrency(currentPhieuNhap.getTongTien())+"đ");
        lblNhanVienTaoPhieu.setText(quanlycuahangsach.quanlycuahangsach.NhanVienBUS.getTenNhanVienByMaNhanVien(currentPhieuNhap.getMaNhanVien()));
    }
    public ChiTietPhieuNhapJPanel(PhieuNhapDTO PhieuNhap) {
        initComponents();
        customTable(tblPhieuNhapHang,scpPhieuNhapHang);
        setJTableColumnsWidth(tblPhieuNhapHang,890,15,30,15,10,5,15,15);  
        pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.currentPhieuNhap = PhieuNhap; 
        setData();
        ShowTable(tblPhieuNhapHang,columnNames,PhieuNhap.getMaPhieuNhap());
    }
    public void ShowTable(JTable table,Object[] columnName,String maphieunhap){
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAll(maphieunhap);
    }
    public static void fetchAll(String maphieunhap)
    {
        listTableModel.setRowCount(0);
        DanhSachChiTietPhieuNhap = quanlycuahangsach.quanlycuahangsach.ChiTietPhieuNhapBUS.getChiTietPhieuNhapByMaPhieuNhap(currentPhieuNhap.getMaPhieuNhap());
        DanhSachChiTietPhieuNhap.forEach(ChiTietPhieuNhap ->{
            Vector row = new Vector();
            currentSanPham = quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(ChiTietPhieuNhap.getMaSanPham());
            row.add(ChiTietPhieuNhap.getMaSanPham());
            row.add(currentSanPham.getTenSanPham());

            row.add(changeCurrency(ChiTietPhieuNhap.getDonGia()));
            row.add(ChiTietPhieuNhap.getSoLuong());
            row.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(currentSanPham.getMaTacGia()));
            row.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(currentSanPham.getMaTheLoai()));
            row.add(changeCurrency(ChiTietPhieuNhap.getThanhTien()));

            row.add(changeCurrency(ChiTietPhieuNhap.getDonGia())+"đ");
            row.add(ChiTietPhieuNhap.getSoLuong());

            listTableModel.addRow(row);         
        });
        tblPhieuNhapHang.setModel(listTableModel);
    }
    private void pnlQuayVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVeMouseClicked
      if (SwingUtilities.isLeftMouseButton(evt)){ 
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new PhieuNhapHangJPanel());
        this.validate();
        this.repaint();  
      }// TODO add your handling code here:
    }//GEN-LAST:event_pnlQuayVeMouseClicked

    private void pnlXuatPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXuatPhieuNhapMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlXuatPhieuNhapMouseClicked

    private void lblXuatPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXuatPhieuNhapMouseClicked
        try {
            String path = SweetFileChooser.PDFFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            HoaDonNhapHangPDF.exportPdf(path, DanhSachChiTietPhieuNhap, currentPhieuNhap);
            SweetAlert.showSweetAlert(new JFrame(), "Hoàn tất", "Xuất dữ liệu thành công", 0);
        } catch (IOException ex) {
            Logger.getLogger(ChiTietPhieuNhapJPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ChiTietPhieuNhapJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblXuatPhieuNhapMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMaNhaCungCap;
    private javax.swing.JLabel lblMaPhieuNhap;
    private javax.swing.JLabel lblNgayNhap;
    private javax.swing.JLabel lblNhanVienTaoPhieu;
    private javax.swing.JLabel lblQuayVe;
    private javax.swing.JLabel lblThongTinHoaDon;
    private javax.swing.JLabel lblThongTinHoaDon1;
    private javax.swing.JLabel lblTitleMaNhaCungCap;
    private javax.swing.JLabel lblTitleMaPhieuNhao;
    private javax.swing.JLabel lblTitleNgayNhap;
    private javax.swing.JLabel lblTitleNhanVienTaoPhieu;
    private javax.swing.JLabel lblTitleTongTien;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblXuatPhieuNhap;
    private GUI.Rounded pnlQuayVe;
    private GUI.Rounded pnlThongTin;
    private javax.swing.JPanel pnlThongTinPhieuNhap;
    private GUI.Rounded pnlXuatPhieuNhap;
    private javax.swing.JScrollPane scpPhieuNhapHang;
    private static javax.swing.JTable tblPhieuNhapHang;
    // End of variables declaration//GEN-END:variables
}
