package src.brick_strategies;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.WindowController;
import danogl.gui.rendering.Camera;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.gameobjects.TrackBallObject;

public class ChangeCameraStrategy extends RemoveBrickStrategyDecorator {


    private final GameManager gameManager;
    private final WindowController windowController;
    /**
     * Constructor
     *
     * @param toBeDecorated - Collision strategy object to be decorated.
     */
    public ChangeCameraStrategy(CollisionStrategy toBeDecorated, WindowController windowController,
                                GameManager gameManager) {
        super(toBeDecorated);
        this.gameManager = gameManager;
        this.windowController = windowController;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        super.onCollision(thisObj, otherObj, counter);
        if(gameManager.getCamera()==null){
            gameManager.setCamera(
                    new Camera(
                            otherObj,
                            Vector2.ZERO,
                            windowController.getWindowDimensions().mult(1.2f),
                            windowController.getWindowDimensions()
                    )
            );
         getGameObjectCollection().addGameObject(new TrackBallObject(otherObj,getGameObjectCollection(),gameManager),
                 Layer.STATIC_OBJECTS);
        }
    }
    public void turnOffCameraChange(){
        gameManager.setCamera(null);
    }

}
