package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.WindowController;
import danogl.util.Counter;

import static src.brick_strategies.SpeedChaneStrategy.FAST_SPEED;
import static src.brick_strategies.SpeedChaneStrategy.SLOW_SPEED;

public class BotStrategy extends RemoveBrickStrategy {
    private final boolean isGood;
    private final WindowController windowController;

    /**
     * Constructor
     * @param isGood - ture for green bot, false for fast
     * @param windowController - global windowController
     * @param gameObjectCollection - global windowController
     */
    public BotStrategy(boolean isGood, WindowController windowController, GameObjectCollection gameObjectCollection) {
        super(gameObjectCollection);
        this.isGood = isGood;
        this.windowController = windowController;
    }

    /**
     * change game speed of collision.
     * @param thisObj - the gameObject that extends the class
     * @param otherObj - other GameObject instance participating in collision.
     * @param counter - global brick counter
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        super.onCollision(thisObj, otherObj, counter);
       windowController.setTimeScale(isGood? SLOW_SPEED: FAST_SPEED);
    }

}
