import danogl.GameManager;
import danogl.util.Vector2;
public class BrickerBallGameManager extends GameManager {

        public static final int BORDER_WIDTH = 5;
        /*
     Parameters:
      windowTitle -
      windowDimensions - pixel dimensions for game window height x width
      */
        public BrickerBallGameManager(String windowTitle, Vector2 windowDimensions){
        }
        /*       Calling this function should initialize the game window. It should initialize objects in the game window - ball, paddle, walls, life counters, bricks.
/        This version of the game has 5 rows, 8 columns of bricks.
/                Overrides:
/        initializeGame in class danogl.GameManager
/        Parameters:
/        imageReader - an ImageReader instance for reading images from files for rendering of objects.
/        soundReader - a SoundReader instance for reading soundclips from files for rendering event sounds.
/        inputListener - an InputListener instance for reading user input.
/         windowController - controls visual rendering of the game window and object renderables.
 */
        public void initializeGame(danogl.gui.ImageReader imageReader,
                                   danogl.gui.SoundReader soundReader,
                                   danogl.gui.UserInputListener inputListener,
                                   danogl.gui.WindowController windowController){

        }

        /*
        Code in this function is run every frame update.
        Overrides:
        update in class danogl.GameManager
        Parameters:
        deltaTime - time between updates. For internal use by game engine. You do not need to call this method yourself.
         */
        public void update(float deltaTime){

        }

/*
Entry point for game. Should contain:
1. An instantiation call to BrickerGameManager constructor.
2. A call to run() method of instance of BrickerGameManager.
Should initialize game window of dimensions (x,y) = (700,500).
Parameters:
args -
 */
public static void main(String[] args) {
        new GameManager("Bricker", new Vector2(700, 500)).run();
        }
}