/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TheLoaiDTO;
import Database.Database;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class TheLoaiDAO extends Database{
    public ArrayList<TheLoaiDTO> fetchAll(){
        ArrayList<TheLoaiDTO> TheLoaiList = new ArrayList<>();
        TheLoaiDTO TheLoai = new TheLoaiDTO();
        select(TheLoai,null);
        try {
            while(Result.next()){
                TheLoai = new TheLoaiDTO();
                TheLoai.setMaTheLoai(Result.getString(1));
                TheLoai.setTenTheLoai(Result.getString(2));
                TheLoaiList.add(TheLoai);
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        return TheLoaiList;
    }
    public void add(TheLoaiDTO TheLoai){
        insert(TheLoai);
    }
    public void edit(TheLoaiDTO TheLoai){
        update(TheLoai);
    }
    public void remove(TheLoaiDTO TheLoai){
        delete(TheLoai);
    }
    

    
    public String getLatest(){
        Query = "SELECT COUNT(*) FROM THELOAI";
        query(Query);
        try{
            Result.next();
            return "TL"+(Result.getInt(1)+1);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close();
        }
        
        return null;
    }    
}
