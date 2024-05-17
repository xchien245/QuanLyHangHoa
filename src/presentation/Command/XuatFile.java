package presentation.Command;

public class XuatFile extends Command{
    
    public XuatFile() {
    }

    @Override
    public void execute() {
        facadeRemote.xuatFile();
    }
}
