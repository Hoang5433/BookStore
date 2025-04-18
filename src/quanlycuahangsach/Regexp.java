/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahangsach;

import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class Regexp {
    private static String PATTERN_EMAIL = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
    private static String PATTERN_MAKHACHHANG = "KH\\d+";
    private static String PATTERN_STRING = "[^\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\=\\\\\\{\\}\\[\\]\\.\\?\\:\\;\\'\"]+";
    private static String PATTERN_MANHANVIEN = "^NV\\d+";
    private static String PATTERN_NAMSINH = "\\d{4}";
    private static String PATTERN_DONGIA = "\\d+";
    private static String PATTERN_SODIENTHOAI = "^0\\d{9}";
    private static String PATTERN_SO = "\\d+";
    private static String PATTERN_MUCLUONG = "\\d+";
    private static String PATTERN_MATHELOAI = "^TL\\d+";
    private static String PATTERN_MANHAXUATBAN = "^NXB\\d+";
    private static String PATTERN_MATACGIA = "^TG\\d+";
    private static String PATTERN_MANHACUNGCAP = "^NCC\\d+";
    private static String PATTERN_TENSIZE = "\\d+";
    private static String PATTERN_MADONHANG = "^DH\\d+";
    private static String PATTERN_TENMAU = "[^\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\=\\\\\\{\\}\\[\\]\\.\\?\\:\\;\\'\"\\d]+";
    private static String PATTERN_MACHUONGTRINHKHUYENMAI = "^CT\\d+";
    private static String PATTERN_MACHITIETKHUYENMAI ="^CTKM\\d+";
    private static String PATTERN_SOLUONG ="^[1-9]\\d*$|^0$";
    private static String PATTERN_MAPHIEUNHAP = "^PN\\d+";
    private static String PATTERN_REPLACE ="\\W";
    private static String PATTERN_DONGIAPHIEUNHAP="^[1-9][0-9]{3,}";
    private static String PATTERN_SOLUONGPHIEUNHAP="^[1-9][0-9]*";
    public static boolean DonGiaPhieuNhap(String dongia){
        return Pattern.matches(PATTERN_DONGIAPHIEUNHAP,dongia);
    }
    public static boolean SoLuongPhieuNhap(String soluong){
        return Pattern.matches(PATTERN_SOLUONGPHIEUNHAP,soluong);
    }
    public static String Replace(String Data){
        return Data.replaceAll(PATTERN_REPLACE, "");
    }
    public static boolean MaChiTietKhuyenMai(String MaChiTietKhuyenMai){
        return Pattern.matches(PATTERN_MACHITIETKHUYENMAI,MaChiTietKhuyenMai);
    }
    public static boolean MaNhaCungCap(String MaNhaCungCap) {
        return Pattern.matches(PATTERN_MANHACUNGCAP, MaNhaCungCap);

    }
    public static boolean MaPhieuNhap(String MaPhieuNhap){
        return Pattern.matches(PATTERN_MAPHIEUNHAP, MaPhieuNhap);
    }
    public static boolean MaKhachHang(String MaKhachHang) {
        return Pattern.matches(PATTERN_MAKHACHHANG, MaKhachHang);
    }

    public static boolean Email(String Email) {
        return Pattern.matches(PATTERN_EMAIL, Email);
    }
    public static boolean DonGia(String DonGia){
            return Pattern.matches(PATTERN_DONGIA, DonGia);
        }
    public static boolean MaChuongTrinhKhuyenMai(String MaChuongTrinhKhuyenMai) {
        return Pattern.matches(PATTERN_MACHUONGTRINHKHUYENMAI, MaChuongTrinhKhuyenMai);
    }
    public static  boolean  MaDonHang(String MaDonHang){
        return Pattern.matches(PATTERN_MADONHANG, MaDonHang);
    }
    public static boolean So(String So) {
        return Pattern.matches(PATTERN_SO, So);
    }

    public static boolean MaNhanVien(String MaNhanVien) {
        return Pattern.matches(PATTERN_MANHANVIEN, MaNhanVien);
    }

    public static boolean MaNhaXuatBan(String MaNhaXuatBan) {
        return Pattern.matches(PATTERN_MANHAXUATBAN, MaNhaXuatBan);
    }

    public static boolean MaTheLoai(String MaTheLoai) {
        return Pattern.matches(PATTERN_MATHELOAI, MaTheLoai);
    }

    public static boolean TenMau(String TenMau) {
        return Pattern.matches(PATTERN_TENMAU, TenMau);
    }

    public static boolean TenSize(String TenSize) {
        return Pattern.matches(PATTERN_TENSIZE, TenSize);
    }

    public static boolean MaTacGia(String MaTacGia) {
        return Pattern.matches(PATTERN_MATACGIA, MaTacGia);
    }

    public static boolean Ten(String Ten) {
        return Pattern.matches(PATTERN_STRING, Ten);
    }

    public static boolean SoDienThoai(String SoDienThoai) {

        return Pattern.matches(PATTERN_SODIENTHOAI, SoDienThoai);
    }

    public static boolean MucLuong(String MucLuong) {
        return Pattern.matches(PATTERN_MUCLUONG, MucLuong);
    }

    public static boolean NamSinh(String NamSinh) {
        return Pattern.matches(PATTERN_NAMSINH, NamSinh) && Integer.parseInt(NamSinh) <= Integer.parseInt(DateTime.getCurrenDateString(1).split("/")[2]) - 18;
    }

    public static boolean DiaChi(String DiaChi) {
        return Pattern.matches(PATTERN_STRING, DiaChi);
    }
    public static boolean SoLuong(String SoLuong){
        return Pattern.matches(PATTERN_SOLUONG,SoLuong);
    }
    
}
