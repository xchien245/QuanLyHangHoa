package presentation.Command;

public class TimKiem extends Command{
    private String tuKhoa;
    
    public TimKiem(String tuKhoa) {
        this.tuKhoa = tuKhoa;
    }

    @Override
    public void execute() {
        facadeRemote.timTTHH(tuKhoa);
    }
    
}
