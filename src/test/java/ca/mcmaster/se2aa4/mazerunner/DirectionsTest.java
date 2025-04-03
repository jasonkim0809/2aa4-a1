package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DirectionsTest {
    private Direction directionTester = new Direction("");

    @Test
    public void testEmptyInitialization(){
        assertEquals("N",directionTester.toString());
    }

    @Test
    public void testTurningRight(){
        directionTester = new Direction("N");
        directionTester.turnRight();
        assertEquals("E",directionTester.toString());
    }

    @Test
    public void testMoveForwards(){
        directionTester = new Direction("E");
        Coordinate testCoord = new Coordinate(0,0);
        testCoord = directionTester.moveForwards(testCoord);
        assertEquals(1,testCoord.xPos);
        assertEquals(0,testCoord.yPos);
    }
}
