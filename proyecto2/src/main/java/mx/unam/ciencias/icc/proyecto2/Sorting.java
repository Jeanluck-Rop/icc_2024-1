package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.*;

/**
 * Class containing methods to sort a list of Line objects.
 */
public class Sorting {

     /**
     * Sorts a list of Line objects in lexicographic order.
     *
     * @param list The list of Line objects to be sorted.
     * @return A sorted list of Line objects.
     */
    public static Lista<Line> sort(Lista<Line> list) {
	/**
	 * Uses merge sort to sort the list based on
	 * cleaned lines (ignoring special characters).
	 */
	return list.mergeSort((a, b) ->
	        a.getCleanedLine().compareTo(b.getCleanedLine()));
    }

    /**
     * Sorts a list of Line objects in reverse lexicographic order.
     * @param list The list of Line objects to be sorted in reverse order.
     * @return A sorted list of Line objects in reverse order.
     */
    public static Lista<Line> sortRev(Lista<Line> list) {
	// Calls the sort method and reverses the resulting sorted list.
        return sort(list).reversa();
    }
}
