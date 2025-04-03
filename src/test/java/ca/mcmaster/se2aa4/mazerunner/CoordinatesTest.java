package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

public class CoordinatesTest {
    private Coordinate coordinateTester;

    @Test
    public void testWithinEmptyMaze(){
        Maze mockMaze = mock(Maze.class); 
        coordinateTester = new Coordinate(0,0);
        assertEquals(false,coordinateTester.withinMaze(mockMaze));
        // mock maze has no actual dimensions, so the coordinate should never be in the maze
    }

    @Test
    public void testWithinEmptyWidth(){
        Maze mockMaze = mock(Maze.class);
        coordinateTester  =new Coordinate(0,0);
        assertEquals(true,coordinateTester.outOfWidth(mockMaze));
    }

    @Test
    public void testCoordinateUpdates(){
        coordinateTester = new Coordinate(0,0);
        coordinateTester.updateX(2141);
        assertEquals(2141,coordinateTester.xPos);
        coordinateTester.updateX(-2000);
        assertEquals(141,coordinateTester.xPos);
    }

    @Test
    public void testNegativeCoordinates(){
        coordinateTester = new Coordinate(-1,-1);
        Direction tempDirection = new Direction("N");
        coordinateTester = tempDirection.moveForwards(coordinateTester);
        assertEquals(-2,coordinateTester.yPos);
    }
}
