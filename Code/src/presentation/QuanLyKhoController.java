package presentation;

import presentation.Command.Command;

//Controller - CommandProcessor
public class QuanLyKhoController {
    public void execute(Command command){
        command.execute();
    }
}
