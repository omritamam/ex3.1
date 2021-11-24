package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.gameobjects.MockPaddle;
import src.gameobjects.Paddle;
import src.gameobjects.UserPaddle;

import java.util.Random;

import static src.BrickerGameManager.*;

public class AddPaddleStrategy extends RemoveBrickStrategyDecorator {

    public static final float DISTANCE_FROM_PADDLE_FACTOR = 0.8f;
    public static int NUM_COLLISIONS_FOR_MOCK_PADDLE_DISAPPEARANCE = 3;
    private ImageReader imageReader;
    private UserInputListener inputListener;
    private Vector2 windowDimensions;

    /**
     * Constructor
     *
     * @param toBeDecorated - Collision strategy object to be decorated.
     */

    public AddPaddleStrategy(CollisionStrategy toBeDecorated,
                              ImageReader imageReader,
                              UserInputListener inputListener,
                              Vector2 windowDimensions){
        super(toBeDecorated);

        this.imageReader = imageReader;
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        super.onCollision(thisObj,otherObj,counter);
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);

        Paddle mockPaddle = new MockPaddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH,PADDLE_HEIGHT), paddleImage,
                inputListener,windowDimensions,PADDLE_MARGIN,getGameObjectCollection(),NUM_COLLISIONS_FOR_MOCK_PADDLE_DISAPPEARANCE );
        mockPaddle.setCenter(new Vector2((float) (Math.random() * (windowDimensions.x()-BORDER_WIDTH-PADDLE_WIDTH)), windowDimensions.y()* DISTANCE_FROM_PADDLE_FACTOR -PADDLE_MARGIN));
        getGameObjectCollection().addGameObject(mockPaddle, Layer.STATIC_OBJECTS);

    }

    @Override
    public GameObjectCollection getGameObjectCollection() {
        return super.getGameObjectCollection();
    }

}
