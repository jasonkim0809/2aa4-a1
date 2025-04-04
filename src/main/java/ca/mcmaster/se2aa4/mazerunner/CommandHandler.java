package ca.mcmaster.se2aa4.mazerunner;

public class CommandHandler implements Command{
    private Command currentCommand;

    public void setCommand(Command inputCommand){
        this.currentCommand = inputCommand;
    }

    public void execute(){
        if (currentCommand!=null){
            currentCommand.execute();
        }
    }
}
