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

        if (cmdline.hasOption("i")){
            try { // read maze from file
                logger.info("**** Reading the maze from file " + args[1]);
                BufferedReader reader = new BufferedReader(new FileReader(args[1]));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            System.out.print("# ");
                        } else if (line.charAt(idx) == ' ') {
                            System.out.print("  ");
                        }
                    }
                    System.out.print(System.lineSeparator());
                }
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

    
    static String toFactorized(String input){ // consists of F, R, or L
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