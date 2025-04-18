/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static DataThongKe.ChartData.createDataChartPresentDoanhThu;
import DataThongKe.DataDoanhThu;
import static DataThongKe.DataDoanhThu.DoanhThuNgay;
import static DataThongKe.DataDoanhThu.DoanhThuTuDen;
import static DataThongKe.DataDoanhThu.DoanhThuThang;
import static DataThongKe.DataDoanhThu.DoanhThuQuy;
import static DataThongKe.DataDoanhThu.DoanhThuNam;
import static DataThongKe.DataDoanhThu.DoanhThuNgayHomNay;
import static DataThongKe.DataDoanhThu.DoanhThuThang;
import static DataThongKe.DataDoanhThu.DoanhThuThangNay;
import static DataThongKe.DataDoanhThu.DoanhThuTuanNay;
import static DataThongKe.DataDoanhThu.processBar;
import static GUI.Chart.createChart.createLineChart;
import GUI.Sweet.SweetComboBox;
import GUI.Sweet.SweetFileChooser;
import GUI.SweetAlert.SweetAlert;
import IO.Excel.DoanhThuQuyExcel;
import IO.Excel.DoanhThuThangExcel;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static quanlycuahangsach.Currency.changeCurrency;
import quanlycuahangsach.DateTime;

/**
 *
 * @author admin
 */
public class DoanhThuJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DoanhThuJPanel
     */
    SweetComboBox cbxNamQuy;
    SweetComboBox cbxNam;
    public void rounded(){
        float[] process = processBar();
                new Thread(new Runnable(){
            public void run(){
                try {
                    for(float i=0;i<=100;i+=0.5){
                        if(i<=process[0]){
                            pgbHomNay.UpdateProgress(i);
                            pgbHomNay.repaint();
                        }
                        if(i<=process[1]){
                            pgbTuanNay.UpdateProgress(i);
                            pgbTuanNay.repaint();
                        }
                        if(i<=process[2]){
                            pgbThangNay.UpdateProgress(i);
                            pgbThangNay.repaint();                        
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
    }
    public void setComboBox(){
        String[] namthang = {"2020","2019","2018","2017"};
        SweetComboBox cbxNamThang = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,namthang);
        String[] thang = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        SweetComboBox cbxThang = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,thang);
        pnlNamThang.add(cbxNamThang);
        pnlThang.add(cbxThang);
        cbxNamThang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
             int[] doanhThu = DoanhThuThang(cbxNamThang,cbxThang);
             lblDoanhThuNamThang.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
             lblChiNamThang.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
             int loiNhuan = doanhThu[0]-doanhThu[1];
             if(loiNhuan<0){
                 loiNhuan=0;
                 lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             else{
                 lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             
            }
        });
        cbxThang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
             int[] doanhThu = DoanhThuThang(cbxNamThang,cbxThang);
             lblDoanhThuNamThang.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
             lblChiNamThang.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
             int loiNhuan = doanhThu[0]-doanhThu[1];
             if(loiNhuan<0){
                 loiNhuan=0;
                 lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             else{
                 lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
            }
        });
        cbxNamThang.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] doanhThu = DoanhThuThang(cbxNamThang,cbxThang);
                lblDoanhThuNamThang.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNamThang.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
            }
        });
        cbxThang.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] doanhThu = DoanhThuThang(cbxNamThang,cbxThang);
                lblDoanhThuNamThang.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNamThang.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNamThang.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
            }
        });
        
        
        
        
        
        String[] namquy = {"2020","2019","2018","2017"};
        cbxNamQuy = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,namquy);   
        String[] quy = {"1","2","3","4"};
        SweetComboBox cbxQuy = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,quy);
        pnlNamQuy.add(cbxNamQuy);
        pnlQuy.add(cbxQuy);
        cbxNamQuy.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
             int[] doanhThu = DoanhThuQuy(cbxNamQuy,cbxQuy);
             lblDoanhThuNamQuy.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
             lblChiNamQuy.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
             int loiNhuan = doanhThu[0]-doanhThu[1];
             if(loiNhuan<0){
                 loiNhuan=0;
                 lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             else{
                 lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             
            }
        });
        cbxQuy.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
             int[] doanhThu = DoanhThuQuy(cbxNamQuy,cbxQuy);
             lblDoanhThuNamQuy.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
             lblChiNamQuy.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
             int loiNhuan = doanhThu[0]-doanhThu[1];
             if(loiNhuan<0){
                 loiNhuan=0;
                 lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
             else{
                 lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
             }
            }
        });
        cbxNamQuy.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] doanhThu = DoanhThuQuy(cbxNamQuy,cbxQuy);
                lblDoanhThuNamQuy.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNamQuy.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
            }
        });
        cbxQuy.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] doanhThu = DoanhThuQuy(cbxNamQuy,cbxQuy);
                lblDoanhThuNamQuy.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNamQuy.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNamQuy.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
            }
        });
        
        
        
        String[] nam = {"2019","2018","2017","2016"};
        cbxNam = new SweetComboBox("#CAE5F6","#181818",0,0,150,30,nam);  
        pnlNam.add(cbxNam);
        cbxNam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int[] doanhThu = DoanhThuNam(cbxNam);
                lblDoanhThuNam.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNam.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNam.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNam.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
            }
        });
        cbxNam.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int[] doanhThu = DoanhThuNam(cbxNam);
                lblDoanhThuNam.setText(changeCurrency(Integer.toString(doanhThu[0]))+"đ");
                lblChiNam.setText(changeCurrency(Integer.toString(doanhThu[1]))+"đ");
                int loiNhuan = doanhThu[0]-doanhThu[1];
                if(loiNhuan<0){
                    loiNhuan=0;
                    lblLoiNhuanNam.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                }
                else{
                    lblLoiNhuanNam.setText(changeCurrency(Integer.toString(loiNhuan))+"đ");
                } 
            }
        });

    }     
    public void setShadow(){
        pnlThongKe.setShadow(1);
        pnlShowChart.setShadow(1);
        pnlNguoiDung1.setShadow(1);
        pnlNguoiDung2.setShadow(1);
        pnlNguoiDung3.setShadow(1);
       
    }
    public void setChart(int[] data){
        createLineChart(pnlChart, data);
    }    
    public DoanhThuJPanel() {
        initComponents();
        rounded();
        setChart(createDataChartPresentDoanhThu(DataDoanhThu.getNgayXuatDonHang()));
        setShadow();
        setComboBox();    
        DateTime.setDate(dateNgay, dateTu, dateDen);
        pnlXuatExcelNam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlXuatExcelNamQuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblSoLuongDoanhThuHomNay.setText(changeCurrency(Integer.toString(DoanhThuNgayHomNay()))+"đ");
        lblSoLuongDoanhThuTuanNay.setText(changeCurrency(Integer.toString(DoanhThuTuanNay()))+"đ");
        lblSoLuongDoanhThuThangNay.setText(changeCurrency(Integer.toString(DoanhThuThangNay()))+"đ");
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
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
        lblDoanhThuNgay = new javax.swing.JLabel();
        lblDoanhThuTuDen = new javax.swing.JLabel();
        lblDoanhThuNamThang = new javax.swing.JLabel();
        lblChiNamThang = new javax.swing.JLabel();
        lblLoiNhuanNamThang = new javax.swing.JLabel();
        lblLoiNhuanNamQuy = new javax.swing.JLabel();
        lblDoanhThuNamQuy = new javax.swing.JLabel();
        lblChiNamQuy = new javax.swing.JLabel();
        lblDoanhThuNam = new javax.swing.JLabel();
        lblChiNam = new javax.swing.JLabel();
        lblLoiNhuanNam = new javax.swing.JLabel();
        pnlXuatExcelNamQuy = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        pnlXuatExcelNam = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        pnlShowChart = new GUI.Rounded();
        pnlChart = new javax.swing.JPanel();
        pnlNguoiDung1 = new GUI.Rounded();
        lblSoLuongDoanhThuHomNay = new javax.swing.JLabel();
        lblNguoiDung1 = new javax.swing.JLabel();
        pgbHomNay = new GUI.ProgressBarJPanel("#ffffff","#81b3a3","#c0d9d0");
        pnlNguoiDung2 = new GUI.Rounded();
        lblSoLuongDoanhThuTuanNay = new javax.swing.JLabel();
        lblNguoiDung2 = new javax.swing.JLabel();
        pgbTuanNay = new GUI.ProgressBarJPanel("#ffffff","#f36b7f","#f4c7d1");
        pnlNguoiDung3 = new GUI.Rounded();
        lblSoLuongDoanhThuThangNay = new javax.swing.JLabel();
        lblNguoiDung3 = new javax.swing.JLabel();
        pgbThangNay = new GUI.ProgressBarJPanel("#ffffff","#f8cf61","#f8ebc9");

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

        jLabel3.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 52, 102));
        jLabel3.setText("Doanh thu:");
        pnlThongKe.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 52, 102));
        jLabel5.setText("Doanh thu:");
        pnlThongKe.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, -1));

        jLabel7.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 52, 102));
        jLabel7.setText("Doanh thu:");
        pnlThongKe.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 52, 102));
        jLabel8.setText("Lợi nhuận:");
        pnlThongKe.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, -1, -1));

        jLabel9.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 52, 102));
        jLabel9.setText("Chi:");
        pnlThongKe.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, -1, -1));

        jLabel10.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 52, 102));
        jLabel10.setText("Lợi nhuận:");
        pnlThongKe.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, -1, -1));

        jLabel11.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 52, 102));
        jLabel11.setText("Doanh thu:");
        pnlThongKe.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));

        jLabel12.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 52, 102));
        jLabel12.setText("Chi:");
        pnlThongKe.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 220, -1, -1));

        jLabel13.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 52, 102));
        jLabel13.setText("Doanh thu:");
        pnlThongKe.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, -1, -1));

        jLabel14.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 52, 102));
        jLabel14.setText("Lợi nhuận:");
        pnlThongKe.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, -1, -1));

        jLabel15.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 52, 102));
        jLabel15.setText("Chi:");
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

        lblDoanhThuNgay.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDoanhThuNgay.setText("1213414134");
        pnlThongKe.add(lblDoanhThuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, -1, -1));

        lblDoanhThuTuDen.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDoanhThuTuDen.setText("123456");
        pnlThongKe.add(lblDoanhThuTuDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, -1, -1));

        lblDoanhThuNamThang.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDoanhThuNamThang.setText("123456");
        pnlThongKe.add(lblDoanhThuNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, -1, -1));

        lblChiNamThang.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblChiNamThang.setForeground(new java.awt.Color(255, 0, 0));
        lblChiNamThang.setText("20.000.000");
        pnlThongKe.add(lblChiNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, -1, -1));

        lblLoiNhuanNamThang.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblLoiNhuanNamThang.setForeground(new java.awt.Color(37, 213, 54));
        lblLoiNhuanNamThang.setText("123456");
        pnlThongKe.add(lblLoiNhuanNamThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, -1, -1));

        lblLoiNhuanNamQuy.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblLoiNhuanNamQuy.setForeground(new java.awt.Color(37, 213, 54));
        lblLoiNhuanNamQuy.setText("123456");
        pnlThongKe.add(lblLoiNhuanNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, -1, -1));

        lblDoanhThuNamQuy.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDoanhThuNamQuy.setText("123456");
        pnlThongKe.add(lblDoanhThuNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, -1, -1));

        lblChiNamQuy.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblChiNamQuy.setForeground(new java.awt.Color(255, 0, 0));
        lblChiNamQuy.setText("20.000.000");
        pnlThongKe.add(lblChiNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, -1, -1));

        lblDoanhThuNam.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDoanhThuNam.setText("123456");
        pnlThongKe.add(lblDoanhThuNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, -1, -1));

        lblChiNam.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblChiNam.setForeground(new java.awt.Color(255, 0, 0));
        lblChiNam.setText("20.000.000");
        pnlThongKe.add(lblChiNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 280, -1, -1));

        lblLoiNhuanNam.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblLoiNhuanNam.setForeground(new java.awt.Color(37, 213, 54));
        lblLoiNhuanNam.setText("123456");
        pnlThongKe.add(lblLoiNhuanNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, -1, -1));

        pnlXuatExcelNamQuy.setBackground(new java.awt.Color(0, 146, 242));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Xuất Excel");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlXuatExcelNamQuyLayout = new javax.swing.GroupLayout(pnlXuatExcelNamQuy);
        pnlXuatExcelNamQuy.setLayout(pnlXuatExcelNamQuyLayout);
        pnlXuatExcelNamQuyLayout.setHorizontalGroup(
            pnlXuatExcelNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
            .addGroup(pnlXuatExcelNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlXuatExcelNamQuyLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlXuatExcelNamQuyLayout.setVerticalGroup(
            pnlXuatExcelNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
            .addGroup(pnlXuatExcelNamQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlXuatExcelNamQuyLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlThongKe.add(pnlXuatExcelNamQuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 240, 70, 20));

        pnlXuatExcelNam.setBackground(new java.awt.Color(0, 146, 242));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Xuất Excel");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlXuatExcelNamLayout = new javax.swing.GroupLayout(pnlXuatExcelNam);
        pnlXuatExcelNam.setLayout(pnlXuatExcelNamLayout);
        pnlXuatExcelNamLayout.setHorizontalGroup(
            pnlXuatExcelNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
            .addGroup(pnlXuatExcelNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlXuatExcelNamLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlXuatExcelNamLayout.setVerticalGroup(
            pnlXuatExcelNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
            .addGroup(pnlXuatExcelNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlXuatExcelNamLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlThongKe.add(pnlXuatExcelNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 300, 70, 20));

        add(pnlThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 980, 330));

        pnlShowChart.setBackground(new java.awt.Color(238, 243, 247));
        pnlShowChart.setForeground(new java.awt.Color(238, 243, 247));

        pnlChart.setLayout(new javax.swing.BoxLayout(pnlChart, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout pnlShowChartLayout = new javax.swing.GroupLayout(pnlShowChart);
        pnlShowChart.setLayout(pnlShowChartLayout);
        pnlShowChartLayout.setHorizontalGroup(
            pnlShowChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlShowChartLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlChart, javax.swing.GroupLayout.PREFERRED_SIZE, 929, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        pnlShowChartLayout.setVerticalGroup(
            pnlShowChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlShowChartLayout.createSequentialGroup()
                .addComponent(pnlChart, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlShowChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 980, 200));

        pnlNguoiDung1.setBackground(new java.awt.Color(238, 243, 247));
        pnlNguoiDung1.setForeground(new java.awt.Color(238, 243, 247));
        pnlNguoiDung1.setPreferredSize(new java.awt.Dimension(279, 120));

        lblSoLuongDoanhThuHomNay.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 22)); // NOI18N
        lblSoLuongDoanhThuHomNay.setForeground(new java.awt.Color(0, 52, 102));
        lblSoLuongDoanhThuHomNay.setText("5.000.000.000");

        lblNguoiDung1.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 18)); // NOI18N
        lblNguoiDung1.setForeground(new java.awt.Color(24, 24, 24));
        lblNguoiDung1.setText("Hôm nay");

        pgbHomNay.setBackground(new java.awt.Color(255, 255, 255));
        pgbHomNay.setForeground(new java.awt.Color(51, 255, 102));

        javax.swing.GroupLayout pgbHomNayLayout = new javax.swing.GroupLayout(pgbHomNay);
        pgbHomNay.setLayout(pgbHomNayLayout);
        pgbHomNayLayout.setHorizontalGroup(
            pgbHomNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 99, Short.MAX_VALUE)
        );
        pgbHomNayLayout.setVerticalGroup(
            pgbHomNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlNguoiDung1Layout = new javax.swing.GroupLayout(pnlNguoiDung1);
        pnlNguoiDung1.setLayout(pnlNguoiDung1Layout);
        pnlNguoiDung1Layout.setHorizontalGroup(
            pnlNguoiDung1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiDung1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(pgbHomNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlNguoiDung1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiDung1)
                    .addComponent(lblSoLuongDoanhThuHomNay))
                .addContainerGap())
        );
        pnlNguoiDung1Layout.setVerticalGroup(
            pnlNguoiDung1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiDung1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblNguoiDung1)
                .addGap(18, 18, 18)
                .addComponent(lblSoLuongDoanhThuHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNguoiDung1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pgbHomNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlNguoiDung1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, 120));

        pnlNguoiDung2.setBackground(new java.awt.Color(238, 243, 247));
        pnlNguoiDung2.setForeground(new java.awt.Color(238, 243, 247));
        pnlNguoiDung2.setPreferredSize(new java.awt.Dimension(279, 120));

        lblSoLuongDoanhThuTuanNay.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 22)); // NOI18N
        lblSoLuongDoanhThuTuanNay.setForeground(new java.awt.Color(0, 52, 102));
        lblSoLuongDoanhThuTuanNay.setText("5.000.000.000");

        lblNguoiDung2.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 18)); // NOI18N
        lblNguoiDung2.setForeground(new java.awt.Color(24, 24, 24));
        lblNguoiDung2.setText("Tuần này");

        pgbTuanNay.setBackground(new java.awt.Color(255, 255, 255));
        pgbTuanNay.setForeground(new java.awt.Color(51, 255, 102));

        javax.swing.GroupLayout pgbTuanNayLayout = new javax.swing.GroupLayout(pgbTuanNay);
        pgbTuanNay.setLayout(pgbTuanNayLayout);
        pgbTuanNayLayout.setHorizontalGroup(
            pgbTuanNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 99, Short.MAX_VALUE)
        );
        pgbTuanNayLayout.setVerticalGroup(
            pgbTuanNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlNguoiDung2Layout = new javax.swing.GroupLayout(pnlNguoiDung2);
        pnlNguoiDung2.setLayout(pnlNguoiDung2Layout);
        pnlNguoiDung2Layout.setHorizontalGroup(
            pnlNguoiDung2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiDung2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(pgbTuanNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlNguoiDung2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiDung2)
                    .addComponent(lblSoLuongDoanhThuTuanNay))
                .addContainerGap())
        );
        pnlNguoiDung2Layout.setVerticalGroup(
            pnlNguoiDung2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiDung2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblNguoiDung2)
                .addGap(18, 18, 18)
                .addComponent(lblSoLuongDoanhThuTuanNay, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNguoiDung2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pgbTuanNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlNguoiDung2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, -1, -1));

        pnlNguoiDung3.setBackground(new java.awt.Color(238, 243, 247));
        pnlNguoiDung3.setForeground(new java.awt.Color(238, 243, 247));
        pnlNguoiDung3.setPreferredSize(new java.awt.Dimension(279, 120));

        lblSoLuongDoanhThuThangNay.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 22)); // NOI18N
        lblSoLuongDoanhThuThangNay.setForeground(new java.awt.Color(0, 52, 102));
        lblSoLuongDoanhThuThangNay.setText("5.000.000.000");

        lblNguoiDung3.setFont(new java.awt.Font("#9Slide03 Source Sans Pro", 0, 18)); // NOI18N
        lblNguoiDung3.setForeground(new java.awt.Color(24, 24, 24));
        lblNguoiDung3.setText("Tháng này");

        pgbThangNay.setBackground(new java.awt.Color(255, 255, 255));
        pgbThangNay.setForeground(new java.awt.Color(51, 255, 102));

        javax.swing.GroupLayout pgbThangNayLayout = new javax.swing.GroupLayout(pgbThangNay);
        pgbThangNay.setLayout(pgbThangNayLayout);
        pgbThangNayLayout.setHorizontalGroup(
            pgbThangNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 99, Short.MAX_VALUE)
        );
        pgbThangNayLayout.setVerticalGroup(
            pgbThangNayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlNguoiDung3Layout = new javax.swing.GroupLayout(pnlNguoiDung3);
        pnlNguoiDung3.setLayout(pnlNguoiDung3Layout);
        pnlNguoiDung3Layout.setHorizontalGroup(
            pnlNguoiDung3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiDung3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(pgbThangNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlNguoiDung3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiDung3)
                    .addComponent(lblSoLuongDoanhThuThangNay))
                .addContainerGap())
        );
        pnlNguoiDung3Layout.setVerticalGroup(
            pnlNguoiDung3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiDung3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblNguoiDung3)
                .addGap(18, 18, 18)
                .addComponent(lblSoLuongDoanhThuThangNay, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNguoiDung3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pgbThangNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlNguoiDung3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void dateTuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateTuPropertyChange
        dateDen.setDate(dateTu.getDate());
        dateDen.setMinSelectableDate(dateTu.getDate());
    }//GEN-LAST:event_dateTuPropertyChange

    private void dateNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayPropertyChange
        int doanhThu=DoanhThuNgay(dateNgay.getDate());
        lblDoanhThuNgay.setText(changeCurrency(Integer.toString(doanhThu))+"đ");
    }//GEN-LAST:event_dateNgayPropertyChange

    private void dateDenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDenPropertyChange
        int doanhThu=DoanhThuTuDen(dateTu.getDate(),dateDen.getDate());
        lblDoanhThuTuDen.setText(changeCurrency(Integer.toString(doanhThu))+"đ");
    }//GEN-LAST:event_dateDenPropertyChange

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        try {
            String path = SweetFileChooser.ExcelFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            DoanhThuQuyExcel.ExportExcelFile(path,DataDoanhThu.DoanhThuQuyExcel(cbxNamQuy.getSelectedItem().toString()) );
            SweetAlert.showSweetAlert(new JFrame(), "Hoàn tất", "Xuất dữ liệu thành công", 0);
        } catch (IOException ex) {
            Logger.getLogger(DoanhThuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
       try {
           String path = SweetFileChooser.ExcelFileChooser();
            if(path==null){
                SweetAlert.showSweetAlert(new JFrame(), "Lỗi", "Vui lòng chọn file phù hợp", 1);
                return;
            }
            DoanhThuThangExcel.ExportExcelFile(path, DataDoanhThu.DoanhThuNamExcel(cbxNam.getSelectedItem().toString()));
            SweetAlert.showSweetAlert(new JFrame(), "Hoàn tất", "Xuất dữ liệu thành công", 0);
       } catch (IOException ex) {
            Logger.getLogger(DoanhThuJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel20MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateDen;
    private com.toedter.calendar.JDateChooser dateNgay;
    private com.toedter.calendar.JDateChooser dateTu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblChiNam;
    private javax.swing.JLabel lblChiNamQuy;
    private javax.swing.JLabel lblChiNamThang;
    private javax.swing.JLabel lblDoanhThuNam;
    private javax.swing.JLabel lblDoanhThuNamQuy;
    private javax.swing.JLabel lblDoanhThuNamThang;
    private javax.swing.JLabel lblDoanhThuNgay;
    private javax.swing.JLabel lblDoanhThuTuDen;
    private javax.swing.JLabel lblLoiNhuanNam;
    private javax.swing.JLabel lblLoiNhuanNamQuy;
    private javax.swing.JLabel lblLoiNhuanNamThang;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblNgay1;
    private javax.swing.JLabel lblNgay3;
    private javax.swing.JLabel lblNgay4;
    private javax.swing.JLabel lblNgay5;
    private javax.swing.JLabel lblNgay6;
    private javax.swing.JLabel lblNgay7;
    private javax.swing.JLabel lblNgay8;
    private javax.swing.JLabel lblNguoiDung1;
    private javax.swing.JLabel lblNguoiDung2;
    private javax.swing.JLabel lblNguoiDung3;
    private javax.swing.JLabel lblSoLuongDoanhThuHomNay;
    private javax.swing.JLabel lblSoLuongDoanhThuThangNay;
    private javax.swing.JLabel lblSoLuongDoanhThuTuanNay;
    private GUI.ProgressBarJPanel pgbHomNay;
    private GUI.ProgressBarJPanel pgbThangNay;
    private GUI.ProgressBarJPanel pgbTuanNay;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JPanel pnlNam;
    private javax.swing.JPanel pnlNamQuy;
    private javax.swing.JPanel pnlNamThang;
    private GUI.Rounded pnlNguoiDung1;
    private GUI.Rounded pnlNguoiDung2;
    private GUI.Rounded pnlNguoiDung3;
    private javax.swing.JPanel pnlQuy;
    private GUI.Rounded pnlShowChart;
    private javax.swing.JPanel pnlThang;
    private GUI.Rounded pnlThongKe;
    private javax.swing.JPanel pnlXuatExcelNam;
    private javax.swing.JPanel pnlXuatExcelNamQuy;
    // End of variables declaration//GEN-END:variables
}
