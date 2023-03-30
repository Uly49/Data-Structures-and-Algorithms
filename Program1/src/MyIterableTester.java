/*
PLEASE READ "README.md" BEFORE EXECUTING
 */

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertArrayEquals;

public class MyIterableTester {

    @Test
    public void testAnyTypeInputSort() {
        ArrayList<String> anyTypeInput = new ArrayList<>();
        anyTypeInput.add("Andrew");
        anyTypeInput.add("Sasha");
        anyTypeInput.add("Mike");
        anyTypeInput.add("John");
        anyTypeInput.add("Amy");
        anyTypeInput.add("Prince");
        anyTypeInput.add("Patty");
        Collections.sort(anyTypeInput, Collections.reverseOrder());
        String[] expected = new String[]{"Sasha", "Prince", "Patty", "Mike", "John", "Andrew", "Amy"};
        assertArrayEquals(expected, anyTypeInput.toArray());
    }

    @Test
    public void testSortingOfIntInput() {
        ArrayList<Integer> intInput = new ArrayList<>();
        intInput.add(12);
        intInput.add(2);
        intInput.add(32);
        intInput.add(14);
        intInput.add(5);
        intInput.add(10);
        intInput.add(7);
        intInput.add(6);

        Collections.sort(intInput, Collections.reverseOrder());

        Integer[] expected = {32, 14, 12, 10, 7, 6, 5, 2};
        assertArrayEquals(expected, intInput.toArray());
    }

    @Test
    public void testSortingOfDoubleInput() {
        ArrayList<Double> dInput = new ArrayList<>();
        dInput.add(10.4);
        dInput.add(30.6);
        dInput.add(4.95);
        dInput.add(4.9);
        dInput.add(6.9);

        Collections.sort(dInput, Collections.reverseOrder());

        Double[] expected = {30.6, 10.4, 6.9, 4.95, 4.9};
        assertArrayEquals(expected, dInput.toArray());
    }

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner scan = new Scanner(inputFile);
        Scanner scan2 = new Scanner(inputFile);

        ArrayList<String> anyTypeInput = new ArrayList<>();

        while (scan.hasNext()) {
            anyTypeInput.add(scan.next());
        }

        Collections.sort(anyTypeInput, Collections.reverseOrder());

        System.out.println("Read \"intput.txt\" and print by reverse sorted order:");
        for (String s : anyTypeInput) {
            System.out.print(s + " ");
        }

        System.out.println();

        List<String> strInput = new ArrayList<>();
        ArrayList<Integer> intInput = new ArrayList<>();
        ArrayList<Double> dInput = new ArrayList<>();
        String buff = "\n----------+----------";

        while (scan2.hasNext()) {                     //while loop to store appropriate data in
            if (scan2.hasNextInt()) {                //right ArrayList.
                intInput.add(scan2.nextInt());
            } else if (scan2.hasNextDouble()) {
                dInput.add(scan2.nextDouble());
            } else
                strInput.add(scan2.next());
        }


        String[] strArr = new String[strInput.size()];  //Converting ArrayList into Array
        Integer[] intArr = new Integer[intInput.size()];
        Double[] doubArr = new Double[dInput.size()];

        strArr = strInput.toArray(strArr);
        intArr = intInput.toArray(intArr);
        doubArr = dInput.toArray(doubArr);

        MyIterable<String> strIt = new MyIterable<>(strArr); //Put Arrays in MyIterable class
        MyIterable<Integer> intIt = new MyIterable<>(intArr);
        MyIterable<Double> doubIt = new MyIterable<>(doubArr);

        System.out.println("List of my friends:");

        for (String s : strIt) {
            System.out.print(s + " ");
        }

        System.out.println(buff);

        System.out.println("List of my numbers:");

        for (Integer i : intIt) {
            System.out.print(i + " ");
        }

        System.out.println(buff);

        System.out.println("List of my scores:");

        for (Double d : doubIt) {
            System.out.print(d + " ");
        }

        System.out.println(buff);

    }
}
