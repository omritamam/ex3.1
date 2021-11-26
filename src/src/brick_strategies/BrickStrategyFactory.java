package src.brick_strategies;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;
import src.BrickerGameManager;

public class BrickStrategyFactory {
    private final GameObjectCollection gameObjectCollection;
    private final BrickerGameManager gameManager;
    private final ImageReader imageReader;
    private final SoundReader soundReader;
    private final UserInputListener inputListener;
    private final WindowController windowController;
    private final Vector2 windowDimensions;

    public BrickStrategyFactory(GameObjectCollection gameObjectCollection, BrickerGameManager gameManager,
                                ImageReader imageReader, SoundReader soundReader,
                                UserInputListener inputListener,
                                WindowController windowController, Vector2 windowDimensions){
        this.gameObjectCollection = gameObjectCollection;
        this.gameManager = gameManager;
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.inputListener = inputListener;
        this.windowController = windowController;
        this.windowDimensions = windowDimensions;
    }



    public CollisionStrategy getStrategy(Strategy strategy) {
        CollisionStrategy removeBrickStrategy = new RemoveBrickStrategy(gameObjectCollection);

        switch (strategy){
            case RemoveBrick:
                return removeBrickStrategy;
            case SplitTo3Balls:
            case Puck:
                return new PuckStrategy(removeBrickStrategy,imageReader, soundReader);
            case AddPaddle:
                return new AddPaddleStrategy(removeBrickStrategy,imageReader,inputListener, windowDimensions);
            case ChangeCamera:
                return new ChangeCameraStrategy(removeBrickStrategy,windowController, gameManager);
        }
        return new PuckStrategy(removeBrickStrategy,imageReader, soundReader);

    }
}
