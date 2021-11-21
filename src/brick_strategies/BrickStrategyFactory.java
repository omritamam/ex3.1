package brick_strategies;

import danogl.collisions.GameObjectCollection;

public class BrickStrategyFactory {
    private GameObjectCollection gameObjectCollection;

    public BrickStrategyFactory(GameObjectCollection gameObjectCollection) {
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
