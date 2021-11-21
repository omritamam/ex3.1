package brick_strategies;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Counter;

public class CollisionStrategy {
    private GameObjectCollection gameObjectCollection;
    /**
     * constructor
     * @param gameObjectCollection - the global gameObects collections
     */
    public CollisionStrategy(GameObjectCollection gameObjectCollection){
        this.gameObjectCollection = gameObjectCollection;
    }

    /**
     * To be called on brick collision.
     * @param thisObj
     * @param otherObj
     * @param counter - global brick counter.
     */
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter){
        gameObjectCollection.removeGameObject(thisObj, Layer.STATIC_OBJECTS);
        counter.decrement();
    }
}
