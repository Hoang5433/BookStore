/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahangsach.Regexp.DiaChi;
import static quanlycuahangsach.Regexp.Email;
import static quanlycuahangsach.Regexp.MaKhachHang;
import static quanlycuahangsach.Regexp.So;
import static quanlycuahangsach.Regexp.SoDienThoai;
import static quanlycuahangsach.Regexp.Ten;
import static quanlycuahangsach.unicode.removeAccent;

/**
 *
 * @author admin
 */
public class KhachHangBUS {
    public ArrayList<KhachHangDTO> KhachHangList;
    public KhachHangDAO KhachHangDAO;
    
    public KhachHangBUS(){
        KhachHangDAO = new KhachHangDAO();
        KhachHangList = new ArrayList<>();
        fetchAll();
    }
    
    public void fetchAll(){
        KhachHangList = KhachHangDAO.fetchAll();
    }

    public String getLatest(){
        return KhachHangDAO.getLatest();
    }
    
    public ArrayList<KhachHangDTO> findByMaKhachHang(String MaKhachHang){
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        KhachHangList.forEach(KhachHang ->{
            if(KhachHang.getMaKhachHang().toLowerCase().contains(MaKhachHang.toLowerCase()))
                result.add(KhachHang);
        });
        return result;
    }
    
    public ArrayList<KhachHangDTO> findByHoTen(String HoTen){
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        KhachHangList.forEach(KhachHang ->{
            if(KhachHang.getHoTen().toLowerCase().contains(HoTen.toLowerCase()))
                result.add(KhachHang);
        });
        return result;
    }
    
    public ArrayList<KhachHangDTO> findByEmail(String Email){
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        KhachHangList.forEach(KhachHang ->{
            if (KhachHang.getEmail().toLowerCase().contains(Email.toLowerCase()))
                result.add(KhachHang);
        });
        return result;
    }
    
    public KhachHangDTO findBySoDienThoai(String SoDienThoai){
        for(KhachHangDTO KhachHang:KhachHangList){
            if(KhachHang.getSoDienThoai().equals(SoDienThoai)) return KhachHang;
        }
        return null;
    }
    
    public String Validation(KhachHangDTO KhachHang){
    if (!MaKhachHang(KhachHang.getMaKhachHang())) {
            return "Mã khách hàng phải có dạng KH";
        }
    if(!Email(KhachHang.getEmail())){
            return "Email không hợp lệ";
    }
       if (!Ten(KhachHang.getHoTen())) {
            return "Họ tên không hợp lệ";
        }
        if (!SoDienThoai(KhachHang.getSoDienThoai())) {
            return "Số điện thoại không hợp lệ";
        }
        if (!DiaChi(KhachHang.getDiaChi())){
            return "Địa chỉ không được chứa ký tự đặc biệt";
        }
        return null;
    }
    
        public boolean Exists(String MaKhachHang) {
        for (KhachHangDTO KhachHang : KhachHangList) {
            if (KhachHang.getMaKhachHang().toLowerCase().equals(MaKhachHang.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean add(KhachHangDTO KhachHang) {
        if (Exists(KhachHang.getMaKhachHang())) {
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã khách hàng đã tồn tại", 1);
            return false;
        } else {
            String Error = Validation(KhachHang);
            if (Error == null) {
                KhachHangDAO.add(KhachHang);
                KhachHangList = KhachHangDAO.fetchAll();
                return true;
            }
            else
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
        }
    }
    public KhachHangDTO getByMaKhachHang(String MaKhachHang){
        for (KhachHangDTO KhachHang : KhachHangList) {
            if (KhachHang.getMaKhachHang().toLowerCase().equals(MaKhachHang.toLowerCase())) {
                return KhachHang;
            }
        }
        return null;
    }
    
    public String getMaKhachHangByTenKhachHang(String TenKhachHang){
        for(KhachHangDTO KhachHang : KhachHangList){
            if(KhachHang.getHoTen().toLowerCase().equals(TenKhachHang.toLowerCase())){
                return KhachHang.getMaKhachHang();
            }
        }
        return null;
    }
    
    public String getTenKhachHangByMaKhachHang(String MaKhachHang){
        for(KhachHangDTO KhachHang : KhachHangList){
            if(KhachHang.getMaKhachHang().toLowerCase().equals(MaKhachHang.toLowerCase())){
                return KhachHang.getHoTen();
            }
        }
        return null;
    }
    
    
    public ArrayList<KhachHangDTO> advancedSearch(HashMap<String, String> advancedKeyValue){
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        KhachHangList.forEach(KhachHang ->{
            boolean HoTenKhachHang = KhachHang.getHoTen().toLowerCase().contains(advancedKeyValue.get("HoTen").toLowerCase());
            if(!HoTenKhachHang)
                HoTenKhachHang = removeAccent(KhachHang.getHoTen().toLowerCase()).contains(advancedKeyValue.get("HoTen").toLowerCase());
            if(KhachHang.getMaKhachHang().toLowerCase().contains(advancedKeyValue.get("MaKhachHang").toLowerCase()) &&
                    HoTenKhachHang &&
                    KhachHang.getEmail().contains(advancedKeyValue.get("Email")) &&
                    KhachHang.getSoDienThoai().contains(advancedKeyValue.get("SoDienThoai")))
                result.add(KhachHang);
        });
        return result;
    }
}