package brick_strategies;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;

public class BrickStrategyFactory {
    private GameObjectCollection gameObjectCollection;

    public BrickStrategyFactory(GameObjectCollection gameObjectCollection, //BrickerGameManager gameManager,
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
        }
        return new RemoveBrickStrategy(gameObjectCollection);
    }
}
