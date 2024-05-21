package presentation.Command;

import domain.HangHoa;

public class CapNhat extends Command{
    private int loaiHang;
    private HangHoa hanghoa;

    public CapNhat(int loaiHang, HangHoa hanghoa) {
        this.loaiHang = loaiHang;
        this.hanghoa = hanghoa;
    }

    @Override
    public void execute() {
        facadeRemote.capnhatHH(loaiHang, hanghoa);
    }
}
