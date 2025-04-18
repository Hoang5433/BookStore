///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package DataThongKe;
//
//import static jdk.javadoc.internal.doclets.toolkit.util.Utils.toLowerCase;
//
//
//
///**
// *
// * @author HUUNHAN
// */
//public class DataThongKeNhanVien {
//    public static String[] getTrangThaiNhanVien(){
//       String[] temp = new String[quanlycuahangsach.quanlycuahangsach.NhanVienBUS.NhanVienList.size()];
//       for(int i=0;i<quanlycuahangsach.quanlycuahangsach.NhanVienBUS.NhanVienList.size();i++){
//           temp[i]=quanlycuahangsach.quanlycuahangsach.NhanVienBUS.NhanVienList.get(i).getTrangThai();
//       }
//       return temp;
//    }
//    public static int ThongKeSoLuongNhanVienConLam(){
//        int SoLuong=0;
//        String[] TrangThaiNhanVien = getTrangThaiNhanVien();
//        for (String TrangThaiNhanVien1 : TrangThaiNhanVien) {
//            if (toLowerCase(TrangThaiNhanVien1).equals(toLowerCase("Còn làm"))) {
//                SoLuong++;
//            }
//        }
//        return SoLuong;
//    }
//}
