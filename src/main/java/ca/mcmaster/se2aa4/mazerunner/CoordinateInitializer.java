package ca.mcmaster.se2aa4.mazerunner;


public abstract class CoordinateInitializer{
    int findStartPos(int[][] maze,int col){
        for (int i = 0; i < maze.length; i++){
            if (maze[i][col] == 0){
                return i;
            }
        }
        return 0;
    }
}