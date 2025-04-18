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
import GUI.Sweet.SweetComboBox;
import GUI.SweetAlert.SweetAlert;
import GUI.Table.CustomScrollBarUI;
import GUI.Table.RenderTable;
import GUI.Table.setIconForCell;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static quanlycuahangsach.Currency.changeCurrency;
import quanlycuahangsach.DateTime;
import static quanlycuahangsach.DateTime.DateStringToTimestamp;

/**
 *
 * @author admin
 */
public class TaoPhieuNhapJPanel extends javax.swing.JPanel {
    private static Object[] columnNames ={"Mã sản phẩm","Tên sản phẩm","Tác giả","Thể loại","Đơn giá","Số lượng","Tạm tính",""};
    SweetComboBox cbxNhaCungCap;
    SweetComboBox cbxTheLoai;
    SweetComboBox cbxTacGia;
    SweetComboBox cbxNhaXuatBan;
    public static PhieuNhapDTO PhieuNhap;
    public static ArrayList<ChiTietPhieuNhapDTO> ChiTietPhieuNhapList; 
    public static ArrayList<ChiTietPhieuNhapDTO> arraySearch;
    private static DefaultTableModel listTableModel;
    private static SanPhamDTO SanPhamTrongTaoPhieu; 
    private static int Tong;
    public static ThemSanPhamJFrame themsanphamJFrame;
    public void setShadow(){
        pnlThongTin.setShadow(1);
        pnlTblDonHangChuaXacNhan.setShadow(1);
    }    
    private static void setIcon()
    {
        for(int j = 0 ; j<tblPhieuNhapHang.getRowCount();j++){
                tblPhieuNhapHang.getColumnModel().getColumn(0).setCellRenderer(new setIconForCell(tblPhieuNhapHang,j,0));
                tblPhieuNhapHang.getColumnModel().getColumn(7).setCellRenderer(new setIconForCell(tblPhieuNhapHang,j,7));
        }
    } 
    public void changeTable(JTable table,JScrollPane scp){
        table.setAutoCreateColumnsFromModel(true);
        //Custom a table
        table.getTableHeader().setBackground(Color.decode("#ffffff"));
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.getTableHeader().setForeground(Color.decode("#003466"));
        table.getTableHeader().setPreferredSize(new Dimension(scp.getWidth(),30));
        table.setShowHorizontalLines(true);
        table.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setShowGrid(false);
        table.setRowMargin(5);
        scp.getViewport().setBackground(Color.decode("#ffffff"));
        UIManager.getDefaults().put("TableHeader.cellBorder" , BorderFactory.createEmptyBorder(0,0,0,0));
        UIManager.getDefaults().put("Table.scrollPaneBorder" , BorderFactory.createEmptyBorder(0,0,0,0));
        scp.setViewportBorder(null);  
        scp.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER); 
    }
    public void setData()
    {
        lblMaPhieuNhap.setText(quanlycuahangsach.quanlycuahangsach.PhieuNhapBUS.getLatest());
    }
    public static void ShowTable(JTable table,Object[] columnName)
    {
        table.setDefaultRenderer(Object.class, new RenderTable());
        Object columnNames[] = columnName;
        listTableModel = new DefaultTableModel(columnNames,0);           
    } 
    public static void fetchTable(){
        tblPhieuNhapHang.setDefaultRenderer(Object.class, new RenderTable());
        String xoa = "Xóa";
        listTableModel = new DefaultTableModel(columnNames,0);
        ChiTietPhieuNhapList.forEach(CTPN -> {
        Vector row = new Vector();
        SanPhamDTO SanPhamTrongThemPhieu = quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(CTPN.getMaSanPham());
        row.add(CTPN.getMaSanPham());
        row.add(SanPhamTrongThemPhieu.getTenSanPham());
        row.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(SanPhamTrongThemPhieu.getMaTacGia()));
        row.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(SanPhamTrongThemPhieu.getMaTheLoai()));
        row.add(changeCurrency(CTPN.getDonGia()));
        row.add(CTPN.getSoLuong());
        row.add(changeCurrency(CTPN.getThanhTien()));
        row.add(xoa);
        listTableModel.addRow(row);
        });
        tblPhieuNhapHang.setModel(listTableModel); 
        setIcon();
        setJTableColumnsWidth(tblPhieuNhapHang,884,15,25,20,10,10,10,10,5);
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
        String[] nhacungcap = quanlycuahangsach.quanlycuahangsach.NhaCungCapBUS.getTenTheLoai();
        cbxNhaCungCap = new SweetComboBox("#CAE5F6","#181818",0,0,170,30,nhacungcap);
        pnlNhaCungCap.add(cbxNhaCungCap);
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
    public void search()
    {
        listTableModel.setRowCount(0);
        arraySearch.forEach(SanPham ->{
        Vector row = new Vector();
        SanPhamTrongTaoPhieu = quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(SanPham.getMaSanPham());
            row.add(SanPham.getMaSanPham());
            row.add(SanPhamTrongTaoPhieu.getTenSanPham());
            row.add(quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenByMa(SanPhamTrongTaoPhieu.getMaTacGia()));
            row.add(quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenByMa(SanPhamTrongTaoPhieu.getMaTheLoai()));
            row.add(SanPham.getDonGia());
            row.add(SanPham.getSoLuong());
            row.add(SanPham.getThanhTien());
            row.add("Xóa");
        listTableModel.addRow(row);
        });
        tblPhieuNhapHang.setModel(listTableModel);
        setIcon();
        setJTableColumnsWidth(tblPhieuNhapHang,884,15,25,20,10,10,10,10,5);
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
        arraySearch = quanlycuahangsach.quanlycuahangsach.ChiTietPhieuNhapBUS.advancedSearchThemSanPham(advancedSearch);
        search();
    }      
    public TaoPhieuNhapJPanel() {
        initComponents();
        setShadow();
        changeTable(tblPhieuNhapHang,scpPhieuNhapHang);
        ShowTable(tblPhieuNhapHang,columnNames);
        setDate();
        setData();
        setComboBox();
        setIcon();
        pnlThemSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlHuyBo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        ChiTietPhieuNhapList = new ArrayList<>();
    }
    public void setDate(){
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        lblNgay.setText(dateFormat.format(d));
    }
    public static void TinhTien()
    {
        Tong=0;
        ChiTietPhieuNhapList.forEach(CTPN -> {
              Tong+=Integer.parseInt(CTPN.getThanhTien());
             });
        lblTongTien.setText(changeCurrency(Integer.toString(Tong)));
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlNhaCungCap = new javax.swing.JPanel();
        lblMaPhieuNhap = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnlTimKiemTenSanPham = new GUI.Rounded();
        txtTimKiemTenSanPham = new javax.swing.JTextField();
        pnlTheLoai = new javax.swing.JPanel();
        pnlTacGia = new javax.swing.JPanel();
        pnlNhaXuatBan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTimKiemMaSanPham = new GUI.Rounded();
        txtTimKiemMaSanPham = new javax.swing.JTextField();
        pnlThemSanPham = new GUI.Rounded();
        lblThemSanPham = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        pnlTblDonHangChuaXacNhan = new GUI.Rounded();
        scpPhieuNhapHang = new javax.swing.JScrollPane();
        tblPhieuNhapHang = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }
        };
        jLabel2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTongTien1 = new javax.swing.JLabel();
        pnlHuyBo = new GUI.Rounded();
        lblHuyBo = new javax.swing.JLabel();
        pnlXacNhan = new GUI.Rounded();
        lblXacNhan = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));

        pnlThongTin.setBackground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setForeground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 52, 102));
        jLabel5.setText("Mã phiếu nhập:");
        pnlThongTin.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 30));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 52, 102));
        jLabel6.setText("Nhà cung cấp:");
        pnlThongTin.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 30));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 146, 242));
        jLabel9.setText("Tìm kiếm sản phẩm");
        pnlThongTin.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        pnlNhaCungCap.setBackground(new java.awt.Color(202, 229, 246));
        pnlNhaCungCap.setPreferredSize(new java.awt.Dimension(170, 30));

        javax.swing.GroupLayout pnlNhaCungCapLayout = new javax.swing.GroupLayout(pnlNhaCungCap);
        pnlNhaCungCap.setLayout(pnlNhaCungCapLayout);
        pnlNhaCungCapLayout.setHorizontalGroup(
            pnlNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        pnlNhaCungCapLayout.setVerticalGroup(
            pnlNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongTin.add(pnlNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        lblMaPhieuNhap.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblMaPhieuNhap.setForeground(new java.awt.Color(0, 146, 242));
        lblMaPhieuNhap.setText("null");
        pnlThongTin.add(lblMaPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, 30));

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 52, 102));
        jLabel7.setText("Tên sản phẩm:");
        pnlThongTin.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 30));

        pnlTimKiemTenSanPham.setBackground(new java.awt.Color(238, 243, 247));
        pnlTimKiemTenSanPham.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemTenSanPham.setFocusable(false);

        txtTimKiemTenSanPham.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemTenSanPham.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtTimKiemTenSanPham.setForeground(new java.awt.Color(18, 18, 18));
        txtTimKiemTenSanPham.setBorder(null);
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

        pnlThongTin.add(pnlTimKiemTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

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

        pnlThongTin.add(pnlTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 150, 30));

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

        pnlThongTin.add(pnlTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, -1));

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

        pnlThongTin.add(pnlNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, -1, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 52, 102));
        jLabel1.setText("Mã sản phẩm:");
        pnlThongTin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, -1, 30));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 52, 102));
        jLabel3.setText("Ngày nhập hàng:");
        pnlThongTin.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, -1, 30));

        pnlTimKiemMaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnlTimKiemMaSanPham.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemMaSanPham.setFocusable(false);

        txtTimKiemMaSanPham.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemMaSanPham.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtTimKiemMaSanPham.setForeground(new java.awt.Color(18, 18, 18));
        txtTimKiemMaSanPham.setBorder(null);
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
                .addComponent(txtTimKiemMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemMaSanPhamLayout.setVerticalGroup(
            pnlTimKiemMaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlThongTin.add(pnlTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 160, -1));

        pnlThemSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnlThemSanPham.setForeground(new java.awt.Color(0, 146, 242));
        pnlThemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThemSanPhamMouseClicked(evt);
            }
        });

        lblThemSanPham.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblThemSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThemSanPham.setText("Thêm sản phẩm");

        javax.swing.GroupLayout pnlThemSanPhamLayout = new javax.swing.GroupLayout(pnlThemSanPham);
        pnlThemSanPham.setLayout(pnlThemSanPhamLayout);
        pnlThemSanPhamLayout.setHorizontalGroup(
            pnlThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlThemSanPhamLayout.setVerticalGroup(
            pnlThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThemSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblThemSanPham)
                .addContainerGap())
        );

        pnlThongTin.add(pnlThemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 210, 130, -1));

        lblNgay.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblNgay.setForeground(new java.awt.Color(0, 146, 242));
        lblNgay.setText("#PN1234");
        pnlThongTin.add(lblNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, -1, 30));

        pnlTblDonHangChuaXacNhan.setBackground(new java.awt.Color(238, 243, 247));
        pnlTblDonHangChuaXacNhan.setForeground(new java.awt.Color(238, 243, 247));

        scpPhieuNhapHang.setForeground(new java.awt.Color(255, 255, 255));

        tblPhieuNhapHang.setAutoCreateRowSorter(true);
        tblPhieuNhapHang.setForeground(new java.awt.Color(18, 18, 18));
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

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 146, 242));
        jLabel2.setText("Sản phẩm nhập");

        lblTongTien.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(210, 48, 44));
        lblTongTien.setText("0");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 52, 102));
        jLabel4.setText("Tổng:");

        lblTongTien1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTongTien1.setForeground(new java.awt.Color(210, 48, 44));
        lblTongTien1.setText("vnđ");

        javax.swing.GroupLayout pnlTblDonHangChuaXacNhanLayout = new javax.swing.GroupLayout(pnlTblDonHangChuaXacNhan);
        pnlTblDonHangChuaXacNhan.setLayout(pnlTblDonHangChuaXacNhanLayout);
        pnlTblDonHangChuaXacNhanLayout.setHorizontalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpPhieuNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE)
                    .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(30, 30, 30)
                .addComponent(lblTongTien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTongTien1)
                .addGap(73, 73, 73))
        );
        pnlTblDonHangChuaXacNhanLayout.setVerticalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpPhieuNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlHuyBo.setBackground(new java.awt.Color(238, 243, 247));
        pnlHuyBo.setForeground(new java.awt.Color(238, 38, 64));
        pnlHuyBo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHuyBoMouseClicked(evt);
            }
        });

        lblHuyBo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblHuyBo.setForeground(new java.awt.Color(255, 255, 255));
        lblHuyBo.setText("Hủy bỏ");

        javax.swing.GroupLayout pnlHuyBoLayout = new javax.swing.GroupLayout(pnlHuyBo);
        pnlHuyBo.setLayout(pnlHuyBoLayout);
        pnlHuyBoLayout.setHorizontalGroup(
            pnlHuyBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHuyBoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblHuyBo)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlHuyBoLayout.setVerticalGroup(
            pnlHuyBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHuyBo, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

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
        lblXacNhan.setText("Tạo phiếu");

        javax.swing.GroupLayout pnlXacNhanLayout = new javax.swing.GroupLayout(pnlXacNhan);
        pnlXacNhan.setLayout(pnlXacNhanLayout);
        pnlXacNhanLayout.setHorizontalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlXacNhanLayout.setVerticalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                            .addComponent(pnlTblDonHangChuaXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(pnlHuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(676, 676, 676)
                        .addComponent(pnlXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlTblDonHangChuaXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pnlThemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThemSanPhamMouseClicked
        PhieuNhap = new PhieuNhapDTO();
        PhieuNhap.setMaPhieuNhap(lblMaPhieuNhap.getText());
//        PhieuNhap.setMaNhanVien(quanlycuahangsach.quanlycuahangsach.currentNhanVien.getMaNhanVien());
        
        themsanphamJFrame = new ThemSanPhamJFrame();
        themsanphamJFrame.setVisible(true);
    }//GEN-LAST:event_pnlThemSanPhamMouseClicked

    private void tblPhieuNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapHangMouseClicked
        int column = tblPhieuNhapHang.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblPhieuNhapHang.getRowHeight();
        if(row < tblPhieuNhapHang.getRowCount() && row >=0 && column<tblPhieuNhapHang.getColumnCount() && column >=0)
        {
            if(tblPhieuNhapHang.getValueAt(row,column).toString().equalsIgnoreCase("Xóa"))
            {
                String MaSanPham=tblPhieuNhapHang.getValueAt(row,0).toString();
                ThemSanPhamJFrame.removeChiTietPhieuNhap(MaSanPham);               
                fetchTable();
                TinhTien();
            }
        }
    }//GEN-LAST:event_tblPhieuNhapHangMouseClicked

    private void pnlHuyBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHuyBoMouseClicked
       if (SwingUtilities.isLeftMouseButton(evt))
       { 
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new PhieuNhapHangJPanel());
        this.validate();
        this.repaint();
        ThemSanPhamJFrame.DanhSachSanPham = null;
       }
    }//GEN-LAST:event_pnlHuyBoMouseClicked
    private void setPhieuNhap()
    {
            PhieuNhap.setMaNhaCungCap(quanlycuahangsach.quanlycuahangsach.NhaCungCapBUS.getMaNhaCungCapByTenNhaCungCap(cbxNhaCungCap.getSelectedItem().toString()));
            PhieuNhap.setMaNhanVien(quanlycuahangsach.quanlycuahangsach.currentNhanVien.getMaNhanVien());
            PhieuNhap.setNgayNhap(DateStringToTimestamp(lblNgay.getText()));  
            PhieuNhap.setTongTien(Integer.toString(Tong));
    }
    private void updateIntoSanPham()
    {
        ChiTietPhieuNhapList.forEach(CTPN -> {
                SanPhamDTO sanphamnhap = quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(CTPN.getMaSanPham());
                int soluongthem = Integer.parseInt(sanphamnhap.getSoLuong()) + Integer.parseInt(CTPN.getSoLuong());
                sanphamnhap.setSoLuong(soluongthem+"");
                quanlycuahangsach.quanlycuahangsach.SachBUS.edit(sanphamnhap);
        });
    }
    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
        if (PhieuNhap == null) {
            SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng thêm sản phẩm vào phiếu nhập", 1);
        } else {
            if (!ChiTietPhieuNhapList.isEmpty()) 
            {
                setPhieuNhap();
                quanlycuahangsach.quanlycuahangsach.PhieuNhapBUS.add(PhieuNhap);
                ChiTietPhieuNhapList.forEach(CTPN -> {
                    quanlycuahangsach.quanlycuahangsach.ChiTietPhieuNhapBUS.add(CTPN);
                });
                SweetAlert.showSweetAlert(new JFrame(), "Thành Công", "Thêm phiếu nhập thành công", 0);
                this.removeAll();
                this.setLayout(new BorderLayout());
                this.add(new PhieuNhapHangJPanel());
                this.validate();
                this.repaint();
            } else {
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Không có sản phẩm trong giỏ", 1);
            }
            updateIntoSanPham();
            ThemSanPhamJFrame.DanhSachSanPham = null;
        }
    }//GEN-LAST:event_pnlXacNhanMouseClicked

    private void txtTimKiemTenSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenSanPhamKeyPressed
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTenSanPhamKeyPressed

    private void txtTimKiemTenSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTenSanPhamKeyReleased

    private void txtTimKiemMaSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamKeyPressed
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaSanPhamKeyPressed

    private void txtTimKiemMaSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaSanPhamKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblHuyBo;
    private javax.swing.JLabel lblMaPhieuNhap;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblThemSanPham;
    private static javax.swing.JLabel lblTongTien;
    private static javax.swing.JLabel lblTongTien1;
    private javax.swing.JLabel lblXacNhan;
    private GUI.Rounded pnlHuyBo;
    private javax.swing.JPanel pnlNhaCungCap;
    private javax.swing.JPanel pnlNhaXuatBan;
    private javax.swing.JPanel pnlTacGia;
    private GUI.Rounded pnlTblDonHangChuaXacNhan;
    private javax.swing.JPanel pnlTheLoai;
    private GUI.Rounded pnlThemSanPham;
    private GUI.Rounded pnlThongTin;
    private GUI.Rounded pnlTimKiemMaSanPham;
    private GUI.Rounded pnlTimKiemTenSanPham;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JScrollPane scpPhieuNhapHang;
    private static javax.swing.JTable tblPhieuNhapHang;
    private javax.swing.JTextField txtTimKiemMaSanPham;
    private javax.swing.JTextField txtTimKiemTenSanPham;
    // End of variables declaration//GEN-END:variables
}
