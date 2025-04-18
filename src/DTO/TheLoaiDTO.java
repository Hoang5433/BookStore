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
public class TheLoaiDTO {
    protected String matheloai,tentheloai;

    public String getMaTheLoai() {
        return matheloai;
    }

    public TheLoaiDTO() {
    }

    public TheLoaiDTO(String matheloai, String tentheloai) {
        this.matheloai = matheloai;
        this.tentheloai = tentheloai;
    }
    
    public String getPrimaryKey(){
        return "MaTheLoai='"+this.getMaTheLoai()+"'";
    }
    public void setMaTheLoai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getTenTheLoai() {
        return tentheloai;
    }

    public void setTenTheLoai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    
}
