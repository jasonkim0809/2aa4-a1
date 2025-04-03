package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PathsTest {
    private Path pathTester;

    @Test
    public void testEmptyPath(){
        pathTester = new Path();
        assertEquals("",pathTester.toCanonical());
        assertEquals("",pathTester.toFactorized());
    }

    @Test
    public void testBasicFactorized(){
        pathTester = new Path();
        pathTester.concatToPath("FFFFRRRRLL");
        assertEquals("4F 4R 2L",pathTester.toFactorized());
    }

    @Test
    public void testFactorizedInSteps(){
        pathTester = new Path();
        pathTester.concatToPath("F");
        assertEquals("F",pathTester.toFactorized());
        pathTester.concatToPath("F");
        assertEquals("2F",pathTester.toFactorized());
        pathTester.concatToPath("R");
        assertEquals("2F R",pathTester.toFactorized());
        pathTester.concatToPath("LLLL");
        assertEquals("2F R 4L",pathTester.toFactorized());
    }

}
