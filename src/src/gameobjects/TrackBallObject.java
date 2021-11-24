package src.gameobjects;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Vector2;

public class TrackBallObject extends GameObject {
    private final GameObject ball;
    private final GameObjectCollection gameObjectCollection;
    private Vector2 CurrentVelocity;
    private int Counter = 0;
    private final GameManager gameManager;

    /**
     * Construct a new GameObject instance.
     *
     * @param ball -
     */
    public TrackBallObject(GameObject ball, GameObjectCollection gameObjectCollection, GameManager gameManager) {
        super(Vector2.ZERO, Vector2.ZERO, null);
        this.ball = ball;
        this.gameObjectCollection = gameObjectCollection;
        this.gameManager = gameManager;
        this.setTag("TrackBallObject");
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 ballVelocity =ball.getVelocity();
        if(!ballVelocity.equals(CurrentVelocity)){
            CurrentVelocity = new Vector2(ball.getVelocity());
            Counter++;
        }
        if(Counter==4 || ball.getDimensions().equals(Vector2.ZERO)){
            gameManager.setCamera(null);
            gameObjectCollection.removeGameObject(this, Layer.STATIC_OBJECTS);
        }
    }
}
