package brick_strategies;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Paddle extends GameObject {
    //TODO: ask about protected
    protected static final float MOVEMENT_SPEED = 300;
    protected final UserInputListener inputListener;
    protected final Vector2  windowDimensions;
    protected final int minDistanceFromEdge;

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
        this.windowDimensions = windowDimensions;
        this.minDistanceFromEdge = minDistanceFromEdge;
    }
    /**
    Overrides: update in class danogl.GameObject
    Parameters:
    deltaTime -
     */
    public void update(float deltaTime){
        super.update(deltaTime);
        float topLeftCorner = getTopLeftCorner().x();
        if(topLeftCorner<minDistanceFromEdge){
            transform().setTopLeftCorner(minDistanceFromEdge, transform().getTopLeftCorner().y());
        }
        else if(topLeftCorner>windowDimensions.x() - minDistanceFromEdge - getDimensions().x()) {

            transform().setTopLeftCorner(windowDimensions.x() - minDistanceFromEdge - getDimensions().x(),
                    transform().getTopLeftCorner().y());

        }
    }
}
