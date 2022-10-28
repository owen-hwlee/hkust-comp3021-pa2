package hk.ust.comp3021.gui.component.control;

import hk.ust.comp3021.actions.Action;
import hk.ust.comp3021.actions.Undo;
import hk.ust.comp3021.entities.Player;
import hk.ust.comp3021.game.InputEngine;
import hk.ust.comp3021.gui.utils.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Control logic for a {@link ControlPanel}.
 * ControlPanelController serves as {@link InputEngine} for the game.
 * It caches users input (move actions) and provides them to the {@link hk.ust.comp3021.gui.scene.game.GUISokobanGame}.
 */
public class ControlPanelController implements Initializable, InputEngine {
    @FXML
    private FlowPane playerControls;

    private volatile Action currentAction;

    /**
     * Fetch the next action made by users.
     * All the actions performed by users should be cached in this class and returned by this method.
     *
     * @return The next action made by users.
     */
    @Override
    public @NotNull Action fetchAction() {
        // DONE
        // Will only return Move.Left, Move.Right, Move.Up, Move.Down, Undo

        // Busy wait
        // FIXME: several concurrency issues
        //  Find method to do wait better than busy wait: temporarily solved by volatile keyword
        //  While-loop does not terminate even after GameScene ends: see Discussion #113
        while (Objects.isNull(this.currentAction)) {
        }

        // Provide Action for game to process and clear cached Action
        Action action = this.currentAction;
        this.currentAction = null;
        return action;
    }

    /**
     * Initialize the controller as you need.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // DONE
        // Add event handler for MoveEvent
        this.playerControls.addEventHandler(MoveEvent.PLAYER_MOVE_EVENT_TYPE, (moveEvent -> {
            this.currentAction = moveEvent.getMove();
            moveEvent.consume();
        }));
    }

    /**
     * Event handler for the undo button.
     * Cache the undo action and return it when {@link #fetchAction()} is called.
     *
     * @param event Event data related to clicking the button.
     */
    public void onUndo(ActionEvent event) {
        // DONE

        this.currentAction = new Undo(-1);

        event.consume();
    }

    /**
     * Adds a player to the control player.
     * Should add a new movement button group for the player.
     *
     * @param player         The player.
     * @param playerImageUrl The URL to the profile image of the player
     */
    public void addPlayer(Player player, URL playerImageUrl) {
        // DONE
        try {
            MovementButtonGroup movementButtonGroup = new MovementButtonGroup();
            movementButtonGroup.getController().setPlayer(player);
            movementButtonGroup.getController().setPlayerImage(playerImageUrl);

            this.playerControls.getChildren().add(movementButtonGroup);
        } catch (IOException e) {
            Message.error("Failed to add player controls",
                    "Failed to add player control button group for player %d".formatted(player.getId()));
        }
    }

}
