package mx.unam.ciencias.icc;

import java.util.NoSuchElementException;

/**
 * <p>Clase para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas son iterables utilizando sus nodos. Las listas no aceptan a
 * <code>null</code> como elemento.</p>
 */
public class Lista {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Object elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(Object elemento) {
            this.elemento = elemento;
	    this.anterior = null;
	    this.siguiente = null;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            return anterior;
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            return siguiente;
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public Object get() {
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
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        return cabeza == null;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(Object elemento) {
        if (elemento == null) {
	    throw new IllegalArgumentException("Elemento no válido");
	}

	Nodo n = new Nodo(elemento);
	longitud ++;

	if (rabo == null) {
	    cabeza = rabo = n;
	} else {
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
    public void agregaInicio(Object elemento) {
	if (elemento == null) {
	    throw new IllegalArgumentException("Elemento no válido");
	}
	
	Nodo n = new Nodo(elemento);
	longitud ++;

	if (cabeza == null) {
	    cabeza = rabo = n;
	} else {
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
    public void inserta(int i, Object elemento) {
	if (elemento == null) {
	    throw new IllegalArgumentException("Elemento no válido");
	}

	if (i <= 0) {
	    agregaInicio(elemento);
	}
	
	 else if (longitud <= i) {
	    agregaFinal(elemento);
	 }
	
	 else {
	     Nodo n = buscaNode(get(i));
	     Nodo newNode = new Nodo(elemento);
	     n.anterior.siguiente = newNode;
	     newNode.anterior = n.anterior;
	     n.anterior = newNode;
	     newNode.siguiente = n;
	     longitud ++;
	 }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Object elemento) {
        if (esVacia() || elemento == null) {
	    return;
	}

	Nodo eliminaNodo = buscaNode(elemento);

	if (eliminaNodo == null) {
	    throw new NoSuchElementException("No existe ese elemento, nada que borrar");
	}

	if (eliminaNodo == cabeza) {
	    eliminaPrimero();
	    return;
	}

	if(eliminaNodo == rabo) {
	    eliminaUltimo();
	    return;
	}

	if (eliminaNodo != cabeza && eliminaNodo != rabo) {
	    eliminaNodo.anterior.siguiente = eliminaNodo.siguiente;
	    eliminaNodo.siguiente.anterior = eliminaNodo.anterior;
	    longitud --;
	}
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object eliminaPrimero() {
        if (esVacia()) {
	    throw new NoSuchElementException("La lista es vacía, nada que borrar");
	}
	
	else {
	    Object regresa = getPrimero();
	    if (longitud == 1) {
		cabeza = rabo = null;
	    }
	    else {
		cabeza = cabeza.siguiente;
		cabeza.anterior = null;
	    }

	    longitud --;
	    return regresa;
	}
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object eliminaUltimo() {
        if (esVacia()) {
	    throw new NoSuchElementException("La lista es vacía, nada que borrar");
	} else {
	    Object regresa = getUltimo();
	    if (longitud == 1) {
		cabeza = rabo = null;
	    } else {
		rabo = rabo.anterior;
		rabo.siguiente = null;
	    }

	    longitud--;
	    return regresa;
	}
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(Object elemento) {
        return buscaNode(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista reversa() {
        Lista reverseList = new Lista();
	Nodo n = cabeza;
	return  recReversa(reverseList, n);
    }

    private Lista recReversa(Lista list, Nodo n) {
	if (n == null) {
	    return list;
	}
	
	list.agregaInicio(n.elemento);
	return recReversa(list, n.siguiente);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista copia() {
        Lista copiedList = new Lista();
	Nodo n = cabeza;
	return recCopia(copiedList, n);
    }

    private Lista recCopia(Lista list, Nodo n) {
	if  (n == null) {
	    return list;
	}
	
	list.agregaFinal(n.elemento);
	return recCopia(list, n.siguiente);
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        if (esVacia()) {
	    return;
	} else {
	    cabeza = rabo = null;
	    longitud = 0;
        }
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object getPrimero() {
        if (esVacia()) {
	    throw new NoSuchElementException("No existe el primer elemento");
	}

	return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object getUltimo() {
        if (esVacia()) {
	    throw new NoSuchElementException("No existe ese último elemento");
	}

	return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public Object get(int i) {
        if (i < 0 || i >= longitud) {
	    throw new ExcepcionIndiceInvalido("Elemento no incluido en la lista");
	}

	Nodo n = cabeza;
	return recGet(n, i, 0);
    }

    private Object recGet(Nodo n, int c, int i) {
	if (i == c) {
	    return n.elemento;
	}

	return recGet(n.siguiente, c, i + 1);
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(Object elemento) {
        Nodo n = cabeza;
	return recIndiceDe(elemento, n, 0);
    }

    private int recIndiceDe(Object elemento, Nodo n, int i) {
	if (n == null) {
	    return -1;
	}

	if (n.elemento.equals(elemento)) {
	    return i;
	}

	return recIndiceDe(elemento, n.siguiente, i + 1);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
	Nodo n = cabeza;
	return "[" + toStringRec(n) + "]";
    }

    private String toStringRec(Nodo n) {
	if (n == null) {
	    return "";
	}

	if (n.siguiente == null) {
	    return n.elemento.toString();
	}

	return n.elemento + ", " + toStringRec(n.siguiente);
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Lista))
            return false;
        Lista lista = (Lista)objeto;
	
        if (this.longitud != lista.longitud) {
	    return false;
	}

	Nodo n1 = this.cabeza;
	Nodo n2 = lista.getCabeza();

	while (n1 != null && n2 != null) {
	    if (!n1.elemento.equals(n2.get())) {
		return false;
	    }

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
        return cabeza;
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        return rabo;
    }

    /**
     * Busca un nodo que contenga el elemento especificado en la lista.
     * @param elemento El elemento que se desea buscar en la lista.
     * @return El nodo que contiene el elemento si se encuentra en la lista
     *         o <code>null</code> si el elemento no se encuentra en la lista.
     */
    private Nodo buscaNode(Object elemento) {
	Nodo n = cabeza;
	    return recBuscaNode(elemento, n);
    }

    /**
     * Método auxiliar recursivo para buscar un nodo que contenga el elemento especificado en la lista.
     * @param elemento El elemento que se desea buscar en la lista.
     *@param n El nodo actual en el proceso de búsqueda.
     * @return El nodo que contiene el elemento si se encuentra en la lista
     *         o <code>null</code> si el elemento no se encuentra en la lista
     */
    private Nodo recBuscaNode(Object elemento, Nodo n) {
	if (n == null)
	    return null;

	if (n.elemento.equals(elemento))
	    return n;
	return recBuscaNode(elemento, n.siguiente);
     }
}
