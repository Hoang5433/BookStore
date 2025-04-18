/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.AccountDTO;
import DTO.NhanVienDTO;
import GUI.NhanVien.ChiTietNhanVienJPanel;
import GUI.Sweet.SweetComboBox;
import GUI.Table.RenderTable;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class QLNVJPanel extends javax.swing.JPanel {

    /**
     * Creates new form QLNVJPanel
     */
    static DefaultTableModel listTableModel;
    public NhanVienDTO NhanVien;
    public AccountDTO Account;
    public ArrayList<NhanVienDTO> ArraySearch;
    SweetComboBox cbxTrangThai;
    SweetComboBox cbxGioiTinh;
    
    public ArrayList<javax.swing.AbstractButton> arrButton =new ArrayList<>();
    Object[] columnNames ={"Mã nhân viên","Họ tên","Năm sinh","Số điện thoại","Ca làm","Trạng thái"};    
    public QLNVJPanel() {
        initComponents();
        setShadow();
        customTable(tblQLNV,scpQLNV);
        ShowTable(tblQLNV,columnNames);
        setComboBox();
        initEvent();
        setJTableColumnsWidth(tblQLNV,890,15,25,15,15,10,10,10);        
    }
    public void setShadow(){
        pnlTblNhanVien.setShadow(1);
        pnlTimKiem.setShadow(1);
    }
    public void fetchAll(){
        listTableModel.setRowCount(0);
        quanlycuahangsach.quanlycuahangsach.NhanVienBUS.NhanVienList.forEach(NhanVien -> {
            Vector row = new Vector();
            row.add(NhanVien.getMaNhanVien());
            row.add(NhanVien.getHoTen());
            row.add(NhanVien.getNamSinh());
            row.add(NhanVien.getSoDienThoai());
            row.add(NhanVien.getCaLam());
            row.add(NhanVien.getTrangThai());
            listTableModel.addRow(row);
        });
        tblQLNV.setModel(listTableModel);
    }
    public void initEvent(){
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
        arrButton.add(cbxCaSang);
        arrButton.add(cbxCaChieu);
        arrButton.add(cbxCaToi);
        
         cbxChonTatCa.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                 javax.swing.AbstractButton currentItem = (javax.swing.AbstractButton) ie.getItem();
                   if(ie.getStateChange()==1)
                       currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
                   else 
                       currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png")));
            }
        });
        for(javax.swing.AbstractButton checkbox:arrButton){
            checkbox.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent ie) {
                   
                    javax.swing.AbstractButton currentItem = (javax.swing.AbstractButton) ie.getItem();
                   if(ie.getStateChange()==1)
                       currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
                   else {
                       currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png")));
                       if(cbxChonTatCa.isSelected())
                           cbxChonTatCa.setSelected(false);
                   }
                   getAdvancedSearch();
                }
            });
    }
        cbxCaSang.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                getAdvancedSearch();
            }
        });
        cbxCaChieu.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                getAdvancedSearch();
            }
        });
        cbxCaToi.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                getAdvancedSearch();
            }
        });
        arrButton.remove(cbxCaSang);
        arrButton.remove(cbxCaChieu);
        arrButton.remove(cbxCaToi);
    }
    public void setComboBox(){
        String[] trangthai = {"Tất cả","Còn làm","Nghỉ làm"};
        cbxTrangThai = new SweetComboBox("#CAE5F6","#181818",0,0,170,30,trangthai);
        String[] gioitinh = {"Tất cả","Nam","Nữ"};
        cbxGioiTinh = new SweetComboBox("#CAE5F6","#181818",0,0,130,30,gioitinh);
         pnlTrangThai.add(cbxTrangThai);
         pnlGioiTinh.add(cbxGioiTinh);

        cbxTrangThai.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });
        cbxGioiTinh.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });
    }   
    
    public void ShowTable(JTable table,Object[] columnName){
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAll(); 
    }     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTimKiem = new GUI.Rounded();
        lblTrangThai = new javax.swing.JLabel();
        cbxCaToi = new javax.swing.JCheckBox();
        cbxKhachHang = new javax.swing.JCheckBox();
        pnlTimKiemMaDonHang = new GUI.Rounded();
        txtTimKiemMaNhanVien = new javax.swing.JTextField();
        cbxThongKe = new javax.swing.JCheckBox();
        lblQuyen = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        pnlTrangThai = new javax.swing.JPanel();
        cbxDonHang = new javax.swing.JCheckBox();
        lblDonHang = new javax.swing.JLabel();
        cbxChonTatCa = new javax.swing.JCheckBox();
        cbxTongQuan = new javax.swing.JCheckBox();
        lblGioiTinh = new javax.swing.JLabel();
        pnlTimKiemHoTenSDT = new GUI.Rounded();
        txtTimKiemHoTenSDT = new javax.swing.JTextField();
        cbxQuanLyNhanVien = new javax.swing.JCheckBox();
        cbxKhuyenMai = new javax.swing.JCheckBox();
        cbxNhapHang = new javax.swing.JCheckBox();
        cbxSanPham = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        pnlGioiTinh = new javax.swing.JPanel();
        cbxQuanLyDanhMuc = new javax.swing.JCheckBox();
        cbxDoanhThu = new javax.swing.JCheckBox();
        cbxCaSang = new javax.swing.JCheckBox();
        cbxCaChieu = new javax.swing.JCheckBox();
        pnlTblNhanVien = new GUI.Rounded();
        scpQLNV = new javax.swing.JScrollPane();
        tblQLNV = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }
        };

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTimKiem.setBackground(new java.awt.Color(238, 243, 247));
        pnlTimKiem.setForeground(new java.awt.Color(238, 243, 247));
        pnlTimKiem.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTrangThai.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(0, 52, 102));
        lblTrangThai.setText("Trạng thái:");
        pnlTimKiem.add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, -1, 30));

        cbxCaToi.setBackground(new java.awt.Color(255, 255, 255));
        cbxCaToi.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxCaToi.setForeground(new java.awt.Color(0, 52, 102));
        cbxCaToi.setText("Ca tối");
        cbxCaToi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxCaToi, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, 30));

        cbxKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        cbxKhachHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxKhachHang.setForeground(new java.awt.Color(0, 52, 102));
        cbxKhachHang.setText("Khách hàng");
        cbxKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, -1, 20));

        pnlTimKiemMaDonHang.setBackground(new java.awt.Color(202, 229, 246));
        pnlTimKiemMaDonHang.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemMaDonHang.setFocusable(false);

        txtTimKiemMaNhanVien.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemMaNhanVien.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemMaNhanVien.setBorder(null);
        txtTimKiemMaNhanVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemMaNhanVienKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemMaDonHangLayout = new javax.swing.GroupLayout(pnlTimKiemMaDonHang);
        pnlTimKiemMaDonHang.setLayout(pnlTimKiemMaDonHangLayout);
        pnlTimKiemMaDonHangLayout.setHorizontalGroup(
            pnlTimKiemMaDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemMaDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlTimKiemMaDonHangLayout.setVerticalGroup(
            pnlTimKiemMaDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaDonHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlTimKiem.add(pnlTimKiemMaDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 150, -1));

        cbxThongKe.setBackground(new java.awt.Color(255, 255, 255));
        cbxThongKe.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxThongKe.setForeground(new java.awt.Color(0, 52, 102));
        cbxThongKe.setText("Thống kê");
        cbxThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 100, 20));

        lblQuyen.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblQuyen.setForeground(new java.awt.Color(0, 52, 102));
        lblQuyen.setText("Quyền:");
        pnlTimKiem.add(lblQuyen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 30));

        lblHoTen.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblHoTen.setForeground(new java.awt.Color(0, 52, 102));
        lblHoTen.setText("Họ tên/Số điện thoại:");
        pnlTimKiem.add(lblHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 30));

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

        pnlTimKiem.add(pnlTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 170, 30));

        cbxDonHang.setBackground(new java.awt.Color(255, 255, 255));
        cbxDonHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxDonHang.setForeground(new java.awt.Color(0, 52, 102));
        cbxDonHang.setText("Đơn hàng");
        cbxDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 100, 20));

        lblDonHang.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDonHang.setForeground(new java.awt.Color(0, 52, 102));
        lblDonHang.setText("Ca làm:");
        pnlTimKiem.add(lblDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, -1, 30));

        cbxChonTatCa.setBackground(new java.awt.Color(255, 255, 255));
        cbxChonTatCa.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxChonTatCa.setForeground(new java.awt.Color(0, 52, 102));
        cbxChonTatCa.setText("Chọn tất cả");
        cbxChonTatCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        cbxChonTatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxChonTatCaMouseClicked(evt);
            }
        });
        pnlTimKiem.add(cbxChonTatCa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 100, 30));

        cbxTongQuan.setBackground(new java.awt.Color(255, 255, 255));
        cbxTongQuan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxTongQuan.setForeground(new java.awt.Color(0, 52, 102));
        cbxTongQuan.setText("Tổng quan");
        cbxTongQuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxTongQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 134, 20));

        lblGioiTinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(0, 52, 102));
        lblGioiTinh.setText("Giới tính:");
        pnlTimKiem.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, -1, 30));

        pnlTimKiemHoTenSDT.setBackground(new java.awt.Color(202, 229, 246));
        pnlTimKiemHoTenSDT.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemHoTenSDT.setFocusable(false);

        txtTimKiemHoTenSDT.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemHoTenSDT.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemHoTenSDT.setBorder(null);
        txtTimKiemHoTenSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemHoTenSDTKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemHoTenSDTLayout = new javax.swing.GroupLayout(pnlTimKiemHoTenSDT);
        pnlTimKiemHoTenSDT.setLayout(pnlTimKiemHoTenSDTLayout);
        pnlTimKiemHoTenSDTLayout.setHorizontalGroup(
            pnlTimKiemHoTenSDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemHoTenSDTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemHoTenSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlTimKiemHoTenSDTLayout.setVerticalGroup(
            pnlTimKiemHoTenSDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemHoTenSDTLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemHoTenSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlTimKiem.add(pnlTimKiemHoTenSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 20, 400, -1));

        cbxQuanLyNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        cbxQuanLyNhanVien.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxQuanLyNhanVien.setForeground(new java.awt.Color(0, 52, 102));
        cbxQuanLyNhanVien.setText("Quản lí nhân viên");
        cbxQuanLyNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxQuanLyNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, -1, 20));

        cbxKhuyenMai.setBackground(new java.awt.Color(255, 255, 255));
        cbxKhuyenMai.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxKhuyenMai.setForeground(new java.awt.Color(0, 52, 102));
        cbxKhuyenMai.setText("Khuyến mãi");
        cbxKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 132, 20));

        cbxNhapHang.setBackground(new java.awt.Color(255, 255, 255));
        cbxNhapHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxNhapHang.setForeground(new java.awt.Color(0, 52, 102));
        cbxNhapHang.setText("Nhập hàng");
        cbxNhapHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxNhapHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 132, 20));

        cbxSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cbxSanPham.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxSanPham.setForeground(new java.awt.Color(0, 52, 102));
        cbxSanPham.setText("Sản phẩm");
        cbxSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 132, 20));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 52, 102));
        jLabel4.setText("Mã nhân viên:");
        pnlTimKiem.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, 30));

        pnlGioiTinh.setBackground(new java.awt.Color(202, 229, 246));
        pnlGioiTinh.setForeground(new java.awt.Color(202, 229, 246));

        javax.swing.GroupLayout pnlGioiTinhLayout = new javax.swing.GroupLayout(pnlGioiTinh);
        pnlGioiTinh.setLayout(pnlGioiTinhLayout);
        pnlGioiTinhLayout.setHorizontalGroup(
            pnlGioiTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        pnlGioiTinhLayout.setVerticalGroup(
            pnlGioiTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlTimKiem.add(pnlGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 130, -1));

        cbxQuanLyDanhMuc.setBackground(new java.awt.Color(255, 255, 255));
        cbxQuanLyDanhMuc.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxQuanLyDanhMuc.setForeground(new java.awt.Color(0, 52, 102));
        cbxQuanLyDanhMuc.setText("Quản lí danh mục");
        cbxQuanLyDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxQuanLyDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, -1, 20));

        cbxDoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        cbxDoanhThu.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxDoanhThu.setForeground(new java.awt.Color(0, 52, 102));
        cbxDoanhThu.setText("Doanh thu");
        cbxDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 134, 20));

        cbxCaSang.setBackground(new java.awt.Color(255, 255, 255));
        cbxCaSang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxCaSang.setForeground(new java.awt.Color(0, 52, 102));
        cbxCaSang.setText("Ca sáng");
        cbxCaSang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxCaSang, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, 30));

        cbxCaChieu.setBackground(new java.awt.Color(255, 255, 255));
        cbxCaChieu.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxCaChieu.setForeground(new java.awt.Color(0, 52, 102));
        cbxCaChieu.setText("Ca chiều");
        cbxCaChieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        pnlTimKiem.add(cbxCaChieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, -1, 30));

        add(pnlTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 973, 290));

        pnlTblNhanVien.setBackground(new java.awt.Color(238, 243, 247));
        pnlTblNhanVien.setForeground(new java.awt.Color(238, 243, 247));

        tblQLNV.setAutoCreateRowSorter(true);
        tblQLNV.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblQLNV.setForeground(new java.awt.Color(24, 24, 24));
        tblQLNV.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblQLNV.setFocusable(false);
        tblQLNV.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblQLNV.setRowHeight(20);
        tblQLNV.setSelectionBackground(new java.awt.Color(152, 210, 248));
        tblQLNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLNVMouseClicked(evt);
            }
        });
        scpQLNV.setViewportView(tblQLNV);

        javax.swing.GroupLayout pnlTblNhanVienLayout = new javax.swing.GroupLayout(pnlTblNhanVien);
        pnlTblNhanVien.setLayout(pnlTblNhanVienLayout);
        pnlTblNhanVienLayout.setHorizontalGroup(
            pnlTblNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpQLNV, javax.swing.GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTblNhanVienLayout.setVerticalGroup(
            pnlTblNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTblNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpQLNV, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 973, 350));
    }// </editor-fold>//GEN-END:initComponents
    private void getAdvancedSearch(){
        HashMap<String,String> advancedSearch = new HashMap<>();
        advancedSearch.put("HoTenSDT","");
        advancedSearch.put("MaNhanVien","");
        advancedSearch.put("TrangThai","");
        advancedSearch.put("CaSang","");
        advancedSearch.put("CaChieu","");
        advancedSearch.put("CaToi","");
        advancedSearch.put("GioiTinh","");
        advancedSearch.put("Quyen","");
        
        if(!txtTimKiemHoTenSDT.getText().equals(""))
        advancedSearch.replace("HoTenSDT", txtTimKiemHoTenSDT.getText());
        if(!txtTimKiemMaNhanVien.getText().equals(""))
        advancedSearch.replace("MaNhanVien", txtTimKiemMaNhanVien.getText());
        if(cbxGioiTinh.getSelectedItem().equals("Nam"))
            advancedSearch.replace("GioiTinh", "Nam");
        if(cbxGioiTinh.getSelectedItem().equals("Nữ"))
            advancedSearch.replace("GioiTinh","Nữ");
        if(cbxTrangThai.getSelectedItem().equals("Còn làm"))
            advancedSearch.replace("TrangThai", "Còn làm");
        if(cbxCaSang.isSelected())
            advancedSearch.replace("CaSang","Ca sáng");
        if(cbxCaChieu.isSelected())
            advancedSearch.replace("CaChieu","Ca chiều");
        if(cbxCaToi.isSelected())
            advancedSearch.replace("CaToi","Ca tối");
        if(cbxTrangThai.getSelectedItem().equals("Nghỉ làm"))
            advancedSearch.replace("TrangThai", "Nghỉ làm");
            advancedSearch.replace("Quyen",getRole());
            
            
        ArraySearch = quanlycuahangsach.quanlycuahangsach.NhanVienBUS.advancedSearch(advancedSearch);
        search();
    }
    public String getRole() {
        String Role = "";
        for(int i=0;i<arrButton.size();i++){
            if(arrButton.get(i).isSelected()){
                Role +="1";
            }
            else Role+="0";
        }
        return Role += "0";
    }    
    public  void search(){
        listTableModel.setRowCount(0);
        ArraySearch.forEach( NhanVien ->{
            Vector row = new Vector();
            row.add((NhanVien).getMaNhanVien());
            row.add((NhanVien).getHoTen());
            row.add((NhanVien).getNamSinh());
            row.add((NhanVien).getSoDienThoai());
            row.add((NhanVien).getCaLam());
            row.add((NhanVien).getTrangThai());
            listTableModel.addRow(row);
        });
        tblQLNV.setModel(listTableModel);
    }    
    private void tblQLNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLNVMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
        int column = tblQLNV.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblQLNV.getRowHeight();

        if(row < tblQLNV.getRowCount() && row >= 0 && column < tblQLNV.getColumnCount() && column >= 0){

            for(int i=0;i<tblQLNV.getColumnCount();i++){
                
                    this.removeAll();
                    this.setLayout(new BorderLayout());
                    NhanVien = quanlycuahangsach.quanlycuahangsach.NhanVienBUS.getByMaNhanVien((String) tblQLNV.getModel().getValueAt(row,0));
                    Account = quanlycuahangsach.quanlycuahangsach.AccountBUS.getByMaNhanVien((String) tblQLNV.getModel().getValueAt(row,0));
                    this.add(new ChiTietNhanVienJPanel(NhanVien,Account));
                    this.validate();
                    this.repaint();               
            }

        }
        }
    }//GEN-LAST:event_tblQLNVMouseClicked

    private void txtTimKiemMaNhanVienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaNhanVienKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaNhanVienKeyReleased

    private void txtTimKiemHoTenSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHoTenSDTKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemHoTenSDTKeyReleased

    private void cbxChonTatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxChonTatCaMouseClicked
        arrButton.forEach(button ->{
            button.setSelected(false);
            if(cbxChonTatCa.isSelected())
            button.setSelected(true);
        });
        getAdvancedSearch();
    }//GEN-LAST:event_cbxChonTatCaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbxCaChieu;
    private javax.swing.JCheckBox cbxCaSang;
    private javax.swing.JCheckBox cbxCaToi;
    private javax.swing.JCheckBox cbxChonTatCa;
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
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblQuyen;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnlGioiTinh;
    private GUI.Rounded pnlTblNhanVien;
    private GUI.Rounded pnlTimKiem;
    private GUI.Rounded pnlTimKiemHoTenSDT;
    private GUI.Rounded pnlTimKiemMaDonHang;
    private javax.swing.JPanel pnlTrangThai;
    private javax.swing.JScrollPane scpQLNV;
    private static javax.swing.JTable tblQLNV;
    private javax.swing.JTextField txtTimKiemHoTenSDT;
    private javax.swing.JTextField txtTimKiemMaNhanVien;
    // End of variables declaration//GEN-END:variables
}
