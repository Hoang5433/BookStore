/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.PhieuNhapHang.SoLuongVaDonGiaJFrame;
import GUI.PhieuNhapHang.TaoPhieuNhapJPanel;
import GUI.PhieuNhapHang.ThemSanPhamJFrame;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahangsach.Regexp.DonGiaPhieuNhap;
import static quanlycuahangsach.Regexp.SoLuong;
import static quanlycuahangsach.Regexp.SoLuongPhieuNhap;
import static quanlycuahangsach.unicode.removeAccent;

/**
 *
 * @author khanh
 */
public class ChiTietPhieuNhapBUS {
    public static ArrayList<ChiTietPhieuNhapDTO> ChiTietPhieuNhapList;
    public ChiTietPhieuNhapDAO ChiTietPhieuNhapDAO;
    private SanPhamDTO SanPhamTrongChiTiet;
    
    public ChiTietPhieuNhapBUS(){
        ChiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
        ChiTietPhieuNhapList = new ArrayList<>();
        fetchAll();
    }
    
    public void fetchAll(){
        ChiTietPhieuNhapList = ChiTietPhieuNhapDAO.fetchAll();
    }
    public static ChiTietPhieuNhapDTO getByMaSanPham(String MaSanPham){
        for(ChiTietPhieuNhapDTO ChiTietPhieuNhap : ChiTietPhieuNhapList){
            if(ChiTietPhieuNhap.getMaSanPham().equals(MaSanPham)){
                return ChiTietPhieuNhap;
            }
        }
        return null;
    }
    public boolean isExist(String MaPhieuNhap){
        for(ChiTietPhieuNhapDTO ChiTietPhieuNhap : ChiTietPhieuNhapList){
            if(ChiTietPhieuNhap.getMaPhieuNhap().toLowerCase().equals(MaPhieuNhap.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public boolean add(ChiTietPhieuNhapDTO ChiTietPhieuNhap){
        if(Exists(ChiTietPhieuNhap.getMaSanPham())){
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã phiếu nhập đã tồn tại", 1);
            return false;
        } else{
            String Error = Validation(ChiTietPhieuNhap);
            if(Error == null){
                    ChiTietPhieuNhapDAO.add(ChiTietPhieuNhap);
                    ChiTietPhieuNhapList = ChiTietPhieuNhapDAO.fetchAll();
                    return true;
            }
            else
                GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
                return false;
        }
    }
        public ArrayList<SanPhamDTO> advancedSearch(HashMap<String, String> advancedKeyValue){
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        ThemSanPhamJFrame.DanhSachSanPham.forEach(SanPham ->{
        boolean TenSanPham = SanPham.getTenSanPham().toLowerCase().contains(advancedKeyValue.get("TenSanPham").toLowerCase());
        if(!TenSanPham)
                TenSanPham = removeAccent(SanPham.getTenSanPham().toLowerCase()).contains(advancedKeyValue.get("TenSanPham").toLowerCase());
        System.out.println(SanPham.getMaTheLoai());
        if(SanPham.getMaSanPham().toLowerCase().contains(advancedKeyValue.get("MaSanPham").toLowerCase()) &&
                TenSanPham &&
                SanPham.getMaTheLoai().contains(advancedKeyValue.get("TheLoai")) &&
                SanPham.getMaTacGia().contains(advancedKeyValue.get("TacGia")) &&
                SanPham.getMaNhaXuatBan().contains(advancedKeyValue.get("NhaXuatBan"))
            )
                result.add(SanPham);
        });    
        return result;
    }
    public ArrayList<ChiTietPhieuNhapDTO> advancedSearchThemSanPham(HashMap<String, String> advancedKeyValue)
        {
            ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
            TaoPhieuNhapJPanel.ChiTietPhieuNhapList.forEach(SanPham ->{
            SanPhamTrongChiTiet = quanlycuahangsach.quanlycuahangsach.SachBUS.getByMaSanPham(SanPham.getMaSanPham());
            boolean TenSanPham = SanPhamTrongChiTiet.getTenSanPham().toLowerCase().contains(advancedKeyValue.get("TenSanPham").toLowerCase());
            if(!TenSanPham)
                    TenSanPham = removeAccent(SanPhamTrongChiTiet.getTenSanPham().toLowerCase()).contains(advancedKeyValue.get("TenSanPham").toLowerCase());
            if(SanPham.getMaSanPham().toLowerCase().contains(advancedKeyValue.get("MaSanPham").toLowerCase()) &&
                    TenSanPham &&
                    SanPhamTrongChiTiet.getMaTheLoai().contains(advancedKeyValue.get("TheLoai")) &&
                    SanPhamTrongChiTiet.getMaTacGia().contains(advancedKeyValue.get("TacGia")) &&
                    SanPhamTrongChiTiet.getMaNhaXuatBan().contains(advancedKeyValue.get("NhaXuatBan"))
                )
                    result.add(SanPham);
            });    
            return result;
        }
    public String Validation(ChiTietPhieuNhapDTO ChiTietPhieuNhap){
        if(!DonGiaPhieuNhap(ChiTietPhieuNhap.getDonGia())){
            return "Đơn giá không hợp lệ";
            
        }
        if(!SoLuongPhieuNhap(ChiTietPhieuNhap.getSoLuong()))
         {
             return "Số lượng không hợp lệ";
             
         }
//        if(ChiTietPhieuNhap.getDonGia().equals("0"))
//        {
//            return "Đơn giá không hợp lệ";
//        }
//        if(ChiTietPhieuNhap.getSoLuong().equals("0"))
//        {
//            return "Số lượng không hợp lệ";
//        }
         return null;
    }
    public boolean Exists(String MaPhieuNhap){
        for (ChiTietPhieuNhapDTO ChiTietPhieuNhap : ChiTietPhieuNhapList) {
            if(ChiTietPhieuNhap.getMaPhieuNhap().toLowerCase().equals(MaPhieuNhap.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public static ArrayList<ChiTietPhieuNhapDTO> getChiTietPhieuNhapByMaPhieuNhap(String MaPhieuNhap){
        ArrayList<ChiTietPhieuNhapDTO> array = new ArrayList<>();
        for (ChiTietPhieuNhapDTO ChiTietPhieuNhap : ChiTietPhieuNhapList) {
            if(ChiTietPhieuNhap.getMaPhieuNhap().toLowerCase().equals(MaPhieuNhap.toLowerCase())){
                array.add(ChiTietPhieuNhap);
            }
        }
        return array;
    }
}
