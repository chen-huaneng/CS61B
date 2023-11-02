import org.junit.ClassRule;
import java.lang.Integer.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestFlik {
    @Test
    public void testFlik() {
        int i = 50;
        int j = 50;
        assertTrue(Flik.isSameNumber(i, j));
    }


    @Test
    public void testFlik2() {
        int i = 129;
        int j = 129;
        //assertTrue(i == j);
        System.out.println("at i:" + i + " j:" + j + " " + Flik.isSameNumber(i, j));
        assertTrue(Flik.isSameNumber(i, j));
    }

    @Test
    public void testFlikAll() {
        for (int i = 0, j = 0; i < 500; ++i, ++j) {
            String errorMessage = "Error in i:" + i + " j:" + j;
            System.out.println(Flik.isSameNumber(i, j) + " at " + errorMessage);
            assertTrue(errorMessage, Flik.isSameNumber(i, j));
        }
    }

    @Test
    /** {@code -XX:AutoBoxCacheMax=<200>} */
    public void testIntegerCache() {
        //int count = 0;
        for (int i = 190, j = 190; i < 250; ++i, ++j) {
            String errorMessage = "Error in i:" + i + " j:" + j;
            //assertTrue(i == j);
            //++count;
            System.out.println(Flik.isSameNumber(i, j) + " at " + errorMessage);
            assertTrue(errorMessage, Flik.isSameNumber(i, j));
        }
    }
}
