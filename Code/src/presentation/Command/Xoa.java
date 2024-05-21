package presentation.Command;

public class Xoa extends Command{
    private String maHang;
    
    public Xoa(String maHang) {
        this.maHang = maHang;
    }

    @Override
    public void execute() {
        facadeRemote.xoaHH(maHang);
    }
}
