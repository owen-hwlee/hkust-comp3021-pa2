package hk.ust.comp3021.gui.scene.game;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event for exiting the game.
 */
public class ExitEvent extends Event {

    /**
     * The event type for the {@link ExitEvent}.
     */
    public static final EventType<ExitEvent> EVENT_TYPE = new EventType<>(ANY, "EXIT");

    /**
     * Default constructor.
     */
    public ExitEvent() {
        super(EVENT_TYPE);
    }
}
