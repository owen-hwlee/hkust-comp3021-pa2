package hk.ust.comp3021.gui;

import hk.ust.comp3021.game.SokobanGame;
import javafx.application.Application;

/**
 * A wrapper implement for SokobanGame that launches the JavaFX app.
 */
public class JavaFXGameWrapper implements SokobanGame {

    @Override
    public void run() {
        Application.launch(App.class);
    }
}
