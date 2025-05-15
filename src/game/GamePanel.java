package game;

import game.entity.Player;
import game.tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final private int TILE_SIZE = 16;
    final private int SCALE = 3;

    //SCREEN SETTINGS
    final private int TILE_SIZE_AFTER_SCALE = TILE_SIZE * SCALE;  //48px x 48px tile
    final private int MAX_SCREEN_COLUMNS = 20;
    final private int MAX_SCREEN_ROWS = 12;
    final private int SCREEN_WIDTH = TILE_SIZE_AFTER_SCALE * MAX_SCREEN_COLUMNS;
    final private int SCREEN_HEIGHT = TILE_SIZE_AFTER_SCALE * MAX_SCREEN_ROWS;
    final private int FPS = 60;

    final private KeyHandler KEY_HANDLER = new KeyHandler();
    private Thread gameThread;
    private Player player = new Player(this, KEY_HANDLER);
    private TileManager map1 = new TileManager(this, "resource/maps/tempMap1.txt");

    public GamePanel(){

        //setting panel size to the size
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        //setting background color to black
        this.setBackground(Color.BLACK);
        //for better flow of refreshing view
        this.setDoubleBuffered(true);
        //adding key listener to handle key events
        this.addKeyListener(KEY_HANDLER);
        //available this class to obtain key events for handling them
        this.setFocusable(true);
    }

    //method that starts game thread
    public void startGameThread(){

        //transfer Game.GamePanel class to the new thread to start method run
        //from this class
        gameThread = new Thread(this);
        //staring method run
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 /FPS;
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


        player.update();
    }
    protected void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        map1.draw(graphics2D);
        player.draw(graphics2D);
        //graphics2D.fillRect(playerX, playerY, tileSize, tileSize);
        graphics2D.dispose();   //clear memory after draw character
    }

    public int getTileSize(){
        return TILE_SIZE_AFTER_SCALE;
    }
    public int getScreenColumns() {
        return MAX_SCREEN_COLUMNS;
    }
    public int getScreenRows() {
        return MAX_SCREEN_ROWS;
    }
}

