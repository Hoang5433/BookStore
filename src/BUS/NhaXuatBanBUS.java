/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhaXuatBanDAO;
import DTO.NhaXuatBanDTO;
import java.util.ArrayList;
import static quanlycuahangsach.Regexp.DiaChi;
import static quanlycuahangsach.Regexp.MaNhaCungCap;
import static quanlycuahangsach.Regexp.MaNhaXuatBan;
import static quanlycuahangsach.Regexp.SoDienThoai;
import static quanlycuahangsach.Regexp.Ten;

/**
 *
 * @author admin
 */
public class NhaXuatBanBUS {
    public ArrayList<NhaXuatBanDTO> NhaXuatBanList;
    public NhaXuatBanDAO NhaXuatBanDAO;
    public NhaXuatBanBUS(){
        NhaXuatBanList = new ArrayList<>();
        NhaXuatBanDAO = new NhaXuatBanDAO();
        fetchAll();
    }
    public void fetchAll(){
        NhaXuatBanList = NhaXuatBanDAO.fetchAll();
    }
    public boolean Exists(String MaNhaXuatBan){
        for(NhaXuatBanDTO NhaXuatBan:NhaXuatBanList){
            if(NhaXuatBan.getMaNhaXuatBan().equalsIgnoreCase(MaNhaXuatBan)) return true;
        }
        return false;
    }
    public String Validation(NhaXuatBanDTO NhaXuatBan){
        if(!MaNhaXuatBan(NhaXuatBan.getMaNhaXuatBan())) return "Mã nhà xuất bản phải có dạng NXB";
        if(!Ten(NhaXuatBan.getTenNhaXuatBan())) return "Tên nhà xuât bản không được bỏ trống";
        return null;
    }
    public String getMaByName(String TenNhaXuatBan){
        for(NhaXuatBanDTO NhaXuatBan:NhaXuatBanList){
            if(NhaXuatBan.getTenNhaXuatBan().equals(TenNhaXuatBan)) return NhaXuatBan.getMaNhaXuatBan();
        }
        return null;
    }    
    public boolean add(NhaXuatBanDTO NhaXuatBan){
        if(Exists(NhaXuatBan.getMaNhaXuatBan())){ GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Mã đã tồn tại, mã mới là: "+getLatest(), 2);
            return false;
        }
        else {
            String Error = Validation(NhaXuatBan);
            if(Error == null){
            NhaXuatBanDAO.add(NhaXuatBan);
            NhaXuatBanList = NhaXuatBanDAO.fetchAll();
            return true;
            }
            else
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
             return false;
                }        
    }
    public String getTenByMa(String MaNhaXuatBan){
        for(NhaXuatBanDTO NhaXuatBan:NhaXuatBanList){
            if(NhaXuatBan.getMaNhaXuatBan().equalsIgnoreCase(MaNhaXuatBan)) return NhaXuatBan.getTenNhaXuatBan();
        }
        return null;
    }
    public NhaXuatBanDTO getByMaNhaXuatBan(String MaNhaXuatBan){
        for(NhaXuatBanDTO NhaXuatBan:NhaXuatBanList){
            if(NhaXuatBan.getMaNhaXuatBan().equalsIgnoreCase(MaNhaXuatBan)) return NhaXuatBan;
        }
        return null;
    }
    public String[] getTenNhaXuatBan(){
        String[] TenNhaXuatBan = new String[NhaXuatBanList.size()+1];
        TenNhaXuatBan[0] = "Nhà xuất bản";
        for(int i=1;i<TenNhaXuatBan.length;i++){
            TenNhaXuatBan[i] = NhaXuatBanList.get(i-1).getTenNhaXuatBan();
        }
        return TenNhaXuatBan;
    }
    public String[] getTenNhaXuatBanNoTitle(){
        String[] TenNhaXuatBan = new String[NhaXuatBanList.size()];
       
        for(int i=0;i<TenNhaXuatBan.length;i++){
            TenNhaXuatBan[i] = NhaXuatBanList.get(i).getTenNhaXuatBan();
        }
        return TenNhaXuatBan;
    }    
    public String getLatest(){
        return NhaXuatBanDAO.getLatest();
    }
            public void edit(NhaXuatBanDTO NhaXuatBan){
        NhaXuatBanDAO.edit(NhaXuatBan);
        fetchAll();
        GUI.QuanLyDanhMuc.NhaXuatBanJPanel.fetchAll();
    }
}
