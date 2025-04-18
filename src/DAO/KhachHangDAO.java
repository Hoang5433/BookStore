/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhachHangDTO;
import Database.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlycuahangsach.quanlycuahangsach;

/**
 *
 * @author admin
 */
public class KhachHangDAO extends Database{
    public ArrayList<KhachHangDTO> fetchAll(){
        ArrayList<KhachHangDTO> KhachHangList= new ArrayList<>();
        KhachHangDTO KhachHang = new KhachHangDTO();
        Query ="SELECT *,CAST(REGEXP_REPLACE(makhachhang, '[A-Z]+', '') As unsigned) as replaced" +
" FROM khachhang " +
"order by replaced";
        query(Query);
        try {
            while(Result.next()){
                KhachHang = new KhachHangDTO();
                KhachHang.setMaKhachHang(Result.getString(1));
                KhachHang.setHoTen(Result.getString(2));
                KhachHang.setSoDienThoai(Result.getString(3));
                KhachHang.setDiaChi(Result.getString(4));
                KhachHang.setEmail(Result.getString(5));
                KhachHangList.add(KhachHang);                
            }
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        return KhachHangList;
    }
   public void add(KhachHangDTO KhachHang){
        insert(KhachHang);
    }
    public void edit(KhachHangDTO KhachHang){
        update(KhachHang);
    }
    public void remove(KhachHangDTO KhachHang){
        delete(KhachHang);
    }   
    public String getLatest() {
        Query = "Select count(*) from  khachhang";
        query(Query);
        try {
            Result.next();
            return "KH" + Result.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return null;
    }  

}
