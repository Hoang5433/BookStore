/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhaXuatBanDTO;
import Database.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class NhaXuatBanDAO extends Database{
    public ArrayList<NhaXuatBanDTO> fetchAll(){
        ArrayList<NhaXuatBanDTO> NhaXuatBanList = new ArrayList<>();
        NhaXuatBanDTO NhaXuatBan = new NhaXuatBanDTO();
        select(NhaXuatBan,null);
        try {
            while(Result.next()){
                NhaXuatBan = new NhaXuatBanDTO();
                NhaXuatBan.setMaNhaXuatBan(Result.getString(1));
                NhaXuatBan.setTenNhaXuatBan(Result.getString(2));
                NhaXuatBanList.add(NhaXuatBan);
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        return NhaXuatBanList;
    }
    public void add(NhaXuatBanDTO NhaXuatBan){
        insert(NhaXuatBan);
    }
    public void edit(NhaXuatBanDTO NhaXuatBan){
        update(NhaXuatBan);
    }
    public void remove(NhaXuatBanDTO NhaXuatBan){
        delete(NhaXuatBan);
    }
    
    public String getLatest(){
        Query = "SELECT COUNT(*) FROM NHAXUATBAN";
        query(Query);
        try{
            Result.next();
            return "NXB"+(Result.getInt(1)+1);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close();
        }
        
        return null;
    }
}
