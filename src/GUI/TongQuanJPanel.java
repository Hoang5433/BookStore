/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static DataThongKe.DataDoanhThu.DoanhThuNgayHomNay;
import static DataThongKe.DataDoanhThu.processBar;
import static DataThongKe.DataThongKeDonHang.DonHangNgayHomNay;
import static DataThongKe.DataThongKeDonHang.processBarDonHang;
//import static DataThongKe.DataThongKeNhanVien.ThongKeSoLuongNhanVienConLam;
import GUI.Table.CustomScrollBarUI;
import GUI.Table.RenderTable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import static quanlycuahangsach.Currency.changeCurrency;
import static quanlycuahangsach.DateTime.TimestampToDateString;

/**
 *
 * @author admin
 */
public class TongQuanJPanel extends javax.swing.JPanel {
    Object[] columnNames ={"Mã đơn hàng","Tên","Số điện thoại","Tổng tiền","Ngày lập đơn","Trạng thái"};
    static DefaultTableModel listTableModel;
   
    public void setShadow(){
        pnlNguoiDung1.setShadow(1);
        pnlDonHang.setShadow(1);
        pnlTblDonHangChuaXacNhan.setShadow(1);
        pnlSanPham.setShadow(1);
    }
    public static void customTable(JTable table,JScrollPane scp){
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
    public void fetchAll(){
        listTableModel.setRowCount(0);
        BUS.DonHangBUS.getTOP10DonHang().forEach(DonHang ->{
            if(Integer.parseInt(DonHang.getNgayXuat().toString())>0){
                Vector row = new Vector();
                row.add(DonHang.getMaDonHang());
                row.add(quanlycuahangsach.quanlycuahangsach.KhachHangBUS.getTenKhachHangByMaKhachHang(DonHang.getMaKhachHang()));
                row.add(quanlycuahangsach.quanlycuahangsach.KhachHangBUS.getByMaKhachHang(DonHang.getMaKhachHang()).getSoDienThoai());
                row.add(changeCurrency(DonHang.getTongTien())+"đ");
                row.add(TimestampToDateString(DonHang.getNgayXuat(),0));
                
                row.add(DonHang.getTrangThai().equals("1") ? "Thành công" : "Hủy");
                listTableModel.addRow(row);
            }
            tblDonHang.setModel(listTableModel);
            
        
    });
    }
//    public void setData(){
//         lblTongNhanVien.setText(changeCurrency(Integer.toString(ThongKeSoLuongNhanVienConLam())));
//         lblTongDoanhThu.setText(changeCurrency(Integer.toString(DoanhThuNgayHomNay())));
//         lblTongDonHang.setText(changeCurrency(Integer.toString(DonHangNgayHomNay())));
//         
//    }
    public void ShowTable(JTable table,Object[] columnName){
        listTableModel = new DefaultTableModel(columnNames,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
       
        
        fetchAll();

       
        
    }
    
    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
        double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }
 
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
    }
}    
    /**
     * Creates new form TongQuanJPanel
     */
    public TongQuanJPanel() {
        initComponents();
        float[] process = processBar();
        float processDH = processBarDonHang();
        customTable(tblDonHang,scpDonHang);
        ShowTable(tblDonHang,columnNames);
                new Thread(new Runnable(){
            public void run(){
                try {
                    for(float i=0;i<=100;i+=0.5f){
                        if(i<=process[0]){
                            pgbDoanhThu.UpdateProgress(i);
                            pgbDoanhThu.repaint();
                        }
                        if(i<=100){
                            pgbNhanVien.UpdateProgress(i);
                            pgbNhanVien.repaint();
                        }
                        if(i<=processDH){
                            pgbDonHang.UpdateProgress(i);
                            pgbDonHang.repaint();
                        }
                        Thread.sleep(50);
                } 
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(TongQuanJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    
    }
        ).start();    
        setShadow();
//        setData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNguoiDung1 = new GUI.Rounded();
        lblTongDoanhThu = new javax.swing.JLabel();
        lblNguoiDung1 = new javax.swing.JLabel();
        pgbDoanhThu = new GUI.ProgressBarJPanel("#ffffff","#81b3a3","#c0d9d0");
        pnlSanPham = new GUI.Rounded();
        lblTongNhanVien = new javax.swing.JLabel();
        lblNguoiDung2 = new javax.swing.JLabel();
        pgbNhanVien = new GUI.ProgressBarJPanel("#ffffff","#f36b7f","#f4c7d1");
        pnlDonHang = new GUI.Rounded();
        lblTongDonHang = new javax.swing.JLabel();
        lblNguoiDung3 = new javax.swing.JLabel();
        pgbDonHang = new GUI.ProgressBarJPanel("#ffffff","#f8cf61","#f8ebc9");
        pnlTblDonHangChuaXacNhan = new GUI.Rounded();
        jLabel2 = new javax.swing.JLabel();
        scpDonHang = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }
        };

        setBackground(new java.awt.Color(238, 243, 247));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 1, 0, 0));
        setPreferredSize(new java.awt.Dimension(1040, 690));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlNguoiDung1.setBackground(new java.awt.Color(238, 243, 247));
        pnlNguoiDung1.setForeground(new java.awt.Color(238, 243, 247));
        pnlNguoiDung1.setPreferredSize(new java.awt.Dimension(279, 120));
        pnlNguoiDung1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTongDoanhThu.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 22)); // NOI18N
        lblTongDoanhThu.setForeground(new java.awt.Color(0, 52, 102));
        lblTongDoanhThu.setText("5.000.000.000");
        pnlNguoiDung1.add(lblTongDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 66, -1, 27));

        lblNguoiDung1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 18)); // NOI18N
        lblNguoiDung1.setForeground(new java.awt.Color(24, 24, 24));
        lblNguoiDung1.setText("Doanh thu");
        pnlNguoiDung1.add(lblNguoiDung1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 25, -1, -1));

        pgbDoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        pgbDoanhThu.setForeground(new java.awt.Color(51, 255, 102));

        javax.swing.GroupLayout pgbDoanhThuLayout = new javax.swing.GroupLayout(pgbDoanhThu);
        pgbDoanhThu.setLayout(pgbDoanhThuLayout);
        pgbDoanhThuLayout.setHorizontalGroup(
            pgbDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        pgbDoanhThuLayout.setVerticalGroup(
            pgbDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlNguoiDung1.add(pgbDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 6, -1, 108));

        add(pnlNguoiDung1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 21, -1, -1));

        pnlSanPham.setBackground(new java.awt.Color(238, 243, 247));
        pnlSanPham.setForeground(new java.awt.Color(238, 243, 247));
        pnlSanPham.setPreferredSize(new java.awt.Dimension(279, 120));
        pnlSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTongNhanVien.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 22)); // NOI18N
        lblTongNhanVien.setForeground(new java.awt.Color(0, 52, 102));
        pnlSanPham.add(lblTongNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 66, -1, 27));

        lblNguoiDung2.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 18)); // NOI18N
        lblNguoiDung2.setForeground(new java.awt.Color(24, 24, 24));
        lblNguoiDung2.setText("Nhân viên");
        pnlSanPham.add(lblNguoiDung2, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 25, -1, -1));

        pgbNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        pgbNhanVien.setForeground(new java.awt.Color(51, 255, 102));

        javax.swing.GroupLayout pgbNhanVienLayout = new javax.swing.GroupLayout(pgbNhanVien);
        pgbNhanVien.setLayout(pgbNhanVienLayout);
        pgbNhanVienLayout.setHorizontalGroup(
            pgbNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        pgbNhanVienLayout.setVerticalGroup(
            pgbNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlSanPham.add(pgbNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 6, -1, 108));

        add(pnlSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 21, -1, -1));

        pnlDonHang.setBackground(new java.awt.Color(238, 243, 247));
        pnlDonHang.setForeground(new java.awt.Color(238, 243, 247));
        pnlDonHang.setPreferredSize(new java.awt.Dimension(279, 120));
        pnlDonHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTongDonHang.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 22)); // NOI18N
        lblTongDonHang.setForeground(new java.awt.Color(0, 52, 102));
        lblTongDonHang.setText("5.000.000.000");
        pnlDonHang.add(lblTongDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 66, -1, 27));

        lblNguoiDung3.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 18)); // NOI18N
        lblNguoiDung3.setForeground(new java.awt.Color(24, 24, 24));
        lblNguoiDung3.setText("Đơn hàng");
        pnlDonHang.add(lblNguoiDung3, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 25, -1, -1));

        pgbDonHang.setBackground(new java.awt.Color(255, 255, 255));
        pgbDonHang.setForeground(new java.awt.Color(51, 255, 102));

        javax.swing.GroupLayout pgbDonHangLayout = new javax.swing.GroupLayout(pgbDonHang);
        pgbDonHang.setLayout(pgbDonHangLayout);
        pgbDonHangLayout.setHorizontalGroup(
            pgbDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        pgbDonHangLayout.setVerticalGroup(
            pgbDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlDonHang.add(pgbDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 6, -1, 108));

        add(pnlDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(723, 21, -1, -1));

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
        tblDonHang.setShowGrid(false);
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
                    .addComponent(scpDonHang)
                    .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addGap(0, 857, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlTblDonHangChuaXacNhanLayout.setVerticalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        add(pnlTblDonHangChuaXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 182, 973, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void tblDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangMouseClicked
        int column = tblDonHang.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblDonHang.getRowHeight();
        if(row < tblDonHang.getRowCount() && row >= 0 && column < tblDonHang.getColumnCount() && column >= 0){
            Object value = tblDonHang.getValueAt(row, column);
                for(int i=0;i<tblDonHang.getColumnCount();i++){
                    System.out.print(tblDonHang.getValueAt(row,i));
             
            }
        }
    }//GEN-LAST:event_tblDonHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblNguoiDung1;
    private javax.swing.JLabel lblNguoiDung2;
    private javax.swing.JLabel lblNguoiDung3;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JLabel lblTongDonHang;
    private javax.swing.JLabel lblTongNhanVien;
    private GUI.ProgressBarJPanel pgbDoanhThu;
    private GUI.ProgressBarJPanel pgbDonHang;
    private GUI.ProgressBarJPanel pgbNhanVien;
    private GUI.Rounded pnlDonHang;
    private GUI.Rounded pnlNguoiDung1;
    private GUI.Rounded pnlSanPham;
    private GUI.Rounded pnlTblDonHangChuaXacNhan;
    private javax.swing.JScrollPane scpDonHang;
    private static javax.swing.JTable tblDonHang;
    // End of variables declaration//GEN-END:variables
}
