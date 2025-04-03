package ca.mcmaster.se2aa4.mazerunner;

public class Direction {
    int directionIndex = 0;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // up,right,down,left
    // ROW, COLUMN (Y,X)

    public Direction(String startingDirection){
        if (startingDirection.equals("N")){
            this.directionIndex = 0;
        }
        else if (startingDirection.equals("E")){
            this.directionIndex = 1;
        }
        else if (startingDirection.equals("S")){
            this.directionIndex = 2;
        }
        else if (startingDirection.equals("W")){
            this.directionIndex = 3;
        }
        else{
            this.directionIndex = 0;
        }
    }

    @Override
    public String toString(){
        if (this.directionIndex == 0){
            return "N";
        }
        else if (this.directionIndex == 1){
            return "E";
        }
        else if (this.directionIndex == 2){
            return "S";
        }
        else if (this.directionIndex == 3){
            return "W";
        }
        return "N"; // error case, no initialization
    }

    public void turnRight(){
        directionIndex = (directionIndex + 1) % 4;
    }
    public void turnLeft(){
        directionIndex = (directionIndex - 1 + 4) % 4;
    }
    public Coordinate moveForwards(Coordinate coordinate){
        return new Coordinate(coordinate.xPos+directions[directionIndex][1],coordinate.yPos + directions[directionIndex][0]);
    }
}
