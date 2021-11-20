import brick_strategies.Ball;
import brick_strategies.Paddle;
import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
public class BouncingBallGameManager extends GameManager {

        public static final int BORDER_WIDTH = 5;
        public static final int BALL_SPEED = 100;
        public static final int PADDLE_MARGIN = 30;
        public static final int PADDLE_WIDTH = 200;
        public static final int PADDLE_HEIGHT = 20;
        public static final int BALL_RADIUS = 50;
        private Ball ball;
        private Vector2 windowDimensions;
        private WindowController windowController;

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
                this.windowController = windowController;
                super.initializeGame(imageReader,soundReader, inputListener,windowController);
                windowController.setTargetFramerate(200);
                //ball
                windowDimensions = windowController.getWindowDimensions();
                createBall(imageReader, soundReader);


                //paddles
                Renderable paddleImage= imageReader.readImage("assets/paddle.png", true);
                //user paddle
                createUserPaddle(inputListener, paddleImage);
                createAIPaddle(paddleImage);

        }

        private void createUserPaddle(UserInputListener inputListener, Renderable paddleImage) {
                Paddle userPaddle = new Paddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH,PADDLE_HEIGHT), paddleImage, inputListener,windowDimensions,PADDLE_MARGIN);
                userPaddle.setCenter(new Vector2(windowDimensions.x()/2, windowDimensions.y()-PADDLE_MARGIN));
                gameObjects().addGameObject(userPaddle);
        }

        private void createBall(ImageReader imageReader, SoundReader soundReader) {
                Renderable ballImage= imageReader.readImage("assets/ball.png", true);
                Sound collisionSound = soundReader.readSound("assets/blop_cut_silenced.wav");
                ball = new Ball(Vector2.ZERO,new Vector2(BALL_RADIUS,BALL_RADIUS),ballImage,collisionSound);
                ball.setCenter(windowDimensions.mult(0.5F));
                ball.setVelocity(Vector2.DOWN.mult(BALL_SPEED));
                gameObjects().addGameObject(ball);
        }

        private void createAIPaddle(Renderable paddleImage) {
                GameObject aiPaddle = new GameObject(Vector2.ZERO, new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT), paddleImage);
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
                String prompt="";
                if(ballHeight<0){
                        prompt=" You win!";
                }
                if(ballHeight> windowDimensions.y()){
                        prompt=" You lost!";

                }
                if(!prompt.isEmpty()){
                        prompt+=" play again?";
                        if(windowController.openYesNoDialog(prompt)){
                                windowController.resetGame();
                        }
                        else {
                                windowController.closeWindow();
                        }
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