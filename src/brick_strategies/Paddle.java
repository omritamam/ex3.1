package brick_strategies;

public class Paddle {
    /*
    Construct a new GameObject instance.
    Parameters:
        topLeftCorner - Position of the object, in window coordinates (pixels). Note that (0,0) is the top-left corner of the window.
        dimensions - Width and height in window coordinates.
        renderable - The renderable representing the object. Can be null, in which case
        minDistanceFromEdge - border for paddle movement
     */
    public Paddle(danogl.util.Vector2 topLeftCorner,
                   danogl.util.Vector2 dimensions,
                   danogl.gui.rendering.Renderable renderable,
                   danogl.gui.UserInputListener inputListener,
                   danogl.util.Vector2 windowDimensions,
                   int minDistanceFromEdge){}
    /*
    Overrides: update in class danogl.GameObject
    Parameters:
    deltaTime -
     */
    public void update(float deltaTime){}
}
