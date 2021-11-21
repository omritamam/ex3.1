package brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;
import danogl.util.Vector2;

public class RemoveBrickStrategyDecorator extends RemoveBrickStrategy {


    /**
     * constructor
     *
     * @param gameObjectCollection - the global gameObects collections
     */
    public RemoveBrickStrategyDecorator(GameObjectCollection gameObjectCollection) {
        super(gameObjectCollection);
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        super.onCollision(thisObj, otherObj, counter);
        Vector2 directions[] =  new Vector2[]{Vector2.LEFT, Vector2.DOWN, Vector2.RIGHT};
        for(Vector2 direction : directions){
//            Ball newBall = new Ball(
//
//                    otherObj.getDimensions(),);
//            newBall.setVelocity(direction.mult(otherObj.getVelocity().magnitude()));
//            gameObjectCollection.addGameObject(newBall);
//
//            ));
        }
    }
}
