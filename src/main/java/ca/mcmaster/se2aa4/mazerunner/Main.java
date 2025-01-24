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
        options.addOption("p","path",true,"custom path input");

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

        Maze maze = null;

        if (cmdline.hasOption("i")){
            try { // read maze from file
                
                String filepath = cmdline.getOptionValue("i");
                logger.info("**** Reading the maze from file " + filepath);
                logger.info("**** Computing path");
                maze = new Maze(filepath);
                maze.printMaze();
            } catch(Exception e) {
                logger.error("/!\\ An error has occured /!\\");
            }
            if (cmdline.hasOption("p")){
                String command = cmdline.getOptionValue("p");
                if (maze.checkSol(command)){
                    logger.info("Solution works");
                }
                else {
                    logger.info("Solution does NOT WORK");
                }
            }
            else{
                maze.printSol();
            }
        }
        else{
            logger.info("Must use the -i flag");
        }
    
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}

class Maze{
    int[][] data;
    Solver solver = null;

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
        this.solver = new Solver();
    }

    void printSol(){
        solver.findPath(data);
        System.out.println(solver.toFactorized(solver.path));
    }

    boolean checkSol(String instructions){
        return solver.tryPath(instructions,data);
    }

    void printMaze(){
        for (int i = 0; i < this.data.length;i++){
            for (int j = 0; j < this.data[i].length;j++){
                System.out.print(((this.data[i][j] == 1) ? "#" : " ")+" ");
            }
            System.out.print(System.lineSeparator());
        }
    }
}

class Solver{
    String path = "";
    int[]coordinate = {0,0}; //placeholder coordinate, doesn't actually start at 0 0
    // coordinate format: {row,col}
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // up,right,down,left
    int current_direction = 1; // start off by facing right, change later
    // To change directions: Use wrapping around with modulo operator
    // ex: rotating right: current_direction = (current_direction + 1) % 4

    // How the direction array works: each index is either 0 or 1, and is added to the current coordinate to change its location.
    // example: facing "north" would be a direction array of -1,0. Adding this to the current coordinate moves the solver up by one.
    void findPath(int[][] maze){ // maze is assumed to be in the correct format
        coordinate[0] = findStartPos(maze,0); // search first column for open area
        while(coordinate[0] < maze.length - 1 && coordinate[1] < maze[0].length - 1){ // this is for starting on the left side of the maze.
            int left = (current_direction - 1 + 4) % 4;
            int right = (current_direction + 1) % 4;

            if (maze[coordinate[0]+directions[right][0]][coordinate[1]+directions[right][1]] == 0){
                // turn right and move forward
                current_direction = (current_direction + 1) % 4;
                this.path = this.path + "RF";
            }
            else if (maze[coordinate[0]+directions[current_direction][0]][coordinate[1]+directions[current_direction][1]] == 0){
                // move forward
                this.path = this.path + "F";
            }
            else if (maze[coordinate[0]+directions[left][0]][coordinate[1]+directions[left][1]] == 0){
                // turn right and move forward
                current_direction = (current_direction - 1 + 4) % 4;
                this.path = this.path + "LF";
            }
            else{
                // turn around and move forward
                current_direction = (current_direction + 1) % 4;
                current_direction = (current_direction + 1) % 4;
                this.path = this.path + "RRF";
            }
            coordinate[0] += directions[current_direction][0];
            coordinate[1] += directions[current_direction][1];
        }
    }

    boolean tryPath(String instructions, int[][] maze){
        coordinate[0] = findStartPos(maze,0); // search leftmost column for open area
        coordinate[1] = 0; // starting column is the leftmost, placeholder for now
        for (int i = 0; i < instructions.length(); i++){
            if (instructions.charAt(i) == 'R'){
                current_direction = (current_direction + 1) % 4;
            }
            else if (instructions.charAt(i) == 'L'){
                current_direction = (current_direction - 1 + 4) % 4;
            }
            else if(instructions.charAt(i) == 'F'){
                coordinate[0] += directions[current_direction][0];
                coordinate[1] += directions[current_direction][1];
            }

            if (coordinate[1] < 0 || coordinate[1] >= maze[0].length){ // out of bounds check
                return false;
            }

            if (maze[coordinate[0]][coordinate[1]] == 1){ // if the current coordinate is a wall
                return false;
            }
        }
        if (coordinate[1] == maze[0].length-1){
            return true;
        }
        return false;
    }

    int findStartPos(int[][] maze,int col){
        for (int i = 0; i < maze.length; i++){
            if (maze[i][col] == 0){
                return i;
            }
        }
        return 0;
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