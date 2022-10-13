package hk.ust.comp3021.gui.component.maplist;

import hk.ust.comp3021.gui.component.HasController;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Objects;

/**
 * UI component display a list of maps.
 */
public class MapList extends ListView<MapModel> implements HasController<MapListController> {

    private final MapListController controller;

    /**
     * @throws IOException When fails to load the FXML.
     */
    public MapList() throws IOException {
        super();
        final var fxml = MapList.class.getClassLoader().getResource("components/map-list.fxml");
        final var fxmlLoader = new javafx.fxml.FXMLLoader(Objects.requireNonNull(fxml));
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
        this.controller = fxmlLoader.getController();
    }

    @Override
    public MapListController getController() {
        return controller;
    }
}
