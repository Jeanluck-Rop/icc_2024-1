package mx.unam.ciencias.icc;

/**
 * Enumeración para los campos de un {@link Chanchito}.
 */
public enum CampoChanchito {

    /** El nombre del chanchito. */
    NOMBRE,
    /** El número de identidad del cerdito. */
    ORIGEN,
    /** El peso del puerquito. */
    PESO,
    /** La edad del chanchito. */
    EDAD,
    /** El nivel de felicidad del cerdito. */
    FELICIDAD;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
	switch (this) {
	case NOMBRE:
	     return "Nombre";
	case ORIGEN:
	     return "Origen";
	case PESO:
	     return "Peso"; 
	case EDAD:
	     return "Edad";
	case FELICIDAD:
	     return "Nivel de Felicidad";
	default:
	     return "";
	}
    }
}
