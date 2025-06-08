package mx.unam.ciencias.icc;

import java.util.Comparator;

/**
 * Clase para ordenar y buscar arreglos genéricos.
 */
public class Arreglos {

    /* Constructor privado para evitar instanciación. */
    private Arreglos() {}

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
    selectionSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
	for (int i = 0; i < arreglo.length; i++) {
	    int m = i;
	    
	    for (int j = i + 1; j < arreglo.length; j++)
		if (comparador.compare(arreglo[j], arreglo[m]) < 0)
		    m = j;
	    
	    swap(arreglo, i, m);
	}
    }

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
	quickSort(arreglo, comparador, 0, arreglo.length-1);
    }

    /**
     * Método auxiliar para ordenar el arrelgo recibido usando el algoritmo QuickSort.
     */
    private static <T> void quickSort(T[] arreglo, Comparator<T> comparador, int a, int b) {
	if (b <= a)
	    return;
	
	int i = a + 1;
	int j = b;

	while (i < j) {
	    if (comparador.compare(arreglo[i], arreglo[a]) > 0 && comparador.compare(arreglo[a], arreglo[j]) >= 0) {
		swap(arreglo, i, j);
		i = i + 1;
		j = j - 1;
	    } else if (comparador.compare(arreglo[i], arreglo[a]) <= 0)
		i = i + 1;
	    else
		j = j - 1;
	}

	if (comparador.compare(arreglo[i], arreglo[a]) > 0)
	    i = i - 1;
	
        swap(arreglo, a, i);
	quickSort(arreglo, comparador, a, i - 1);
	quickSort(arreglo, comparador, i + 1, b);	    
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
        // Aquí va su código.
	int a = 0;
	int b = arreglo.length - 1;

	while (a <= b) {
	    int m = (a + b) / 2;
	    
	    if (comparador.compare(elemento, arreglo[m]) == 0)
		return m;
	    else if (comparador.compare(elemento, arreglo[m]) < 0)
		b = m - 1;
	    else
		a = m + 1;
	}
	
	return -1;
    }
    
    /**
     * Método auxiliar para intercambiar dos elementos en un arreglo.
     */
    private static <T> void swap(T[] array, int i, int j) {
	if (array == null)
            throw new IllegalArgumentException("No se puede hacer el trueque.");
        
        T t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
