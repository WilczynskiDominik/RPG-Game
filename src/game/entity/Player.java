package game.entity;

import game.GamePanel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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
            standFront = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerStandFront.png")));
            walkDown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerWalkDown1.png")));
            walkDown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerWalkDown2.png")));
            standBack = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerStandBack.png")));
            walkUp1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerWalkUp1.png")));
            walkUp2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerWalkUp2.png")));
            standLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerStandLeft.png")));
            walkLeft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerWalkLeft1.png")));
            walkLeft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerWalkLeft2.png")));
            walkLeft3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerWalkLeft3.png")));
            walkLeft4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerWalkLeft4.png")));
            standRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerStandRight.png")));

        }catch(IOException e){
            //e.printStackTrace();
        }
    }
    //UPDATE
    public void update(){

        playerMove();
    }
    private void playerMove(){

        if(keyHandler.isKeyPressed()){

            if(keyHandler.isOnlyOneKeyPressed()){
                playerMoveVerticallyAndHorizontally();
            }
            if(!keyHandler.isOnlyOneKeyPressed()){
                playerMoveDiagonally();
            }
            spriteCounter++;
            if(spriteCounter > 11){
                if(!isSideWalking){
                    verticalMove();
                }
                else if(isSideWalking){
                    horizontalMove();
                }
                spriteCounter = 0;
            }
        }
    }
    private void playerMoveVerticallyAndHorizontally(){

        if(keyHandler.isUpPressed()){
            direction = "up";
            y -= speed;
            isSideWalking = false;
        }
        if(keyHandler.isDownPressed()){
            direction = "down";
            y += speed;
            isSideWalking = false;
        }
        if(keyHandler.isLeftPressed()){
            direction = "left";
            x -= speed;
            isSideWalking = true;
        }
        if(keyHandler.isRightPressed()){
            direction = "right";
            x += speed;
            isSideWalking = true;
        }
    }
    private void playerMoveDiagonally(){

        float diagonalSpeed = (float)(speed * 0.707);

        if(keyHandler.isUpPressed() && keyHandler.isLeftPressed()){
            direction = "up";
            y -= diagonalSpeed;
            x -= diagonalSpeed;
        }
        if(keyHandler.isUpPressed() && keyHandler.isRightPressed()){
            direction = "up";
            y -= diagonalSpeed;
            x += diagonalSpeed;
        }
        if(keyHandler.isDownPressed() && keyHandler.isLeftPressed()){
            direction = "down";
            y += diagonalSpeed;
            x -= diagonalSpeed;
        }
        if(keyHandler.isDownPressed() && keyHandler.isRightPressed()){
            direction = "down";
            y += diagonalSpeed;
            x += diagonalSpeed;
        }
    }
    private void verticalMove(){
        if(moveSprite == 1){
            moveSprite = 2;
        }
        else if(moveSprite == 2){
            moveSprite = 1;
        }
    }
    private void horizontalMove(){
        switch (moveSprite){
            case 1 -> moveSprite = 2;
            case 2 -> moveSprite = 3;
            case 3 -> moveSprite = 4;
            case 4 -> moveSprite = 5;
            case 5 -> moveSprite = 1;
        }
    }
    //DRAW
    public void draw(Graphics2D graphics2D){

        BufferedImage image = null;

        switch (direction){
            case "up" -> image = walkAnimationSetter(image, standBack, walkUp1, walkUp2);
            case "down" -> image = walkAnimationSetter(image, standFront, walkDown1, walkDown2);
            case "left" -> image = walkAnimationSetter(image, standLeft, walkLeft1, walkLeft2, walkLeft3, walkLeft4);
            case "right" -> image = standRight;
        }

        int playerWidth = gamePanel.getTileSize() * 2;
        int playerHeight = gamePanel.getTileSize() * 3;

        graphics2D.drawImage(image, (int)x, (int)y, playerWidth, playerHeight, null);
    }
    private BufferedImage walkAnimationSetter(BufferedImage image, BufferedImage standing, BufferedImage animation1, BufferedImage animation2, BufferedImage animation3, BufferedImage animation4){

        if(!keyHandler.isKeyPressed()){
            image = standing;
            return image;
        }
        switch (moveSprite){
            case 1 -> image = animation1;
            case 2 -> image = animation2;
            case 3 -> image = standing;
            case 4 -> image = animation3;
            case 5 -> image = animation4;
        }
        return image;
    }
    private BufferedImage walkAnimationSetter(BufferedImage image, BufferedImage standing, BufferedImage animation1, BufferedImage animation2){

        if(!keyHandler.isKeyPressed()){
            image = standing;
            return image;
        }
        switch (moveSprite){
            case 1  -> image = animation1;
            case 2 -> image = animation2;
            case 3  -> image = standing;
            case 4 -> image = animation1;
            case 5 -> image = animation2;
        }
        return image;
    }
}
