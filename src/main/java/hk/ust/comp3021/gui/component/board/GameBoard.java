package hk.ust.comp3021.gui.component.board;

import hk.ust.comp3021.gui.component.HasController;
import hk.ust.comp3021.gui.component.control.MovementButtonGroupController;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

/**
 * UI component for displaying the game board.
 */
public class GameBoard extends VBox implements HasController<GameBoardController> {

    private final GameBoardController controller;

    /**
     * Default constructor
     *
     * @throws IOException When fails to load the FXML.
     */
    public GameBoard() throws IOException {
        super();
        final var fxml = MovementButtonGroupController.class.getClassLoader().getResource("components/board.fxml");
        final var fxmlLoader = new javafx.fxml.FXMLLoader(Objects.requireNonNull(fxml));
        fxmlLoader.setRoot(this);
        fxmlLoader.<GameBoardController>load();
        this.controller = fxmlLoader.getController();
    }

    @Override
    public GameBoardController getController() {
        return controller;
    }
}
