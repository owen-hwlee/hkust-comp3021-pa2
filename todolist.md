# To-do List for COMP3021 PA2

This list documents the progress of implementation of PA2, with reference to the given marking scheme in README.md of PA2.

## Grading scheme and completeness

- [x] Start Scene
  - [x] By default, two built-in maps (`map00.map` and `map01.map`) (at `src/main/resources`) should be preloaded into the map list.
  - [x] Load Map
    - [x] `Load Map` button should open a file chooser to select a map file.
    - [x] If an invalid map file is given, an error message should be displayed.
    - [x] If a valid map file is selected, the map should be added in the map list.
    - [x] The map list should be sorted according to the timestamp of loading each map.
    - [x] Loading the same map should override the previous one (duplication is decided by the absolute path of map file), and update the load timestamp.
  - [x] Drag map files
Dragging files to the start scene should work the same as clicking `Load Map` button.
    - [x] Dragging multiple files should supported to load multiple map files at once.
    - [x] When any dragged file is invalid, an error message should be displayed for this invalid file.
    - [x] Even if some dragged map files are invalid, other valid files should be processed.
  - [x] Open Map
    - [x] `Open Map` button should be disabled when no map is selected in the map list.
    - [x] `Open Map` button should be enabled when a map is selected in the map list.
    - [x] Clicking `Open Map` button should switch to the game scene, and the selected map should be loaded.
  - [x] Delete Map
    - [x] `Delete Map` button should be disabled when no map is selected in the map list.
    - [x] `Delete Map` button should be enabled when a map is selected in the map list.
    - [x] Clicking `Delete Map` button should delete the selected map from the map list.
  - [x] Items in the map list should be persistent when switching from start scene to game scene and back to start scene.
- [x] Game Scene
  - [x] Exit game
    - [x] Clicking the `Exit` button should switch back to the start scene.
    - [x] Exiting the game should discard any progress of current game, i.e., start game with the same map again should start a new game.
  - [x] Game map
    - [x] The game map should display the same as what is expected, i.e., each position should have the correct entity ( or empty).
    - [x] Each player and its corresponding boxes should have the same color.
    - [x] When a box is moved to a destination place, a green tick `✓` should be displayed on top of the box.
    - [x] When a box is moved out of a destination place, the green tick `✓` should be removed.
    - [x] Below the game map, there should be a text area displaying the current undo quota left.
    - [x] The undo quota should be updated when an undo action is performed.
    - [x] The undo quota text should show `unlimited` if the undo quota is unlimited (-1 in the game map file).
  - [x] Player Control panel
    - [x] Each player should have its own control panel on the right of the game scene (4 players at most).
    - [x] The control panel should display the player's picture in the middle, and buttons to move the player in the four directions.
    - [x] The number of control panels should be the same as the number of players in the game.
    - [x] Each control panel should only control the corresponding player.
  - [x] Undo
    - [x] Clicking the `Undo` button should revert to the previous checkpoint (same as PA1).
  - [x] Messages
    - [x] When a player performs an invalid move, there should be an error message displayed.
    - [x] When the game wins, there should be a message displayed.

## Non-specified implementations

- [x] Start stage
  - [x] Initial
    - [x] Both `Open Map` and `Delete Map` buttons are disabled
    - [x] No games are selected
  - [x] Load map
    - [x] Maps with more than 4 players should not be loaded, and should display an error message
      - Handled in StartScene (verified by TAs in Discussion #116)
      - `map13.map`, with 5 players and 10 boxes, should not be allowed
    - [x] `Load Map` button only need to support choosing single file (verified by TAs in Discussion #125)
  - [x] MapList
    - [x] Sort by timestamp, either ascending or descending is fine (verified by TAs in Discussion #114)
  - [x] MapListItem
    - [x] Should display name, file path, load time properly in their respective `@FXML` components
  - [x] Set up event handlers in `App::start`
    - [x] `App::onOpenMap` for `MapEvent`
    - [x] `App::onExitGame` for `ExitEvent`
  - [x] Delete map
    - [x] After deleting map, the MapList should not have a selected item
      - Observable by the `Open Map` and `Delete Map` buttons being disabled
- [x] Game stage
  - [x] Exiting game
    - [x] What to do if user directly closes window instead of `Exit` button? Ans: anything, not specified in requirements (verified by TAs in Discussion #115)
  - [x] GameBoard
    - [x] Positions outside wall can have `null` value and no entity, and those boxes should not show green tick
    - [x] All rendering should be wrapped in `Platform.runLater()` to avoid Thread error

## Issue board

- [x] `MapModel::load`: Path to URL single slash problem (Windows-specific)
  - `MapModel::load` uses `Path.of(url.getPath())`
  - If we previously use `.toURI().toURL()` methods, the returned `URL` object begins with `file/C:/...` instead of `file///C:/...`
  - `URL::getPath()` then returns `/C:/...`, which is an invalid file path, resulting in parsing error
  - [x] Solutions:
    - [x] Solution 1: Use `new URL()` constructor and feed canonical path to ensure path uniqueness
    - [ ] Solution 2: Use `Paths.get` instead (TA fix)
- [x] `MapListItemController`: `this.mapModelProperty` has `null` value during initialization
  - `MapListCell::updateItem` is called after `MapListItemController::initialize`
  - `this.mapModelProperty.value` had not been set, so has `null` value
  - [x] Solution: add `StringBinding` to `textProperty` of `@FXML` Labels
- [x] `GameBoardController::render`: performing `this.map.add()` repeatedly to add all `Cell()`s will result in Threading issue, causing `Not on FX Application Thread` error
  - [x] Solution: use `Platform.runLater()` to wrap the `.add(Cell)` operation
- [x] `CheckStyle` returns style error with `MapEvent.OPEN_MAP_EVENT_TYPE`
  - [x] Solution: TA updated skeleton code to add `final` keyword
- [ ] [Optional] The JavaFX Application Thread `gameThreadLoop` was not properly handled, so the thread is not terminated even after exit, stuck at `.fetchAction()`
  - [ ] Solution: see TA's remarks in Discussion #113
- [x] [Optional] `ControlPanelController`: using busy wait in `.fetchAction()` requires placing a time consuming operation such as `System.out.println()` inside the while-loop, otherwise `.fetchAction()` does not proceed
  - Likely originated from different caching location for `this.currentAction` used in while-loop condition and event handler
  - Preferrably NOT `Thread` related fix, since threading is not needed for this programming assignment
  - If possible, not use busy wait since it consumes CPU resources
  - [x] Solutions:
    - [x] Solution 1: use `volatile` keyword in busy wait
      - CheckStyle will issue an error for using an empty while-loop
    - [ ] Solution 2: use Lock classes provided by Java
    - [ ] Solution 3: use `synchronous`, `.notify()`, `.wait()`
      - May result in `java.lang.IllegalMonitorStateException`: current thread is not owner

## This is the end of this todo list.
