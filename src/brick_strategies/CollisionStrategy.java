package brick_strategies;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;

public class CollisionStrategy {
    public CollisionStrategy(GameObjectCollection gameObjectCollection){}
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter){}
}
