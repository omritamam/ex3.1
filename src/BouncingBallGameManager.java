import brick_strategies.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.*;

public class BouncingBallGameManager extends GameManager {

        public static final int BORDER_WIDTH = 10;
        public static final int BALL_SPEED = 300;
        public static final int PADDLE_MARGIN = 30;
        public static final int PADDLE_WIDTH = 200;
        public static final int PADDLE_HEIGHT = 20;
        public static final int BALL_RADIUS = 50;
        public static final Color WALL_COLOR = Color.CYAN;
        public static final int INITIAL_HEARTS = 3;
        public static final int BRICK_MARGIN=10;
        public static final int BRICK_SPACING=5;
        public static final int ROWS = 8;
        public static final int COLS = 5;
        public static final int COUNTER_SIZE = 50;

        private Ball ball;
        private Vector2 windowDimensions;
        private ImageReader imageReader;
        private SoundReader soundReader;
        private UserInputListener inputListener;
        private WindowController windowController;
        private Counter counter = new Counter(INITIAL_HEARTS);

        /**
         Parameters:
         windowTitle -
         windowDimensions - pixel dimensions for game window height x width
       */
        public BouncingBallGameManager(String windowTitle, Vector2 windowDimensions){
                super(windowTitle, windowDimensions);
        }
        /**   Calling this function should initialize the game window. It should initialize objects in the game window - ball, paddle, walls, life counters, bricks.
        /  This version of the game has 5 rows, 8 columns of bricks.
        / Overrides:
        / initializeGame in class danogl.GameManager
        / Parameters:
        / imageReader - an ImageReader instance for reading images from files for rendering of objects.
        / soundReader - a SoundReader instance for reading soundclips from files for rendering event sounds.
        / inputListener - an InputListener instance for reading user input.
        / windowController - controls visual rendering of the game window and object renderables.
        */
        @Override
        public void initializeGame(ImageReader imageReader,
                                         SoundReader soundReader, UserInputListener inputListener,
                                         WindowController windowController){
                super.initializeGame(imageReader,soundReader, inputListener,windowController);
                this.imageReader = imageReader;
                this.soundReader = soundReader;
                this.inputListener = inputListener;
                this.windowController = windowController;
                windowDimensions = windowController.getWindowDimensions();
                windowController.setTargetFramerate(200);


                createBall();
                Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);
                createUserPaddle(paddleImage);
                //createAIPaddle(paddleImage);
                createWalls();
                createBackground();
                createBricks(ROWS, COLS);
                createNumericLifeCounter();


        }

        private void createNumericLifeCounter() {
                GameObject brick = new NumericLifeCounter(
                        counter,
                        new Vector2(BORDER_WIDTH,0),
                        new Vector2(COUNTER_SIZE, COUNTER_SIZE),
                        gameObjects());
                gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
        }

        private void createBricks(int rows, int cols) {
                Renderable brickImage = imageReader.readImage("assets/brick.png", true);
                float brick_wight = (windowDimensions.x()-(cols-1)*BRICK_SPACING-2*BRICK_MARGIN)/cols;
                for(int row=0; row<rows;row++){
                        for(int col =0; col<cols;col++){
                                GameObject brick = new Brick(
                                        new Vector2(BRICK_MARGIN+col*(brick_wight+BRICK_SPACING),
                                                row*(15+BRICK_SPACING)),
                                        new Vector2(brick_wight,15),
                                        brickImage,
                                        new CollisionStrategy(gameObjects()),
                                       counter);
                                gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
                        }
                }


        }

        private void createBackground() {
                GameObject background = new GameObject(
                        Vector2.ZERO,
                        windowController.getWindowDimensions(),
                        imageReader.readImage("assets/DARK_BG2_small.jpeg", false));
                background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
                gameObjects().addGameObject(background, Layer.BACKGROUND);
        }

        private void createWalls() {
                Vector2[] topLeftCorners=new Vector2[]{Vector2.ZERO,
                        new Vector2(windowDimensions.x()-BORDER_WIDTH,0)};
                for(Vector2 topLeftCorner : topLeftCorners)
                {
                        gameObjects().addGameObject(
                                new GameObject(
                                        topLeftCorner,
                                        new Vector2(BORDER_WIDTH, windowDimensions.y()),
                                        new RectangleRenderable(WALL_COLOR))
                        );
                }
        }

        private void createUserPaddle(Renderable paddleImage) {
                Paddle userPaddle = new UserPaddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH,PADDLE_HEIGHT), paddleImage,
                        inputListener,windowDimensions,PADDLE_MARGIN);
                userPaddle.setCenter(new Vector2(windowDimensions.x()/2, windowDimensions.y()-PADDLE_MARGIN));
                gameObjects().addGameObject(userPaddle);
        }

        private void createBall(){
                Renderable ballImage= imageReader.readImage("assets/ball.png", true);
                Sound collisionSound = soundReader.readSound("assets/blop_cut_silenced.wav");
                ball = new Ball(Vector2.ZERO,new Vector2(BALL_RADIUS,BALL_RADIUS),ballImage,collisionSound);
                gameObjects().addGameObject(ball);
                ResetBall();

        }

        private void createAIPaddle(Renderable paddleImage) {
                GameObject aiPaddle = new AIPaddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT), paddleImage,
                        inputListener,windowDimensions,PADDLE_MARGIN,ball);
                aiPaddle.setCenter(new Vector2(windowDimensions.x()/2, PADDLE_MARGIN));
                gameObjects().addGameObject(aiPaddle);
        }

        /**
        Code in this function is run every frame update.
        Overrides:
        update in class danogl.GameManager
        Parameters:
        deltaTime - time between updates. For internal use by game engine. You do not need to call this method yourself.
         */
        public void update(float deltaTime){
                super.update(deltaTime);
                isGameEnded();
        }

        private void isGameEnded() {
                double ballHeight = ball.getCenter().y();
                if(ballHeight<0){
                        GameEnded("You win!, play again?");
                }
                if(ballHeight > windowDimensions.y()) {
                        counter.decrement();
                        ResetBall();
                }
                if(counter.value()==-1){
                        GameEnded("You lose!, play again?");
                }
        }

        private void ResetBall() {
                ball.setCenter(windowDimensions.mult(0.5F));
                ball.setVelocity(Vector2.DOWN.mult(BALL_SPEED));        }

        private void GameEnded(String prompt) {
                if(windowController.openYesNoDialog(prompt)){
                        windowController.resetGame();
                }
                else {
                        windowController.closeWindow();
                }
        }

        /**
        Entry point for game. Should contain:
        1. An instantiation call to BrickerGameManager constructor.
        2. A call to run() method of instance of BrickerGameManager.
        Should initialize game window of dimensions (x,y) = (700,500).
        Parameters:
        args -
         */
        public static void main(String[] args) {
        new BouncingBallGameManager("Bouncing Ball", new Vector2(1000, 800)).run();
        }
}