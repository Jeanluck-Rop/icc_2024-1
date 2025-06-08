package mx.unam.ciencias.icc.igu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import mx.unam.ciencias.icc.ChanchitoFeliz;

/**
 * Clase para el controlador del contenido del diálogo para editar y crear
 * chanchitos.
 */
public class ControladorFormaEditaChanchitos
    extends ControladorFormaChanchitos {

    /* La entrada verificable para el nombre. */
    @FXML private EntradaVerificable entradaNombre;
    /* La entrada verificable para el origen. */
    @FXML private EntradaVerificable entradaOrigen;
    /* La entrada verificable para el peso. */
    @FXML private EntradaVerificable entradaPeso;
    /* La entrada verificable para la edad. */
    @FXML private EntradaVerificable entradaEdad;
    /* La entrada verificable para la felicidad. */
    @FXML private EntradaVerificable entradaFelicidad;

    /* El chanchito creado o editado. */
    private ChanchitoFeliz chanchito;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaNombre.setVerificador(n -> verificaNombre(n));
        entradaOrigen.setVerificador(o -> verificaOrigen(o));
        entradaPeso.setVerificador(p -> verificaPeso(p));
        entradaEdad.setVerificador(e -> verificaEdad(e));
	entradaFelicidad.setVerificador(f -> verificaFelicidad(f));
	
        entradaNombre.textProperty().addListener(
            (o, v, n) -> verificaChanchitoFeliz());
        entradaOrigen.textProperty().addListener(
            (o, v, n) -> verificaChanchitoFeliz());
        entradaPeso.textProperty().addListener(
            (o, v, n) -> verificaChanchitoFeliz());
        entradaEdad.textProperty().addListener(
            (o, v, n) -> verificaChanchitoFeliz());
	entradaFelicidad.textProperty().addListener(
            (o, v, n) -> verificaChanchitoFeliz());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        actualizaChanchitoFeliz();
        aceptado = true;
        escenario.close();
    }

    /* Actualiza al estudiante, o lo crea si no existe. */
    private void actualizaChanchitoFeliz() {
        if (chanchito != null) {
            chanchito.setNombre(nombre);
            chanchito.setOrigen(origen);
            chanchito.setPeso(peso);
	    chanchito.setEdad(edad);
	    chanchito.setFelicidad(felicidad);
        } else {
            chanchito = new ChanchitoFeliz(nombre, origen, peso, edad, felicidad);
        }
    }

    /**
     * Define el chanchito del diálogo.
     * @param chanchito el nuevo chanchito del diálogo.
     */
    public void setChanchitoFeliz(ChanchitoFeliz chanchito) {
        this.chanchito = chanchito;
        if (chanchito == null)
            return;
        this.chanchito = new ChanchitoFeliz(null, null, 0, 0, 0);
        this.chanchito.actualiza(chanchito);
        entradaNombre.setText(chanchito.getNombre());
        entradaOrigen.setText(chanchito.getOrigen());
        String p = String.format("%2.2f", chanchito.getPeso());
        entradaPeso.setText(p);
        entradaEdad.setText(String.valueOf(chanchito.getEdad()));
	String q = String.format("%2.2f", chanchito.getFelicidad());
        entradaFelicidad.setText(q);
    }

    /**
     * Regresa el chanchito del diálogo.
     * @return el chanchito del diálogo.
     */
    public ChanchitoFeliz getChanchitoFeliz() {
        return chanchito;
    }

    /**
     * Define el verbo del botón de aceptar.
     * @param verbo el nuevo verbo del botón de aceptar.
     */
    public void setVerbo(String verbo) {
        botonAceptar.setText(verbo);
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaNombre.requestFocus();
    }

    /* Verifica que los cuatro campos sean válidos. */
    private void verificaChanchitoFeliz() {
        boolean n = entradaNombre.esValida();
        boolean o = entradaOrigen.esValida();
        boolean p = entradaPeso.esValida();
        boolean e = entradaEdad.esValida();
	boolean f = entradaFelicidad.esValida();
        botonAceptar.setDisable(!n || !o || !p || !e || !f);
    }

    /**
     * Verifica que el peso sea válido.
     * @param peso, el peso a verificar.
     * @return <code>true</code> si el peso es válido; <code>false</code> en
     *         otro caso.
     */
    @Override protected boolean verificaPeso(String peso) {
        return super.verificaPeso(peso) &&
            this.peso >= 0.0 && this.peso <= 120.0;
    }

    /**
     * Verifica que la edad sea válida.
     * @param edad la edad a verificar.
     * @return <code>true</code> si la edad es válida; <code>false</code> en
     *         otro caso.
     */
    @Override protected boolean verificaEdad(String edad) {
        return super.verificaEdad(edad) &&
            this.edad >= 1 && this.edad <= 30;
    }

    /**
     * Verifica que el nivel de felicidad sea válido.
     * @param felicidad, el nivel de felicidad a verificar.
     * @return <code>true</code> si la felicidad es válida; <code>false</code> en
     *         otro caso.
     */
    @Override protected boolean verificaFelicidad(String felicidad) {
        return super.verificaFelicidad(felicidad) &&
            this.felicidad >= 0.0 && this.felicidad <= 100.0;
    }
}
