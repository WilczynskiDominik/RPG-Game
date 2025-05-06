import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16;    //16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale;  //48x48 tile
    final int maxScreenColumns = 16;
    final int maxScreenRows = 12;
    final int screenWidth = tileSize * maxScreenColumns;
    final int screenHeight = tileSize * maxScreenRows;

    Thread gameThread;

    public GamePanel(){

        //setting panel size to the size
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        //setting background color to black
        this.setBackground(Color.BLACK);
        //for better flow of refreshing view
        this.setDoubleBuffered(true);
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

    }
}
