package mx.unam.ciencias.icc;

/**
 * Clase para representar cerditos felices. Un chanchito feliz tiene nombre,
 * lugar de origen del cerdito, peso, edad y su nivel de felicidad que puede
 * estar entre 0 y  100. La clase implementa {@link Registro}, por lo que
 * puede seriarse en una línea de texto y deseriarse de una línea de
 * texto; además de determinar si sus campos casan valores arbitrarios.
 */
public class ChanchitoFeliz implements Registro {

    /* Nombre del chanchito feliz. */
    private String nombre;
    /* Número de cerdito. */
    private String origen;
    /* Peso del puerquito. */
    private double peso;
    /* Edad del marranito.*/
    private int edad;
    /* Nivel de felicidad del chanchito.*/
    private double felicidad;
    

    /**
     * Define el estado inicial de un chanchito.
     * @param nombre el nombre del chanchito.
     * @param origen lugar de procedencia del cerdito.
     * @param peso el peso del puerquito.
     * @param edad la edad del marranito.
     * @param felicidad el nivel de felicidad del chanchito.
     */
    public ChanchitoFeliz(String nombre,
                          String origen,
                          double peso,
                          int    edad,
			  double felicidad) {
	this.nombre = nombre;
	this.origen = origen;
	this.peso = peso;
	this.edad = edad;
	this.felicidad = felicidad;
    }
    
    /**
     * Regresa el nombre del chanchito.
     * @return el nombre del chanchito.
     */   
    public String getNombre() {
	return nombre;
    }

    /**
     * Define el nombre del chanchito.
     * @param nombre el nuevo nombre del chanchito.
     */
    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    /**
     * Regresa el origen del chanchito.
     * @return el origen del chanchito.
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Define el lugar de procedencia del chanchito.
     * @param origen del chanchito.
     */
    public void setOrigen(String origen) {
	this.origen = origen;
    }

    /**
     * Regresa el peso  del chanchito.
     * @return el peso del chanchito.
     */
    public double getPeso() {
	return peso;
    }

    /**
     * Define el nivel de felicidad del chanchito.
     * @param felicidad el nuevo nivel de felicidad del chanchito.
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Regresa la edad del chanchito.
     * @return la edad del chanchito.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Define la edad del chanchito.
     * @param edad la nueva edad del chanchito.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    /**
     * Regresa el nivel de felicidad del chanchito.
     * @return el nivel de felicidad del chanchito.
     */
    public double getFelicidad() {
        return felicidad;
    }

    /**
     * Define el peso  del chanchito.
     * @param peso el nuevo peso del chanchito.
     */
    public void setFelicidad(double felicidad) {
        this.felicidad = felicidad;
    }

    /**
     * Regresa una representación en cadena del chanchito.
     * @return una representación en cadena del chanchito.
     */
    @Override public String toString() {
        return String.format("Nombre             : %s\n" +
			     "Origen             : %s\n" +
			     "Peso               : %.2f\n" +
			     "Edad               : %d\n" +
			     "Nivel de felicidad : %.2f",
			     nombre, origen, peso, edad, felicidad);
    }

    /**
     * Nos dice si el objeto recibido es un chanchito feliz igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el chanchito se comparará.
     * @return <code>true</code> si el objeto recibido es un chanchito feliz con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof ChanchitoFeliz))
            return false;
        ChanchitoFeliz chanchito = (ChanchitoFeliz)objeto;
        return this.nombre.equals(chanchito.nombre) &&
	       this.origen.equals(chanchito.origen) &&
	       this.peso == chanchito.peso &&
	       this.edad == chanchito.edad &&
	       this.felicidad == chanchito.felicidad;
    }

    /**
     * Regresa al chanchito felíz  seriado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * ChanchitoFeliz#deseria}.
     * @return la seriación del chanchito en una línea de texto.
     */
    @Override public String seria() {
        return String.format("%s\t%s\t%2.2f\t%d\t%2.2f\n", nombre, origen, peso, edad, felicidad);
    }

    /**
     * Deseria una línea de texto en las propiedades del chanchito. La
     * seriación producida por el método {@link ChanchitoFeliz#seria} debe
     * ser aceptada por este método.
     * @param linea la línea a deseriar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una seriación válida de un chanchito.
     */
    @Override public void deseria(String linea) {
        if (linea == null) {
	    throw new ExcepcionLineaInvalida("Esta línea está vacía");
	}

	String[] a = linea.trim().split("\t");
	if (a.length != 5) {
	    throw new ExcepcionLineaInvalida("Faltan elementos");
	}

	try {
	    nombre = a[0];
	    origen = a[1];
	    peso = Double.parseDouble(a[2]);
	    edad = Integer.parseInt(a[3]);
	    felicidad = Double.parseDouble(a[4]);
	} catch (NumberFormatException e) {
	    throw new ExcepcionLineaInvalida("Error, los datos no son inválidos");
	}
    }

    /**
     * Nos dice si el chanchito casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoChanchito#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del chanchito.</li>
     *           <li><code>campo</code> es {@link CampoChanchito#ORIGEN} y
     *              <code>valor</code> es instancia de {@link Integer} y es una
     *              subcadena del origen del chanchito.</li>
     *           <li><code>campo</code> es {@link CampoChanchito#PESO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al peso del chanchito.</li>
     *           <li><code>campo</code> es {@link CampoChanchito#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la edad del chanchito.</li>
     *           <li><code>campo</code> es {@link CampoChanchito#FELICIDAD} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al nivel de felicidad del
     *              chanchito.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo no es instancia de {@link
     *         CampoChanchito}.
     */
    @Override public boolean casa(Enum campo, Object valor) {
        if (!(campo instanceof CampoChanchito)) {
	    throw new IllegalArgumentException("No es instancia de CampoChanchito");
	}

	CampoChanchito campoChanchito = (CampoChanchito) campo;

	switch (campoChanchito) {
	case NOMBRE:
	    if (valor instanceof String) {
		String valorCad = (String) valor;
		String name = getNombre();
		if (valorCad.equals("")) {
		    return false;
		}
		return name.contains(valorCad);
	    }
	    break;

	case ORIGEN:
	    if (valor instanceof String) {
		String valorCadena = (String) valor;
		String orgn = getOrigen();
		if (valorCadena.equals("")) {
		    return false;
		}
		return orgn.contains(valorCadena);
	    }
	    break;

	case PESO:
	    if (valor instanceof Double) {
		Double weight = getPeso();
		Double valorDou = (Double) valor;
		return valorDou <= weight;
	    }
	    break;

	case EDAD:
	    if (valor instanceof Integer) {
		Integer age = getEdad();
		Integer valorInt = (Integer) valor;
		return valorInt <= age;
	    }
	    break;
	    
	case FELICIDAD:
	     if (valor instanceof Double) {
		Double happy = getFelicidad();
		Double valDoub = (Double) valor;
		return valDoub <= happy;
	    }
	    break;
	}

	return false;
    }
}
