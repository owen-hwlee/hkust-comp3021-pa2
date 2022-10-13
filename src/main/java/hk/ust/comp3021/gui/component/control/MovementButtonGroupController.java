package hk.ust.comp3021.gui.component.control;

import hk.ust.comp3021.entities.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Control logic for {@link MovementButtonGroup}.
 */
public class MovementButtonGroupController implements Initializable {
    @FXML
    private GridPane playerControl;

    @FXML
    private ImageView playerImage;

    private Player player = null;

    /**
     * Sets the player controller by the button group.
     *
     * @param player The player.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * The URL to the profile image of the player.
     *
     * @param url The URL.
     */
    public void setPlayerImage(URL url) {
        // TODO
    }

    @FXML
    private void moveUp() {
        // TODO
    }

    @FXML
    private void moveDown() {
        // TODO
    }

    @FXML
    private void moveLeft() {
        // TODO
    }

    @FXML
    private void moveRight() {
        // TODO
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }
}
