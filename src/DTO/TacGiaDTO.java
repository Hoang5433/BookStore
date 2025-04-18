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
public class TacGiaDTO {
    protected String matacgia,tentacgia;

    public TacGiaDTO() {
    }

    public TacGiaDTO(String matacgia, String tentacgia) {
        this.matacgia = matacgia;
        this.tentacgia = tentacgia;
    }
    public String getPrimaryKey(){
        return "MaTacGia='"+ this.getMaTacGia() + "'";
    }
    public void setMaTacGia(String matacgia) {
        this.matacgia = matacgia;
    }

    public void setTenTacGia(String tentacgia) {
        this.tentacgia = tentacgia;
    }

    public String getMaTacGia() {
        return matacgia;
    }

    public String getTenTacGia() {
        return tentacgia;
    }
}
