/*
PLEASE READ "README.md" BEFORE EXECUTING
 */

import edu.princeton.cs.algs4.LinearRegression;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSumFast;

public class ThreeSumTimeEstimator {
    public static void main(String[] args) {
        int[] ns = {1000, 2000, 4000, 8000, 16000}; // input sizes
        double[] times = new double[ns.length]; // execution times for each input size

        for (int i = 0; i < ns.length; i++) {
            int n = ns[i];
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = j;
            }

            Stopwatch stopwatch = new Stopwatch();
            ThreeSumFast.count(a);
            times[i] = stopwatch.elapsedTime();

            System.out.printf("n=%d, time=%.6f\n", n, times[i]);
        }

        // Regression analysis
        double[] logns = new double[ns.length];
        double[] logtimes = new double[ns.length];

        for (int i = 0; i < ns.length; i++) {
            logns[i] = Math.log(ns[i]);
            logtimes[i] = Math.log(times[i]);
        }

        LinearRegression regression = new LinearRegression(logns, logtimes);
        double exponent = -regression.slope();
        double constant = Math.exp(regression.intercept());

        System.out.printf("Power law equation: y = %.15f * x^%.2f\n", constant, exponent);

        // Prediction for array of size 1 million
        int n = 1000000;
        double predictedTime = constant * Math.pow(n, exponent);

        System.out.printf("Predicted execution time for n=%d: %.2f seconds\n", n, predictedTime);
    }
}
