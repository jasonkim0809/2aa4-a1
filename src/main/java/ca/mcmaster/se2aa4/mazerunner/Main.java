package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;


// java -jar target/mazerunner.jar examples/direct.maz.txt

public class Main {

    public static void main(String[] args) {

        UsageManager usage = new UsageManager();
        
        Options options = new Options();
        options.addOption("i","input",true,"maze input");
        options.addOption("p","path",true,"custom path input");
        options.addOption("r","right",false,"start on right side");
        options.addOption("h","help",false,"help flag");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdline;
        try { // parsing command line and arguments
            cmdline = parser.parse(options,args);
        } 
        catch (Exception e) {
            System.err.println("/!\\ An error has occured while parsing the command line /!\\");
            return;
        }

        // "actual" main code starts here VVV

        Maze maze = null;

        if (cmdline.hasOption("h")){
            usage.generalUsage();
            return;
        }

        if (cmdline.hasOption("i")){
            String startingDirection = "E";
            boolean startRight = false;
            if (cmdline.hasOption("r")){
                startingDirection = "W";
                startRight = true;
            }
            
            try { // read maze from file
                String filepath = cmdline.getOptionValue("i");
                maze = new Maze(filepath);
                maze.printMaze();

            } catch(Exception e) {
                System.err.println("/!\\ An error has occured /!\\");
            }

            if (cmdline.hasOption("p")){
                PathChecker pathChecker = new PathChecker(startingDirection,startRight);
                String command = cmdline.getOptionValue("p");

                if (maze.checkSol(command,pathChecker)){
                    System.out.println(command+"\nThis is a valid solution");
                }
                else {
                    System.out.println(command+"\nThis is an invalid solution");
                }
            }
            else{
                Solver solver = new Solver(startingDirection,startRight);
                Path path = maze.attemptSolve(solver);
                System.out.println(path.toFactorized());
            }
        }
        else{
            usage.iFlag();
            usage.generalUsage();
        }
    }
}