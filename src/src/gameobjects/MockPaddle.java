package src.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class MockPaddle extends UserPaddle{
    int ColliderCounter = 0;
    GameObjectCollection gameObjectCollection;
    private int maxCollisions;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner       -Position of the object, in window coordinates (pixels). Note that (0,0)
     *                            is the top-left corner of the window.
     *                            dimensions - Width and height in window coordinates.
     * @param dimensions          - Width and height in window coordinates.
     * @param renderable          -  The renderable representing the object. Can be null, in which case
     *                            minDistanceFromEdge - border for paddle movement
     * @param inputListener       Contains a single method: isKeyPressed,
     *                            which returns whether a given key is currently pressed by the user or not.
     * @param windowDimensions    -dimensions in pixels. can be null to indicate a
     *                            full-screen window whose size in pixels is the main screen's resolution
     * @param minDistanceFromEdge - number of pixels from end og screen, from right and left
     */
    public MockPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                      UserInputListener inputListener, Vector2 windowDimensions, int minDistanceFromEdge,
                      GameObjectCollection gameObjectCollection, int maxCollisions) {
        super(topLeftCorner, dimensions, renderable, inputListener, windowDimensions, minDistanceFromEdge);
        this.gameObjectCollection = gameObjectCollection;
        this.maxCollisions = maxCollisions;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        ColliderCounter++;
        if(ColliderCounter==maxCollisions){
            gameObjectCollection.removeGameObject(this, Layer.STATIC_OBJECTS);
        }

    }
}
