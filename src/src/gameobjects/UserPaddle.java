package src.gameobjects;

import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

public class UserPaddle extends Paddle{
    /**
     * Construct a new GameObject instance.
     * @param topLeftCorner  Position of the object, in window coordinates (pixels). Note that (0,0) is the top-left
     *                      corner of the window.
     * @param dimensions Width and height in window coordinates.
     * @param renderable The renderable representing the object. Can be null, in which case
     *                   minDistanceFromEdge - border for paddle movement
     * @param inputListener - Contains a single method: isKeyPressed,
                              which returns whether a given key is currently pressed by the user or not.
     * @param windowDimensions -dimensions in pixels. can be null to indicate a
     *                          full-screen window whose size in pixels is the main screen's resolution
     *
     * @param minDistanceFromEdge - number of pixels from end og screen, from right and left
     */
    public UserPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, UserInputListener inputListener, Vector2 windowDimensions, int minDistanceFromEdge) {
        super(topLeftCorner, dimensions, renderable, inputListener, windowDimensions, minDistanceFromEdge);
        super.setTag("Paddle");

    }

    /**
     * @param deltaTime The time elapsed, in seconds, since the last frame. Can
     *                  be used to determine a new position/velocity by multiplying
     *                  this delta with the velocity/acceleration respectively
     *                  and adding to the position/velocity:
     *                  velocity += deltaTime*acceleration
     */
    @Override
    public void update(float deltaTime) {
        Vector2 movementDir = Vector2.ZERO;
        if(inputListener.isKeyPressed((KeyEvent.VK_LEFT))){
            movementDir = movementDir.add(Vector2.LEFT.mult(MOVEMENT_SPEED));
        }
        if(inputListener.isKeyPressed((KeyEvent.VK_RIGHT))){
            movementDir = movementDir.add(Vector2.RIGHT.mult(MOVEMENT_SPEED));
        }
        setVelocity(movementDir);
        super.update(deltaTime);
    }
}
