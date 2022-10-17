package hk.ust.comp3021.gui;

import hk.ust.comp3021.game.GameState;
import hk.ust.comp3021.gui.component.maplist.MapEvent;
import hk.ust.comp3021.gui.scene.game.ExitEvent;
import hk.ust.comp3021.gui.scene.game.GameScene;
import hk.ust.comp3021.gui.scene.start.StartScene;
import hk.ust.comp3021.gui.utils.Message;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

/**
 * The JavaFX application that launches the game.
 */
public class App extends Application {
    private Stage primaryStage;

    private StartScene startScene;

    /**
     * Set up the primary stage and show the {@link StartScene}.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sokoban Game - COMP3021 2022Fall");

        // DONE
        this.startScene = new StartScene();
        this.primaryStage.setScene(this.startScene);
        this.primaryStage.show();
    }

    /**
     * Event handler for opening a map.
     * Switch to the {@link GameScene} in the {@link this#primaryStage}.
     *
     * @param event The event data related to the map being opened.
     */
    public void onOpenMap(MapEvent event) {
        // DONE
        this.primaryStage.hide();
        Path mapPath = event.getModel().file().toAbsolutePath();
        try {
            this.primaryStage.setScene(new GameScene(new GameState(event.getModel().gameMap())));
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Message.error("Failed to open game map.", "Failed to open game map at %s".formatted(mapPath));
        }
    }

    /**
     * Event handler for exiting the game.
     * Switch to the {@link StartScene} in the {@link this#primaryStage}.
     *
     * @param event The event data related to exiting the game.
     */
    public void onExitGame(ExitEvent event) {
        // DONE
        this.primaryStage.hide();
        this.primaryStage.setScene(this.startScene);
        this.primaryStage.show();
    }
}
