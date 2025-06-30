package mx.unam.ciencias.icc.red;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.Lista;
import mx.unam.ciencias.icc.Registro;

/**
 * Clase abstracta para servidores de bases de datos genéricas.
 */
public abstract class ServidorBaseDeDatos<R extends Registro<R, ?>> {

    /* La base de datos. */
    private BaseDeDatos<R, ?> bdd;
    /* La ruta donde cargar/guardar la base de datos. */
    private String ruta;
    /* El servidor de enchufes. */
    private ServerSocket servidor;
    /* El puerto. */
    private int puerto;
    /* Lista con las conexiones. */
    private Lista<Conexion<R>> conexiones;
    /* Bandera de continuación. */
    private boolean continuaEjecucion;
    /* Escuchas del servidor. */
    private Lista<EscuchaServidor> escuchas;

    /**
     * Crea un nuevo servidor usando la ruta recibida para poblar la base de
     * datos.
     * @param puerto el puerto dónde escuchar por conexiones.
     * @param ruta la ruta en el disco del cual cargar/guardar la base de
     *             datos. Puede ser <code>null</code>, en cuyo caso se usará el
     *             nombre por omisión <code>base-de-datos.bd</code>.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public ServidorBaseDeDatos(int puerto, String ruta)
        throws IOException {
        // Aquí va su código.
	this.bdd = creaBaseDeDatos();
	this.ruta = (ruta != null) ? ruta : "base-de-datos.bd";
	this.puerto = puerto;
	this.servidor = new ServerSocket(puerto);
	this.conexiones = new Lista<Conexion<R>>();
	this.escuchas = new Lista<EscuchaServidor>();
	
	carga();
    }

    /**
     * Comienza a escuchar por conexiones de clientes.
     */
    public void sirve() {
        // Aquí va su código.
	this.continuaEjecucion = true;
	anotaMensaje("Escuchando en el puerto: %d.", this.puerto);
	while (this.continuaEjecucion) {
	    try {
		Socket enchufe = this.servidor.accept();
		Conexion<R> conexion = new Conexion<>(this.bdd, enchufe);
		anotaMensaje("Conexión recibida de: %s.", enchufe.getInetAddress().getCanonicalHostName());
		anotaMensaje("Serie de conexión: %d.", conexion.getSerie());
	      	conexion.agregaEscucha((c, u) -> mensajeRecibido(c, u));
		new Thread(() -> conexion.recibeMensajes()).start();
		synchronized (conexiones) {
		    conexiones.agregaFinal(conexion);
		}	
	    } catch (IOException e) {
		if (this.continuaEjecucion)
		    anotaMensaje("Error al recibir una conexión...");
	    }
	}
	anotaMensaje("La ejecución del servidor ha terminado.");
    }

    /**
     * Agrega un escucha de servidor.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscucha(EscuchaServidor escucha) {
        // Aquí va su código.
	escuchas.agregaFinal(escucha);
    }

    /**
     * Limpia todos los escuchas del servidor.
     */
    public void limpiaEscuchas() {
        // Aquí va su código.
	escuchas.limpia();
    }

    /* Carga la base de datos del disco duro. */
    private void carga() {
        // Aquí va su código.
	try {
	    anotaMensaje("Cargando base de datos de %s.\n", ruta);
	    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
	    bdd.carga(in);
	    in.close();
	    anotaMensaje("Base de datos cargada exitosamente de %s.\n", ruta);
	} catch (IOException e) {
	    e.printStackTrace();
	    anotaMensaje("Ocurrió un error al tratar de cargar %s.\n", ruta);
	    anotaMensaje("La base de datos estará inicialmente vacía.");
	}	
    }

    /* Guarda la base de datos en el disco duro. */
    private synchronized void guarda() {
        // Aquí va su código.
	anotaMensaje("Guardando base de datos en %s.", ruta);
	try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta)))) {
	    synchronized (bdd) {
		bdd.guarda(out);
	    }
	    out.close();
	    anotaMensaje("Base de datos guardada.");
	} catch (IOException e) {
	    anotaMensaje("Ocurrió un error al tratar de guardar la base de datos en %s.", ruta);
	}	
    }
    
    /* Recibe los mensajes de la conexión. */
    private void mensajeRecibido(Conexion<R> conexion, Mensaje mensaje) {
        // Aquí va su código.
	if (!conexion.isActiva() || continuaEjecucion == false)
	    return;

	switch (mensaje) {
	case BASE_DE_DATOS:
	    baseDeDatos(conexion);
	    break;
	case REGISTRO_AGREGADO:
	    registroAlterado(conexion, Mensaje.REGISTRO_AGREGADO);
	    break;
	case REGISTRO_ELIMINADO:
	    registroAlterado(conexion, Mensaje.REGISTRO_ELIMINADO);
	    break;
	case REGISTRO_MODIFICADO:
	    registroModificado(conexion);
	    break;    
	case DESCONECTAR:
	    desconectar(conexion);
	    break;	    
	case GUARDA:
	    guarda();
	    break;
	case DETENER_SERVICIO:
	    detenerServicio();
	    break;	    
	case ECO:
	    eco(conexion);
	    break;	    
	default:
	    error(conexion, "Mensaje inválido.");
	    break;
	}
    }
    
    /* Maneja el mensaje BASE_DE_DATOS. */
    private void baseDeDatos(Conexion<R> conexion) {
        // Aquí va su código.
	try {
	    conexion.enviaMensaje(Mensaje.BASE_DE_DATOS);
	    conexion.enviaBaseDeDatos();
	} catch(IOException e) {
	    e.printStackTrace();
	    error(conexion, "Error enviando la base de datos.");
	}
	anotaMensaje("Base de datos pedida por %d.", conexion.getSerie());
    }

    /* Maneja los mensajes REGISTRO_AGREGADO y REGISTRO_MODIFICADO. */
    private void registroAlterado(Conexion<R> conexion, Mensaje mensaje) {
        // Aquí va su código.
	try {
	    R registro = conexion.recibeRegistro();
	    switch (mensaje) {
            case REGISTRO_AGREGADO:
                bdd.agregaRegistro(registro);
                break;
            case REGISTRO_ELIMINADO:
                bdd.eliminaRegistro(registro);
                break;
	    }
	    for (Conexion<R> otra : conexiones) {
		if (otra == conexion)
		    continue;
		try {
		    otra.enviaMensaje(mensaje);
		    otra.enviaRegistro(registro);
		} catch (IOException e) {
		    error(conexion, "Error enviando registro.");
		}
	    }
	} catch (IOException e) {
	    error(conexion, "Error recibiendo registro.");
	    return;
	}
	
	if (mensaje == Mensaje.REGISTRO_AGREGADO)
	    anotaMensaje("Registro agregado por %d.", conexion.getSerie());
	else
	    anotaMensaje("Registro eliminado por %d.", conexion.getSerie());
	guarda();
    }

    /* Maneja el mensaje REGISTRO_MODIFICADO. */
    private void registroModificado(Conexion<R> conexion) {
        // Aquí va su código.
	try {
	    R registro1 = conexion.recibeRegistro();
	    R registro2 = conexion.recibeRegistro();
	    modificaRegistro(registro1, registro2);
	    for (Conexion<R> otra : conexiones) {
		if (otra == conexion)
		    continue;
		try {
		    otra.enviaMensaje(Mensaje.REGISTRO_MODIFICADO);
		    otra.enviaRegistro(registro1);
		    otra.enviaRegistro(registro2);
		} catch (IOException e) {
		    error(conexion, "Error enviando registros.");
		}
	    }
	} catch (IOException e) {
	    error(conexion, "Error recibiendo registro.");
	    return;
	}
	anotaMensaje("Registro modificado por %d.", conexion.getSerie());
	guarda();
    }

    /* Maneja el mensaje DESCONECTAR. */
    private void desconectar(Conexion<R> conexion) {
        // Aquí va su código.
	anotaMensaje("Solicitud de desconexión de %d.", conexion.getSerie());
	desconecta(conexion);
    }

    /* Maneja el mensaje DETENER_SERVICIO. */
    private void detenerServicio() {
        // Aquí va su código.
	anotaMensaje("Solicitud para detener el servicio.");
	continuaEjecucion = false;
	for (Conexion<R> otra : conexiones)
	    desconecta(otra);
	try {
	    servidor.close();
	} catch (IOException e) {}
    }

    /* Maneja el mensaje ECO. */
    private void eco(Conexion<R> conexion) {
        // Aquí va su código.
	anotaMensaje("Solicitud de eco de %d.", conexion.getSerie());
	try {
	    conexion.enviaMensaje(Mensaje.ECO);
	} catch (IOException e) {
	    error(conexion, "Error enviando eco.");
	}
    }

    /* Imprime un mensaje a los escuchas y desconecta la conexión. */
    private void error(Conexion<R> conexion, String mensaje) {
        // Aquí va su código.
	anotaMensaje("Desconectando la conexión %d: Mensaje inválido.", conexion.getSerie(), mensaje);
	desconecta(conexion);
    }

    /* Desconecta la conexión. */
    private void desconecta(Conexion<R> conexion) {
        // Aquí va su código.
	conexion.desconecta();
	synchronized (conexiones) {
	    conexiones.elimina(conexion);
	}
	anotaMensaje(String.format("La conexión %d ha sido desconectada.", conexion.getSerie()));
    }

    /* Agrega el registro a la base de datos. */
    private synchronized void agregaRegistro(R registro) {
        // Aquí va su código.
	bdd.agregaRegistro(registro);
    }

    /* Elimina el registro de la base de datos. */
    private synchronized void eliminaRegistro(R registro) {
        // Aquí va su código.}
	bdd.eliminaRegistro(registro);
    }

    /* Modifica el registro en la base de datos. */
    private synchronized void modificaRegistro(R registro1, R registro2) {
        // Aquí va su código.
	bdd.modificaRegistro(registro1, registro2);
    }

    /* Procesa los mensajes de todos los escuchas. */
    private void anotaMensaje(String formato, Object ... argumentos) {
        // Aquí va su código.
	for(EscuchaServidor escucha : escuchas)
	    escucha.procesaMensaje(formato, argumentos);
    }

    /**
     * Crea la base de datos concreta.
     * @return la base de datos concreta.
     */
    public abstract BaseDeDatos<R, ?> creaBaseDeDatos();
}
