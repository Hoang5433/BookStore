/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SanPhamDTO;
import Database.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class SanPhamDAO extends Database{
    public ArrayList<SanPhamDTO> fetchAll(){
        ArrayList<SanPhamDTO> SanPhamList = new ArrayList<>();
        SanPhamDTO SanPham = new SanPhamDTO();
        Query ="SELECT *,CAST(REGEXP_REPLACE(masanpham, '[A-Z]+', '') As unsigned) as replaced" +
" FROM sanpham " +
"order by replaced";
        query(Query);
        try {
            while(Result.next()){
                SanPham = new SanPhamDTO();
                SanPham.setMaSanPham(Result.getString(1));
                SanPham.setTenSanPham(Result.getString(2));
                SanPham.setMaNhaXuatBan(Result.getString(3));
                SanPham.setMaTacGia(Result.getString(4));
                SanPham.setMaTheLoai(Result.getString(5));
                SanPham.setGia(Result.getString(6));
                SanPham.setSoLuong(Result.getString(7));
                SanPham.setImageSource(Result.getString(8));
                SanPhamList.add(SanPham);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return SanPhamList;
    }
    
    public void add(SanPhamDTO SanPham){
        insert(SanPham);
    }
    public void remove(SanPhamDTO SanPham){
        remove(SanPham);
    }
    public void edit(SanPhamDTO SanPham){
        update(SanPham);
    }
    public String getLatest(){
        Query = "SELECT COUNT(*) FROM SANPHAM";
        query(Query);
        try {
            Result.next();
            return "SP"+(Result.getInt(1)+1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        return null;
    }
}
