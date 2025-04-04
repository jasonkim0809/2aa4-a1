package ca.mcmaster.se2aa4.mazerunner;


public abstract class CoordinateInitializer{
    int findStartPos(int[][] maze,boolean startRight){
        int flipper = 0;
        if (startRight){
            flipper = 1;
        }
        for (int i = 0; i < maze.length; i++){
            if (maze[i][flipper*(maze[0].length-1)] == 0){
                return i;
            }
        }
        return 0;
    }
}