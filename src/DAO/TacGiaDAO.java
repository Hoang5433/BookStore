/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TacGiaDTO;
import Database.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class TacGiaDAO extends Database{
    
    public ArrayList<TacGiaDTO> fetchAll(){
        ArrayList<TacGiaDTO> TacGiaList = new ArrayList<>();
        TacGiaDTO TacGia = new TacGiaDTO();
        select(TacGia,null);
        try {
            while(Result.next()){
                TacGia = new TacGiaDTO();
                TacGia.setMaTacGia(Result.getString(1));
                TacGia.setTenTacGia(Result.getString(2));
                TacGiaList.add(TacGia);
            }
            }catch (Exception e) {
            e.printStackTrace();
        }finally{
            close();
        }
        return TacGiaList;
    }
    public void add(TacGiaDTO TacGia){
        insert(TacGia);
    }
    public void remove(TacGiaDTO TacGia){
        delete(TacGia);
    }
    public void edit(TacGiaDTO TacGia){
        update(TacGia);
    }
    public String getLatest(){
        Query = "SELECT COUNT(*) FROM TACGIA";
        query(Query);
        try{
            Result.next();
            return "TG"+(Result.getInt(1)+1);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close();
        }
        return null;
    }
}
