package presentation.Command;

import domain.Facade;

public abstract class Command {
    protected Facade facadeRemote;
    
    public Command() {
        this.facadeRemote = Facade.getInstance();
    }

    public abstract void execute();
}
