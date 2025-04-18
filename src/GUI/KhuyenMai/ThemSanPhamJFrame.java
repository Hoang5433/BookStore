/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.KhuyenMai;

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
import static quanlycuahangsach.Regexp.So;
import static quanlycuahangsach.unicode.removeAccent;

/**
 *
 * @author admin
 */
public class ThemSanPhamJFrame extends javax.swing.JFrame {

    int xMouse, yMouse;
    static ArrayList<SanPhamDTO> arraySearch;
    ArrayList<SanPhamDTO> Arrsearch;
    SweetComboBox cbxTheLoai, cbxTacGia, cbxNhaXuatBan;
    static DefaultTableModel listTableModelTop;
    Object[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Tác giả", "Thể loại", "Nhà xuất bản", ""};
    Object[] columnNamesBot = {"Mã sản phẩm", "Tên sản phẩm", "Tác giả", "Thể loại", "Nhà xuất bản", ""};
    /**
     * Creates new form ThemSanPhamVaoDonHangJFrame
     */
    
    
    public void getAdvancedSearch(){
            Arrsearch = new ArrayList<>();
            arraySearch.forEach(SanPhamKhuyenMai -> {
            Vector row = new Vector();
            SanPhamDTO SanPham = quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(SanPhamKhuyenMai.getMaSanPham());
            boolean TenTacGia = cbxTacGia.getSelectedItem().toString().equals("Tác giả");
                boolean TenTheLoai = cbxTheLoai.getSelectedItem().toString().equals("Thể loại");
                boolean TenNhaXuatBan = cbxNhaXuatBan.getSelectedItem().toString().equals("Nhà xuất bản");
                        if(TenTacGia == false)
                            TenTacGia = SanPham.getMaTacGia().equals(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getMaByName(cbxTacGia.getSelectedItem().toString()));
                        if(TenTheLoai == false)
                            TenTheLoai = SanPham.getMaTheLoai().equals(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getMaByName(cbxTheLoai.getSelectedItem().toString()));
                        if(TenNhaXuatBan == false)
                            TenNhaXuatBan = SanPham.getMaNhaXuatBan().equals(quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getMaByName(cbxNhaXuatBan.getSelectedItem().toString()));
                        
                        if(removeAccent(SanPham.getTenSanPham().toLowerCase()).contains(removeAccent(txtTimKiemTenSanPham.getText().toLowerCase())) &&
                        SanPham.getMaSanPham().contains(txtTimKiemMaSanPham.getText().toUpperCase()) &&
                        TenTacGia && TenNhaXuatBan && TenTheLoai){
                    Vector data = new Vector();
            data.add(SanPham.getMaSanPham());
            data.add(SanPham.getTenSanPham());
            data.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(SanPham.getMaTacGia()));
            data.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(SanPham.getMaTheLoai()));
            data.add(quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getTenByMa(SanPham.getMaNhaXuatBan()));
            data.add("Thêm sản phẩm");
            listTableModelTop.addRow(data);
            Arrsearch.add(SanPham);
                        }
            });
            search();
    }
    public void search(){
    listTableModelTop.setRowCount(0);
    Arrsearch.forEach(Sach->{
         Vector data = new Vector();
            data.add(Sach.getMaSanPham());
            data.add(Sach.getTenSanPham());
            data.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(Sach.getMaTacGia()));
            data.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(Sach.getMaTheLoai()));
            data.add(quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getTenByMa(Sach.getMaNhaXuatBan()));
            data.add("Thêm sản phẩm");
            listTableModelTop.addRow(data);
    });
    }
    public static void fetchAllSanPham() {
        listTableModelTop.setRowCount(0);
        if(arraySearch == null){
            arraySearch = new ArrayList<>(quanlycuahangsach.quanlycuahangsach.SachBUS.SachList);
        }
        arraySearch.forEach(Sach ->{
            Vector data = new Vector();
            data.add(Sach.getMaSanPham());
            data.add(Sach.getTenSanPham());
            data.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(Sach.getMaTacGia()));
            data.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(Sach.getMaTheLoai()));
            data.add(quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getTenByMa(Sach.getMaNhaXuatBan()));
            data.add("Thêm sản phẩm");
            listTableModelTop.addRow(data);
            
        });
        
        tblSanPham.setModel(listTableModelTop);

    }

    public void setIcon() {
        for (int j = 0; j < tblSanPham.getRowCount(); j++) {
            tblSanPham.getColumnModel().getColumn(5).setCellRenderer(new setIconForCell(tblSanPham, j, 5));
        }
    }

    public void setIconBot() {
        for (int i = 0; i < tblSanPhamDaThem.getRowCount(); i++) {
            tblSanPhamDaThem.getColumnModel().getColumn(5).setCellRenderer(new setIconForCell(tblSanPhamDaThem, i, 5));
        }
    }

    public ThemSanPhamJFrame() {
        ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(quanlycuahangsach.quanlycuahangsach.bg)).setEnabled(false);
        initComponents();
        customTable(tblSanPham, scpSanPham);
        ShowTable(tblSanPham, columnNames);
        customTable(tblSanPhamDaThem, scpSanPhamDaThem);
        ShowTableBottom(tblSanPhamDaThem, columnNames);
        setIcon();
        setComboBox();
        setLocationRelativeTo(null);
        setJTableColumnsWidth(tblSanPham, 884, 15, 35, 15, 15, 15, 5);
        setJTableColumnsWidth(tblSanPhamDaThem, 884, 15, 35, 15, 15, 15, 5);
        pnlXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void ShowTable(JTable table, Object[] columnName) {
        table.setDefaultRenderer(Object.class, new RenderTable());
        listTableModelTop = new DefaultTableModel(columnNames, 0);
        fetchAllSanPham();
    }

    public void ShowTableBottom(JTable table, Object[] columnName) {

        table.setDefaultRenderer(Object.class, new RenderTable());
        if(GUI.KhuyenMai.ThemChiTietKhuyenMaiJPanel.listTableModel==null)
        GUI.KhuyenMai.ThemChiTietKhuyenMaiJPanel.listTableModel = new DefaultTableModel(columnNamesBot, 0);
        table.setModel(GUI.KhuyenMai.ThemChiTietKhuyenMaiJPanel.listTableModel);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    

    

    public void setComboBox() {

        String[] theloai = quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenTheLoai();
        String[] trangthai = {"Còn hàng","Hết hàng"};
        cbxTheLoai = new SweetComboBox("#CAE5F6", "#181818", 0, 0, 160, 30, theloai);
        pnlTheLoai.add(cbxTheLoai);
        String[] tacgia = quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenTacGia();
        cbxTacGia = new SweetComboBox("#CAE5F6", "#181818", 0, 0, 160, 30, tacgia);
        pnlTacGia.add(cbxTacGia);
        String[] nhaxuatban = quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getTenNhaXuatBan();
        cbxNhaXuatBan = new SweetComboBox("#CAE5F6", "#181818", 0, 0, 160, 30, nhaxuatban);
        
        pnlNhaXuatBan.add(cbxNhaXuatBan);
        cbxTacGia.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                getAdvancedSearch();
            }
        });
        cbxTheLoai.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                getAdvancedSearch();
            }
        });
        cbxNhaXuatBan.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                getAdvancedSearch();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        pnlNhaXuatBan1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
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

        jPanel1.add(pnlTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 150, 30));

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

        jPanel1.add(pnlTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, -1, -1));

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

        jPanel1.add(pnlNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, -1, -1));

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

        pnlNhaXuatBan1.setBackground(new java.awt.Color(202, 229, 246));
        pnlNhaXuatBan1.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlNhaXuatBan1Layout = new javax.swing.GroupLayout(pnlNhaXuatBan1);
        pnlNhaXuatBan1.setLayout(pnlNhaXuatBan1Layout);
        pnlNhaXuatBan1Layout.setHorizontalGroup(
            pnlNhaXuatBan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlNhaXuatBan1Layout.setVerticalGroup(
            pnlNhaXuatBan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(pnlNhaXuatBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, -1, -1));

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
        int row = evt.getY() / tblSanPham.getRowHeight();

        if (tblSanPham.getModel().getValueAt(row, column).equals("Thêm sản phẩm")) {
            
            SanPhamDTO Sach = quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(tblSanPham.getModel().getValueAt(row, 0).toString());
            Vector data = new Vector();
            data.add(Sach.getMaSanPham());
            data.add(Sach.getTenSanPham());
            data.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(Sach.getMaTacGia()));
            data.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(Sach.getMaTheLoai()));
            data.add(quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getTenByMa(Sach.getMaNhaXuatBan()));
            data.add("Xóa");
            listTableModelTop.removeRow(row);
            arraySearch.remove(Sach);
            GUI.KhuyenMai.ThemChiTietKhuyenMaiJPanel.currentSanPhamDuocKhuyenMai.add(Sach);
            GUI.KhuyenMai.ThemChiTietKhuyenMaiJPanel.listTableModel.addRow(data);
            setIconBot();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblSanPhamDaThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamDaThemMouseClicked
        int column = tblSanPhamDaThem.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tblSanPhamDaThem.getRowHeight();

        if (tblSanPhamDaThem.getValueAt(row, column).toString().equalsIgnoreCase("Xóa")) {
            SanPhamDTO SachXoa = quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(tblSanPhamDaThem.getModel().getValueAt(row, 0).toString());
            Vector data = new Vector();
            data.add(SachXoa.getMaSanPham());
            data.add(SachXoa.getTenSanPham());
            data.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(SachXoa.getMaTacGia()));
            data.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(SachXoa.getMaTheLoai()));
            data.add(quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getTenByMa(SachXoa.getMaNhaXuatBan()));
            data.add("Thêm sản phẩm");
            listTableModelTop.addRow(data);
            GUI.KhuyenMai.ThemChiTietKhuyenMaiJPanel.currentSanPhamDuocKhuyenMai.remove(SachXoa);
            arraySearch.add(SachXoa);
            ((DefaultTableModel) tblSanPhamDaThem.getModel()).removeRow(row);
            setIcon();

        }


    }//GEN-LAST:event_tblSanPhamDaThemMouseClicked

    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
        ((javax.swing.JFrame) SwingUtilities.getWindowAncestor(quanlycuahangsach.quanlycuahangsach.bg)).setEnabled(true);
        GUI.KhuyenMai.ThemChiTietKhuyenMaiJPanel.fetchAll();
        this.dispose();
    }//GEN-LAST:event_pnlXacNhanMouseClicked

    private void jLabel5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jLabel5MouseDragged

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jLabel5MousePressed

    private void txtTimKiemTenSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTenSanPhamKeyReleased

    private void txtTimKiemMaSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaSanPhamKeyReleased

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
    private javax.swing.JPanel pnlNhaXuatBan1;
    private javax.swing.JPanel pnlTacGia;
    private javax.swing.JPanel pnlTheLoai;
    private GUI.Rounded pnlThongTin;
    private GUI.Rounded pnlTimKiemMaSanPham;
    private GUI.Rounded pnlTimKiemTenSanPham;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JScrollPane scpSanPham;
    private javax.swing.JScrollPane scpSanPhamDaThem;
    private static javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamDaThem;
    private javax.swing.JTextField txtTimKiemMaSanPham;
    private javax.swing.JTextField txtTimKiemTenSanPham;
    // End of variables declaration//GEN-END:variables
}
