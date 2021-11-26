package src.gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.WindowController;
import danogl.util.Counter;
import src.brick_strategies.RemoveBrickStrategy;

public class BotStrategy extends RemoveBrickStrategy {
    private final boolean isGood;
    private final WindowController windowController;

    public BotStrategy(boolean isGood, WindowController windowController, GameObjectCollection gameObjectCollection) {
        super(gameObjectCollection);
        this.isGood = isGood;
        this.windowController = windowController;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        super.onCollision(thisObj, otherObj, counter);
       windowController.setTimeScale(isGood? 0.9f: 1.1f);
    }

}
