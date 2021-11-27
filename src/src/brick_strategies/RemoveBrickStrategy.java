package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Counter;

public class RemoveBrickStrategy implements CollisionStrategy {
    private boolean isRemoved = false;
    protected final GameObjectCollection gameObjectCollection;
    /**
     * constructor
     * @param gameObjectCollection - the global gameObects collections
     */
    public RemoveBrickStrategy(GameObjectCollection gameObjectCollection){
        this.gameObjectCollection = gameObjectCollection;
    }


    /**
     * To be called on brick collision.
     * @param thisObj - the gameObject that extends the class
     * @param otherObj - other GameObject instance participating in collision.
     * @param counter - global brick counter
     */
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter){
        if(!isRemoved){
            if(!gameObjectCollection.removeGameObject(thisObj, Layer.STATIC_OBJECTS)){
                gameObjectCollection.removeGameObject(thisObj, Layer.DEFAULT);
            }
            isRemoved=true;
            counter.decrement();
        }
    }
    @Override
    public GameObjectCollection getGameObjectCollection() {
        return gameObjectCollection;
    }
}
