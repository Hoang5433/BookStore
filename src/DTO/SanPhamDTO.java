/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author admin
 */
public class SanPhamDTO {
    protected String MaSanPham,TenSanPham,MaNhaXuatBan,MaTacGia,MaTheLoai,Gia,SoLuong,ImageSource;

    public SanPhamDTO(String MaSanPham, String TenSanPham, String MaNhaXuatBan, String MaTacGia, String MaTheLoai, String Gia, String SoLuong, String ImageSource) {
        this.MaSanPham = MaSanPham;
        this.TenSanPham = TenSanPham;
        this.MaNhaXuatBan = MaNhaXuatBan;
        this.MaTacGia = MaTacGia;
        this.MaTheLoai = MaTheLoai;
        this.Gia = Gia;
        this.SoLuong = SoLuong;
        this.ImageSource = ImageSource;
    }
    public String getPrimaryKey(){
        return "MaSanPham='"+this.getMaSanPham()+"'";
    }
    public SanPhamDTO() {
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getMaNhaXuatBan() {
        return MaNhaXuatBan;
    }

    public void setMaNhaXuatBan(String MaNhaXuatBan) {
        this.MaNhaXuatBan = MaNhaXuatBan;
    }

    public String getMaTacGia() {
        return MaTacGia;
    }

    public void setMaTacGia(String MaTacGia) {
        this.MaTacGia = MaTacGia;
    }

    public String getMaTheLoai() {
        return MaTheLoai;
    }

    public void setMaTheLoai(String MaTheLoai) {
        this.MaTheLoai = MaTheLoai;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String Gia) {
        this.Gia = Gia;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getImageSource() {
        return ImageSource;
    }

    public void setImageSource(String ImageSource) {
        this.ImageSource = ImageSource;
    }
}
