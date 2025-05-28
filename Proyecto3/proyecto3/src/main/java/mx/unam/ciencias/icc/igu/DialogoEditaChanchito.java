package mx.unam.ciencias.icc.igu;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.unam.ciencias.icc.ChanchitoFeliz;

/**
 * Clase para diálogos con formas para editar chanchitos.
 */
public class DialogoEditaChanchito extends Stage {

    /* Vista de la forma para agregar/editar chanchitos. */
    private static final String EDITA_CHANCHITO_FXML =
        "fxml/forma-edita-chanchito.fxml";

    /* El controlador. */
    private ControladorFormaEditaChanchitos controlador;

    /**
     * Define el estado inicial de un diálogo para chanchito.
     * @param escenario el escenario al que el diálogo pertenece.
     * @param chanchito el chanchito; puede ser <code>null</code> para agregar
     *                   un chanchito.
     * @throws IOException si no se puede cargar el archivo FXML.
     */
    public DialogoEditaChanchito(Stage escenario,
                                  ChanchitoFeliz chanchito) throws IOException {
        ClassLoader cl = getClass().getClassLoader();
        FXMLLoader cargador =
            new FXMLLoader(cl.getResource(EDITA_CHANCHITO_FXML));
        AnchorPane cristal = (AnchorPane)cargador.load();

        if (chanchito == null)
            setTitle("Agregar chanchito feliz");
        else
            setTitle("Editar chanchito feliz");
        initOwner(escenario);
        initModality(Modality.WINDOW_MODAL);
        Scene escena = new Scene(cristal);
        setScene(escena);

        controlador = cargador.getController();
        controlador.setEscenario(this);
        controlador.setChanchitoFeliz(chanchito);
        if (chanchito == null)
            controlador.setVerbo("Agregar");
        else
            controlador.setVerbo("Actualizar");

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
     * Regresa el chanchito feliz del diálogo.
     * @return el chanchito feliz del diálogo.
     */
    public ChanchitoFeliz getChanchitoFeliz() {
	return controlador.getChanchitoFeliz();
    }
}
