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
        public static final int BALL_SPEED = 450;
        public static final int PADDLE_MARGIN = 30;
        public static final int PADDLE_WIDTH = 200;
        public static final int PADDLE_HEIGHT = 50;
        public static final int HEART_WIDTH = 50;
        public static final int HEART_HEIGHT = 50;
        public static final int BALL_RADIUS = 50;
        public static final Color WALL_COLOR = Color.CYAN;
        public static final int INITIAL_HEARTS = 3;
        public static final int BRICK_MARGIN=10;
        public static final int BRICK_SPACING=5;
        public static final int ROWS = 3; //4
        public static final int COLS = 5; //3
        public static final int COUNTER_SIZE = 50;
        public static final float BRICK_HEIGHT = 20;

        private Ball ball;
        private Vector2 windowDimensions;
        private ImageReader imageReader;
        private SoundReader soundReader;
        private UserInputListener inputListener;
        private WindowController windowController;
        private Counter brickCounter;
        private Counter heartCounter;

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

                initCounters();
                createBall();
                Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);
                createUserPaddle(paddleImage);
                createWalls();
                createBackground();
                createBricks(ROWS, COLS);
                createNumericLifeCounter();
                createGraphicLifeCounter();
        }

        private void initCounters() {
                brickCounter = new Counter(ROWS*COLS);
                heartCounter = new Counter(INITIAL_HEARTS);
        }

        private void createGraphicLifeCounter() {
                Renderable heartImage = imageReader.readImage("assets/heart.png", true);

                GameObject GraphicLifeCounter = new GraphicLifeCounter(
                        new Vector2(windowDimensions.x()-INITIAL_HEARTS*HEART_WIDTH-BORDER_WIDTH,0),
                        new Vector2(HEART_WIDTH, HEART_HEIGHT),
                        heartCounter,
                        heartImage,
                        gameObjects(),
                        INITIAL_HEARTS);
                gameObjects().addGameObject(GraphicLifeCounter, Layer.FOREGROUND);
        }

        private void createNumericLifeCounter() {
                GameObject NumericLifeCounter = new NumericLifeCounter(
                        heartCounter,
                        new Vector2(BORDER_WIDTH,0),
                        new Vector2(COUNTER_SIZE, COUNTER_SIZE),
                        gameObjects());
                gameObjects().addGameObject(NumericLifeCounter, Layer.STATIC_OBJECTS);
        }

        private void createBricks(int rows, int cols) {
                Renderable brickImage = imageReader.readImage("assets/brick.png", true);
                float brick_wight = (windowDimensions.x()-(cols-1)*BRICK_SPACING-2*BRICK_MARGIN)/cols;
                for(int row=0; row<rows;row++){
                        for(int col =0; col<cols;col++){
                                GameObject brick = new Brick(
                                        new Vector2(BRICK_MARGIN+col*(brick_wight+BRICK_SPACING),
                                                row*(BRICK_HEIGHT +BRICK_SPACING)+HEART_HEIGHT+BORDER_WIDTH),
                                        new Vector2(brick_wight, BRICK_HEIGHT),
                                        brickImage,
                                        new CollisionStrategy(gameObjects()),
                                        brickCounter);
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
                gameObjects().addGameObject(
                        new GameObject(
                                new Vector2(0,Math.max(HEART_HEIGHT,COUNTER_SIZE)),
                                new Vector2(windowDimensions.x(),BORDER_WIDTH),
                                new RectangleRenderable(WALL_COLOR))
                );
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
         * Code in this function is run every frame update.
         * Overrides:
         * update in class danogl.GameManager
         *
         * @param deltaTime The time, in seconds, that passed since the last invocation
         *                  of this method (i.e., since the last frame). This is useful
         *                  for either accumulating the total time that passed since some
         *                  event, or for physics integration (i.e., multiply this by
         *                  the acceleration to get an estimate of the added velocity or
         */
        public void update(float deltaTime){
                super.update(deltaTime);
                isGameEnded();
        }

        /**
         * checks if the player wins/loses and invokes corresponding methods
         */
        private void isGameEnded() {
                double ballHeight = ball.getCenter().y();
                if(brickCounter.value()==0){
                        GameEnded("You win!, play again?");
                }
                else if(ballHeight > windowDimensions.y()) {
                        heartCounter.decrement();
                        ResetBall();
                }
                if(heartCounter.value()==-1){
                        GameEnded("You lose! play again?");
                }
        }

        /**
         * locates the ball in the center with velocity down.
         */
        private void ResetBall() {
                ball.setCenter(windowDimensions.mult(0.5F));
                ball.setVelocity(Vector2.DOWN.mult(BALL_SPEED));
        }

        /**
         *
         * @param message - a message to present
         */
        private void GameEnded(String message) {
                if(windowController.openYesNoDialog(message)){
                        windowController.resetGame();
                }
                else {
                        windowController.closeWindow();
                }
        }

        /**
         *      Entry point for game. Should contain:
         *      1. An instantiation call to BrickerGameManager constructor.
         *      2. A call to run() method of instance of BrickerGameManager.
         *      Should initialize game window of dimensions (x,y) = (700,500).
         *
         * @param args - NONE
         */
        public static void main(String[] args) {
        new BouncingBallGameManager("Bouncing Ball", new Vector2(1000, 800)).run();
        }
}