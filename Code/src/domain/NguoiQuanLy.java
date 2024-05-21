package domain;

//Model
public interface NguoiQuanLy extends Publisher{
    void themHH(int loaiHang, HangHoa hanghoa);
    void capnhatHH(int loaiHang, HangHoa hanghoa);
    void xoaHH(String maHang);
    void xemTTAllHH(); 
    HangHoa xemThongTin1HH(String maHang);
    void timTTHH(String thongtin); 
    double tinhVAT(HangHoa hanghoa);
    void tongTonKho();
    void xemDSHetHan();
    void sapXepHH(String tieuchi, boolean isTangDan);
    void xuatFile();
    void nhapFile(int nhuCau);
    void xoaAll();
}
