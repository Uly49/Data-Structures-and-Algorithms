/*
 * Ulysses Palomar
 * 2/23/2023
 * University of Wisconsin - Parkside
 * CSCI 340 - Data Structures and Algorithms
 * Professor Vijayalakshmi (Viji) Ramasamy
 * Homework - Program 3
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class StreamMedian {
    private PriorityQueue<Integer> bigger; //a min-heap for the larger half of the stream
    private PriorityQueue<Integer> smaller; //a max-heap for the smaller half of the stream

    public StreamMedian() {
        bigger = new PriorityQueue<>();
        smaller = new PriorityQueue<>(Comparator.reverseOrder()); //initialize the two heaps
    }


    /*
     * Method for adding a new integer to the stream.
     * First it determines whether the integer should go into the smaller
     * or larger half of the stream based on whether it is less than or
     * greater than the current median.
     * If the sizes of the two halves differ by more than 1, it rebalances
     * the two queues by moving an element from the larger queue to the
     * smaller one, or vice versa.
     */
    public void insert(Integer i) {
        if (smaller.isEmpty() || i <= smaller.peek()) {
            smaller.offer(i); //add i to the smaller half if it's smaller than the current median
        } else {
            bigger.offer(i); // add i to the larger half if it's larger than the current median
        }
        if (bigger.size() > smaller.size()) {
            smaller.offer(bigger.poll()); // move the smallest element from the larger half to the smaller half
        } else if (smaller.size() - bigger.size() > 1) {
            bigger.offer(smaller.poll()); // move the largest element from the smaller half to the larger half
        }
    }

    /*
     * Method for returning the current median of the stream.
     * If the sizes of the two halves are the same, it returns the
     * average of the two middle elements. Otherwise, it returns
     * the middle element of the smaller half.
     */
    public double getMedian() {
        if (smaller.size() == bigger.size()) {
            return (double)(smaller.peek() + bigger.peek()) / 2; // return the average of the two middle elements if the stream has an even number of elements
        } else {
            return (double)smaller.peek(); // return the middle element of the smaller half if the stream has an odd number of elements
        }
    }
}