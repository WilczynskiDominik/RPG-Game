package game.entity;

import game.GamePanel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }
    private void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    private void getPlayerImage(){

        try{

            standFront = ImageIO.read(getClass().getResourceAsStream("/player/temp.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //UPDATE
    public void update(){

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
            //direction = "up";
            y -= speed;
        }
        if(keyHandler.isDownPressed()){
            //direction = "down";
            y += speed;
        }
        if(keyHandler.isLeftPressed()){
            //direction = "left";
            x -= speed;
        }
        if(keyHandler.isRightPressed()){
            //direction = "right";
            x += speed;
        }
    }
    private void playerMoveDiagonally(){

        float diagonalSpeed = (float)(speed * 0.707);

        if(keyHandler.isUpPressed() && keyHandler.isLeftPressed()){
            //direction = "up";
            y -= diagonalSpeed;
            x -= diagonalSpeed;
        }
        if(keyHandler.isUpPressed() && keyHandler.isRightPressed()){
            //direction = "up";
            y -= diagonalSpeed;
            x += diagonalSpeed;
        }
        if(keyHandler.isDownPressed() && keyHandler.isLeftPressed()){
            //direction = "down";
            y += diagonalSpeed;
            x -= diagonalSpeed;
        }
        if(keyHandler.isDownPressed() && keyHandler.isRightPressed()){
            //direction = "down";
            y += diagonalSpeed;
            x += diagonalSpeed;
        }
    }
    //DRAW
    public void draw(Graphics2D graphics2D){

        BufferedImage image = null;

        switch (direction){
            case "up" -> image = standBack;
            case "down" -> image = standFront;
            case "left" -> image = standLeft;
            case "right" -> image = standRight;
        }

        graphics2D.drawImage(image, (int)x, (int)y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}
