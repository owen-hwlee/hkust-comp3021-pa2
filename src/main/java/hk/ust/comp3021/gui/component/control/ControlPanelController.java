package hk.ust.comp3021.gui.component.control;

import hk.ust.comp3021.actions.Action;
import hk.ust.comp3021.entities.Player;
import hk.ust.comp3021.game.InputEngine;
import hk.ust.comp3021.utils.NotImplementedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Control logic for a {@link ControlPanel}.
 * ControlPanelController serves as {@link InputEngine} for the game.
 * It caches users input (move actions) and provides them to the {@link hk.ust.comp3021.gui.scene.game.GUISokobanGame}.
 */
public class ControlPanelController implements Initializable, InputEngine {
    @FXML
    private FlowPane playerControls;

    /**
     * Fetch the next action made by users.
     * All the actions performed by users should be cached in this class and returned by this method.
     *
     * @return The next action made by users.
     */
    @Override
    public @NotNull Action fetchAction() {
        // TODO
        throw new NotImplementedException();
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
        // TODO
    }

    /**
     * Event handler for the undo button.
     * Cache the undo action and return it when {@link #fetchAction()} is called.
     *
     * @param event Event data related to clicking the button.
     */
    public void onUndo(ActionEvent event) {
        // TODO
    }

    /**
     * Adds a player to the control player.
     * Should add a new movement button group for the player.
     *
     * @param player         The player.
     * @param playerImageUrl The URL to the profile image of the player
     */
    public void addPlayer(Player player, URL playerImageUrl) {
        // TODO
    }

}
