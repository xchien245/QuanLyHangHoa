package presentation.Command;

import domain.HangHoa;

public class Them extends Command{
    private int loaiHang;
    private HangHoa hanghoa;

    public Them(int loaiHang, HangHoa hanghoa) {
        this.loaiHang = loaiHang;
        this.hanghoa = hanghoa;
    }
    
    @Override
    public void execute() {
        facadeRemote.themHH(loaiHang, hanghoa);
    }
    
}
