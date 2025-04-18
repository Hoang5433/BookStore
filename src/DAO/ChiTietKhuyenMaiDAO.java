/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietKhuyenMaiDTO;
import Database.Database;
import java.util.ArrayList;

/**
 *
 * @author TKH
 */
public class ChiTietKhuyenMaiDAO extends Database {

    public ArrayList<ChiTietKhuyenMaiDTO> fetchAll() {
        ArrayList<ChiTietKhuyenMaiDTO> ChiTietKhuyenMaiList = new ArrayList<>();
        ChiTietKhuyenMaiDTO ChiTietKhuyenMai = new ChiTietKhuyenMaiDTO();
        Query ="SELECT *,CAST(REGEXP_REPLACE(machitietkhuyenmai, '[A-Z]+', '') As unsigned) as replaced" +
" FROM chitietkhuyenmai " +
"order by replaced";
        query(Query);
        try {
            while (Result.next()) {
                ChiTietKhuyenMai = new ChiTietKhuyenMaiDTO();
                ChiTietKhuyenMai.setMaChiTietKhuyenMai(Result.getString(1));
                ChiTietKhuyenMai.setMaChuongTrinhKhuyenMai(Result.getString(2));
                ChiTietKhuyenMai.setMaCode(Result.getString(3));
                ChiTietKhuyenMai.setPhanTramKhuyenMai(Result.getString(4));
                ChiTietKhuyenMai.setGiaTienKhuyenMai(Result.getString(5));
                ChiTietKhuyenMai.setGiaTriToiThieuSuDung(Result.getString(6));
                ChiTietKhuyenMai.setGiaTriGiamToiDa(Result.getString(7));
                ChiTietKhuyenMai.setThoiGianBatDauSuDung(Result.getString(8));
                ChiTietKhuyenMai.setThoiGianKetThucSuDung(Result.getString(9));
                ChiTietKhuyenMai.setSoLuong(Result.getString(10));
                ChiTietKhuyenMai.setSoLuongDaDung(Result.getString(11));
                ChiTietKhuyenMaiList.add(ChiTietKhuyenMai);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return ChiTietKhuyenMaiList;
    }

    public void add(ChiTietKhuyenMaiDTO ChiTietKhuyenMai) {
        insert(ChiTietKhuyenMai);
    }

    public void edit(ChiTietKhuyenMaiDTO ChiTietKhuyenMai) {
        update(ChiTietKhuyenMai);
    }

    public void remove(ChiTietKhuyenMaiDTO ChiTietKhuyenMai) {
        delete(ChiTietKhuyenMai);
    }

    public String getLatest() {
        Query = "SELECT COUNT(*) FROM ChiTietKhuyenMai";
        query(Query);
        try {
            Result.next();
            return "CTKM" + (Result.getInt(1) + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public String getSoLuongChiTietKhuyenMai(String MaChuongTrinhKhuyenMai) {
        Query = "Select machuongtrinhkhuyenmai,Count(*) "
                + " from chitietkhuyenmai group by machuongtrinhkhuyenmai"
                + " having machuongtrinhkhuyenmai='" + MaChuongTrinhKhuyenMai + "'";
        query(Query);
        try {
            if(Result.next())
            return Result.getString(2);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return "0";
    }
}
