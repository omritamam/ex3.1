package brick_strategies;

import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

public class GraphicLifeCounter {
    /**
    Constructor
    Parameters:
        widgetTopLeftCorner - top left corner of left most life widgets. Other widgets will be displayed to its right,
        aligned in hight.
        widgetDimensions - dimensions of widgets to be displayed.
        livesCounter - global lives counter of game.
        widgetRenderable - image to use for widgets.
        gameObjectsCollection - global game object collection managed by game manager.
        numOfLives - global setting of number of lives a player will have in a gam
     */
    public GraphicLifeCounter(Vector2 widgetTopLeftCorner,
                               Vector2 widgetDimensions,
                               Counter livesCounter,
                               Renderable widgetRenderable,
                               GameObjectCollection gameObjectsCollection,
                               int numOfLives){}
    /**
    Overrides: update in class danogl.GameObject
    Parameters:
    deltaTime -
     */
    public void update(float deltaTime){}
}
