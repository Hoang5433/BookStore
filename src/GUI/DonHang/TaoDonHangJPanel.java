    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.DonHang;

import DTO.ChiTietDonHangDTO;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.DonHangDTO;
import DTO.KhachHangDTO;
import DTO.SanPhamDTO;
import DTO.SanPhamKhuyenMaiDTO;
import static GUI.DonHang.ThemSanPhamJFrame.arraySearch;
import GUI.DonHangJPanel;
import GUI.Sweet.SweetComboBox;
import GUI.Table.RenderTable;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import GUI.Table.setIconForCell;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import static quanlycuahangsach.Currency.changeCurrency;
import quanlycuahangsach.DateTime;
import static quanlycuahangsach.DateTime.TimestampToDateString;
import static quanlycuahangsach.DateTime.getCurrentTimestamp;
import static quanlycuahangsach.Regexp.Replace;
import static quanlycuahangsach.Regexp.SoDienThoai;

/**
 *
 * @author admin
 */
public class TaoDonHangJPanel extends javax.swing.JPanel {
    public static ArrayList<SanPhamKhuyenMaiDTO> currentSanPhamDuocKhuyenMai;
    public static ChiTietKhuyenMaiDTO currentChiTietKhuyenMai;
    
    public static KhachHangDTO newKhachHang;
    public static DefaultTableModel listTableModel;
    public static DonHangDTO newDonHang;
    public static ArrayList<ChiTietDonHangDTO> newChiTietDonHangList;
    
    Object[] columnNames ={"Mã sản phẩm","Tên sản phẩm","Tác giả","Thể loại","Đơn giá","Số lượng","Thành tiền",""};
    public void setShadow(){
        pnlThongTin.setShadow(1);
        pnlTblDonHangChuaXacNhan1.setShadow(1);
        pnlTinhToan.setShadow(1);
    }
    
    public static void setIcon(){
        for(int j = 0 ; j<tblSanPham.getRowCount();j++){
                tblSanPham.getColumnModel().getColumn(7).setCellRenderer(new setIconForCell(tblSanPham,j,7));
        }
        
        
    }     
    
    public void ShowTable(JTable table,Object[] columnName){
        table.setDefaultRenderer(Object.class, new RenderTable());
        listTableModel = new DefaultTableModel(columnNames,0);
        fetchAllSanPham();
    }
   
    public void createNewKhachHang(boolean isNew){
        txtHoTen.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        if(isNew == false){
            txtHoTen.setText(newKhachHang.getHoTen());
            txtEmail.setText(newKhachHang.getEmail());
            txtDiaChi.setText(newKhachHang.getDiaChi());
        }
        txtEmail.setEnabled(isNew);
        txtDiaChi.setEnabled(isNew);
        txtHoTen.setEnabled(isNew);
    }
    
    public static void TinhToan(){
       Double PhanTramGiamGia = 0.0,
        GiaTienGiamGia = 0.0,
        GiaTriToiThieuSuDung = 0.0,
        GiaTriGiamToiDa = 0.0,
        ThanhTien = 0.0,
        GiamGia =0.0,
        TamTinh=0.0,
        PhiVanChuyen=30000.0;
        if(currentChiTietKhuyenMai !=null){
        PhanTramGiamGia = Double.parseDouble(currentChiTietKhuyenMai.getPhanTramKhuyenMai());
        GiaTienGiamGia = Double.parseDouble(currentChiTietKhuyenMai.getGiaTienKhuyenMai());
        GiaTriToiThieuSuDung = Double.parseDouble(currentChiTietKhuyenMai.getGiaTriToiThieuSuDung());
        GiaTriGiamToiDa = Double.parseDouble(currentChiTietKhuyenMai.getGiaTriGiamToiDa());
        }
        if(currentSanPhamDuocKhuyenMai == null){
            for(ChiTietDonHangDTO ChiTietDonHang:newChiTietDonHangList){
                GiamGia += Double.parseDouble(Replace(ChiTietDonHang.getThanhTien()))*PhanTramGiamGia/100 + GiaTienGiamGia;
                TamTinh +=Double.parseDouble(Replace(ChiTietDonHang.getThanhTien()));
            }  
        }
        else{
            for(ChiTietDonHangDTO CTDH: newChiTietDonHangList){
                for(SanPhamKhuyenMaiDTO SPKM:currentSanPhamDuocKhuyenMai){
                    if(CTDH.getMaSanPham().equals(SPKM.getMaSanPham()))
                        GiamGia +=Double.parseDouble(Replace(CTDH.getThanhTien()))*PhanTramGiamGia/100 + GiaTienGiamGia;
                }
                TamTinh +=Double.parseDouble(Replace(CTDH.getThanhTien()));
            }
        }
        if(GiamGia > GiaTriGiamToiDa) GiamGia=GiaTriGiamToiDa;
        if(TamTinh < GiaTriToiThieuSuDung) {
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Thông báo", "Mã code này chỉ dùng cho hóa đơn ít nhất\n"+changeCurrency(GiaTriToiThieuSuDung+"")+" đồng.", 1);
            GiamGia=0.0;
            txtMaCode.setText("");
            currentChiTietKhuyenMai=null;
            currentSanPhamDuocKhuyenMai=null;
        }
            lblGiamGia.setText(changeCurrency(GiamGia+"")+"đ");
            lblTamTinh.setText(changeCurrency(TamTinh+"")+"đ");
            lblPhiVanChuyen.setText(changeCurrency(PhiVanChuyen+"")+"đ");
            Double TongTien= PhiVanChuyen+TamTinh-GiamGia;
            lblTongTien.setText(changeCurrency(TongTien+"")+"đ");
            newDonHang = new DonHangDTO();
            newDonHang.setTamTinh(TamTinh+"");
            newDonHang.setGiamGia(GiamGia+"");
            newDonHang.setTongTien(TongTien+"");
            newDonHang.setPhiVanChuyen(PhiVanChuyen+"");
    }
    
    public static void fetchAllSanPham(){
    listTableModel.setRowCount(0);
        tblSanPham.setModel(listTableModel);
        setIcon();
    }
    public void setComboBox(){
        String[] trangthai = {"Thành công"};
        SweetComboBox cbxTrangThai = new SweetComboBox("#ffffff","#181818",0,0,130,30,trangthai);
        pnlTrangThai.add(cbxTrangThai);
        
    }   
    
    public TaoDonHangJPanel() {
        initComponents();
            customTable(tblSanPham,scpSanPham);
            ShowTable(tblSanPham,columnNames);
            setComboBox();  
            pnlHuyDon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setShadow();
            currentSanPhamDuocKhuyenMai=null;
            newChiTietDonHangList=null;
            newKhachHang=null;
            lblNhanVienTaoMa.setText(quanlycuahangsach.quanlycuahangsach.currentNhanVien.getMaNhanVien());
            lblNgayDat.setText(TimestampToDateString(getCurrentTimestamp(),1));
            lblMaDonHang.setText(quanlycuahangsach.quanlycuahangsach.DonHangBUS.getLatest());
            currentChiTietKhuyenMai = null;
            setIcon();
            pnlThemSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnlXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
    }
    
    private void getNewDonHang(){
            newDonHang=new DonHangDTO();
            newDonHang.setMaDonHang(quanlycuahangsach.quanlycuahangsach.DonHangBUS.getLatest());
            newDonHang.setMaNhanVien(quanlycuahangsach.quanlycuahangsach.currentNhanVien.getMaNhanVien());
            newDonHang.setNgayXuat(DateTime.getCurrentTimestamp());
            newDonHang.setTrangThai("1");
            newDonHang.setPhiVanChuyen(Replace(lblPhiVanChuyen.getText()));
            newDonHang.setTamTinh(Replace(lblTamTinh.getText()));
            newDonHang.setTongTien(Replace(lblTongTien.getText()));
            newDonHang.setGiamGia(Replace(lblGiamGia.getText()));
            newDonHang.setMaKhachHang(newKhachHang.getMaKhachHang());
            newDonHang.setMaCode(null);
            
            if(currentChiTietKhuyenMai != null)
                newDonHang.setMaCode(currentChiTietKhuyenMai.getMaCode());
            
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
        lblTitleMaDonHang = new javax.swing.JLabel();
        lblTitleHoTen = new javax.swing.JLabel();
        lblTitleEmail = new javax.swing.JLabel();
        lblTitleSoDienThoai = new javax.swing.JLabel();
        lblTitleDiaChi = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        lblMaDonHang = new javax.swing.JLabel();
        lblTitleNgayDat = new javax.swing.JLabel();
        lblTitleMaGiamGia1 = new javax.swing.JLabel();
        lblNgayDat = new javax.swing.JLabel();
        pnlTimKiem = new GUI.Rounded();
        lblTimKiem = new javax.swing.JLabel();
        pnlTrangThai = new javax.swing.JPanel();
        lblTitleTrangThai = new javax.swing.JLabel();
        pnlDungMa = new GUI.Rounded();
        lblDungMa = new javax.swing.JLabel();
        pnlHuyDon = new GUI.Rounded();
        lblHuyDon = new javax.swing.JLabel();
        lblTitleMaDonHang1 = new javax.swing.JLabel();
        lblNhanVienTaoMa = new javax.swing.JLabel();
        txtMaCode = new javax.swing.JTextField();
        pnlTblDonHangChuaXacNhan1 = new GUI.Rounded();
        scpSanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }
        };
        pnlTinhToan = new GUI.Rounded();
        lblTitleTamTinh = new javax.swing.JLabel();
        lblTitleMaGiamGia = new javax.swing.JLabel();
        lblTitlePhiVanChuyen = new javax.swing.JLabel();
        lblTitleTong = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblPhiVanChuyen = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        lblTamTinh = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlXacNhan = new GUI.Rounded();
        lblXacNhan = new javax.swing.JLabel();
        pnlThemSanPham = new GUI.Rounded();
        lblThemSanPham = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongTin.setBackground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setForeground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleMaDonHang.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleMaDonHang.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaDonHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaDonHang.setText("Mã đơn hàng:");
        pnlThongTin.add(lblTitleMaDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 90, -1));

        lblTitleHoTen.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleHoTen.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleHoTen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleHoTen.setText("Số điện thoại:");
        pnlThongTin.add(lblTitleHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        lblTitleEmail.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleEmail.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleEmail.setText("Email:");
        pnlThongTin.add(lblTitleEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        lblTitleSoDienThoai.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleSoDienThoai.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleSoDienThoai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleSoDienThoai.setText("Họ tên:");
        pnlThongTin.add(lblTitleSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 87, -1));

        lblTitleDiaChi.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleDiaChi.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleDiaChi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleDiaChi.setText("Địa chỉ:");
        pnlThongTin.add(lblTitleDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(53, 55, 70));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDiaChi.setBackground(new java.awt.Color(202, 229, 246));
        txtDiaChi.setColumns(20);
        txtDiaChi.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtDiaChi.setLineWrap(true);
        txtDiaChi.setRows(5);
        txtDiaChi.setText("\n");
        txtDiaChi.setBorder(null);
        txtDiaChi.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiaChi.setEnabled(false);
        jScrollPane1.setViewportView(txtDiaChi);

        pnlThongTin.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 290, 30));

        txtHoTen.setBackground(new java.awt.Color(202, 229, 246));
        txtHoTen.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(24, 24, 24));
        txtHoTen.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtHoTen.setBorder(null);
        txtHoTen.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtHoTen.setEnabled(false);
        pnlThongTin.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 290, 30));

        txtEmail.setBackground(new java.awt.Color(202, 229, 246));
        txtEmail.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(24, 24, 24));
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEmail.setBorder(null);
        txtEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtEmail.setEnabled(false);
        pnlThongTin.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 78, 290, 30));

        txtSoDienThoai.setBackground(new java.awt.Color(202, 229, 246));
        txtSoDienThoai.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtSoDienThoai.setForeground(new java.awt.Color(24, 24, 24));
        txtSoDienThoai.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtSoDienThoai.setBorder(null);
        txtSoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKeyPressed(evt);
            }
        });
        pnlThongTin.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 41, 290, 30));

        lblMaDonHang.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblMaDonHang.setForeground(new java.awt.Color(210, 48, 44));
        lblMaDonHang.setText("12345");
        pnlThongTin.add(lblMaDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, 20));

        lblTitleNgayDat.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleNgayDat.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleNgayDat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleNgayDat.setText("Ngày đặt:");
        pnlThongTin.add(lblTitleNgayDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 70, -1));

        lblTitleMaGiamGia1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleMaGiamGia1.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaGiamGia1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaGiamGia1.setText("Mã giảm giá:");
        pnlThongTin.add(lblTitleMaGiamGia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, -1, -1));

        lblNgayDat.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblNgayDat.setForeground(new java.awt.Color(24, 24, 24));
        lblNgayDat.setText("01/05/2000");
        pnlThongTin.add(lblNgayDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, -1, -1));

        pnlTimKiem.setBackground(new java.awt.Color(255, 255, 255));
        pnlTimKiem.setForeground(new java.awt.Color(238, 38, 63));
        pnlTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTimKiemMouseClicked(evt);
            }
        });

        lblTimKiem.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        lblTimKiem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimKiem.setText("Tìm kiếm");

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 41, -1, -1));

        pnlTrangThai.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTrangThaiLayout = new javax.swing.GroupLayout(pnlTrangThai);
        pnlTrangThai.setLayout(pnlTrangThaiLayout);
        pnlTrangThaiLayout.setHorizontalGroup(
            pnlTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        pnlTrangThaiLayout.setVerticalGroup(
            pnlTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, -1, -1));

        lblTitleTrangThai.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleTrangThai.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleTrangThai.setText("Trạng thái:");
        pnlThongTin.add(lblTitleTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 80, 30));

        pnlDungMa.setBackground(new java.awt.Color(255, 255, 255));
        pnlDungMa.setForeground(new java.awt.Color(238, 38, 63));
        pnlDungMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDungMaMouseClicked(evt);
            }
        });

        lblDungMa.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDungMa.setForeground(new java.awt.Color(255, 255, 255));
        lblDungMa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDungMa.setText("Kích hoạt");

        javax.swing.GroupLayout pnlDungMaLayout = new javax.swing.GroupLayout(pnlDungMa);
        pnlDungMa.setLayout(pnlDungMaLayout);
        pnlDungMaLayout.setHorizontalGroup(
            pnlDungMaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDungMaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblDungMa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        pnlDungMaLayout.setVerticalGroup(
            pnlDungMaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDungMa, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlDungMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, -1, -1));

        pnlHuyDon.setBackground(new java.awt.Color(255, 255, 255));
        pnlHuyDon.setForeground(new java.awt.Color(238, 38, 63));
        pnlHuyDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHuyDonMouseClicked(evt);
            }
        });

        lblHuyDon.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblHuyDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHuyDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHuyDon.setText("Hủy đơn");

        javax.swing.GroupLayout pnlHuyDonLayout = new javax.swing.GroupLayout(pnlHuyDon);
        pnlHuyDon.setLayout(pnlHuyDonLayout);
        pnlHuyDonLayout.setHorizontalGroup(
            pnlHuyDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHuyDonLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(lblHuyDon, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        pnlHuyDonLayout.setVerticalGroup(
            pnlHuyDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHuyDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlHuyDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 140, 120, -1));

        lblTitleMaDonHang1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTitleMaDonHang1.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaDonHang1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaDonHang1.setText("Nhân viên tạo mã:");
        pnlThongTin.add(lblTitleMaDonHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        lblNhanVienTaoMa.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblNhanVienTaoMa.setForeground(new java.awt.Color(24, 24, 24));
        lblNhanVienTaoMa.setText("NV1");
        pnlThongTin.add(lblNhanVienTaoMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, -1, -1));

        txtMaCode.setBackground(new java.awt.Color(202, 229, 246));
        txtMaCode.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtMaCode.setForeground(new java.awt.Color(24, 24, 24));
        txtMaCode.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMaCode.setBorder(null);
        txtMaCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaCodeKeyPressed(evt);
            }
        });
        pnlThongTin.add(txtMaCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 130, 30));

        add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 973, 200));

        pnlTblDonHangChuaXacNhan1.setBackground(new java.awt.Color(238, 243, 247));
        pnlTblDonHangChuaXacNhan1.setForeground(new java.awt.Color(238, 243, 247));

        tblSanPham.setAutoCreateRowSorter(true);
        tblSanPham.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblSanPham.setForeground(new java.awt.Color(24, 24, 24));
        tblSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblSanPham.setFocusable(false);
        tblSanPham.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblSanPham.setRowHeight(50);
        tblSanPham.setSelectionBackground(new java.awt.Color(152, 210, 248));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        scpSanPham.setViewportView(tblSanPham);

        javax.swing.GroupLayout pnlTblDonHangChuaXacNhan1Layout = new javax.swing.GroupLayout(pnlTblDonHangChuaXacNhan1);
        pnlTblDonHangChuaXacNhan1.setLayout(pnlTblDonHangChuaXacNhan1Layout);
        pnlTblDonHangChuaXacNhan1Layout.setHorizontalGroup(
            pnlTblDonHangChuaXacNhan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpSanPham)
                .addContainerGap())
        );
        pnlTblDonHangChuaXacNhan1Layout.setVerticalGroup(
            pnlTblDonHangChuaXacNhan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTblDonHangChuaXacNhan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTblDonHangChuaXacNhan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 973, 280));

        pnlTinhToan.setBackground(new java.awt.Color(238, 243, 247));
        pnlTinhToan.setForeground(new java.awt.Color(238, 243, 247));
        pnlTinhToan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleTamTinh.setBackground(new java.awt.Color(192, 192, 192));
        lblTitleTamTinh.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleTamTinh.setText("Tạm tính");
        pnlTinhToan.add(lblTitleTamTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        lblTitleMaGiamGia.setBackground(new java.awt.Color(192, 192, 192));
        lblTitleMaGiamGia.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaGiamGia.setText("Mã giảm giá");
        pnlTinhToan.add(lblTitleMaGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblTitlePhiVanChuyen.setBackground(new java.awt.Color(192, 192, 192));
        lblTitlePhiVanChuyen.setForeground(new java.awt.Color(0, 52, 102));
        lblTitlePhiVanChuyen.setText("Phí vận chuyển");
        pnlTinhToan.add(lblTitlePhiVanChuyen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        lblTitleTong.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblTitleTong.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleTong.setText("Tổng");
        pnlTinhToan.add(lblTitleTong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        lblTongTien.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(210, 48, 44));
        lblTongTien.setText("0đ");
        pnlTinhToan.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));

        lblPhiVanChuyen.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblPhiVanChuyen.setForeground(new java.awt.Color(24, 24, 24));
        lblPhiVanChuyen.setText("0đ");
        pnlTinhToan.add(lblPhiVanChuyen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        lblGiamGia.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblGiamGia.setForeground(new java.awt.Color(24, 24, 24));
        lblGiamGia.setText("0đ");
        pnlTinhToan.add(lblGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        lblTamTinh.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblTamTinh.setForeground(new java.awt.Color(24, 24, 24));
        lblTamTinh.setText("0đ");
        pnlTinhToan.add(lblTamTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        add(pnlTinhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 570, 280, 110));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 52, 102));
        jLabel3.setText("Sản phẩm");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, 39));

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
        lblXacNhan.setText("Xác nhận");

        javax.swing.GroupLayout pnlXacNhanLayout = new javax.swing.GroupLayout(pnlXacNhan);
        pnlXacNhan.setLayout(pnlXacNhanLayout);
        pnlXacNhanLayout.setHorizontalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlXacNhanLayout.setVerticalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, -1, -1));

        pnlThemSanPham.setBackground(new java.awt.Color(238, 243, 247));
        pnlThemSanPham.setForeground(new java.awt.Color(0, 146, 242));
        pnlThemSanPham.setPreferredSize(new java.awt.Dimension(160, 35));
        pnlThemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThemSanPhamMouseClicked(evt);
            }
        });
        pnlThemSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThemSanPham.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblThemSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThemSanPham.setText("Thêm sản phảm");
        pnlThemSanPham.add(lblThemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 2, 136, 30));

        add(pnlThemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 230, -1, 35));
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPham.getRowHeight();

        
        if(row < tblSanPham.getRowCount() && row >=0 && column<tblSanPham.getColumnCount() && column >=0){

            if(tblSanPham.getValueAt(row,column).toString().equalsIgnoreCase("Xóa sản phẩm đã thêm")){
                ChiTietDonHangDTO ChiTietDonHang = new ChiTietDonHangDTO();
                for(ChiTietDonHangDTO CTDH:TaoDonHangJPanel.newChiTietDonHangList){
                    if(CTDH.getMaSanPham().equals(tblSanPham.getValueAt(row,0).toString())){
                    ChiTietDonHang = CTDH;
                    break;
                    }
               
            }
                
                    TaoDonHangJPanel.listTableModel.removeRow(row);
                    TaoDonHangJPanel.newChiTietDonHangList.remove(ChiTietDonHang);
                    TinhToan();
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void pnlHuyDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHuyDonMouseClicked
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(new DonHangJPanel());
            this.validate();
            this.repaint();
    }//GEN-LAST:event_pnlHuyDonMouseClicked

    private void getNewKhachHang(){
        newKhachHang = new KhachHangDTO();
        newKhachHang.setMaKhachHang(quanlycuahangsach.quanlycuahangsach.KhachHangBUS.getLatest());
        newKhachHang.setHoTen(txtHoTen.getText());
        newKhachHang.setDiaChi(txtDiaChi.getText());
        newKhachHang.setEmail(txtEmail.getText().toLowerCase());
        newKhachHang.setSoDienThoai(txtSoDienThoai.getText());
        newChiTietDonHangList.forEach(ChiTietDonHang->{
                ChiTietDonHang.setMaDonHang(newDonHang.getMaDonHang());
            });
    }
    private void createNewDonHang(){
    quanlycuahangsach.quanlycuahangsach.DonHangBUS.add(newDonHang);
    if(newDonHang.getMaCode() !=null){
    currentChiTietKhuyenMai = quanlycuahangsach.quanlycuahangsach.ChiTietKhuyenMaiBUS.getByMaCode(newDonHang.getMaCode());
    currentChiTietKhuyenMai.setSoLuongDaDung(Integer.parseInt(currentChiTietKhuyenMai.getSoLuongDaDung())+1+"");
    quanlycuahangsach.quanlycuahangsach.ChiTietKhuyenMaiBUS.edit(currentChiTietKhuyenMai);
    }
                 newChiTietDonHangList.forEach(ChiTietDonHang->{
                     ChiTietDonHang.setMaDonHang(newDonHang.getMaDonHang());
                     ChiTietDonHang.setThanhTien(Replace(ChiTietDonHang.getThanhTien()));
                quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.add(ChiTietDonHang);
                SanPhamDTO SanPham = quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(ChiTietDonHang.getMaSanPham());
                SanPham.setSoLuong(-Integer.parseInt(ChiTietDonHang.getSoLuong())+Integer.parseInt(SanPham.getSoLuong())+"");
                quanlycuahangsach.quanlycuahangsach.SachBUS.edit(SanPham);
                });
        arraySearch = new ArrayList<>(quanlycuahangsach.quanlycuahangsach.SachBUS.SachList);
                 
          //edit lai so luong       
    }
    private void add(){
        if(txtSoDienThoai.getText().equals("")){
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Không được bỏ trống số điện thoại!", 1);
           
            return;}
        if(newChiTietDonHangList==null || newChiTietDonHangList.size()==0){
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Chưa thêm sản phẩm!", 1);
           return;
        }
        if(newKhachHang==null){
            getNewKhachHang();
            getNewDonHang();
            
            if(quanlycuahangsach.quanlycuahangsach.KhachHangBUS.add(newKhachHang))
                createNewDonHang();
            else{
                newKhachHang=null;
            return;
            }
        }else{
            getNewDonHang();
            createNewDonHang();
            
        }
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(new DonHangJPanel());
            this.validate();
            this.repaint();
 
    }
    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
    if (SwingUtilities.isLeftMouseButton(evt)){  
        if(GUI.SweetAlert.SweetAlert.showSweetOption(new javax.swing.JFrame(), "Thêm mới đơn hàng?","Xác nhận thêm mới?", 1)==1){
                add();
        }
        }
    }//GEN-LAST:event_pnlXacNhanMouseClicked

    private void pnlThemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThemSanPhamMouseClicked
       if (SwingUtilities.isLeftMouseButton(evt)){
           
        new ThemSanPhamJFrame().setVisible(true);   
       }
    }//GEN-LAST:event_pnlThemSanPhamMouseClicked

    private void pnlDungMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDungMaMouseClicked
        if(newChiTietDonHangList == null || newChiTietDonHangList.size()==0){
           GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Chưa thêm sản phẩm!", 1);
            
        return;
        }
        timCode();
    }//GEN-LAST:event_pnlDungMaMouseClicked

    public boolean timKiem(){
    String SoDienThoai = txtSoDienThoai.getText();
        if(!SoDienThoai(SoDienThoai)){
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Số điện thoại không hợp lệ", 1);
            return false;
        }
        else{
            newKhachHang = quanlycuahangsach.quanlycuahangsach.KhachHangBUS.findBySoDienThoai(SoDienThoai);
            if(newKhachHang==null){
                if(GUI.SweetAlert.SweetAlert.showSweetOption(new javax.swing.JFrame(), "Thông báo","Số điện thoại chưa tồn tại.\nBạn có muốn thêm mới khách hàng này?", 1)==1){
                    txtSoDienThoai.setEnabled(false);
                    createNewKhachHang(true);
                }else{
                    GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Thông báo", "Đây sẽ là đơn cho khách vãng lai!", 3);
                        newKhachHang = quanlycuahangsach.quanlycuahangsach.KhachHangBUS.KhachHangList.get(0);
                        createNewKhachHang(false);
                        return true;
                }
            
            }
            else 
                createNewKhachHang(false);
            return true;
        }
    }
    private void pnlTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTimKiemMouseClicked
        timKiem();
    }//GEN-LAST:event_pnlTimKiemMouseClicked

    private static String isValidated(){
        Long BatDau = Long.parseLong(currentChiTietKhuyenMai.getThoiGianBatDauSuDung());
        Long KetThuc = Long.parseLong(currentChiTietKhuyenMai.getThoiGianKetThucSuDung());
        Long HienTai = Long.parseLong(DateTime.getCurrentTimestamp());
        if(HienTai < BatDau) return "Chưa tới thời gian sử dụng mã";
        if(HienTai>KetThuc) return "Mã đã hết hiệu lực";
        if(currentChiTietKhuyenMai.getSoLuong().equals(currentChiTietKhuyenMai.getSoLuongDaDung())) return "Số lượng mã đã hết";
        
        return null;
    }

    private static boolean timCode(){
        currentChiTietKhuyenMai = quanlycuahangsach.quanlycuahangsach.ChiTietKhuyenMaiBUS.getByMaCode(txtMaCode.getText());
        if(currentChiTietKhuyenMai == null){
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Mã giảm giá không hợp lệ", 1);
            return false;
        }
        else{
            String Validation = isValidated();
            if(Validation != null) {
                GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Validation, 1);
                currentChiTietKhuyenMai=null;
                TinhToan();
                return false;
            }
        }
        currentSanPhamDuocKhuyenMai =    quanlycuahangsach.quanlycuahangsach.SanPhamKhuyenMaiBUS.getSanPhamKhuyenMai(currentChiTietKhuyenMai.getMaChiTietKhuyenMai());
        TinhToan();
        return true;
    }
    private void txtSoDienThoaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
            timKiem();
    }//GEN-LAST:event_txtSoDienThoaiKeyPressed

    private void txtMaCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaCodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if(newChiTietDonHangList == null || newChiTietDonHangList.size()==0){
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Chưa thêm sản phẩm!", 1);
            
            return;
        }
        timCode();
        }
    }//GEN-LAST:event_txtMaCodeKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDungMa;
    public static javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblHuyDon;
    private javax.swing.JLabel lblMaDonHang;
    private javax.swing.JLabel lblNgayDat;
    private javax.swing.JLabel lblNhanVienTaoMa;
    public static javax.swing.JLabel lblPhiVanChuyen;
    public static javax.swing.JLabel lblTamTinh;
    private javax.swing.JLabel lblThemSanPham;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTitleDiaChi;
    private javax.swing.JLabel lblTitleEmail;
    private javax.swing.JLabel lblTitleHoTen;
    private javax.swing.JLabel lblTitleMaDonHang;
    private javax.swing.JLabel lblTitleMaDonHang1;
    private javax.swing.JLabel lblTitleMaGiamGia;
    private javax.swing.JLabel lblTitleMaGiamGia1;
    private javax.swing.JLabel lblTitleNgayDat;
    private javax.swing.JLabel lblTitlePhiVanChuyen;
    private javax.swing.JLabel lblTitleSoDienThoai;
    private javax.swing.JLabel lblTitleTamTinh;
    private javax.swing.JLabel lblTitleTong;
    private javax.swing.JLabel lblTitleTrangThai;
    public static javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblXacNhan;
    private GUI.Rounded pnlDungMa;
    private GUI.Rounded pnlHuyDon;
    private GUI.Rounded pnlTblDonHangChuaXacNhan1;
    private GUI.Rounded pnlThemSanPham;
    private GUI.Rounded pnlThongTin;
    private GUI.Rounded pnlTimKiem;
    private GUI.Rounded pnlTinhToan;
    private javax.swing.JPanel pnlTrangThai;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JScrollPane scpSanPham;
    private static javax.swing.JTable tblSanPham;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    public static javax.swing.JTextField txtMaCode;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables
}
