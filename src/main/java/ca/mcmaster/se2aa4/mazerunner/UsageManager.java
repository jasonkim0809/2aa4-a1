package ca.mcmaster.se2aa4.mazerunner;

public class UsageManager {
    public void iFlag(){
        System.err.println("The -i flag is mandatory!\n");
    }
    public void generalUsage(){
        System.out.println("Usage: mazerunner.jar -i < maze.txt > -p < path > -r\n");
        System.out.println("-i is mandatory\n");
        System.out.println("-p is optional, this is to check if a given path solves the maze\n");
        System.out.println("-r is optional, solve maze from right, default is left\n");
    }
}

