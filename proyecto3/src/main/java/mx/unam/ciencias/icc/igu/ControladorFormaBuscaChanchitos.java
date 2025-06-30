package mx.unam.ciencias.icc.igu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import mx.unam.ciencias.icc.CampoChanchito;

/**
 * Clase para el controlador del contenido del diálogo para buscar chanchitos.
 */
public class ControladorFormaBuscaChanchitos
    extends ControladorFormaChanchitos {

    /* El combo del campo. */
    @FXML private ComboBox<CampoChanchito> opcionesCampo;
    /* El campo de texto para el valor. */
    @FXML private EntradaVerificable entradaValor;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaValor.setVerificador(s -> verificaValor(s));
        entradaValor.textProperty().addListener(
            (o, v, n) -> revisaValor(null));
    }

    /* Revisa el valor después de un cambio. */
    @FXML private void revisaValor(ActionEvent evento) {
        Tooltip.install(entradaValor, getTooltip());
        botonAceptar.setDisable(!entradaValor.esValida());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
    }

    /* Verifica el valor. */
    private boolean verificaValor(String valor) {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:    return verificaNombre(valor);
        case ORIGEN:    return verificaOrigen(valor);
        case PESO:      return verificaPeso(valor);
        case EDAD:      return verificaEdad(valor);
	case FELICIDAD: return verificaFelicidad(valor);
	default:        return false; // No puede ocurrir.
        }
    }

    /* Obtiene la pista. */
    private Tooltip getTooltip() {
        String m = "";
        switch (opcionesCampo.getValue()) {
        case NOMBRE:
            m = "Buscar por nombre necesita al menos un carácter";
            break;

	case ORIGEN:
            m = "Buscar por nombre necesita al menos un carácter";
            break;

	case PESO:
            m = "Buscar por peso necesita un número entre 00.0 y 120.0";
            break;

	case EDAD:
            m = "Buscar por edad necesita un número entre 1 y 30";
            break;

	case FELICIDAD:
	    m = "Buscar por peso necesita un número entre 0.0 y 100.0";
            break;
        }
        return new Tooltip(m);
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
        switch (opcionesCampo.getValue()) {
            // Aquí va su código.
	case NOMBRE:
	    return entradaValor.getText();
	case ORIGEN:
	    return entradaValor.getText();
	case PESO:
	    return Double.parseDouble(entradaValor.getText());
	case EDAD:
	    return Integer.parseInt(entradaValor.getText());
	case FELICIDAD:
	    return Double.parseDouble(entradaValor.getText());
	default:
	    return entradaValor.getText(); // No puede ocurrir.
        }
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoChanchito getCampo() {
        return opcionesCampo.getValue();
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaValor.requestFocus();
    }
}
