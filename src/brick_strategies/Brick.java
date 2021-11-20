package brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

public class Brick extends GameObject {
    private final CollisionStrategy collisionStrategy;
    private final Counter counter;

    /*
        Construct a new GameObject instance.
        Parameters:
        topLeftCorner - Position of the object, in window coordinates (pixels). Note that (0,0) is the top-left corner of the window.
        dimensions - Width and height in window coordinates.
        renderable - The renderable representing the object. Can be null, in which case
         */
    public Brick(Vector2 topLeftCorner,
                 Vector2 dimensions,
                 Renderable renderable,
                 CollisionStrategy collisionStrategy,
                 Counter counter){
        super(topLeftCorner, dimensions,renderable);
        this.collisionStrategy = collisionStrategy;
        this.counter = counter;
    }

    /*
    Overrides: onCollisionEnter in class danogl.GameObject
    Parameters:
        other -
        collision
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision){
        super.onCollisionEnter(other, collision);
        counter.decrement();
        collisionStrategy.onCollision(this, other, counter);
    }
}
