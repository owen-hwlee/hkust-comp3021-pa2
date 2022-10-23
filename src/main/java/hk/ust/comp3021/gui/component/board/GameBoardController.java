package hk.ust.comp3021.gui.component.board;

import hk.ust.comp3021.entities.*;
import hk.ust.comp3021.game.GameState;
import hk.ust.comp3021.game.Position;
import hk.ust.comp3021.game.RenderingEngine;
import hk.ust.comp3021.gui.utils.Message;
import hk.ust.comp3021.gui.utils.Resource;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Control logic for a {@link GameBoard}.
 * <p>
 * GameBoardController serves the {@link RenderingEngine} which draws the current game map.
 */
public class GameBoardController implements RenderingEngine, Initializable {
    @FXML
    private GridPane map;

    @FXML
    private Label undoQuota;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Draw the game map in the {@link #map} GridPane.
     *
     * @param state The current game state.
     */
    @Override
    public void render(@NotNull GameState state) {
        // DONE

        // Clear all map cells for re-render
        Platform.runLater(() -> this.map.getChildren().clear());

        // Add undoQuota to board
        this.undoQuota.setText(
                "Undo quota: " +
                        (state.getUndoQuota().isPresent() ? state.getUndoQuota().get().toString() : "unlimited")
        );

        // Re-render the map
        try {
            for (int rowIndex = 0; rowIndex < state.getMapMaxHeight(); ++rowIndex) {
                for (int columnIndex = 0; columnIndex < state.getMapMaxWidth(); ++columnIndex) {
                    Cell cell = new Cell();
                    Entity entity = state.getEntity(Position.of(columnIndex, rowIndex));
                    if (Objects.nonNull(entity)) {
                        URL imageUrl = switch (entity) {
                            case Player player -> Resource.getPlayerImageURL(player.getId());
                            case Box box -> {
                                // Check if box is on destination
                                if (state.getDestinations().contains(Position.of(columnIndex, rowIndex))) {
                                    cell.getController().markAtDestination();
                                }
                                yield Resource.getBoxImageURL(box.getPlayerId());
                            }
                            case Wall ignored -> Resource.getWallImageURL();
                            case Empty ignored -> {
                                // Check if cell is a destination
                                if (state.getDestinations().contains(Position.of(columnIndex, rowIndex))) {
                                    yield Resource.getDestinationImageURL();
                                } else {
                                    yield Resource.getEmptyImageURL();
                                }
                            }
                        };
                        cell.getController().setImage(imageUrl);
                    }
                    int finalColumnIndex = columnIndex;
                    int finalRowIndex = rowIndex;
                    Platform.runLater(() -> this.map.add(cell, finalColumnIndex, finalRowIndex));
                }
            }
        } catch (IOException e) {
            Platform.runLater(() -> Message.error("Render error", "Unable to render game board cells."));
        }
    }

    /**
     * Display a message via a dialog.
     *
     * @param content The message
     */
    @Override
    public void message(@NotNull String content) {
        Platform.runLater(() -> Message.info("Sokoban", content));
    }
}
