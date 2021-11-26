package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.gameobjects.BotStrategy;
import src.gameobjects.StatusDefiner;

public class SpeedChaneStrategy extends RemoveBrickStrategyDecorator{
    private static final String GOOD_CLOCK_PATH = "assets/botGood.png";
    private static final String BAD_CLOCK_PATH = "assets/botBad.png";
    private static final Vector2 BOT_DIMENSIONS = new Vector2(50,50);
    private WindowController windowController;
    private ImageReader imageReader;

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

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        super.onCollision(thisObj, otherObj, counter);
        boolean isGood = windowController.getTimeScale() != 0.9f;
        Renderable renderer =  imageReader.readImage(isGood? GOOD_CLOCK_PATH: BAD_CLOCK_PATH, true);

        getGameObjectCollection().addGameObject(
                new StatusDefiner(thisObj.getTopLeftCorner(),BOT_DIMENSIONS,renderer, new BotStrategy(isGood,
                        windowController, getGameObjectCollection()), new Counter(), thisObj));

    }
}
