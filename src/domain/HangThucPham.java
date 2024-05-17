package domain;

import java.util.Date;

public class HangThucPham extends HangHoa {
    private Date ngaySX;
    private Date ngayHH;
    private String nhaCC;

    public HangThucPham(String maHH, String tenHang, int slTon, Double donGia, Date ngaySX, Date ngayHH, String nhaCC) {
        super(maHH, tenHang, slTon, Math.round((donGia)*100.0)/100.0);
        this.ngaySX = ngaySX;
        this.ngayHH = ngayHH;
        this.nhaCC = nhaCC;
    }

    @Override
    public double tinhVAT() {
        return Math.round((0.05 * donGia)*100.0)/100.0 ;
    }

    @Override
    public Date getNgaySX() {
        return ngaySX;
    }

    @Override
    public Date getNgayHH() {
        return ngayHH;
    }

    @Override
    public String getNhaCC() {
        return nhaCC;
    }
}
