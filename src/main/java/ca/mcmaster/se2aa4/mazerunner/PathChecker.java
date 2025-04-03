package ca.mcmaster.se2aa4.mazerunner;

public class PathChecker extends CoordinateInitializer{
    private Direction direction = new Direction("E");
    private Coordinate coordinate = new Coordinate(0,0);

    boolean tryPath(String instructions, Maze maze){
        coordinate.updateY(findStartPos(maze.mazeArray,0)); // search first column for open area
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
                System.err.println("Must consist of R,F,L only");
                return false;
            }

            if (!coordinate.withinMaze(maze)){ // out of bounds check
                return false;
            }

            if (maze.getTile(coordinate.yPos,coordinate.xPos) == 1){ // if the current coordinate is a wall
                return false;
            }
            
            if (coordinate.xPos == maze.getWidth() - 1){ // if reached the other side
                return true;
            }
        }
        return false;
    }
}
