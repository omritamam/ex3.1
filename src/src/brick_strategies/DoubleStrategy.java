package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;

public class DoubleStrategy implements CollisionStrategy {
    final CollisionStrategy strategy1;
    final CollisionStrategy strategy2;

    public DoubleStrategy(CollisionStrategy collisionStrategy1, CollisionStrategy collisionStrategy2) {
        strategy1= collisionStrategy1;
        strategy2= collisionStrategy2;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        counter.increment();
        strategy1.onCollision(thisObj, otherObj, counter);
        strategy2.onCollision(thisObj, otherObj, counter);
    }

    @Override
    public GameObjectCollection getGameObjectCollection() {
        return strategy1.getGameObjectCollection();
    }
}
