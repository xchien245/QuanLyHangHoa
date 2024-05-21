CREATE DATABASE jdbc_db;
USE jdbc_db;

CREATE TABLE HangHoa (
    MaHangHoa varchar(5) PRIMARY KEY,
    TenHangHoa VARCHAR(50) NOT NULL,
    slTonKho INT,
    DonGia double,
    NgaySX date,
    NgayHetHan date,
    NhaCungCap VARCHAR(100),
	ThoiGianBH VARCHAR(20),
    CongSuat VARCHAR(20),
	NgayNhapKho date,
    NhaSX VARCHAR(100)
);

DELETE FROM hanghoa;

INSERT INTO hanghoa (MaHangHoa, TenHangHoa, slTonKho, DonGia, NgaySX, NgayHetHan, NhaCungCap) 
VALUES ('tp001', 'Hàng thực phẩm', 5, 100, '2023-01-01', '2023-08-20', 'Nhà cung cấp hàng thực phẩm');
INSERT INTO hanghoa (MaHangHoa, TenHangHoa, slTonKho, DonGia, NgaySX, NgayHetHan, NhaCungCap) 
VALUES ('tp002', 'Hàng thực phẩm', 3, 100, '2023-01-01', '2023-08-10', 'Nhà cung cấp hàng thực phẩm');

INSERT INTO hanghoa (MaHangHoa, TenHangHoa, slTonKho, DonGia, ThoiGianBH, CongSuat) 
VALUES ('dm001', 'Hàng điện máy', 5, 100, '20 tháng', '50 kW');
INSERT INTO hanghoa (MaHangHoa, TenHangHoa, slTonKho, DonGia, ThoiGianBH, CongSuat) 
VALUES ('dm002', 'Hàng điện máy', 4, 100, '30 tháng', '20 kW');

INSERT INTO hanghoa (MaHangHoa, TenHangHoa, slTonKho, DonGia, NgayNhapKho, NhaSX) 
VALUES ('ss001', 'Hàng sành sứ', 5, 100, '2023-01-01', 'Nhà sản suất hàng sành sứ');
INSERT INTO hanghoa (MaHangHoa, TenHangHoa, slTonKho, DonGia, NgayNhapKho, NhaSX) 
VALUES ('ss002', 'Hàng sành sứ', 6, 100, '2023-01-01', 'Nhà sản suất hàng sành sứ');

-- chức năng tìm kiếm
DELIMITER $$
CREATE PROCEDURE TimKiem(
	tuKhoa VARCHAR(50))
BEGIN
    SELECT *
    FROM HangHoa
    WHERE 
		lower(MaHangHoa) LIKE lower(CONCAT('%', tuKhoa, '%'))
		OR lower(TenHangHoa) LIKE lower(CONCAT('%', tuKhoa, '%'))
		OR lower(slTonKho) LIKE lower(CONCAT('%', tuKhoa, '%'))
		OR lower(DonGia) LIKE lower(CONCAT('%', tuKhoa, '%'))
		OR lower(NgaySX) LIKE lower(CONCAT('%', tuKhoa, '%'))
        OR lower(NgayHetHan) LIKE lower(CONCAT('%', tuKhoa, '%'))
        OR lower(NhaCungCap) LIKE lower(CONCAT('%', tuKhoa, '%'))
        OR lower(ThoiGianBH) LIKE lower(CONCAT('%', tuKhoa, '%'))
        OR lower(CongSuat) LIKE lower(CONCAT('%', tuKhoa, '%'))
        OR lower(NgayNhapKho) LIKE lower(CONCAT('%', tuKhoa, '%'))
        OR lower(NhaSX) LIKE lower(CONCAT('%', tuKhoa, '%'));
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE TonKhoHangTP()
BEGIN
    SELECT sum(slTonKho) as TongTonKho
    FROM HangHoa
    WHERE 
		NgaySX is not null;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE TonKhoHangDM()
BEGIN
    SELECT sum(slTonKho) as TongTonKho
    FROM HangHoa
    WHERE 
		ThoiGianBH is not null;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE TonKhoHangSS()
BEGIN
    SELECT sum(slTonKho) as TongTonKho
    FROM HangHoa
    WHERE 
		NgayNhapKho is not null;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SapHetHan()
BEGIN
    SELECT *
    FROM HangHoa
    WHERE 
		datediff(hanghoa.NgayHetHan, CURDATE()) <= 7;
END$$
DELIMITER ;