package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;



abstract public class RemoveBrickStrategyDecorator implements CollisionStrategy {

    private final CollisionStrategy collisionStrategy;

    /**
     * Constructor
     * @param toBeDecorated - Collision strategy object to be decorated.
     */
    public RemoveBrickStrategyDecorator(CollisionStrategy toBeDecorated){

        collisionStrategy = toBeDecorated;
    }

    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        collisionStrategy.onCollision(thisObj, otherObj, counter);

    }

    @Override
    public GameObjectCollection getGameObjectCollection() {
        return collisionStrategy.getGameObjectCollection();
    }
}
