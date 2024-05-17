package presentation.Command;

public class SapXep extends Command {
    private String tieuchi;
    private Boolean isTangDan;

    public SapXep(String tieuchi, Boolean isTangDan) {
        this.tieuchi = tieuchi;
        this.isTangDan = isTangDan;
    }

    @Override
    public void execute() {
        facadeRemote.sapXepHH(tieuchi, isTangDan);
    }
}
