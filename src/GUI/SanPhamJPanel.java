/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.SanPhamDTO;
import static DataThongKe.DataThongKeSanPham.SanPhamDaBan;
import static DataThongKe.DataThongKeSanPham.SanPhamDaBanThang;
import GUI.SanPham.ChiTietSanPhamJPanel;
import GUI.SanPham.TaoSanPhamJPanel;
import GUI.Sweet.SweetComboBox;
import GUI.Table.RenderTable;
import GUI.Table.setColorForCell;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import java.awt.BorderLayout;
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
import static quanlycuahangsach.Regexp.So;

/**
 *
 * @author admin
 */
public class SanPhamJPanel extends javax.swing.JPanel {
    public static SanPhamDTO currentSanPham;
    static DefaultTableModel listTableModel;
    public ArrayList<SanPhamDTO> arraySearch ;
    SweetComboBox cbxTrangThai,cbxTheLoai,cbxTacGia,cbxNhaXuatBan;
    Object[] columnNames ={"Mã sản phẩm","Tên sản phẩm","Đơn giá","Số lượng","Đã bán","Đã bán tháng qua"};
    /**
     * Creates new form SanPhamJPanel
     */
    public void setShadow(){
        pnlTblSanPham.setShadow(1);
    }    
    public void setComboBox(){
        String[] trangthai = {"Tất cả","Còn hàng","Hết hàng"};
        cbxTrangThai = new SweetComboBox("#CAE5F6","#181818",0,0,170,30,trangthai);
        pnlTrangThai.add(cbxTrangThai);        
        String[] theloai = quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getTenTheLoai();
        cbxTheLoai = new SweetComboBox("#CAE5F6","#181818",0,0,160,30,theloai);
        pnlTheLoai.add(cbxTheLoai);
        String[] tacgia = quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getTenTacGia();
        cbxTacGia = new SweetComboBox("#CAE5F6","#181818",0,0,160,30,tacgia);
        pnlTacGia.add(cbxTacGia);
        String[] nhaxuatban = quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getTenNhaXuatBan();
        cbxNhaXuatBan = new SweetComboBox("#CAE5F6","#181818",0,0,160,30,nhaxuatban);
        pnlNhaXuatBan.add(cbxNhaXuatBan);        
        cbxTrangThai.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                getAdvancedSearch();
            }
        });
        cbxTacGia.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                getAdvancedSearch();
            }
        });
        cbxTheLoai.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                getAdvancedSearch();
            }
        });
        cbxNhaXuatBan.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                getAdvancedSearch();
            }
        });
    } 
    public void ShowTable(JTable table,Object[] columnName){
        listTableModel = new DefaultTableModel(columnName,0);        
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAll();
    }    
    public void setColor(){
        
        for(int j = 0 ; j<tblSanPham.getRowCount();j++){
            for(int i=3;i<tblSanPham.getColumnCount()-3;i++){  
                tblSanPham.getColumnModel().getColumn(i).setCellRenderer(new setColorForCell(tblSanPham,j,i));

            }
            
        }
        
    }      
    public SanPhamJPanel() {
        initComponents();
        customTable(tblSanPham,scpSanPham);
        ShowTable(tblSanPham,columnNames);
        
        setJTableColumnsWidth(tblSanPham,948,17,27,14,12,12,16);
        setColor();
        setComboBox();
        setShadow();
        pnlTaoSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));        
    }
    public void fetchAll(){
        listTableModel.setRowCount(0);
        quanlycuahangsach.quanlycuahangsach.SachBUS.SachList.forEach(Sach ->{
            Vector row = new Vector();
            row.add(Sach.getMaSanPham());
            row.add(Sach.getTenSanPham());
            row.add(Sach.getGia());
            row.add(Sach.getSoLuong());
            row.add(changeCurrency(Integer.toString(SanPhamDaBan(Sach.getMaSanPham()))));
            row.add(changeCurrency(Integer.toString(SanPhamDaBanThang(Sach.getMaSanPham())[0])));
           
            listTableModel.addRow(row);
        });
        tblSanPham.setModel(listTableModel);
    }
    
    public void search(){
        listTableModel.setRowCount(0);
        arraySearch.forEach(Sach ->{
            Vector row = new Vector();
            row.add(Sach.getMaSanPham());
            row.add(Sach.getTenSanPham());
            row.add(Sach.getGia());
            row.add(Sach.getSoLuong());
            row.add(changeCurrency(Integer.toString(SanPhamDaBan(Sach.getMaSanPham()))));
            row.add(changeCurrency(Integer.toString(SanPhamDaBanThang(Sach.getMaSanPham())[0])));
           listTableModel.addRow(row);
        });
        tblSanPham.setModel(listTableModel);
    }
private void getAdvancedSearch(){
        HashMap<String, String> advancedSearch = new HashMap<>();
        advancedSearch.put("MaSanPham",txtTimKiemMaSanPham.getText());
        advancedSearch.put("TenSanPham",txtTimKiemTheoTenSanPham.getText());
        advancedSearch.put("TrangThai","");
        advancedSearch.put("MaTheLoai","");
        advancedSearch.put("MaNhaXuatBan","");
        advancedSearch.put("MaTacGia","");
        advancedSearch.put("MaTrangThai", "");
        advancedSearch.put("Tu","");
        advancedSearch.put("Den","");
        if(!cbxTrangThai.getSelectedItem().equals("Tất cả"))
            advancedSearch.replace("TrangThai", cbxTrangThai.getSelectedItem().toString());
        if(!cbxTheLoai.getSelectedItem().equals("Thể loại"))
            advancedSearch.replace("MaTheLoai", quanlycuahangsach.quanlycuahangsach.TheLoaiBUS.getMaByName(cbxTheLoai.getSelectedItem().toString()));
        if(!cbxTacGia.getSelectedItem().equals("Tác giả"))
            advancedSearch.replace("MaTacGia", quanlycuahangsach.quanlycuahangsach.TacGiaBUS.getMaByName(cbxTacGia.getSelectedItem().toString()));
        if(!cbxNhaXuatBan.getSelectedItem().equals("Nhà xuất bản"))
            advancedSearch.replace("MaNhaXuatBan", quanlycuahangsach.quanlycuahangsach.NhaXuatBanBUS.getMaByName(cbxNhaXuatBan.getSelectedItem().toString()));
        if(!txtTimKiemGiaTu.getText().equals(""))
        if(!So(txtTimKiemGiaTu.getText())){
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Không hợp lệ", 1);
            txtTimKiemGiaTu.setText("");
        return;
        }
        else advancedSearch.replace("Tu", txtTimKiemGiaTu.getText());
        if(!txtTimKiemGiaDen.getText().equals(""))
        if(!So(txtTimKiemGiaDen.getText())){
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Không hợp lệ", 1);
            txtTimKiemGiaDen.setText("");
        return;
        }
        else advancedSearch.replace("Den", txtTimKiemGiaDen.getText());
        arraySearch = quanlycuahangsach.quanlycuahangsach.SachBUS.advancedSearch(advancedSearch);
        search();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTblSanPham = new GUI.Rounded();
        scpSanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        pnlTaoSanPham = new GUI.Rounded();
        lblTaoSanPham = new javax.swing.JLabel();
        lblTimKiemTheoTenSanPham = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlTimKiemTheoKhoangGia = new GUI.Rounded();
        txtTimKiemGiaTu = new javax.swing.JTextField();
        pnlTimKiemMaSanPham = new GUI.Rounded();
        txtTimKiemMaSanPham = new javax.swing.JTextField();
        pnlTheLoai = new javax.swing.JPanel();
        pnlTacGia = new javax.swing.JPanel();
        pnlNhaXuatBan = new javax.swing.JPanel();
        lblTrangThai = new javax.swing.JLabel();
        pnlTrangThai = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pnlTimKiemTheoTenSanPham = new GUI.Rounded();
        txtTimKiemTheoTenSanPham = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pnlTimKiemGiaDen = new GUI.Rounded();
        txtTimKiemGiaDen = new javax.swing.JTextField();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTblSanPham.setBackground(new java.awt.Color(238, 243, 247));
        pnlTblSanPham.setForeground(new java.awt.Color(238, 243, 247));

        scpSanPham.setForeground(new java.awt.Color(255, 255, 255));

        tblSanPham.setAutoCreateRowSorter(true);
        tblSanPham.setForeground(new java.awt.Color(24, 24, 24));
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

        javax.swing.GroupLayout pnlTblSanPhamLayout = new javax.swing.GroupLayout(pnlTblSanPham);
        pnlTblSanPham.setLayout(pnlTblSanPhamLayout);
        pnlTblSanPhamLayout.setHorizontalGroup(
            pnlTblSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTblSanPhamLayout.setVerticalGroup(
            pnlTblSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        add(pnlTblSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 960, 430));

        pnlTaoSanPham.setBackground(new java.awt.Color(238, 243, 247));
        pnlTaoSanPham.setForeground(new java.awt.Color(0, 146, 242));
        pnlTaoSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTaoSanPhamMouseClicked(evt);
            }
        });

        lblTaoSanPham.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTaoSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblTaoSanPham.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTaoSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        lblTaoSanPham.setText("Tạo sản phẩm");

        javax.swing.GroupLayout pnlTaoSanPhamLayout = new javax.swing.GroupLayout(pnlTaoSanPham);
        pnlTaoSanPham.setLayout(pnlTaoSanPhamLayout);
        pnlTaoSanPhamLayout.setHorizontalGroup(
            pnlTaoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaoSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaoSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTaoSanPhamLayout.setVerticalGroup(
            pnlTaoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTaoSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaoSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTaoSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 120, -1, -1));

        lblTimKiemTheoTenSanPham.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        lblTimKiemTheoTenSanPham.setForeground(new java.awt.Color(0, 52, 102));
        lblTimKiemTheoTenSanPham.setText("Tên sản phẩm:");
        add(lblTimKiemTheoTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, 30));

        jLabel2.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Đến:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, -1, 30));

        pnlTimKiemTheoKhoangGia.setBackground(new java.awt.Color(238, 243, 247));
        pnlTimKiemTheoKhoangGia.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemTheoKhoangGia.setFocusable(false);

        txtTimKiemGiaTu.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemGiaTu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemGiaTu.setForeground(new java.awt.Color(24, 24, 24));
        txtTimKiemGiaTu.setBorder(null);
        txtTimKiemGiaTu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemGiaTuKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemTheoKhoangGiaLayout = new javax.swing.GroupLayout(pnlTimKiemTheoKhoangGia);
        pnlTimKiemTheoKhoangGia.setLayout(pnlTimKiemTheoKhoangGiaLayout);
        pnlTimKiemTheoKhoangGiaLayout.setHorizontalGroup(
            pnlTimKiemTheoKhoangGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemTheoKhoangGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemGiaTu, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemTheoKhoangGiaLayout.setVerticalGroup(
            pnlTimKiemTheoKhoangGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemTheoKhoangGiaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemGiaTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemTheoKhoangGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 140, -1));

        pnlTimKiemMaSanPham.setBackground(new java.awt.Color(238, 243, 247));
        pnlTimKiemMaSanPham.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemMaSanPham.setFocusable(false);

        txtTimKiemMaSanPham.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemMaSanPham.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemMaSanPham.setForeground(new java.awt.Color(24, 24, 24));
        txtTimKiemMaSanPham.setBorder(null);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemMaSanPhamLayout.setVerticalGroup(
            pnlTimKiemMaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemMaSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        pnlTheLoai.setBackground(new java.awt.Color(202, 229, 246));
        pnlTheLoai.setPreferredSize(new java.awt.Dimension(170, 30));

        javax.swing.GroupLayout pnlTheLoaiLayout = new javax.swing.GroupLayout(pnlTheLoai);
        pnlTheLoai.setLayout(pnlTheLoaiLayout);
        pnlTheLoaiLayout.setHorizontalGroup(
            pnlTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        pnlTheLoaiLayout.setVerticalGroup(
            pnlTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 160, 30));

        pnlTacGia.setBackground(new java.awt.Color(202, 229, 246));
        pnlTacGia.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlTacGiaLayout = new javax.swing.GroupLayout(pnlTacGia);
        pnlTacGia.setLayout(pnlTacGiaLayout);
        pnlTacGiaLayout.setHorizontalGroup(
            pnlTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        pnlTacGiaLayout.setVerticalGroup(
            pnlTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 160, -1));

        pnlNhaXuatBan.setBackground(new java.awt.Color(202, 229, 246));
        pnlNhaXuatBan.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlNhaXuatBanLayout = new javax.swing.GroupLayout(pnlNhaXuatBan);
        pnlNhaXuatBan.setLayout(pnlNhaXuatBanLayout);
        pnlNhaXuatBanLayout.setHorizontalGroup(
            pnlNhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        pnlNhaXuatBanLayout.setVerticalGroup(
            pnlNhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, 160, -1));

        lblTrangThai.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(0, 52, 102));
        lblTrangThai.setText("Trạng thái:");
        add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, -1, 30));

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

        add(pnlTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 170, 30));

        jLabel3.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 52, 102));
        jLabel3.setText("Mã sản phẩm:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, 30));

        pnlTimKiemTheoTenSanPham.setBackground(new java.awt.Color(238, 243, 247));
        pnlTimKiemTheoTenSanPham.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemTheoTenSanPham.setFocusable(false);

        txtTimKiemTheoTenSanPham.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemTheoTenSanPham.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemTheoTenSanPham.setForeground(new java.awt.Color(24, 24, 24));
        txtTimKiemTheoTenSanPham.setBorder(null);
        txtTimKiemTheoTenSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemTheoTenSanPhamKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemTheoTenSanPhamLayout = new javax.swing.GroupLayout(pnlTimKiemTheoTenSanPham);
        pnlTimKiemTheoTenSanPham.setLayout(pnlTimKiemTheoTenSanPhamLayout);
        pnlTimKiemTheoTenSanPhamLayout.setHorizontalGroup(
            pnlTimKiemTheoTenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemTheoTenSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemTheoTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemTheoTenSanPhamLayout.setVerticalGroup(
            pnlTimKiemTheoTenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemTheoTenSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemTheoTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemTheoTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 52, 102));
        jLabel4.setText("Tìm theo khoảng giá:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, -1, 30));

        jLabel5.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Từ:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, 30));

        pnlTimKiemGiaDen.setBackground(new java.awt.Color(238, 243, 247));
        pnlTimKiemGiaDen.setForeground(new java.awt.Color(202, 229, 246));
        pnlTimKiemGiaDen.setFocusable(false);

        txtTimKiemGiaDen.setBackground(new java.awt.Color(202, 229, 246));
        txtTimKiemGiaDen.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiemGiaDen.setForeground(new java.awt.Color(24, 24, 24));
        txtTimKiemGiaDen.setBorder(null);
        txtTimKiemGiaDen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemGiaDenKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemGiaDenLayout = new javax.swing.GroupLayout(pnlTimKiemGiaDen);
        pnlTimKiemGiaDen.setLayout(pnlTimKiemGiaDenLayout);
        pnlTimKiemGiaDenLayout.setHorizontalGroup(
            pnlTimKiemGiaDenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemGiaDenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemGiaDen, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemGiaDenLayout.setVerticalGroup(
            pnlTimKiemGiaDenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemGiaDenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemGiaDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlTimKiemGiaDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
       if (SwingUtilities.isLeftMouseButton(evt)){ 
        int column = tblSanPham.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblSanPham.getRowHeight();

        if(row < tblSanPham.getRowCount() && row >= 0 && column < tblSanPham.getColumnCount() && column >= 0){

            for(int i=0;i<tblSanPham.getColumnCount();i++){
                currentSanPham = quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(tblSanPham.getModel().getValueAt(row, 0).toString());
                this.removeAll();
                this.setLayout(new BorderLayout());
                this.add(new ChiTietSanPhamJPanel());
                this.validate();
                this.repaint();
            }

        }
       }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void pnlTaoSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTaoSanPhamMouseClicked
    if (SwingUtilities.isLeftMouseButton(evt)){
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new TaoSanPhamJPanel());
        this.validate();
        this.repaint();
    }
    }//GEN-LAST:event_pnlTaoSanPhamMouseClicked

    private void txtTimKiemGiaTuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemGiaTuKeyReleased
       getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemGiaTuKeyReleased

    private void txtTimKiemGiaDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemGiaDenKeyReleased
       getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemGiaDenKeyReleased

    private void txtTimKiemTheoTenSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTheoTenSanPhamKeyReleased
       getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemTheoTenSanPhamKeyReleased

    private void txtTimKiemMaSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamKeyReleased
        getAdvancedSearch();
    }//GEN-LAST:event_txtTimKiemMaSanPhamKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblTaoSanPham;
    private javax.swing.JLabel lblTimKiemTheoTenSanPham;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnlNhaXuatBan;
    private javax.swing.JPanel pnlTacGia;
    private GUI.Rounded pnlTaoSanPham;
    private GUI.Rounded pnlTblSanPham;
    private javax.swing.JPanel pnlTheLoai;
    private GUI.Rounded pnlTimKiemGiaDen;
    private GUI.Rounded pnlTimKiemMaSanPham;
    private GUI.Rounded pnlTimKiemTheoKhoangGia;
    private GUI.Rounded pnlTimKiemTheoTenSanPham;
    private javax.swing.JPanel pnlTrangThai;
    private javax.swing.JScrollPane scpSanPham;
    private static javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTimKiemGiaDen;
    private javax.swing.JTextField txtTimKiemGiaTu;
    private javax.swing.JTextField txtTimKiemMaSanPham;
    private javax.swing.JTextField txtTimKiemTheoTenSanPham;
    // End of variables declaration//GEN-END:variables
}
