package brick_strategies;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class AIPaddle extends Paddle{
    private static final float MIN_DISTANCE = 5;
    private GameObject objectToFollow;

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
    public AIPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, UserInputListener inputListener,
                    Vector2 windowDimensions, int minDistanceFromEdge, GameObject objectToFollow) {
        super(topLeftCorner, dimensions, renderable, inputListener, windowDimensions, minDistanceFromEdge);
        this.objectToFollow = objectToFollow;
    }

    @Override
    public void update(float deltaTime) {
        Vector2 movementDir = Vector2.ZERO;
        float distanceWithObject = objectToFollow.getCenter().x()-getCenter().x();
        if(Math.abs(distanceWithObject) >= MIN_DISTANCE) {

            if(distanceWithObject<0){
                movementDir=Vector2.LEFT;
            } else {
                movementDir=Vector2.RIGHT;
            }
        }
        setVelocity(movementDir.mult(MOVEMENT_SPEED));
        super.update(deltaTime);
    }
}
