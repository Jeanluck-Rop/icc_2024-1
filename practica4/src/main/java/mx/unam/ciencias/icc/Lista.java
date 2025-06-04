package mx.unam.ciencias.icc;

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
        public Object get() {
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
	return longitud == 0;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaFinal(Object elemento) {
        // Aquí va su código.
	if (elemento == null)
	return;

	Nodo newNode = new Nodo(elemento);
	longitud ++;

	if (rabo == null) {
	    cabeza = rabo = newNode;
	} else {
	    rabo.siguiente = newNode;
	    newNode.anterior = rabo;
	    rabo = newNode;
	}
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaInicio(Object elemento) {
        // Aquí va su código.
	if (elemento == null)
	return;

	Nodo newNode = new Nodo(elemento);
	longitud ++;

	if (cabeza == null) {
	    cabeza = rabo = newNode;
	} else {
	    newNode.siguiente = cabeza;
	    cabeza.anterior = newNode;
	    cabeza = newNode;
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
     * @param elemento el elemento a insertar. El elemento se inserta únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void inserta(int i, Object elemento) {
        // Aquí va su código.
	if (elemento == null) {
	    return;
	}

	if (i <= 0) {
	    agregaInicio(elemento);
	}
	  else if (longitud <= i) {
	    agregaFinal(elemento);
	}
	  else {
	    Nodo node = buscaNode(get(i));
	    Nodo newNode = new Nodo(elemento);
            node.anterior.siguiente = newNode;
	    newNode.anterior = node.anterior;
	    node.anterior = newNode;
	    newNode.siguiente = node;
	    longitud ++;
	}
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Object elemento) {
        // Aquí va su código.
	if (esVacia()) {
	    return;
	}

	Nodo deleteNode = buscaNode(elemento);
	if (deleteNode == null) {
	    return;
	}

	if (deleteNode == cabeza) {
	    eliminaPrimero();
	    return;
	}

	if (deleteNode == rabo) {
	    eliminaUltimo();
	    return;
	}

	if(deleteNode != cabeza && deleteNode != rabo) {
	    deleteNode.anterior.siguiente = deleteNode.siguiente;
	    deleteNode.siguiente.anterior = deleteNode.anterior;
	    longitud --;
	}
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Object eliminaPrimero() {
        // Aquí va su código.
	if (esVacia()) {
	    return null;
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
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Object eliminaUltimo() {
        // Aquí va su código.
	if (esVacia()) {
	    return null;
	}
	  else {
	    Object regresa = getUltimo();
	    if (longitud == 1) {
		cabeza = rabo = null;
	    }
	      else {
		  rabo = rabo.anterior;
		  rabo.siguiente = null;
	    }

	    longitud --;
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
        // Aquí va su código.
	return buscaNode(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista reversa() {
        // Aquí va su código.
	Lista reverseList = new Lista();
	Nodo node = cabeza;
	return recReversa(reverseList, node);
    }

    private Lista recReversa(Lista list, Nodo node) {
	if (node == null) {
	    return list;
	}
	
	list.agregaInicio(node.elemento);
	return recReversa(list, node.siguiente);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista copia() {
        // Aquí va su código.
	Lista copiedList = new Lista();
	Nodo node = cabeza;
	return recCopia(copiedList, node);
    }

    private Lista recCopia(Lista list, Nodo node) {
	if  (node == null) {
	    return list;
	}
	
	list.agregaFinal(node.elemento);
	return recCopia(list, node.siguiente);
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        // Aquí va su código.
	if (esVacia()) {
	    return;
	} else {
	    cabeza = rabo = null;
	    longitud = 0;
        }
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Object getPrimero() {
        // Aquí va su código.
	if (esVacia()) {
	    return null;
	}

	return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Object getUltimo() {
        // Aquí va su código.
	if (esVacia()) {
	    return null;
	}

	return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, o <code>null</code> si
     *         <em>i</em> es menor que cero o mayor o igual que el número de
     *         elementos en la lista.
     */
    public Object get(int i) {
        // Aquí va su código.
	if (i < 0 || i >= longitud) {
	    return null;
	}

	Nodo node = cabeza;
	return recGet(node, i, 0);
    }

    private Object recGet(Nodo node, int c, int i) {
	if (i == c) {
	    return node.elemento;
	}

	return recGet(node.siguiente, c, i + 1);
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(Object elemento) {
        // Aquí va su código.}
	Nodo node = cabeza;
	return recIndiceDe(elemento, node, 0);
    }

    private int recIndiceDe(Object elemento, Nodo node, int i) {
	if (node == null) {
	    return -1;
	}

	if (node.elemento.equals(elemento)) {
	    return i;
	}

	return recIndiceDe(elemento, node.siguiente, i + 1);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        // Aquí va su código.
	if (esVacia()) {
	    return "[]";
	}

	StringBuffer sb = new StringBuffer();
	sb.append("[");
	Nodo node = cabeza;
	sb.append(node.get());
	
	while(node.siguiente != null) {
	    sb.append(", ");
	    node = node.siguiente;
	    sb.append(node.get());
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
        if (!(objeto instanceof Lista))
            return false;
        Lista lista = (Lista)objeto;
        // Aquí va su código.

	if (this.longitud != lista.longitud) {
	    return false;
	}

	Nodo node1 = this.cabeza;
	Nodo node2 = lista.getCabeza();

	while (node1 != null && node2 != null) {
	    if (!node1.elemento.equals(node2.get())) {
		return false;
	    }

	    node1 = node1.siguiente;
	    node2 = node2.getSiguiente();
	}

	return node1 == null && node2 == null;
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

    private Nodo buscaNode(Object elemento) {
	Nodo node = cabeza;
	    return recBuscaNode(elemento, node);
    }

    private Nodo recBuscaNode(Object elemento, Nodo node) {
	if (node == null)
	    return null;

	if (node.elemento.equals(elemento))
	    return node;
	return recBuscaNode(elemento, node.siguiente);
    }
}
