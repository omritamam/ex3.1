package brick_strategies;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Counter;

public class CollisionStrategy {
    private GameObjectCollection gameObjectCollection;

    public CollisionStrategy(GameObjectCollection gameObjectCollection){
        this.gameObjectCollection = gameObjectCollection;
    }
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter){
        gameObjectCollection.removeGameObject(thisObj, Layer.STATIC_OBJECTS);
        System.out.println("collision with brick detected");

    }
}
