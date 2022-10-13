package hk.ust.comp3021.gui.scene.start;

import hk.ust.comp3021.gui.component.HasController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;

/**
 * The game selection scene.
 */
public class StartScene extends Scene implements HasController<StartController> {

    private final StartController controller;

    /**
     * @throws IOException When fails to load the FXML.
     */
    public StartScene() throws IOException {
        super(new Label("Loading..."));
        final var fxml = getClass().getClassLoader().getResource("scene/start.fxml");
        final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
        final var startPane = loader.<GridPane>load();
        this.setRoot(startPane);
        controller = loader.getController();
    }

    @Override
    public StartController getController() {
        return controller;
    }
}
