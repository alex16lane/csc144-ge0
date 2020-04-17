
package searchandsort;

/**
 * Java classes from which we can pull existing code to utilize
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class SearchAndSort {
    
    private static Random rng = new Random();
    private static final int SIZE_THRESHOLD = 16;

    /**
     * makeList creates a random number list
     * @param size Determines the length of the list 
     * @return returns the list
     */
    
    public static List<Integer> makeList(int size) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int n = 10 + rng.nextInt(90);
            result.add(n);
        } // for

        return result;
    } // makeList( int )

    /**
     * printList prints the random number of list of makeList, formatting 
     * the list based on the size of the list relative to threshold
     * @param values 
     */
    public static void printList(List<Integer> values) {
        if (values.size() < SIZE_THRESHOLD) {
            for (int n : values) {
                System.out.printf("%4d", n);
            } // for
            System.out.println();
        } // if
        else {
            for (int n : values) {
                System.out.printf("%4d\n", n);
            } // for
        } // else
    } // printList( List<Integer> )

    
    
    
    
    // TO-DO: Define a method that determines
    // the index of the first integer in a list
    // of integers that matches a given integer.
    // The method should return -1 if no match is found.
    // Use the sequential search algorithm.
    
    /**
     * linearSearch searches the list value by value, and if true returns the 
     * index
     * @param values the list that will be searched
     * @param target the target that is being searched for
     * @return 
     */
    
    public static int linearSearch(List<Integer> values,
            int target) {
        int result = -1;

        int index = 0;
        while (index < values.size() && result < 0) {
            if (target == values.get(index)) {
                result = index;
            } // if
            index = index + 1;
        } // while
        return result;
    } // linearSearch( List<Integer>, int )

    
    
    
    // TO-DO: Define a method that determines
    // the index of the first integer in a list
    // of integers that matches a given integer.
    // The method should return -1 if no match is found.
    // Use the binary search algorithm.
    
    /**
     * binarySearch differs from linear in that it searches only half the list, 
     * and does this through finding the position of the middle relative to the
     * target, and moving in the direction sequentially of the target
     * @param values like the linear search,  the list that will be searched
     * @param target also like the linear search,
     * the target that is being searched for
     * @return 
     */
    
    public static int binarySearch(List<Integer> values,
            int target) {
        int result = -1;

        int lo = 0;
        int hi = values.size() - 1;

        while (lo < hi && result < 0) {
            int mid = (lo + hi) / 2;
            if (target == values.get(lo)) {
                result = lo;
            } // if
            else if (target == values.get(mid)) {
                result = mid;
            } // else if
            else if (target == values.get(hi)) {
                result = hi;
            } // else if
            else if (target < values.get(mid)) {
                hi = mid - 1;
            } // else if
            else {
                lo = mid + 1;
            } // else
        } // while

        return result;
    } // binarySearch( List<Integer>, int )

    
    
    
    // TO-DO: Define a method that sorts a list
    // of integers using the selection sort algorithm.
    
    /**
     * swap and findPosMin are both required programs for the selectionSort 
     * algorithm. findPosMin operates by searching the numbers sequentially and
     * returning the smallest one continously. swap allows this by providing
     * the getters and setters used in selectionSort to switch the smaller 
     * values from findPosMin
     * @param values
     * @param i the first value to swap indexes
     * @param j the second value to swap indexes
     */
    
    public static void swap(List<Integer> values, int i, int j) {
        int temp = values.get(i);
        values.set(i, values.get(j));
        values.set(j, temp);
    } // swap( List<Integer>, int, int )

    public static int findPosMin(List<Integer> values, int start) {
        int bestGuessSoFar = start;
        for (int i = start + 1; i < values.size(); i++) {
            if (values.get(i) < values.get(bestGuessSoFar)) {
                bestGuessSoFar = i;
            } // if
        } // for
        return bestGuessSoFar;
    } // findPosMin( List<Integer>, int )

    public static void selectionSort(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            int j = findPosMin(values, i);
            swap(values, i, j);
        } // for
    } // selectionSort( List<Integer> )

    
    
    
    /**
     * the insert and insertionSort methods work by moving down the list of 
     * values and comparing them to the previous indexes, and swaps them based 
     * on the size. insertionSort applies the insert method to the entire list 
     * until the lower values appear earlier and the higher values later.
     *
     * @param values the list that will be searched
     * @param next 
     */
    
    // TO-DO: Define a method that sorts a list
    // of integers using the insertion sort algorithm.
    public static void insert(List<Integer> values, int next) {

        int i = next;
        while (i > 0 && values.get(i) < values.get(i - 1)) {
            swap(values, i, i - 1);
            i = i - 1;
        } // while

    } // insert( List<Integer>, int )

    public static void insertionSort(List<Integer> values) {
        for (int i = 1; i < values.size(); i++) {
            insert(values, i);
        } // for
    } // insertionSort( List<Integer> )

    
    
    
    
    // TO-DO: Define a method that sorts a list
    // of integers using the merge sort algorithm.
    
    /**
     * mergeSort functions different than previous sorting algorithms as its 
     * runtime is logarithmic and much faster compared to say insertion sort
     * for large arrays of numbers. mergeSort takes the initial list and divides
     * each index into its own separate sublists, and then combining those 
     * sublists into the final list.  
     * @param values
     * @param prefixStart
     * @param suffixStart
     * @param suffixEnd 
     */
     
    public static void merge(List<Integer> values, int prefixStart,
            int suffixStart, int suffixEnd) {
        List<Integer> temp = new ArrayList<>();

        int i = prefixStart;
        int j = suffixStart;

        while (i < suffixStart && j < suffixEnd) {
            if (values.get(i) < values.get(j)) {
                temp.add(values.get(i));
                i++;
            } // if
            else {
                temp.add(values.get(j));
                j++;
            } // else
        } // while

        while (i < suffixStart) {
            temp.add(values.get(i));
            i++;
        } // while

        while (j < suffixEnd) {
            temp.add(values.get(j));
            j++;
        } // while

        i = prefixStart;
        for (int index = 0; index < temp.size(); index++) {
            values.set(i, temp.get(index));
            i++;
        } // for
    } // merge( List<Integer>, int, int )

    public static void mergeSort(List<Integer> values) {
        for (int stepSize = 2; stepSize < values.size(); stepSize *= 2) {
            for (int i = 0; i < values.size(); i += stepSize) {
                int prefixStart = i;
                int suffixStart = i + stepSize / 2;
                int suffixEnd = Math.min(values.size(), i + stepSize);
                merge(values, prefixStart, suffixStart, suffixEnd);
            } // for
            if (stepSize > values.size() / 2) {
                int prefixStart = 0;
                int suffixStart = stepSize;
                int suffixEnd = values.size();
                merge(values, prefixStart, suffixStart, suffixEnd);
            } // if
            //printList(values);
        } // for
    } // mergeSort( List<Integer> )

  
    
    
    
    
    /**
     * the main method calls the various sorting algorithms and runs the program.
     * it prints both the randomized list and sorted list of each variety.
     * 
     */
        
    public static void main(String[] args) {
        System.out.println("Searching and sorting algorithms");

        // TO-DO: Add code that tests the searching and sorting
        // methods.
        System.out.println("Selection sort.");
        List<Integer> data = makeList(12);
        printList(data);
        System.out.println(" **** ");
        selectionSort(data);
        printList(data);

        System.out.println(" **** ");

        System.out.println("Insertion sort.");
        data = makeList(12);
        printList(data);
        System.out.println(" **** ");
        insertionSort(data);
        printList(data);

        System.out.println(" **** ");

        System.out.println("Merge sort.");
        data = makeList(12);
        printList(data);
        System.out.println(" **** ");
        mergeSort(data);
        printList(data);
    } // main( String [] )
} // SearchAndSort