    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataThongKe;

import BUS.DonHangBUS;
import DTO.DonHangDTO;
import GUI.Sweet.SweetComboBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;

import static quanlycuahangsach.DateTime.DateStringToTimestamp;
import static quanlycuahangsach.DateTime.TimestampToDateString;

/**
 *
 * @author HUUNHAN
 */
public class DataThongKeSanPham {
    public static String[] getTrangThaiDonHang(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.get(i).getTrangThai();
        }
        return temp;
    }
    public static String[] getMaSPChiTietDonHang(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.size();i++){
            temp[i]= quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.get(i).getMaSanPham();
        }
        return temp;
    }
    public static ArrayList<Object> getNgayXuat(){
        ArrayList<Object> SoLuongSanPhamVaoNgay = new ArrayList<>();
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size();i++){
            SoLuongSanPhamVaoNgay.add(BUS.DonHangBUS.getSoLuongVaoNgay(quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.get(i).getMaDonHang()));
        }
        return SoLuongSanPhamVaoNgay;
    }
    public static ArrayList<Object> getSanPhamVaoNgay(){
        ArrayList<Object> SoLuongSanPhamVaoNgay = new ArrayList<>();
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size();i++){
            SoLuongSanPhamVaoNgay.add(BUS.DonHangBUS.getSanPhamVaoNgay(quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.get(i).getMaDonHang()));
        }
        return SoLuongSanPhamVaoNgay;
    }
    public static String[] getMaSanPham(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.SachBUS.SachList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.SachBUS.SachList.size();i++){
            temp[i] = quanlycuahangsach.quanlycuahangsach.SachBUS.SachList.get(i).getMaSanPham();
        }
        return temp;
    }
    public static int[] DataChartSanPhamNamTruoc(){
        int[] data={0,0,0,0,0,0,0,0,0,0,0,0};
        Date d = new Date();
        DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        String strYearPresent = DateStringToTimestamp(dateFormat.format(d));
        ArrayList<Object> SoLuongSanPhamTheoThoiGian = getSanPhamVaoNgay();
        SoLuongSanPhamTheoThoiGian.forEach(SoLuongSanPhamTheoDonHang ->{
            ((ArrayList<String[]>) SoLuongSanPhamTheoDonHang).forEach(DuLieu ->{
                if(DuLieu[1].equals("1")){
                if(TimestampToDateString(DuLieu[0], 1).split("/")[2].equals(Integer.toString(Integer.parseInt(TimestampToDateString(strYearPresent,1).split("/")[2])-1))){
                    switch(TimestampToDateString(DuLieu[0],1).split("/")[1]){
                        case "01" :
                            data[0]=data[0]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "02" :
                            data[1]=data[1]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "03":
                            data[2]=data[2]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "04":
                            data[3]=data[3]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "05":
                            data[4]=data[4]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "06":
                            data[5]=data[5]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "07":
                            data[6]=data[6]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "08":
                            data[7]=data[7]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "09":
                            data[8]=data[8]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "10":
                            data[9]=data[9]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "11":
                            data[10]=data[10]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "12":
                            data[11]=data[11]+Integer.parseInt(DuLieu[3]);
                            break;    
                    }
                }
                }
            });
        });
        return data;
    }
    public static int[] DataChartSanPhamHienTai(){
        int[] data={0,0,0,0,0,0,0,0,0,0,0,0};
        Date d = new Date();
        DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        String strYearPresent = DateStringToTimestamp(dateFormat.format(d));
        ArrayList<Object> SoLuongSanPhamTheoThoiGian = getSanPhamVaoNgay();
        SoLuongSanPhamTheoThoiGian.forEach(SoLuongSanPhamTheoDonHang ->{
            ((ArrayList<String[]>) SoLuongSanPhamTheoDonHang).forEach(DuLieu ->{
                if(DuLieu[1].equals("1")){
                if(TimestampToDateString(DuLieu[0], 1).split("/")[2].equals(TimestampToDateString(strYearPresent,1).split("/")[2])){
                    switch(TimestampToDateString(DuLieu[0],1).split("/")[1]){
                        case "01" :
                            data[0]=data[0]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "02" :
                            data[1]=data[1]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "03":
                            data[2]=data[2]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "04":
                            data[3]=data[3]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "05":
                            data[4]=data[4]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "06":
                            data[5]=data[5]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "07":
                            data[6]=data[6]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "08":
                            data[7]=data[7]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "09":
                            data[8]=data[8]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "10":
                            data[9]=data[9]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "11":
                            data[10]=data[10]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "12":
                            data[11]=data[11]+Integer.parseInt(DuLieu[3]);
                            break;    
                    }
                }
                }
            });
        });
        return data;
    }
    public static int[] DataChartSanPhamHienTaiMoiSanPham(String MaSanPham){
        int[] data={0,0,0,0,0,0,0,0,0,0,0,0};
        Date d = new Date();
        DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        String strYearPresent = DateStringToTimestamp(dateFormat.format(d));
        ArrayList<Object> SoLuongSanPhamTheoThoiGian = getSanPhamVaoNgay();
        SoLuongSanPhamTheoThoiGian.forEach(SoLuongSanPhamTheoDonHang ->{
            ((ArrayList<String[]>) SoLuongSanPhamTheoDonHang).forEach(DuLieu ->{
                if(DuLieu[1].equals("1")){
                if(TimestampToDateString(DuLieu[0], 1).split("/")[2].equals(TimestampToDateString(strYearPresent,1).split("/")[2])){
                    if(DuLieu[2].equals(MaSanPham)){
                    switch(TimestampToDateString(DuLieu[0],1).split("/")[1]){
                        case "01" :
                            data[0]=data[0]+Integer.parseInt(DuLieu[3]);
                            break;
                            
                        case "02" :
                            data[1]=data[1]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "03":
                            data[2]=data[2]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "04":
                            data[3]=data[3]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "05":
                            data[4]=data[4]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "06":
                            data[5]=data[5]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "07":
                            data[6]=data[6]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "08":
                            data[7]=data[7]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "09":
                            data[8]=data[8]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "10":
                            data[9]=data[9]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "11":
                            data[10]=data[10]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "12":
                            data[11]=data[11]+Integer.parseInt(DuLieu[3]);
                            break;    
                    }
                    }
                }
                }
            });
        });
        return data;
    }
    public static int[] DataChartSanPhamNamTruocMoiSanPham(String MaSanPham){
        int[] data={0,0,0,0,0,0,0,0,0,0,0,0};
        Date d = new Date();
        DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        String strYearPresent = DateStringToTimestamp(dateFormat.format(d));
        ArrayList<Object> SoLuongSanPhamTheoThoiGian = getSanPhamVaoNgay();
        SoLuongSanPhamTheoThoiGian.forEach(SoLuongSanPhamTheoDonHang ->{
            ((ArrayList<String[]>) SoLuongSanPhamTheoDonHang).forEach(DuLieu ->{
                if(DuLieu[0].equals("1")){
                if(TimestampToDateString(DuLieu[0], 1).split("/")[2].equals(Integer.toString(Integer.parseInt(TimestampToDateString(strYearPresent,1).split("/")[2])-1))){
                    if(DuLieu[2].equals(MaSanPham)){
                    switch(TimestampToDateString(DuLieu[0],1).split("/")[1]){
                        case "01" :
                            data[0]=data[0]+Integer.parseInt(DuLieu[3]);
                            break;
                            
                        case "02" :
                            data[1]=data[1]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "03":
                            data[2]=data[2]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "04":
                            data[3]=data[3]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "05":
                            data[4]=data[4]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "06":
                            data[5]=data[5]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "07":
                            data[6]=data[6]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "08":
                            data[7]=data[7]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "09":
                            data[8]=data[8]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "10":
                            data[9]=data[9]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "11":
                            data[10]=data[10]+Integer.parseInt(DuLieu[3]);
                            break;
                        case "12":
                            data[11]=data[11]+Integer.parseInt(DuLieu[3]);
                            break;    
                    }
                    }
                }
                }
            });
        });
        return data;
    }
    
    
    
    
    
    /*ThongKePanel*/
    public static String[] getNgayXuatDonHang(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.get(i).getNgayXuat();
        }
        return temp;
    } 
    public static String[] getMaDonHang(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.get(i).getMaDonHang();
        }
        return temp;
    }
    public static String[] getMaDonHangChiTietDH(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.get(i).getMaDonHang();
        }
        return temp;
    }
    public static String[] getSoLuong(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.size();i++){
            temp[i]=quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.get(i).getSoLuong();
        }
        return temp;
    }
    
    public  static int ThongKeSanPhamNgay(Date dateNgay){
        int thongKeSanPham = 0;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strNgay = dateFormat.format(dateNgay); 
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] maDonHangChiTietDH = getMaDonHangChiTietDH();
        String[] soLuong = getSoLuong();
        String[] trangthai =getTrangThaiDonHang();
        
        for(int i = 0;i<ngayXuatDonHang.length;i++){
            if(trangthai[i].equals("1")){
                if(TimestampToDateString(ngayXuatDonHang[i],1).equals(strNgay)){
                    for(int j =0;j<maDonHangChiTietDH.length;j++){
                        if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                            thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                        }
                    }
                }
            }
        }
        return thongKeSanPham;
    }
    public static int ThongKeSanPhamTuDen(Date dateTu,Date dateDen){
        int thongKeSanPham=0;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDateTu= dateFormat.format(dateTu);
        String strDateDen= dateFormat.format(dateDen);
        
        long longDateTu = Long.parseLong(DateStringToTimestamp(strDateTu+" 00:00:01"));
        long longDateDen = Long.parseLong(DateStringToTimestamp(strDateDen+" 23:59:59"));
        
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] maDonHangChiTietDH = getMaDonHangChiTietDH();
        String[] soLuong = getSoLuong();
        String[] trangthai = getTrangThaiDonHang();
        
        for(int i=0;i<ngayXuatDonHang.length;i++){
            if(trangthai[i].equals("1")){
                if( longDateTu <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <= longDateDen){
                    for(int j=0;j<maDonHangChiTietDH.length;j++){
                        if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                            thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                        }
                    }
                }
            }
        }
        return thongKeSanPham;
    }
    public static int ThongKeSanPhamThang(SweetComboBox cbxNam,SweetComboBox cbxThang){
        int thongKeSanPham = 0;
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] maDonHangChiTietDH = getMaDonHangChiTietDH();
        String[] soLuong = getSoLuong();
        String[] trangthai = getTrangThaiDonHang();
        
        for(int i=0;i<ngayXuatDonHang.length;i++)
        {
            String year = TimestampToDateString(ngayXuatDonHang[i],1).split("/")[2];
            String month = TimestampToDateString(ngayXuatDonHang[i], 1).split("/")[1];
            if(trangthai[i].equals("1")){
                if(cbxNam.getSelectedItem().toString().equals(year) && cbxThang.getSelectedItem().toString().equals(month))
                {
                   for(int j=0;j<maDonHangChiTietDH.length;j++){
                        if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                            thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                        }
                    } 
                }
            }
        }
        
        return thongKeSanPham;
    }
    public static int ThongKeSanPhamQuy(SweetComboBox cbxNam,SweetComboBox cbxQuy){
        int thongKeSanPham = 0;
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] maDonHangChiTietDH = getMaDonHangChiTietDH();
        String[] soLuong = getSoLuong();
        String[] trangthai = getTrangThaiDonHang();
        switch(cbxQuy.getSelectedItem().toString()){
            case "1":
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/01/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                        {
                            for(int j=0;j<maDonHangChiTietDH.length;j++){
                                if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                                    thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                                }
                            }
                        }
                    }
                }
                break;
            case "2":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/04/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                        {
                           for(int j=0;j<maDonHangChiTietDH.length;j++){
                            if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                                thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                            }
                        }
                        }
                    }
                }
                break;
            case "3":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/07/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                        {
                            for(int j=0;j<maDonHangChiTietDH.length;j++){
                            if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                                thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                            }
                        }
                        }
                    }
                }
                break;
            case "4":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/07/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                        {
                            for(int j=0;j<maDonHangChiTietDH.length;j++){
                            if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                                thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                            }
                        }
                        }
                    }
                        
                }
                break;
        }
        return thongKeSanPham;
    }
    public static int ThongKeSanPhamNam(SweetComboBox cbxNam){
        int thongKeSanPham = 0;
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] maDonHangChiTietDH = getMaDonHangChiTietDH();
        String[] soLuong = getSoLuong();
        String[] trangthai =getTrangThaiDonHang();
        
        for(int i=0;i<ngayXuatDonHang.length;i++)
        {
            if(trangthai[i].equals("1")){
                if(TimestampToDateString(ngayXuatDonHang[i],1).split("/")[2].equals(cbxNam.getSelectedItem().toString()))
                {
                    for(int j=0;j<maDonHangChiTietDH.length;j++){
                        if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                            thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                        }
                    }
                }
            }
        }
        return thongKeSanPham;
    }
    public static int[] SanPhamDaBanThang(String MaSanPham){
        int[] DaBan = {0,0};
        Date d = new  Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = DateStringToTimestamp(dateFormat.format(d));
        String monthDate = TimestampToDateString(strDate,1).split("/")[1];     
        ArrayList<Object> SoLuongSanPhamTheoThoiGian = getSanPhamVaoNgay();
        SoLuongSanPhamTheoThoiGian.forEach(SoLuongDaBan ->{
            ((ArrayList<String[]>) SoLuongDaBan).forEach(DuLieu ->{
                if(DuLieu[1].equals("1")){
                    if(DuLieu[2].equals(MaSanPham)){
                        if(TimestampToDateString(DuLieu[0],1).split("/")[2].equals(TimestampToDateString(strDate,1).split("/")[2])){
                            if(TimestampToDateString(DuLieu[0],1).split("/")[1].equals(monthDate)){
                                DaBan[0] = DaBan[0]+Integer.parseInt(DuLieu[3]);
                            }
                            if(Integer.parseInt(TimestampToDateString(DuLieu[0],1).split("/")[1]) == (Integer.parseInt(monthDate)-1)){
                                DaBan[1] = DaBan[1]+Integer.parseInt(DuLieu[3]);
                            }
                        }

                    }
                }
            }); 
        }); 
        return DaBan;
    }
     public static int SanPhamDaBan(String MaSanPham){
        int DaBan=0;
        
        String[] maDonHang = getMaDonHang();
        String[] maDonHangChiTietDH = getMaDonHangChiTietDH();
        String[] soLuong = getSoLuong();
        String[] trangthai =getTrangThaiDonHang();
        String[] maSanPham = getMaSPChiTietDonHang();
        for(int i = 0;i<maDonHang.length;i++){
                    for(int j =0;j<maDonHangChiTietDH.length;j++){
                        if(trangthai[i].equals("1")){
                            if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                                if(maSanPham[j].equals(MaSanPham)){
                                    DaBan=DaBan+Integer.parseInt(soLuong[j]);
                                }
                            }
                        }

            }
        }
        return DaBan;
    } 
    
    
    /*Thong Ke Tung San Pham*/
    
    public static String[] getMaSanPhamCTDonHang(){
        String[] temp = new String[quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.size()];
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.size();i++){
            temp[i] = quanlycuahangsach.quanlycuahangsach.ChiTietDonHangBUS.ChiTietDonHangList.get(i).getMaSanPham();
        }
        return temp;
    }
    public static int[] SanPhamBanRaDoanhThuTuDen(String MaSanPham,Date dateTu,Date dateDen){
        int[] SoLuongVaDoanhThu = {0,0};
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDateTu= dateFormat.format(dateTu);
        String strDateDen= dateFormat.format(dateDen);
        
        long longDateTu = Long.parseLong(DateStringToTimestamp(strDateTu+" 00:00:01"));
        long longDateDen = Long.parseLong(DateStringToTimestamp(strDateDen+" 23:59:59"));
        
        ArrayList<Object> SoLuongSanPhamTheoThoiGian = getSanPhamVaoNgay();
        SoLuongSanPhamTheoThoiGian.forEach(SoLuongDaBan->{
            ((ArrayList<String[]>) SoLuongDaBan).forEach(DuLieu ->{
                if(DuLieu[1].equals("1")){
                    if(DuLieu[2].equals(MaSanPham)){
                        if(longDateTu <= Long.parseLong(DuLieu[0]) && Long.parseLong(DuLieu[0]) <= longDateDen){
                            SoLuongVaDoanhThu[0]=SoLuongVaDoanhThu[0]+Integer.parseInt(DuLieu[3]);
                        }
                    }
                }
            });
        });
        ArrayList<Object> SanPhamNgayDoanhThu = getSanPhamNgayDoanhThu();
        SanPhamNgayDoanhThu.forEach(SanPhamDoanhThu->{
            ((ArrayList<String[]>) SanPhamDoanhThu).forEach(DuLieuDoanhThu ->{
                if(DuLieuDoanhThu[1].equals("1")){
                    if(DuLieuDoanhThu[2].equals(MaSanPham)){
                        if(longDateTu <= Long.parseLong(DuLieuDoanhThu[0]) && Long.parseLong(DuLieuDoanhThu[0]) <= longDateDen){
                            SoLuongVaDoanhThu[1]=SoLuongVaDoanhThu[1]+Integer.parseInt(DuLieuDoanhThu[3]);
                        }
                    }
                }
            });
        });
        return SoLuongVaDoanhThu;
    }
    public static int[] TongDoanhThuSanPham(String MaSanPham){
        int[] DoanhThu = {0};
        ArrayList<Object> SanPhamNgayDoanhThu = getSanPhamNgayDoanhThu();
        SanPhamNgayDoanhThu.forEach(SanPhamDoanhThu ->{
            ((ArrayList<String[]>) SanPhamDoanhThu).forEach(DuLieu->{
                if(DuLieu[1].equals("1")){
                   if(DuLieu[2].equals(MaSanPham)){
                        DoanhThu[0]=DoanhThu[0]+Integer.parseInt(DuLieu[3]);
                   }
                }
            });
        });
        return  DoanhThu;
    }
   
    
    
    
    public static ArrayList<Object> getSanPhamNgayDoanhThu(){
        ArrayList<Object> SanPhamNgayDoanhThu = new ArrayList<>();
        for(int i=0;i<quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.size();i++){
            SanPhamNgayDoanhThu.add(BUS.DonHangBUS.getSanPhamNgayDoanhThu(quanlycuahangsach.quanlycuahangsach.DonHangBUS.DonHangList.get(i).getMaDonHang()));
        }
        return SanPhamNgayDoanhThu;
    }
    
    public static int[] DoanhThuSanPham(String MaSanPham){
        int[] DoanhThuThang={0,0};

        Date d = new  Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = DateStringToTimestamp(dateFormat.format(d));
        String monthDate = TimestampToDateString(strDate,1).split("/")[1];
        ArrayList<Object> SanPhamNgayDoanhThu = getSanPhamNgayDoanhThu();
        SanPhamNgayDoanhThu.forEach(SanPhamDoanhThu ->{
            ((ArrayList<String[]>) SanPhamDoanhThu).forEach(DuLieu->{
                if(DuLieu[1].equals("1")){
                if(DuLieu[2].equals(MaSanPham)){
                    if(TimestampToDateString(DuLieu[0],1).split("/")[1].equals(monthDate)){
                        DoanhThuThang[0]=DoanhThuThang[0]+Integer.parseInt(DuLieu[3]);
                    }
                    if(Integer.parseInt(TimestampToDateString(DuLieu[0],1).split("/")[1]) == (Integer.parseInt(monthDate)-1)){
                        DoanhThuThang[1]=DoanhThuThang[1]+Integer.parseInt(DuLieu[3]);
                    }

                }  
                }
            });    
        });
        
        return DoanhThuThang;
    }
    //Excel
    public static int ThongKeSanPhamThang(String Nam,String Thang){
        int thongKeSanPham = 0;
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] maDonHangChiTietDH = getMaDonHangChiTietDH();
        String[] soLuong = getSoLuong();
        String[] trangthai = getTrangThaiDonHang(); 
        
        for(int i=0;i<ngayXuatDonHang.length;i++)
        {
            String year = TimestampToDateString(ngayXuatDonHang[i],1).split("/")[2];
            String month = TimestampToDateString(ngayXuatDonHang[i], 1).split("/")[1];
            if(trangthai[i].equals("1")){
                if(Nam.equals(year) && Thang.equals(month))
                {
                   for(int j=0;j<maDonHangChiTietDH.length;j++){
                        if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                            thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                        }
                    } 
                }
            }
        }
        
        return thongKeSanPham;
    }
    public static int ThongKeSanPhamQuy(String Nam,String Quy){
        int thongKeSanPham = 0;
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] maDonHangChiTietDH = getMaDonHangChiTietDH();
        String[] soLuong = getSoLuong();
        String[] trangthai = getTrangThaiDonHang();
        switch(Quy){
            case "1":
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/01/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+Nam+" 23:59:59")) )
                        {
                        for(int j=0;j<maDonHangChiTietDH.length;j++){
                            if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                                thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                            }
                        }
                        }
                    }
                }
                break;
            case "2":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/04/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+Nam+" 23:59:59")) )
                        {
                           for(int j=0;j<maDonHangChiTietDH.length;j++){
                            if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                                thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                            }
                        }
                        }    
                    }
                }
                break;
            case "3":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/07/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+Nam+" 23:59:59")) )
                        {
                            for(int j=0;j<maDonHangChiTietDH.length;j++){
                            if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                                thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                            }
                        }
                        }
                    }
                }
                break;
            case "4":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(trangthai[i].equals("1")){
                        if(Long.parseLong(DateStringToTimestamp("01/07/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+Nam+" 23:59:59")) )
                        {
                            for(int j=0;j<maDonHangChiTietDH.length;j++){
                            if(maDonHang[i].equals(maDonHangChiTietDH[j])){
                                thongKeSanPham=thongKeSanPham+Integer.parseInt(soLuong[j]);
                            }
                        }
                        }
                    }
                }
                break;
        }
        return thongKeSanPham;
    }
    public static ArrayList<String[]> ThongKeSanPhamThangExcel( String nam )
    {
        ArrayList<String[]> result = new ArrayList<>();
        String[] thang = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        for(int i=0;i<thang.length;i++)
        {
            int temp = ThongKeSanPhamThang(nam,thang[i]);

            String[] tungquy = {nam,thang[i],temp+""};
            result.add(tungquy);
        }
        return result;
    }
    public static ArrayList<String[]> ThongKeSanPhamQuyExcel( String nam )
    {
        ArrayList<String[]> result = new ArrayList<>();
        String[] quy = {"1","2","3","4"};
        for(int i=0;i<quy.length;i++)
        {
            int temp = ThongKeSanPhamQuy(nam,quy[i]);
            String[] tungquy = {nam,quy[i],temp+""};
            result.add(tungquy);
        }
        return result;
    }    
}
