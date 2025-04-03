package ca.mcmaster.se2aa4.mazerunner;

public class UsageManager {
    public void iFlag(){
        System.err.println("The -i flag is mandatory!");
        System.err.println("Usage: mazerunner.jar -i < maze.txt >");
    }
}
