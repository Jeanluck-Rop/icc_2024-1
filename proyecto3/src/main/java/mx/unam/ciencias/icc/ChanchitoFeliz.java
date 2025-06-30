package mx.unam.ciencias.icc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, origen,
 * peso, edad y felicidad. La clase implementa {@link Registro}, por lo que
 * puede seriarse en una línea de texto y deseriarse de una línea de
 * texto; además de determinar si sus campos casan valores arbitrarios y
 * actualizarse con los valores de otro chanchito.
 */
public class ChanchitoFeliz implements Registro<ChanchitoFeliz, CampoChanchito> {

    /* Nombre del chanchito. */
    private final StringProperty nombre;
    /* Origen del chanchito. */
    private final StringProperty origen;
    /* Peso del chanchito. */
    private final DoubleProperty peso;
    /* Edad del chanchito.*/
    private final IntegerProperty edad;
    /* Nivel de felicidad del chanchito.*/
    private final DoubleProperty felicidad;
    /**
     * Define el estado inicial de un chanchito.
     * @param nombre, el nombre del chanchito.
     * @param origen, el lugar de procedencia del chanchito.
     * @param peso, el peso del chanchito.
     * @param edad, la edad del chanchito.
     * @param felicidad, el nivel de felicidad del chanchito.
     */
    public ChanchitoFeliz(String nombre,
                      String origen,
                      double peso,
                      int    edad,
		      double felicidad) {
        this.nombre = new SimpleStringProperty(nombre);
	this.origen = new SimpleStringProperty(origen);
	this.peso = new SimpleDoubleProperty(peso);
	this.edad = new SimpleIntegerProperty(edad);
	this.felicidad = new SimpleDoubleProperty(felicidad);
    }

    /**
     * Regresa el nombre del chanchito.
     * @return el nombre del chanchito.
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     * Define el nombre del chanchito.
     * @param nombre el nuevo nombre del chanchito.
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
     * Regresa el origen del chanchito.
     * @return el origen del chanchito.
     */
    public String getOrigen() {
	return origen.get();
    }

    /**
     * Define el origen del chanchito.
     * @param origen, el nuevo origen del chanchito.
     */
    public void setOrigen(String origen) {
	this.origen.set(origen);
    }

    /**
     * Regresa la propiedad del origen.
     * @return la propiedad del origen.
     */
    public StringProperty origenProperty() { 
	return this.origen;
    }

    /**
     * Regresa el peso del chanchito.
     * @return el peso del chanchito.
     */
    public double getPeso() { 
	return peso.get();
	
    }

    /**
     * Define el peso del chanchito.
     * @param peso, el nuevo peso del chanchito.
     */
    public void setPeso(double peso) { 
	this.peso.set(peso);
    }

    /**
     * Regresa la propiedad del peso.
     * @return la propiedad del peso.
     */
    public DoubleProperty pesoProperty() {
	return this.peso;
    }

    /**
     * Regresa la edad del chanchito.
     * @return la edad del chanchito.
     */
    public int getEdad() { 
	return edad.get();
    }

    /**
     * Define la edad del chanchito.
     * @param edad la nueva edad del chanchito.
     */
    public void setEdad(int edad) {
	this.edad.set(edad);
    }

    /**
     * Regresa la propiedad de la edad.
     * @return la propiedad de la edad.
     */
    public IntegerProperty edadProperty() { 
	return this.edad;
    }

    /**
     * Regresa el nivel de felicidad del chanchito.
     * @return la felicidad del chanchito.
     */
    public double getFelicidad() { 
	return felicidad.get();
	
    }

    /**
     * Define la felicidad del chanchito.
     * @param peso, el nuevo nivel de felicidad del chanchito.
     */
    public void setFelicidad(double felicidad) { 
	this.felicidad.set(felicidad);
    }

    /**
     * Regresa la propiedad de la felicidad.
     * @return la propiedad de la felicidad.
     */
    public DoubleProperty felicidadProperty() {
	return this.felicidad;
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
			     nombre.get(), origen.get(), peso.get(), edad.get(), felicidad.get());
    }

    /**
     * Nos dice si el objeto recibido es un chanchito igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el chanchito se comparará.
     * @return <code>true</code> si el objeto recibido es un chanchito con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof ChanchitoFeliz))
            return false;
        ChanchitoFeliz chanchito = (ChanchitoFeliz)objeto; 
	return this.nombre.get().equals(chanchito.nombre.get()) &&
	       this.origen.get().equals(chanchito.origen.get()) &&
	       this.peso.get() == chanchito.peso.get() &&
	       this.edad.get() == chanchito.edad.get() &&
	       this.felicidad.get() == chanchito.felicidad.get();
    }

    /**
     * Regresa el chanchito seriado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * ChanchitoFeliz#deseria}.
     * @return la seriación del chanchito en una línea de texto.
     */
    @Override public String seria() {
	return String.format("%s\t%s\t%2.2f\t%d\t%2.2f\n", nombre.get(), origen.get(), peso.get(), edad.get(), felicidad.get());
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
	if (linea == null)
	    throw new ExcepcionLineaInvalida("La línea está vacía.");
	
	String[] a = linea.trim().split("\t");
	if (a.length  != 5)
	    throw new ExcepcionLineaInvalida("Faltan elementos.");
	try {
	    nombre.set(a[0]);
	    origen.set(a[1]);
	    peso.set(Double.parseDouble(a[2]));
	    edad.set(Integer.parseInt(a[3]));
	    felicidad.set(Double.parseDouble(a[4]));
	} catch (NumberFormatException e) {
	    throw new ExcepcionLineaInvalida("Error, datos inválidos.");
	}
    }

    /**
     * Actualiza los valores del chanchito con los del chanchito recibido.
     * @param chanchito, el chanchito con el cual actualizar los valores.
     * @throws IllegalArgumentException si el chanchito es <code>null</code>.
     */
    public void actualiza(ChanchitoFeliz chanchito) {
	if (chanchito == null)
	    throw new IllegalArgumentException("Este Chanchito no existe.");

	this.nombre.set(chanchito.getNombre());
	this.origen.set(chanchito.getOrigen());
	this.peso.set(chanchito.getPeso());
	this.edad.set(chanchito.getEdad());
	this.felicidad.set(chanchito.getFelicidad());
    }

    /**
     * Nos dice si el chanchito casa el valor dado en el campo especificado.
     * @param campo, el campo que hay que casar.
     * @param valor, el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoChanchito#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del chanchito.</li>
     *           <li><code>campo</code> es {@link CampoChanchito#ORIGEN} y
     *              <code>valor</code> es instancia de {@link String} y su
     *              valor entero es menor o igual al origen
     *              del chanchito.</li>
     *           <li><code>campo</code> es {@link CampoChanchito#PESO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al peso del
     *              chanchito.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la edad del
     *              chanchito.</li>
     *           <li><code>campo</code> es {@link CampoChanchito#FELICIDAD} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al nivel de
     *              felicidad del chanchito.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean casa(CampoChanchito campo, Object valor) { 
	if (!(campo instanceof CampoChanchito))
	    throw new IllegalArgumentException("El campo no es instancia de CampoChanchito");
	
	CampoChanchito campoChanchito = (CampoChanchito) campo;
	switch (campoChanchito) {
	case NOMBRE:
	    if (valor instanceof String) {
		String valString = (String) valor;
		String name = getNombre();
		if (valString.equals(""))
		    return false;
		return name.contains(valString);
	    }	    
	    break;
	case ORIGEN:
	    if (valor instanceof String) {
		String valString = (String) valor;
		String origen = getOrigen();
		if (valString.equals(""))
		    return false;
		return origen.contains(valString);
	    }
	    break;
	case PESO:
	    if (valor instanceof Double) {
		Double peso = getPeso();
		Double valDouble = (Double) valor;
		return valDouble <= peso;
	    }	   
	    break;
	case EDAD:
	    if (valor instanceof Integer) {
		Integer edad = getEdad();
		Integer valInteger = (Integer) valor;
		return valInteger <= edad;
	    }	    
	    break;
	case FELICIDAD:
	    if (valor instanceof Double) {
		Double feliz = getFelicidad();
		Double valDouble = (Double) valor;
		return valDouble <= feliz;
	    }	   
	    break;
	}
	
	return false;
    }
}
