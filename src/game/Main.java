package game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        //creating basic window application without any content
        JFrame window = new JFrame();
        //setting close after clicking X button in the top right corner
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting window to be not resizable
        window.setResizable(false);
        //setting title of window to RPG Game
        window.setTitle("RPG Game");

        //creating gamePanel object
        GamePanel gamePanel = new GamePanel();
        //adding gamePanel to the window
        window.add(gamePanel);

        //setting size of window to fit size of gamePanel
        window.pack();

        //setting window in the center of the screen, null means that is
        //position according to the screen
        window.setLocationRelativeTo(null);
        //setting window to show on the screen
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}