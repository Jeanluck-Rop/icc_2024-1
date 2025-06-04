package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas de estudiantes doblemente ligadas.</p>
 *
 * <p>Las listas de estudiantes nos permiten agregar elementos al inicio o final
 * de la lista, eliminar elementos de la lista, comprobar si un elemento está o
 * no en la lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas de estudiantes son iterables utilizando sus nodos. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * <p>Los elementos en una lista de estudiantes siempre son instancias de la
 * clase {@link Estudiante}.</p>
 */
public class ListaEstudiante {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Estudiante elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(Estudiante elemento) {
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
        public Estudiante get() {
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
    public void agregaFinal(Estudiante elemento) {
        // Aquí va su código.
	if (elemento == null)
	    return;
	
	Nodo nuevoNodo = new Nodo(elemento);
	longitud++;
	
	if (rabo == null)
	    cabeza = rabo = nuevoNodo;
	else {
	    rabo.siguiente = nuevoNodo;
	    nuevoNodo.anterior = rabo;
	    rabo = nuevoNodo;
	}
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    
    public void agregaInicio(Estudiante elemento) {
        // Aquí va su código.
	if (elemento == null)
	    return;
	
	Nodo nuevoNodo = new Nodo(elemento);
	longitud++;
	
	if (cabeza == null)
	    cabeza = rabo = nuevoNodo;
	else {
	    nuevoNodo.siguiente = cabeza;
	    cabeza.anterior = nuevoNodo;
	    cabeza = nuevoNodo;
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
    public void inserta(int i, Estudiante elemento) {
        // Aquí va su código.
	if (elemento == null)
	    return;
	
	if (i <= 0)
	    agregaInicio(elemento);
	else if (longitud <= i)
	    agregaFinal(elemento);
	else {
	    Nodo n = buscaNodo(get(i)); 
	    Nodo nuevoNodo = new Nodo(elemento);
	    n.anterior.siguiente = nuevoNodo;
	    nuevoNodo.anterior = n.anterior;
	    n.anterior = nuevoNodo;
	    nuevoNodo.siguiente = n;
	    longitud++;
	}
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Estudiante elemento) {
        // Aquí va su código.
	if (esVacia())
	    return;
	
	Nodo eliminable = buscaNodo(elemento);
	
	if (eliminable == null)
	    return;
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
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaPrimero() {
        // Aquí va su código.
	if (esVacia())
	    return null;
	
	Estudiante eliminado = getPrimero();

	if (longitud == 1)
	    cabeza = rabo = null;
	else {
	    cabeza = cabeza.siguiente;
	    cabeza.anterior = null;
	}
	
	longitud--;
	return eliminado;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaUltimo() {
        // Aquí va su código.
	if (esVacia())
	    return null;
	
	Estudiante eliminado = getUltimo();
	
	if (longitud == 1)
	    cabeza = rabo = null;
	else {
	    rabo = rabo.anterior;
	    rabo.siguiente = null;
	}
	
	longitud--;
	return eliminado;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(Estudiante elemento) {
        // Aquí va su código.
	return buscaNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public ListaEstudiante reversa() {
        // Aquí va su código.
	ListaEstudiante reversa = new ListaEstudiante();
	Nodo nuevoNodo = cabeza;
	return reversaRecursiva(reversa, nuevoNodo);
    }

    /**
     * Función auxiliar para obtener la reversa de una lista de manera recursiva.
     */
    private ListaEstudiante reversaRecursiva(ListaEstudiante reversa, Nodo nuevoNodo) {
	if (nuevoNodo == null)
	    return reversa;
	
	reversa.agregaInicio(nuevoNodo.elemento);
	return reversaRecursiva(reversa, nuevoNodo.siguiente);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public ListaEstudiante copia() {
        // Aquí va su código.
	ListaEstudiante copia = new ListaEstudiante();
	Nodo nuevoNodo = cabeza;
	return copiaRecursiva(copia, nuevoNodo);
    }

    /**
     * Función auxiliar para obtener la copia de una lista de manera recursiva.
     */
    private ListaEstudiante copiaRecursiva(ListaEstudiante copia, Nodo nuevoNodo) {
	if (nuevoNodo == null)
	    return copia;
	
	copia.agregaFinal(nuevoNodo.elemento);
	return copiaRecursiva(copia, nuevoNodo.siguiente);
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
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getPrimero() {
        // Aquí va su código.
	if (esVacia())
	    return null;

	return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getUltimo() {
        // Aquí va su código.
	if (esVacia())
	    return null;
	
	return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, o <code>null</code> si
     *         <em>i</em> es menor que cero o mayor o igual que el número de
     *         elementos en la lista.
     */
    public Estudiante get(int i) {
        // Aquí va su código.
	if (i < 0 || i >= longitud)
	    return null;
	
	Nodo n = cabeza;
	return getRecursiva(n, i, 0);
    }  

    /**
     * Función auxiliar para obtener el i-ésimo elemento de una lista de manera recursiva.
     */
    private Estudiante getRecursiva(Nodo n, int i, int j) {
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
    public int indiceDe(Estudiante elemento) {
        // Aquí va su código.
	Nodo n = cabeza;
	return indiceDeRecursiva(elemento, n, 0);
    }

    /**
     * Función auxiliar para regresar el índice del elemento recibido de una lista de manera recursiva.
     */
    private int indiceDeRecursiva(Estudiante elemento, Nodo n, int i){
	if (n == null)
	    return -1;
	
	if (n.elemento.equals(elemento))
	    return i;
	
	return indiceDeRecursiva(elemento, n.siguiente, i + 1);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    public String toString() {
        // Aquí va su código.
	Nodo n = cabeza;
	return "[" + toStringRecursive(n) + "]";
    }

    /**
     * Función auxiliar para representar una lista en cadena de manera recursiva.
     */
    private String toStringRecursive(Nodo n) {
	if (n == null)
	    return "";
	
	if (n.getSiguiente() == null)
	    return n.get().toString();
	
	return n.get() + ", " + toStringRecursive(n.getSiguiente());
    }

    /**
     * Nos dice si la lista es igual a la lista recibida.
     * @param lista la lista con la que hay que comparar.
     * @return <code>true</code> si la lista es igual a la recibida;
     *         <code>false</code> en otro caso.
     */
    public boolean equals(ListaEstudiante lista) {
        // Aquí va su código.
	if (lista == null)
	    return false;
	
	Nodo n = cabeza;
	return equalsRecursiva(n, lista.getCabeza());
    }

    /**
     * Función auxiliar para saber si la lista es igual a la lista recibida de manera recursiva.
     */
    private boolean equalsRecursiva(Nodo n1, Nodo n2) {
	if (n1 == null && n2 == null)
	    return true;
	else if (n1 == null || n2 == null)
	    return false;
	
	if (!n1.get().equals(n2.get()))
	    return false;
	
	return equalsRecursiva(n1.getSiguiente(), n2.getSiguiente());
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
     * Función auxiliar para buscar un nodo por su elemento.
     */
    private Nodo buscaNodo(Estudiante elemento) {
	Nodo n = cabeza;
	return buscaNodoRec(elemento, n);
    }

    /**
     * Función auxiliar para buscar un nodo por su elemento resursivamente.
     */
    private Nodo buscaNodoRec(Estudiante elemento, Nodo n) {
	if (n == null)
	    return null;
	else if (n.elemento.equals(elemento))
	    return n;
	
	return buscaNodoRec(elemento, n.siguiente);
    }
}
