package src.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.brick_strategies.CollisionStrategy;

import static src.BrickerGameManager.STATUS_DEFINER_SPEED;

public class StatusDefiner extends GameObject {
    private final CollisionStrategy collisionStrategy;

    /**
     * Construct a new GameObject instance.
     * @param topLeftCorner - Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions - Width and height in window coordinates.
     * @param renderable - The renderable representing the object. Can be null, in which case
     * @param collisionStrategy - behavior of the status definer when it collied
     * @param brick - a brick to be dropped from
     */
    public StatusDefiner(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, CollisionStrategy collisionStrategy, GameObject brick) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionStrategy = collisionStrategy;
        setCenter(brick.getCenter());
        setVelocity(Vector2.DOWN.mult(STATUS_DEFINER_SPEED));
    }

    /**
     * Called on the first frame of a collision.
     * @param other The GameObject with which a collision occurred.
     * @param collision Information regarding this collision.
     *                  A reasonable elastic behavior can be achieved with:
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        collisionStrategy.onCollision(this, other, new Counter());
    }

    /**
     *
     * @param other The GameObject with which a collision occurred.
     * @return - true if other is a Paddle
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return other instanceof Paddle;
    }
}
