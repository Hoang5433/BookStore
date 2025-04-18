/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.NhanVien;

import DTO.AccountDTO;
import DTO.NhanVienDTO;
import GUI.Sweet.SweetComboBox;
import static GUI.Sweet.SweetFileChooser.FileExists;
import static GUI.Sweet.SweetFileChooser.PATH_NHANVIEN;
import static GUI.Sweet.SweetImage.readImageFromFilePath;
import static GUI.Sweet.SweetImage.resizeImage;
import static GUI.Sweet.SweetImage.saveImage;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import quanlycuahangsach.Currency;
import static quanlycuahangsach.DateTime.TimestampToDateString;

/**
 *
 * @author admin
 */
public class SuaNhanVienJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SuaNhanVienJPanel
     */
    public ArrayList<javax.swing.AbstractButton> arrButton = new ArrayList<>();
    public ButtonGroup groupGioiTinh = new ButtonGroup();
    SweetComboBox cbxTrangThai,cbxCaLam;
    public ImageIcon imgAnhDaiDien;
    public Currency currency;
    
    public void setComboBox() {
    String[] trangthai={"Còn làm","Nghỉ làm"},calam={"Ca sáng","Ca chiều","Ca tối"};
    cbxTrangThai = new SweetComboBox("#CAE5F6", "#181818", 0, 0, 170, 30, trangthai);
    cbxCaLam = new SweetComboBox("#CAE5F6", "#181818", 0, 0, 170, 30, calam);
    cbxTrangThai.setSelectedItem(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getTrangThai());
    cbxCaLam.setSelectedItem(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getCaLam());
    pnlCaLam.add(cbxCaLam);
    pnlTrangThai.add(cbxTrangThai);
    
    }

    public void setShadow() {
        pnlThongTin.setShadow(1);
        pnlTacVu.setShadow(1);

    }

    public void initEvent() {
        arrButton.add(cbxTongQuan);
        arrButton.add(cbxDonHang);
        arrButton.add(cbxSanPham);
        arrButton.add(cbxKhachHang);
        arrButton.add(cbxDoanhThu);
        arrButton.add(cbxThongKe);
        arrButton.add(cbxKhuyenMai);
        arrButton.add(cbxNhapHang);
        arrButton.add(cbxQuanLyNhanVien);
        arrButton.add(cbxQuanLyDanhMuc);
        arrButton.add(radNam);
        arrButton.add(radNu);
        for (javax.swing.AbstractButton checkbox : arrButton) {
            checkbox.setActionCommand(checkbox.getText());
            checkbox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ie) {
                    javax.swing.AbstractButton currentItem = (javax.swing.AbstractButton) ie.getItem();
                    if (ie.getStateChange() == 1) {
                        currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
                    } else {
                        currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png")));
                    }
                }
            });
        }

    }

    public SuaNhanVienJPanel() {

        initComponents();
        initEvent();
        setShadow();

        groupGioiTinh.add(radNam);
        groupGioiTinh.add(radNu);

        setData();

        pnlXacNhan2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblResetMatkhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setRole() {
        String Role = GUI.NhanVien.ChiTietNhanVienJPanel.Account.getRole();
        int i;
        for (i = 0; i < Role.length(); i++) {
            if (Role.codePointAt(i) == 49) {
                arrButton.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
                arrButton.get(i).setSelected(true);
            }
        }
        for (i = i - 1; i < arrButton.size(); i++) {
            String Command = arrButton.get(i).getActionCommand();
            if (Command.equals(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getGioiTinh()) || Command.equals(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getCaLam()) || Command.equals(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getTrangThai())) {
                arrButton.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
                arrButton.get(i).setSelected(true);
            }
        }

    }

    public String getRole() {
        String Role = "";
        for (int i = 0; i < GUI.NhanVien.ChiTietNhanVienJPanel.Account.getRole().length()-1; i++) {
            if (arrButton.get(i).isSelected()) {
                Role += "1";
            } else {
                Role += "0";
            }
        }
        return Role += "1";
    }

    private void setNhanVien() {
    
        GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.setHoTen(txtHoTen.getText());
        GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.setNamSinh(txtNamSinh.getText());
        GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.setSoDienThoai(txtSoDienThoai.getText());
        GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.setDiaChi(txtDiaChi.getText());
        GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.setLuong(txtLuong.getText().replace(".",""));
        GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.setGioiTinh(groupGioiTinh.getSelection().getActionCommand());
        GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.setImgSource(saveImage(lblAnh.getIcon(), lblMaNhanVien.getText()));
        GUI.NhanVien.ChiTietNhanVienJPanel.Account.setRole(getRole());
        
        GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.setTrangThai(cbxTrangThai.getSelectedItem().toString());
        GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.setCaLam(cbxCaLam.getSelectedItem().toString());

    }

    public void setData() {
        lblMaNhanVien.setText(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getMaNhanVien());
        txtHoTen.setText(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getHoTen());
        txtNamSinh.setText(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getNamSinh());
        txtDiaChi.setText(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getDiaChi());
        txtSoDienThoai.setText(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getSoDienThoai());
        Image imageScale = readImageFromFilePath(PATH_NHANVIEN + GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getImgSource(), 180, 240);
        imgAnhDaiDien = new ImageIcon(imageScale);
        lblAnh.setIcon(imgAnhDaiDien);
        String luong = currency.changeCurrency(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getLuong());
        txtLuong.setText(luong);
        lblNgayVaoLam.setText(TimestampToDateString(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getNgayVaoLam(), 1));

        setRole();
        setComboBox();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTin = new GUI.Rounded();
        lblTrangThai = new javax.swing.JLabel();
        pnlTrangThai = new javax.swing.JPanel();
        pnlCaLam = new javax.swing.JPanel();
        lblDonHang = new javax.swing.JLabel();
        lblTrangThai1 = new javax.swing.JLabel();
        lblTrangThai2 = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JLabel();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        lblNam = new javax.swing.JLabel();
        lblDienThoai = new javax.swing.JLabel();
        lblDienThoai1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        lblResetMatkhau = new javax.swing.JLabel();
        lblDonHang1 = new javax.swing.JLabel();
        pnlTimKiemHoTenSDT = new GUI.Rounded();
        txtLuong = new javax.swing.JTextField();
        lblMaNhanVien = new javax.swing.JLabel();
        lblDienThoai2 = new javax.swing.JLabel();
        lblAddImage = new javax.swing.JLabel();
        pnlTimKiemHoTenSDT1 = new GUI.Rounded();
        txtHoTen = new javax.swing.JTextField();
        pnlTimKiemHoTenSDT2 = new GUI.Rounded();
        txtNamSinh = new javax.swing.JTextField();
        pnlTimKiemHoTenSDT3 = new GUI.Rounded();
        txtSoDienThoai = new javax.swing.JTextField();
        lblDienThoai3 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        lblDonHang4 = new javax.swing.JLabel();
        lblNgayVaoLam = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlQuayVe = new GUI.Rounded();
        lblQuayVe2 = new javax.swing.JLabel();
        pnlXacNhan2 = new GUI.Rounded();
        lblThem2 = new javax.swing.JLabel();
        pnlTacVu = new GUI.Rounded();
        cbxTongQuan = new javax.swing.JCheckBox();
        cbxDonHang = new javax.swing.JCheckBox();
        cbxSanPham = new javax.swing.JCheckBox();
        cbxKhachHang = new javax.swing.JCheckBox();
        cbxDoanhThu = new javax.swing.JCheckBox();
        cbxThongKe = new javax.swing.JCheckBox();
        cbxKhuyenMai = new javax.swing.JCheckBox();
        cbxNhapHang = new javax.swing.JCheckBox();
        cbxQuanLyNhanVien = new javax.swing.JCheckBox();
        cbxQuanLyDanhMuc = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongTin.setBackground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setForeground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTrangThai.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(0, 52, 102));
        lblTrangThai.setText("Họ và tên:");
        pnlThongTin.add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, 60));

        pnlTrangThai.setBackground(new java.awt.Color(202, 229, 246));
        pnlTrangThai.setForeground(new java.awt.Color(202, 229, 246));

        javax.swing.GroupLayout pnlTrangThaiLayout = new javax.swing.GroupLayout(pnlTrangThai);
        pnlTrangThai.setLayout(pnlTrangThaiLayout);
        pnlTrangThaiLayout.setHorizontalGroup(
            pnlTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        pnlTrangThaiLayout.setVerticalGroup(
            pnlTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 170, 30));

        pnlCaLam.setBackground(new java.awt.Color(202, 229, 246));
        pnlCaLam.setForeground(new java.awt.Color(202, 229, 246));

        javax.swing.GroupLayout pnlCaLamLayout = new javax.swing.GroupLayout(pnlCaLam);
        pnlCaLam.setLayout(pnlCaLamLayout);
        pnlCaLamLayout.setHorizontalGroup(
            pnlCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        pnlCaLamLayout.setVerticalGroup(
            pnlCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlCaLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 170, 30));

        lblDonHang.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDonHang.setForeground(new java.awt.Color(0, 52, 102));
        lblDonHang.setText("Mật khẩu:");
        pnlThongTin.add(lblDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, -1, 30));

        lblTrangThai1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTrangThai1.setForeground(new java.awt.Color(0, 52, 102));
        lblTrangThai1.setText("Trạng thái:");
        pnlThongTin.add(lblTrangThai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, 30));

        lblTrangThai2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTrangThai2.setForeground(new java.awt.Color(0, 52, 102));
        lblTrangThai2.setText("Mã nhân viên:");
        pnlThongTin.add(lblTrangThai2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, 30));

        txtGioiTinh.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        txtGioiTinh.setForeground(new java.awt.Color(0, 52, 102));
        txtGioiTinh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtGioiTinh.setText("Giới tính:");
        pnlThongTin.add(txtGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, -1, -1));

        radNam.setBackground(new java.awt.Color(255, 255, 255));
        radNam.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        radNam.setForeground(new java.awt.Color(24, 24, 24));
        radNam.setText("Nam");
        radNam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlThongTin.add(radNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 67, -1));

        radNu.setBackground(new java.awt.Color(255, 255, 255));
        radNu.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        radNu.setForeground(new java.awt.Color(24, 24, 24));
        radNu.setText("Nữ");
        radNu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlThongTin.add(radNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, -1));

        lblNam.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblNam.setForeground(new java.awt.Color(0, 52, 102));
        lblNam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNam.setText("Năm sinh:");
        pnlThongTin.add(lblNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        lblDienThoai.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblDienThoai.setForeground(new java.awt.Color(0, 52, 102));
        lblDienThoai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDienThoai.setText("Lương:");
        pnlThongTin.add(lblDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, 50, -1));

        lblDienThoai1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblDienThoai1.setForeground(new java.awt.Color(0, 52, 102));
        lblDienThoai1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDienThoai1.setText("Địa chỉ:");
        pnlThongTin.add(lblDienThoai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(53, 55, 70));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDiaChi.setBackground(new java.awt.Color(202, 229, 246));
        txtDiaChi.setColumns(20);
        txtDiaChi.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtDiaChi.setLineWrap(true);
        txtDiaChi.setRows(5);
        txtDiaChi.setText("120 Công Chúa Ngọc Hân,\nPhường 12,Quận 11, TP Hồ Chí Minh\n");
        txtDiaChi.setBorder(null);
        txtDiaChi.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtDiaChi);

        pnlThongTin.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, -1, 60));

        lblResetMatkhau.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblResetMatkhau.setForeground(new java.awt.Color(255, 51, 51));
        lblResetMatkhau.setText("Reset mật khẩu");
        lblResetMatkhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 51, 51)));
        lblResetMatkhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblResetMatkhauMouseClicked(evt);
            }
        });
        pnlThongTin.add(lblResetMatkhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, -1, 30));

        lblDonHang1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDonHang1.setForeground(new java.awt.Color(0, 52, 102));
        lblDonHang1.setText("Ca làm:");
        pnlThongTin.add(lblDonHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, -1, 30));

        pnlTimKiemHoTenSDT.setBackground(new java.awt.Color(202, 229, 246));
        pnlTimKiemHoTenSDT.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemHoTenSDT.setFocusable(false);

        txtLuong.setBackground(new java.awt.Color(202, 229, 246));
        txtLuong.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtLuong.setBorder(null);
        txtLuong.setSelectedTextColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTimKiemHoTenSDTLayout = new javax.swing.GroupLayout(pnlTimKiemHoTenSDT);
        pnlTimKiemHoTenSDT.setLayout(pnlTimKiemHoTenSDTLayout);
        pnlTimKiemHoTenSDTLayout.setHorizontalGroup(
            pnlTimKiemHoTenSDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemHoTenSDTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        pnlTimKiemHoTenSDTLayout.setVerticalGroup(
            pnlTimKiemHoTenSDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemHoTenSDTLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlThongTin.add(pnlTimKiemHoTenSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 170, -1));

        lblMaNhanVien.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblMaNhanVien.setForeground(new java.awt.Color(24, 24, 24));
        lblMaNhanVien.setText("NV1");
        pnlThongTin.add(lblMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, 30));

        lblDienThoai2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblDienThoai2.setForeground(new java.awt.Color(0, 52, 102));
        lblDienThoai2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDienThoai2.setText("Số điện thoại:");
        pnlThongTin.add(lblDienThoai2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 100, -1));

        lblAddImage.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblAddImage.setForeground(new java.awt.Color(0, 52, 102));
        lblAddImage.setText("Thêm ảnh mới");
        lblAddImage.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 52, 102)));
        lblAddImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblAddImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddImageMouseClicked(evt);
            }
        });
        pnlThongTin.add(lblAddImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 90, -1));

        pnlTimKiemHoTenSDT1.setBackground(new java.awt.Color(202, 229, 246));
        pnlTimKiemHoTenSDT1.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemHoTenSDT1.setFocusable(false);

        txtHoTen.setBackground(new java.awt.Color(202, 229, 246));
        txtHoTen.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtHoTen.setBorder(null);
        txtHoTen.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txtHoTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHoTenKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemHoTenSDT1Layout = new javax.swing.GroupLayout(pnlTimKiemHoTenSDT1);
        pnlTimKiemHoTenSDT1.setLayout(pnlTimKiemHoTenSDT1Layout);
        pnlTimKiemHoTenSDT1Layout.setHorizontalGroup(
            pnlTimKiemHoTenSDT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemHoTenSDT1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlTimKiemHoTenSDT1Layout.setVerticalGroup(
            pnlTimKiemHoTenSDT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemHoTenSDT1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlThongTin.add(pnlTimKiemHoTenSDT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 260, -1));

        pnlTimKiemHoTenSDT2.setBackground(new java.awt.Color(202, 229, 246));
        pnlTimKiemHoTenSDT2.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemHoTenSDT2.setFocusable(false);

        txtNamSinh.setBackground(new java.awt.Color(202, 229, 246));
        txtNamSinh.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNamSinh.setBorder(null);
        txtNamSinh.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txtNamSinh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamSinhKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemHoTenSDT2Layout = new javax.swing.GroupLayout(pnlTimKiemHoTenSDT2);
        pnlTimKiemHoTenSDT2.setLayout(pnlTimKiemHoTenSDT2Layout);
        pnlTimKiemHoTenSDT2Layout.setHorizontalGroup(
            pnlTimKiemHoTenSDT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemHoTenSDT2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNamSinh, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTimKiemHoTenSDT2Layout.setVerticalGroup(
            pnlTimKiemHoTenSDT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemHoTenSDT2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pnlThongTin.add(pnlTimKiemHoTenSDT2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 260, 30));

        pnlTimKiemHoTenSDT3.setBackground(new java.awt.Color(202, 229, 246));
        pnlTimKiemHoTenSDT3.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemHoTenSDT3.setFocusable(false);

        txtSoDienThoai.setBackground(new java.awt.Color(202, 229, 246));
        txtSoDienThoai.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtSoDienThoai.setBorder(null);
        txtSoDienThoai.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txtSoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemHoTenSDT3Layout = new javax.swing.GroupLayout(pnlTimKiemHoTenSDT3);
        pnlTimKiemHoTenSDT3.setLayout(pnlTimKiemHoTenSDT3Layout);
        pnlTimKiemHoTenSDT3Layout.setHorizontalGroup(
            pnlTimKiemHoTenSDT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemHoTenSDT3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemHoTenSDT3Layout.setVerticalGroup(
            pnlTimKiemHoTenSDT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemHoTenSDT3Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlThongTin.add(pnlTimKiemHoTenSDT3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 260, -1));

        lblDienThoai3.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblDienThoai3.setForeground(new java.awt.Color(255, 0, 0));
        lblDienThoai3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDienThoai3.setText("đ");
        pnlThongTin.add(lblDienThoai3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 170, 10, -1));

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh.setPreferredSize(new java.awt.Dimension(168, 238));
        pnlThongTin.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 168, 238));

        lblDonHang4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDonHang4.setForeground(new java.awt.Color(0, 52, 102));
        lblDonHang4.setText("Ngày vào làm:");
        pnlThongTin.add(lblDonHang4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, -1, 30));

        lblNgayVaoLam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblNgayVaoLam.setText("Ngày vào làm");
        pnlThongTin.add(lblNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 220, -1, 30));

        add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 973, 370));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 52, 102));
        jLabel4.setText("Chỉnh sửa thông tin cá nhân:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 30));

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

        pnlXacNhan2.setBackground(new java.awt.Color(238, 243, 247));
        pnlXacNhan2.setForeground(new java.awt.Color(37, 213, 54));
        pnlXacNhan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXacNhan2MouseClicked(evt);
            }
        });

        lblThem2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblThem2.setForeground(new java.awt.Color(255, 255, 255));
        lblThem2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThem2.setText("Cập nhật");

        javax.swing.GroupLayout pnlXacNhan2Layout = new javax.swing.GroupLayout(pnlXacNhan2);
        pnlXacNhan2.setLayout(pnlXacNhan2Layout);
        pnlXacNhan2Layout.setHorizontalGroup(
            pnlXacNhan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXacNhan2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThem2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlXacNhan2Layout.setVerticalGroup(
            pnlXacNhan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThem2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        add(pnlXacNhan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 630, 90, 40));

        pnlTacVu.setBackground(new java.awt.Color(238, 243, 247));
        pnlTacVu.setForeground(new java.awt.Color(238, 243, 247));
        pnlTacVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxTongQuan.setBackground(new java.awt.Color(255, 255, 255));
        cbxTongQuan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxTongQuan.setForeground(new java.awt.Color(0, 52, 102));
        cbxTongQuan.setText("Tổng quan");
        cbxTongQuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTacVu.add(cbxTongQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 134, 20));

        cbxDonHang.setBackground(new java.awt.Color(255, 255, 255));
        cbxDonHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxDonHang.setForeground(new java.awt.Color(0, 52, 102));
        cbxDonHang.setText("Đơn hàng");
        cbxDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTacVu.add(cbxDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 100, 20));

        cbxSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cbxSanPham.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxSanPham.setForeground(new java.awt.Color(0, 52, 102));
        cbxSanPham.setText("Sản phẩm");
        cbxSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTacVu.add(cbxSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 132, 20));

        cbxKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        cbxKhachHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxKhachHang.setForeground(new java.awt.Color(0, 52, 102));
        cbxKhachHang.setText("Khách hàng");
        cbxKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        cbxKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKhachHangActionPerformed(evt);
            }
        });
        pnlTacVu.add(cbxKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, -1, 20));

        cbxDoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        cbxDoanhThu.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxDoanhThu.setForeground(new java.awt.Color(0, 52, 102));
        cbxDoanhThu.setText("Doanh thu");
        cbxDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTacVu.add(cbxDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 134, 20));

        cbxThongKe.setBackground(new java.awt.Color(255, 255, 255));
        cbxThongKe.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxThongKe.setForeground(new java.awt.Color(0, 52, 102));
        cbxThongKe.setText("Thống kê");
        cbxThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTacVu.add(cbxThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 100, 20));

        cbxKhuyenMai.setBackground(new java.awt.Color(255, 255, 255));
        cbxKhuyenMai.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxKhuyenMai.setForeground(new java.awt.Color(0, 52, 102));
        cbxKhuyenMai.setText("Khuyến mãi");
        cbxKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTacVu.add(cbxKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 132, 20));

        cbxNhapHang.setBackground(new java.awt.Color(255, 255, 255));
        cbxNhapHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxNhapHang.setForeground(new java.awt.Color(0, 52, 102));
        cbxNhapHang.setText("Nhập hàng");
        cbxNhapHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTacVu.add(cbxNhapHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 132, 20));

        cbxQuanLyNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        cbxQuanLyNhanVien.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxQuanLyNhanVien.setForeground(new java.awt.Color(0, 52, 102));
        cbxQuanLyNhanVien.setText("Quản lí nhân viên");
        cbxQuanLyNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTacVu.add(cbxQuanLyNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, -1, 20));

        cbxQuanLyDanhMuc.setBackground(new java.awt.Color(255, 255, 255));
        cbxQuanLyDanhMuc.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxQuanLyDanhMuc.setForeground(new java.awt.Color(0, 52, 102));
        cbxQuanLyDanhMuc.setText("Quản lí danh mục");
        cbxQuanLyDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        cbxQuanLyDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxQuanLyDanhMucActionPerformed(evt);
            }
        });
        pnlTacVu.add(cbxQuanLyDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, -1, 20));

        add(pnlTacVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 970, 100));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 52, 102));
        jLabel5.setText("Quyền:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlQuayVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVeMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)) {
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(new ChiTietNhanVienJPanel(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien, GUI.NhanVien.ChiTietNhanVienJPanel.Account));
            this.validate();
            this.repaint();
        }      // TODO add your handling code here:
    }//GEN-LAST:event_pnlQuayVeMouseClicked

    private void pnlXacNhan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhan2MouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)) {
            if (GUI.SweetAlert.SweetAlert.showSweetOption(new javax.swing.JFrame(), "Thông báo", "Xác nhận chỉnh sửa?", 0) == 1) {
                if (isNotFill()) {
                    GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Không được bỏ trống", 0);
                } else {
                    setNhanVien();
                    if (quanlycuahangsach.quanlycuahangsach.NhanVienBUS.edit(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien)) {
                        quanlycuahangsach.quanlycuahangsach.AccountBUS.edit(GUI.NhanVien.ChiTietNhanVienJPanel.Account);
                        paint();
                    }
                }
            }
        }

    }//GEN-LAST:event_pnlXacNhan2MouseClicked
    private boolean isNotFill() {
        if (txtHoTen.getText().equals("") || txtNamSinh.getText().equals("") || txtSoDienThoai.getText().equals("") || txtDiaChi.getText().equals("") || txtLuong.getText().equals("")) {
            return true;
        }
        return false;
    }

    private void paint() {
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new ChiTietNhanVienJPanel(GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien, GUI.NhanVien.ChiTietNhanVienJPanel.Account));
        this.validate();
        this.repaint();
    }

    ;    
    private void lblAddImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddImageMouseClicked
if (SwingUtilities.isLeftMouseButton(evt)) {
            try {
                File file =  GUI.Sweet.SweetFileChooser.ImageFileChooser();
                if(file!=null)
                lblAnh.setIcon(resizeImage(ImageIO.read(file),168,238));
                GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.setImgSource(saveImage(lblAnh.getIcon(),lblMaNhanVien.getText()));
                Image imageScale = readImageFromFilePath(PATH_NHANVIEN+GUI.NhanVien.ChiTietNhanVienJPanel.NhanVien.getImgSource(),168,238);
                imgAnhDaiDien = new ImageIcon(imageScale);
                lblAnh.setIcon(imgAnhDaiDien);
                
                
            } catch (IOException ex) {
                Logger.getLogger(SuaNhanVienJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lblAddImageMouseClicked

    private void txtHoTenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoTenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNamSinh.requestFocus();
        }
    }//GEN-LAST:event_txtHoTenKeyPressed

    private void txtNamSinhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamSinhKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSoDienThoai.requestFocus();
        }
    }//GEN-LAST:event_txtNamSinhKeyPressed

    private void txtSoDienThoaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDiaChi.requestFocus();
        }
    }//GEN-LAST:event_txtSoDienThoaiKeyPressed

    private void cbxKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxKhachHangActionPerformed

    private void cbxQuanLyDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxQuanLyDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxQuanLyDanhMucActionPerformed

    private void lblResetMatkhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResetMatkhauMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)) {
            if (GUI.SweetAlert.SweetAlert.showSweetOption(new javax.swing.JFrame(), "Thông báo", "Reset password cho nhân viên này?", 0) == 1) {
                quanlycuahangsach.quanlycuahangsach.AccountBUS.resetPassword(lblMaNhanVien.getText());
                GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Thành công", "Reset thành công", 1);
            }
        }
    }//GEN-LAST:event_lblResetMatkhauMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbxDoanhThu;
    private javax.swing.JCheckBox cbxDonHang;
    private javax.swing.JCheckBox cbxKhachHang;
    private javax.swing.JCheckBox cbxKhuyenMai;
    private javax.swing.JCheckBox cbxNhapHang;
    private javax.swing.JCheckBox cbxQuanLyDanhMuc;
    private javax.swing.JCheckBox cbxQuanLyNhanVien;
    private javax.swing.JCheckBox cbxSanPham;
    private javax.swing.JCheckBox cbxThongKe;
    private javax.swing.JCheckBox cbxTongQuan;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddImage;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblDienThoai;
    private javax.swing.JLabel lblDienThoai1;
    private javax.swing.JLabel lblDienThoai2;
    private javax.swing.JLabel lblDienThoai3;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblDonHang1;
    private javax.swing.JLabel lblDonHang4;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblNgayVaoLam;
    private javax.swing.JLabel lblQuayVe2;
    private javax.swing.JLabel lblResetMatkhau;
    private javax.swing.JLabel lblThem2;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblTrangThai1;
    private javax.swing.JLabel lblTrangThai2;
    private javax.swing.JPanel pnlCaLam;
    private GUI.Rounded pnlQuayVe;
    private GUI.Rounded pnlTacVu;
    private GUI.Rounded pnlThongTin;
    private GUI.Rounded pnlTimKiemHoTenSDT;
    private GUI.Rounded pnlTimKiemHoTenSDT1;
    private GUI.Rounded pnlTimKiemHoTenSDT2;
    private GUI.Rounded pnlTimKiemHoTenSDT3;
    private javax.swing.JPanel pnlTrangThai;
    private GUI.Rounded pnlXacNhan2;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JLabel txtGioiTinh;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables
}
