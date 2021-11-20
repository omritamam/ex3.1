package brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import java.util.ArrayList;

public class GraphicLifeCounter extends GameObject{
    private final Vector2 widgetDimensions;
    private final Counter livesCounter;
    private final Renderable widgetRenderable;
    private final GameObjectCollection gameObjectsCollection;
    private int numOfLives;
    private ArrayList<GameObject> hearts;

    /**
     * Constructor
     * @param widgetTopLeftCorner - top left corner of left most life widgets. Other widgets will be displayed to its right,
     *         aligned in hight.
     * @param widgetDimensions - dimensions of widgets to be displayed.
     * @param livesCounter - a global counter of current lives
     * @param widgetRenderable  - image to use for widgets.
     * @param gameObjectsCollection - global game object collection managed by game manager.
     * @param numOfLives  - global setting of number of lives a player will have in a game
     */
    public GraphicLifeCounter(Vector2 widgetTopLeftCorner,
                               Vector2 widgetDimensions,
                               Counter livesCounter,
                               Renderable widgetRenderable,
                               GameObjectCollection gameObjectsCollection,
                               int numOfLives){
        super(widgetTopLeftCorner,Vector2.ZERO,null);
        this.widgetDimensions = widgetDimensions;
        this.livesCounter = livesCounter;
        this.widgetRenderable = widgetRenderable;
        this.gameObjectsCollection = gameObjectsCollection;
        this.numOfLives = numOfLives;
        initHearts(widgetTopLeftCorner, widgetDimensions);
    }

    /**
     * creates a row of hearts
     * @param widgetTopLeftCorner - top left corner of left most life widgets. Other widgets will be displayed to its right,
     *         aligned in hight.
     * @param widgetDimensions - dimensions of widgets to be displayed.
     */
    private void initHearts(Vector2 widgetTopLeftCorner, Vector2 widgetDimensions) {
        hearts = new ArrayList(numOfLives);
        for(int heartNum = 0; heartNum< numOfLives; heartNum++){
            hearts.add(initHeart(new Vector2(widgetTopLeftCorner.x()
                    + widgetDimensions.x()*heartNum, widgetTopLeftCorner.y())));
        }
    }

    /**
     * create a new heart in a given point
     * @param heartTopLeftCorner - top left corner of left most life widgets. Other widgets will be displayed to its right,
     *         aligned in hight.
     * @return the new heart
     */
    private GameObject initHeart(Vector2 heartTopLeftCorner) {
        GameObject heart = new GameObject(
                heartTopLeftCorner, widgetDimensions, widgetRenderable);
        gameObjectsCollection.addGameObject(heart, Layer.FOREGROUND);
        return heart;
    }



    /**
     * Overrides: update in class danogl.GameObject
     * @param deltaTime The time elapsed, in seconds, since the last frame. Can
     *                  be used to determine a new position/velocity by multiplying
     *                  this delta with the velocity/acceleration respectively
     *                  and adding to the position/velocity:
     *                  velocity += deltaTime*acceleration
     */
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        if(livesCounter.value()==-1){
            gameObjectsCollection.removeGameObject(this);
            return;
        }
        if(numOfLives > livesCounter.value()){
            numOfLives--;
            gameObjectsCollection.removeGameObject(hearts.get(numOfLives), Layer.FOREGROUND);
        }
    }}
