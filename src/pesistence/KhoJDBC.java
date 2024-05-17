package pesistence;

import java.util.List;
import domain.HangHoa;

public interface KhoJDBC {
    void themHang(int loaiHang, HangHoa hanghoa);
    void capnhatHang(int loaiHang, HangHoa hanghoa);
    void xoaHang(String maHang);
    List<HangHoa> xemTTAllHH(); 
    HangHoa xemThongTin1HH(String maHang);
    List<HangHoa> timTTHH(String thongtin); 
    int tongHangThucPham();
    int tongHangDienMay();
    int tongHangSanhSu();
    List<HangHoa> xemDSHetHan(); 
    void xoaAll();
}
