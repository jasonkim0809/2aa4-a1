package ca.mcmaster.se2aa4.mazerunner;

public class PathChecker extends CoordinateInitializer{
    private Direction direction;
    private Coordinate coordinate = new Coordinate(0,0);
    private boolean startRight;

    public PathChecker(String startingDirection,boolean startRight){
        this.direction = new Direction(startingDirection);
        this.startRight = startRight;
    }

    boolean tryPath(String instructions, Maze maze){

        coordinate.updateY(findStartPos(maze.mazeArray,this.startRight)); // search first column for open area
        if (startRight){
            coordinate.updateX(maze.getWidth()-1);
        }

        for (int i = 0; i < instructions.length(); i++){
            if (instructions.charAt(i) == 'R'){
                direction.turnRight();
            }
            else if (instructions.charAt(i) == 'L'){
                direction.turnLeft();
            }
            else if(instructions.charAt(i) == 'F'){
                coordinate = direction.moveForwards(coordinate);
            }
            else{
                System.err.println("Please enter a string in canonical form consisting of R,F,L only!");
                return false;
            }

            if (!coordinate.withinMaze(maze)){ // out of bounds check
                return false;
            }

            if (maze.getTile(coordinate.yPos,coordinate.xPos) == 1){ // if the current coordinate is a wall
                return false;
            }
            
            if (startRight){
                if (coordinate.xPos == 0){ // reached left side
                    return true;
                }
            }
            else if (coordinate.xPos == maze.getWidth() - 1){ // reached right side
                return true;
            }
        }
        return false;
    }
}
