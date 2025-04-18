/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataThongKe;

import GUI.Sweet.SweetComboBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static quanlycuahangsach.DateTime.DateStringToTimestamp;
import static quanlycuahangsach.DateTime.TimestampToDateString;

/**
 *
 * @author HUUNHAN
 */
public class DataDoanhThu {
    public static String[] getTrangThaiDonHang(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.get(i).getTrangThai();
        }
        return temp;
    }
    public static String[] getNgayXuatDonHang(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.get(i).getNgayXuat();
        }
        return temp;
    }    
    public static String[] getTongTienDonHang(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.get(i).getTongTien();
        }
        return temp;
    }
    public static String[] getLuongNhanVien(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.NhanVienBUS.NhanVienList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.NhanVienBUS.NhanVienList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.NhanVienBUS.NhanVienList.get(i).getLuong();
        }
        return temp;
    }
    public static String[] getNgayNhapPhieuNhap(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.PhieuNhapBUS.PhieuNhapList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.PhieuNhapBUS.PhieuNhapList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.PhieuNhapBUS.PhieuNhapList.get(i).getNgayNhap();
        }
        return temp;
    }
    public static String[] getTongTienPhieuNhap(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.PhieuNhapBUS.PhieuNhapList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.PhieuNhapBUS.PhieuNhapList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.PhieuNhapBUS.PhieuNhapList.get(i).getTongTien();
        }
        return temp;
    }
    public static String[] getTrangThaiNhanVien(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.NhanVienBUS.NhanVienList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.NhanVienBUS.NhanVienList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.NhanVienBUS.NhanVienList.get(i).getTrangThai();
       }
        return temp;
    }
    
    
    public static int DoanhThuNgay(Date dateNgay){
        int doanhThu = 0;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strNgay = dateFormat.format(dateNgay);
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] tongTienDonHang = getTongTienDonHang();
        String[] trangthai = getTrangThaiDonHang();
        for(int i=0;i<ngayXuatDonHang.length;i++){
            if(trangthai[i].equals("1")){
                if(TimestampToDateString(ngayXuatDonHang[i],1).equals(strNgay)){
                    doanhThu=doanhThu+Integer.parseInt(tongTienDonHang[i]);
                }
            }
        }
        return doanhThu;
    }
    public static int DoanhThuTuDen(Date dateTu,Date dateDen){
        int doanhThu = 0;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDateTu= dateFormat.format(dateTu);
        String strDateDen= dateFormat.format(dateDen);
        
        long longDateTu = Long.parseLong(DateStringToTimestamp(strDateTu+" 00:00:01"));
        long longDateDen = Long.parseLong(DateStringToTimestamp(strDateDen+" 23:59:59"));
        
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] tongTienDonHang = getTongTienDonHang();
        String[] trangthai = getTrangThaiDonHang();
        for(int i=0;i<ngayXuatDonHang.length;i++){
            if(trangthai[i].equals("1")){
                if(Long.parseLong(ngayXuatDonHang[i]) >= longDateTu && Long.parseLong(ngayXuatDonHang[i]) <= longDateDen){
                    doanhThu=doanhThu+Integer.parseInt(tongTienDonHang[i]);
                }
            }
        }
        return doanhThu;
    }
    public static int[] DoanhThuThang(SweetComboBox cbxNam,SweetComboBox cbxThang){
        int[] doanhThu={0,0};
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] tongTienDonHang = getTongTienDonHang();
        String[] luongNhanVien = getLuongNhanVien();
        String[] trangThaiNhanVien = getTrangThaiNhanVien();
        String[] ngayNhapPhieuNhap = getNgayNhapPhieuNhap();
        String[] tongTienPhieuNhap= getTongTienPhieuNhap();
        String[] trangthai = getTrangThaiDonHang();
        String[] temp={"0","0","0","0","0"};
        for(int i=0;i<ngayXuatDonHang.length;i++)
        {
            String year = TimestampToDateString(ngayXuatDonHang[i],1).split("/")[2];
            String month = TimestampToDateString(ngayXuatDonHang[i], 1).split("/")[1];
            if(trangthai[i].equals("1")){
                if(cbxNam.getSelectedItem().toString().equals(year) && cbxThang.getSelectedItem().toString().equals(month))
                {
                    doanhThu[0]=doanhThu[0]+Integer.parseInt(tongTienDonHang[i]);
                }
            }    
        }
        for(int i=0;i<trangThaiNhanVien.length;i++){
            if(trangThaiNhanVien[i].equals("1")){
                doanhThu[1]=doanhThu[1]+Integer.parseInt(luongNhanVien[i]);
            }
        }
        for(int i=0;i<ngayNhapPhieuNhap.length;i++){
            String yearPhieuNhap = TimestampToDateString(ngayNhapPhieuNhap[i],1).split("/")[2];
            String monthPhieuNhap = TimestampToDateString(ngayNhapPhieuNhap[i], 1).split("/")[1];
            if(cbxNam.getSelectedItem().toString().equals(yearPhieuNhap) && cbxThang.getSelectedItem().toString().equals(monthPhieuNhap)){
                doanhThu[1]=doanhThu[1]+Integer.parseInt(tongTienPhieuNhap[i]);
            }
        }
        return doanhThu;
    }  
    public static int[] DoanhThuQuy(SweetComboBox cbxNam,SweetComboBox cbxQuy){
        int[] doanhThu ={0,0};
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] tongTienDonHang = getTongTienDonHang();
        String[] luongNhanVien = getLuongNhanVien();
        String[] trangThaiNhanVien = getTrangThaiNhanVien();
        String[] ngayNhapPhieuNhap = getNgayNhapPhieuNhap();
        String[] tongTienPhieuNhap= getTongTienPhieuNhap();
        String[] trangthai = getTrangThaiDonHang();
        switch(cbxQuy.getSelectedItem().toString()){
            case "1":
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/01/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+cbxNam.getSelectedItem().toString()+" 23:59:59"))){
                            doanhThu[0]=doanhThu[0]+Integer.parseInt(tongTienDonHang[i]);
                        } 
                    }
                }
                for(int i=0;i<trangThaiNhanVien.length;i++)
                {
                    if(trangThaiNhanVien[i].equals("1"))
                    {
                        doanhThu[1]=doanhThu[1]+(Integer.parseInt(luongNhanVien[i])*3);
                    }
                }
                for(int i=0;i<ngayNhapPhieuNhap.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/01/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayNhapPhieuNhap[i]) && Long.parseLong(ngayNhapPhieuNhap[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+cbxNam.getSelectedItem().toString()+" 23:59:59"))){
                        doanhThu[1]=doanhThu[1]+Integer.parseInt(tongTienPhieuNhap[i]);
                    }
                }
                break;
            case "2":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/04/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) ){
                            doanhThu[0]=doanhThu[0]+Integer.parseInt(tongTienDonHang[i]);
                        } 
                    }
                }
                for(int i=0;i<trangThaiNhanVien.length;i++)
                {
                    if(trangThaiNhanVien[i].equals("1"))
                    {
                        doanhThu[1]=doanhThu[1]+(Integer.parseInt(luongNhanVien[i])*3);
                    }
                }
                for(int i=0;i<ngayNhapPhieuNhap.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/04/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayNhapPhieuNhap[i]) && Long.parseLong(ngayNhapPhieuNhap[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+cbxNam.getSelectedItem().toString()+" 23:59:59"))){
                        doanhThu[1]=doanhThu[1]+Integer.parseInt(tongTienPhieuNhap[i]);
                    }
                }               
                break;
                
            case "3":
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/07/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) ){
                            doanhThu[0]=doanhThu[0]+Integer.parseInt(tongTienDonHang[i]);
                        } 
                
                    }
                }
                for(int i=0;i<trangThaiNhanVien.length;i++)
                {
                    if(trangThaiNhanVien[i].equals("1"))
                    {
                        doanhThu[1]=doanhThu[1]+(Integer.parseInt(luongNhanVien[i])*3);
                    }
                }
                for(int i=0;i<ngayNhapPhieuNhap.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/07/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayNhapPhieuNhap[i]) && Long.parseLong(ngayNhapPhieuNhap[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+cbxNam.getSelectedItem().toString()+" 23:59:59"))){
                        doanhThu[1]=doanhThu[1]+Integer.parseInt(tongTienPhieuNhap[i]);
                    }
                }
                break;
            case "4":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/10/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) ){
                            doanhThu[0]=doanhThu[0]+Integer.parseInt(tongTienDonHang[i]);
                        }
                    }
                }
                for(int i=0;i<trangThaiNhanVien.length;i++)
                {
                    if(trangThaiNhanVien[i].equals("1"))
                    {
                        doanhThu[1]=doanhThu[1]+(Integer.parseInt(luongNhanVien[i])*3);
                    }
                }
                for(int i=0;i<ngayNhapPhieuNhap.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/10/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayNhapPhieuNhap[i]) && Long.parseLong(ngayNhapPhieuNhap[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+cbxNam.getSelectedItem().toString()+" 23:59:59"))){
                        doanhThu[1]=doanhThu[1]+Integer.parseInt(tongTienPhieuNhap[i]);
                    }
                }
                break;
        }
        return doanhThu;
    }
    public static int[] DoanhThuNam(SweetComboBox cbxNam){
        int[] doanhThu= {0,0};
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] tongTienDonHang = getTongTienDonHang();
        String[] luongNhanVien = getLuongNhanVien();
        String[] trangThaiNhanVien = getTrangThaiNhanVien();
        String[] ngayNhapPhieuNhap = getNgayNhapPhieuNhap();
        String[] tongTienPhieuNhap= getTongTienPhieuNhap();
        String[] trangthai = getTrangThaiDonHang();
        for(int i=0;i<ngayXuatDonHang.length;i++){
            if(trangthai[i].equals("1")){
                if(TimestampToDateString(ngayXuatDonHang[i],1).split("/")[2].equals(cbxNam.getSelectedItem().toString())){
                    doanhThu[0]=doanhThu[0]+Integer.parseInt(tongTienDonHang[i]);
                }
            }
        }
        for(int i=0;i<trangThaiNhanVien.length;i++){
            if(trangThaiNhanVien[i].equals("1")){
                doanhThu[1]=doanhThu[1]+(Integer.parseInt(luongNhanVien[i])*12);
            }
        }
        for(int i=0;i<ngayNhapPhieuNhap.length;i++){
            if(TimestampToDateString(ngayNhapPhieuNhap[i],1).split("/")[2].equals(cbxNam.getSelectedItem().toString())){
                doanhThu[1]=doanhThu[1]+Integer.parseInt(tongTienPhieuNhap[i]);
            }
        }
        return doanhThu;
    }
    
    public static int DoanhThuNgayHomNay(){
        int doanhThuNgay=0;
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] tongTienDonHang = getTongTienDonHang();
        String[] trangthai = getTrangThaiDonHang();
        DateFormat dFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        
        Date d = new Date();
        for(int i=0;i<ngayXuatDonHang.length;i++)
        {
            if(trangthai[i].equals("1")){
                if(Long.parseLong(DateStringToTimestamp(dateFormat.format(d)+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <= Long.parseLong(DateStringToTimestamp(dFormat.format(d))))
                {
                    doanhThuNgay=doanhThuNgay+Integer.parseInt(tongTienDonHang[i]);
                }
            }
        }
            
        return doanhThuNgay;
    }
    public static int DoanhThuTuanNay(){
        int doanhThuTuan=0;
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] tongTienDonHang = getTongTienDonHang();
        String[] trangthai = getTrangThaiDonHang();
        DateFormat dFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dayFormat = new SimpleDateFormat("EE");
        Date d = new Date();
        switch(dayFormat.format(d))
        {
            case "Mon":
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp(dateFormat.format(d)+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <= Long.parseLong(DateStringToTimestamp(dFormat.format(d))))
                        {
                            doanhThuTuan=doanhThuTuan+Integer.parseInt(tongTienDonHang[i]);
                        }
                    }
                }
                break;
            case "Tue":
                long longThu3 = Long.parseLong(DateStringToTimestamp(dateFormat.format(d)))-(24*60*60);
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(longThu3 <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <= Long.parseLong(DateStringToTimestamp(dFormat.format(d))) )
                        {
                            doanhThuTuan=doanhThuTuan+Integer.parseInt(tongTienDonHang[i]);
                        }
                    }
                }
                break;
            case "Wed":
                long longThu4 = Long.parseLong(DateStringToTimestamp(dateFormat.format(d)))-(2*24*60*60);
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(longThu4 <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <= Long.parseLong(DateStringToTimestamp(dFormat.format(d))) )
                        {
                            doanhThuTuan=doanhThuTuan+Integer.parseInt(tongTienDonHang[i]);
                        }
                    }
                }
                break;
            case "Thu":
                long longThu5 = Long.parseLong(DateStringToTimestamp(dateFormat.format(d)))-(3*24*60*60);
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(longThu5 <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <= Long.parseLong(DateStringToTimestamp(dFormat.format(d))) )
                        {
                            doanhThuTuan=doanhThuTuan+Integer.parseInt(tongTienDonHang[i]);
                        }
                    }
                }
                break;
            case "Fri":
                long longThu6 = Long.parseLong(DateStringToTimestamp(dateFormat.format(d)))-(4*24*60*60);
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(longThu6 <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <= Long.parseLong(DateStringToTimestamp(dFormat.format(d))) )
                        {
                            doanhThuTuan=doanhThuTuan+Integer.parseInt(tongTienDonHang[i]);
                        }
                    }
                }
                break;
            case "Sat":
                long longThu7 = Long.parseLong(DateStringToTimestamp(dateFormat.format(d)))-(5*24*60*60);
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(longThu7 <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <= Long.parseLong(DateStringToTimestamp(dFormat.format(d))) )
                        {
                            doanhThuTuan=doanhThuTuan+Integer.parseInt(tongTienDonHang[i]);
                        }
                    }
                }
                break;
            case "Sun":
                long longCn = Long.parseLong(DateStringToTimestamp(dateFormat.format(d)))-(6*24*60*60);
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){    
                        if(longCn <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <= Long.parseLong(DateStringToTimestamp(dFormat.format(d))) )
                        {
                            doanhThuTuan=doanhThuTuan+Integer.parseInt(tongTienDonHang[i]);
                        }
                    }
                }    
                break;
        }
        return doanhThuTuan;
    }
    public static int DoanhThuThangNay(){
        int doanhThuThang = 0;
        String[] ngayXuatDonHang= getNgayXuatDonHang();
        String[] tongTienDonHang = getTongTienDonHang();
        String[] trangthai = getTrangThaiDonHang();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        
        String ts= DateStringToTimestamp(dateFormat.format(d));
        
        
        for(int i=0;i<ngayXuatDonHang.length;i++)
        {
            String year = TimestampToDateString(ngayXuatDonHang[i],1).split("/")[2];
            String month = TimestampToDateString(ngayXuatDonHang[i], 1).split("/")[1];
            if(trangthai[i].equals("1")){
                if(TimestampToDateString(ts,1).split("/")[2].equals(year) && TimestampToDateString(ts,1).split("/")[1].equals(month)){
                    doanhThuThang=doanhThuThang+Integer.parseInt(tongTienDonHang[i]);
                }
            }
        }
        return doanhThuThang;
    }
    
    /*process bar doanh thu*/
    public static float[] processBar(){
        float DoanhThuNgay=500000000;
        float DoanhThuTuan=350000000;
        float DoanhThuThang=1500000000;
        float[] phanTram= {0,0,0};
        phanTram[0] =((float)DoanhThuNgayHomNay()*100)/DoanhThuNgay;
        phanTram[1] =((float)DoanhThuTuanNay()*100)/DoanhThuTuan;
        phanTram[2] =((float)DoanhThuThangNay()*100)/DoanhThuThang;
        for(int i=0;i<phanTram.length;i++){
            if(phanTram[i]>100)
            {
                phanTram[i]=100;
            }
        }
        return phanTram;
    }
    
    
    
    
    /*Xuat ex*/
    public static int[] DoanhThuQuy(String Nam,String Quy){
        int[] doanhThu ={0,0};
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] tongTienDonHang = getTongTienDonHang();
        String[] luongNhanVien = getLuongNhanVien();
        String[] trangThaiNhanVien = getTrangThaiNhanVien();
        String[] ngayNhapPhieuNhap = getNgayNhapPhieuNhap();
        String[] tongTienPhieuNhap= getTongTienPhieuNhap();
        switch(Quy){
            case "1":
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                   if(Long.parseLong(DateStringToTimestamp("01/01/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+Nam.toString()+" 23:59:59"))){
                       doanhThu[0]=doanhThu[0]+Integer.parseInt(tongTienDonHang[i]);
                   } 
                }
                for(int i=0;i<trangThaiNhanVien.length;i++)
                {
                    if(trangThaiNhanVien[i].equals("1"))
                    {
                        doanhThu[1]=doanhThu[1]+(Integer.parseInt(luongNhanVien[i])*3);
                    }
                }
                for(int i=0;i<ngayNhapPhieuNhap.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/01/"+Nam+" 00:00:01")) <= Long.parseLong(ngayNhapPhieuNhap[i]) && Long.parseLong(ngayNhapPhieuNhap[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+Nam+" 23:59:59"))){
                        doanhThu[1]=doanhThu[1]+Integer.parseInt(tongTienPhieuNhap[i]);
                    }
                }
                break;
            case "2":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                   if(Long.parseLong(DateStringToTimestamp("01/04/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+Nam+" 23:59:59")) ){
                       doanhThu[0]=doanhThu[0]+Integer.parseInt(tongTienDonHang[i]);
                   } 
                }
                for(int i=0;i<trangThaiNhanVien.length;i++)
                {
                    if(trangThaiNhanVien[i].equals("1"))
                    {
                        doanhThu[1]=doanhThu[1]+(Integer.parseInt(luongNhanVien[i])*3);
                    }
                }
                for(int i=0;i<ngayNhapPhieuNhap.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/04/"+Nam+" 00:00:01")) <= Long.parseLong(ngayNhapPhieuNhap[i]) && Long.parseLong(ngayNhapPhieuNhap[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+Nam+" 23:59:59"))){
                        doanhThu[1]=doanhThu[1]+Integer.parseInt(tongTienPhieuNhap[i]);
                    }
                }               
                break;
                
            case "3":
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                   if(Long.parseLong(DateStringToTimestamp("01/07/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+Nam+" 23:59:59")) ){
                       doanhThu[0]=doanhThu[0]+Integer.parseInt(tongTienDonHang[i]);
                   } 
                }
                for(int i=0;i<trangThaiNhanVien.length;i++)
                {
                    if(trangThaiNhanVien[i].equals("1"))
                    {
                        doanhThu[1]=doanhThu[1]+(Integer.parseInt(luongNhanVien[i])*3);
                    }
                }
                for(int i=0;i<ngayNhapPhieuNhap.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/07/"+Nam+" 00:00:01")) <= Long.parseLong(ngayNhapPhieuNhap[i]) && Long.parseLong(ngayNhapPhieuNhap[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+Nam+" 23:59:59"))){
                        doanhThu[1]=doanhThu[1]+Integer.parseInt(tongTienPhieuNhap[i]);
                    }
                }
                break;
            case "4":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                   if(Long.parseLong(DateStringToTimestamp("01/10/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+Nam+" 23:59:59")) ){
                       doanhThu[0]=doanhThu[0]+Integer.parseInt(tongTienDonHang[i]);
                   } 
                }
                for(int i=0;i<trangThaiNhanVien.length;i++)
                {
                    if(trangThaiNhanVien[i].equals("1"))
                    {
                        doanhThu[1]=doanhThu[1]+(Integer.parseInt(luongNhanVien[i])*3);
                    }
                }
                for(int i=0;i<ngayNhapPhieuNhap.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/10/"+Nam.toString()+" 00:00:01")) <= Long.parseLong(ngayNhapPhieuNhap[i]) && Long.parseLong(ngayNhapPhieuNhap[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+Nam+" 23:59:59"))){
                        doanhThu[1]=doanhThu[1]+Integer.parseInt(tongTienPhieuNhap[i]);
                    }
                }
                break;
        }
        return doanhThu;
    }
    public static ArrayList<String[]> DoanhThuQuyExcel(String nam)
    {
        ArrayList<String[]> result = new ArrayList<>();
        String[] quy = {"1","2","3","4"};
        for(int i=0;i<quy.length;i++)
        {
            int[] temp = DoanhThuQuy(nam,quy[i]);
            int loinhuan =temp[0]-temp[1];
            if (loinhuan<0){
                loinhuan=0;
            }
            String[] tungquy = {nam,quy[i],temp[0]+"",temp[1]+"",loinhuan+""};
            result.add(tungquy);
        }
        return result;
    }
    public static int[] DoanhThuNam( String Nam, String Thang )
    {
        int[] doanhThu={0,0};
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] tongTienDonHang = getTongTienDonHang();
        String[] luongNhanVien = getLuongNhanVien();
        String[] trangThaiNhanVien = getTrangThaiNhanVien();
        String[] ngayNhapPhieuNhap = getNgayNhapPhieuNhap();
        String[] tongTienPhieuNhap= getTongTienPhieuNhap();
        String[] temp={"0","0","0","0","0"};
        for(int i=0;i<ngayXuatDonHang.length;i++)
        {
            String year = TimestampToDateString(ngayXuatDonHang[i],1).split("/")[2];
            String month = TimestampToDateString(ngayXuatDonHang[i], 1).split("/")[1];
            if(Nam.equals(year) && Thang.equals(month))
            {
                doanhThu[0]=doanhThu[0]+Integer.parseInt(tongTienDonHang[i]);
            }
        }
        for(int i=0;i<trangThaiNhanVien.length;i++){
            if(trangThaiNhanVien[i].equals("1")){
                doanhThu[1]=doanhThu[1]+Integer.parseInt(luongNhanVien[i]);
            }
        }
        for(int i=0;i<ngayNhapPhieuNhap.length;i++){
            String yearPhieuNhap = TimestampToDateString(ngayNhapPhieuNhap[i],1).split("/")[2];
            String monthPhieuNhap = TimestampToDateString(ngayNhapPhieuNhap[i], 1).split("/")[1];
            if(Nam.equals(yearPhieuNhap) && Thang.equals(monthPhieuNhap)){
                doanhThu[1]=doanhThu[1]+Integer.parseInt(tongTienPhieuNhap[i]);
            }
        }
        return doanhThu;
    } 
    public static ArrayList<String[]> DoanhThuNamExcel( String nam )
    {
        ArrayList<String[]> result = new ArrayList<>();
        String[] thang = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        for(int i=0;i<thang.length;i++)
        {
            int[] temp = DoanhThuNam(nam,thang[i]);
            int loinhuan =temp[0]-temp[1];
            if (loinhuan<0){
                loinhuan=0;
            }
            String[] tungquy = {nam,thang[i],temp[0]+"",temp[1]+"",loinhuan+""};
            result.add(tungquy);
        }
        return result;
    }
    
}
