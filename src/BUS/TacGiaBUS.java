/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.TacGiaDAO;
import DTO.TacGiaDTO;
import java.util.ArrayList;
import static quanlycuahangsach.Regexp.MaTacGia;
import static quanlycuahangsach.Regexp.Ten;

/**
 *
 * @author admin
 */
public class TacGiaBUS {
    public ArrayList<TacGiaDTO> TacGiaList;
    public TacGiaDAO TacGiaDAO;
    public TacGiaBUS(){
        TacGiaList = new ArrayList<>();
        TacGiaDAO = new TacGiaDAO();
        fetchAll();
    }
    public void fetchAll(){
        TacGiaList = TacGiaDAO.fetchAll();
    }
    
    public boolean Exists(String MaTacGia){
        for(TacGiaDTO TacGia:TacGiaList){
            if(TacGia.getMaTacGia().equalsIgnoreCase(MaTacGia)) return true;
        }
        return false;
    }
    public String Validation(TacGiaDTO TacGia){
        if(!MaTacGia(TacGia.getMaTacGia())) return "Mã tác giả phải có dạng TG";
        if(!Ten(TacGia.getTenTacGia())) return "Tên tác giả không được chứa ký tự đặc biệt";
        return null;
    }
    public boolean add(TacGiaDTO TacGia){
        if(Exists(TacGia.getMaTacGia())){ GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi","Mã đã tồn tại, mã mới là: "+getLatest(), 2);
            return false;
        }
        else {
            String Error = Validation(TacGia);
            if(Error == null){
            TacGiaDAO.add(TacGia);
            TacGiaList = TacGiaDAO.fetchAll();
            return true;
            }
            else
            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
             return false;
                }        
    }  
    public String[] getTenTacGia(){
        String[] TenTacGia = new String[TacGiaList.size()+1];
        TenTacGia[0] = "Tác giả";
        for(int i=1;i<TenTacGia.length;i++){
            TenTacGia[i] = TacGiaList.get(i-1).getTenTacGia();
        }
        
        return TenTacGia;
    } 
    public String[] getTenTacGiaNoTitle(){
        String[] TenTacGia = new String[TacGiaList.size()];
        
        for(int i=0;i<TenTacGia.length;i++){
            TenTacGia[i] = TacGiaList.get(i).getTenTacGia();
        }
        
        return TenTacGia;
    }     
    public  TacGiaDTO getByMaTacGia(String matacgia){
        for(TacGiaDTO TacGia:TacGiaList)
            if(TacGia.getMaTacGia().equalsIgnoreCase(matacgia)) return TacGia;
        return null;
    }
    public String getLatest(){
        return TacGiaDAO.getLatest();
    }
    public void edit(TacGiaDTO TacGia) {
        TacGiaDAO.edit(TacGia);
        fetchAll();
        GUI.QuanLyDanhMuc.TacGiaJPanel.fetchAll();
    }
    public String getTenByMa(String MaTacGia){
        for(TacGiaDTO TacGia:TacGiaList){
            if(TacGia.getMaTacGia().equalsIgnoreCase(MaTacGia)) return TacGia.getTenTacGia();
        }
        return null;
    }
    public String getMaByName(String TenTacGia){
        for(TacGiaDTO TacGia:TacGiaList){
            if(TacGia.getTenTacGia().equals(TenTacGia)) return TacGia.getMaTacGia();
        }
        return null;
    }
    public void remove(TacGiaDTO TacGia){
    }
}
