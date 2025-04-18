/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.ChiTietDonHangDTO;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.DonHangDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import GUI.DonHang.ChiTietDonHangJPanel;
import GUI.DonHang.TaoDonHangJPanel;
import GUI.Sweet.SweetComboBox;
import GUI.Table.RenderTable;
import GUI.Table.setIconForCell;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import quanlycuahangsach.DateTime;
import static quanlycuahangsach.DateTime.TimestampToDateString;

/**
 *
 * @author admin
 */
public class DonHangJPanel extends javax.swing.JPanel {
    public static DefaultTableModel listTableModel;
    Object[] columnNames ={"Mã đơn hàng","Họ tên","Số điện thoại","Ngày lập đơn","Nhân viên lập đơn","Trạng thái"};
    SweetComboBox cbxTrangThai;
    ArrayList<DonHangDTO> arraySearch;
    public static DonHangDTO currentDonHang;
    public static ArrayList<ChiTietDonHangDTO> currentChiTietDonHangList;
    public static KhachHangDTO currentKhachHang;
    public static ArrayList<SanPhamDTO> currentSanPhamList;
    public static NhanVienDTO currentNhanVien;
    public static ChiTietKhuyenMaiDTO currentChiTietKhuyenMai;
    
    /**
     * Creates new form DonHangJPanel
     */
    public void setShadow(){
        pnlTblDonHangChuaXacNhan.setShadow(1);
    }
    
    public void ShowTable(JTable table,Object[] columnName){
        table.setDefaultRenderer(Object.class, new RenderTable());
        listTableModel = new DefaultTableModel(columnNames,0);
        fetchAll();
    }  
    
    public  void fetchAll(){
    listTableModel.setRowCount(0);
        quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.forEach(DonHang -> {
            Vector row = new Vector();
            KhachHangDTO KhachHang = quanlycuahangsach.quanlycuahangsach.KhachHangBUS.getByMaKhachHang(DonHang.getMaKhachHang());
            row.add(DonHang.getMaDonHang());
            row.add(KhachHang.getHoTen());
            row.add(KhachHang.getSoDienThoai());
            row.add(TimestampToDateString(DonHang.getNgayXuat(),1));
            row.add(DonHang.getMaNhanVien());
            row.add(getTrangThai(DonHang.getTrangThai()));
            listTableModel.addRow(row);
        });
        tblDonHang.setModel(listTableModel);
    }
    public  String getTrangThai(String MaTrangThai){
        return MaTrangThai.equals("1") ? "Thành Công" : "Hủy";
    }
    public String getGiaTriTrangThai(String currentTrangThai){
        return currentTrangThai.equals("Hủy") ? "0" : "1";
    }
    public void setComboBox(){
        String[] trangthai = {"Tất cả","Hủy","Thành công"};
        cbxTrangThai = new SweetComboBox("#CAE5F6","#181818",0,0,170,30,trangthai);
        pnlTrangThai.add(cbxTrangThai);   
        cbxTrangThai.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                getAdvancedSearch();
            }
        });
         
    }
    private void getAdvancedSearch(){
        HashMap<String,String> advancedSearch = new HashMap<>();
        advancedSearch.put("HoTenSDT",txtTimKiemHoTenSDT.getText());
        advancedSearch.put("MaDonHang",txtTimKiemMaDonHang.getText());
        advancedSearch.put("TrangThai","");  
        advancedSearch.put("MaNhanVien",txtTimKiemMaNhanVien.getText());
        if(!cbxTrangThai.getSelectedItem().equals("Tất cả"))
            advancedSearch.replace("TrangThai",getGiaTriTrangThai(cbxTrangThai.getSelectedItem().toString()));
        arraySearch = quanlycuahangsach.quanlycuahangsach.DonHangBUS.advancedSearch(advancedSearch);
        search();
    }

    public  void search(){
        listTableModel.setRowCount(0);
        arraySearch.forEach( DonHang ->{
            KhachHangDTO KhachHang = quanlycuahangsach.quanlycuahangsach.KhachHangBUS.getByMaKhachHang(DonHang.getMaKhachHang());
            Vector row = new Vector();
            row.add(DonHang.getMaDonHang());
            row.add(KhachHang.getHoTen());
            row.add(KhachHang.getSoDienThoai());
            row.add(TimestampToDateString(DonHang.getNgayXuat(),1));
            row.add(DonHang.getMaNhanVien());
            row.add(getTrangThai(DonHang.getTrangThai()));
            listTableModel.addRow(row);
        });
        tblDonHang.setModel(listTableModel);
    }
    
    public DonHangJPanel() {
        initComponents();
        customTable(tblDonHang,scpDonHang);
        ShowTable(tblDonHang,columnNames);
        setJTableColumnsWidth(tblDonHang,tblDonHang.getWidth(),15,25,15,10,10,15,10);

        
        setComboBox();
        setShadow();
        pnlTrangThai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlTaoDonHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTrangThai = new javax.swing.JPanel();
        lblTrangThai = new javax.swing.JLabel();
        pnlTimKiemHoTenSDT = new GUI.Rounded();
        txtTimKiemHoTenSDT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTimKiemMaDonHang = new GUI.Rounded();
        txtTimKiemMaDonHang = new javax.swing.JTextField();
        pnlTblDonHangChuaXacNhan = new GUI.Rounded();
        jLabel2 = new javax.swing.JLabel();
        scpDonHang = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }
        };
        jLabel4 = new javax.swing.JLabel();
        pnlTimKiemMaNhanVien = new GUI.Rounded();
        txtTimKiemMaNhanVien = new javax.swing.JTextField();
        pnlTaoDonHang = new GUI.Rounded();
        lblTaoPhieuNhap = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTrangThai.setBackground(new java.awt.Color(202, 229, 246));

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

        add(pnlTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 170, 30));

        lblTrangThai.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(0, 52, 102));
        lblTrangThai.setText("Trạng thái:");
        add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, 30));

        pnlTimKiemHoTenSDT.setBackground(new java.awt.Color(238, 243, 247));
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
                .addGap(16, 16, 16)
                .addComponent(txtTimKiemHoTenSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlTimKiemHoTenSDTLayout.setVerticalGroup(
            pnlTimKiemHoTenSDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemHoTenSDTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemHoTenSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTimKiemHoTenSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 52, 102));
        jLabel1.setText("Mã nhân viên:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 30));

        jLabel3.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 52, 102));
        jLabel3.setText("Họ tên/Số điện thoại:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 30));

        pnlTimKiemMaDonHang.setBackground(new java.awt.Color(238, 243, 247));
        pnlTimKiemMaDonHang.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemMaDonHang.setFocusable(false);

        txtTimKiemMaDonHang.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemMaDonHang.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemMaDonHang.setBorder(null);
        txtTimKiemMaDonHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemMaDonHangKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemMaDonHangLayout = new javax.swing.GroupLayout(pnlTimKiemMaDonHang);
        pnlTimKiemMaDonHang.setLayout(pnlTimKiemMaDonHangLayout);
        pnlTimKiemMaDonHangLayout.setHorizontalGroup(
            pnlTimKiemMaDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemMaDonHangLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(txtTimKiemMaDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlTimKiemMaDonHangLayout.setVerticalGroup(
            pnlTimKiemMaDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaDonHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemMaDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, -1));

        pnlTblDonHangChuaXacNhan.setBackground(new java.awt.Color(238, 243, 247));
        pnlTblDonHangChuaXacNhan.setForeground(new java.awt.Color(238, 243, 247));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 52, 102));
        jLabel2.setText("Đơn hàng");

        tblDonHang.setAutoCreateRowSorter(true);
        tblDonHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblDonHang.setForeground(new java.awt.Color(24, 24, 24));
        tblDonHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblDonHang.setFocusable(false);
        tblDonHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblDonHang.setRowHeight(25);
        tblDonHang.setSelectionBackground(new java.awt.Color(152, 210, 248));
        tblDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonHangMouseClicked(evt);
            }
        });
        scpDonHang.setViewportView(tblDonHang);

        javax.swing.GroupLayout pnlTblDonHangChuaXacNhanLayout = new javax.swing.GroupLayout(pnlTblDonHangChuaXacNhan);
        pnlTblDonHangChuaXacNhan.setLayout(pnlTblDonHangChuaXacNhanLayout);
        pnlTblDonHangChuaXacNhanLayout.setHorizontalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                        .addComponent(scpDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlTblDonHangChuaXacNhanLayout.setVerticalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTblDonHangChuaXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 213, 973, 430));

        jLabel4.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 52, 102));
        jLabel4.setText("Mã đơn hàng:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        pnlTimKiemMaNhanVien.setBackground(new java.awt.Color(238, 243, 247));
        pnlTimKiemMaNhanVien.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemMaNhanVien.setFocusable(false);

        txtTimKiemMaNhanVien.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemMaNhanVien.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemMaNhanVien.setBorder(null);
        txtTimKiemMaNhanVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemMaNhanVienKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemMaNhanVienLayout = new javax.swing.GroupLayout(pnlTimKiemMaNhanVien);
        pnlTimKiemMaNhanVien.setLayout(pnlTimKiemMaNhanVienLayout);
        pnlTimKiemMaNhanVienLayout.setHorizontalGroup(
            pnlTimKiemMaNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemMaNhanVienLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(txtTimKiemMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlTimKiemMaNhanVienLayout.setVerticalGroup(
            pnlTimKiemMaNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaNhanVienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

        pnlTaoDonHang.setBackground(new java.awt.Color(238, 243, 247));
        pnlTaoDonHang.setForeground(new java.awt.Color(0, 146, 242));
        pnlTaoDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTaoDonHangMouseClicked(evt);
            }
        });

        lblTaoPhieuNhap.setBackground(new java.awt.Color(0, 146, 242));
        lblTaoPhieuNhap.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTaoPhieuNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblTaoPhieuNhap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTaoPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        lblTaoPhieuNhap.setText("Tạo đơn hàng");

        javax.swing.GroupLayout pnlTaoDonHangLayout = new javax.swing.GroupLayout(pnlTaoDonHang);
        pnlTaoDonHang.setLayout(pnlTaoDonHangLayout);
        pnlTaoDonHangLayout.setHorizontalGroup(
            pnlTaoDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaoDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaoPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTaoDonHangLayout.setVerticalGroup(
            pnlTaoDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTaoPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(pnlTaoDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 170, 160, 32));
    }// </editor-fold>//GEN-END:initComponents

    private void tblDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangMouseClicked
        int column = tblDonHang.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblDonHang.getRowHeight();
        if(row < tblDonHang.getRowCount() && row >= 0 && column < tblDonHang.getColumnCount() && column >= 0){
                    currentDonHang = quanlycuahangsach.quanlycuahangsach.DonHangBUS.getByMaDonHang(tblDonHang.getValueAt(row,0).toString());
                    currentKhachHang = quanlycuahangsach.quanlycuahangsach.KhachHangBUS.getByMaKhachHang(currentDonHang.getMaKhachHang());
                    currentChiTietDonHangList = quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.getListChiTietByMaDonHang(currentDonHang.getMaDonHang());
                    currentChiTietKhuyenMai = quanlycuahangsach.quanlycuahangsach.ChiTietKhuyenMaiBUS.getByMaCode(currentDonHang.getMaCode());
                    currentSanPhamList = new ArrayList<>();
                    currentChiTietDonHangList.forEach(ChiTietDonHang->{
                        currentSanPhamList.add(quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(ChiTietDonHang.getMaSanPham()));
                    }); 
                    currentNhanVien = quanlycuahangsach.quanlycuahangsach.NhanVienBUS.getByMaNhanVien(currentDonHang.getMaNhanVien());
                    this.removeAll();
                    this.setLayout(new BorderLayout());
                    this.add(new ChiTietDonHangJPanel());
                    this.validate();
                    this.repaint();
            
        }
    }//GEN-LAST:event_tblDonHangMouseClicked

    private void pnlTaoDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTaoDonHangMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(new TaoDonHangJPanel());
            this.validate();
            this.repaint();
        }      
    }//GEN-LAST:event_pnlTaoDonHangMouseClicked

    private void pnlNhapExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNhapExcelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlNhapExcelMouseClicked

    private void txtTimKiemHoTenSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHoTenSDTKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemHoTenSDTKeyReleased

    private void txtTimKiemMaDonHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaDonHangKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaDonHangKeyReleased

    private void txtTimKiemMaNhanVienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaNhanVienKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaNhanVienKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblTaoPhieuNhap;
    private javax.swing.JLabel lblTrangThai;
    private GUI.Rounded pnlTaoDonHang;
    private GUI.Rounded pnlTblDonHangChuaXacNhan;
    private GUI.Rounded pnlTimKiemHoTenSDT;
    private GUI.Rounded pnlTimKiemMaDonHang;
    private GUI.Rounded pnlTimKiemMaNhanVien;
    private javax.swing.JPanel pnlTrangThai;
    private javax.swing.JScrollPane scpDonHang;
    private static javax.swing.JTable tblDonHang;
    private javax.swing.JTextField txtTimKiemHoTenSDT;
    private javax.swing.JTextField txtTimKiemMaDonHang;
    private javax.swing.JTextField txtTimKiemMaNhanVien;
    // End of variables declaration//GEN-END:variables
}
