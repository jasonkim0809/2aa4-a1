package ca.mcmaster.se2aa4.mazerunner;

public class FindPathCommand implements Command {
    private Maze maze;
    private Solver solver;

    public FindPathCommand(Maze maze, Solver solver){
        this.maze = maze;
        this.solver = solver;
    }

    @Override
    public void execute(){
        Path path = maze.attemptSolve(solver);
        System.out.println(path.toFactorized());
    }
}
