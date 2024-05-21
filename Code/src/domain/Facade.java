package domain;

import presentation.Subscriber;

public class Facade {
    private static Facade instance;
    private NguoiQuanLy modelRemote;

    private Facade() {
        modelRemote = new NguoiQuanLyImpl();
    }

    public static Facade getInstance() {
        if(instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public void themHH(int loaiHang, HangHoa hanghoa){
        modelRemote.themHH(loaiHang, hanghoa);
    }

    public void capnhatHH(int loaiHang, HangHoa hanghoa) {
        modelRemote.capnhatHH(loaiHang, hanghoa);
    }

    public void xoaHH(String maHang) {
        modelRemote.xoaHH(maHang);
    }

    public void xemTTAllHH() {
        modelRemote.xemTTAllHH();
    }

    public HangHoa xemThongTin1HH(String maHang) {
        return modelRemote.xemThongTin1HH(maHang);
    }

    public void timTTHH(String thongtin) {
        modelRemote.timTTHH(thongtin);
    }

    public double tinhVAT(HangHoa hanghoa) {
        return modelRemote.tinhVAT(hanghoa);
    }

    public void tongTonKho() {
        modelRemote.tongTonKho();
    }

    public void xemDSHetHan() {
        modelRemote.xemDSHetHan();
    }

    public void sapXepHH(String tieuchi, Boolean isTangDan) {
        modelRemote.sapXepHH(tieuchi, isTangDan);
    }

    public void xuatFile() {
        modelRemote.xuatFile();
    }

    public void nhapFile(int nhuCau) {
        modelRemote.nhapFile(nhuCau);
    }
    
    public void xoaAll() {
        modelRemote.xoaAll();
    }

    public void subscribe(Subscriber subscriber) {
        modelRemote.subscribe(subscriber);
    }

	public void unsubscribe(Subscriber subscriber){
        modelRemote.unsubscribe(subscriber);
    }
}
