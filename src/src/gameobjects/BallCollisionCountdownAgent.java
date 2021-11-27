package src.gameobjects;

import danogl.GameObject;
import danogl.util.Vector2;
import src.brick_strategies.ChangeCameraStrategy;

public class BallCollisionCountdownAgent extends GameObject {
    public static final int BALL_COLLISIONS_UNTIL_TURNOFF = 4;
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

    /**
     * checks if ball was collied more than 4 times and stops camera change.
     * @param deltaTime - The time elapsed, in seconds, since the last frame.
     */
    public void update(float deltaTime){
        super.update(deltaTime);
        if(ball.getCollisionCount()>=countDownValue+ BALL_COLLISIONS_UNTIL_TURNOFF){
            owner.turnOffCameraChange();
        }
    }

}
