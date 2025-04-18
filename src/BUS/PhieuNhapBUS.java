
package BUS;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahangsach.Regexp.MaNhaCungCap;
import static quanlycuahangsach.Regexp.MaNhanVien;
import static quanlycuahangsach.Regexp.MaPhieuNhap;
import static quanlycuahangsach.Regexp.Ten;
import static quanlycuahangsach.unicode.removeAccent;

public class PhieuNhapBUS {
    public ArrayList<PhieuNhapDTO> PhieuNhapList;
    public PhieuNhapDAO PhieuNhapDAO;

    public PhieuNhapBUS() {
        PhieuNhapDAO = new PhieuNhapDAO();
        PhieuNhapList = new ArrayList<>();
        fetchAll();
    }

    public void fetchAll() {
        PhieuNhapList = PhieuNhapDAO.fetchAll();
    }

    public boolean Exists(String MaPhieuNhap) {
        for (PhieuNhapDTO PhieuNhap : PhieuNhapList) {
            if (PhieuNhap.getMaPhieuNhap().toLowerCase().equals(MaPhieuNhap.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean add(PhieuNhapDTO PhieuNhap) {
        if (Exists(PhieuNhap.getMaPhieuNhap())) {
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã phiếu nhập đã tồn tại", 1);
            return false;
        } else {
            String Error = Validation(PhieuNhap);
            if (Error == null) {
                PhieuNhapDAO.add(PhieuNhap);
                PhieuNhapList = PhieuNhapDAO.fetchAll();
                return true;
            }
            else
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
        }
    }
    
    
    public ArrayList<PhieuNhapDTO> advancedSearch(HashMap<String,String> advancedKeyValue){
            ArrayList<PhieuNhapDTO> result = new ArrayList<>();
            PhieuNhapList.forEach(PhieuNhap ->{
            boolean MaPhieuNhap = PhieuNhap.getMaPhieuNhap().toLowerCase().contains(advancedKeyValue.get("MaPhieuNhap").toLowerCase());
            boolean MaNhanVien = PhieuNhap.getMaNhanVien().toLowerCase().contains(advancedKeyValue.get("MaNhanVien").toLowerCase());
            if(!MaPhieuNhap) MaPhieuNhap = removeAccent(PhieuNhap.getMaPhieuNhap().toLowerCase()).contains(advancedKeyValue.get("MaPhieuNhap").toLowerCase());
            if(!MaNhanVien) MaNhanVien = removeAccent(PhieuNhap.getMaNhanVien().toLowerCase()).contains(advancedKeyValue.get("MaNhanVien").toLowerCase());
            if (MaPhieuNhap && MaNhanVien)
                result.add(PhieuNhap);
         });     
         return result;
         
    }
    
    public boolean edit(PhieuNhapDTO PhieuNhap) {
        String Error = Validation(PhieuNhap);
                if (Error == null) {
                PhieuNhapDAO.edit(PhieuNhap);
                PhieuNhapList = PhieuNhapDAO.fetchAll();
                return true;
            }
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
    }

    public ArrayList<PhieuNhapDTO> findByHoTen(String HoTen){
        
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        PhieuNhapList.forEach(PhieuNhap ->{
            if(PhieuNhap.getMaPhieuNhap().toLowerCase().contains(HoTen.toLowerCase())) result.add(PhieuNhap);
        });
        return result;
    }
    public ArrayList<PhieuNhapDTO> findByMaPhieuNhap(String MaPhieuNhap){
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        PhieuNhapList.forEach(PhieuNhap ->{
            if(PhieuNhap.getMaPhieuNhap().toLowerCase().contains(MaPhieuNhap.toLowerCase())) result.add(PhieuNhap);
        });
        return result;
    }
    public void remove(PhieuNhapDTO PhieuNhap) {
    }
    


    public PhieuNhapDTO getByMaPhieuNhap(String MaPhieuNhap) {
        for (PhieuNhapDTO PhieuNhap : PhieuNhapList) {
            System.out.println(PhieuNhap.getMaPhieuNhap()+"~"+MaPhieuNhap);
            if (PhieuNhap.getMaPhieuNhap().toLowerCase().equals(MaPhieuNhap.toLowerCase())) {
                return PhieuNhap;
            }
        }
        return null;
    }

    private String Validation(PhieuNhapDTO PhieuNhap) {
        if (!MaPhieuNhap(PhieuNhap.getMaPhieuNhap())) {
            return "Mã phiếu nhập phải có dạng PN";
        }
       if (!MaNhanVien(PhieuNhap.getMaNhanVien())) {
           
            return "Mã nhân viên không hợp lệ";
        }
        if (!MaNhaCungCap(PhieuNhap.getMaNhaCungCap())) {
            return "Mã nhà cung cấp không hợp lệ";
        }
        return null;
    }
    public String getLatest(){
    return PhieuNhapDAO.getLatest();
    }
}
