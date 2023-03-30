/*
PLEASE READ "README.md" BEFORE EXECUTING
 */

public class FindMedian {
    public static double findMedian(int[] array) {
        int length = array.length;

        // check if the array has an odd or even number of elements
        if (length % 2 == 1) {
            // for odd number of elements, find the (n+1)/2-th smallest element
            return findKthSmallest(array, length / 2 + 1, 0, length - 1);
        } else {
            // for even number of elements, find the n/2-th and (n/2)+1-th smallest elements and calculate the average
            int midIndex1 = length / 2 - 1;
            int midIndex2 = length / 2;
            int midElement1 = findKthSmallest(array, midIndex1 + 1, 0, length - 1);
            int midElement2 = findKthSmallest(array, midIndex2 + 1, 0, length - 1);
            return (midElement1 + midElement2) / 2.0;
        }
    }

    private static int findKthSmallest(int[] array, int k, int left, int right) {
        if (left == right) {
            return array[left];
        }

        int pivotIndex = partition(array, left, right);
        int pivotRank = pivotIndex - left + 1;

        if (k == pivotRank) {
            return array[pivotIndex];
        } else if (k < pivotRank) {
            return findKthSmallest(array, k, left, pivotIndex - 1);
        } else {
            return findKthSmallest(array, k - pivotRank, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] array, int left, int right) {
        int pivotValue = array[left];
        int i = left;
        int j = right;

        while (i < j) {
            while (i < j && array[j] >= pivotValue) {
                j--;
            }
            array[i] = array[j];

            while (i < j && array[i] <= pivotValue) {
                i++;
            }
            array[j] = array[i];
        }

        array[i] = pivotValue;
        return i;
    }

}
