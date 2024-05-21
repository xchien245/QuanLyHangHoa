package domain;

import java.util.Date;

public abstract class HangHoa {
    protected String maHH;
    protected String tenHH;
    protected int slTon;
    protected double donGia;

    public HangHoa(String maHH, String tenHH, int slTon, double donGia) {
        this.maHH = maHH;
        this.tenHH = tenHH;
        this.slTon = slTon;
        this.donGia = donGia;
    }

    public HangHoa(String tenHH, int slTon, double donGia) {
        this.tenHH = tenHH;
        this.slTon = slTon;
        this.donGia = donGia;
    }

    abstract double tinhVAT();

    public String getMaHH() {
        return maHH;
    }

    public String getTenHH() {
        return tenHH;
    }

    public int getSlTon() {
        return slTon;
    }

    public double getDonGia() {
        return donGia;
    }

    public String getThoiGianBH() {
        return null;
    }

    public String getCongSuat() {
        return null;
    }

    public Date getNgayNK() {
        return null;
    }

    public String getNhaSX() {
        return null;
    }

    public Date getNgaySX() {
        return null;
    }

    public Date getNgayHH() {
        return null;
    }

    public String getNhaCC() {
        return null;
    }
}
