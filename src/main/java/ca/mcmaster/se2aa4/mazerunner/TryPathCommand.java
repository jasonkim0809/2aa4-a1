package ca.mcmaster.se2aa4.mazerunner;

public class TryPathCommand implements Command{
    private Maze maze;
    private String inputPath;

    public TryPathCommand(Maze maze, String path) {
        this.maze = maze;
        this.inputPath = path;
    }

    @Override
    public void execute(){
        if (maze.checkSol(inputPath)){
            System.out.println(inputPath+"\nThis is a valid solution");
        }
        else {
            System.out.println(inputPath+"\nThis is an invalid solution");
        }
    }
}
