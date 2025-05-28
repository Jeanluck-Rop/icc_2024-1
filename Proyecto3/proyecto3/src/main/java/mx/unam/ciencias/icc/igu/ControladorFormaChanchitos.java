package mx.unam.ciencias.icc.igu;

/**
 * Clase abstracta para controladores del contenido de diálogo con formas con
 * datos de chanchitos que se aceptan o rechazan.
 */
public abstract class ControladorFormaChanchitos extends ControladorForma {

    /** El valor del nombre. */
    protected String nombre;
    /** El valor del origen. */
    protected String origen;
    /** El valor del peso. */
    protected double peso;
    /** El valor de la edad. */
    protected int edad;
     /** El valor de la felicidad. */
    protected double felicidad;

    /**
     * Verifica que el nombre sea válido.
     * @param nombre el nombre a verificar.
     * @return <code>true</code> si el nombre es válido; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaNombre(String nombre) {
	if (nombre == null || nombre.isEmpty())
	    return false;
	this.nombre = nombre;
	return true;	
    }

    /**
     * Verifica que el lugar de origen sea válido.
     * @param origen, el origen a verificar.
     * @return <code>true</code> si el origen es válido;
     *         <code>false</code> en otro caso.
     */
    protected boolean verificaOrigen(String origen) {
	if (origen == null || origen.isEmpty())
	    return false;
	this.origen = origen;
	return true;
    }

    /**
     * Verifica que el peso sea válido.
     * @param peso, el peso a verificar.
     * @return <code>true</code> si el peso es válido; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaPeso(String peso) {
	if (peso == null || peso.isEmpty())
	    return false;
	
	try {
	    this.peso = Double.parseDouble(peso);
	} catch(NumberFormatException nf) {
	    return false;
	}
	
	return true;
    }

    /**
     * Verifica que la edad sea válida.
     * @param edad la edad a verificar.
     * @return <code>true</code> si la edad es válida; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaEdad(String edad) {
	if (edad == null || edad.isEmpty())
	    return false;
	
	try {
	    this.edad = Integer.parseInt(edad);
	} catch(NumberFormatException e) {
	    return false;
	}
	
	return true;
    }

    /**
     * Verifica que el nivel de felicidad sea válido.
     * @param felicidad, el nivel de felicidad a verificar.
     * @return <code>true</code> si la felicidad es válida; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaFelicidad(String felicidad) {
	if (felicidad == null || felicidad.isEmpty())
	    return false;
	
	try {
	    this.felicidad = Double.parseDouble(felicidad);
	} catch(NumberFormatException nf) {
	    return false;
	}
	
	return true;
    }
}
