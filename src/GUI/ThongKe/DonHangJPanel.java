/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ThongKe;

import static DataThongKe.ChartData.createDataChartPresent;
import static DataThongKe.DataThongKeDonHang.ThongKeDonHangNgay;
import static DataThongKe.DataThongKeDonHang.ThongKeDonHangTuDen;
import static DataThongKe.DataThongKeDonHang.ThongKeDonHangThang;
import static DataThongKe.DataThongKeDonHang.ThongKeDonHangQuy;
import static DataThongKe.DataThongKeDonHang.ThongKeDonHangNam;
import static DataThongKe.DataThongKeDonHang.ThongKeDonHangThang;
import static DataThongKe.DataThongKeDonHang.getNgayXuatDonHang;
import static GUI.Chart.createChart.createLineChart;
import static GUI.Chart.createChart.createLineChart2;
import GUI.Sweet.SweetComboBox;
import GUI.ThongKeJPanel;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.SwingUtilities;
import static quanlycuahangsach.Currency.changeCurrency;
import quanlycuahangsach.DateTime;

/**
 *
 * @author admin
 */
public class DonHangJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DonHangJPanel
     */
    public void setComboBox(){
        String[] namthang = {"2020","2019","2018","2017"};
        SweetComboBox cbxNamThang = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,namthang);
        String[] thang = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        SweetComboBox cbxThang = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,thang);
        pnlNamThang.add(cbxNamThang);
        pnlThang.add(cbxThang);
        cbxNamThang.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                int[] donHang = ThongKeDonHangThang(cbxNamThang,cbxThang);
                lblDonHangNamThang.setText(changeCurrency(Integer.toString(donHang[0])));
                lblDonHoanThanhNamThang.setText(changeCurrency(Integer.toString(donHang[1])));
                lblDonHuyNamThang.setText(changeCurrency(Integer.toString(donHang[2])));
            }
        });
        cbxThang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int[] donHang = ThongKeDonHangThang(cbxNamThang,cbxThang);
                lblDonHangNamThang.setText(changeCurrency(Integer.toString(donHang[0])));
                lblDonHoanThanhNamThang.setText(changeCurrency(Integer.toString(donHang[1])));
                lblDonHuyNamThang.setText(changeCurrency(Integer.toString(donHang[2])));
            }
        });
        cbxNamThang.addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] donHang = ThongKeDonHangThang(cbxNamThang,cbxThang);
                lblDonHangNamThang.setText(changeCurrency(Integer.toString(donHang[0])));
                lblDonHoanThanhNamThang.setText(changeCurrency(Integer.toString(donHang[1])));
                lblDonHuyNamThang.setText(changeCurrency(Integer.toString(donHang[2])));
            }
        });
        cbxThang.addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] donHang = ThongKeDonHangThang(cbxNamThang,cbxThang);
                lblDonHangNamThang.setText(changeCurrency(Integer.toString(donHang[0])));
                lblDonHoanThanhNamThang.setText(changeCurrency(Integer.toString(donHang[1])));
                lblDonHuyNamThang.setText(changeCurrency(Integer.toString(donHang[2])));
            }
        });
        
        
        
        
        String[] namquy = {"2020","2019","2018","2017"};
        SweetComboBox cbxNamQuy = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,namquy);   
        String[] quy = {"1","2","3","4"};
        SweetComboBox cbxQuy = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,quy);
        pnlNamQuy.add(cbxNamQuy);
        pnlQuy.add(cbxQuy);
        cbxNamQuy.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                int[] donHang = ThongKeDonHangQuy(cbxNamQuy,cbxQuy);
                lblDonHangNamQuy.setText(changeCurrency(Integer.toString(donHang[0])));
                lblDonHoanThanhNamQuy.setText(changeCurrency(Integer.toString(donHang[1])));
                lblDonHuyNamQuy.setText(changeCurrency(Integer.toString(donHang[2])));
            }
        });
        cbxQuy.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int[] donHang = ThongKeDonHangQuy(cbxNamQuy,cbxQuy);
                lblDonHangNamQuy.setText(changeCurrency(Integer.toString(donHang[0])));
                lblDonHoanThanhNamQuy.setText(changeCurrency(Integer.toString(donHang[1])));
                lblDonHuyNamQuy.setText(changeCurrency(Integer.toString(donHang[2])));
            }
        });
        cbxNamQuy.addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] donHang = ThongKeDonHangQuy(cbxNamQuy,cbxQuy);
                lblDonHangNamQuy.setText(changeCurrency(Integer.toString(donHang[0])));
                lblDonHoanThanhNamQuy.setText(changeCurrency(Integer.toString(donHang[1])));
                lblDonHuyNamQuy.setText(changeCurrency(Integer.toString(donHang[2])));
            }
        });
        cbxQuy.addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] donHang = ThongKeDonHangQuy(cbxNamQuy,cbxQuy);
                lblDonHangNamQuy.setText(changeCurrency(Integer.toString(donHang[0])));
                lblDonHoanThanhNamQuy.setText(changeCurrency(Integer.toString(donHang[1])));
                lblDonHuyNamQuy.setText(changeCurrency(Integer.toString(donHang[2])));
            }
        });
  
        
        
        String[] nam = {"2020","2019","2018","2017"};
        SweetComboBox cbxNam = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,nam);  
        pnlNam.add(cbxNam);
         cbxNam.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                int[] donHang = ThongKeDonHangNam(cbxNam);
                lblDonHangNam.setText(changeCurrency(Integer.toString(donHang[0])));
                lblDonHoanThanhNam.setText(changeCurrency(Integer.toString(donHang[1])));
                lblDonHuyNam.setText(changeCurrency(Integer.toString(donHang[2])));
            }
        });
        cbxNam.addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] donHang = ThongKeDonHangNam(cbxNam);
                lblDonHangNam.setText(changeCurrency(Integer.toString(donHang[0])));
                lblDonHoanThanhNam.setText(changeCurrency(Integer.toString(donHang[1])));
                lblDonHuyNam.setText(changeCurrency(Integer.toString(donHang[2])));
            } 
        });
    }     
    public void setShadow(){
        pnlThongKe.setShadow(1);
        pnlShowChart.setShadow(1);
    }
        public void setChart(){
        createLineChart(pnlChart,createDataChartPresent(getNgayXuatDonHang()));
        
    }
    public DonHangJPanel() {
        initComponents();
        setChart();
        setShadow();
        setComboBox();
         pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        DateTime.setDate(dateNgay, dateTu, dateDen);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongKe = new GUI.Rounded();
        lblNgay = new javax.swing.JLabel();
        dateNgay = new com.toedter.calendar.JDateChooser();
        lblNgay1 = new javax.swing.JLabel();
        dateTu = new com.toedter.calendar.JDateChooser();
        dateDen = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblNgay3 = new javax.swing.JLabel();
        lblNgay4 = new javax.swing.JLabel();
        lblNgay5 = new javax.swing.JLabel();
        lblNgay6 = new javax.swing.JLabel();
        lblNgay7 = new javax.swing.JLabel();
        lblNgay8 = new javax.swing.JLabel();
        pnlNamThang = new javax.swing.JPanel();
        pnlThang = new javax.swing.JPanel();
        pnlQuy = new javax.swing.JPanel();
        pnlNamQuy = new javax.swing.JPanel();
        pnlNam = new javax.swing.JPanel();
        lblDonHuyNgay = new javax.swing.JLabel();
        lblDonHangNgay = new javax.swing.JLabel();
        lblDonHoanThanhNgay = new javax.swing.JLabel();
        lblDonHuyTuDen = new javax.swing.JLabel();
        lblDonHangTuDen = new javax.swing.JLabel();
        lblDonHoanThanhTuDen = new javax.swing.JLabel();
        lblDonHangNamThang = new javax.swing.JLabel();
        lblDonHuyNamThang = new javax.swing.JLabel();
        lblDonHoanThanhNamThang = new javax.swing.JLabel();
        lblDonHoanThanhNamQuy = new javax.swing.JLabel();
        lblDonHangNamQuy = new javax.swing.JLabel();
        lblDonHuyNamQuy = new javax.swing.JLabel();
        lblDonHangNam = new javax.swing.JLabel();
        lblDonHuyNam = new javax.swing.JLabel();
        lblDonHoanThanhNam = new javax.swing.JLabel();
        pnlShowChart = new GUI.Rounded();
        pnlChart = new javax.swing.JPanel();
        pnlQuayVe = new GUI.Rounded();
        lblQuayVe = new javax.swing.JLabel();
        pnlQuayVe1 = new GUI.Rounded();
        lblQuayVe1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongKe.setBackground(new java.awt.Color(238, 243, 247));
        pnlThongKe.setForeground(new java.awt.Color(238, 243, 247));
        pnlThongKe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNgay.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay.setForeground(new java.awt.Color(0, 52, 102));
        lblNgay.setText("Ngày:");
        pnlThongKe.add(lblNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 40));

        dateNgay.setBackground(new java.awt.Color(255, 255, 255));
        dateNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayPropertyChange(evt);
            }
        });
        pnlThongKe.add(dateNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 140, -1));

        lblNgay1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay1.setForeground(new java.awt.Color(0, 52, 102));
        lblNgay1.setText("Năm:");
        pnlThongKe.add(lblNgay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 30));

        dateTu.setBackground(new java.awt.Color(255, 255, 255));
        dateTu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateTuPropertyChange(evt);
            }
        });
        pnlThongKe.add(dateTu, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 140, -1));

        dateDen.setBackground(new java.awt.Color(255, 255, 255));
        dateDen.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateDenPropertyChange(evt);
            }
        });
        pnlThongKe.add(dateDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 140, -1));

        jLabel1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 52, 102));
        jLabel1.setText("Đơn hoàn thành:");
        pnlThongKe.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 52, 102));
        jLabel2.setText("Đơn hủy:");
        pnlThongKe.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 52, 102));
        jLabel3.setText("Số đơn hàng:");
        pnlThongKe.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 52, 102));
        jLabel4.setText("Đơn hủy:");
        pnlThongKe.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, -1, -1));

        jLabel5.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 52, 102));
        jLabel5.setText("Số đơn hàng:");
        pnlThongKe.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 52, 102));
        jLabel6.setText("Đơn hoàn thành:");
        pnlThongKe.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, -1, -1));

        jLabel7.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 52, 102));
        jLabel7.setText("Số đơn hàng:");
        pnlThongKe.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 52, 102));
        jLabel8.setText("Đơn hoàn thành:");
        pnlThongKe.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, -1, -1));

        jLabel9.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 52, 102));
        jLabel9.setText("Đơn hủy:");
        pnlThongKe.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, -1, -1));

        jLabel10.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 52, 102));
        jLabel10.setText("Đơn hoàn thành:");
        pnlThongKe.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, -1, -1));

        jLabel11.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 52, 102));
        jLabel11.setText("Số đơn hàng:");
        pnlThongKe.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));

        jLabel12.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 52, 102));
        jLabel12.setText("Đơn hủy:");
        pnlThongKe.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 220, -1, -1));

        jLabel13.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 52, 102));
        jLabel13.setText("Số đơn hàng:");
        pnlThongKe.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, -1, -1));

        jLabel14.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 52, 102));
        jLabel14.setText("Đơn hoàn thành:");
        pnlThongKe.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, -1, -1));

        jLabel15.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 52, 102));
        jLabel15.setText("Đơn hủy:");
        pnlThongKe.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 280, -1, -1));

        lblNgay3.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay3.setForeground(new java.awt.Color(0, 52, 102));
        lblNgay3.setText("Từ:");
        pnlThongKe.add(lblNgay3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 30));

        lblNgay4.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay4.setForeground(new java.awt.Color(0, 52, 102));
        lblNgay4.setText("Năm:");
        pnlThongKe.add(lblNgay4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 30));

        lblNgay5.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay5.setForeground(new java.awt.Color(0, 52, 102));
        lblNgay5.setText("Năm:");
        pnlThongKe.add(lblNgay5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, 30));

        lblNgay6.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay6.setForeground(new java.awt.Color(0, 52, 102));
        lblNgay6.setText("Đến:");
        pnlThongKe.add(lblNgay6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, 30));

        lblNgay7.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay7.setForeground(new java.awt.Color(0, 52, 102));
        lblNgay7.setText("Tháng:");
        pnlThongKe.add(lblNgay7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, 30));

        lblNgay8.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 1, 14)); // NOI18N
        lblNgay8.setForeground(new java.awt.Color(0, 52, 102));
        lblNgay8.setText("Quý:");
        pnlThongKe.add(lblNgay8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, -1, 30));

        pnlNamThang.setBackground(new java.awt.Color(202, 229, 246));
        pnlNamThang.setForeground(new java.awt.Color(202, 229, 246));
        pnlNamThang.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlNamThangLayout = new javax.swing.GroupLayout(pnlNamThang);
        pnlNamThang.setLayout(pnlNamThangLayout);
        pnlNamThangLayout.setHorizontalGroup(
            pnlNamThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlNamThangLayout.setVerticalGroup(
            pnlNamThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongKe.add(pnlNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 150, 30));

        pnlThang.setBackground(new java.awt.Color(202, 229, 246));
        pnlThang.setForeground(new java.awt.Color(202, 229, 246));
        pnlThang.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlThangLayout = new javax.swing.GroupLayout(pnlThang);
        pnlThang.setLayout(pnlThangLayout);
        pnlThangLayout.setHorizontalGroup(
            pnlThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlThangLayout.setVerticalGroup(
            pnlThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongKe.add(pnlThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        pnlQuy.setBackground(new java.awt.Color(202, 229, 246));
        pnlQuy.setForeground(new java.awt.Color(202, 229, 246));
        pnlQuy.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlQuyLayout = new javax.swing.GroupLayout(pnlQuy);
        pnlQuy.setLayout(pnlQuyLayout);
        pnlQuyLayout.setHorizontalGroup(
            pnlQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlQuyLayout.setVerticalGroup(
            pnlQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongKe.add(pnlQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        pnlNamQuy.setBackground(new java.awt.Color(202, 229, 246));
        pnlNamQuy.setForeground(new java.awt.Color(202, 229, 246));
        pnlNamQuy.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlNamQuyLayout = new javax.swing.GroupLayout(pnlNamQuy);
        pnlNamQuy.setLayout(pnlNamQuyLayout);
        pnlNamQuyLayout.setHorizontalGroup(
            pnlNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlNamQuyLayout.setVerticalGroup(
            pnlNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongKe.add(pnlNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));

        pnlNam.setBackground(new java.awt.Color(202, 229, 246));
        pnlNam.setForeground(new java.awt.Color(202, 229, 246));
        pnlNam.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlNamLayout = new javax.swing.GroupLayout(pnlNam);
        pnlNam.setLayout(pnlNamLayout);
        pnlNamLayout.setHorizontalGroup(
            pnlNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlNamLayout.setVerticalGroup(
            pnlNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlThongKe.add(pnlNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

        lblDonHuyNgay.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHuyNgay.setForeground(new java.awt.Color(255, 0, 0));
        lblDonHuyNgay.setText("1234");
        pnlThongKe.add(lblDonHuyNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, -1, -1));

        lblDonHangNgay.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHangNgay.setText("1213414134");
        pnlThongKe.add(lblDonHangNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, -1, -1));

        lblDonHoanThanhNgay.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHoanThanhNgay.setForeground(new java.awt.Color(37, 213, 54));
        lblDonHoanThanhNgay.setText("123456");
        pnlThongKe.add(lblDonHoanThanhNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, -1, -1));

        lblDonHuyTuDen.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHuyTuDen.setForeground(new java.awt.Color(255, 0, 0));
        lblDonHuyTuDen.setText("1234");
        pnlThongKe.add(lblDonHuyTuDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 80, -1, -1));

        lblDonHangTuDen.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHangTuDen.setText("123456");
        pnlThongKe.add(lblDonHangTuDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, -1, -1));

        lblDonHoanThanhTuDen.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHoanThanhTuDen.setForeground(new java.awt.Color(37, 213, 54));
        lblDonHoanThanhTuDen.setText("123456");
        pnlThongKe.add(lblDonHoanThanhTuDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, -1, -1));

        lblDonHangNamThang.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHangNamThang.setText("123456");
        pnlThongKe.add(lblDonHangNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, -1, -1));

        lblDonHuyNamThang.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHuyNamThang.setForeground(new java.awt.Color(255, 0, 0));
        lblDonHuyNamThang.setText("1234");
        pnlThongKe.add(lblDonHuyNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 150, -1, -1));

        lblDonHoanThanhNamThang.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHoanThanhNamThang.setForeground(new java.awt.Color(37, 213, 54));
        lblDonHoanThanhNamThang.setText("123456");
        pnlThongKe.add(lblDonHoanThanhNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, -1, -1));

        lblDonHoanThanhNamQuy.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHoanThanhNamQuy.setForeground(new java.awt.Color(37, 213, 54));
        lblDonHoanThanhNamQuy.setText("123456");
        pnlThongKe.add(lblDonHoanThanhNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, -1, -1));

        lblDonHangNamQuy.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHangNamQuy.setText("123456");
        pnlThongKe.add(lblDonHangNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, -1, -1));

        lblDonHuyNamQuy.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHuyNamQuy.setForeground(new java.awt.Color(255, 0, 0));
        lblDonHuyNamQuy.setText("1234");
        pnlThongKe.add(lblDonHuyNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 220, -1, -1));

        lblDonHangNam.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHangNam.setText("123456");
        pnlThongKe.add(lblDonHangNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, -1, -1));

        lblDonHuyNam.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHuyNam.setForeground(new java.awt.Color(255, 0, 0));
        lblDonHuyNam.setText("1234");
        pnlThongKe.add(lblDonHuyNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 280, -1, -1));

        lblDonHoanThanhNam.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDonHoanThanhNam.setForeground(new java.awt.Color(37, 213, 54));
        lblDonHoanThanhNam.setText("123456");
        pnlThongKe.add(lblDonHoanThanhNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, -1, -1));

        add(pnlThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 980, 330));

        pnlShowChart.setBackground(new java.awt.Color(238, 243, 247));
        pnlShowChart.setForeground(new java.awt.Color(238, 243, 247));

        pnlChart.setLayout(new javax.swing.BoxLayout(pnlChart, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout pnlShowChartLayout = new javax.swing.GroupLayout(pnlShowChart);
        pnlShowChart.setLayout(pnlShowChartLayout);
        pnlShowChartLayout.setHorizontalGroup(
            pnlShowChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlShowChartLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(pnlChart, javax.swing.GroupLayout.PREFERRED_SIZE, 929, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnlShowChartLayout.setVerticalGroup(
            pnlShowChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlShowChartLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(pnlChart, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        add(pnlShowChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 980, 230));

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

        add(pnlQuayVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        pnlQuayVe1.setBackground(new java.awt.Color(238, 243, 247));
        pnlQuayVe1.setForeground(new java.awt.Color(0, 146, 242));
        pnlQuayVe1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlQuayVe1MouseClicked(evt);
            }
        });

        lblQuayVe1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblQuayVe1.setForeground(new java.awt.Color(255, 255, 255));
        lblQuayVe1.setText("Xem chi tiết thống kê");

        javax.swing.GroupLayout pnlQuayVe1Layout = new javax.swing.GroupLayout(pnlQuayVe1);
        pnlQuayVe1.setLayout(pnlQuayVe1Layout);
        pnlQuayVe1Layout.setHorizontalGroup(
            pnlQuayVe1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuayVe1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuayVe1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlQuayVe1Layout.setVerticalGroup(
            pnlQuayVe1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblQuayVe1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        add(pnlQuayVe1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 30, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void dateTuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateTuPropertyChange
        dateDen.setDate(dateTu.getDate());
        dateDen.setMinSelectableDate(dateTu.getDate());
    }//GEN-LAST:event_dateTuPropertyChange

    private void pnlQuayVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVeMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(new ThongKeJPanel());
            this.validate();
            this.repaint();
        }// TODO add your handling code here:
    }//GEN-LAST:event_pnlQuayVeMouseClicked

    private void dateNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayPropertyChange
        lblDonHangNgay.setText(Integer.toString(ThongKeDonHangNgay(dateNgay.getDate())[0]));
        lblDonHoanThanhNgay.setText(Integer.toString(ThongKeDonHangNgay(dateNgay.getDate())[1]));
        lblDonHuyNgay.setText(Integer.toString(ThongKeDonHangNgay(dateNgay.getDate())[2]));
        
        
    }//GEN-LAST:event_dateNgayPropertyChange

    private void dateDenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDenPropertyChange
        lblDonHangTuDen.setText(Integer.toString(ThongKeDonHangTuDen(dateTu.getDate(),dateDen.getDate())[0]));     
        lblDonHoanThanhTuDen.setText(Integer.toString(ThongKeDonHangTuDen(dateTu.getDate(),dateDen.getDate())[1]));     
        lblDonHuyTuDen.setText(Integer.toString(ThongKeDonHangTuDen(dateTu.getDate(),dateDen.getDate())[2]));
    }//GEN-LAST:event_dateDenPropertyChange

    private void pnlQuayVe1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVe1MouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(new ChiTietThongKeDonHang());
            this.validate();
            this.repaint();
        }
    }//GEN-LAST:event_pnlQuayVe1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateDen;
    private com.toedter.calendar.JDateChooser dateNgay;
    private com.toedter.calendar.JDateChooser dateTu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblDonHangNam;
    private javax.swing.JLabel lblDonHangNamQuy;
    private javax.swing.JLabel lblDonHangNamThang;
    private javax.swing.JLabel lblDonHangNgay;
    private javax.swing.JLabel lblDonHangTuDen;
    private javax.swing.JLabel lblDonHoanThanhNam;
    private javax.swing.JLabel lblDonHoanThanhNamQuy;
    private javax.swing.JLabel lblDonHoanThanhNamThang;
    private javax.swing.JLabel lblDonHoanThanhNgay;
    private javax.swing.JLabel lblDonHoanThanhTuDen;
    private javax.swing.JLabel lblDonHuyNam;
    private javax.swing.JLabel lblDonHuyNamQuy;
    private javax.swing.JLabel lblDonHuyNamThang;
    private javax.swing.JLabel lblDonHuyNgay;
    private javax.swing.JLabel lblDonHuyTuDen;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblNgay1;
    private javax.swing.JLabel lblNgay3;
    private javax.swing.JLabel lblNgay4;
    private javax.swing.JLabel lblNgay5;
    private javax.swing.JLabel lblNgay6;
    private javax.swing.JLabel lblNgay7;
    private javax.swing.JLabel lblNgay8;
    private javax.swing.JLabel lblQuayVe;
    private javax.swing.JLabel lblQuayVe1;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JPanel pnlNam;
    private javax.swing.JPanel pnlNamQuy;
    private javax.swing.JPanel pnlNamThang;
    private GUI.Rounded pnlQuayVe;
    private GUI.Rounded pnlQuayVe1;
    private javax.swing.JPanel pnlQuy;
    private GUI.Rounded pnlShowChart;
    private javax.swing.JPanel pnlThang;
    private GUI.Rounded pnlThongKe;
    // End of variables declaration//GEN-END:variables
}
