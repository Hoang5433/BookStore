/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;
import static quanlycuahangsach.Regexp.DiaChi;
import static quanlycuahangsach.Regexp.MaNhaCungCap;
import static quanlycuahangsach.Regexp.SoDienThoai;
import static quanlycuahangsach.Regexp.Ten;

/**
 *
 * @author Administrator
 */
public class NhaCungCapBUS {
    public ArrayList<NhaCungCapDTO> NhaCungCapList;
    public NhaCungCapDAO NhaCungCapDAO;
    public NhaCungCapBUS(){
        NhaCungCapList = new ArrayList<>();
        NhaCungCapDAO = new NhaCungCapDAO();
        fetchAll();
    }
    public void fetchAll(){
        NhaCungCapList = NhaCungCapDAO.fetchAll();
    }
       public boolean Exists(String MaNhaCungCap){
        for(NhaCungCapDTO NhaCungCap:NhaCungCapList)
            if(NhaCungCap.getMaNhaCungCap().toLowerCase().equals(MaNhaCungCap.toLowerCase())) return true;
            return false;
    } 
    
    public boolean add(NhaCungCapDTO NhaCungCap){
        if(Exists(NhaCungCap.getMaNhaCungCap())){ GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Mã đã tồn tại, mã mới là: "+getLatest(), 2);
            return false;
        }
        else {
            String Error = Validation(NhaCungCap);
            if(Error == null){
            NhaCungCapDAO.add(NhaCungCap);
            NhaCungCapList = NhaCungCapDAO.fetchAll();
            return true;
            }
            else
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
             return false;
                }
            
    }
    public void edit(NhaCungCapDTO NhaCungCap){
        NhaCungCapDAO.edit(NhaCungCap);
        fetchAll();
        GUI.QuanLyDanhMuc.NhaCungCapJPanel.fetchAll();
    }
    public void remove(NhaCungCapDTO NhaCungCap){
        
    }
    public String Validation(NhaCungCapDTO NhaCungCap){
        if(!MaNhaCungCap(NhaCungCap.getMaNhaCungCap())) return "Mã nhà cung cấp phải có dạng NCC";
        if(!SoDienThoai(NhaCungCap.getSoDienThoai())) return "Số điện thoại không hợp lệ";
        if(!Ten(NhaCungCap.getTenNhaCungCap())) return "Tên không được chứa ký tự hợp lệ";
        if(!DiaChi(NhaCungCap.getDiaChi())) return "Địa chỉ không được chứa ký tự đặc biệt";
        return null;
    }
    public NhaCungCapDTO getByMaNhaCungCap(String MaNhaCungCap) {
        for (NhaCungCapDTO NhaCungCap : NhaCungCapList) {
            if (NhaCungCap.getMaNhaCungCap().toLowerCase().equals(MaNhaCungCap.toLowerCase())) {
                return NhaCungCap;
            }
        }
        return null;
    }
    public String getLatest(){
        return NhaCungCapDAO.getLatest();
    }
    public String getMaNhaCungCapByTenNhaCungCap(String TenNhaCungCap){
        for (NhaCungCapDTO NhaCungCap : NhaCungCapList) {
            System.out.println(NhaCungCap.getTenNhaCungCap()+"~"+TenNhaCungCap);
            if(NhaCungCap.getTenNhaCungCap().equals(TenNhaCungCap))
                return NhaCungCap.getMaNhaCungCap();
        }
        return null;
    }
    public String[] getTenTheLoai(){
        String[] TenNhaCungCap = new String[NhaCungCapList.size()];
        for(int i=0;i<TenNhaCungCap.length;i++){
            TenNhaCungCap[i] = NhaCungCapList.get(i).getTenNhaCungCap();
        }
        return TenNhaCungCap;
    }
    }
    
