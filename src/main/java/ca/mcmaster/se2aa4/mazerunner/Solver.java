package ca.mcmaster.se2aa4.mazerunner;

public class Solver extends CoordinateInitializer{
    private Direction direction;
    private Coordinate coordinate = new Coordinate(0,0);
    private boolean startRight;

    public Solver(String startingDirection,boolean startRight){
        this.direction = new Direction(startingDirection);
        this.startRight = startRight;
    }

    public Path findPath(Maze maze){ // maze is assumed to be in the correct format

        Path path = new Path();
        coordinate.updateY(findStartPos(maze.mazeArray,startRight)); // search first column for open area
        if (startRight){
            coordinate.updateX(maze.getWidth()-1);
        }

        while(!coordinate.outOfWidth(maze)){ // this is for starting on the left side of the maze
            if (startRight){
                if (coordinate.xPos == 0){
                    return path;
                }
            }
            else if (coordinate.xPos == maze.getWidth() - 1){
                return path;
            }

            Direction left = new Direction(direction.toString());
            left.turnLeft();
            Direction right = new Direction(direction.toString());
            right.turnRight();

            Coordinate leftTile = left.moveForwards(coordinate);
            Coordinate rightTile = right.moveForwards(coordinate);
            Coordinate forwardTile = direction.moveForwards(coordinate);

            if (maze.getTile(rightTile.yPos,rightTile.xPos) != 1){ // 1 = wall
                direction.turnRight();
                path.concatToPath("RF");
            }
            else if (maze.getTile(forwardTile.yPos,forwardTile.xPos) != 1){
                path.concatToPath("F");
            }
            else if (maze.getTile(leftTile.yPos,leftTile.xPos) != 1){ // 1 = wall
                // turn left and move forward
                direction.turnLeft();
                path.concatToPath("LF");
            }
            else{
                // turn around and move forward
                direction.turnRight();
                direction.turnRight();
                path.concatToPath("RRF");
            }
            // move forwards here
            coordinate = direction.moveForwards(coordinate);

            if (startRight){
                if (coordinate.xPos == maze.getWidth() - 1){
                    System.err.println("Infinite loop detected.");
                    break;
                }
            }
            else if (coordinate.xPos == 0){ // check if back at start
                System.err.println("Infinite loop detected.");
                break;
            }

        }
        return path;
    }
}