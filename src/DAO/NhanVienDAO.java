/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhanVienDTO;
import java.util.ArrayList;
import quanlycuahangsach.quanlycuahangsach;

/**
 *
 * @author Administrator
 */
public class NhanVienDAO extends Database.Database{
    public ArrayList<NhanVienDTO> fetchAll(){
        ArrayList<NhanVienDTO> NhanVienList = new ArrayList<>();
        NhanVienDTO NhanVien = new NhanVienDTO();
        Query = "SELECT *,CAST(REGEXP_REPLACE(manhanvien, '[A-Z]+', '') As unsigned) as replaced\n" +
" FROM NHANVIEN\n" +
"order by replaced";
        query(Query);
        try{
            while(Result.next()){
                NhanVien = new NhanVienDTO();
                NhanVien.setMaNhanVien(Result.getString(1));
                NhanVien.setHoTen(Result.getString(2));
                NhanVien.setGioiTinh(Result.getString(3));
                NhanVien.setNamSinh(Result.getString(4));
                NhanVien.setSoDienThoai(Result.getString(5));
                NhanVien.setDiaChi(Result.getString(6));
                NhanVien.setTrangThai(Result.getString(7));
                NhanVien.setLuong(Result.getString(8));
                NhanVien.setNgayVaoLam(Result.getString(9));
                NhanVien.setCaLam(Result.getString(10));
                NhanVien.setImgSource(Result.getString(11));
                NhanVienList.add(NhanVien);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return NhanVienList;
    }
    public static void getById(String MaNhanVien){
       
    }
    public void add(NhanVienDTO NhanVien){
        insert(NhanVien);
    }
    public void edit(NhanVienDTO NhanVien){
        update(NhanVien);
    }
    public void remove(NhanVienDTO NhanVien){
        delete(NhanVien);
    }
    public static void getAuth(String MaNhanVien){
         try{
             ConnectOpen();
             Query = "Select * From NhanVien Where MaNhanVien='"+MaNhanVien+"'";
             query(Query);
             if(Result.next()){
                quanlycuahangsach.currentNhanVien = new NhanVienDTO();
                quanlycuahangsach.currentNhanVien.setMaNhanVien(Result.getString(1));
                quanlycuahangsach.currentNhanVien.setHoTen(Result.getString(2));
                quanlycuahangsach.currentNhanVien.setGioiTinh(Result.getString(3));
                quanlycuahangsach.currentNhanVien.setNamSinh(Result.getString(4));
                quanlycuahangsach.currentNhanVien.setSoDienThoai(Result.getString(5));
                quanlycuahangsach.currentNhanVien.setDiaChi(Result.getString(6));
                quanlycuahangsach.currentNhanVien.setTrangThai(Result.getString(7));
                quanlycuahangsach.currentNhanVien.setLuong(Result.getString(8));
                quanlycuahangsach.currentNhanVien.setNgayVaoLam(Result.getString(9));
                quanlycuahangsach.currentNhanVien.setCaLam(Result.getString(10));
                quanlycuahangsach.currentNhanVien.setImgSource(Result.getString(11));
             }
         }catch(Exception ex){
             ex.printStackTrace();
         }finally{
             close();
           }
         
    }
    public String getLatest() {
        Query = "Select count(*) from  nhanvien";
        query(Query);
        try {
            Result.next();
            return "NV" + (Result.getInt(1) + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return null;
    }    
}
