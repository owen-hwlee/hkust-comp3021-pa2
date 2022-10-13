package hk.ust.comp3021.gui.utils;

import javafx.scene.control.Alert;

/**
 * Utility class for popping up message dialogs.
 */
public class Message {
    /**
     * Pops up a message dialog with an error icon.
     *
     * @param header  The header of the message.
     * @param content The content of the message.
     */
    public static void error(String header, String content) {
        alert(Alert.AlertType.ERROR, header, content);
    }

    /**
     * Pops up a message dialog with an info icon.
     *
     * @param header  The header of the message.
     * @param content The content of the message.
     */
    public static void info(String header, String content) {
        alert(Alert.AlertType.INFORMATION, header, content);
    }

    /**
     * Pops up a message dialog with a specific alert icon.
     *
     * @param type    The type of the icon.
     * @param header  The header of the message.
     * @param content The content of the message.
     */
    public static void alert(Alert.AlertType type, String header, String content) {
        var alert = new Alert(type);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
