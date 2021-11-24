package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;
import src.brick_strategies.CollisionStrategy;

public class PuckStrategy extends RemoveBrickStrategyDecorator {
    /**
     * Constructor
     *
     * @param toBeDecorated - Collision strategy object to be decorated.
     */
    public PuckStrategy(CollisionStrategy toBeDecorated) {
        super(toBeDecorated);
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {

    }
    @Override
    public GameObjectCollection getGameObjectCollection() {
        return null;
    }
}
