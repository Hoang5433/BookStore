/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahangsach.Regexp.DiaChi;
import static quanlycuahangsach.Regexp.MaNhanVien;
import static quanlycuahangsach.Regexp.MucLuong;
import static quanlycuahangsach.Regexp.NamSinh;
import static quanlycuahangsach.Regexp.So;
import static quanlycuahangsach.Regexp.SoDienThoai;
import static quanlycuahangsach.Regexp.Ten;
import static quanlycuahangsach.unicode.removeAccent;

/**
 *
 * @author Administrator
 */
public class NhanVienBUS {

    public ArrayList<NhanVienDTO> NhanVienList;
    public NhanVienDAO NhanVienDAO;

    public NhanVienBUS() {
        NhanVienDAO = new NhanVienDAO();
        NhanVienList = new ArrayList<>();
        fetchAll();
    }

    public void fetchAll() {
        NhanVienList = NhanVienDAO.fetchAll();
    }

    public boolean Exists(String MaNhanVien) {
        for (NhanVienDTO NhanVien : NhanVienList) {
            if (NhanVien.getMaNhanVien().toLowerCase().equals(MaNhanVien.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean add(NhanVienDTO NhanVien) {
        if (Exists(NhanVien.getMaNhanVien())) {
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã nhân viên đã tồn tại", 1);
            return false;
        } else {
            String Error = Validation(NhanVien);
            if (Error == null) {
                NhanVienDAO.add(NhanVien);
                NhanVienList.add(NhanVien);
                return true;
            }
            else
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
        }
    }
    
    
    public ArrayList<NhanVienDTO> advancedSearch(HashMap<String,String> advancedKeyValue){
            ArrayList<NhanVienDTO> result = new ArrayList<>();
         NhanVienList.forEach(NhanVien ->{
             boolean CaLam =advancedKeyValue.get("CaSang").equals("") && advancedKeyValue.get("CaChieu").equals("") && advancedKeyValue.get("CaToi").equals("");
             if(CaLam == false)
             CaLam = NhanVien.getCaLam().equals(advancedKeyValue.get("CaSang")) || NhanVien.getCaLam().equals(advancedKeyValue.get("CaChieu")) || NhanVien.getCaLam().equals(advancedKeyValue.get("CaToi"));
             boolean TenHoacSDT = NhanVien.getHoTen().toLowerCase().contains(advancedKeyValue.get("HoTenSDT").toLowerCase());//
             if(!TenHoacSDT) TenHoacSDT = removeAccent(NhanVien.getHoTen().toLowerCase()).contains(advancedKeyValue.get("HoTenSDT").toLowerCase());
             
             if(So(advancedKeyValue.get("HoTenSDT"))){
                 TenHoacSDT = NhanVien.getSoDienThoai().contains(advancedKeyValue.get("HoTenSDT"));
             }
             if(NhanVien.getMaNhanVien().contains(advancedKeyValue.get("MaNhanVien").toUpperCase()) &&
                TenHoacSDT && 
                quanlycuahangsach.quanlycuahangsach.AccountBUS.checkRole(NhanVien.getMaNhanVien(), advancedKeyValue.get("Quyen"))     &&
                CaLam &&
                NhanVien.getTrangThai().contains(advancedKeyValue.get("TrangThai")) && NhanVien.getGioiTinh().contains(advancedKeyValue.get("GioiTinh")))
           
                
                 result.add(NhanVien);
         });
         
         return result;
         
    }
    
    public boolean edit(NhanVienDTO NhanVien) {
        String Error = Validation(NhanVien);
                if (Error == null) {
                NhanVienDAO.edit(NhanVien);
                NhanVienList = NhanVienDAO.fetchAll();
                return true;
            }
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
    }

    public ArrayList<NhanVienDTO> findByHoTen(String HoTen){
        
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        NhanVienList.forEach(NhanVien ->{
            if(NhanVien.getHoTen().toLowerCase().contains(HoTen.toLowerCase())) result.add(NhanVien);
        });
        return result;
    }
    public ArrayList<NhanVienDTO> findByMaNhanVien(String MaNhanVien){
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        NhanVienList.forEach(NhanVien ->{
            if(NhanVien.getMaNhanVien().toLowerCase().contains(MaNhanVien.toLowerCase())) result.add(NhanVien);
        });
        return result;
    }
    public void remove(NhanVienDTO NhanVien) {
    }
    

    public static void getAuth(String MaNhanVien) {
        DAO.NhanVienDAO.getAuth(MaNhanVien);
    }

    public NhanVienDTO getByMaNhanVien(String MaNhanVien) {
        for (NhanVienDTO NhanVien : NhanVienList) {
            if (NhanVien.getMaNhanVien().toLowerCase().equals(MaNhanVien.toLowerCase())) {
                return NhanVien;
            }
        }
        return null;
    }
    public String getTenNhanVienByMaNhanVien(String MaNhanVien) {
        for (NhanVienDTO NhanVien : NhanVienList) {
            if (NhanVien.getMaNhanVien().toLowerCase().equals(MaNhanVien.toLowerCase())) {
                return NhanVien.getHoTen();
            }
        }
        return null;
    }
    private String Validation(NhanVienDTO NhanVien) {
        if (!MaNhanVien(NhanVien.getMaNhanVien())) {
            return "Mã nhân viên phải có dạng NV";
        }
       if (!Ten(NhanVien.getHoTen())) {
            return "Họ tên không hợp lệ";
        }
        if (!NamSinh(NhanVien.getNamSinh())) {
            return "Năm sinh không hợp lệ";
        }
        if (!SoDienThoai(NhanVien.getSoDienThoai())) {
            return "Số điện thoại không hợp lệ";
        }
        if (!MucLuong(NhanVien.getLuong())) {
            return "Lương không hợp lệ";
        }
        if (!DiaChi(NhanVien.getDiaChi())){
            return "Địa chỉ không được chứa ký tự đặc biệt";
        }
        return null;
    }
    public String getLatest(){
    return NhanVienDAO.getLatest();
    }
}
