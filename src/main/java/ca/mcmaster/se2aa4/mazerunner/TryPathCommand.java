package ca.mcmaster.se2aa4.mazerunner;

public class TryPathCommand implements Command{
    private Maze maze;
    private String inputPath;
    private PathChecker pathChecker;

    public TryPathCommand(Maze maze, String path, PathChecker pathChecker) {
        this.maze = maze;
        this.inputPath = path;
        this.pathChecker = pathChecker;
    }

    @Override
    public void execute(){
        if (maze.checkSol(inputPath,pathChecker)){
            System.out.println(inputPath+"\nThis is a valid solution");
        }
        else {
            System.out.println(inputPath+"\nThis is an invalid solution");
        }
    }
}
