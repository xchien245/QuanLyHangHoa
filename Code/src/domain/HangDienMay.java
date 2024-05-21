package domain;

public class HangDienMay extends HangHoa {
    private String thoiGianBH;
    private String congSuat;

    public HangDienMay(String maHang, String tenHang, int soLuongTon, double donGia, String thoiGianBH, String congSuat) {
        super(maHang, tenHang, soLuongTon, Math.round((donGia)*100.0)/100.0);
        this.thoiGianBH = thoiGianBH;
        this.congSuat = congSuat;
    }

    public HangDienMay(String maHang, String tenHang, int soLuongTon, double donGia, Double thoiGianBH, Double congSuat) {
        super(maHang, tenHang, soLuongTon, Math.round((donGia)*100.0)/100.0);
        this.thoiGianBH = thoiGianBH + " tháng";
        this.congSuat = congSuat + " kW";
    }

    @Override
    public double tinhVAT() {
        return Math.round((0.1 * donGia)*100.0)/100.0;
    }

    @Override
    public String getThoiGianBH() {
        return thoiGianBH;
    }

    @Override
    public String getCongSuat() {
        return congSuat;
    }
}
