/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.PhieuNhapHang;

import DTO.ChiTietPhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.DonHang.*;
import GUI.Sweet.SweetComboBox;
import GUI.Table.RenderTable;
import GUI.Table.setIconForCell;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import static quanlycuahangsach.Currency.changeCurrency;

/**
 *
 * @author admin
 */
public class ThemSanPhamJFrame extends javax.swing.JFrame {
    int xMouse,yMouse;
    public static ChiTietPhieuNhapDTO currentChiTietPhieuNhap;
    public static SanPhamDTO currentSanPham;
    private static DefaultTableModel listTableModel;
    private static DefaultTableModel listTableModelBot;
    public static ArrayList<SanPhamDTO> DanhSachSanPham;
    public static ArrayList<ChiTietPhieuNhapDTO> GioHang;
    public static ArrayList<SanPhamDTO> MangTiemKiemSanPham;
    SweetComboBox cbxTheLoai;
    SweetComboBox cbxTacGia;
    SweetComboBox cbxNhaXuatBan;
    private static Object[] columnNames ={"Mã sản phẩm","Tên sản phẩm","Tác giả","Thể loại",""};
    private static Object[] columnNamesBot = {"Mã sản phẩm","Tên sản phẩm","Tác giả","Thể loại","Đơn giá","Số lượng","Tạm tính",""};
    //Function init
    private static void setIcon()
    {
        for(int i = 0 ; i<tblSanPham.getRowCount();i++){
                tblSanPham.getColumnModel().getColumn(4).setCellRenderer(new setIconForCell(tblSanPham,i,4));
        }
    }  
    public static void setIconBottom()
    {
        for(int i = 0 ;i <tblSanPhamDaThem.getRowCount();i++){
            tblSanPhamDaThem.getColumnModel().getColumn(7).setCellRenderer(new setIconForCell(tblSanPhamDaThem,i,7));
        }
    } 
    public ThemSanPhamJFrame() 
    {
        initComponents();
        customTable(tblSanPham,scpSanPham);
        ShowTable(tblSanPham,columnNames);
        customTable(tblSanPhamDaThem,scpSanPhamDaThem);
        ShowTableBottom(tblSanPhamDaThem,columnNamesBot);
        setIcon();
        pnlXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setComboBox();
        setLocationRelativeTo(null);   
        setJTableColumnsWidth(tblSanPham,884,15,45,20,15,5);
        setJTableColumnsWidth(tblSanPhamDaThem,884,15,25,20,10,10,10,10,5);
        GioHang = new ArrayList();
        ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(quanlycuahangsach.quanlycuahangsach.bg.getRootPane())).setEnabled(false);
    }
    public void ShowTableBottom(JTable table,Object[] columnName)
    {
        listTableModelBot = new DefaultTableModel(columnName,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        table.setModel(listTableModelBot);
    }       
    public void ShowTable(JTable table,Object[] columnName)
    {
        String themsanpham = "Thêm sản phẩm";
        if(DanhSachSanPham==null)
        {
        DanhSachSanPham = new ArrayList();
        table.setDefaultRenderer(Object.class, new RenderTable());
        Object columnNames[] = columnName;
        listTableModel = new DefaultTableModel(columnNames,0);    
        quanlycuahangsach.quanlycuahangsach.SachBUS.SachList.forEach(sp->{
        DanhSachSanPham.add(sp);
        });
        DanhSachSanPham.forEach(SanPham ->{
            Vector row = new Vector();
            row.add(SanPham.getMaSanPham());
            row.add(SanPham.getTenSanPham());
            row.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(SanPham.getMaTacGia()));
            row.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(SanPham.getMaTheLoai()));
            row.add(themsanpham);
            listTableModel.addRow(row);            
        });
        table.setModel(listTableModel); 
        setIcon();
        setJTableColumnsWidth(tblSanPham,884,15,45,20,15,5);}
        else
        {
        fetchData();
        }
    }      
    public void setComboBox()
    {
        String[] theloai = quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenTheLoai();
        cbxTheLoai = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,theloai);
        pnlTheLoai.add(cbxTheLoai);
        String[] tacgia = quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenTacGia();
        cbxTacGia = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,tacgia);
        pnlTacGia.add(cbxTacGia);
        String[] nhaxuatban = quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getTenNhaXuatBan();
        cbxNhaXuatBan = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,nhaxuatban);
        pnlNhaXuatBan.add(cbxNhaXuatBan);    
        cbxTheLoai.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });
        cbxTacGia.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });
        cbxNhaXuatBan.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                if(ie.getStateChange() == ItemEvent.SELECTED)
                getAdvancedSearch();
            }
        });
    } 
   //AdvancedSearch
    private void search()
    {
        String themsanpham = "Thêm sản phẩm";
        listTableModel.setRowCount(0);
        MangTiemKiemSanPham.forEach(SanPham ->{
            Vector row = new Vector();
            row.add(SanPham.getMaSanPham());
            row.add(SanPham.getTenSanPham());
            row.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(SanPham.getMaTacGia()));
            row.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(SanPham.getMaTheLoai()));
            row.add(themsanpham);
        listTableModel.addRow(row);
        });
        tblSanPham.setModel(listTableModel);
        setJTableColumnsWidth(tblSanPham,884,15,45,20,15,5);
        setIcon();
    }
    
    private void getAdvancedSearch()
    {
        HashMap<String, String> advancedSearch = new HashMap<>();
        advancedSearch.put("MaSanPham",txtTimKiemMaSanPham.getText());
        advancedSearch.put("TenSanPham",txtTimKiemTenSanPham.getText());
        advancedSearch.put("TheLoai","");
        advancedSearch.put("TacGia","");
        advancedSearch.put("NhaXuatBan","");
        if(!cbxTheLoai.getSelectedItem().toString().equals("Thể loại"))
            advancedSearch.replace("TheLoai", quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getMaByName(cbxTheLoai.getSelectedItem().toString()));
        if(!cbxTacGia.getSelectedItem().toString().equals("Tác giả"))
            advancedSearch.replace("TacGia", quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getMaByName(cbxTacGia.getSelectedItem().toString()));
        if(!cbxNhaXuatBan.getSelectedItem().toString().equals("Nhà xuất bản"))
            advancedSearch.replace("NhaXuatBan", quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getMaByName(cbxNhaXuatBan.getSelectedItem().toString()));
            System.out.println(advancedSearch.get("TheLoai"));
        MangTiemKiemSanPham = quanlycuahangsach.quanlycuahangsach.ChiTietPhieuNhapBUS.advancedSearch(advancedSearch);
        search();
    }
    //ADD TO CART
    public static void createCTPN( String masanpham )
    {
        currentChiTietPhieuNhap = new ChiTietPhieuNhapDTO();
        currentChiTietPhieuNhap.setMaSanPham(masanpham);
    }    
    public static void setSoLuongDonGiaCTPN( String soluong, String dongia) 
    {
        currentChiTietPhieuNhap.setMaPhieuNhap(TaoPhieuNhapJPanel.PhieuNhap.getMaPhieuNhap());
        currentChiTietPhieuNhap.setSoLuong(soluong);
        currentChiTietPhieuNhap.setDonGia(dongia);             
    }
    public static void setThanhTienCTPN(String thanhtien)
    {
        currentChiTietPhieuNhap.setThanhTien(thanhtien);
    }
    public static void addChiTietPhieuNhapList( ChiTietPhieuNhapDTO ctpn )
    {
        GioHang.add(ctpn);
        TaoPhieuNhapJPanel.ChiTietPhieuNhapList.add(ctpn);
        fetchDataBottom();
        fetchData();
    }
    public static void fetchDSSPWhenAddingCTPN(String masanpham)
    {
        for(int i=0;i<DanhSachSanPham.size();i++)
                {
                    if(DanhSachSanPham.get(i).getMaSanPham().equals(masanpham))
                    {
                        DanhSachSanPham.remove(i);
                    }
                } 
    }

    
    //REMOVE FORM CART
    public static void fetchDSSPWhenRemovingCTPN(String masanpham)
    {
        DanhSachSanPham.add(quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(masanpham));
    }
    public static void removeChiTietPhieuNhap ( String masanpham )
    {
                for(int i=0;i<TaoPhieuNhapJPanel.ChiTietPhieuNhapList.size();i++)
                {
                    if(TaoPhieuNhapJPanel.ChiTietPhieuNhapList.get(i).getMaSanPham().equals(masanpham)){
                    TaoPhieuNhapJPanel.ChiTietPhieuNhapList.remove(i);
                    for  (int j=0;j<GioHang.size();j++){
                        if(GioHang.get(i).getMaSanPham().equals(masanpham)){
                            GioHang.remove(i);
                        }
                    }
                    fetchDSSPWhenRemovingCTPN(masanpham);
                    }
                }  
                
    }
    
    //REFESH TABLE
    public static void fetchData()
    {
        tblSanPham.setDefaultRenderer(Object.class, new RenderTable());
        listTableModel = new DefaultTableModel(columnNames,0);    
        String themsanpham = "Thêm sản phẩm";
        DanhSachSanPham.forEach(SanPham ->{
            Vector row = new Vector();
            row.add(SanPham.getMaSanPham());
            row.add(SanPham.getTenSanPham());
            row.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(SanPham.getMaTacGia()));
            row.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(SanPham.getMaTheLoai()));
            row.add(themsanpham);
            listTableModel.addRow(row);            
        });
        tblSanPham.setModel(listTableModel);
        setIcon();
        setJTableColumnsWidth(tblSanPham,884,15,45,20,15,5);
    }
    public static void fetchDataBottom()
    {
        tblSanPhamDaThem.setDefaultRenderer(Object.class, new RenderTable());
        listTableModelBot = new DefaultTableModel(columnNamesBot,0);    
        String xoasanpham = "Xóa sản phẩm đã thêm";
        GioHang.forEach(ctpn ->
        {
            SanPhamDTO sanpham = quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(ctpn.getMaSanPham());
            Vector row = new Vector();
            row.add(ctpn.getMaSanPham());
            row.add(sanpham.getTenSanPham());
            row.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(sanpham.getMaTacGia()));
            row.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(sanpham.getMaTheLoai()));
            row.add(changeCurrency(ctpn.getDonGia()));
            row.add(ctpn.getSoLuong());
            row.add(changeCurrency(ctpn.getThanhTien()));
            row.add(xoasanpham);
            listTableModelBot.addRow(row);            
        });
        tblSanPhamDaThem.setModel(listTableModelBot); 
        setIconBottom();
        setJTableColumnsWidth(tblSanPhamDaThem,884,15,25,20,10,10,10,10,5);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlThongTin = new GUI.Rounded();
        scpSanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        lblDonHang = new javax.swing.JLabel();
        lblDonHang1 = new javax.swing.JLabel();
        scpSanPhamDaThem = new javax.swing.JScrollPane();
        tblSanPhamDaThem = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        pnlTimKiemTenSanPham = new GUI.Rounded();
        txtTimKiemTenSanPham = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTimKiemMaSanPham = new GUI.Rounded();
        txtTimKiemMaSanPham = new javax.swing.JTextField();
        pnlTheLoai = new javax.swing.JPanel();
        pnlTacGia = new javax.swing.JPanel();
        pnlNhaXuatBan = new javax.swing.JPanel();
        pnlXacNhan = new GUI.Rounded();
        lblXacNhan = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongTin.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongTin.setForeground(new java.awt.Color(255, 255, 255));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(920, 750));

        scpSanPham.setForeground(new java.awt.Color(255, 255, 255));

        tblSanPham.setAutoCreateRowSorter(true);
        tblSanPham.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblSanPham.setForeground(new java.awt.Color(18, 18, 18));
        tblSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblSanPham.setFocusable(false);
        tblSanPham.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblSanPham.setRowHeight(25);
        tblSanPham.setSelectionBackground(new java.awt.Color(152, 210, 248));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        scpSanPham.setViewportView(tblSanPham);

        lblDonHang.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblDonHang.setForeground(new java.awt.Color(0, 52, 102));
        lblDonHang.setText("Tất cả sản phẩm");

        lblDonHang1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblDonHang1.setForeground(new java.awt.Color(0, 52, 102));
        lblDonHang1.setText("Sản phẩm đã thêm");

        scpSanPhamDaThem.setForeground(new java.awt.Color(255, 255, 255));

        tblSanPhamDaThem.setAutoCreateRowSorter(true);
        tblSanPhamDaThem.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblSanPhamDaThem.setForeground(new java.awt.Color(18, 18, 18));
        tblSanPhamDaThem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblSanPhamDaThem.setFocusable(false);
        tblSanPhamDaThem.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblSanPhamDaThem.setRowHeight(25);
        tblSanPhamDaThem.setSelectionBackground(new java.awt.Color(152, 210, 248));
        tblSanPhamDaThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamDaThemMouseClicked(evt);
            }
        });
        scpSanPhamDaThem.setViewportView(tblSanPhamDaThem);

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpSanPham)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDonHang)
                            .addComponent(lblDonHang1))
                        .addContainerGap(711, Short.MAX_VALUE))
                    .addComponent(scpSanPhamDaThem)))
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDonHang)
                .addGap(18, 18, 18)
                .addComponent(scpSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDonHang1)
                .addGap(18, 18, 18)
                .addComponent(scpSanPhamDaThem, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 158, 890, 578));

        pnlTimKiemTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnlTimKiemTenSanPham.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemTenSanPham.setFocusable(false);

        txtTimKiemTenSanPham.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemTenSanPham.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemTenSanPham.setForeground(new java.awt.Color(24, 24, 24));
        txtTimKiemTenSanPham.setBorder(null);
        txtTimKiemTenSanPham.setPreferredSize(new java.awt.Dimension(0, 20));
        txtTimKiemTenSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemTenSanPhamKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemTenSanPhamKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemTenSanPhamLayout = new javax.swing.GroupLayout(pnlTimKiemTenSanPham);
        pnlTimKiemTenSanPham.setLayout(pnlTimKiemTenSanPhamLayout);
        pnlTimKiemTenSanPhamLayout.setHorizontalGroup(
            pnlTimKiemTenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemTenSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlTimKiemTenSanPhamLayout.setVerticalGroup(
            pnlTimKiemTenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemTenSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(pnlTimKiemTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(24, 24, 24));
        jLabel1.setText("Mã sản phẩm:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 30));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(24, 24, 24));
        jLabel3.setText("Tên sản phẩm:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 18, -1, 30));

        pnlTimKiemMaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnlTimKiemMaSanPham.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemMaSanPham.setFocusable(false);

        txtTimKiemMaSanPham.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemMaSanPham.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemMaSanPham.setForeground(new java.awt.Color(24, 24, 24));
        txtTimKiemMaSanPham.setBorder(null);
        txtTimKiemMaSanPham.setMinimumSize(new java.awt.Dimension(0, 20));
        txtTimKiemMaSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemMaSanPhamKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemMaSanPhamKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemMaSanPhamLayout = new javax.swing.GroupLayout(pnlTimKiemMaSanPham);
        pnlTimKiemMaSanPham.setLayout(pnlTimKiemMaSanPhamLayout);
        pnlTimKiemMaSanPhamLayout.setHorizontalGroup(
            pnlTimKiemMaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemMaSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlTimKiemMaSanPhamLayout.setVerticalGroup(
            pnlTimKiemMaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(pnlTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        pnlTheLoai.setBackground(new java.awt.Color(202, 229, 246));

        javax.swing.GroupLayout pnlTheLoaiLayout = new javax.swing.GroupLayout(pnlTheLoai);
        pnlTheLoai.setLayout(pnlTheLoaiLayout);
        pnlTheLoaiLayout.setHorizontalGroup(
            pnlTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlTheLoaiLayout.setVerticalGroup(
            pnlTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(pnlTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 150, 30));

        pnlTacGia.setBackground(new java.awt.Color(202, 229, 246));
        pnlTacGia.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlTacGiaLayout = new javax.swing.GroupLayout(pnlTacGia);
        pnlTacGia.setLayout(pnlTacGiaLayout);
        pnlTacGiaLayout.setHorizontalGroup(
            pnlTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlTacGiaLayout.setVerticalGroup(
            pnlTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(pnlTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, -1, -1));

        pnlNhaXuatBan.setBackground(new java.awt.Color(202, 229, 246));
        pnlNhaXuatBan.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlNhaXuatBanLayout = new javax.swing.GroupLayout(pnlNhaXuatBan);
        pnlNhaXuatBan.setLayout(pnlNhaXuatBanLayout);
        pnlNhaXuatBanLayout.setHorizontalGroup(
            pnlNhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlNhaXuatBanLayout.setVerticalGroup(
            pnlNhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(pnlNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, -1, -1));

        pnlXacNhan.setBackground(new java.awt.Color(255, 255, 255));
        pnlXacNhan.setForeground(new java.awt.Color(34, 212, 52));
        pnlXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXacNhanMouseClicked(evt);
            }
        });

        lblXacNhan.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        lblXacNhan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXacNhan.setText("Thêm hoàn tất");

        javax.swing.GroupLayout pnlXacNhanLayout = new javax.swing.GroupLayout(pnlXacNhan);
        pnlXacNhan.setLayout(pnlXacNhanLayout);
        pnlXacNhanLayout.setHorizontalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXacNhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlXacNhanLayout.setVerticalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(pnlXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 20, 170, -1));

        jLabel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel5MouseDragged(evt);
            }
        });
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 730));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 934, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 753, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPham.getRowHeight();
        if(tblSanPham.getValueAt(row, column).toString().equalsIgnoreCase("Thêm sản phẩm"))
        {
            new SoLuongVaDonGiaJFrame().setVisible(true);
            String MaSanPham = tblSanPham.getValueAt(row,0).toString();
            createCTPN(MaSanPham);  
            fetchDSSPWhenAddingCTPN(MaSanPham);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblSanPhamDaThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamDaThemMouseClicked
        int column = tblSanPhamDaThem.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPhamDaThem.getRowHeight();

        if(row < tblSanPhamDaThem.getRowCount() && row >=0 && column<tblSanPhamDaThem.getColumnCount() && column >=0)
        {
            String MaSanPham = tblSanPhamDaThem.getValueAt(row,0).toString();
            if(tblSanPhamDaThem.getValueAt(row,column).toString().equalsIgnoreCase("Xóa sản phẩm đã thêm"))
            {
                removeChiTietPhieuNhap(MaSanPham);
                fetchData();
                fetchDataBottom();     
            }
        }
    }//GEN-LAST:event_tblSanPhamDaThemMouseClicked

    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
        ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(quanlycuahangsach.quanlycuahangsach.bg.getRootPane())).setEnabled(true);
        this.dispose();
        TaoPhieuNhapJPanel.fetchTable();
        TaoPhieuNhapJPanel.TinhTien();
    }//GEN-LAST:event_pnlXacNhanMouseClicked

    private void jLabel5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y-yMouse);
    }//GEN-LAST:event_jLabel5MouseDragged

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jLabel5MousePressed

    private void txtTimKiemTenSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenSanPhamKeyPressed
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTenSanPhamKeyPressed

    private void txtTimKiemMaSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamKeyPressed
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaSanPhamKeyPressed

    private void txtTimKiemTenSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenSanPhamKeyReleased
    getAdvancedSearch();        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemTenSanPhamKeyReleased

    private void txtTimKiemMaSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamKeyReleased
      getAdvancedSearch();  // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemMaSanPhamKeyReleased
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
            java.util.logging.Logger.getLogger(ThemSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemSanPhamJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblDonHang1;
    private javax.swing.JLabel lblXacNhan;
    private javax.swing.JPanel pnlNhaXuatBan;
    private javax.swing.JPanel pnlTacGia;
    private javax.swing.JPanel pnlTheLoai;
    private GUI.Rounded pnlThongTin;
    private GUI.Rounded pnlTimKiemMaSanPham;
    private GUI.Rounded pnlTimKiemTenSanPham;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JScrollPane scpSanPham;
    private javax.swing.JScrollPane scpSanPhamDaThem;
    private static javax.swing.JTable tblSanPham;
    private static javax.swing.JTable tblSanPhamDaThem;
    private javax.swing.JTextField txtTimKiemMaSanPham;
    private javax.swing.JTextField txtTimKiemTenSanPham;
    // End of variables declaration//GEN-END:variables
}
