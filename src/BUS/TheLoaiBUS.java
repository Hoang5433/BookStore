/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.TheLoaiDAO;
import DTO.TheLoaiDTO;
import java.util.ArrayList;
import static quanlycuahangsach.Regexp.MaTheLoai;
import static quanlycuahangsach.Regexp.Ten;

/**
 *
 * @author admin
 */
public class TheLoaiBUS {
    public ArrayList<TheLoaiDTO> TheLoaiList;
    public TheLoaiDAO TheLoaiDAO;
    
    public TheLoaiBUS(){
        TheLoaiList = new ArrayList<>();
        TheLoaiDAO = new TheLoaiDAO();
        fetchAll();
    }
    public TheLoaiDTO getByMaTheLoai(String matheloai){
        for(TheLoaiDTO TheLoai:TheLoaiList)
            if(TheLoai.getMaTheLoai().equalsIgnoreCase(matheloai)) return TheLoai;
        return null;
    }
    public void fetchAll(){
        TheLoaiList = TheLoaiDAO.fetchAll();
    }
    public boolean Exists(String MaTheLoai){
        for(TheLoaiDTO NhaTheLoai:TheLoaiList){
            if(NhaTheLoai.getMaTheLoai().equalsIgnoreCase(MaTheLoai)) return true;
        }
        return false;
    }
    public String[] getTenTheLoai(){
        String[] TenTheLoai = new String[TheLoaiList.size()+1];
        TenTheLoai[0] = "Thể loại";
        for(int i=1;i<TenTheLoai.length;i++){
            TenTheLoai[i] = TheLoaiList.get(i-1).getTenTheLoai();
        }
        return TenTheLoai;
    }
    public String[] getTenTheLoaiNoTitle(){
        String[] TenTheLoai = new String[TheLoaiList.size()];
        
        for(int i=0;i<TenTheLoai.length;i++){
            TenTheLoai[i] = TheLoaiList.get(i).getTenTheLoai();
        }
        return TenTheLoai;
    }    
    public String Validation(TheLoaiDTO TheLoai){
        if(!MaTheLoai(TheLoai.getMaTheLoai())) return "Mã thể loại phải có dạng TL";
        if(!Ten(TheLoai.getTenTheLoai())) return "Tên thể loại không được bỏ trống";
        return null;
    }    
    public boolean add(TheLoaiDTO TheLoai){
        if(Exists(TheLoai.getMaTheLoai())){ GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Mã đã tồn tại, mã mới là: "+getLatest(), 2);
            return false;
        }
        else {
            String Error = Validation(TheLoai);
            if(Error == null){
            TheLoaiDAO.add(TheLoai);
            TheLoaiList = TheLoaiDAO.fetchAll();
            return true;
            }
            else
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
             return false;
                }        
    }
    public String getTenByMa(String MaTheLoai){
        for(TheLoaiDTO TheLoai:TheLoaiList){
            if(TheLoai.getMaTheLoai().equalsIgnoreCase(MaTheLoai)) return TheLoai.getTenTheLoai();
        }
        return null;
    }    
    public String getMaByName(String TenTheLoai){
        for(TheLoaiDTO TheLoai:TheLoaiList){
            if(TheLoai.getTenTheLoai().equals(TenTheLoai)) return TheLoai.getMaTheLoai();
        }
        return null;
    }    
    public String getLatest(){
        return TheLoaiDAO.getLatest();
    }    
        public void edit(TheLoaiDTO TheLoai){
        TheLoaiDAO.edit(TheLoai);
        fetchAll();
        GUI.QuanLyDanhMuc.TheLoaiJPanel.fetchAll();
    }
        
}
