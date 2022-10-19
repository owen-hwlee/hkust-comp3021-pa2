package hk.ust.comp3021.gui.scene.start;

import hk.ust.comp3021.gui.component.maplist.MapEvent;
import hk.ust.comp3021.gui.component.maplist.MapList;
import hk.ust.comp3021.gui.component.maplist.MapModel;
import hk.ust.comp3021.gui.utils.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;

/**
 * Control logic for {@link  StartScene}.
 */
public class StartController implements Initializable {
    @FXML
    private MapList mapList;

    @FXML
    private Button deleteButton;

    @FXML
    private Button openButton;

    /**
     * Initialize the controller.
     * Load the built-in maps to {@link this#mapList}.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // DONE

        // Disable buttons, default does not select any items
        openButton.setDisable(true);
        deleteButton.setDisable(true);

        // Load built-in maps map00.map and map01.map to mapList
        try {
            File map00 = new File(StartController.class.getClassLoader().getResource("map00.map").toURI());
            File map01 = new File(StartController.class.getClassLoader().getResource("map01.map").toURI());

            File[] builtInMapFiles = { map00, map01 };
            addMapsToMapList(builtInMapFiles);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Message.error("Failed to load built-in maps.", "Built-in map files could not be loaded.");
        } catch (NullPointerException e) {
            e.printStackTrace();
            Message.error("Failed to load built-in maps.", "Built-in map files could not be located.");
        }
    }

    /**
     * Event handler for the open button.
     * Display a file chooser, load the selected map and add to {@link this#mapList}.
     * If the map is invalid or cannot be loaded, display an error message.
     *
     * @param event Event data related to clicking the button.
     */
    @FXML
    private void onLoadMapBtnClicked(ActionEvent event) {
        // DONE
        // Display file chooser
        FileChooser fileChooser = new FileChooser();
        List<File> fileList = fileChooser.showOpenMultipleDialog(null);
        if (Objects.isNull(fileList)) {
            return;
        }
        File[] mapFiles = new File[fileList.size()];
        fileList.toArray(mapFiles);
        // Validate and add maps to mapList
        addMapsToMapList(mapFiles);
    }

    /**
     * Handle the event when the delete button is clicked.
     * Delete the selected map from the {@link this#mapList}.
     */
    @FXML
    public void onDeleteMapBtnClicked() {
        // TODO
        this.mapList.getItems().remove(this.mapList.getFocusModel().getFocusedIndex());
    }

    /**
     * Handle the event when the map open button is clicked.
     * Retrieve the selected map from the {@link this#mapList}.
     * Fire an {@link MapEvent} so that the {@link hk.ust.comp3021.gui.App} can handle it and switch to the game scene.
     */
    @FXML
    public void onOpenMapBtnClicked() {
        // TODO
        MapModel focusMapModel = this.mapList.getFocusModel().getFocusedItem();

//        MapEvent mapEvent = new MapEvent(, focusMapModel);
//        MapEvent.fireEvent(, mapEvent);
    }

    /**
     * Handle the event when a file is dragged over.
     *
     * @param event The drag event.
     * @see <a href="https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm">JavaFX Drag and Drop</a>
     */
    @FXML
    public void onDragOver(DragEvent event) {
        // TODO
//        if (event.getGestureSource() != target &&
//                event.getDragboard().hasString()) {
//            /* allow for both copying and moving, whatever user chooses */
//            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//        }
//
//        event.consume();
    }

    /**
     * Handle the event when the map file is dragged to this app.
     * <p>
     * Multiple files should be supported.
     * Display error message if some dragged files are invalid.
     * All valid maps should be loaded.
     *
     * @param dragEvent The drag event.
     * @see <a href="https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm">JavaFX Drag and Drop</a>
     */
    @FXML
    public void onDragDropped(DragEvent dragEvent) {
        // TODO
//        Dragboard db = event.getDragboard();
//        boolean success = false;
        File[] mapFiles = new File[0];
        // TODO: Put down absolute paths
//        if (db.hasString()) {
//            target.setText(db.getString());
//            success = true;
//        }
        addMapsToMapList(mapFiles);
//        /* let the source know whether the string was successfully
//         * transferred and used */
//        event.setDropCompleted(success);
//
//        event.consume();
    }

    // Helper function: validate and add maps to mapList
    // Assume function argument is array of absolute paths
    // Used in initialize and after loadMap and dragDropped
    private void addMapsToMapList(File[] mapFiles) {
        for (final File mapFile: mapFiles) {
            try {
                // Validate map file extension
                if (!mapFile.getCanonicalPath().endsWith(".map")) {
                    Message.error("Imported map must be a .map file.", "Invalid file type: %s".formatted(mapFile));
                    continue;
                }
                // Validate map file exists
                if (!mapFile.getCanonicalFile().exists()) {
                    Message.error("File not found at given map path.", "Map file at %s does not exist.".formatted(mapFile));
                    continue;
                }

                // On duplicate absolute paths, override previous map and update load timestamp
                // Here, perform "overriding" by deleting the map from memory and reloading
                for (MapModel existingModel: this.mapList.getItems()) {
                    if (existingModel.file().toAbsolutePath().equals(mapFile.toPath())) {
                        this.mapList.getItems().remove(existingModel);
                        break;
                    }
                }

                // Load map from file
                URL url = new URL("file", "", mapFile.getCanonicalPath());
                MapModel mapModel = MapModel.load(url);

                // Add maps to mapList
                this.mapList.getItems().add(mapModel);

                // Sort maps by timestamp of loading each map
                this.mapList.getItems().sort(Comparator
                        .comparing((Function<MapModel, Date>) map -> map.loadAt())
                        .reversed());

                this.mapList.refresh();     // TBC

            } catch (IOException e) {
                // Display error message
                e.printStackTrace();
                Message.error("Failed to access .map file.", "Failed to load map at %s".formatted(mapFile));
            }
        }
    }

}
