package src.brick_strategies;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.BrickerGameManager;

import java.util.Random;

public class BrickStrategyFactory {
    private static final int MAX_STRATEGIES = 3;
    private final GameObjectCollection gameObjectCollection;
    private final BrickerGameManager gameManager;
    private final ImageReader imageReader;
    private final SoundReader soundReader;
    private final UserInputListener inputListener;
    private final WindowController windowController;
    private final Vector2 windowDimensions;
    private final Random random = new Random();

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

    private Strategy randomStrategy() {
        int pick = random.nextInt(Strategy.values().length);
        return Strategy.values()[pick];
    }

    /**
     * method randomly selects between 5 strategies and returns one CollisionStrategy
     * object which is a RemoveBrickStrategy decorated by
     * one of the decorator strategies,
     * or decorated by two randomly selected strategies,
     * or decorated by one of the decorator strategies and a pair of additional two decorator strategies.
     *
     * @return CollisionStrategy object.
     */
    public CollisionStrategy getStrategy() {
        Strategy strategy = randomStrategy();
        return matchStrategyToCollisionStrategy(strategy);
    }

    private CollisionStrategy matchStrategyToCollisionStrategy(Strategy strategy) {
        CollisionStrategy removeBrickStrategy = new RemoveBrickStrategy(gameObjectCollection);

        switch (strategy){
            case Puck:
                return new PuckStrategy(removeBrickStrategy,imageReader, soundReader);
            case AddPaddle:
                return new AddPaddleStrategy(removeBrickStrategy,imageReader,inputListener, windowDimensions);
            case ChangeCamera:
                return new ChangeCameraStrategy(removeBrickStrategy,windowController, gameManager);
            case SpeedChane:
                return new SpeedChaneStrategy(removeBrickStrategy,windowController, imageReader);
            case DoubleStrategy:
                return getDoubleStrategy(MAX_STRATEGIES, new Counter(0));
        }
        return removeBrickStrategy;
    }

    private CollisionStrategy getDoubleStrategy(int maxStrategies, Counter currentStrategies){
        CollisionStrategy collisionStrategy1;
        CollisionStrategy collisionStrategy2;
        Strategy strategy1 = randomStrategy();
        if (strategy1 != Strategy.DoubleStrategy || maxStrategies < 2) {
            while (strategy1==Strategy.DoubleStrategy){
                strategy1 = randomStrategy();
            }
            collisionStrategy1 = matchStrategyToCollisionStrategy(strategy1);
            currentStrategies.increment();
        } else {
            collisionStrategy1 = getDoubleStrategy(maxStrategies-currentStrategies.value()-1,currentStrategies);
        }

        Strategy strategy2 = randomStrategy();
        if (strategy2 != Strategy.DoubleStrategy || currentStrategies.value() < 2) {
            while (strategy2==Strategy.DoubleStrategy){
                strategy2 = randomStrategy();
            }
            collisionStrategy2 = matchStrategyToCollisionStrategy(strategy2);
            currentStrategies.increment();
        } else {
            collisionStrategy2 = getDoubleStrategy(maxStrategies - currentStrategies.value(),currentStrategies);
        }
        return new DoubleStrategy(collisionStrategy1, collisionStrategy2);
    }
}
