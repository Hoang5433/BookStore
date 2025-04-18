-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 28, 2020 at 07:59 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlycuahangsach`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(155) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Position` varchar(155) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Nhân Viên'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`username`, `password`, `role`, `Position`) VALUES
('admin', 'admin', 'admin', 'Admin'),
('NV1', '123456', '011101011101', 'Nhân viên'),
('NV10', '123456', '000000000001', 'Nhân viên'),
('NV11', '123456', '000000000001', 'Nhân viên'),
('NV2', '123456', '111111111101', 'Nhân viên'),
('NV3', '123456', '000010000101', 'Nhân viên'),
('NV4', '123456', '101010101001', 'Nhân viên'),
('NV5', '123456', '001101110001', 'Nhân viên'),
('NV6', '123456', '001100111001', 'Nhân viên'),
('NV7', '123456', '000000001001', 'Nhân viên'),
('NV8', '123456', '00000000000', 'Nhân viên'),
('NV9', '123456', '00000000000', 'Nhân viên');

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `madonhang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `masanpham` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `soluong` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dongia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `thanhtien` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`madonhang`, `masanpham`, `soluong`, `dongia`, `thanhtien`) VALUES
('DH1', 'SP1', '1', '300000', '300000'),
('DH2', 'SP5', '1', '500000', '500000'),
('DH3', 'SP3', '1', '400000', '400000'),
('DH4', 'SP1', '1', '300000', '300000'),
('DH5', 'SP1', '1', '300000', '300000'),
('DH6', 'SP9', '1', '700000', '700000'),
('DH7', 'SP1', '1', '300000', '300000'),
('DH7', 'SP9', '1', '700000', '700000'),
('DH8', 'SP5', '1', '500000', '500000'),
('DH8', 'SP9', '1', '700000', '700000'),
('DH9', 'SP2', '10', '350000', '3500000'),
('DH9', 'SP4', '10', '450000', '4500000'),
('DH9', 'SP6', '10', '550000', '5500000'),
('DH9', 'SP8', '10', '650000', '6500000'),
('DH9', 'SP5', '20', '500000', '5000000'),
('DH10', 'SP1', '10', '300000', '3000000'),
('DH10', 'SP3', '10', '400000', '4000000'),
('DH10', 'SP5', '10', '500000', '5000000'),
('DH10', 'SP5', '10', '500000', '5000000'),
('DH11', 'SP4', '10', '450000', '4500000'),
('DH12', 'SP1', '3', '300000', '9000000'),
('DH12', 'SP4', '3', '350000', '10500000'),
('DH13', 'SP1', '5', '300000', '15000000'),
('DH14', 'SP1', '50', '300000', '15000000'),
('DH15', 'SP5', '2', '500000', '10000000'),
('DH15', 'SP3', '2', '400000', '8000000'),
('DH16', 'SP3', '2', '400000', '8000000'),
('DH16', 'SP5', '2', '500000', '10000000'),
('DH17', 'SP5', '1', '500000', '5000000'),
('DH18', 'SP9', '7', '700000', '49000000'),
('DH19', 'SP3', '3', '400000', '12000000'),
('DH20', 'SP3', '20', '400000', '80000000'),
('DH21', 'SP3', '1', '400000', '4000000'),
('DH22', 'SP3', '1', '400000', '4000000'),
('DH23', 'SP1', '5', '300000', '15000000'),
('DH23', 'SP3', '4', '400000', '16000000'),
('DH24', 'SP1', '1', '300000', '3000000'),
('DH25', 'SP3', '2', '400000', '8000000'),
('DH26', 'SP3', '20', '400000', '80000000'),
('DH27', 'SP3', '4', '400000', '16000000'),
('DH27', 'SP4', '10', '350000', '35000000'),
('DH28', 'SP4', '20', '350000', '27500000'),
('DH28', 'SP6', '50', '550000', '27500000'),
('DH29', 'SP7', '40', '600000', '24000000'),
('DH30', 'SP8', '120', '650000', '78000000'),
('DH31', 'SP5', '25', '500000', '12500000'),
('DH32', 'SP6', '17', '550000', '9350000'),
('DH32', 'SP4', '10', '350000', '3500000'),
('DH33', 'SP10', '30', '400000', '12000000'),
('DH34', 'SP9', '40', '700000', '28000000'),
('DH35', 'SP9', '20', '700000', '14000000'),
('DH36', 'SP9', '20', '700000', '14000000');

-- --------------------------------------------------------

--
-- Table structure for table `chitietkhuyenmai`
--

CREATE TABLE `chitietkhuyenmai` (
  `machitietkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `machuongtrinhkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `macode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phantramkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `giatienkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `giatritoithieusudung` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `giatrigiamtoida` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `thoigianbatdausudung` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `thoigianketthucsudung` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `soluong` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `soluongdadung` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `chitietkhuyenmai`
--

INSERT INTO `chitietkhuyenmai` (`machitietkhuyenmai`, `machuongtrinhkhuyenmai`, `macode`, `phantramkhuyenmai`, `giatienkhuyenmai`, `giatritoithieusudung`, `giatrigiamtoida`, `thoigianbatdausudung`, `thoigianketthucsudung`, `soluong`, `soluongdadung`) VALUES
('CTKM1', 'CT1', 'MAGIAMGIA1', '15', '0', '300000', '30000', '1577881800', '1609417800', '100', '100'),
('CTKM10', 'CT1', '123123123213', '32', '0', '123123213213', '123213123213', '1592006400', '1592092800', '123', '0'),
('CTKM11', 'CT1', 'KM1', '0', '200000', '500000', '200000', '1592438400', '1592524800', '50', '1'),
('CTKM12', 'CT1', 'KM2', '20', '0', '500000', '250000', '1592438400', '1592524800', '50', '0'),
('CTKM13', 'CT1', 'GIAMGIACUCSOCK', '0', '100000', '400000', '100000', '1592438400', '1592524800', '100', '1'),
('CTKM2', 'CT2', 'MAGIAMGIA2', '0', '20000', '300000', '20000', '1577881800', '1609417800', '30', '27'),
('CTKM3', 'CT1', 'MAGIAMGIA3', '10', '0', '250000', '25000', '1577881800', '1609417800', '50', '20'),
('CTKM4', 'CT2', 'MAGIAMGIA4', '0', '50000', '550000', '55000', '1577881800', '1609417800', '150', '80'),
('CTKM5', 'CT1', 'CODE123', '0', '50000', '200000', '50000', '1591833600', '1591920000', '50', '0'),
('CTKM6', 'CT1', 'CODE3', '0', '50000', '200000', '50000', '1591833600', '1591920000', '50', '0'),
('CTKM7', 'CT20', 'GIAMDOCSANGTAO', '100', '0', '1000', '4000000', '1591833600', '1593475200', '10', '1'),
('CTKM8', 'CT1', 'SAGOBO', '100', '0', '900000', '100000', '1591833600', '1593475200', '100', '1'),
('CTKM9', 'CT1', '123', '15', '0', '30000', '1232133', '1592006400', '1592092800', '123', '0');

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `maphieunhap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `masanpham` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `soluong` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dongia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `thanhtien` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`maphieunhap`, `masanpham`, `soluong`, `dongia`, `thanhtien`) VALUES
('PN1', 'SP1', '50', '300000', '15000000'),
('PN1', 'SP2', '40', '350000', '14000000'),
('PN1', 'SP3', '50', '400000', '20000000'),
('PN2', 'SP4', '30', '450000', '13500000'),
('PN2', 'SP5', '70', '500000', '20000000'),
('PN2', 'SP6', '50', '550000', '27500000'),
('PN2', 'SP7', '40', '600000', '24000000'),
('PN3', 'SP8', '100', '650000', '65000000'),
('PN3', 'SP9', '120', '700000', '84000000'),
('PN4', 'SP1', '20', '300000', '6000000'),
('PN4', 'SP3', '20', '400000', '8000000'),
('PN4', 'SP5', '20', '500000', '10000000'),
('PN5', 'SP2', '30', '350000', '10500000'),
('PN6', 'SP4', '30', '450000', '13500000'),
('PN7', 'SP6', '30', '550000', '16500000'),
('PN8', 'SP8', '30', '650000', '19500000'),
('PN9', 'SP9', '30', '700000', '21000000'),
('PN10', 'SP10', '100', '9000', '900000');

-- --------------------------------------------------------

--
-- Table structure for table `chuongtrinhkhuyenmai`
--

CREATE TABLE `chuongtrinhkhuyenmai` (
  `machuongtrinhkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tenchuongtrinh` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ngaytao` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `chuongtrinhkhuyenmai`
--

INSERT INTO `chuongtrinhkhuyenmai` (`machuongtrinhkhuyenmai`, `tenchuongtrinh`, `ngaytao`) VALUES
('CT1', 'Chương Trình Khuyến Mãi 1', '1567641600'),
('CT10', 'qwe', '1577865600'),
('CT11', 'asd', '1577865600'),
('CT12', '123', '1577865600'),
('CT13', 'Day la CT13', '1577865600'),
('CT14', 'day la ct14', '1577865600'),
('CT15', 'day la chuong trinh 15', '1577865600'),
('CT16', '123123', '1577865600'),
('CT17', 'bnm', '1577865600'),
('CT18', 'Tết Đong Đầy', '1591568882'),
('CT19', 'Xuân Yêu Thương', '1591569287'),
('CT2', 'Chương Trình Khuyến Mãi 2', '1567728000'),
('CT20', 'Công ty phá sản', '1591569576'),
('CT3', 'hello', '1577865600'),
('CT4', 'hi', '1577865600'),
('CT5', 'hule', '1577865600'),
('CT6', 'hu', '1577865600'),
('CT7', 'ha', '1577865600'),
('CT8', 'ho', '1577865600'),
('CT9', 'zxc', '1577865600');

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `madonhang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `makhachhang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manhanvien` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ngayxuat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `macode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tamtinh` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phivanchuyen` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `giamgia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tongtien` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `trangthai` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`madonhang`, `makhachhang`, `manhanvien`, `ngayxuat`, `macode`, `tamtinh`, `phivanchuyen`, `giamgia`, `tongtien`, `trangthai`) VALUES
('DH1', 'KH1', 'NV1', '1590313403', 'MAGIAMGIA1', '300000', '30000', '30000', '300000', '0'),
('DH10', 'KH7', 'NV5', '1589966609', 'MAGIAMGIA1', '17000000', '30000', '30000', '17000000', '1'),
('DH11', 'KH8', 'NV6', '1590699073', 'MAGIAMGIA2', '4500000', '30000', '20000', '4510000', '1'),
('DH12', 'KH10', 'NV1', '1592100274', 'SAGOBO', '1950000', '30000', '100000', '1880000', '1'),
('DH13', 'KH10', 'NV2', '1592101409', 'MAGIAMGIA1', '1500000', '30000', '30000', '1500000', '1'),
('DH14', 'KH10', 'NV1', '1592101579', 'MAGIAMGIA1', '15000000', '30000', '30000', '15000000', '1'),
('DH15', 'KH0', 'NV1', '1592101804', 'MAGIAMGIA1', '1800000', '30000', '30000', '1800000', '1'),
('DH16', 'KH0', 'NV2', '1592102008', NULL, '1800000', '30000', '0', '1830000', '1'),
('DH17', 'KH11', 'NV2', '1592102717', NULL, '500000', '30000', '0', '530000', '1'),
('DH18', 'KH0', 'NV2', '1592103067', 'MAGIAMGIA1', '4900000', '30000', '30000', '4900000', '1'),
('DH19', 'KH0', 'NV2', '1592103332', NULL, '1200000', '30000', '0', '1230000', '1'),
('DH2', 'KH1', 'NV1', '1590333836', 'MAGIAMGIA2', '500000', '30000', '20000', '510000', '1'),
('DH20', 'KH12', 'NV2', '1592141973', NULL, '8000000', '30000', '0', '8030000', '1'),
('DH21', 'KH12', 'NV2', '1592142065', NULL, '400000', '30000', '0', '430000', '1'),
('DH22', 'KH12', 'NV2', '1592142496', NULL, '400000', '30000', '0', '430000', '1'),
('DH23', 'KH0', 'NV2', '1592143880', 'SAGOBO', '3100000', '30000', '100000', '3030000', '1'),
('DH24', 'KH13', 'NV2', '1592143954', NULL, '300000', '30000', '0', '330000', '1'),
('DH25', 'KH10', 'NV2', '1592144264', NULL, '800000', '30000', '0', '830000', '1'),
('DH26', 'KH12', 'NV2', '1592144926', NULL, '8000000', '30000', '0', '8030000', '1'),
('DH27', 'KH10', 'NV1', '1592169753', 'GIAMDOCSANGTAO', '5100000', '30000', '0', '5130000', '1'),
('DH28', 'KH13', 'NV1', '1592217461', 'MAGIAMGIA2', '34500000', '30000', '20000', '34510000', '1'),
('DH29', 'KH10', 'NV2', '1592405614', NULL, '24000000', '30000', '0', '24030000', '1'),
('DH3', 'KH2', 'NV2', '1590924263', 'MAGIAMGIA1', '400000', '30000', '30000', '400000', '1'),
('DH30', 'KH10', 'NV2', '1592406431', NULL, '78000000', '30000', '0', '78030000', '1'),
('DH31', 'KH10', 'NV2', '1592407272', NULL, '12500000\r\n', '300000', '0', '12530000', '1'),
('DH32', 'KH10', 'NV2', '1592408650', NULL, '12850000', '30000', '0', '12880000', '1'),
('DH33', 'KH10', 'NV1', '1592429890', NULL, '12000000', '30000', '0', '12030000', '1'),
('DH34', 'KH13', 'NV2', '1592464305', 'MAGIAMGIA2', '28000000', '30000', '20000', '28010000', '1'),
('DH35', 'KH0', 'NV2', '1592465047', 'KM1', '14000000', '30000', '200000', '13830000', '1'),
('DH36', 'KH10', 'NV2', '1592467750', 'GIAMGIACUCSOCK', '14000000', '30000', '100000', '13930000', '1'),
('DH4', 'KH3', 'NV2', '1590999345', 'MAGIAMGIA1', '300000', '30000', '30000', '300000', '1'),
('DH5', 'KH3', 'NV2', '1591005361', 'MAGIAMGIA2', '300000', '30000', '20000', '310000', '1'),
('DH6', 'KH4', 'NV3', '1591021264', 'MAGIAMGIA2', '700000', '30000', '20000', '710000', '1'),
('DH7', 'KH5', 'NV3', '1591137916', 'MAGIAMGIA2', '1000000', '30000', '20000', '1100000', '1'),
('DH8', 'KH5', 'NV4', '1591180233', 'MAGIAMGIA1', '1200000', '30000', '30000', '1200000', '1'),
('DH9', 'KH6', 'NV4', '1591228092', 'MAGIAMGIA1', '25000000', '30000', '30000', '25000000', '1');

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `makhachhang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hoten` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sodienthoai` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `diachi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`makhachhang`, `hoten`, `sodienthoai`, `diachi`, `email`) VALUES
('KH0', 'Khách Vãng Lai', '0999999999', '272 An Dương Vương P.3 Q.5', 'sagobo@sago.vn'),
('KH1', 'Phan Công Hà', '0916375864', '120 Công Chúa Ngọc Hân', 'phancongha@gmail.com'),
('KH10', 'Phan Công Hà', '0913175755', '233 An Dương Vương', 'phancongha24@gmail.com'),
('KH11', 'Phan Công Hà', '0913154544', '19238 An Dương Vương', 'oancongha2@gmail.com'),
('KH12', 'Nguyen hu nhana', '0775852596', '123 Nhghgh', 'nnnnnguyenn@gamil.com'),
('KH13', 'Phan Công Hà', '0916375645', '12398 An Dương', 'phancongha24@gmail.com'),
('KH2', 'Phan Công Sơn', '0916375861', '120 Công Chúa Ngọc Hân', 'phancongson@gmail.com'),
('KH3', 'Âu Khánh Toàn', '0916375464', '120 Công Chúa Ngọc Hân', 'aukhanhtoan@gmail.com'),
('KH4', 'Nguyễn Hữu Nhân', '0916375864', '120 Công Chúa Ngọc Hân', 'nguyenhuunhan@gmail.com'),
('KH5', 'Nguyễn Minh Phương Nam', '0916375862', '120 Công Chúa Ngọc Hân', 'nguyenminhphuongnam@gmail.com'),
('KH6', 'Hồ Sỹ Đạt', '0916375864', '120 Công Chúa Ngọc Hân', 'hosydat@gmail.com'),
('KH7', 'Trần Quốc Bảo', '0916375863', '120 Công Chúa Ngọc Hân', 'tranquocbao@gmail.com'),
('KH8', 'Thái Kiến Hào', '0916315864', '120 Công Chúa Ngọc Hân', 'thaikienhao@gmail.com'),
('KH9', 'Phan Công Hà', '0123456789', '299 An Dương Vương', 'congha@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `manhacungcap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tennhacungcap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sodienthoai` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `diachi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`manhacungcap`, `tennhacungcap`, `sodienthoai`, `diachi`) VALUES
('NCC1', 'Nhà Cung Cấp A', '0912345678', '512 Vương Diệu'),
('NCC10', 'Nhà Cung Cấp F', '0913175755', '512 An Dương Vương, Phường 12, Quận 5'),
('NCC11', 'Nhà Cung Cấp Đạt', '0912345678', '123 An Bình'),
('NCC12', 'Nhà Cung Cấp O', '0913232455', '120 Nguyễn Văn Cừ,Phường 12, Quận 11'),
('NCC13', 'Nhà Cung Cấp P', '0913175122', '120, An Dương Vương, Phường 12,Quận 8'),
('NCC14', 'Nhà Cung Cấp Hà', '0913175755', '12 Hồ Vĩnh Hy,Phường 12,Quận 11'),
('NCC2', 'Nhà Cung Cấp B', '0912345678', '120 Minh Phụng, Phường 10, Quận 11'),
('NCC3', 'Nhà Cung Cấp C', '0912345678', '120 Minh Phụng, Phường 10, Quận 11'),
('NCC4', 'Nhà Cung Cấp D', '0912345678', '120 Minh Phụng, Phường 10, Quận 11'),
('NCC5', 'Nhà Cung Cấp E', '0912345678', '120 Minh Phụng, Phường 10, Quận 11'),
('NCC6', 'Nhà Cung Cấp F', '0912345678', '120 Công Chúa Ngọc Hân\r\nPhường 12 Quận 11'),
('NCC7', 'Nhà Cung Cấp G ', '0912345678', '120 Công Chúa Ngọc Hân\r\nPhường 12 Quận 11'),
('NCC8', 'Nhà Cung Cấp H', '0912345678', '120 Công Chúa Ngọc Hân\r\nPhường 12 Quận 11'),
('NCC9', 'Nhà Cung Cấp J', '0912345678', '120 Công Chúa Ngọc Hân\r\nPhường 12 Quận 11');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manhanvien` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hoten` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gioitinh` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `namsinh` int(20) NOT NULL,
  `sodienthoai` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `diachi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `trangthai` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Còn làm',
  `luong` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngayvaolam` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `calam` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imgsource` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`manhanvien`, `hoten`, `gioitinh`, `namsinh`, `sodienthoai`, `diachi`, `trangthai`, `luong`, `ngayvaolam`, `calam`, `imgsource`) VALUES
('NV1', 'Phan Công Hà', 'Nữ', 2000, '0916375864', '120 Công Chúa Ngọc Hân', 'Nghỉ làm', '20000', '1589726979', 'Ca chiều', 'NV1.jpg'),
('NV10', 'Lại 1 Ai Đó Trên Mạng', 'Nữ', 1999, '0913175755', 'Ở trên mạng', 'Còn làm', '2999999', '1590835456', 'Ca sáng', 'NV10.jpg'),
('NV11', 'Vũ Thị Hương', 'Nam', 1998, '0913175755', '1238 Đại thế giới,Phường 12,Quận 11', 'Còn làm', '199999', '1590836601', 'Ca sáng', 'default.jpg'),
('NV2', 'Âu Khánh Toàn', 'Nữ', 2000, '0913175755', '120 Công Chúa Ngọc Hân\nPhường 12 Quận 11', 'Còn làm', '12000000', '1590156670', 'Ca tối', 'default.jpg'),
('NV3', 'Phan Công Sơn', 'Nữ', 2000, '0913175755', '120 Công Chúa Ngọc Hân\nPhường 12 Quận 11', 'Còn làm', '50000000', '1590157976', 'Ca sáng', 'NV3.jpg'),
('NV4', 'Nguyễn Hữu Nhân', 'Nữ', 2000, '0913175755', '120 Minh Phụng, Phường 10, Quận 11', 'Nghỉ làm', '20000', '1590159721', 'Ca sáng', 'NV4.jpg'),
('NV5', 'Thái Kiến Hào', 'Nam', 2000, '0916375645', '120 Minh Phụng, Phường 10, Quận 11', 'Còn làm', '250000', '1590159670', 'Ca sáng', NULL),
('NV6', 'Nguyễn Minh Phương Nam', 'Nam', 2000, '0913175755', '120 Công Chúa Ngọc Hân\nPhường 12 Quận 11', 'Còn làm', '20000', '1590178736', 'Ca tối', NULL),
('NV7', 'Trần Quốc Bảo', 'Nam', 2000, '0913175755', '120 Công Chúa Ngọc Hân\nPhường 12 Quận 11', 'Còn làm', '20000', '1590267523', 'Ca chiều', NULL),
('NV8', 'Tôn Thất Nguyên', 'Nam', 2000, '0913175755', '120 Công Chúa Ngọc Hân\nPhường 12 Quận 11', 'Còn làm', '2000000', '1590756609', 'Ca chiều', NULL),
('NV9', 'Ai Đó Trên Mạng', 'Nam', 2000, '0913175755', 'Ở đâu đó trên mạng3', 'Còn làm', '3000000', '1590796007', 'Ca sáng', 'NV9.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `nhaxuatban`
--

CREATE TABLE `nhaxuatban` (
  `manhaxuatban` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `tennhaxuatban` varchar(150) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhaxuatban`
--

INSERT INTO `nhaxuatban` (`manhaxuatban`, `tennhaxuatban`) VALUES
('NXB1', 'Nhà xuất bản 1'),
('NXB2', 'Nhà xuất bản 2'),
('NXB3', 'Nhà xuất bản 3'),
('NXB4', 'Nhà xuất bản 4'),
('NXB5', 'Nhà xuất bản 5'),
('NXB6', 'Nhà xuất bản 6'),
('NXB7', 'Nhà xuất bản 7'),
('NXB8', 'Nhà xuất bản 8'),
('NXB9', 'Nhà xuất bản 9');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maphieunhap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manhanvien` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manhacungcap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ngaynhap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tongtien` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`maphieunhap`, `manhanvien`, `manhacungcap`, `ngaynhap`, `tongtien`) VALUES
('PN1', 'NV1', 'NCC2', '1590313403', '49000000'),
('PN10', 'NV1', 'NCC1', '1592352000', '900000'),
('PN2', 'NV2', 'NCC4', '1590333836', '85000000'),
('PN3', 'NV3', 'NCC6', '1590924263', '149000000'),
('PN4', 'NV4', 'NCC8', '1590999345', '24000000'),
('PN5', 'NV5', 'NCC1', '1591005361', '10500000'),
('PN6', 'NV6', 'NCC3', '1591021264', '13500000'),
('PN7', 'NV7', 'NCC5', '1591137916', '16500000'),
('PN8', 'NV1', 'NCC7', '1591180233', '19500000'),
('PN9', 'NV2', 'NCC9', '1591228092', '21000000');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `masanpham` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `tensanpham` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `manhaxuatban` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `matacgia` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `matheloai` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gia` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `soluong` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `imagesource` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`masanpham`, `tensanpham`, `manhaxuatban`, `matacgia`, `matheloai`, `gia`, `soluong`, `imagesource`) VALUES
('SP1', 'Đắc Nhân Tâm', 'NXB3', 'TG2', 'TL5', '300000', '0', 'tuoitredanggiabaonhieu.png'),
('SP10', 'Tuổi trẻ đáng giá bao nhiêu', 'NXB3', 'TG1', 'TL8', '400000', '70', 'SP10.jpg'),
('SP11', 'SAN PHAM NE HIHI', 'NXB2', 'TG1', 'TL2', '50000000', '0', 'SP11.jpg'),
('SP2', 'Vui vẻ không quạo nha', 'NXB4', 'TG1', 'TL5', '350000', '0', 'default.jpg'),
('SP3', 'Tiếng Anh 3', 'NXB3', 'TG2', 'TL1', '400000', '0', 'default.jpg'),
('SP4', 'Tiếng Anh Chuyên Ngành', 'NXB2', 'TG3', 'TL4', '350000', '0', 'default.jpg'),
('SP5', 'Toán Rời Rạc', 'NXB2', 'TG2', 'TL2', '500000', '10', 'default.jpg'),
('SP6', 'Vui vẻ không quạo nha 6', 'NXB1', 'TG3', 'TL5', '550000', '0', 'SP6.jpg'),
('SP7', 'Vui vẻ không quạo nha 7', 'NXB3', 'TG6', 'TL2', '600000', '0', 'null'),
('SP8', 'Vui vẻ không quạo nha 8', 'NXB4', 'TG1', 'TL5', '650000', '0', 'SP8.jpg'),
('SP9', 'Vui vẻ không quạo nha 9', 'NXB2', 'TG2', 'TL2', '700000', '60', 'null');

-- --------------------------------------------------------

--
-- Table structure for table `sanphamkhuyenmai`
--

CREATE TABLE `sanphamkhuyenmai` (
  `machitietkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `masanpham` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sanphamkhuyenmai`
--

INSERT INTO `sanphamkhuyenmai` (`machitietkhuyenmai`, `masanpham`) VALUES
('CTKM1', 'SP1'),
('CTKM1', 'SP2'),
('CTKM1', 'SP3'),
('CTKM1', 'SP4'),
('CTKM1', 'SP5'),
('CTKM1', 'SP6'),
('CTKM1', 'SP7'),
('CTKM1', 'SP8'),
('CTKM1', 'SP9'),
('CTKM2', NULL),
('CTKM6', NULL),
('CTKM7', 'SP6'),
('CTKM7', 'SP7'),
('CTKM7', 'SP8'),
('CTKM7', 'SP9'),
('CTKM9', NULL),
('CTKM10', 'SP1'),
('CTKM10', 'SP2'),
('CTKM10', 'SP3'),
('CTKM8', NULL),
('CTKM11', NULL),
('CTKM12', 'SP1'),
('CTKM13', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tacgia`
--

CREATE TABLE `tacgia` (
  `matacgia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tentacgia` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tacgia`
--

INSERT INTO `tacgia` (`matacgia`, `tentacgia`) VALUES
('TG1', 'Nguyễn Văn A'),
('TG10', 'Nguyễn Văn K'),
('TG2', 'Nguyễn Văn B'),
('TG3', 'Nguyễn Văn C'),
('TG4', 'Nguyễn Văn D'),
('TG5', 'Nguyễn Văn E'),
('TG6', 'Nguyễn Văn F'),
('TG7', 'Nguyễn Văn G'),
('TG8', 'Nguyễn Văn H'),
('TG9', 'Nguyễn Văn J');

-- --------------------------------------------------------

--
-- Table structure for table `theloai`
--

CREATE TABLE `theloai` (
  `matheloai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tentheloai` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `theloai`
--

INSERT INTO `theloai` (`matheloai`, `tentheloai`) VALUES
('TL1', 'Văn Học'),
('TL2', 'Tiểu thuyết'),
('TL3', 'Thiếu nhi'),
('TL4', 'Khoa học công nghệ - xã hội'),
('TL5', 'Giả tưởng'),
('TL6', 'Chính trị - kinh tế'),
('TL7', 'Giáo trình'),
('TL8', 'Truyện');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD KEY `madonhang` (`madonhang`),
  ADD KEY `masanpham` (`masanpham`);

--
-- Indexes for table `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD PRIMARY KEY (`machitietkhuyenmai`),
  ADD KEY `machuongtrinhkhuyenmai` (`machuongtrinhkhuyenmai`),
  ADD KEY `macode` (`macode`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD KEY `maphieunhap` (`maphieunhap`),
  ADD KEY `mansanpham` (`masanpham`);

--
-- Indexes for table `chuongtrinhkhuyenmai`
--
ALTER TABLE `chuongtrinhkhuyenmai`
  ADD PRIMARY KEY (`machuongtrinhkhuyenmai`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`madonhang`),
  ADD KEY `makhachhang` (`makhachhang`),
  ADD KEY `manhanvien` (`manhanvien`),
  ADD KEY `macode` (`macode`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makhachhang`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`manhacungcap`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manhanvien`);

--
-- Indexes for table `nhaxuatban`
--
ALTER TABLE `nhaxuatban`
  ADD PRIMARY KEY (`manhaxuatban`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `manhanvien` (`manhanvien`),
  ADD KEY `manhacungcap` (`manhacungcap`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masanpham`),
  ADD KEY `manhaxuatban` (`manhaxuatban`),
  ADD KEY `matacgia` (`matacgia`),
  ADD KEY `matheloai` (`matheloai`);

--
-- Indexes for table `sanphamkhuyenmai`
--
ALTER TABLE `sanphamkhuyenmai`
  ADD KEY `machitietkhuyenmai` (`machitietkhuyenmai`),
  ADD KEY `masanpham` (`masanpham`);

--
-- Indexes for table `tacgia`
--
ALTER TABLE `tacgia`
  ADD PRIMARY KEY (`matacgia`);

--
-- Indexes for table `theloai`
--
ALTER TABLE `theloai`
  ADD PRIMARY KEY (`matheloai`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD CONSTRAINT `chitietdonhang_hoadon` FOREIGN KEY (`madonhang`) REFERENCES `donhang` (`madonhang`),
  ADD CONSTRAINT `chitietdonhang_masanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`);

--
-- Constraints for table `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD CONSTRAINT `chitietkhuyenmai_chuongtrinhkhuyenmai` FOREIGN KEY (`machuongtrinhkhuyenmai`) REFERENCES `chuongtrinhkhuyenmai` (`machuongtrinhkhuyenmai`);

--
-- Constraints for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_masanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`),
  ADD CONSTRAINT `chitietphieunhap_phieunhap` FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`);

--
-- Constraints for table `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `donhang_chitietkhuyenmai` FOREIGN KEY (`macode`) REFERENCES `chitietkhuyenmai` (`macode`),
  ADD CONSTRAINT `donhang_makhachhang` FOREIGN KEY (`makhachhang`) REFERENCES `khachhang` (`makhachhang`),
  ADD CONSTRAINT `donhang_manhanvien` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manhanvien`);

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manhanvien`),
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`manhacungcap`) REFERENCES `nhacungcap` (`manhacungcap`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_manhaxuatban` FOREIGN KEY (`manhaxuatban`) REFERENCES `nhaxuatban` (`manhaxuatban`),
  ADD CONSTRAINT `FK_matacgia` FOREIGN KEY (`matacgia`) REFERENCES `tacgia` (`matacgia`),
  ADD CONSTRAINT `FK_matheloai` FOREIGN KEY (`matheloai`) REFERENCES `theloai` (`matheloai`);

--
-- Constraints for table `sanphamkhuyenmai`
--
ALTER TABLE `sanphamkhuyenmai`
  ADD CONSTRAINT `sanphamkhuyenmai_chitietkhuyenmai` FOREIGN KEY (`machitietkhuyenmai`) REFERENCES `chitietkhuyenmai` (`machitietkhuyenmai`),
  ADD CONSTRAINT `sanphamkhuyenmai_sanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
