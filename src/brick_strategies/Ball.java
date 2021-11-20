package brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Ball extends GameObject {
    private final Sound collisionSound;

    /*
        Construct a new GameObject instance.
        Parameters:
            topLeftCorner - Position of the object, in window coordinates (pixels). Note that (0,0) is the top-left corner of the window.
            dimensions - Width and height in window coordinates.
            renderable - The renderable representing the object. Can be null, in which case
         */
    public Ball(Vector2 topLeftCorner,
                Vector2 dimensions,
                Renderable renderable,
                Sound collisionSound){
        super(topLeftCorner, dimensions, renderable);
        this.collisionSound = collisionSound;
    }
    /*
    On collision, object velocity is reflected about the normal vector of the surface it collides with.
    Overrides: onCollisionEnter in class danogl.GameObject
    Parameters:
        other - other GameObject instance participating in collision.
        collision - Collision object.
    */
    public void onCollisionEnter(GameObject other, Collision collision){
        super.onCollisionEnter(other,collision);
        Vector2 newVal = getVelocity().flipped(collision.getNormal());
        setVelocity(newVal);
    }
}
