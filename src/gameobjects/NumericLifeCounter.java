package gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;
import danogl.util.Vector2;

public class NumericLifeCounter  extends GameObject {

    /**
     *     Constructor
     * @param livesCounter - global lives counter of game.
     * @param topLeftCorner - top left corner of renderable
     * @param dimensions  - dimensions of renderable
     * @param gameObjectCollection - global game object collection
     */
    public NumericLifeCounter( Counter livesCounter,
                               Vector2 topLeftCorner,
                               Vector2 dimensions,
                               GameObjectCollection gameObjectCollection){
        super(topLeftCorner, dimensions, new CounterRenderer(livesCounter));
        super.setTag("NumericLifeCounter");

    }

    /**
     * method to run once a frame
     * @param deltaTime The time elapsed, in seconds, since the last frame. Can
     *                  be used to determine a new position/velocity by multiplying
     *                  this delta with the velocity/acceleration respectively
     *                  and adding to the position/velocity:
     *                  velocity += deltaTime*acceleration
     */
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
    }
}
