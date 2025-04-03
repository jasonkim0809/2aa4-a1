package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

public class Maze{
    int[][] mazeArray;

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

            this.mazeArray = new int [y][x]; // create a new 2d array, 0 = open area, 1 = wall

            reader = new BufferedReader(new FileReader(filepath));
            int row = 0;

            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '#') {
                        this.mazeArray[row][i] = 1;
                    }
                }
                row++;
            }
        } catch(Exception e) {
            return;
        }
    }

    public int getWidth(){
        return mazeArray.length;
    }

    public int getHeight(){
        return mazeArray[0].length;
    }

    public int getTile(int row, int col){
        return this.mazeArray[row][col];
    }

    Path attemptSolve(Solver solver){
        return solver.findPath(this);
    }

    boolean checkSol(String instructions,PathChecker pathChecker){
        return pathChecker.tryPath(instructions,this);
    }

    void printMaze(){
        for (int i = 0; i < this.mazeArray.length;i++){
            for (int j = 0; j < this.mazeArray[i].length;j++){
                System.out.print(((this.mazeArray[i][j] == 1) ? "#" : " ")+" ");
            }
            System.out.print(System.lineSeparator());
        }
    }
}
