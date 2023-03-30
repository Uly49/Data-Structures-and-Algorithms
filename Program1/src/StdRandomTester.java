/*
PLEASE READ "README.md" BEFORE EXECUTING
 */

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static edu.princeton.cs.algs4.StdRandom.bernoulli;
import static edu.princeton.cs.algs4.StdRandom.uniform;

public class StdRandomTester {

    @Test
    public void testUniform() {
        ArrayList thousand = new ArrayList();

        for (int i = 0; i < 1000; i++) {
            int num = uniform(1000);
            thousand.add(num);
            assertTrue(num >= 0 && num < 1000);
        }
    }

    @Test
    public void testBernoulli() {
        ArrayList tenThousand = new ArrayList();

        for (int i = 0; i < 10000; i++) {
            boolean bool = bernoulli(0.5);
            tenThousand.add(bool);
            assertTrue(bool == true || bool == false);
        }
    }

    public static void main(String[] args) {
        ArrayList thousand = new ArrayList();
        ArrayList tenThousand = new ArrayList();

        for (int i = 0; i < 1000; i++) {
            thousand.add(uniform(1000));
        }
        for (int i = 0; i < 10000; i++) {
            tenThousand.add(bernoulli(0.5));
        }
        System.out.println("1000 uniform random numbers between 0 and 999: " + thousand);
        System.out.println("10000 Bernoulli random booleans: " + tenThousand);
    }
}
