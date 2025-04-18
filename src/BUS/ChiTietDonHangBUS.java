/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietDonHangDAO;
import DTO.ChiTietDonHangDTO;
import java.util.ArrayList;

/**
 *
 * @author HUUNHAN
 */
public class ChiTietDonHangBUS {
    public ArrayList<ChiTietDonHangDTO> ChiTietDonHangList;
    public ChiTietDonHangDAO ChiTietDonHangDAO;
    
    public ChiTietDonHangBUS(){
        ChiTietDonHangDAO = new ChiTietDonHangDAO();
        ChiTietDonHangList = new ArrayList<>();
        fetchAll();   
    }
    
    public ChiTietDonHangDTO getByMaDonHang(String MaDonHang){
        for (ChiTietDonHangDTO ChiTietDonHang : ChiTietDonHangList) {
            if (ChiTietDonHang.getMaDonHang().toLowerCase().equals(MaDonHang.toLowerCase())) {
                return ChiTietDonHang;
            }
        }
        return null;
    }
    public ArrayList<ChiTietDonHangDTO> getListChiTietByMaDonHang(String MaDonHang){
       ArrayList<ChiTietDonHangDTO> result = new ArrayList<>();
        for (ChiTietDonHangDTO ChiTietDonHang : ChiTietDonHangList) {
            if (ChiTietDonHang.getMaDonHang().equals(MaDonHang)) {
                result.add(ChiTietDonHang);
            }
        }
        return result;
    }
    public void  fetchAll(){
        ChiTietDonHangList = ChiTietDonHangDAO.fetchAll();
    }
    public void add(ChiTietDonHangDTO ChiTietDonHang){
        ChiTietDonHangDAO.add(ChiTietDonHang);
        ChiTietDonHangList = ChiTietDonHangDAO.fetchAll();
    }
}
