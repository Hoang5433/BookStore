/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.KhuyenMai;

import DTO.ChiTietKhuyenMaiDTO;
import DTO.SanPhamDTO;
import DTO.SanPhamKhuyenMaiDTO;
import GUI.Sweet.SweetComboBox;
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
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import quanlycuahangsach.DateTime;
import static quanlycuahangsach.DateTime.DateStringToTimestamp;

/**
 *
 * @author admin
 */
public class ThemChiTietKhuyenMaiJPanel extends javax.swing.JPanel {
    SweetComboBox cbxChuongTrinhKhuyenMai;
    Object[] columnNames ={"Mã sản phẩm","Tên sản phẩm","Tác giả","Thể loại","Nhà xuất bản",""};
    static DefaultTableModel listTableModel;
    static ArrayList<SanPhamDTO> currentSanPhamDuocKhuyenMai = new ArrayList<>();
   
    static ChiTietKhuyenMaiDTO currentChiTietKhuyenMai = new ChiTietKhuyenMaiDTO();
    public void setComboBox(){
        String[] trangthai = quanlycuahangsach.quanlycuahangsach.ChuongTrinhKhuyenMaiBUS.getChuongTrinhKhuyenMaiList();
        cbxChuongTrinhKhuyenMai = new SweetComboBox("#CAE5F6","#181818",0,0,190,30,trangthai);
         pnlMaChuongTrinhKhuyenMai.add(cbxChuongTrinhKhuyenMai);
         
    }    
    public void setShadow(){
        pnlThongTin.setShadow(1);
    }
    public static void fetchAll(){
        
        tblSanPham.setModel(listTableModel);
        setIcon();
        
    }
    public void setData(){
        currentChiTietKhuyenMai = new ChiTietKhuyenMaiDTO();
        
        currentChiTietKhuyenMai.setMaChuongTrinhKhuyenMai(cbxChuongTrinhKhuyenMai.getSelectedItem().toString());
        currentChiTietKhuyenMai.setMaChiTietKhuyenMai(lblMaChiTietKhuyenMai.getText());
        currentChiTietKhuyenMai.setMaCode(txtMaKhuyenMai.getText());
        currentChiTietKhuyenMai.setSoLuong(txtSoLuong.getText());
        currentChiTietKhuyenMai.setGiaTriGiamToiDa(txtGiamToiDa.getText());
        currentChiTietKhuyenMai.setGiaTriToiThieuSuDung(txtGiaTriToiThieu.getText());
        if(radPhanTram.isSelected()){
            currentChiTietKhuyenMai.setPhanTramKhuyenMai(txtPhanTram.getText());
            currentChiTietKhuyenMai.setGiaTienKhuyenMai("0");
        }
        else{
            currentChiTietKhuyenMai.setPhanTramKhuyenMai("0");
            currentChiTietKhuyenMai.setGiaTienKhuyenMai(txtGiaTien.getText()); 
        }
        currentChiTietKhuyenMai.setThoiGianBatDauSuDung(DateTime.DateStringToTimestamp(DateTime.dateFormat.format(dateBatDau.getDate())));
        currentChiTietKhuyenMai.setThoiGianKetThucSuDung(DateTime.DateStringToTimestamp(DateTime.dateFormat.format(dateKetThuc.getDate())));
        currentChiTietKhuyenMai.setSoLuongDaDung("0");
        
    }
    public boolean isNull(){
        if(radGiaTien.isSelected() && txtGiaTien.equals("")) return false; 
        if(radPhanTram.isSelected() && txtPhanTram.equals("")) return false;
       return txtSoLuong.equals("") || txtMaKhuyenMai.equals("");
    }
    public void initEvent(){
        javax.swing.ButtonGroup z = new javax.swing.ButtonGroup();
        z.add(radPhanTram);
        z.add(radGiaTien);
        txtGiaTien.setEnabled(false);
        txtGiaTien.setBackground(Color.decode("#EBEBE4")); 
        txtPhanTram.setEnabled(false);
        txtPhanTram.setBackground(Color.decode("#EBEBE4"));        
        radPhanTram.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie)
                {
                    javax.swing.JRadioButton currentItem = (javax.swing.JRadioButton) ie.getItem();
                    if(ie.getStateChange()==1){
                        currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
                        txtPhanTram.setEnabled(true);
                        txtPhanTram.setBackground(Color.decode("#CAE5F6"));
                        scpSanPham.setVisible(true);
                        txtGiamToiDa.setEnabled(true);
                        txtGiamToiDa.setBackground(Color.decode("#CAE5F6"));
                        lblDonHang.setVisible(true);
                        txtGiamToiDa.setText("");
                        pnlThemSanPham.setVisible(true);                         
                    }else
                        currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png")));                        
                        txtGiaTien.setEnabled(false);
                        txtGiaTien.setText("");
                        txtGiaTien.setBackground(Color.decode("#dddddd"));                       

                }});
        radGiaTien.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie)
                {
                    javax.swing.JRadioButton currentItem = (javax.swing.JRadioButton) ie.getItem();
                    if(ie.getStateChange()==1){
                        System.out.println(ie.getItem().toString());
                        currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked.png")));
                        txtGiaTien.setEnabled(true);
                        txtGiaTien.setBackground(Color.decode("#CAE5F6"));
                        scpSanPham.setVisible(false);
                        lblDonHang.setVisible(false);
                        pnlThemSanPham.setVisible(false); 
                        txtGiamToiDa.setText("");
                        txtGiamToiDa.setEnabled(false);
                         txtGiamToiDa.setBackground(Color.decode("#dddddd"));
                         txtGiamToiDa.setForeground(Color.black);
                    }else
                        currentItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png")));
                        txtPhanTram.setEnabled(false);
                        txtPhanTram.setText("");
                        txtPhanTram.setBackground(Color.decode("#dddddd"));
                        
                        
                }});
        
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
    
    public static void setIcon() {
        for (int j = 0; j < tblSanPham.getRowCount(); j++) {
            tblSanPham.getColumnModel().getColumn(5).setCellRenderer(new setIconForCell(tblSanPham, j, 5));
        }
    }    

    public void ShowTable(JTable table,Object[] columnName){
        table.setDefaultRenderer(Object.class, new RenderTable());
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setModel(listTableModel);

       
        
    }     
    public void setDate(){
        dateBatDau.setDateFormatString("dd/MM/yyyy");
        dateKetThuc.setDateFormatString("dd/MM/yyyy");
        dateBatDau.setDate(new Date());
        dateKetThuc.setDate(dateBatDau.getDate());
        dateBatDau.setMinSelectableDate(new Date());
        dateKetThuc.setMinSelectableDate(dateBatDau.getDate());        
    }    
    public ThemChiTietKhuyenMaiJPanel() {
        initComponents();
        initEvent();
        changeTable(tblSanPham,scpSanPham);
        ShowTable(tblSanPham,columnNames);
        setJTableColumnsWidth(tblSanPham,976,15,35,15,15,15,5);
        scpSanPham.setVisible(false);
        lblDonHang.setVisible(false);
        pnlThemSanPham.setVisible(false);
        setDate();
        setComboBox();
        setShadow();
        listTableModel = null;
    ThemSanPhamJFrame.arraySearch = null;
        setData();
         lblMaChiTietKhuyenMai.setText(quanlycuahangsach.quanlycuahangsach.ChiTietKhuyenMaiBUS.getLatest());
         radGiaTien.setSelected(true);
        pnlThemSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlHuyBo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dateBatDau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dateKetThuc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  
        
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
        scpSanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        lblDonHang = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTitleMaKhuyenMai = new javax.swing.JLabel();
        lblTitleMaPhieuNhao = new javax.swing.JLabel();
        lblTitleHinhThucKhuyenMai = new javax.swing.JLabel();
        lblTitleMaChiTietKhuyenMai = new javax.swing.JLabel();
        lblMaChiTietKhuyenMai = new javax.swing.JLabel();
        lblTitleMaPhieuNhao4 = new javax.swing.JLabel();
        lblTitleMaPhieuNhao5 = new javax.swing.JLabel();
        lblTitleMaPhieuNhao7 = new javax.swing.JLabel();
        txtGiaTien = new javax.swing.JTextField();
        txtGiamToiDa = new javax.swing.JTextField();
        lblTitleSoLuong = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        radPhanTram = new javax.swing.JRadioButton();
        radGiaTien = new javax.swing.JRadioButton();
        txtMaKhuyenMai = new javax.swing.JTextField();
        txtPhanTram = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtGiaTriToiThieu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateKetThuc = new com.toedter.calendar.JDateChooser();
        dateBatDau = new com.toedter.calendar.JDateChooser();
        lblTitleMaChuongTrinhKhuyenMai = new javax.swing.JLabel();
        pnlMaChuongTrinhKhuyenMai = new javax.swing.JPanel();
        pnlThemSanPham = new GUI.Rounded();
        lblThemSanPham = new javax.swing.JLabel();
        pnlXacNhan = new GUI.Rounded();
        lblXacNhan = new javax.swing.JLabel();
        pnlHuyBo = new GUI.Rounded();
        lblHuyBo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongTin.setBackground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setForeground(new java.awt.Color(238, 243, 247));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(920, 750));
        pnlThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scpSanPham.setBackground(new java.awt.Color(45, 47, 62));
        scpSanPham.setForeground(new java.awt.Color(45, 47, 62));

        tblSanPham.setAutoCreateRowSorter(true);
        tblSanPham.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
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

        pnlThongTin.add(scpSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 322, 900, 240));

        lblDonHang.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblDonHang.setForeground(new java.awt.Color(0, 146, 242));
        lblDonHang.setText("Sản phẩm sử dụng mã");
        pnlThongTin.add(lblDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 253, 24));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleMaKhuyenMai.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaKhuyenMai.setText("Mã khuyến mãi:");
        jPanel1.add(lblTitleMaKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        lblTitleMaPhieuNhao.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaPhieuNhao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao.setText("Thời gian kết thúc:");
        jPanel1.add(lblTitleMaPhieuNhao, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 150, -1));

        lblTitleHinhThucKhuyenMai.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleHinhThucKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleHinhThucKhuyenMai.setText("Hình thức khuyến mãi:");
        jPanel1.add(lblTitleHinhThucKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        lblTitleMaChiTietKhuyenMai.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaChiTietKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaChiTietKhuyenMai.setText("Mã chi tiết khuyến mãi:");
        jPanel1.add(lblTitleMaChiTietKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 190, 30));

        lblMaChiTietKhuyenMai.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblMaChiTietKhuyenMai.setForeground(new java.awt.Color(0, 146, 242));
        lblMaChiTietKhuyenMai.setText("12345");
        jPanel1.add(lblMaChiTietKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, 30));

        lblTitleMaPhieuNhao4.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaPhieuNhao4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao4.setText("Giá trị sử dụng tối thiểu:");
        jPanel1.add(lblTitleMaPhieuNhao4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 170, -1));

        lblTitleMaPhieuNhao5.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaPhieuNhao5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao5.setText("Giá trị giảm tối đa:");
        jPanel1.add(lblTitleMaPhieuNhao5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 150, -1));

        lblTitleMaPhieuNhao7.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaPhieuNhao7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaPhieuNhao7.setText("Thời gian bắt đầu:");
        jPanel1.add(lblTitleMaPhieuNhao7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 150, 20));

        txtGiaTien.setBackground(new java.awt.Color(202, 229, 246));
        txtGiaTien.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtGiaTien.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtGiaTien.setBorder(null);
        txtGiaTien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiaTienKeyReleased(evt);
            }
        });
        jPanel1.add(txtGiaTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 120, 30));

        txtGiamToiDa.setBackground(new java.awt.Color(202, 229, 246));
        txtGiamToiDa.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        txtGiamToiDa.setBorder(null);
        jPanel1.add(txtGiamToiDa, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 180, 30));

        lblTitleSoLuong.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleSoLuong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleSoLuong.setText("Số lượng:");
        jPanel1.add(lblTitleSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        txtSoLuong.setBackground(new java.awt.Color(202, 229, 246));
        txtSoLuong.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtSoLuong.setBorder(null);
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyPressed(evt);
            }
        });
        jPanel1.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 190, 30));

        radPhanTram.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        radPhanTram.setForeground(new java.awt.Color(0, 52, 102));
        radPhanTram.setText("Phần trăm khuyến mãi:");
        radPhanTram.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        jPanel1.add(radPhanTram, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        radGiaTien.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        radGiaTien.setForeground(new java.awt.Color(0, 52, 102));
        radGiaTien.setText("Giá tiền khuyến mãi:");
        radGiaTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unchecked.png"))); // NOI18N
        jPanel1.add(radGiaTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        txtMaKhuyenMai.setBackground(new java.awt.Color(202, 229, 246));
        txtMaKhuyenMai.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtMaKhuyenMai.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMaKhuyenMai.setBorder(null);
        txtMaKhuyenMai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaKhuyenMaiKeyPressed(evt);
            }
        });
        jPanel1.add(txtMaKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 190, 30));

        txtPhanTram.setBackground(new java.awt.Color(202, 229, 246));
        txtPhanTram.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtPhanTram.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPhanTram.setBorder(null);
        jPanel1.add(txtPhanTram, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 120, 30));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(210, 48, 44));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("đ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, 20, -1));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(210, 48, 44));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("%");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 172, 20, 30));

        txtGiaTriToiThieu.setBackground(new java.awt.Color(202, 229, 246));
        txtGiaTriToiThieu.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        txtGiaTriToiThieu.setBorder(null);
        txtGiaTriToiThieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGiaTriToiThieuKeyPressed(evt);
            }
        });
        jPanel1.add(txtGiaTriToiThieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 180, 30));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(210, 48, 44));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("đ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 20, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(210, 48, 44));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("đ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 20, -1));

        dateKetThuc.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(dateKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 180, -1));

        dateBatDau.setBackground(new java.awt.Color(255, 255, 255));
        dateBatDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateBatDauPropertyChange(evt);
            }
        });
        jPanel1.add(dateBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 180, -1));

        lblTitleMaChuongTrinhKhuyenMai.setForeground(new java.awt.Color(0, 52, 102));
        lblTitleMaChuongTrinhKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleMaChuongTrinhKhuyenMai.setText("Mã chương trình khuyến mãi:");
        jPanel1.add(lblTitleMaChuongTrinhKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 190, -1));

        pnlMaChuongTrinhKhuyenMai.setBackground(new java.awt.Color(202, 229, 246));

        javax.swing.GroupLayout pnlMaChuongTrinhKhuyenMaiLayout = new javax.swing.GroupLayout(pnlMaChuongTrinhKhuyenMai);
        pnlMaChuongTrinhKhuyenMai.setLayout(pnlMaChuongTrinhKhuyenMaiLayout);
        pnlMaChuongTrinhKhuyenMaiLayout.setHorizontalGroup(
            pnlMaChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        pnlMaChuongTrinhKhuyenMaiLayout.setVerticalGroup(
            pnlMaChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(pnlMaChuongTrinhKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 190, 30));

        pnlThongTin.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, 250));

        pnlThemSanPham.setBackground(new java.awt.Color(238, 243, 247));
        pnlThemSanPham.setForeground(new java.awt.Color(0, 146, 242));
        pnlThemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThemSanPhamMouseClicked(evt);
            }
        });

        lblThemSanPham.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblThemSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThemSanPham.setText("Thêm sản phảm");

        javax.swing.GroupLayout pnlThemSanPhamLayout = new javax.swing.GroupLayout(pnlThemSanPham);
        pnlThemSanPham.setLayout(pnlThemSanPhamLayout);
        pnlThemSanPhamLayout.setHorizontalGroup(
            pnlThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlThemSanPhamLayout.setVerticalGroup(
            pnlThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThemSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblThemSanPham)
                .addContainerGap())
        );

        pnlThongTin.add(pnlThemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, -1, -1));

        add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 920, 590));

        pnlXacNhan.setBackground(new java.awt.Color(238, 243, 247));
        pnlXacNhan.setForeground(new java.awt.Color(34, 212, 52));
        pnlXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXacNhanMouseClicked(evt);
            }
        });

        lblXacNhan.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        lblXacNhan.setText("Thêm mã");

        javax.swing.GroupLayout pnlXacNhanLayout = new javax.swing.GroupLayout(pnlXacNhan);
        pnlXacNhan.setLayout(pnlXacNhanLayout);
        pnlXacNhanLayout.setHorizontalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXacNhanLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblXacNhan)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        pnlXacNhanLayout.setVerticalGroup(
            pnlXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 620, 120, 40));

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

        add(pnlHuyBo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPham.getRowHeight();
        if(tblSanPham.getModel().getValueAt(row,column).equals("Xóa")){
            currentSanPhamDuocKhuyenMai.remove(quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(tblSanPham.getModel().getValueAt(row, 0).toString()));
            GUI.KhuyenMai.ThemSanPhamJFrame.arraySearch.add(quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(tblSanPham.getModel().getValueAt(row, 0).toString()));
            
            listTableModel.removeRow(row);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtSoLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtGiaTriToiThieu.requestFocus();
        }
    }//GEN-LAST:event_txtSoLuongKeyPressed

    private void txtMaKhuyenMaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKhuyenMaiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSoLuong.requestFocus();
        }
    }//GEN-LAST:event_txtMaKhuyenMaiKeyPressed

    private void txtGiaTriToiThieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaTriToiThieuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtGiamToiDa.requestFocus();
        }
    }//GEN-LAST:event_txtGiaTriToiThieuKeyPressed

    private void dateBatDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateBatDauPropertyChange
        Date tomorrow = new Date(dateBatDau.getDate().getTime() + (24 * 60 * 60 * 1000));
        dateKetThuc.setDate(tomorrow);
        dateKetThuc.setMinSelectableDate(tomorrow);
    }//GEN-LAST:event_dateBatDauPropertyChange

    private void pnlThemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThemSanPhamMouseClicked
       if (SwingUtilities.isLeftMouseButton(evt)){
           
           new ThemSanPhamJFrame().setVisible(true);
        }   
               
    }//GEN-LAST:event_pnlThemSanPhamMouseClicked

    public void add(){
        setData();
        if(currentSanPhamDuocKhuyenMai.size() <= 0 && radPhanTram.isSelected()){
                GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Chưa thêm sản phẩm", 1);
                return;
                }
        else
        if(quanlycuahangsach.quanlycuahangsach.ChiTietKhuyenMaiBUS.add(currentChiTietKhuyenMai)){
            if(currentSanPhamDuocKhuyenMai.size() == quanlycuahangsach.quanlycuahangsach.SachBUS.SachList.size() || radGiaTien.isSelected()){
            SanPhamKhuyenMaiDTO SanPhamKhuyenMai = new SanPhamKhuyenMaiDTO(currentChiTietKhuyenMai.getMaChiTietKhuyenMai(),null);
                quanlycuahangsach.quanlycuahangsach.SanPhamKhuyenMaiBUS.add(SanPhamKhuyenMai);
            }else{
                
                
            currentSanPhamDuocKhuyenMai.forEach(SanPham->{
                System.out.println(SanPham.getMaSanPham());
                SanPhamKhuyenMaiDTO SanPhamKhuyenMai = new SanPhamKhuyenMaiDTO(currentChiTietKhuyenMai.getMaChiTietKhuyenMai(),SanPham.getMaSanPham());
                quanlycuahangsach.quanlycuahangsach.SanPhamKhuyenMaiBUS.add(SanPhamKhuyenMai);
            });
            }
            
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Thành công", "Lưu thành công", 0);
                        quanlycuahangsach.quanlycuahangsach.SachBUS.fetchAll();
                        this.removeAll();
                        this.setLayout(new BorderLayout());
                        this.add(new ChiTietKhuyenMaiJPanel());
                        this.validate();
                        this.repaint();
                        currentSanPhamDuocKhuyenMai = new ArrayList<>();
        }
    }
    private void pnlXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXacNhanMouseClicked
          if (SwingUtilities.isLeftMouseButton(evt)){
        if(GUI.SweetAlert.SweetAlert.showSweetOption(new javax.swing.JFrame(), "Thêm mới", "Xác nhận thêm mới?",0)==1){
        add();
        }   
       } 
//        if (SwingUtilities.isLeftMouseButton(evt)){
//        this.removeAll();
//        this.setLayout(new BorderLayout());
//        this.add(new ChiTietKhuyenMaiJPanel());
//        this.validate();
//        this.repaint();
//        }
    }//GEN-LAST:event_pnlXacNhanMouseClicked

    private void pnlHuyBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHuyBoMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new ChiTietKhuyenMaiJPanel());
        this.validate();
        this.repaint();
        }
    }//GEN-LAST:event_pnlHuyBoMouseClicked

    private void txtGiaTienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaTienKeyReleased
        txtGiamToiDa.setText(txtGiaTien.getText());
    }//GEN-LAST:event_txtGiaTienKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateBatDau;
    private com.toedter.calendar.JDateChooser dateKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblHuyBo;
    private javax.swing.JLabel lblMaChiTietKhuyenMai;
    private javax.swing.JLabel lblThemSanPham;
    private javax.swing.JLabel lblTitleHinhThucKhuyenMai;
    private javax.swing.JLabel lblTitleMaChiTietKhuyenMai;
    private javax.swing.JLabel lblTitleMaChuongTrinhKhuyenMai;
    private javax.swing.JLabel lblTitleMaKhuyenMai;
    private javax.swing.JLabel lblTitleMaPhieuNhao;
    private javax.swing.JLabel lblTitleMaPhieuNhao4;
    private javax.swing.JLabel lblTitleMaPhieuNhao5;
    private javax.swing.JLabel lblTitleMaPhieuNhao7;
    private javax.swing.JLabel lblTitleSoLuong;
    private javax.swing.JLabel lblXacNhan;
    private GUI.Rounded pnlHuyBo;
    private javax.swing.JPanel pnlMaChuongTrinhKhuyenMai;
    private GUI.Rounded pnlThemSanPham;
    private GUI.Rounded pnlThongTin;
    private GUI.Rounded pnlXacNhan;
    private javax.swing.JRadioButton radGiaTien;
    private javax.swing.JRadioButton radPhanTram;
    private javax.swing.JScrollPane scpSanPham;
    private static javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtGiaTriToiThieu;
    private javax.swing.JTextField txtGiamToiDa;
    private javax.swing.JTextField txtMaKhuyenMai;
    private javax.swing.JTextField txtPhanTram;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
