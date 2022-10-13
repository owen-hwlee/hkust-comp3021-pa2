package hk.ust.comp3021.gui.component.maplist;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Control logic for {@link MapListItem}.
 */
public class MapListItemController implements Initializable {
    @FXML
    private GridPane item;

    @FXML
    private Label mapName;

    @FXML
    private Label loadAt;

    @FXML
    private Label mapFilePath;

    private final Property<MapModel> mapModelProperty = new SimpleObjectProperty<>();

    /**
     * Initialize the controller as you need.
     * You should update the displayed information for the list item when the map model changes.
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
     * @return The property for the map model.
     */
    public Property<MapModel> getMapModelProperty() {
        return mapModelProperty;
    }
}
