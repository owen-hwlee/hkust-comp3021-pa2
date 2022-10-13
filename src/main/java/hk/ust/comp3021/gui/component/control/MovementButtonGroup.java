package hk.ust.comp3021.gui.component.control;

import hk.ust.comp3021.gui.component.HasController;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;

/**
 * UI component that contains buttons for moving a play left, right, up, and down.
 */
public class MovementButtonGroup extends GridPane implements HasController<MovementButtonGroupController> {
    private final MovementButtonGroupController controller;

    /**
     * @throws IOException When fails to load the FXML.
     */
    public MovementButtonGroup() throws IOException {
        final var fxml = MovementButtonGroupController.class.getClassLoader().getResource("components/movement-button-group.fxml");
        final var fxmlLoader = new javafx.fxml.FXMLLoader(Objects.requireNonNull(fxml));
        fxmlLoader.setRoot(this);
        fxmlLoader.<MovementButtonGroupController>load();
        this.controller = fxmlLoader.getController();
    }

    @Override
    public MovementButtonGroupController getController() {
        return controller;
    }
}
