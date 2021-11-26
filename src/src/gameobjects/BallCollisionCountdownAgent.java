package src.gameobjects;

import danogl.GameObject;
import danogl.util.Vector2;
import src.brick_strategies.ChangeCameraStrategy;

public class BallCollisionCountdownAgent extends GameObject {
    private final Ball ball;
    private final ChangeCameraStrategy owner;
    private final int countDownValue;

    /**
     *
     * @param ball - Ball object whose collisions are to be counted.
     * @param owner - Object asking for countdown notification.
     * @param countDownValue - Number of ball collisions. Notify caller object that the ball collided countDownValue times since instantiation.
     */
    public BallCollisionCountdownAgent(Ball ball,
                                        ChangeCameraStrategy owner,
                                        int countDownValue){
        super(Vector2.ZERO,Vector2.ZERO, null);
        this.ball = ball;
        this.owner = owner;
        this.countDownValue = countDownValue;
    }
    public void update(float deltaTime){
        super.update(deltaTime);
        if(ball.getCollisionCount()>=countDownValue+4){
            owner.turnOffCameraChange();
        }
    }

}
