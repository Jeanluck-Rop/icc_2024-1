package mx.unam.ciencias.icc.red;

import java.io.IOException;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosChanchitos;
import mx.unam.ciencias.icc.CampoChanchito;
import mx.unam.ciencias.icc.ChanchitoFeliz;

/**
 * Clase para servidores de bases de datos de chanchitos felices.
 */
public class ServidorBaseDeDatosChanchitos
    extends ServidorBaseDeDatos<ChanchitoFeliz> {

    /**
     * Construye un servidor de base de datos de chanchitos.
     * @param puerto el puerto dónde escuchar por conexiones.
     * @param archivo el archivo en el disco del cual cargar/guardar la base de
     *                datos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public ServidorBaseDeDatosChanchitos(int puerto, String archivo)
        throws IOException {
	super(puerto, archivo);
    }

    /**
     * Crea una base de datos de chanchitos.
     * @return una base de datos de chanchitos.
     */
    @Override public
    BaseDeDatos<ChanchitoFeliz, CampoChanchito> creaBaseDeDatos() {
	return new BaseDeDatosChanchitos();
    }
}
