package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de chanchitos.
 */
public class BaseDeDatosChanchitos extends BaseDeDatos {

    /**
     * Crea un chanchito feliz en blanco.
     * @return un chanchito feliz en blanco.
     */
    @Override public Registro creaRegistro() {
        return new ChanchitoFeliz(null, null, 0.0, 0, 0.0);
    }
}
