package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.gameobjects.Ball;
import src.gameobjects.Puck;

import static src.BrickerGameManager.BALL_IMAGE_PATH;
import static src.BrickerGameManager.BALL_SOUND_PATH;

public class PuckStrategy extends RemoveBrickStrategyDecorator {
    private final ImageReader imageReader;
    private final SoundReader soundReader;

    /**
     * Constructor
     *
     * @param toBeDecorated - Collision strategy object to be decorated.
     */
    public PuckStrategy(CollisionStrategy toBeDecorated,
                        ImageReader imageReader,
                        SoundReader soundReader) {
        super(toBeDecorated);
        this.imageReader = imageReader;
        this.soundReader = soundReader;
    }

    /**
     * @param thisObj - the gameObject that extends the class
     * @param otherObj - other GameObject instance participating in collision.
     * @param counter - global brick counter
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        super.onCollision(thisObj, otherObj, counter);
        Vector2[] directions = new Vector2[]{Vector2.LEFT.add(Vector2.DOWN),
                Vector2.RIGHT.add(Vector2.DOWN)};
        for (Vector2 direction : directions) {
            Ball newBall = new Puck(otherObj.getTopLeftCorner(), otherObj.getDimensions(),
                    imageReader.readImage(BALL_IMAGE_PATH, true), soundReader.readSound(BALL_SOUND_PATH));
            newBall.setCenter(thisObj.getCenter());
            newBall.setVelocity(direction.mult(otherObj.getVelocity().magnitude()));
            getGameObjectCollection().addGameObject(newBall, Layer.DEFAULT);
        }
    }
}
