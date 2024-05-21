package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pesistence.KhoDAO;
import pesistence.KhoDAOImpl;
import pesistence.KhoJDBCImpl;
import presentation.Subscriber;

public class NguoiQuanLyImpl implements NguoiQuanLy {
    private KhoDAO khoRemote;
    private List<HangHoa> dsHangHoa;
    private String[] tongTonKho = new String[3];

    public NguoiQuanLyImpl() {
        khoRemote = new KhoDAOImpl(new KhoJDBCImpl());
    }

    @Override
    public void themHH(int loaiHang, HangHoa hanghoa) {
        khoRemote.themHang(loaiHang, hanghoa);
        tongTonKho();
        xemTTAllHH();
    }

    @Override
    public void capnhatHH(int loaiHang, HangHoa hanghoa) {
        khoRemote.capnhatHang(loaiHang, hanghoa);
        tongTonKho();
        xemTTAllHH();
    }

    @Override
    public void xoaHH(String maHang) {
        khoRemote.xoaHang(maHang);
        tongTonKho();
        xemTTAllHH();
    }

    @Override
    public void xemTTAllHH() {
        dsHangHoa = khoRemote.xemTTAllHH();
        notifySubscribers();
    }

    @Override
    public void timTTHH(String thongtin) {
        dsHangHoa = khoRemote.timTTHH(thongtin);
        notifySubscribers();
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);

    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber s : subscribers) {
            s.update(dsHangHoa);
            s.update(tongTonKho);
        }
    }

    @Override
    public HangHoa xemThongTin1HH(String maHang) {
        return khoRemote.xemThongTin1HH(maHang);
    }

    @Override
    public double tinhVAT(HangHoa hanghoa) {
        return hanghoa.tinhVAT();
    }

    @Override
    public void tongTonKho() {
        tongHangThucPham();
        tongHangDienMay();
        tongHangSanhSu();
        notifySubscribers();
    }

    public void tongHangThucPham() {
        tongTonKho[0] = Integer.toString(khoRemote.tongHangThucPham());
    }

    public void tongHangDienMay() {
        tongTonKho[1] = Integer.toString(khoRemote.tongHangDienMay());
    }

    public void tongHangSanhSu() {
        tongTonKho[2] = Integer.toString(khoRemote.tongHangSanhSu());
    }

    @Override
    public void xemDSHetHan() {
        dsHangHoa = khoRemote.xemDSHetHan();
        notifySubscribers();
    }

    @Override
    public void xuatFile(){
        XSSFWorkbook excelFile = new XSSFWorkbook();

        XSSFSheet bangTP = excelFile.createSheet("Hàng thực phẩm");
        XSSFSheet bangDM = excelFile.createSheet("Hàng điện máy");
        XSSFSheet bangSS = excelFile.createSheet("Hàng sành sứ");
        CellStyle styleDongDau = excelFile.createCellStyle();
        styleDongDau.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        styleDongDau.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleDongDau.setBorderBottom(BorderStyle.THIN);
        
        Row dongDauTP = bangTP.createRow(0);
        dongDauTP = taoDuLieuCoBan(dongDauTP);
        dongDauTP.createCell(5).setCellValue("Ngày sản xuất");
        dongDauTP.createCell(6).setCellValue("Ngày hết hạn");
        dongDauTP.createCell(7).setCellValue("Nhà cung cấp");

        Row dongDauDM = bangDM.createRow(0);
        dongDauDM = taoDuLieuCoBan(dongDauDM);
        dongDauDM.createCell(5).setCellValue("Thời gian bảo hành");
        dongDauDM.createCell(6).setCellValue("Công suất");

        Row dongDauSS = bangSS.createRow(0);
        dongDauSS = taoDuLieuCoBan(dongDauSS);
        dongDauSS.createCell(5).setCellValue("Ngày nhập kho");
        dongDauSS.createCell(6).setCellValue("Nhà sản Xuất");

        for (int i = 0; i <= 6; i++) {
            dongDauTP.getCell(i).setCellStyle(styleDongDau);
            if(i == 6) {
                dongDauTP.getCell(i+1).setCellStyle(styleDongDau);
            }
            dongDauDM.getCell(i).setCellStyle(styleDongDau);
            dongDauSS.getCell(i).setCellStyle(styleDongDau);
        }

        CellStyle styleNgay = excelFile.createCellStyle();
        CreationHelper createHelper = excelFile.getCreationHelper();
        styleNgay.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        for (HangHoa hanghoa : dsHangHoa) {
            if(hanghoa instanceof HangThucPham) {
                Row Row = bangTP.createRow(bangTP.getLastRowNum() + 1);
                Row = xuatDuLieuCoBan(Row, hanghoa);
                Row.createCell(5).setCellValue(hanghoa.getNgaySX());
                Row.getCell(5).setCellStyle(styleNgay);
                Row.createCell(6).setCellValue(hanghoa.getNgayHH());
                Row.getCell(6).setCellStyle(styleNgay);
                Row.createCell(7).setCellValue(hanghoa.getNhaCC());
            } else if(hanghoa instanceof HangDienMay) {
                Row Row = bangDM.createRow(bangDM.getLastRowNum() + 1);
                Row = xuatDuLieuCoBan(Row, hanghoa);
                Row.createCell(5).setCellValue(hanghoa.getThoiGianBH());
                Row.createCell(6).setCellValue(hanghoa.getCongSuat());
            } else if(hanghoa instanceof HangSanhSu) {
                Row Row = bangSS.createRow(bangSS.getLastRowNum() + 1);
                Row = xuatDuLieuCoBan(Row, hanghoa);
                Row.createCell(5).setCellValue(hanghoa.getNgayNK());
                Row.getCell(5).setCellStyle(styleNgay);
                Row.createCell(6).setCellValue(hanghoa.getNhaSX());
            } 
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Microsoft Excel", "xlsx"));
        int ketqua = fileChooser.showSaveDialog(null);
        if (ketqua == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            file = new File(file.getAbsolutePath() + ".xlsx");
            try {
                FileOutputStream out = new FileOutputStream(file);
                try {
                    excelFile.write(out);
                    out.close();
                    excelFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private Row taoDuLieuCoBan(Row row) {
        row.createCell(0).setCellValue("Mã hàng");
        row.createCell(1).setCellValue("Tên hàng");
        row.createCell(2).setCellValue("Số lượng tồn");
        row.createCell(3).setCellValue("Đơn giá");
        row.createCell(4).setCellValue("VAT");
        return row;
    }

    private Row xuatDuLieuCoBan(Row row, HangHoa hanghoa) {
        row.createCell(0).setCellValue(hanghoa.getMaHH());
        row.createCell(1).setCellValue(hanghoa.getTenHH());
        row.createCell(2).setCellValue(hanghoa.getSlTon());
        row.createCell(3).setCellValue(hanghoa.getDonGia());
        row.createCell(4).setCellValue(tinhVAT(hanghoa));
        return row;
    }

    @Override
    public void nhapFile(int nhuCau) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Microsoft Excel", "xlsx"));
        int ketqua = fileChooser.showOpenDialog(null);
        if (ketqua == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (XSSFWorkbook excelFile = new XSSFWorkbook(new FileInputStream(selectedFile.getAbsolutePath()))) {
                XSSFSheet bangTP = excelFile.getSheetAt(0);
                XSSFSheet bangDM = excelFile.getSheetAt(1);
                XSSFSheet bangSS = excelFile.getSheetAt(2);
                if(nhuCau == 0){
                    xoaAll();
                }
                layDuLieuTP(bangTP);
                layDuLieuDM(bangDM);
                layDuLieuSS(bangSS);
                
                excelFile.close();
                tongTonKho();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void layDuLieuTP(XSSFSheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            String maHH = row.getCell(0).getStringCellValue();
            String tenHH = row.getCell(1).getStringCellValue();
            String[] temp = Double.toString(row.getCell(2).getNumericCellValue()).split("");
            int slTon = Integer.parseInt(temp[0]);
            Double donGia = row.getCell(3).getNumericCellValue();
            java.util.Date temp1 = row.getCell(5).getDateCellValue();
            Date ngaySX = new java.sql.Date(temp1.getTime());
            java.util.Date temp2 = row.getCell(6).getDateCellValue();
            Date ngayHH = new java.sql.Date(temp2.getTime());
            String nhaCC = row.getCell(7).getStringCellValue();
            if(checkMaHH(maHH)) {
                themHH(1, new HangThucPham(maHH, tenHH, slTon, donGia, ngaySX, ngayHH, nhaCC));
            }
        }
    }

    private void layDuLieuDM(XSSFSheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            String maHH = row.getCell(0).getStringCellValue();
            String tenHH = row.getCell(1).getStringCellValue();
            String[] temp = Double.toString(row.getCell(2).getNumericCellValue()).split("");
            int slTon = Integer.parseInt(temp[0]);
            Double donGia = row.getCell(3).getNumericCellValue();
            String ThoiGianBH = row.getCell(5).getStringCellValue();
            String CongSuat = row.getCell(6).getStringCellValue();
            if(checkMaHH(maHH)) {
                themHH(2, new HangDienMay(maHH, tenHH, slTon, donGia, ThoiGianBH, CongSuat));
            }
        }
    }

    private void layDuLieuSS(XSSFSheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            String maHH = row.getCell(0).getStringCellValue();
            String tenHH = row.getCell(1).getStringCellValue();;
            String[] temp = Double.toString(row.getCell(2).getNumericCellValue()).split("");
            int slTon = Integer.parseInt(temp[0]);
            Double donGia = row.getCell(3).getNumericCellValue();
            java.util.Date temp1 = row.getCell(5).getDateCellValue();
            Date ngayNK = new java.sql.Date(temp1.getTime());
            String NhaSX = row.getCell(6).getStringCellValue();
            if(checkMaHH(maHH)) {
                themHH(3, new HangSanhSu(maHH, tenHH, slTon, donGia, ngayNK, NhaSX));
            }
        }
    }

    private boolean checkMaHH(String maHH) {
        if(xemThongTin1HH(maHH) == null) {
            return true;
        } else{
            JOptionPane.showMessageDialog(null, "Mã hàng hóa '" + maHH + "' đã tồn tại");
            return false;
        }
    }

    @Override
    public void sapXepHH(String tieuchi, boolean isTangDan) {
        Collections.sort(dsHangHoa, new Comparator<HangHoa>() {
            @Override
            public int compare(HangHoa hh1, HangHoa hh2) {
                if(isTangDan) {
                    if (tieuchi.equals("maHH")) {
                        return hh1.getMaHH().compareTo(hh2.getMaHH());
                    } else if (tieuchi.equals("tenHH")) {
                        return hh1.getTenHH().compareTo(hh2.getTenHH());
                    } else if (tieuchi.equals("slTon")) {
                        return Integer.compare(hh1.getSlTon(), hh2.getSlTon());
                    } else if (tieuchi.equals("donGia")) {
                        return Double.compare(hh1.getDonGia(), hh2.getDonGia());
                    } else {
                        return hh1.getMaHH().compareTo(hh2.getMaHH());
                    }
                } else {
                    if (tieuchi.equals("maHH")) {
                        return hh2.getMaHH().compareTo(hh1.getMaHH());
                    } else if (tieuchi.equals("tenHH")) {
                        return hh2.getTenHH().compareTo(hh1.getTenHH());
                    } else if (tieuchi.equals("slTon")) {
                        return Integer.compare(hh2.getSlTon(), hh1.getSlTon());
                    } else if (tieuchi.equals("donGia")) {
                        return Double.compare(hh2.getDonGia(), hh1.getDonGia());
                    } else {
                        return hh2.getMaHH().compareTo(hh1.getMaHH());
                    }
                }

            }
        });
        notifySubscribers();
    }

    @Override
    public void xoaAll() {
        khoRemote.xoaAll();
        dsHangHoa = null;
    }
}
