package brick_strategies;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

public class Paddle extends GameObject {
    private static final float MOVEMENT_SPEED = 300;
    private UserInputListener inputListener;

    /**
        Construct a new GameObject instance.
        Parameters:
            topLeftCorner - Position of the object, in window coordinates (pixels). Note that (0,0) is the top-left corner of the window.
            dimensions - Width and height in window coordinates.
            renderable - The renderable representing the object. Can be null, in which case
            minDistanceFromEdge - border for paddle movement
     */
    public Paddle(Vector2 topLeftCorner,
                   Vector2 dimensions,
                   Renderable renderable,
                   UserInputListener inputListener,
                   Vector2 windowDimensions,
                   int minDistanceFromEdge){
        super(topLeftCorner,dimensions,renderable);
        this.inputListener = inputListener;
    }
    /**
    Overrides: update in class danogl.GameObject
    Parameters:
    deltaTime -
     */
    public void update(float deltaTime){
        super.update(deltaTime);
        Vector2 movementDir = Vector2.ZERO;
        if(inputListener.isKeyPressed((KeyEvent.VK_LEFT))){
            movementDir = movementDir.add(Vector2.LEFT.mult(MOVEMENT_SPEED));
        }
        if(inputListener.isKeyPressed((KeyEvent.VK_RIGHT))){
            movementDir = movementDir.add(Vector2.RIGHT.mult(MOVEMENT_SPEED));
        }
        setVelocity(movementDir);
    }
}
