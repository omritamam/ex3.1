package src.brick_strategies;

import danogl.GameObject;
import danogl.gui.ImageReader;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.gameobjects.StatusDefiner;

public class SpeedChaneStrategy extends RemoveBrickStrategyDecorator{
    private static final String GOOD_CLOCK_PATH = "assets/botGood.png";
    private static final String BAD_CLOCK_PATH = "assets/botBad.png";
    private static final Vector2 BOT_DIMENSIONS = new Vector2(50,50);
    public static final float SLOW_SPEED = 0.9f;
    public static final float FAST_SPEED = 1.1f;
    private final WindowController windowController;
    private final ImageReader imageReader;

    /**
    /**
     * Constructor
     *
     * @param toBeDecorated - Collision strategy object to be decorated.
     */

    public SpeedChaneStrategy(CollisionStrategy toBeDecorated, WindowController windowController, ImageReader imageReader) {
        super(toBeDecorated);
        this.windowController = windowController;
        this.imageReader = imageReader;
    }

    /**
     *
     * @param thisObj - the gameObject that extends the class
     * @param otherObj - other GameObject instance participating in collision.
     * @param counter - global brick counter
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        super.onCollision(thisObj, otherObj, counter);
        boolean isGood = windowController.getTimeScale() != SLOW_SPEED;
        Renderable renderer =  imageReader.readImage(isGood? GOOD_CLOCK_PATH: BAD_CLOCK_PATH, true);

        getGameObjectCollection().addGameObject(
                new StatusDefiner(thisObj.getTopLeftCorner(),BOT_DIMENSIONS,renderer, new BotStrategy(isGood,
                        windowController, getGameObjectCollection()), thisObj));

    }
}
