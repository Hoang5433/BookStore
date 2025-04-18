/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChuongTrinhKhuyenMaiDTO;
import DTO.DonHangDTO;
import java.util.ArrayList;
import quanlycuahangsach.quanlycuahangsach;

/**
 *
 * @author HUUNHAN
 */
public class DonHangDAO extends Database.Database {

    public ArrayList<DonHangDTO> fetchAll() {
        ArrayList<DonHangDTO> DonHangList = new ArrayList<>();
        DonHangDTO DonHang = new DonHangDTO();
        Query ="SELECT *,CAST(REGEXP_REPLACE(madonhang, '[A-Z]+', '') As unsigned) as replaced" +
" FROM donhang " +
"order by replaced";
        query(Query);
        try {
            while (Result.next()) {
                DonHang = new DonHangDTO();
                DonHang.setMaDonHang(Result.getString(1));
                DonHang.setMaKhachHang(Result.getString(2));
                DonHang.setMaNhanVien(Result.getString(3));
                DonHang.setNgayXuat(Result.getString(4));
                DonHang.setPhiVanChuyen(Result.getString(7));
                DonHang.setMaCode(Result.getString(5));
                DonHang.setTongTien(Result.getString(9));
                DonHang.setGiamGia(Result.getString(8));
                DonHang.setTamTinh(Result.getString(6));
                DonHang.setTrangThai(Result.getString(10));
                DonHangList.add(DonHang);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return DonHangList;

    }

    public void add(DonHangDTO DonHang) {
        Query = "INSERT INTO `donhang`(`madonhang`, `makhachhang`, `manhanvien`, "
                + "`ngayxuat`, `macode`, `tamtinh`, `phivanchuyen`, `giamgia`, `tongtien`, `trangthai`)"
                + " VALUES ('"
                + DonHang.getMaDonHang()+"','"
                + DonHang.getMaKhachHang()+"','"
                + DonHang.getMaNhanVien()+"','"
                + DonHang.getNgayXuat()+"',"
                + (DonHang.getMaCode() == null ? null : ("'"+DonHang.getMaCode()+"'"))+",'"
                + DonHang.getTamTinh()+"','"
                + DonHang.getPhiVanChuyen()+"','"
                + DonHang.getGiamGia()+"','"
                + DonHang.getTongTien()+"','"
                + DonHang.getTrangThai()+"')";
                queryUpdate(Query);
    }

    public void edit(DonHangDTO DonHang) {
        Query = "Update DonHang SET TRANGTHAI='"+DonHang.getTrangThai()+"' WHERE MADONHANG='"+DonHang.getMaDonHang()+"'";
        queryUpdate(Query);
    }

    public void remove(DonHangDTO DonHang) {
        delete(DonHang);
    }

    public String getLatest() {
        Query = "SELECT COUNT(*) FROM DonHang";
        query(Query);
        try {
            Result.next();
            return "DH" + (Result.getInt(1) + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public static ArrayList<String[]> getSoLuongVaoNgay(String MaDonHang) {
        Query = "SELECT donhang.ngayxuat,chitietdonhang.soluong"
                + " FROM donhang join chitietdonhang on donhang.madonhang=chitietdonhang.madonhang"
                + " WHERE donhang.madonhang='" + MaDonHang + "'";
        ArrayList<String[]> result = new ArrayList<>();
        query(Query);
        try {
            while (Result.next()) {
                String[] data = new String[2];
                data[0] = Result.getString(1);
                data[1] = Result.getString(2);
                result.add(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        };

        return result;
    }
    public static ArrayList<String[]> getSanPhamVaoNgay(String MaDonHang){
        Query = "SELECT donhang.ngayxuat,donhang.trangthai,chitietdonhang.masanpham,chitietdonhang.soluong"
                + " FROM donhang join chitietdonhang on donhang.madonhang=chitietdonhang.madonhang"
                + " WHERE donhang.madonhang='" + MaDonHang + "'";
        ArrayList<String[]> result = new ArrayList<>();
        query(Query);
        try {
            while (Result.next()) {
                String[] data = new String[4];
                
                data[0] = Result.getString(1);
                data[1] = Result.getString(2);
                data[2] = Result.getString(3);
                data[3] = Result.getString(4);
                result.add(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        };

        return result;
    }
    public static ArrayList<String[]> getSanPhamNgayDoanhThu(String MaDonHang){
        Query = "SELECT donhang.ngayxuat,donhang.trangthai,chitietdonhang.masanpham,chitietdonhang.thanhtien"
                + " FROM donhang join chitietdonhang on donhang.madonhang=chitietdonhang.madonhang"
                + " WHERE donhang.madonhang='" + MaDonHang + "'";
        ArrayList<String[]> result = new ArrayList<>();
        query(Query);
        try {
            while (Result.next()) {
                String[] data = new String[4];
                data[0] = Result.getString(1);
                data[1] = Result.getString(2);
                data[2] = Result.getString(3);
                data[3] = Result.getString(4);
                result.add(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        };

        return result;
    }
    public static ArrayList<DonHangDTO> getTOP10DonHang(){
        ArrayList<DonHangDTO> result = new ArrayList<>();
        Query = "SELECT * FROM donhang order by ngayxuat DESC LIMIT 10";
        query(Query);
        try{
            while(Result.next()){
                DonHangDTO DonHang = new DonHangDTO();
                DonHang.setMaDonHang(Result.getString(1));
                DonHang.setMaKhachHang(Result.getString(2));
                DonHang.setMaNhanVien(Result.getString(3));
                DonHang.setNgayXuat(Result.getString(4));
                DonHang.setPhiVanChuyen(Result.getString(7));
                DonHang.setMaCode(Result.getString(5));
                DonHang.setTongTien(Result.getString(9));
                DonHang.setGiamGia(Result.getString(8));
                DonHang.setTamTinh(Result.getString(6));
                DonHang.setTrangThai(Result.getString(10));
                result.add(DonHang);
            }
        }catch(Exception ex){ex.printStackTrace();}finally{close();};
        return result;
    }
}
