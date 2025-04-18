/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ThongKe;

import static DataThongKe.ChartData.createDataChartPresent;
import static DataThongKe.DataThongKePhieuNhap.ThongKePhieuNhapNam;
import static DataThongKe.DataThongKePhieuNhap.ThongKePhieuNhapNgay;
import static DataThongKe.DataThongKePhieuNhap.ThongKePhieuNhapQuy;
import static DataThongKe.DataThongKePhieuNhap.ThongKePhieuNhapThang;
import static DataThongKe.DataThongKePhieuNhap.ThongKePhieuNhapTuDen;
import static DataThongKe.DataThongKePhieuNhap.getNgayPhieuNhap;
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
public class PhieuNhapJPanel extends javax.swing.JPanel {

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
                int[] phieuNhap = ThongKePhieuNhapThang(cbxNamThang,cbxThang);
                lblPhieuNhapNamThang.setText(changeCurrency(Integer.toString(phieuNhap[0])));
                lblTongChiNamThang.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
            }

            
        });
        cbxThang.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                int[] phieuNhap = ThongKePhieuNhapThang(cbxNamThang,cbxThang);
                lblPhieuNhapNamThang.setText(changeCurrency(Integer.toString(phieuNhap[0])));
                lblTongChiNamThang.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
            }
            
        });
        cbxNamThang.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] phieuNhap = ThongKePhieuNhapThang(cbxNamThang,cbxThang);
                lblPhieuNhapNamThang.setText(changeCurrency(Integer.toString(phieuNhap[0])));
                lblTongChiNamThang.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
            }
        });
        cbxThang.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] phieuNhap = ThongKePhieuNhapThang(cbxNamThang,cbxThang);
                lblPhieuNhapNamThang.setText(changeCurrency(Integer.toString(phieuNhap[0])));
                lblTongChiNamThang.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
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
                int[] phieuNhap = ThongKePhieuNhapQuy(cbxNamQuy,cbxQuy);
                lblPhieuNhapNamQuy.setText(changeCurrency(Integer.toString(phieuNhap[0])));
                lblTongChiNamQuy.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
            }
        });
        cbxQuy.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                int[] phieuNhap = ThongKePhieuNhapQuy(cbxNamQuy,cbxQuy);
                lblPhieuNhapNamQuy.setText(changeCurrency(Integer.toString(phieuNhap[0])));
                lblTongChiNamQuy.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
            }       
        });
        cbxNamQuy.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] phieuNhap = ThongKePhieuNhapQuy(cbxNamQuy,cbxQuy);
                lblPhieuNhapNamQuy.setText(changeCurrency(Integer.toString(phieuNhap[0])));
                lblTongChiNamQuy.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
            }
        });
        cbxQuy.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] phieuNhap = ThongKePhieuNhapQuy(cbxNamQuy,cbxQuy);
                lblPhieuNhapNamQuy.setText(changeCurrency(Integer.toString(phieuNhap[0])));
                lblTongChiNamQuy.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
            }
        });
        
        
        String[] nam = {"2020","2019","2018","2017"};
        SweetComboBox cbxNam = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,nam);  
        pnlNam.add(cbxNam);
        cbxNam.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                int[] phieuNhap = ThongKePhieuNhapNam(cbxNam);
                lblPhieuNhapNam.setText(changeCurrency(Integer.toString(phieuNhap[0])));
                lblTongChiNam.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
            }
        });
        cbxNam.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] phieuNhap = ThongKePhieuNhapNam(cbxNam);
                lblPhieuNhapNam.setText(changeCurrency(Integer.toString(phieuNhap[0])));
                lblTongChiNam.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
            }
        });

    }     
    public void setShadow(){
        pnlThongKe.setShadow(1);
        pnlShowChart.setShadow(1);
    }
        public void setChart(){
        createLineChart(pnlChart, createDataChartPresent(getNgayPhieuNhap()));
    }
    public PhieuNhapJPanel() {
        initComponents();
        setChart();
        setShadow();
        setComboBox();
         pnlQuayVe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        DateTime.setDate(dateNgay,dateTu,dateDen);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlShowChart = new GUI.Rounded();
        pnlChart = new javax.swing.JPanel();
        pnlThongKe = new GUI.Rounded();
        lblNgay = new javax.swing.JLabel();
        dateNgay = new com.toedter.calendar.JDateChooser();
        lblNgay1 = new javax.swing.JLabel();
        dateTu = new com.toedter.calendar.JDateChooser();
        dateDen = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
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
        lblPhieuNhapNgay = new javax.swing.JLabel();
        lblTongChiNgay = new javax.swing.JLabel();
        lblPhieuNhapTuDen = new javax.swing.JLabel();
        lblTongChiTuDen = new javax.swing.JLabel();
        lblPhieuNhapNamThang = new javax.swing.JLabel();
        lblTongChiNamThang = new javax.swing.JLabel();
        lblTongChiNamQuy = new javax.swing.JLabel();
        lblPhieuNhapNamQuy = new javax.swing.JLabel();
        lblPhieuNhapNam = new javax.swing.JLabel();
        lblTongChiNam = new javax.swing.JLabel();
        pnlQuayVe = new GUI.Rounded();
        lblQuayVe = new javax.swing.JLabel();
        pnlQuayVe1 = new GUI.Rounded();
        lblQuayVe1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 243, 247));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        add(pnlShowChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 980, 230));

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
        jLabel1.setText("Tổng chi:");
        pnlThongKe.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 52, 102));
        jLabel3.setText("Số phiếu nhập:");
        pnlThongKe.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 52, 102));
        jLabel5.setText("Số phiếu nhập:");
        pnlThongKe.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 52, 102));
        jLabel6.setText("Tổng chi:");
        pnlThongKe.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, -1, -1));

        jLabel7.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 52, 102));
        jLabel7.setText("Số phiếu nhập:");
        pnlThongKe.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 52, 102));
        jLabel8.setText("Tổng chi:");
        pnlThongKe.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, -1, -1));

        jLabel10.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 52, 102));
        jLabel10.setText("Tổng chi:");
        pnlThongKe.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, -1, -1));

        jLabel11.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 52, 102));
        jLabel11.setText("Số phiếu nhập:");
        pnlThongKe.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));

        jLabel13.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 52, 102));
        jLabel13.setText("Số phiếu nhập:");
        pnlThongKe.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, -1, -1));

        jLabel14.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 52, 102));
        jLabel14.setText("Tổng chi:");
        pnlThongKe.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, -1, -1));

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

        lblPhieuNhapNgay.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        lblPhieuNhapNgay.setText("1213414134");
        pnlThongKe.add(lblPhieuNhapNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, -1, -1));

        lblTongChiNgay.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        lblTongChiNgay.setForeground(new java.awt.Color(255, 0, 0));
        lblTongChiNgay.setText("123456");
        pnlThongKe.add(lblTongChiNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, -1, -1));

        lblPhieuNhapTuDen.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        lblPhieuNhapTuDen.setText("123456");
        pnlThongKe.add(lblPhieuNhapTuDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, -1, -1));

        lblTongChiTuDen.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        lblTongChiTuDen.setForeground(new java.awt.Color(255, 0, 0));
        lblTongChiTuDen.setText("123456");
        pnlThongKe.add(lblTongChiTuDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, -1, -1));

        lblPhieuNhapNamThang.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        lblPhieuNhapNamThang.setText("123456");
        pnlThongKe.add(lblPhieuNhapNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, -1, -1));

        lblTongChiNamThang.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        lblTongChiNamThang.setForeground(new java.awt.Color(255, 0, 0));
        lblTongChiNamThang.setText("123456");
        pnlThongKe.add(lblTongChiNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, -1, -1));

        lblTongChiNamQuy.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        lblTongChiNamQuy.setForeground(new java.awt.Color(255, 0, 0));
        lblTongChiNamQuy.setText("123456");
        pnlThongKe.add(lblTongChiNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, -1, -1));

        lblPhieuNhapNamQuy.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        lblPhieuNhapNamQuy.setText("123456");
        pnlThongKe.add(lblPhieuNhapNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, -1, -1));

        lblPhieuNhapNam.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        lblPhieuNhapNam.setText("123456");
        pnlThongKe.add(lblPhieuNhapNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, -1, -1));

        lblTongChiNam.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        lblTongChiNam.setForeground(new java.awt.Color(255, 0, 0));
        lblTongChiNam.setText("123456");
        pnlThongKe.add(lblTongChiNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, -1, -1));

        add(pnlThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 980, 330));

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

        add(pnlQuayVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

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
        int[] phieuNhap = ThongKePhieuNhapNgay(dateNgay.getDate());
        lblPhieuNhapNgay.setText(changeCurrency(Integer.toString(phieuNhap[0])));
        lblTongChiNgay.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
    }//GEN-LAST:event_dateNgayPropertyChange

    private void dateDenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDenPropertyChange
        int[] phieuNhap = ThongKePhieuNhapTuDen(dateTu.getDate(),dateDen.getDate());
        lblPhieuNhapTuDen.setText(changeCurrency(Integer.toString(phieuNhap[0])));
        lblTongChiTuDen.setText(changeCurrency(Integer.toString(phieuNhap[1]))+"đ");
    }//GEN-LAST:event_dateDenPropertyChange

    private void pnlQuayVe1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuayVe1MouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)){
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(new ChiTietThongKePhieuNhap());
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
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblNgay1;
    private javax.swing.JLabel lblNgay3;
    private javax.swing.JLabel lblNgay4;
    private javax.swing.JLabel lblNgay5;
    private javax.swing.JLabel lblNgay6;
    private javax.swing.JLabel lblNgay7;
    private javax.swing.JLabel lblNgay8;
    private javax.swing.JLabel lblPhieuNhapNam;
    private javax.swing.JLabel lblPhieuNhapNamQuy;
    private javax.swing.JLabel lblPhieuNhapNamThang;
    private javax.swing.JLabel lblPhieuNhapNgay;
    private javax.swing.JLabel lblPhieuNhapTuDen;
    private javax.swing.JLabel lblQuayVe;
    private javax.swing.JLabel lblQuayVe1;
    private javax.swing.JLabel lblTongChiNam;
    private javax.swing.JLabel lblTongChiNamQuy;
    private javax.swing.JLabel lblTongChiNamThang;
    private javax.swing.JLabel lblTongChiNgay;
    private javax.swing.JLabel lblTongChiTuDen;
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
