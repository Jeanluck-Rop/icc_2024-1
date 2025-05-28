package mx.unam.ciencias.icc.igu;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.unam.ciencias.icc.CampoChanchito;

/**
 * Clase para diálogos con formas de búsquedas de chanchitos.
 */
public class DialogoBuscaChanchitos extends Stage {

    /* Vista de la forma para realizar búsquedas de chanchitos. */
    private static final String BUSCA_CHANCHITOS_FXML =
        "fxml/forma-busca-chanchitos.fxml";

    /* El controlador. */
    private ControladorFormaBuscaChanchitos controlador;

    /**
     * Define el estado inicial de un diálogo para búsquedas de chanchitos.
     * @param escenario el escenario al que el diálogo pertenece.
     * @throws IOException si no se puede cargar el archivo FXML.
     */
    public DialogoBuscaChanchitos(Stage escenario) throws IOException {
        ClassLoader cl = getClass().getClassLoader();
        FXMLLoader cargador = new FXMLLoader(
            cl.getResource(BUSCA_CHANCHITOS_FXML));
        AnchorPane cristal = (AnchorPane)cargador.load();

        setTitle("Buscar chanchitos");
        initOwner(escenario);
        initModality(Modality.WINDOW_MODAL);
        Scene escena = new Scene(cristal);
        setScene(escena);
        controlador = cargador.getController();
        controlador.setEscenario(this);
        setOnShown(w -> controlador.defineFoco());
        setResizable(false);
    }

    /**
     * Nos dice si el usuario activó el botón de aceptar.
     * @return <code>true</code> si el usuario activó el botón de aceptar,
     *         <code>false</code> en otro caso.
     */
    public boolean isAceptado() { 
	return controlador.isAceptado();
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoChanchito getCampo() {
	return controlador.getCampo();
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
	return controlador.getValor();
    }
}
