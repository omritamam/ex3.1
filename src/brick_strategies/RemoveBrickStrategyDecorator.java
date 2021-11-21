package brick_strategies;

import danogl.GameObject;
import danogl.util.Counter;

abstract public class RemoveBrickStrategyDecorator implements CollisionStrategy{
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {

    }
}
