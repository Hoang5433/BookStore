/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ThongKe;

import DataThongKe.DataThongKeDonHang;
import static DataThongKe.DataThongKeDonHang.ThongKeDonHangQuyExcel;
import static DataThongKe.DataThongKeDonHang.ThongKeDonHangThangExcel;
import static DataThongKe.DataThongKeKhuyenMai.ThongKeKhuyenMaiThangExcel;
import GUI.*;
import GUI.DonHang.ChiTietDonHangJPanel;
import GUI.DonHang.TaoDonHangJPanel;
import GUI.Sweet.SweetComboBox;
import GUI.Sweet.SweetFileChooser;
import GUI.SweetAlert.SweetAlert;
import GUI.Table.RenderTable;
import static GUI.TongQuanJPanel.customTable;
import static GUI.TongQuanJPanel.setJTableColumnsWidth;
import IO.Excel.ThongKeDonHangQuyExcel;
import IO.Excel.ThongKeDonHangThangExcel;
import IO.Excel.ThongKeKhuyenMaiThangExcel;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class ChiTietThongKeDonHang extends javax.swing.JPanel {

    Object[] columnNames ={"Tháng","Tổng đơn hàng","Đơn thành công","Đơn hủy"};
    Object[] columnNamesBot = {"Quý","Tổng đơn hàng","Đơn thành công","Đơn hủy"};
    static DefaultTableModel listTableModelThang;
    static DefaultTableModel listTableModelQuy;
    public static SweetComboBox cbxNam,cbxThangQuy;    
    /**
     * Creates new form DonHangJPanel
     */
    
    public void setShadow(){
        pnlTblDonHangChuaXacNhan.setShadow(1);
        pnlTblDonHangChuaXacNhan1.setShadow(1);
    }
    public void ShowTable(JTable table,Object[] columnName){
        listTableModelThang = new DefaultTableModel(columnName,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAllThang();
        tblDonHang.setModel(listTableModelThang);
    }  
    public void ShowTableBottom(JTable table,Object[] columnName){
        listTableModelQuy = new DefaultTableModel(columnName,0);
        table.setDefaultRenderer(Object.class, new RenderTable());
        fetchAllQuy();
        tblDonHangQuy.setModel(listTableModelThang);
    }      
    public void setComboBox(){
        String[] nam = {"2020","2019","2018"};
        cbxNam = new SweetComboBox("#ffffff","#181818",0,0,130,30,nam);
        pnlNam.add(cbxNam);          
        cbxNam.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                   lblNamThang.setText("Năm "+cbxNam.getSelectedItem().toString());
                   lblNamQuy.setText("Năm "+cbxNam.getSelectedItem().toString());
                   fetchAllThang();
                   fetchAllQuy();
            }
        });
        cbxNam.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                   lblNamThang.setText("Năm "+cbxNam.getSelectedItem().toString());
                   lblNamQuy.setText("Năm "+cbxNam.getSelectedItem().toString());
                   fetchAllThang();
                   fetchAllQuy();
            }
        });
    }
    public static void fetchAllQuy(){
        listTableModelQuy.setRowCount(0);
        ThongKeDonHangQuyExcel(cbxNam.getSelectedItem().toString()).forEach(ThongKeQuy->{
            Vector row = new Vector();
                row.add(ThongKeQuy[1]);
                row.add(ThongKeQuy[2]);
                row.add(ThongKeQuy[3]);
                row.add(ThongKeQuy[4]);
                listTableModelQuy.addRow(row);
        });
        tblDonHangQuy.setModel(listTableModelQuy);
    }    
    public static void fetchAllThang(){
        
        listTableModelThang.setRowCount(0);
            ThongKeDonHangThangExcel(cbxNam.getSelectedItem().toString()).forEach(ThongKeThang->{
            Vector row = new Vector();
                row.add(ThongKeThang[1]);
                row.add(ThongKeThang[2]);
                row.add(ThongKeThang[3]);
                row.add(ThongKeThang[4]);
                listTableModelThang.addRow(row);
            });
            tblDonHang.setModel(listTableModelThang);
    }
    public ChiTietThongKeDonHang() {
        initComponents();
        setComboBox();
        customTable(tblDonHang,scpDonHang);
        ShowTable(tblDonHang,columnNames);
       
        ShowTableBottom(tblDonHangQuy,columnNamesBot);
        customTable(tblDonHangQuy,scpDonHang1);
        
        setShadow();
        pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlXuatExcelThang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlXuatExcelQuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTrangThai = new javax.swing.JLabel();
        pnlNam = new javax.swing.JPanel();
        pnlXuatExcelThang = new GUI.Rounded();
        lblQuayVe1 = new javax.swing.JLabel();
        pnlQuayVe = new GUI.Rounded();
        lblQuayVe = new javax.swing.JLabel();
        lblNamThang = new javax.swing.JLabel();
        pnlTblDonHangChuaXacNhan = new GUI.Rounded();
        scpDonHang = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        lblNamQuy = new javax.swing.JLabel();
        pnlTblDonHangChuaXacNhan1 = new GUI.Rounded();
        scpDonHang1 = new javax.swing.JScrollPane();
        tblDonHangQuy = new javax.swing.JTable(){
            public boolean isCellEditable ( int row, int col)
            {
                return false;
            }

        };
        pnlXuatExcelQuy = new GUI.Rounded();
        lblQuayVe2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTrangThai.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(0, 52, 102));
        lblTrangThai.setText("Năm:");
        add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, -1, 30));

        pnlNam.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlNamLayout = new javax.swing.GroupLayout(pnlNam);
        pnlNam.setLayout(pnlNamLayout);
        pnlNamLayout.setHorizontalGroup(
            pnlNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        pnlNamLayout.setVerticalGroup(
            pnlNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(pnlNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 130, 30));

        pnlXuatExcelThang.setBackground(new java.awt.Color(238, 243, 247));
        pnlXuatExcelThang.setForeground(new java.awt.Color(0, 146, 242));
        pnlXuatExcelThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXuatExcelThangMouseClicked(evt);
            }
        });

        lblQuayVe1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblQuayVe1.setForeground(new java.awt.Color(255, 255, 255));
        lblQuayVe1.setText("Xuất Excel");

        javax.swing.GroupLayout pnlXuatExcelThangLayout = new javax.swing.GroupLayout(pnlXuatExcelThang);
        pnlXuatExcelThang.setLayout(pnlXuatExcelThangLayout);
        pnlXuatExcelThangLayout.setHorizontalGroup(
            pnlXuatExcelThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXuatExcelThangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuayVe1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlXuatExcelThangLayout.setVerticalGroup(
            pnlXuatExcelThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQuayVe1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        add(pnlXuatExcelThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 50, -1, 30));

        pnlQuayVe.setBackground(new java.awt.Color(238, 243, 247));
        pnlQuayVe.setForeground(new java.awt.Color(0, 146, 242));
        pnlQuayVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlQuayVeMouseClicked(evt);
            }
        });

        lblQuayVe.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblQuayVe.setForeground(new java.awt.Color(255, 255, 255));
        lblQuayVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        lblQuayVe.setText("  Quay về");

        javax.swing.GroupLayout pnlQuayVeLayout = new javax.swing.GroupLayout(pnlQuayVe);
        pnlQuayVe.setLayout(pnlQuayVeLayout);
        pnlQuayVeLayout.setHorizontalGroup(
            pnlQuayVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuayVeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuayVe, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlQuayVeLayout.setVerticalGroup(
            pnlQuayVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQuayVe, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        add(pnlQuayVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        lblNamThang.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblNamThang.setForeground(new java.awt.Color(0, 146, 242));
        lblNamThang.setText("Năm 2020");
        add(lblNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        pnlTblDonHangChuaXacNhan.setBackground(new java.awt.Color(238, 243, 247));
        pnlTblDonHangChuaXacNhan.setForeground(new java.awt.Color(255, 255, 255));

        tblDonHang.setAutoCreateRowSorter(true);
        tblDonHang.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblDonHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblDonHang.setFocusable(false);
        tblDonHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblDonHang.setRowHeight(25);
        tblDonHang.setSelectionBackground(new java.awt.Color(72, 74, 89));
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
                .addComponent(scpDonHang)
                .addContainerGap())
        );
        pnlTblDonHangChuaXacNhanLayout.setVerticalGroup(
            pnlTblDonHangChuaXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTblDonHangChuaXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 980, 360));

        lblNamQuy.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblNamQuy.setForeground(new java.awt.Color(0, 146, 242));
        lblNamQuy.setText("Năm 2020");
        add(lblNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, -1));

        pnlTblDonHangChuaXacNhan1.setBackground(new java.awt.Color(238, 243, 247));
        pnlTblDonHangChuaXacNhan1.setForeground(new java.awt.Color(255, 255, 255));

        tblDonHangQuy.setAutoCreateRowSorter(true);
        tblDonHangQuy.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblDonHangQuy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblDonHangQuy.setFocusable(false);
        tblDonHangQuy.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblDonHangQuy.setRowHeight(25);
        tblDonHangQuy.setSelectionBackground(new java.awt.Color(72, 74, 89));
        tblDonHangQuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonHangQuyMouseClicked(evt);
            }
        });
        scpDonHang1.setViewportView(tblDonHangQuy);

        javax.swing.GroupLayout pnlTblDonHangChuaXacNhan1Layout = new javax.swing.GroupLayout(pnlTblDonHangChuaXacNhan1);
        pnlTblDonHangChuaXacNhan1.setLayout(pnlTblDonHangChuaXacNhan1Layout);
        pnlTblDonHangChuaXacNhan1Layout.setHorizontalGroup(
            pnlTblDonHangChuaXacNhan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpDonHang1, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTblDonHangChuaXacNhan1Layout.setVerticalGroup(
            pnlTblDonHangChuaXacNhan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDonHangChuaXacNhan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpDonHang1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        add(pnlTblDonHangChuaXacNhan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 970, 160));

        pnlXuatExcelQuy.setBackground(new java.awt.Color(238, 243, 247));
        pnlXuatExcelQuy.setForeground(new java.awt.Color(0, 146, 242));
        pnlXuatExcelQuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlXuatExcelQuyMouseClicked(evt);
            }
        });

        lblQuayVe2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblQuayVe2.setForeground(new java.awt.Color(255, 255, 255));
        lblQuayVe2.setText("Xuất Excel");

        javax.swing.GroupLayout pnlXuatExcelQuyLayout = new javax.swing.GroupLayout(pnlXuatExcelQuy);
        pnlXuatExcelQuy.setLayout(pnlXuatExcelQuyLayout);
        pnlXuatExcelQuyLayout.setHorizontalGroup(
            pnlXuatExcelQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXuatExcelQuyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuayVe2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlXuatExcelQuyLayout.setVerticalGroup(
            pnlXuatExcelQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQuayVe2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        add(pnlXuatExcelQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 460, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlQuayVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVeMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt))
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new DonHangJPanel());
        this.validate();
        this.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_pnlQuayVeMouseClicked

    private void tblDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangMouseClicked

    }//GEN-LAST:event_tblDonHangMouseClicked

    private void tblDonHangQuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangQuyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDonHangQuyMouseClicked

    private void pnlXuatExcelQuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXuatExcelQuyMouseClicked
        try {
            String path = SweetFileChooser.ExcelFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            ThongKeDonHangQuyExcel.ExportExcelFile(path, DataThongKeDonHang.ThongKeDonHangQuyExcel(cbxNam.getSelectedItem().toString()));
            SweetAlert.showSweetAlert(new JFrame(), "Hoàn tất", "Xuất dữ liệu thành công", 0);
        } catch (IOException ex) {
            Logger.getLogger(DonHangJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlXuatExcelQuyMouseClicked

    private void pnlXuatExcelThangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXuatExcelThangMouseClicked
        try {
            String path = SweetFileChooser.ExcelFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            ThongKeDonHangThangExcel.ExportExcelFile(path, DataThongKeDonHang.ThongKeDonHangThangExcel(cbxNam.getSelectedItem().toString()));
            SweetAlert.showSweetAlert(new JFrame(), "Hoàn tất", "Xuất dữ liệu thành công", 0);
        } catch (IOException ex) {
            Logger.getLogger(DonHangJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlXuatExcelThangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblNamQuy;
    private javax.swing.JLabel lblNamThang;
    private javax.swing.JLabel lblQuayVe;
    private javax.swing.JLabel lblQuayVe1;
    private javax.swing.JLabel lblQuayVe2;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnlNam;
    private GUI.Rounded pnlQuayVe;
    private GUI.Rounded pnlTblDonHangChuaXacNhan;
    private GUI.Rounded pnlTblDonHangChuaXacNhan1;
    private GUI.Rounded pnlXuatExcelQuy;
    private GUI.Rounded pnlXuatExcelThang;
    private javax.swing.JScrollPane scpDonHang;
    private javax.swing.JScrollPane scpDonHang1;
    private static javax.swing.JTable tblDonHang;
    private static javax.swing.JTable tblDonHangQuy;
    // End of variables declaration//GEN-END:variables
}
