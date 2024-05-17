package presentation.Command;

public class NhapFile extends Command{
    private int nhucau;
    
    public NhapFile(int nhucau) {
        this.nhucau = nhucau;
    }

    @Override
    public void execute() {
        facadeRemote.nhapFile(nhucau);
    }
}
