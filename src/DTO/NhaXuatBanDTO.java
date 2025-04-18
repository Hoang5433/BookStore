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
public class NhaXuatBanDTO {
    protected String MaNhaXuatBan,TenNhaXuatBan;

    public String getMaNhaXuatBan() {
        return MaNhaXuatBan;
    }
    public NhaXuatBanDTO() {}
    public NhaXuatBanDTO(String MaNhaXuatBan, String TenNhaXuatBan) {
        this.MaNhaXuatBan = MaNhaXuatBan;
        this.TenNhaXuatBan = TenNhaXuatBan;
    }

    public void setMaNhaXuatBan(String MaNhaXuatBan) {
        this.MaNhaXuatBan = MaNhaXuatBan;
    }
    public String getPrimaryKey(){
        return "MaNhaXuatBan='"+this.getMaNhaXuatBan()+"'";
    }
    public String getTenNhaXuatBan() {
        return TenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String TenNhaXuatBan) {
        this.TenNhaXuatBan = TenNhaXuatBan;
    }


}
