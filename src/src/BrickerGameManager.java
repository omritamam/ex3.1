package src;
import src.brick_strategies.BrickStrategyFactory;
import src.brick_strategies.Strategy;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.gameobjects.*;

import java.awt.*;
import java.util.Iterator;

public class BrickerGameManager extends GameManager {

        public static final Color BORDER_COLOR = Color.CYAN;
        public static final float BORDER_WIDTH = 10;

        public static final int BALL_SPEED = 600;
        public static final int BALL_RADIUS = 50;

        public static final int PADDLE_MARGIN = 30;
        public static final int PADDLE_WIDTH = 200;
        public static final int PADDLE_HEIGHT = 50;

        public static final int HEART_WIDTH = 50;
        public static final int HEART_HEIGHT = 50;
        public static final int INITIAL_HEARTS = 4;

        public static final int BRICK_MARGIN = 10;
        public static final int BRICK_SPACING = 5;
        public static final float BRICK_HEIGHT = 20;
        public static final int ROWS = 5; //4
        public static final int COLS = 8; //3

        public static final int NUMERIC_COUNTER_SIZE = 50;
        public static final int STATUS_DEFINER_SPEED = BALL_SPEED;
        public static final String BALL_SOUND_PATH = "assets/blop_cut_silenced.wav";
        public static final String BALL_IMAGE_PATH = "assets/ball.png";
        public static final String HEART_IMAGE_PATH = "assets/heart.png";
        public static final String BRICK_IMAGE_PATH = "assets/brick.png";
        public static final String BACKGROUND_IMAGE_PATH = "assets/DARK_BG2_small.jpeg";
        public static final String PADDLE_IMAGE_PATH = "assets/paddle.png";

        private Ball ball;
        private Vector2 windowDimensions;
        private ImageReader imageReader;
        private SoundReader soundReader;
        private UserInputListener inputListener;
        private WindowController windowController;
        private Counter brickCounter;
        private Counter heartCounter;
        private BrickStrategyFactory brickStrategyFactory;

        /**
         *  constructor
         * @param windowTitle - string for window title
         * @param windowDimensions - pixel dimensions for game window height x width
         */
        public BrickerGameManager(String windowTitle, Vector2 windowDimensions){
                super(windowTitle, windowDimensions);
        }

        /**
         * Calling this function should initialize the game window. It should initialize objects in the game window
         * - ball, paddle, walls, life counters, bricks.
         *           This version of the game has 5 rows, 8 columns of bricks.
         * Overrides:  initializeGame in class danogl.GameManager
         * @param imageReader Contains a single method: readImage, which reads an image from disk.
         *                 See its documentation for help.
         * @param soundReader Contains a single method: readSound, which reads a wav file from
         *                    disk. See its documentation for help.
         * @param inputListener Contains a single method: isKeyPressed, which returns whether
         *                      a given key is currently pressed by the user or not. See its
         *                      documentation.
         * @param windowController Contains an array of helpful, self explanatory methods
         *                         concerning the window.
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

                this.brickStrategyFactory = new BrickStrategyFactory(gameObjects(),this,imageReader,
                        soundReader,inputListener,windowController,windowDimensions);
                windowController.setTargetFramerate(100);

                initCounters();
                createBall();
                createUserPaddle();
                createWalls();
                createBackground();
                createBricks(ROWS, COLS);
                createNumericLifeCounter();
                createGraphicLifeCounter();
        }

        /**
         * initializes global counters for lives snd bricks
         */
        private void initCounters() {
                brickCounter = new Counter(ROWS*COLS);
                heartCounter = new Counter(INITIAL_HEARTS);
        }
        /**
         * creates graphic life counter in the top right corner of the screen
         */
        private void createGraphicLifeCounter() {
                Renderable heartImage = imageReader.readImage(HEART_IMAGE_PATH, true);

                GameObject GraphicLifeCounter = new GraphicLifeCounter(
                        new Vector2(windowDimensions.x()-INITIAL_HEARTS*HEART_WIDTH-BORDER_WIDTH,0),
                        new Vector2(HEART_WIDTH, HEART_HEIGHT),
                        heartCounter,
                        heartImage,
                        gameObjects(),
                        INITIAL_HEARTS);
                gameObjects().addGameObject(GraphicLifeCounter, Layer.FOREGROUND);
        }

        /**
         * creates numeric life counter in the top left corner of the screen
         */
        private void createNumericLifeCounter() {
                GameObject NumericLifeCounter = new NumericLifeCounter(
                        heartCounter,
                        new Vector2(BORDER_WIDTH,0),
                        new Vector2(NUMERIC_COUNTER_SIZE, NUMERIC_COUNTER_SIZE),
                        gameObjects());
                gameObjects().addGameObject(NumericLifeCounter, Layer.STATIC_OBJECTS);
        }

        /**
         * creates block of bricks at the top of the screen
         * @param rows - number of rows
         * @param cols - number of columns
         */
        private void createBricks(int rows, int cols) {
                Renderable brickImage = imageReader.readImage(BRICK_IMAGE_PATH, true);
                float brick_wight = (windowDimensions.x()-(cols-1)*BRICK_SPACING-2*BRICK_MARGIN)/cols;
                for(int row=0; row<1;row++){
                        for(int col =0; col<cols;col++){
                                GameObject brick = new Brick(
                                        new Vector2(BRICK_MARGIN + col * (brick_wight + BRICK_SPACING),
                                                row * (BRICK_HEIGHT + BRICK_SPACING) + HEART_HEIGHT + BORDER_WIDTH),
                                        new Vector2(brick_wight, BRICK_HEIGHT),
                                        brickImage,
                                        brickStrategyFactory.getStrategy(),
                                        brickCounter);
                                gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
                        }
                }
        }
        /**
         * creates background
         */
        private void createBackground() {
                GameObject background = new GameObject(
                        Vector2.ZERO,
                        windowController.getWindowDimensions(),
                        imageReader.readImage(BACKGROUND_IMAGE_PATH, false));
                background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
                background.setTag("background");
                gameObjects().addGameObject(background, Layer.BACKGROUND);
        }

        /**
         * creates walls
         */
        private void createWalls() {
                Vector2[] topLeftCorners=new Vector2[]{Vector2.ZERO,
                        new Vector2(windowDimensions.x()-BORDER_WIDTH,0)};
                for(Vector2 topLeftCorner : topLeftCorners)
                {
                        GameObject newObject = new GameObject(
                                        topLeftCorner,
                                        new Vector2(BORDER_WIDTH, windowDimensions.y()),
                                        new RectangleRenderable(BORDER_COLOR));
                        newObject.setTag("Wall");
                        gameObjects().addGameObject(newObject, Layer.STATIC_OBJECTS);
                }
                GameObject newObject = new GameObject(
                                new Vector2(0,Math.max(HEART_HEIGHT, NUMERIC_COUNTER_SIZE)),
                                new Vector2(windowDimensions.x(),BORDER_WIDTH),
                                new RectangleRenderable(BORDER_COLOR));
                newObject.setTag("Wall");
                gameObjects().addGameObject(newObject,Layer.STATIC_OBJECTS);
        }
        /**
         * creates one user paddle
         */
        private void createUserPaddle() {
                Renderable paddleImage = imageReader.readImage(PADDLE_IMAGE_PATH, true);
                Paddle userPaddle = new UserPaddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH,PADDLE_HEIGHT), paddleImage,
                        inputListener,windowDimensions,PADDLE_MARGIN);
                userPaddle.setCenter(new Vector2(windowDimensions.x()/2, windowDimensions.y()-PADDLE_MARGIN));
                gameObjects().addGameObject(userPaddle, Layer.STATIC_OBJECTS);
        }

        private void createBall(){
                Renderable ballImage= imageReader.readImage(BALL_IMAGE_PATH, true);
                Sound collisionSound = soundReader.readSound(BALL_SOUND_PATH);
                ball = new Ball(Vector2.ZERO,new Vector2(BALL_RADIUS,BALL_RADIUS),ballImage,collisionSound);
                gameObjects().addGameObject(ball);
                ball.setCenter(windowDimensions.mult(0.5F));
                ball.setVelocity(Vector2.DOWN.mult(BALL_SPEED));
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
                removeObjectOutsideWindow();
        }

        private void removeObjectOutsideWindow(){
                Iterator<GameObject> i = gameObjects().iterator();
                while (i.hasNext()) {
                        GameObject object = i.next();
                        Vector2 position = object.getTopLeftCorner();
                        if(position.x()<-10 || position.x()>windowDimensions.x() ||
                                position.y() > windowDimensions.y()+10){
                                gameObjects().removeGameObject(object, Layer.DEFAULT);
                                object.setDimensions(Vector2.ZERO);
                        }
                }
        }

        /**
         * checks if the player wins/loses and invokes corresponding methods
         */
        private void isGameEnded() {
                if(brickCounter.value()==0){
                        GameEnded("You win! play again?");
                }
                else if(gameObjects().isLayerEmpty(Layer.DEFAULT)){
                        heartCounter.decrement();
                        createBall();

                }
                if(heartCounter.value()==-1){
                        GameEnded("You lose! play again?");
                }
        }

        /**
         * game ended operation
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
         *  Entry point for game.
         * @param args - NONE
         */
        public static void main(String[] args) {
        new BrickerGameManager("Bouncing Ball",
                new Vector2(1000, 800)).run();
        }
}