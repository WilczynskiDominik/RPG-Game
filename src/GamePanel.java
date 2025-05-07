import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final private int tileSize = 48;  //48px x 48px tile
    final private int maxScreenColumns = 16;
    final private int maxScreenRows = 12;
    final private int screenWidth = tileSize * maxScreenColumns;
    final private int screenHeight = tileSize * maxScreenRows;
    final private int FPS = 60;

    private KeyHandler keyHandler = new KeyHandler();
    private Thread gameThread;

    //PLAYER'S DEFAULT POSITION SETTINGS
    private int playerX = 100;
    private int playerY = 100;
    private float playerSpeed = 4;

    public GamePanel(){

        //setting panel size to the size
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        //setting background color to black
        this.setBackground(Color.BLACK);
        //for better flow of refreshing view
        this.setDoubleBuffered(true);
        //adding key listener to handle key events
        this.addKeyListener(keyHandler);
        //available this class to obtain key events for handling them
        this.setFocusable(true);
    }

    //method that starts game thread
    public void startGameThread(){

        //transfer GamePanel class to the new thread to start method run
        //from this class
        gameThread = new Thread(this);
        //staring method run
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            //every loop is count how much time has been past from the last loop
            //and add divided solution by drawInterval (interval is time between
            //refreshing screen) to delta, when delta hit 1 or more it updates,
            //repaint screen and subtract delta by 1
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                //update information about for example sprites
                update();
                //draw screen with updated information, using repaint method
                //is calls paintComponent method
                repaint();
                delta--;
            }
        }
    }
    private void update(){

        playerMove();
    }
    private void playerMove(){

        if(keyHandler.isOnlyOneKeyPressed()){
            playerMoveVerticallyAndHorizontally();
        }
        if(!keyHandler.isOnlyOneKeyPressed()){
            playerMoveDiagonally();
        }

    }
    private void playerMoveVerticallyAndHorizontally(){

        if(keyHandler.isUpPressed()){
            playerY -= playerSpeed;
        }
        if(keyHandler.isDownPressed()){
            playerY += playerSpeed;
        }
        if(keyHandler.isLeftPressed()){
            playerX -= playerSpeed;
        }
        if(keyHandler.isRightPressed()){
            playerX += playerSpeed;
        }
    }
    private void playerMoveDiagonally(){

        float diagonalSpeed = (float)(playerSpeed - Math.sqrt(2));

        if(keyHandler.isUpPressed() && keyHandler.isLeftPressed()){
            playerY -= diagonalSpeed;
            playerX -= diagonalSpeed;
            System.out.println(diagonalSpeed);
        }
        if(keyHandler.isUpPressed() && keyHandler.isRightPressed()){
            playerY -= diagonalSpeed;
            playerX += diagonalSpeed;
        }
        if(keyHandler.isDownPressed() && keyHandler.isLeftPressed()){
            playerY += diagonalSpeed;
            playerX -= diagonalSpeed;
        }
        if(keyHandler.isDownPressed() && keyHandler.isRightPressed()){
            playerY += diagonalSpeed;
            playerX += diagonalSpeed;
        }
    }
    protected void paintComponent(Graphics graphics){

        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        //temporary player character
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(playerX, playerY, tileSize, tileSize);
        graphics2D.dispose();   //clear memory after draw character
    }
}

