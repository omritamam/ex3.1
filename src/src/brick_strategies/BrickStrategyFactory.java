package src.brick_strategies;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;
import src.BrickerGameManager;

public class BrickStrategyFactory {
    private GameObjectCollection gameObjectCollection;

    public BrickStrategyFactory(GameObjectCollection gameObjectCollection, BrickerGameManager gameManager,
                                ImageReader imageReader, SoundReader soundReader,
                                UserInputListener inputListener,
                                WindowController windowController, Vector2 windowDimensions){
        this.gameObjectCollection = gameObjectCollection;
    }



    public CollisionStrategy getStrategy(Strategy strategy) {
        switch (strategy){
            case RemoveBrick:
                return new RemoveBrickStrategy(gameObjectCollection);
            case Puck:
                return new PuckStrategy();
            case SplitTo3Balls:
                return new RemoveBrickStrategyDecorator(new RemoveBrickStrategy(gameObjectCollection));
        }
        return new RemoveBrickStrategy(gameObjectCollection);
    }
}
