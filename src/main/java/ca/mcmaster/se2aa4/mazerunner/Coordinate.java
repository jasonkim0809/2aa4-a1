package ca.mcmaster.se2aa4.mazerunner;


// ROW IS Y, COLUMN IS X
public class Coordinate {
    int xPos;
    int yPos;

    public Coordinate(int x, int y){
        this.xPos = x;
        this.yPos = y;
    }

    public void updateX(int x){
        this.xPos += x;
    }

    public void updateY(int y){
        this.yPos += y;
    }

    public boolean withinMaze(Maze maze){
        return (this.xPos >= 0 && this.xPos < maze.getWidth() && this.yPos >= 0 && this.yPos < maze.getHeight());
    }

    public boolean outOfWidth(Maze maze){
        return this.xPos < 0 || this.xPos >= maze.getWidth();
    }
}
