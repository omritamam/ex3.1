package src.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Ball extends GameObject {
    private final Sound collisionSound;

    /**
     * Constructor
     * @param topLeftCorner - - Position of the object, in window coordinates (pixels). Note that (0,0) is the top-left corner of the window.
     * @param dimensions Width and height in window coordinates.
     * @param renderable The renderable representing the object. Can be null, in which case
     * @param collisionSound - instance of Sound to be played when collision occurs
     */
    public Ball(Vector2 topLeftCorner,
                Vector2 dimensions,
                Renderable renderable,
                Sound collisionSound){
        super(topLeftCorner, dimensions, renderable);
        super.setTag("Ball");
        this.collisionSound = collisionSound;
    }

    /**
     * On collision, object velocity is reflected about the normal vector of the surface it collides with.
     *  Overrides: onCollisionEnter in class danogl.GameObject
     * @param other The GameObject with which a collision occurred.
     * @param collision Information regarding this collision.
     *                  A reasonable elastic behavior can be achieved with:
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision){
        super.onCollisionEnter(other,collision);
        Vector2 newVal = getVelocity().flipped(collision.getNormal());
        setVelocity(newVal);
        collisionSound.play();
    }

}
