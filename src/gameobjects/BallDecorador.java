package gameobjects;

import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import src.brick_strategies.CollisionStrategy;
import src.gameobjects.Ball;

public class BallDecorador extends Ball {
    private Vector2 windowDimensions;
    private GameObjectCollection objectCollection;

    /**
     * Constructor
     * @param topLeftCorner  - - Position of the object, in window coordinates (pixels). Note that (0,0) is the top-left corner of the window.
     * @param dimensions     Width and height in window coordinates.
     * @param renderable     The renderable representing the object. Can be null, in which case
     * @param collisionSound - instance of Sound to be played when collision occurs
     * @param windowDimensions
     * @param objectCollection
     */
    public BallDecorador(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound, Vector2 windowDimensions, GameObjectCollection objectCollection) {
        super(topLeftCorner, dimensions, renderable, collisionSound);
        this.windowDimensions = windowDimensions;
        this.objectCollection = objectCollection;
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 position = getTopLeftCorner();
        if(position.x()<0 || position.x()>windowDimensions.x() ||
                position.y() > windowDimensions.y()+10){
            objectCollection.removeGameObject(this);
        }
    }
}
