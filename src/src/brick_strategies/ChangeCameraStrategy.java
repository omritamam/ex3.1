package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;
import src.brick_strategies.CollisionStrategy;

public class ChangeCameraStrategy implements CollisionStrategy {
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {

    }
    @Override
    public GameObjectCollection getGameObjectCollection() {
        return null;
    }
}
