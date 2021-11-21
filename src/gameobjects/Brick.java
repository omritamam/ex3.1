package gameobjects;
import brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

public class Brick extends GameObject {
    private final CollisionStrategy collisionStrategy;
    private final Counter counter;

    /**
     * constructor
     * @param topLeftCorner -  Position of the object, in window coordinates (pixels). Note that (0,0) is the top-left corner of the window.
     *         dimensions - Width and height in window coordinates.
     * @param dimensions- Width and height in window coordinates.
     * @param renderable - The renderable representing the object. Can be null, in which case
     * @param collisionStrategy - instance of CollisionStrategy with .onCollision(...) method
     * @param counter - global bricks counter of the game
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

    /**
     * Called on the first frame of a collision.
     * @param other The GameObject with which a collision occurred.
     * @param collision Information regarding this collision.
     *                  A reasonable elastic behavior can be achieved with:
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision){
        super.onCollisionEnter(other, collision);
        collisionStrategy.onCollision(this, other, counter);
    }
}
