package brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;
import danogl.util.Vector2;

public class NumericLifeCounter  extends GameObject {
    private final Counter livesCounter;
    private final GameObjectCollection gameObjectCollection;

    /**
    Constructor
    Parameters:
        livesCounter - global lives counter of game.
        topLeftCorner - top left corner of renderable
        dimensions - dimensions of renderable
        gameObjectCollection - global game object collection
     */
    public NumericLifeCounter( Counter livesCounter,
                               Vector2 topLeftCorner,
                               Vector2 dimensions,
                               GameObjectCollection gameObjectCollection){
        super(topLeftCorner, dimensions, new CounterRenderer(livesCounter));

        this.livesCounter = livesCounter;
        this.gameObjectCollection = gameObjectCollection;
    }

    /**
    Overrides: update in class danogl.GameObject
    Parameters:
    deltaTime -
     */
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);


    }


}
