package hk.ust.comp3021.gui.component.maplist;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.util.Objects;

/**
 * UI component for an item in the {@link  MapList}.
 */
public class MapListItem extends GridPane {

    /**
     * Creates a FXML loader.
     *
     * @return The FXML loader.
     */
    public static FXMLLoader fxmlLoader() {
        final var fxml = MapListItem.class.getClassLoader().getResource("components/map-list-item.fxml");
        return new FXMLLoader(Objects.requireNonNull(fxml));
    }
}
