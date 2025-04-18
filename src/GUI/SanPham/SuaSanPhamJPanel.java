/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SanPham;

import static GUI.SanPhamJPanel.currentSanPham;
import GUI.Sweet.SweetComboBox;
import static GUI.Sweet.SweetFileChooser.PATH_SANPHAM;
import static GUI.Sweet.SweetImage.readImageFromFilePath;
import static GUI.Sweet.SweetImage.resizeImage;
import static GUI.Sweet.SweetImage.saveImage;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import static quanlycuahangsach.Currency.changeCurrency;
import static quanlycuahangsach.Regexp.DonGia;

/**
 *
 * @author admin
 */
public class SuaSanPhamJPanel extends javax.swing.JPanel {
    public ImageIcon imgAnhDaiDien;
    /**
     * Creates new form SuaSanPhamJPanel
     */
    SweetComboBox cbxTheLoai,cbxTacGia,cbxNhaXuatBan;
    public void setComboBox(){
        String[] theloai = quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenTheLoaiNoTitle();
        cbxTheLoai = new SweetComboBox("#CAE5F6","#181818",0,0,190,30,theloai);
        pnlTheLoai.add(cbxTheLoai);
        String[] tacgia = quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenTacGiaNoTitle();
        cbxTacGia = new SweetComboBox("#CAE5F6","#181818",0,0,190,30,tacgia);
        pnlTacGia.add(cbxTacGia);
        String[] nhaxuatban = quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getTenNhaXuatBan();
        cbxNhaXuatBan = new SweetComboBox("#CAE5F6","#181818",0,0,190,30,nhaxuatban);
        cbxTacGia.setSelectedItem(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(currentSanPham.getMaTacGia()));
        cbxTheLoai.setSelectedItem(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(currentSanPham.getMaTheLoai()));
        cbxNhaXuatBan.setSelectedItem(quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getTenByMa(currentSanPham.getMaNhaXuatBan()));
        pnlNhaXuatBan.add(cbxNhaXuatBan);        
        
    }      
    public void setShadow(){
        pnlThongTin.setShadow(1);
    }      
    public SuaSanPhamJPanel() {
        initComponents();
        pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        
        setShadow();
        setComboBox();
        setData();
        
    }

    public void setData(){
      lblMaSanPham.setText(currentSanPham.getMaSanPham());
      txtTenSanPham.setText(currentSanPham.getTenSanPham());
      txtDonGia.setText(changeCurrency(currentSanPham.getGia()));
        Image imageScale = readImageFromFilePath(PATH_SANPHAM + currentSanPham.getImageSource(), 290, 350);
        imgAnhDaiDien = new ImageIcon(imageScale);
        lblAnh.setIcon(imgAnhDaiDien);      
    }
    public void updateSanPham(){
        currentSanPham.setTenSanPham(txtTenSanPham.getText());
        currentSanPham.setGia(txtDonGia.getText().replace(".",""));
        currentSanPham.setMaNhaXuatBan(quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getMaByName(cbxNhaXuatBan.getSelectedItem().toString()));
        currentSanPham.setMaTacGia(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getMaByName(cbxTacGia.getSelectedItem().toString()));
        currentSanPham.setMaTheLoai(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getMaByName(cbxTheLoai.getSelectedItem().toString()));
        currentSanPham.setImageSource(saveImage(lblAnh.getIcon(), lblMaSanPham.getText()));
    }
    public boolean isNotFill(){
        if(txtDonGia.getText().equals("") || txtTenSanPham.equals("")){
            return true;      
        }
        return false;
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
        lblQuayVe2 = new javax.swing.JLabel();
        pnlThongTin = new GUI.Rounded();
        lblAddImage1 = new javax.swing.JLabel();
        lblTitleMaSanPham = new javax.swing.JLabel();
        lblTitleTenSanPham = new javax.swing.JLabel();
        lblTitleDonGia = new javax.swing.JLabel();
        lblTitleSize = new javax.swing.JLabel();
        lblTitleNhaCungCap = new javax.swing.JLabel();
        lblTitleThuongHieu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        txtTenSanPham = new javax.swing.JTextField();
        lblMaSanPham = new javax.swing.JLabel();
        pnlTheLoai = new javax.swing.JPanel();
        pnlTacGia = new javax.swing.JPanel();
        pnlNhaXuatBan = new javax.swing.JPanel();
        pnlXacNhan = new GUI.Rounded();
        lblXacNhan = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlQuayVe.setBackground(new java.awt.Color(238, 243, 247));
        pnlQuayVe.setForeground(new java.awt.Color(0, 146, 242));
        pnlQuayVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlQuayVeMouseClicked(evt);
            }
        });

        lblQuayVe2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblQuayVe2.setForeground(new java.awt.Color(255, 255, 255));
        lblQuayVe2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        lblQuayVe2.setText("  Quay về");

        javax.swing.GroupLayout pnlQuayVeLayout = new javax.swing.GroupLayout(pnlQuayVe);
        pnlQuayVe.setLayout(pnlQuayVeLayout);
        pnlQuayVeLayout.setHorizontalGroup(
            pnlQuayVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuayVeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuayVe2, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlQuayVeLayout.setVerticalGroup(
            pnlQuayVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQuayVe2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        add(pnlQuayVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        pnlThongTin.setBackground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setForeground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(920, 750));
        pnlThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAddImage1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblAddImage1.setForeground(new java.awt.Color(0, 52, 102));
        lblAddImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddImage1.setText("Thêm hình ảnh");
        lblAddImage1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 52, 102)));
        lblAddImage1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblAddImage1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddImage1MouseClicked(evt);
            }
        });
        pnlThongTin.add(lblAddImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 100, -1));

        lblTitleMaSanPham.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTitleMaSanPham.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaSanPham.setText("Mã sản phẩm:");
        lblTitleMaSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTitleMaSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTitleMaSanPhamMouseClicked(evt);
            }
        });
        pnlThongTin.add(lblTitleMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 100, 40));

        lblTitleTenSanPham.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTitleTenSanPham.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleTenSanPham.setText("Tên sản phẩm:");
        lblTitleTenSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlThongTin.add(lblTitleTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 110, 30));

        lblTitleDonGia.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTitleDonGia.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleDonGia.setText("Đơn giá:");
        lblTitleDonGia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlThongTin.add(lblTitleDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 100, 30));

        lblTitleSize.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTitleSize.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleSize.setText("Thể loại:");
        lblTitleSize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlThongTin.add(lblTitleSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, 70, 30));

        lblTitleNhaCungCap.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTitleNhaCungCap.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleNhaCungCap.setText("Tác giả:");
        lblTitleNhaCungCap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlThongTin.add(lblTitleNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, 60, 30));

        lblTitleThuongHieu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTitleThuongHieu.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleThuongHieu.setText("Nhà xuất bản:");
        lblTitleThuongHieu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlThongTin.add(lblTitleThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, 100, 30));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 146, 242));
        jLabel1.setText("đ");
        pnlThongTin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 30, 30));

        txtDonGia.setBackground(new java.awt.Color(202, 229, 246));
        txtDonGia.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtDonGia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDonGia.setBorder(null);
        pnlThongTin.add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 190, 30));

        txtTenSanPham.setBackground(new java.awt.Color(202, 229, 246));
        txtTenSanPham.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtTenSanPham.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTenSanPham.setBorder(null);
        pnlThongTin.add(txtTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 190, 30));

        lblMaSanPham.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMaSanPham.setForeground(new java.awt.Color(0, 146, 242));
        lblMaSanPham.setText("#213");
        pnlThongTin.add(lblMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, -1, 30));

        pnlTheLoai.setBackground(new java.awt.Color(202, 229, 246));

        javax.swing.GroupLayout pnlTheLoaiLayout = new javax.swing.GroupLayout(pnlTheLoai);
        pnlTheLoai.setLayout(pnlTheLoaiLayout);
        pnlTheLoaiLayout.setHorizontalGroup(
            pnlTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        pnlTheLoaiLayout.setVerticalGroup(
            pnlTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 190, 30));

        pnlTacGia.setBackground(new java.awt.Color(202, 229, 246));

        javax.swing.GroupLayout pnlTacGiaLayout = new javax.swing.GroupLayout(pnlTacGia);
        pnlTacGia.setLayout(pnlTacGiaLayout);
        pnlTacGiaLayout.setHorizontalGroup(
            pnlTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        pnlTacGiaLayout.setVerticalGroup(
            pnlTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, 190, -1));

        pnlNhaXuatBan.setBackground(new java.awt.Color(202, 229, 246));

        javax.swing.GroupLayout pnlNhaXuatBanLayout = new javax.swing.GroupLayout(pnlNhaXuatBan);
        pnlNhaXuatBan.setLayout(pnlNhaXuatBanLayout);
        pnlNhaXuatBanLayout.setHorizontalGroup(
            pnlNhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        pnlNhaXuatBanLayout.setVerticalGroup(
            pnlNhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, -1, -1));

        pnlXacNhan.setBackground(new java.awt.Color(238, 243, 247));
        pnlXacNhan.setForeground(new java.awt.Color(34, 212, 52));
        pnlXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXacNhanMouseClicked(evt);
            }
        });

        lblXacNhan.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        lblXacNhan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXacNhan.setText("Cập nhật");

        javax.swing.GroupLayout pnlXacNhanLayout = new javax.swing.GroupLayout(pnlXacNhan);
        pnlXacNhan.setLayout(pnlXacNhanLayout);
        pnlXacNhanLayout.setHorizontalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(lblXacNhan)
                .addGap(19, 19, 19))
        );
        pnlXacNhanLayout.setVerticalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlThongTin.add(pnlXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 520, 120, -1));

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlThongTin.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 290, 350));

        add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 990, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlQuayVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVeMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
            if (GUI.SweetAlert.SweetAlert.showSweetOption(new javax.swing.JFrame(), "Thông báo", "Xác nhận chỉnh sửa?", 0) == 1) {
                if (isNotFill()) {
                    GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Không được bỏ trống", 0);
                } else {
                    updateSanPham();
                    if (quanlycuahangsach.quanlycuahangsach.SachBUS.edit(currentSanPham)) {
                        this.removeAll();
                        this.setLayout(new BorderLayout());
                        this.add(new ChiTietSanPhamJPanel());
                        this.validate();
                        this.repaint();
                    }
                }
            }
        }      // TODO add your handling code here:
    }//GEN-LAST:event_pnlQuayVeMouseClicked

    private void lblAddImage1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddImage1MouseClicked
         if (SwingUtilities.isLeftMouseButton(evt)) {
            try {
                File file =  GUI.Sweet.SweetFileChooser.ImageFileChooser();
                if(file!=null)
                lblAnh.setIcon(resizeImage(ImageIO.read(file),290,350));
                GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.setImgSource(saveImage(lblAnh.getIcon(),lblMaSanPham.getText()));
                Image imageScale = readImageFromFilePath(PATH_SANPHAM+currentSanPham.getImageSource(),290,350);
                imgAnhDaiDien = new ImageIcon(imageScale);
                lblAnh.setIcon(imgAnhDaiDien);
            } catch (IOException ex) {
                Logger.getLogger(SuaSanPhamJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lblAddImage1MouseClicked

    private void lblTitleMaSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTitleMaSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTitleMaSanPhamMouseClicked

    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
            if (GUI.SweetAlert.SweetAlert.showSweetOption(new javax.swing.JFrame(), "Thông báo", "Xác nhận chỉnh sửa?", 0) == 1) {
                if (isNotFill()) {
                    GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Không được bỏ trống", 0);
                } else {
                    updateSanPham();
                    if (quanlycuahangsach.quanlycuahangsach.SachBUS.edit(currentSanPham)) {
                        this.removeAll();
                        this.setLayout(new BorderLayout());
                        this.add(new ChiTietSanPhamJPanel());
                        this.validate();
                        this.repaint();
                    }
                }
            }
        } 
    }//GEN-LAST:event_pnlXacNhanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAddImage1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblMaSanPham;
    private javax.swing.JLabel lblQuayVe2;
    private javax.swing.JLabel lblTitleDonGia;
    private javax.swing.JLabel lblTitleMaSanPham;
    private javax.swing.JLabel lblTitleNhaCungCap;
    private javax.swing.JLabel lblTitleSize;
    private javax.swing.JLabel lblTitleTenSanPham;
    private javax.swing.JLabel lblTitleThuongHieu;
    private javax.swing.JLabel lblXacNhan;
    private javax.swing.JPanel pnlNhaXuatBan;
    private GUI.Rounded pnlQuayVe;
    private javax.swing.JPanel pnlTacGia;
    private javax.swing.JPanel pnlTheLoai;
    private GUI.Rounded pnlThongTin;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}
