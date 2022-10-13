package hk.ust.comp3021.gui.utils;

import java.net.URL;
import java.util.Objects;

/**
 * Utility classes to ease implementing the GUI version of the game.
 */
public class Resource {

    /**
     * @param playerId The ID of the player.
     * @return The URL to the profile image of the player.
     */
    public static URL getPlayerImageURL(int playerId) {
        return Objects.requireNonNull(Resource.class.getClassLoader().getResource(String.format("components/img/player-%d.png", playerId)));
    }

    /**
     * @param playerId The ID of the player.
     * @return The URL to th image of the box belongs to the player.
     */
    public static URL getBoxImageURL(int playerId) {
        return Objects.requireNonNull(Resource.class.getClassLoader().getResource(String.format("components/img/box-%d.png", playerId)));
    }

    /**
     * @return The URL to the image of the wall.
     */
    public static URL getWallImageURL() {
        return Objects.requireNonNull(Resource.class.getClassLoader().getResource("components/img/wall.png"));
    }

    /**
     * @return The URL to the image of an empty location.
     */
    public static URL getEmptyImageURL() {
        return Objects.requireNonNull(Resource.class.getClassLoader().getResource("components/img/empty.png"));
    }

    /**
     * @return The URL to the image of a destination.
     */
    public static URL getDestinationImageURL() {
        return Objects.requireNonNull(Resource.class.getClassLoader().getResource("components/img/destination.png"));
    }

}
