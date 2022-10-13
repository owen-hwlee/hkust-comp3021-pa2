package hk.ust.comp3021.gui.scene.game;

import hk.ust.comp3021.game.GameState;
import hk.ust.comp3021.gui.component.HasController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;

/**
 * The root scene of the game.
 */
public class GameScene extends Scene implements HasController<GameSceneController> {

    private final GameSceneController controller;

    /**
     * @param gameState The game state.
     * @throws IOException When fails to load the FXML.
     */
    public GameScene(GameState gameState) throws IOException {
        super(new Label("Game Loading..."));
        final var fxml = getClass().getClassLoader().getResource("scene/game.fxml");
        final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
        final var startPane = loader.<GridPane>load();
        this.setRoot(startPane);
        this.controller = loader.getController();
        controller.setGameState(gameState);
    }

    @Override
    public GameSceneController getController() {
        return controller;
    }
}
