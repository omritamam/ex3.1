package brick_strategies;

import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

public class UserPaddle extends Paddle{
    /**
     * Construct a new GameObject instance.
     * Parameters:
     * topLeftCorner - Position of the object, in window coordinates (pixels). Note that (0,0) is the top-left corner of the window.
     * dimensions - Width and height in window coordinates.
     * renderable - The renderable representing the object. Can be null, in which case
     * minDistanceFromEdge - border for paddle movement
     *
     * @param topLeftCorner
     * @param dimensions
     * @param renderable
     * @param inputListener
     * @param windowDimensions
     * @param minDistanceFromEdge
     */
    public UserPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, UserInputListener inputListener, Vector2 windowDimensions, int minDistanceFromEdge) {
        super(topLeftCorner, dimensions, renderable, inputListener, windowDimensions, minDistanceFromEdge);
    }

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
