package hk.ust.comp3021.gui.component.board;

import hk.ust.comp3021.gui.component.HasController;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

/**
 * UI component for a call in the {@link GameBoard}.
 */
public class Cell extends StackPane implements HasController<CellController> {
    private final CellController controller;

    /**
     * Default constructor.
     *
     * @throws IOException When fails to load the FXML.
     */
    public Cell() throws IOException {
        super();
        final var fxml = getClass().getClassLoader().getResource("components/board-cell.fxml");
        final var fxmlLoader = new javafx.fxml.FXMLLoader(Objects.requireNonNull(fxml));
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
        this.controller = fxmlLoader.getController();
    }

    @Override
    public CellController getController() {
        return controller;
    }
}
