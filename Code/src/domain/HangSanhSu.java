package domain;
import java.util.Date;

public class HangSanhSu extends HangHoa {
    private Date ngayNK;
    private String nhaSX;

    public HangSanhSu(String maHH, String tenHH, int slTon, Double donGia, Date ngayNK, String nhaSX) {
        super(maHH, tenHH, slTon, Math.round((donGia)*100.0)/100.0);
        this.ngayNK = ngayNK;
        this.nhaSX = nhaSX;
    }

    @Override
    double tinhVAT() {
        return Math.round((0.1 * donGia)*100.0)/100.0;
    }

    @Override
    public Date getNgayNK() {
        return ngayNK;
    }

    @Override
    public String getNhaSX() {
        return nhaSX;
    }
}
