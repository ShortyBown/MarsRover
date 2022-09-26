package mars.rover;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarsRoverTest {

    private static MarsRover classUnderTest;

    @BeforeEach
    void setUp() {
        classUnderTest = new MarsRover();
    }

    @Test
    void testOne() {
        String commands = "4 8\n(2, 3, E) LFRFF\n(0, 2, N) FFLFRFF";
        String expectedFinalPositions = "(4, 4, E)\n(0, 4, W) LOST\n";
        String finalPositions = classUnderTest.moveRovers(commands);
        System.out.println("Expecting \n" + finalPositions + "\nto be equal to \n" + expectedFinalPositions);
        assertEquals(finalPositions, expectedFinalPositions);
    }

    @Test
    void testTwo() {
        String commands = "4 8\n(2, 3, N) FLLFR\n(1, 0, S) FFRLF";
        String expectedFinalPositions = "(2, 3, W)\n(1, 0, S) LOST\n";
        String finalPositions = classUnderTest.moveRovers(commands);
        System.out.println("Expecting \n" + finalPositions + "\nto be equal to \n" + expectedFinalPositions);
        assertEquals(finalPositions, expectedFinalPositions);
    }
}