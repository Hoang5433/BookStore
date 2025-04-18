/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.SanPhamKhuyenMaiDAO;
import DTO.SanPhamKhuyenMaiDTO;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class SanPhamKhuyenMaiBUS {
    public ArrayList<SanPhamKhuyenMaiDTO> SanPhamKhuyenMaiList;
    public SanPhamKhuyenMaiDAO SanPhamKhuyenMaiDAO;
    
    public SanPhamKhuyenMaiBUS(){
    SanPhamKhuyenMaiList = new ArrayList<>();
    SanPhamKhuyenMaiDAO = new SanPhamKhuyenMaiDAO();
    fetchAll();
    }
    public void fetchAll(){
        SanPhamKhuyenMaiList = SanPhamKhuyenMaiDAO.fetchAll();
    
    }
    
    public void add(SanPhamKhuyenMaiDTO SanPhamKhuyenMai){
        SanPhamKhuyenMaiDAO.add(SanPhamKhuyenMai);
        SanPhamKhuyenMaiList = SanPhamKhuyenMaiDAO.fetchAll();
    }
    public void edit(SanPhamKhuyenMaiDTO SanPhamKhuyenMai){
        SanPhamKhuyenMaiDAO.edit(SanPhamKhuyenMai);
        SanPhamKhuyenMaiList = SanPhamKhuyenMaiDAO.fetchAll();
    }
    public void remove(SanPhamKhuyenMaiDTO SanPhamKhuyenMai){
    }
    public ArrayList<SanPhamKhuyenMaiDTO> getSanPhamKhuyenMai(String MaChiTietKhuyenMai){
        ArrayList<SanPhamKhuyenMaiDTO> SanPhamList = new ArrayList<>();
        for(SanPhamKhuyenMaiDTO SanPhamKhuyenMai: SanPhamKhuyenMaiList){
            if(SanPhamKhuyenMai.getMaChiTietKhuyenMai().equals(MaChiTietKhuyenMai))
               SanPhamList.add(SanPhamKhuyenMai);
                }
        if(SanPhamList.size()==0) return SanPhamList;
        return SanPhamList.get(0).getMaSanPham() == null ? null : SanPhamList;
    }
}
