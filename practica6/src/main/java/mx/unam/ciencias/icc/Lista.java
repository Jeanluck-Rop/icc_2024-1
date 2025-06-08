package mx.unam.ciencias.icc;

import java.util.NoSuchElementException;

/**
 * <p>Clase para listas genéricas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas son iterables utilizando sus nodos. Las listas no aceptan a
 * <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            // Aquí va su código.
	    this.elemento = elemento;
	    this.anterior = null;
	    this.siguiente = null;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            // Aquí va su código.
	    return anterior;
	    
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            // Aquí va su código.
	    return siguiente;
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public T get() {
            // Aquí va su código.
	    return elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        // Aquí va su código.
	return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        // Aquí va su código.
	return cabeza == null;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        // Aquí va su código.
	if (elemento == null)
	    throw new IllegalArgumentException("Elemento inválido.");

	Nodo n = new Nodo(elemento);
	longitud ++;

	if (rabo == null)
	    cabeza = rabo = n;
	else {
	    rabo.siguiente = n;
	    n.anterior = rabo;
	    rabo = n;
	}
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        // Aquí va su código.
	if (elemento == null)
	    throw new IllegalArgumentException("Elemento inválido.");

	Nodo n = new Nodo(elemento);
	longitud ++;
	
        if (cabeza == null)
	    cabeza = rabo = n;
	else {
	    n.siguiente = cabeza;
	    cabeza.anterior = n;
	    cabeza = n;
	}
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        // Aquí va su código.
	if (elemento == null)
	    throw new IllegalArgumentException("Elemento inválido.");
	
	if (i <= 0) 
	    agregaInicio(elemento);
	else if (longitud <= i)
	    agregaFinal(elemento);
	else {
	     Nodo n = buscaNodo(get(i));
	     Nodo nN = new Nodo(elemento);
	     n.anterior.siguiente = nN;
	     nN.anterior = n.anterior;
	     n.anterior = nN;
	     nN.siguiente = n;
	     longitud ++;
	}
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
        // Aquí va su código.
	if (esVacia() || elemento == null)
	    return;
	
	Nodo eliminable = buscaNodo(elemento);

	if (eliminable == null)
	    throw new NoSuchElementException("No existe el elemento, nada que borrar.");
        else if (eliminable == cabeza) {
	    eliminaPrimero();
	    return;
	}
	else if (eliminable == rabo) {
	    eliminaUltimo();
	    return;
	}
	else {
	    eliminable.anterior.siguiente = eliminable.siguiente;
	    eliminable.siguiente.anterior = eliminable.anterior;
	    longitud--;
	}	
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        // Aquí va su código.
	if (esVacia())
	    throw new NoSuchElementException("La lista es vacía, nada que borrar.");
	
        T eliminado = getPrimero();
	
	if (longitud == 1)
	    cabeza = rabo = null;
	else {
	    cabeza = cabeza.siguiente;
	    cabeza.anterior = null;
	}
	
	longitud --;
	return eliminado;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        // Aquí va su código.
        if (esVacia())
	    throw new NoSuchElementException("La lista es vacía, nada que borrar.");

	T eliminado = getUltimo();

	if (longitud == 1)
	    cabeza = rabo = null;
	else {
	    rabo = rabo.anterior;
	    rabo.siguiente = null;
	}
	
	longitud --;
	return eliminado;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(T elemento) {
        // Aquí va su código.
	return buscaNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        // Aquí va su código.
	Lista<T> reversa = new Lista<>();
	Nodo n = cabeza;
	return reversaRecursiva(reversa, n);
    }

    /**
     * Método auxiliar para obtener la reversa de una lista de manera recursiva.
     */
    private Lista<T> reversaRecursiva(Lista<T> l, Nodo n) {
	if (n == null)
	    return l;
	
	l.agregaInicio(n.elemento);
	return reversaRecursiva(l, n.siguiente);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        // Aquí va su código.
	Lista<T> copia = new Lista<>();
	Nodo n = cabeza;
	return copiaRecursiva(copia, n);
    }

    /**
     * Método auxiliar para obtener la copia de una lista de manera recursiva.
     */
    private Lista<T> copiaRecursiva(Lista<T> l, Nodo n) {
	if (n == null)
	    return l;
	
	l.agregaFinal(n.elemento);
	return copiaRecursiva(l, n.siguiente);
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        // Aquí va su código.
        if (esVacia())
	    return;
	
	cabeza = rabo = null;
	longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        // Aquí va su código.
        if (esVacia())
	    throw new NoSuchElementException("La lista es vacía, no hay primer elemento.");

	return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        // Aquí va su código.
	if (esVacia())
	    throw new NoSuchElementException("La lista es vacía, no hay último elemento.");

	return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        // Aquí va su código.
	if (i < 0 || i >= longitud)
	    throw new ExcepcionIndiceInvalido("Elemento no está incluido en la lista.");

	Nodo n = cabeza;
	return getRecursiva(n, i, 0);
    }

    /**
     * Método auxiliar para obtener el i-ésimo elemento de una lista de manera recursiva.
     */
    private T getRecursiva(Nodo n, int i, int j) {
	if (i == j)
	    return n.elemento;
	
	return getRecursiva(n.siguiente, i, j + 1);
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        // Aquí va su código.
	Nodo n = cabeza;
	return indiceDeRecursiva(elemento, n, 0);
    }

    /**
     * Método auxiliar para regresar el índice del elemento recibido de una lista de manera recursiva.
     */
    private int indiceDeRecursiva(T elemento, Nodo nodo, int i){
	if (nodo == null)
	    return -1;
	
	if (nodo.elemento.equals(elemento))
	    return i;
	
	return indiceDeRecursiva(elemento, nodo.siguiente, i + 1);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        // Aquí va su código.
        if (esVacia())
	    return "[]";
	
	StringBuffer sb = new StringBuffer();
	sb.append("[");
	Nodo nodo = cabeza;
	sb.append(nodo.get());
	
	while (nodo.siguiente != null) {
	    sb.append(", ");
	    nodo = nodo.siguiente;
	    sb.append(nodo.get());
	}
	
	sb.append("]");
	return sb.toString();
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        // Aquí va su código.
        if (this.longitud != lista.longitud)
	    return false;
	
	Nodo n1 = this.cabeza;
	Nodo n2 = lista.getCabeza();

	while (n1 != null && n2 != null) {
	    if (!n1.elemento.equals(n2.get()))
		return false;
	    
	    n1 = n1.siguiente;
	    n2 = n2.getSiguiente();
	}

	return n1 == null && n2 == null;
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        // Aquí va su código.
	return cabeza;
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        // Aquí va su código.
	return rabo;
    }

    /**
     * Método auxiliar para buscar un nodo por su elemento.
     */
    private Nodo buscaNodo(T elemento) {
	Nodo nodo = cabeza;
	return buscaNodoRec(elemento, nodo);
    }

    /**
     * Método auxiliar para buscar un nodo por su elemento resursivamente.
     */
    private Nodo buscaNodoRec(T elemento, Nodo nodo) {
	if (nodo == null)
	    return null;
	else if (nodo.elemento.equals(elemento))
	    return nodo;
	
	return buscaNodoRec(elemento, nodo.siguiente);
    }
}
