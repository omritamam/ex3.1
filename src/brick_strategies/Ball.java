package brick_strategies;

public class Ball {
    /*
    Construct a new GameObject instance.
    Parameters:
        topLeftCorner - Position of the object, in window coordinates (pixels). Note that (0,0) is the top-left corner of the window.
        dimensions - Width and height in window coordinates.
        renderable - The renderable representing the object. Can be null, in which case
     */
    public Ball(danogl.util.Vector2 topLeftCorner,
                 danogl.util.Vector2 dimensions,
                 danogl.gui.rendering.Renderable renderable,
                 danogl.gui.Sound collisionSound){}
    /*
    On collision, object velocity is reflected about the normal vector of the surface it collides with.
    Overrides: onCollisionEnter in class danogl.GameObject
    Parameters:
        other - other GameObject instance participating in collision.
        collision - Collision object.
    */
    public void onCollisionEnter(danogl.GameObject other, danogl.collisions.Collision collision){}
}
