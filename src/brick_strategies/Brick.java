package brick_strategies;

public class Brick {
    /*
    Construct a new GameObject instance.
    Parameters:
    topLeftCorner - Position of the object, in window coordinates (pixels). Note that (0,0) is the top-left corner of the window.
    dimensions - Width and height in window coordinates.
    renderable - The renderable representing the object. Can be null, in which case
     */
    public Brick(danogl.util.Vector2 topLeftCorner,
                  danogl.util.Vector2 dimensions,
                  danogl.gui.rendering.Renderable renderable,
                  CollisionStrategy collisionStrategy,
                  danogl.util.Counter counter){}

    /*
    Overrides: onCollisionEnter in class danogl.GameObject
    Parameters:
        other -
        collision
     */
    public void onCollisionEnter(danogl.GameObject other, danogl.collisions.Collision collision){}
}
