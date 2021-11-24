package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.Sound;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.gameobjects.Ball;

public class SplitTo3Balls extends RemoveBrickStrategyDecorator  {
    private Sound sound;

    /**
     * Constructor
     *
     * @param toBeDecorated - Collision strategy object to be decorated.
     */
    public SplitTo3Balls(CollisionStrategy toBeDecorated, Sound sound) {
        super(toBeDecorated);
        this.sound = sound;
    }


    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        super.onCollision(thisObj, otherObj, counter);
        Vector2[] directions =  new Vector2[]{  Vector2.LEFT.add(Vector2.DOWN),
                                                Vector2.RIGHT.add(Vector2.DOWN)};
        for(Vector2 direction : directions){
            Ball newBall = new Ball(otherObj.getTopLeftCorner(), otherObj.getDimensions(),
                    otherObj.renderer().getRenderable(), sound);
            newBall.setCenter(thisObj.getCenter());
            newBall.setVelocity(direction.mult(otherObj.getVelocity().magnitude()));
            getGameObjectCollection().addGameObject(newBall, Layer.STATIC_OBJECTS);
        }
    }


}
