/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietKhuyenMaiDAO;
import DTO.ChiTietKhuyenMaiDTO;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahangsach.Regexp.MaChiTietKhuyenMai;
import static quanlycuahangsach.Regexp.SoLuong;
import static quanlycuahangsach.Regexp.Ten;
import static quanlycuahangsach.unicode.removeAccent;
/**
 *
 * @author TKH
 */
public class ChiTietKhuyenMaiBUS {
    public ArrayList<ChiTietKhuyenMaiDTO> ChiTietKhuyenMaiList;
    public ChiTietKhuyenMaiDAO ChiTietKhuyenMaiDAO;
    
    public ChiTietKhuyenMaiBUS() {
        ChiTietKhuyenMaiList = new ArrayList<>();
        ChiTietKhuyenMaiDAO = new ChiTietKhuyenMaiDAO();
        fetchAll();
    }
    
    public void fetchAll() {
        ChiTietKhuyenMaiList = ChiTietKhuyenMaiDAO.fetchAll();
    }
    
    public boolean Exists(String MaChiTietKhuyenMai) {
        for (ChiTietKhuyenMaiDTO ChiTietKhuyenMai : ChiTietKhuyenMaiList) {
            if (ChiTietKhuyenMai.getMaChiTietKhuyenMai().toLowerCase().equals(MaChiTietKhuyenMai.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    private boolean CodeExists(String MaCode){
    for (ChiTietKhuyenMaiDTO ChiTietKhuyenMai : ChiTietKhuyenMaiList) {
            if (ChiTietKhuyenMai.getMaCode().toLowerCase().equals(MaCode.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    private String Validation(ChiTietKhuyenMaiDTO ChiTietKhuyenMai) {
        if (CodeExists(ChiTietKhuyenMai.getMaCode()))
            return "Mã code này đã tồn tại";
        if (!MaChiTietKhuyenMai(ChiTietKhuyenMai.getMaChiTietKhuyenMai())) {
            return "Mã chi tiết khuyến mãi phải có dạng CTKM";
        }
        if (!Ten(ChiTietKhuyenMai.getMaCode())) {
            return "Mã Code không hợp lệ";
        }
        if( ChiTietKhuyenMai.getThoiGianBatDauSuDung().equals(ChiTietKhuyenMai.getThoiGianKetThucSuDung()))
            return "Ngày kết thúc phải lớn hơn ngày bắt đầu";
            if (!SoLuong(ChiTietKhuyenMai.getSoLuong()))
            return "Số lượng không hợp lệ";
        if (!SoLuong(ChiTietKhuyenMai.getPhanTramKhuyenMai()) || Integer.parseInt(ChiTietKhuyenMai.getPhanTramKhuyenMai())>100)
            return "Phần trăm khuyến mãi không hợp lệ";
        if (!SoLuong(ChiTietKhuyenMai.getGiaTriGiamToiDa()))
            return "Giá trị giảm tối đa không hợp lệ";
        if (!SoLuong(ChiTietKhuyenMai.getGiaTriToiThieuSuDung()))
            return "Giá trị sử dụng tối thiểu không hợp lệ";
        if (!SoLuong(ChiTietKhuyenMai.getGiaTienKhuyenMai()))
            return "Giá tiền khuyến mãi không hợp lệ";
        return null;
    }
    
    public ChiTietKhuyenMaiDTO getByMaCode(String MaCode) {
        if(MaCode!=null){ 
        for (ChiTietKhuyenMaiDTO ChiTietKhuyenMai : ChiTietKhuyenMaiList) {
            if (ChiTietKhuyenMai.getMaCode().toLowerCase().equals(MaCode.toLowerCase())) {
                return ChiTietKhuyenMai;
            }
        }
        }
        return null;
    }
    public void edit(ChiTietKhuyenMaiDTO ChiTietKhuyenMai){
            ChiTietKhuyenMaiDAO.edit(ChiTietKhuyenMai);
            ChiTietKhuyenMaiList = ChiTietKhuyenMaiDAO.fetchAll();  
    }
    public ArrayList<ChiTietKhuyenMaiDTO> advancedSearch(HashMap<String, String> advancedKeyValue) {
        ArrayList<ChiTietKhuyenMaiDTO> result = new ArrayList<>();
        ChiTietKhuyenMaiList.forEach(ChiTietKhuyenMai -> {
            boolean TrangThai = true;
            if(advancedKeyValue.get("TrangThai").equals("Còn"))
                TrangThai = (Integer.parseInt(ChiTietKhuyenMai.getSoLuong()) - Integer.parseInt(ChiTietKhuyenMai.getSoLuongDaDung())) > 0;
            if(advancedKeyValue.get("TrangThai").equals("Hết"))
                TrangThai = (Integer.parseInt(ChiTietKhuyenMai.getSoLuong()) - Integer.parseInt(ChiTietKhuyenMai.getSoLuongDaDung())) == 0;
            if (ChiTietKhuyenMai.getMaChuongTrinhKhuyenMai().toLowerCase().contains(advancedKeyValue.get("MaChuongTrinhKhuyenMai").toLowerCase()) &&
                    ChiTietKhuyenMai.getMaCode().toLowerCase().contains(advancedKeyValue.get("MaChiTiet").toLowerCase()) &&
                    TrangThai)
                result.add(ChiTietKhuyenMai);
        });
        return result;
    }
    public ChiTietKhuyenMaiDTO getByMaChiTietKhuyenMai(String MaChiTietKhuyenMai){
        for(ChiTietKhuyenMaiDTO ChiTietKhuyenMai : ChiTietKhuyenMaiList){
            if(ChiTietKhuyenMai.getMaChiTietKhuyenMai().toLowerCase().equals(MaChiTietKhuyenMai.toLowerCase()))
                return ChiTietKhuyenMai;
        }
        return null;
    }
    public ArrayList<ChiTietKhuyenMaiDTO> getChiTietKhuyenMaiByMaChuongTrinhKhuyenMai(String MaChuongTrinhKhuyenMai){
        ArrayList<ChiTietKhuyenMaiDTO> result = new ArrayList<>();
        for(ChiTietKhuyenMaiDTO ChiTietKhuyenMai : ChiTietKhuyenMaiList){
            if(ChiTietKhuyenMai.getMaChuongTrinhKhuyenMai().equals(MaChuongTrinhKhuyenMai))
                result.add(ChiTietKhuyenMai);
        }
        return result;
    }
    public String getSoLuongbyMaChuongTrinhKhuyenMai(String MaChuongTrinhKhuyenMai){
        for (ChiTietKhuyenMaiDTO ChiTietKhuyenMai : ChiTietKhuyenMaiList) {
            if(ChiTietKhuyenMai.getMaChuongTrinhKhuyenMai().equals(MaChuongTrinhKhuyenMai))
                return ChiTietKhuyenMai.getSoLuong();
        }
        return null;
    }
    public String getSoLuongChiTietKhuyenMai(String MaChuongTrinhKhuyenMai){
        return ChiTietKhuyenMaiDAO.getSoLuongChiTietKhuyenMai(MaChuongTrinhKhuyenMai);
    }
    public String getLatest(){
        return ChiTietKhuyenMaiDAO.getLatest();
    }
    public boolean add(ChiTietKhuyenMaiDTO ChiTietKhuyenMai){
        if(Exists(ChiTietKhuyenMai.getMaChiTietKhuyenMai())){
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã chương trình đã tồn tại", 1);
            return false;
        } else{
            String Error = Validation(ChiTietKhuyenMai);
            if(Error == null){
                    ChiTietKhuyenMaiDAO.add(ChiTietKhuyenMai);
                    ChiTietKhuyenMaiList = ChiTietKhuyenMaiDAO.fetchAll();
                    return true;
            }
            else
                GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
                return false;
        }
    }
}
