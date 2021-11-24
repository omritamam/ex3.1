package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.gameobjects.Ball;


public class RemoveBrickStrategyDecorator implements CollisionStrategy {

    private CollisionStrategy collisionStrategy;

    /**
     * Constructor
     * @param toBeDecorated - Collision strategy object to be decorated.
     */
    public RemoveBrickStrategyDecorator(CollisionStrategy toBeDecorated){

        collisionStrategy = toBeDecorated;
    }

    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        collisionStrategy.onCollision(thisObj, otherObj, counter);
        Vector2 directions[] =  new Vector2[]{Vector2.LEFT, Vector2.DOWN, Vector2.RIGHT};
        for(Vector2 direction : directions){
            Ball newBall = new Ball(otherObj.getTopLeftCorner(), otherObj.getDimensions(), otherObj.renderer().getRenderable(),
                    null);
            newBall.setCenter(thisObj.getCenter());
            newBall.setVelocity(direction.mult(otherObj.getVelocity().magnitude()));
            getGameObjectCollection().addGameObject(newBall);
        }
    }

    @Override
    public GameObjectCollection getGameObjectCollection() {
        return collisionStrategy.getGameObjectCollection();
    }
}
