/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahangsach;

import BUS.AccountBUS;
import BUS.ChiTietDonHangBUS;
import BUS.ChiTietKhuyenMaiBUS;
import BUS.ChiTietPhieuNhapBUS;
import BUS.ChuongTrinhKhuyenMaiBUS;
import BUS.DonHangBUS;
import BUS.KhachHangBUS;
import BUS.NhaCungCapBUS;
import BUS.NhaXuatBanBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import BUS.SanPhamKhuyenMaiBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
import DTO.AccountDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import GUI.RoundedJPanel;
import GUI.CaiDatJPanel;
import bean.DanhMucBean;
import controller.changeScreenController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.font.TextAttribute.FONT;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;




/**
 *
 * @author admin
 */
public class quanlycuahangsach extends javax.swing.JFrame {
    public static AccountDTO currentAccount = null;
    public static AccountBUS AccountBUS;
    public static NhanVienDTO currentNhanVien = null;
    public static NhanVienBUS NhanVienBUS;    
    public static SanPhamDTO currentBook;
    public static SanPhamBUS SachBUS;
    public static TacGiaBUS TacGiaBUS;
    public static TheLoaiBUS TheLoaiBUS;
    public static NhaCungCapBUS NhaCungCapBUS;
    public static NhaXuatBanBUS NhaXuatBanBUS;
    public static KhachHangBUS KhachHangBUS;
    public static ChuongTrinhKhuyenMaiBUS ChuongTrinhKhuyenMaiBUS;
    public static ChiTietKhuyenMaiBUS ChiTietKhuyenMaiBUS;
    public static DonHangBUS DonHangBUS;
    public static ChiTietDonHangBUS ChiTietDonHangBUS;
    public static SanPhamKhuyenMaiBUS SanPhamKhuyenMaiBUS;
    public static PhieuNhapBUS PhieuNhapBUS;
    public static ChiTietPhieuNhapBUS ChiTietPhieuNhapBUS;
    int xMouse,yMouse;
    
    /**
     * Creates new form 
     */
    //<editor-fold defaultstate="collapsed" desc="comment">
    
//</editor-fold>
    public quanlycuahangsach() {
        initComponents();
        initData();
        setScreenBaseOnRole(currentAccount.getRole());
        lblName1.setText(currentAccount.getPosition());
        if(!currentAccount.getRole().equals("admin")){
            BUS.NhanVienBUS.getAuth(currentAccount.getUsername());
            lblName1.setText(currentNhanVien.getHoTen());
        }
        lblRole1.setText(currentAccount.getPosition());       
        this.setLocationRelativeTo(null); 
    }
    public void setScreenBaseOnRole(String Role){
        changeScreenController  controller = new changeScreenController(pnlView); 
        controller.setScreen("TongQuan",pnlTongQuan,lblTongQuan);
        List<DanhMucBean> listItem = new ArrayList<>();
        listItem.add(new DanhMucBean("TongQuan",pnlTongQuan,lblTongQuan));
        listItem.add(new DanhMucBean("DonHang",pnlDonHang,lblDonHang));
        listItem.add(new DanhMucBean("SanPham",pnlSanPham,lblSanPham));
        listItem.add(new DanhMucBean("KhachHang",pnlKhachHang,lblKhachHang)); 
        listItem.add(new DanhMucBean("DoanhThu",pnlDoanhThu,lblDoanhThu));
        listItem.add(new DanhMucBean("ThongKe",pnlThongKe,lblThongKe));
        listItem.add(new DanhMucBean("KhuyenMai",pnlKhuyenMai,lblKhuyenMai));
        listItem.add(new DanhMucBean("PhieuNhapHang",pnlPhieuNhapHang,lblPhieuNhapHang));
        listItem.add(new DanhMucBean("QLNV",pnlQLNV,lblQLNV));
        listItem.add(new DanhMucBean("QLDM",pnlQLDM,lblQLDM));
        listItem.add(new DanhMucBean("ThemTaiKhoan",pnlThemTaiKhoan,lblThemTaiKhoan));
        listItem.add(new DanhMucBean("CaiDat",pnlCaiDat,lblCaiDat));
        if(Role.equals("admin")){
            for(int i=0;i<listItem.size();i++){
            listItem.get(i).getPnlName().setVisible(false);  
            if(i==listItem.size()-2||i==listItem.size()-1)
                listItem.get(i).getPnlName().setVisible(true);  
            
            }
        controller.setScreen(listItem.get(listItem.size()-2).getKindOfScreen(),listItem.get(listItem.size()-2).getPnlName(), listItem.get(listItem.size()-2).getLblName());
        
        lblCaiDat.setText("Đăng xuất");
        pnlCaiDat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(quanlycuahangsach.bg)).dispose();
                    new GUI.LoginJFrame().setVisible(true);
            }
        });        
          
        }
        if(!Role.equals("admin")){
            
        for(int i=0;i<listItem.size();i++){
            if(49 == Role.codePointAt(i))
                listItem.get(i).getPnlName().setVisible(true);
                
            else 
                listItem.get(i).getPnlName().setVisible(false);
        }
        controller.setScreen(listItem.get(Role.indexOf("1")).getKindOfScreen(),listItem.get(Role.indexOf("1")).getPnlName(), listItem.get(Role.indexOf("1")).getLblName());
        }
        controller.setEvent(listItem); 
    }
    public void initData(){
        NhanVienBUS = new NhanVienBUS(); 
        NhaCungCapBUS = new NhaCungCapBUS();
        NhaXuatBanBUS = new NhaXuatBanBUS();
        TheLoaiBUS = new TheLoaiBUS();
        TacGiaBUS = new TacGiaBUS();
        AccountBUS = new AccountBUS();
        KhachHangBUS = new KhachHangBUS();
        SachBUS = new SanPhamBUS();
        ChuongTrinhKhuyenMaiBUS = new ChuongTrinhKhuyenMaiBUS();
        ChiTietKhuyenMaiBUS = new ChiTietKhuyenMaiBUS();
        DonHangBUS = new DonHangBUS();
        ChiTietDonHangBUS = new ChiTietDonHangBUS();
        SanPhamKhuyenMaiBUS = new SanPhamKhuyenMaiBUS();
        PhieuNhapBUS = new PhieuNhapBUS();
        ChiTietPhieuNhapBUS = new ChiTietPhieuNhapBUS();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        pnlSidebar = new javax.swing.JPanel();
        pnlLogo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        pnlTongQuan = new GUI.RoundedJPanel();
        lblTongQuan = new javax.swing.JLabel();
        pnlDonHang = new GUI.RoundedJPanel();
        lblDonHang = new javax.swing.JLabel();
        pnlSanPham = new GUI.RoundedJPanel();
        lblSanPham = new javax.swing.JLabel();
        pnlKhachHang = new GUI.RoundedJPanel();
        lblKhachHang = new javax.swing.JLabel();
        pnlDoanhThu = new GUI.RoundedJPanel();
        lblDoanhThu = new javax.swing.JLabel();
        pnlThongKe = new GUI.RoundedJPanel();
        lblThongKe = new javax.swing.JLabel();
        pnlKhuyenMai = new GUI.RoundedJPanel();
        lblKhuyenMai = new javax.swing.JLabel();
        pnlPhieuNhapHang = new GUI.RoundedJPanel();
        lblPhieuNhapHang = new javax.swing.JLabel();
        pnlQLNV = new GUI.RoundedJPanel();
        lblQLNV = new javax.swing.JLabel();
        pnlQLDM = new GUI.RoundedJPanel();
        lblQLDM = new javax.swing.JLabel();
        pnlThemTaiKhoan = new GUI.RoundedJPanel();
        lblThemTaiKhoan = new javax.swing.JLabel();
        pnlCaiDat = new GUI.RoundedJPanel();
        lblCaiDat = new javax.swing.JLabel();
        pnlHeader = new javax.swing.JPanel();
        lblExit1 = new javax.swing.JLabel();
        pnlNameUser1 = new javax.swing.JPanel();
        lblName1 = new javax.swing.JLabel();
        lblRole1 = new javax.swing.JLabel();
        lblAvatar = new javax.swing.JLabel();
        pnlView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(217, 223, 227));
        setUndecorated(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(221, 223, 226));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlSidebar.setBackground(new java.awt.Color(255, 255, 255));
        pnlSidebar.setFont(new java.awt.Font("Wingdings", 0, 13)); // NOI18N

        pnlLogo.setBackground(new java.awt.Color(255, 255, 255));
        pnlLogo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(185, 194, 202)));
        pnlLogo.setPreferredSize(new java.awt.Dimension(240, 55));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        javax.swing.GroupLayout pnlLogoLayout = new javax.swing.GroupLayout(pnlLogo);
        pnlLogo.setLayout(pnlLogoLayout);
        pnlLogoLayout.setHorizontalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        pnlLogoLayout.setVerticalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlLogo);

        pnlTongQuan.setBackground(new java.awt.Color(255, 255, 255));
        pnlTongQuan.setForeground(new java.awt.Color(255, 255, 255));
        pnlTongQuan.setPreferredSize(new java.awt.Dimension(222, 40));

        lblTongQuan.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblTongQuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/TongQuanColor.png"))); // NOI18N
        lblTongQuan.setText("   Tổng quan");

        javax.swing.GroupLayout pnlTongQuanLayout = new javax.swing.GroupLayout(pnlTongQuan);
        pnlTongQuan.setLayout(pnlTongQuanLayout);
        pnlTongQuanLayout.setHorizontalGroup(
            pnlTongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongQuanLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTongQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        pnlTongQuanLayout.setVerticalGroup(
            pnlTongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongQuanLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTongQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlSidebar.add(pnlTongQuan);

        pnlDonHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlDonHang.setForeground(new java.awt.Color(255, 255, 255));
        pnlDonHang.setPreferredSize(new java.awt.Dimension(222, 40));

        lblDonHang.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/DonHangColor.png"))); // NOI18N
        lblDonHang.setText("    Đơn hàng");

        javax.swing.GroupLayout pnlDonHangLayout = new javax.swing.GroupLayout(pnlDonHang);
        pnlDonHang.setLayout(pnlDonHangLayout);
        pnlDonHangLayout.setHorizontalGroup(
            pnlDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDonHangLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
        pnlDonHangLayout.setVerticalGroup(
            pnlDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDonHangLayout.createSequentialGroup()
                .addComponent(lblDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlDonHang);

        pnlSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnlSanPham.setForeground(new java.awt.Color(255, 255, 255));
        pnlSanPham.setPreferredSize(new java.awt.Dimension(222, 40));

        lblSanPham.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/SanPhamColor.png"))); // NOI18N
        lblSanPham.setText("     Sản phẩm");

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addComponent(lblSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlSanPham);

        pnlKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        pnlKhachHang.setPreferredSize(new java.awt.Dimension(222, 40));

        lblKhachHang.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/KhachHangColor.png"))); // NOI18N
        lblKhachHang.setText("     Khách hàng");

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addComponent(lblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlKhachHang);

        pnlDoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        pnlDoanhThu.setForeground(new java.awt.Color(255, 255, 255));

        lblDoanhThu.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/DoanhThuColor.png"))); // NOI18N
        lblDoanhThu.setText("   Doanh thu");

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addComponent(lblDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlDoanhThu);

        pnlThongKe.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongKe.setForeground(new java.awt.Color(255, 255, 255));

        lblThongKe.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/ThongKeColor.png"))); // NOI18N
        lblThongKe.setText("    Số liệu thống kê");

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKeLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKeLayout.createSequentialGroup()
                .addComponent(lblThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlThongKe);

        pnlKhuyenMai.setBackground(new java.awt.Color(255, 255, 255));
        pnlKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        pnlKhuyenMai.setPreferredSize(new java.awt.Dimension(222, 40));

        lblKhuyenMai.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/KhuyenMaiColor.png"))); // NOI18N
        lblKhuyenMai.setText("     Khuyến mãi");

        javax.swing.GroupLayout pnlKhuyenMaiLayout = new javax.swing.GroupLayout(pnlKhuyenMai);
        pnlKhuyenMai.setLayout(pnlKhuyenMaiLayout);
        pnlKhuyenMaiLayout.setHorizontalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhuyenMaiLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        pnlKhuyenMaiLayout.setVerticalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhuyenMaiLayout.createSequentialGroup()
                .addComponent(lblKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlKhuyenMai);

        pnlPhieuNhapHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlPhieuNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        pnlPhieuNhapHang.setPreferredSize(new java.awt.Dimension(222, 40));

        lblPhieuNhapHang.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblPhieuNhapHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/PhieuNhapHangColor.png"))); // NOI18N
        lblPhieuNhapHang.setText("      Phiếu nhập hàng");

        javax.swing.GroupLayout pnlPhieuNhapHangLayout = new javax.swing.GroupLayout(pnlPhieuNhapHang);
        pnlPhieuNhapHang.setLayout(pnlPhieuNhapHangLayout);
        pnlPhieuNhapHangLayout.setHorizontalGroup(
            pnlPhieuNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhieuNhapHangLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblPhieuNhapHang)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        pnlPhieuNhapHangLayout.setVerticalGroup(
            pnlPhieuNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhieuNhapHangLayout.createSequentialGroup()
                .addComponent(lblPhieuNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlPhieuNhapHang);

        pnlQLNV.setBackground(new java.awt.Color(255, 255, 255));
        pnlQLNV.setForeground(new java.awt.Color(255, 255, 255));

        lblQLNV.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblQLNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/QLNVColor.png"))); // NOI18N
        lblQLNV.setText("   Nhân viên");

        javax.swing.GroupLayout pnlQLNVLayout = new javax.swing.GroupLayout(pnlQLNV);
        pnlQLNV.setLayout(pnlQLNVLayout);
        pnlQLNVLayout.setHorizontalGroup(
            pnlQLNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLNVLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        pnlQLNVLayout.setVerticalGroup(
            pnlQLNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLNVLayout.createSequentialGroup()
                .addComponent(lblQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlQLNV);

        pnlQLDM.setBackground(new java.awt.Color(255, 255, 255));
        pnlQLDM.setForeground(new java.awt.Color(255, 255, 255));

        lblQLDM.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblQLDM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/QLDMColor.png"))); // NOI18N
        lblQLDM.setText("  Quản lý danh mục");

        javax.swing.GroupLayout pnlQLDMLayout = new javax.swing.GroupLayout(pnlQLDM);
        pnlQLDM.setLayout(pnlQLDMLayout);
        pnlQLDMLayout.setHorizontalGroup(
            pnlQLDMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLDMLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblQLDM, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pnlQLDMLayout.setVerticalGroup(
            pnlQLDMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLDMLayout.createSequentialGroup()
                .addComponent(lblQLDM, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlQLDM);

        pnlThemTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        pnlThemTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));

        lblThemTaiKhoan.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblThemTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/ThemTaiKhoanColor.png"))); // NOI18N
        lblThemTaiKhoan.setText("   Thêm tài khoản");

        javax.swing.GroupLayout pnlThemTaiKhoanLayout = new javax.swing.GroupLayout(pnlThemTaiKhoan);
        pnlThemTaiKhoan.setLayout(pnlThemTaiKhoanLayout);
        pnlThemTaiKhoanLayout.setHorizontalGroup(
            pnlThemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemTaiKhoanLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblThemTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        pnlThemTaiKhoanLayout.setVerticalGroup(
            pnlThemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemTaiKhoanLayout.createSequentialGroup()
                .addComponent(lblThemTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlThemTaiKhoan);

        pnlCaiDat.setBackground(new java.awt.Color(255, 255, 255));
        pnlCaiDat.setForeground(new java.awt.Color(255, 255, 255));

        lblCaiDat.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 15)); // NOI18N
        lblCaiDat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/CaiDatColor.png"))); // NOI18N
        lblCaiDat.setText("  Cài đặt");

        javax.swing.GroupLayout pnlCaiDatLayout = new javax.swing.GroupLayout(pnlCaiDat);
        pnlCaiDat.setLayout(pnlCaiDatLayout);
        pnlCaiDatLayout.setHorizontalGroup(
            pnlCaiDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCaiDatLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblCaiDat, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pnlCaiDatLayout.setVerticalGroup(
            pnlCaiDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCaiDatLayout.createSequentialGroup()
                .addComponent(lblCaiDat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlSidebar.add(pnlCaiDat);

        bg.add(pnlSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 239, 750));

        pnlHeader.setBackground(new java.awt.Color(239, 255, 242));
        pnlHeader.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(185, 194, 202)));
        pnlHeader.setForeground(new java.awt.Color(0, 52, 102));
        pnlHeader.setPreferredSize(new java.awt.Dimension(1200, 71));
        pnlHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlHeaderMouseDragged(evt);
            }
        });
        pnlHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlHeaderMousePressed(evt);
            }
        });

        lblExit1.setBackground(new java.awt.Color(255, 0, 0));
        lblExit1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        lblExit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExit1MouseClicked(evt);
            }
        });

        pnlNameUser1.setBackground(new java.awt.Color(239, 255, 242));

        lblName1.setBackground(new java.awt.Color(135, 135, 135));
        lblName1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblName1.setForeground(new java.awt.Color(135, 135, 135));
        lblName1.setText("Phan Công Sơn");
        lblName1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblName1MouseClicked(evt);
            }
        });

        lblRole1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblRole1.setForeground(new java.awt.Color(24, 24, 24));
        lblRole1.setText("Admin");

        lblAvatar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user-icon.png"))); // NOI18N

        javax.swing.GroupLayout pnlNameUser1Layout = new javax.swing.GroupLayout(pnlNameUser1);
        pnlNameUser1.setLayout(pnlNameUser1Layout);
        pnlNameUser1Layout.setHorizontalGroup(
            pnlNameUser1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNameUser1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlNameUser1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRole1)
                    .addComponent(lblName1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNameUser1Layout.setVerticalGroup(
            pnlNameUser1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNameUser1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNameUser1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNameUser1Layout.createSequentialGroup()
                        .addComponent(lblName1)
                        .addGap(10, 10, 10)
                        .addComponent(lblRole1))
                    .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlNameUser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblExit1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblExit1)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addComponent(pnlNameUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        bg.add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 1040, 60));

        pnlView.setBackground(new java.awt.Color(239, 255, 242));

        javax.swing.GroupLayout pnlViewLayout = new javax.swing.GroupLayout(pnlView);
        pnlView.setLayout(pnlViewLayout);
        pnlViewLayout.setHorizontalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        pnlViewLayout.setVerticalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );

        bg.add(pnlView, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 1040, 690));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblExit1MouseClicked

    private void pnlHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y-yMouse);
    }//GEN-LAST:event_pnlHeaderMouseDragged

    private void pnlHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_pnlHeaderMousePressed

    private void lblName1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblName1MouseClicked

    }//GEN-LAST:event_lblName1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(quanlycuahangsach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(quanlycuahangsach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(quanlycuahangsach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(quanlycuahangsach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new quanlycuahangsach(
                ).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel bg;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblCaiDat;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblKhuyenMai;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblPhieuNhapHang;
    private javax.swing.JLabel lblQLDM;
    private javax.swing.JLabel lblQLNV;
    private javax.swing.JLabel lblRole1;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThemTaiKhoan;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblTongQuan;
    private GUI.RoundedJPanel pnlCaiDat;
    private GUI.RoundedJPanel pnlDoanhThu;
    private GUI.RoundedJPanel pnlDonHang;
    private javax.swing.JPanel pnlHeader;
    private GUI.RoundedJPanel pnlKhachHang;
    private GUI.RoundedJPanel pnlKhuyenMai;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlNameUser1;
    private GUI.RoundedJPanel pnlPhieuNhapHang;
    private GUI.RoundedJPanel pnlQLDM;
    private GUI.RoundedJPanel pnlQLNV;
    private GUI.RoundedJPanel pnlSanPham;
    private javax.swing.JPanel pnlSidebar;
    private GUI.RoundedJPanel pnlThemTaiKhoan;
    private GUI.RoundedJPanel pnlThongKe;
    private GUI.RoundedJPanel pnlTongQuan;
    private javax.swing.JPanel pnlView;
    // End of variables declaration//GEN-END:variables
}
