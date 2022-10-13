package hk.ust.comp3021.gui.component.control;

import hk.ust.comp3021.gui.component.HasController;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

/**
 * UI component for operating the game.
 * Contains one {@link MovementButtonGroup} for each of the player, and an undo button.
 */
public class ControlPanel extends VBox implements HasController<ControlPanelController> {
    private final ControlPanelController controller;

    /**
     * Default constructor.
     *
     * @throws IOException When fails to load the FXML.
     */
    public ControlPanel() throws IOException {
        super();
        final var fxml = getClass().getClassLoader().getResource("components/control-panel.fxml");
        final var fxmlLoader = new javafx.fxml.FXMLLoader(Objects.requireNonNull(fxml));
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
        this.controller = fxmlLoader.getController();
    }

    @Override
    public ControlPanelController getController() {
        return controller;
    }
}
