
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class GradeBookTest {

    private GradeBook g1;
    private GradeBook g2;

    /**
     * Creates two gradebooks before each test.
     */
    @BeforeEach
    public void setUp() {
        g1 = new GradeBook(5);
        g1.addScore(50.0);
        g1.addScore(75.0);

        g2 = new GradeBook(5);
        g2.addScore(90.0);
        g2.addScore(85.0);
        g2.addScore(70.0);
    }

    /**
     * Cleans up after each test.
     */
    @AfterEach
    public void tearDown() {
        g1 = null;
        g2 = null;
    }

    /**
     * Tests adding scores and displaying them in order.
     */
    @Test
    public void testAddScoreAndToString() {
        assertEquals("50.0 75.0 ", g1.toString());
        assertEquals("90.0 85.0 70.0 ", g2.toString());

        assertTrue(g1.addScore(95.0));
        assertEquals("50.0 75.0 95.0 ", g1.toString());
    }

    /**
     * Tests the number of scores in each gradebook.
     */
    @Test
    public void testGetScoreSize() {
        assertEquals(2, g1.getScoreSize());
        assertEquals(3, g2.getScoreSize());

        g1.addScore(100.0);
        assertEquals(3, g1.getScoreSize());
    }

    /**
     * Tests the sum of all scores.
     */
    @Test
    public void testSum() {
        assertEquals(125.0, g1.sum(), 0.0001);
        assertEquals(245.0, g2.sum(), 0.0001);
    }

    /**
     * Tests the smallest score and an empty gradebook.
     */
    @Test
    public void testMinimum() {
        assertEquals(50.0, g1.minimum(), 0.0001);
        assertEquals(70.0, g2.minimum(), 0.0001);

        GradeBook empty = new GradeBook(5);
        assertEquals(0.0, empty.minimum(), 0.0001);
    }

    /**
     * Tests dropping the lowest score and special cases.
     */
    @Test
    public void testFinalScore() {
        assertEquals(75.0, g1.finalScore(), 0.0001);
        assertEquals(175.0, g2.finalScore(), 0.0001);

        GradeBook empty = new GradeBook(5);
        assertEquals(0.0, empty.finalScore(), 0.0001);

        GradeBook single = new GradeBook(5);
        single.addScore(100.0);
        assertEquals(100.0, single.finalScore(), 0.0001);
    }
}
