package mx.unam.ciencias.icc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede seriarse en una línea de texto y deseriarse de una línea de
 * texto; además de determinar si sus campos casan valores arbitrarios y
 * actualizarse con los valores de otro estudiante.
 */
public class Estudiante implements Registro<Estudiante, CampoEstudiante> {

    /* Nombre del estudiante. */
    private final StringProperty nombre;
    /* Número de cuenta. */
    private final IntegerProperty cuenta;
    /* Pormedio del estudiante. */
    private final DoubleProperty promedio;
    /* Edad del estudiante.*/
    private final IntegerProperty edad;

    /**
     * Define el estado inicial de un estudiante.
     * @param nombre el nombre del estudiante.
     * @param cuenta el número de cuenta del estudiante.
     * @param promedio el promedio del estudiante.
     * @param edad la edad del estudiante.
     */
    public Estudiante(String nombre,
                      int    cuenta,
                      double promedio,
                      int    edad) {
        this.nombre = new SimpleStringProperty(nombre);
        // Aquí va su código.
	this.cuenta = new SimpleIntegerProperty(cuenta);
	this.promedio = new SimpleDoubleProperty(promedio);
	this.edad = new SimpleIntegerProperty(edad);
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * Regresa la propiedad del nombre.
     * @return la propiedad del nombre.
     */
    public StringProperty nombreProperty() {
        return this.nombre;
    }

    /**
     * Regresa el número de cuenta del estudiante.
     * @return el número de cuenta del estudiante.
     */
    public int getCuenta() {
        // Aquí va su código.
	return cuenta.get();
    }

    /**
     * Define el número cuenta del estudiante.
     * @param cuenta el nuevo número de cuenta del estudiante.
     */
    public void setCuenta(int cuenta) {
        // Aquí va su código.
	this.cuenta.set(cuenta);
    }

    /**
     * Regresa la propiedad del número de cuenta.
     * @return la propiedad del número de cuenta.
     */
    public IntegerProperty cuentaProperty() {
        // Aquí va su código.
	return this.cuenta;
    }

    /**
     * Regresa el promedio del estudiante.
     * @return el promedio del estudiante.
     */
    public double getPromedio() {
        // Aquí va su código.
	return promedio.get();
	
    }

    /**
     * Define el promedio del estudiante.
     * @param promedio el nuevo promedio del estudiante.
     */
    public void setPromedio(double promedio) {
        // Aquí va su código.
	this.promedio.set(promedio);
    }

    /**
     * Regresa la propiedad del promedio.
     * @return la propiedad del promedio.
     */
    public DoubleProperty promedioProperty() {
        // Aquí va su código.
	return this.promedio;
    }

    /**
     * Regresa la edad del estudiante.
     * @return la edad del estudiante.
     */
    public int getEdad() {
        // Aquí va su código.
	return edad.get();
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setEdad(int edad) {
        // Aquí va su código.
	this.edad.set(edad);
    }

    /**
     * Regresa la propiedad de la edad.
     * @return la propiedad de la edad.
     */
    public IntegerProperty edadProperty() {
        // Aquí va su código.
	return this.edad;
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
        // Aquí va su código.
	return String.format("Nombre   : %s\n" +
			     "Cuenta   : %09d\n" +
			     "Promedio : %.2f\n" +
			     "Edad     : %d",
			     nombre.get(), cuenta.get(), promedio.get(), edad.get());
    }

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el estudiante se comparará.
     * @return <code>true</code> si el objeto recibido es un estudiante con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Estudiante))
            return false;
        Estudiante estudiante = (Estudiante)objeto;
        // Aquí va su código.
	return this.nombre.get().equals(estudiante.nombre.get()) &&
	       this.cuenta.get() == estudiante.cuenta.get() &&
	       this.promedio.get() == estudiante.promedio.get() &&
	       this.edad.get() == estudiante.edad.get();
    }

    /**
     * Regresa el estudiante seriado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Estudiante#deseria}.
     * @return la seriación del estudiante en una línea de texto.
     */
    @Override public String seria() {
        // Aquí va su código.
	return String.format("%s\t%d\t%2.2f\t%d\n", nombre.get(), cuenta.get(), promedio.get(), edad.get());
    }

    /**
     * Deseria una línea de texto en las propiedades del estudiante. La
     * seriación producida por el método {@link Estudiante#seria} debe
     * ser aceptada por este método.
     * @param linea la línea a deseriar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una seriación válida de un estudiante.
     */
    @Override public void deseria(String linea) {
        // Aquí va su código.
	if (linea == null)
	    throw new ExcepcionLineaInvalida("La línea está vacía.");
	
	String[] a = linea.trim().split("\t");
	if (a.length  != 4)
	    throw new ExcepcionLineaInvalida("Faltan elementos.");
	try {
	    nombre.set(a[0]);
	    cuenta.set(Integer.parseInt(a[1]));
	    promedio.set(Double.parseDouble(a[2]));
	    edad.set(Integer.parseInt(a[3]));
	} catch (Exception e) {
	    throw new ExcepcionLineaInvalida("Error, datos inválidos.");
	}
    }

    /**
     * Actualiza los valores del estudiante con los del estudiante recibido.
     * @param estudiante el estudiante con el cual actualizar los valores.
     * @throws IllegalArgumentException si el estudiante es <code>null</code>.
     */
    public void actualiza(Estudiante estudiante) {
        // Aquí va su código.
	if (estudiante == null)
	    throw new IllegalArgumentException("El Estudiante no existe.");
       
	this.nombre.set(estudiante.getNombre());
	this.cuenta.set(estudiante.getCuenta());
	this.promedio.set(estudiante.getPromedio());
	this.edad.set(estudiante.getEdad());
    }

    /**
     * Nos dice si el estudiante casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoEstudiante#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#CUENTA} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la cuenta del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#PROMEDIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al promedio del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la edad del
     *              estudiante.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean casa(CampoEstudiante campo, Object valor) {
        // Aquí va su código.
	if (!(campo instanceof CampoEstudiante))
	    throw new IllegalArgumentException("El campo no es instancia de CampoEstudiante.");
	
	CampoEstudiante campoEstudiante = (CampoEstudiante) campo;
	
	switch (campoEstudiante) {
	case NOMBRE:
	    if (valor instanceof String) {
		String valString = (String) valor;
		String nomb = getNombre();
		if (valString.equals(""))
		    return false;
		return nomb.contains(valString);
	    }	    
	    break;
	    
	case CUENTA:
	    if (valor instanceof Integer) {
		Integer noCuenta = getCuenta();
		Integer valInteger = (Integer) valor;
		return valInteger <= noCuenta;
	    }
	    break;
	    
	case PROMEDIO:
	    if (valor instanceof Double) {
		Double prom = getPromedio();
		Double valDouble = (Double) valor;
		return valDouble <= prom;
	    }	   
	    break;
	    
	case EDAD:
	    if (valor instanceof Integer) {
		Integer edadEst = getEdad();
		Integer valInteger = (Integer) valor;
		return valInteger <= edadEst;
	    }	    
	    break;
	}

	return false;
    }
}
