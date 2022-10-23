package hk.ust.comp3021.gui.component.control;

import hk.ust.comp3021.actions.Move;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event related to moving via arrow buttons in MovementButtonGroup.
 */
public class MoveEvent extends Event {
    /**
     * The event type of performing a move.
     */
    public static final EventType<MoveEvent> PLAYER_MOVE_EVENT_TYPE = new EventType<>("PLAYER_MOVE");

    private final Move move;

    /**
     * @param type  The type of the event.
     * @param move  The move.
     */
    public MoveEvent(EventType<? extends MoveEvent> type, Move move) {
        super(type);
        this.move = move;
    }

    /**
     * @return The map model related to the event.
     */
    public Move getMove() {
        return move;
    }
}
