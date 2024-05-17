package pesistence;

import java.util.List;

import domain.HangHoa;

public class KhoDAOImpl implements KhoDAO{
    private KhoJDBC khoJDBCRemote;
    
    public KhoDAOImpl(KhoJDBC khoJDBCRemote) {
        this.khoJDBCRemote = khoJDBCRemote;
    }

    @Override
    public void capnhatHang(int loaiHang, HangHoa hanghoa) {
        khoJDBCRemote.capnhatHang(loaiHang, hanghoa);
    }
        
    @Override
    public void themHang(int loaiHang, HangHoa hanghoa) {
        khoJDBCRemote.themHang(loaiHang, hanghoa);
    }

    @Override
    public List<HangHoa> timTTHH(String thongtin) {
        return khoJDBCRemote.timTTHH(thongtin);
    }

    @Override
    public List<HangHoa> xemTTAllHH() {
        return khoJDBCRemote.xemTTAllHH();
    }

    @Override
    public void xoaHang(String maHang) {
        khoJDBCRemote.xoaHang(maHang);
    }
    
    @Override
    public HangHoa xemThongTin1HH(String maHang) {
        return khoJDBCRemote.xemThongTin1HH(maHang);
    }

    @Override
    public int tongHangThucPham() {
        return khoJDBCRemote.tongHangThucPham();
    }

    @Override
    public int tongHangDienMay() {
        return khoJDBCRemote.tongHangDienMay();
    }

    @Override
    public int tongHangSanhSu() {
        return khoJDBCRemote.tongHangSanhSu();
    }

    @Override
    public List<HangHoa> xemDSHetHan() {
        return khoJDBCRemote.xemDSHetHan();
    }

    @Override
    public void xoaAll() {
        khoJDBCRemote.xoaAll();
    }
}
