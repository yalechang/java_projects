/******************************************************************************
 *  Compilation:  javac Merge.java
 *  Execution:    java Merge < input.txt
 *
 *  A bare-bones N log N implementation of mergesort.
 *
 *  Remarks
 *  ---------
 *     - number of comparisons is guaranteed to be N log N
 *     - sort is stable
 *
 ******************************************************************************/

public class Merge {

   /***************************************************************************
    *  Merge the subarrays a[lo] .. a[mid-1] and a[mid] .. a[hi-1] into
    *  a[lo] .. a[hi-1] using the auxilliary array aux[] as scratch space.
    *
    *  Precondition:   the two subarrays are in ascending order
    *  Postcondition:  a[lo] .. a[hi-1] is in ascending order
    *
    ***************************************************************************/
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid;
        for (int k = lo; k < hi; k++) {
            if      (i == mid)                 aux[k-lo] = a[j++];
            else if (j == hi)                  aux[k-lo] = a[i++];
            else if (a[j].compareTo(a[i]) < 0) aux[k-lo] = a[j++];
            else                               aux[k-lo] = a[i++];
        }

        // copy back
        for (int k = lo; k < hi; k++)
            a[k] = aux[k-lo];
    }


   /***************************************************************************
    *  Mergesort the subarray a[lo] .. a[hi-1], using the
    *  auxilliary array aux[] as scratch space.
    ***************************************************************************/
    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {

        // base case
        if (hi - lo <= 1) return;

        // sort each half, recursively
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid, hi);

        // merge back together
        merge(a, aux, lo, mid, hi);
    }


   /***************************************************************************
    *  Sort the array a using mergesort.
    ***************************************************************************/
    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        sort(a, aux, 0, N);
    }

   /***************************************************************************
    *  Sort the subarray a[lo..hi] using mergesort.
    ***************************************************************************/
    public static void sort(Comparable[] a, int lo, int hi) {
        int N = hi - lo + 1;
        Comparable[] aux = new Comparable[N];
        sort(a, aux, lo, hi);
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i-1]) < 0) return false;
        return true;
    }

   /***************************************************************************
    *  Show results.
    ***************************************************************************/
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }


   /***************************************************************************
    *  Test client.
    ***************************************************************************/
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Merge.sort(a);
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
}
