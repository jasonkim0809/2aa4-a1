package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// java -jar target/mazerunner.jar examples/direct.maz.txt

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i","input",true,"maze input");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdline;
        try { // parsing command line and arguments
            cmdline = parser.parse(options,args);
        } 
        catch (Exception e) {
            logger.error("/!\\ An error has occured while parsing the command line /!\\");
            return;
        }

        // "actual" main code starts here VVV

        String raw_maze = "";

        Maze maze;

        if (cmdline.hasOption("i")){
            try { // read maze from file
                logger.info("**** Reading the maze from file " + args[1]);
                maze = new Maze(args[1]);
                maze.printMaze();
            } catch(Exception e) {
                logger.error("/!\\ An error has occured /!\\");
            }
        }
        else{
            logger.info("Must use the -i flag");
        }
        

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}

class Maze{
    int[][] data;
    
    public Maze(String filepath){
        try { // read maze from file
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            
            int x = 0;
            int y = 0;

            while ((line = reader.readLine()) != null){
                y++;
                x = line.length();
            }

            this.data = new int [y][x]; // create a new 2d array, 0 = open area, 1 = wall

            reader = new BufferedReader(new FileReader(filepath));
            int row = 0;

            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '#') {
                        this.data[row][i] = 1;
                    }
                }
                row++;
            }
        } catch(Exception e) {
            return;
        }
    }

    void printMaze(){
        for (int i = 0; i < this.data.length;i++){
            for (int j = 0; j < this.data[i].length;j++){
                System.out.print(this.data[i][j]+" ");
            }
            System.out.print(System.lineSeparator());
        }
    }
    
    String toFactorized(String input){ // consists of F, R, or L
        char current = input.charAt(0);
        int sum = 0;
        String factorized = "";

        for (int i = 0; i < input.length(); i++){
            current = input.charAt(i);
            for (int j = i; j < input.length(); j++){
                if (input.charAt(j) == current){
                    sum++;
                }
                else{
                    j = input.length();
                }
            }
            factorized = (sum == 1) ? factorized + current + " " : factorized + sum + current + " ";
            i += (sum - 1);
            sum = 0;
        }
        return factorized;
    }
}