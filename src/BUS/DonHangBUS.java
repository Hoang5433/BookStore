/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DonHangDAO;
import DTO.DonHangDTO;
import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;

import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahangsach.Regexp.DiaChi;
import static quanlycuahangsach.Regexp.MaDonHang;
import static quanlycuahangsach.Regexp.MaNhanVien;
import static quanlycuahangsach.Regexp.MucLuong;
import static quanlycuahangsach.Regexp.NamSinh;
import static quanlycuahangsach.Regexp.So;
import static quanlycuahangsach.Regexp.SoDienThoai;
import static quanlycuahangsach.Regexp.Ten;
import static quanlycuahangsach.unicode.removeAccent;

/**
 *
 * @author HUUNHAN
 */
public class DonHangBUS {
    public ArrayList<DonHangDTO> DonHangList;
    public DonHangDAO DonHangDAO;
    
    public DonHangBUS(){
        DonHangDAO = new DonHangDAO();
        DonHangList = new ArrayList<>();
        fetchAll();   
    }
    public void  fetchAll(){
        DonHangList = DonHangDAO.fetchAll();
    }
    public boolean Exists(String MaDonHang){
        for(DonHangDTO DonHang : DonHangList){
            if(DonHang.getMaDonHang().toLowerCase().equals(MaDonHang.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    /*
    public ArrayList<DonHangDTO> findByMaDonHang(String MaDonHang){
        ArrayList<DonHangDTO> result = new ArrayList<>();
        DonHangList.forEach(DonHang ->{
            if(DonHang.getMaDonHang().toLowerCase().contains(MaDonHang.toLowerCase()))
                result.add(DonHang);
        });
        return result;
    }
    */
    public DonHangDTO getByMaDonHang(String MaDonHang) {
        for (DonHangDTO DonHang : DonHangList) {
            if (DonHang.getMaDonHang().toLowerCase().equals(MaDonHang.toLowerCase())) {
                return DonHang;
            }
        }
        return null;
    }
    /*
    public ArrayList<DonHangDTO> findByHoTen(String HoTen){
        
        ArrayList<DonHangDTO> result = new ArrayList<>();
        DonHangList.forEach(DonHang ->{
            KhachHangDTO KhachHang = quanlycuahangsach.quanlycuahangsach.KhachHangBUS.getByMaKhachHang(DonHang.getMaKhachHang());
            if(KhachHang.getHoTen().toLowerCase().contains(HoTen.toLowerCase())) result.add(DonHang);
        });
        return result;
    }
    */
    public ArrayList<DonHangDTO> advancedSearch(HashMap<String,String> advancedKeyValue){
            ArrayList<DonHangDTO> result = new ArrayList<>();
         DonHangList.forEach(DonHang ->{
             KhachHangDTO KhachHang = quanlycuahangsach.quanlycuahangsach.KhachHangBUS.getByMaKhachHang(DonHang.getMaKhachHang());
             boolean TenHoacSDT = KhachHang.getHoTen().toLowerCase().contains(advancedKeyValue.get("HoTenSDT").toLowerCase());//
             if(!TenHoacSDT) TenHoacSDT = removeAccent(KhachHang.getHoTen().toLowerCase()).contains(advancedKeyValue.get("HoTenSDT").toLowerCase());
             if(So(advancedKeyValue.get("HoTenSDT"))){
                 TenHoacSDT = KhachHang.getSoDienThoai().contains(advancedKeyValue.get("HoTenSDT"));
             }
             if(DonHang.getMaDonHang().contains(advancedKeyValue.get("MaDonHang").toUpperCase()) &&
                TenHoacSDT &&
                DonHang.getTrangThai().contains(advancedKeyValue.get("TrangThai")) &&
                DonHang.getMaNhanVien().contains(advancedKeyValue.get("MaNhanVien")))
                
                result.add(DonHang);
         });
         return result;  
    }
    
    public boolean add(DonHangDTO DonHang) {
        if (Exists(DonHang.getMaKhachHang())) {
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã đơn hàng đã tồn tại", 1);
            return false;
        } else {
            String Error = Validation(DonHang);
            if (Error == null) {
                DonHangDAO.add(DonHang);
                DonHangList = DonHangDAO.fetchAll();
                return true;
            }
            else
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
        }
    }

    public boolean edit(DonHangDTO DonHang) {
        String Error = Validation(DonHang);
                if (Error == null) {
                DonHangDAO.edit(DonHang);
                DonHangList = DonHangDAO.fetchAll();
                return true;
            }
        GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
    }
    
    private String Validation(DonHangDTO DonHang) {
        if (!MaDonHang(DonHang.getMaDonHang())) {
            return "Mã đon hàng phải có dạng DH";
        }
        return null;
    }
    
     public String getLatest(){
    return DonHangDAO.getLatest();
    }
    
     public static  ArrayList<String[]> getSoLuongVaoNgay(String MaDon){
         return DAO.DonHangDAO.getSoLuongVaoNgay(MaDon);
     }
     public static ArrayList<String[]> getSanPhamVaoNgay(String MaDon){
         return DAO.DonHangDAO.getSanPhamVaoNgay(MaDon);
     }
     public ArrayList<DonHangDTO> getDonHangByMaGiamGia(String MaGiamGia){
        ArrayList<DonHangDTO> result = new ArrayList<>();
        for(DonHangDTO DonHang : DonHangList){
            if( DonHang.getMaCode() !=null && DonHang.getMaCode().equals(MaGiamGia))
                result.add(DonHang);
        }
        return result;
    }
     public static ArrayList<String[]> getSanPhamNgayDoanhThu(String MaDon){
         return DAO.DonHangDAO.getSanPhamNgayDoanhThu(MaDon);
     }
     public static ArrayList<DonHangDTO> getTOP10DonHang(){
         return DAO.DonHangDAO.getTOP10DonHang();
          
     }
     public ArrayList<DonHangDTO> getByMaKhachHang(String MaKhachHang){
         ArrayList<DonHangDTO> result  = new ArrayList<>();
         for(DonHangDTO DonHang:DonHangList){
             if(DonHang.getMaKhachHang().equals(MaKhachHang))
                 result.add(DonHang);
         }
         return result;
     }
}
