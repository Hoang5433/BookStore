/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahangsach.Regexp.DonGia;
import static quanlycuahangsach.Regexp.Ten;
import static quanlycuahangsach.unicode.removeAccent;

/**
 *
 * @author admin
 */
public class SanPhamBUS {
    public ArrayList<SanPhamDTO> SachList;
    public SanPhamDAO SachDAO;
    
    public SanPhamBUS(){
        SachDAO = new SanPhamDAO();
        SachList = new ArrayList<>();
        fetchAll();
    }
    public void fetchAll(){
        SachList = SachDAO.fetchAll();
    }
    public boolean isExist(String MaSach){
        for(SanPhamDTO Sach:SachList)
            if(Sach.getMaSanPham().equalsIgnoreCase(MaSach)) return true;
        return false;
    }
public SanPhamDTO getByMaSanPham(String MaSanPham){
        for (SanPhamDTO SanPham : SachList) {
            if(SanPham.getMaSanPham().toLowerCase().equals(MaSanPham.toLowerCase())){
                return SanPham;
            }
        }
        return null;
    }    
    private String Validation(SanPhamDTO SanPham){
        if(!DonGia(SanPham.getGia())){
            return "Đơn giá không hợp lệ";
        }
        if(!Ten(SanPham.getTenSanPham())){
            return "Tên sản phẩm không hợp lệ";
        }
        return null;
    }    
    public boolean add(SanPhamDTO SanPham){
        if(Exists(SanPham.getMaSanPham())){
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã sản phẩm đã tồn tại", 1);
            return false;
        } else{
            String Error = Validation(SanPham);
            if(Error == null){
                    SachDAO.add(SanPham);
                    SachList = SachDAO.fetchAll();
                    return true;
            }
            else
                GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
                return false;
        }
    }
    public boolean edit(SanPhamDTO SanPham){
        String Error = Validation(SanPham);
            if(Error == null){
                SachDAO.edit(SanPham);
                SachList = SachDAO.fetchAll();
                return true;
            }
        GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
            return false;
    }
    public boolean Exists(String MaSanPham){
        for (SanPhamDTO SanPham : SachList) {
            if(SanPham.getMaSanPham().toLowerCase().equals(MaSanPham.toLowerCase())){
                return true;
            }
        }
        return false;
    }
        

public ArrayList<SanPhamDTO> advancedSearch(HashMap<String, String> advancedKeyValue){
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        SachList.forEach(Sach ->{
            boolean TenSach = Sach.getTenSanPham().toLowerCase().contains(advancedKeyValue.get("TenSanPham").toLowerCase()); 
            boolean MaSach = Sach.getMaSanPham().contains(advancedKeyValue.get("MaSanPham").toUpperCase());
            boolean TrangThai = advancedKeyValue.get("TrangThai").equals("");
            if(TrangThai == false){
                TrangThai = advancedKeyValue.get("TrangThai").equals("Hết hàng");
                    if(TrangThai)
                         TrangThai = Integer.parseInt(Sach.getSoLuong()) == 0;
                    else 
                        TrangThai = Integer.parseInt(Sach.getSoLuong()) >0;
            }
            boolean TheLoai = advancedKeyValue.get("MaTheLoai").equals("");
            if(TheLoai == false)
                TheLoai = Sach.getMaTheLoai().equals(advancedKeyValue.get("MaTheLoai"));
            boolean TacGia = advancedKeyValue.get("MaTacGia").equals("");
            if(TacGia == false)
                TacGia = Sach.getMaTacGia().equals(advancedKeyValue.get("MaTacGia"));
            boolean NhaXuatBan = advancedKeyValue.get("MaNhaXuatBan").equals("");
            if(NhaXuatBan == false)
                NhaXuatBan = Sach.getMaNhaXuatBan().equals(advancedKeyValue.get("MaNhaXuatBan"));
            if(TenSach==false){
                TenSach = removeAccent(Sach.getTenSanPham()).toLowerCase().contains(advancedKeyValue.get("TenSanPham").toLowerCase());
            }
            boolean Tu = advancedKeyValue.get("Tu").equals("");
            if(Tu == false)
                Tu = Double.parseDouble(Sach.getGia()) >= Double.parseDouble(advancedKeyValue.get("Tu"));
            boolean Den = advancedKeyValue.get("Den").equals("");
            if(Den == false)
                Den = Double.parseDouble(Sach.getGia()) <= Double.parseDouble(advancedKeyValue.get("Den"));
            if(TenSach && MaSach && TrangThai && TheLoai && NhaXuatBan && TacGia && Den && Tu)
                result.add(Sach);
        });
        
        return result;
    }   
    public String getLatest(){
        return SachDAO.getLatest();
    }

    public String getTenByMa(String MaSanPham){
        for(SanPhamDTO SanPham:SachList){
            if(SanPham.getMaSanPham().equals(MaSanPham))
                return SanPham.getTenSanPham();
        }
        return null;
    }
}
