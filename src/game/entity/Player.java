package game.entity;

import game.GamePanel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Player extends Entity{

    final private GamePanel gamePanel;
    final private KeyHandler keyHandler;
    final private int playerWidth;
    final private int playerHeight;
    final private int screenX;
    final private int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        playerWidth = gamePanel.getTileSize() * 2;
        playerHeight = gamePanel.getTileSize() * 3;
        screenX = gamePanel.getScreenWidth() / 2 - (playerWidth / 2);
        screenY = gamePanel.getScreenHeight() / 2 - (playerHeight / 2);

        setDefaultValues();
        getPlayerImage();
    }
    private void setDefaultValues(){

        x = gamePanel.getTileSize() * 8;
        y = gamePanel.getTileSize() * 7;
        speed = 4;
        direction = "down";
    }
    private void getPlayerImage(){

        initializeWalkAnimationList();

        try{
            //down
            walkDown.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Front1.png"))));
            walkDown.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Front2.png"))));
            walkDown.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Front3.png"))));
            walkDown.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Front4.png"))));
            walkDown.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Front5.png"))));
            walkDown.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Front6.png"))));
            //up
            waldUp.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Back1.png"))));
            waldUp.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Back2.png"))));
            waldUp.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Back3.png"))));
            waldUp.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Back4.png"))));
            waldUp.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Back5.png"))));
            waldUp.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Back6.png"))));
            //left
            walkLeft.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Left1.png"))));
            walkLeft.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Left2.png"))));
            walkLeft.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Left3.png"))));
            walkLeft.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Left4.png"))));
            walkLeft.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Left5.png"))));
            walkLeft.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Left6.png"))));
            //right
            walkRight.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Right1.png"))));
            walkRight.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Right2.png"))));
            walkRight.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Right3.png"))));
            walkRight.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Right4.png"))));
            walkRight.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Right5.png"))));
            walkRight.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entity/player/Right6.png"))));

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
            if(spriteCounter > 8){
                animationSpriteSetter();
                spriteCounter = 0;
            }
        }
    }
    private void playerMoveVerticallyAndHorizontally(){

        if(keyHandler.isUpPressed()){
            direction = "up";
            y -= speed;
        }
        if(keyHandler.isDownPressed()){
            direction = "down";
            y += speed;
        }
        if(keyHandler.isLeftPressed()){
            direction = "left";
            x -= speed;
        }
        if(keyHandler.isRightPressed()){
            direction = "right";
            x += speed;
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
    private void animationSpriteSetter(){

        switch (moveSprite){
            case 0 -> moveSprite = 1;
            case 1 -> moveSprite = 2;
            case 2 -> moveSprite = 3;
            case 3 -> moveSprite = 4;
            case 4 -> moveSprite = 5;
            case 5 -> moveSprite = 0;
        }
    }
    //DRAW
    public void draw(Graphics2D graphics2D){

        BufferedImage image = null;

        switch (direction){
            case "up" -> image = walkAnimationSetter(image, waldUp);
            case "down" -> image = walkAnimationSetter(image, walkDown);
            case "left" -> image = walkAnimationSetter(image, walkLeft);
            case "right" -> image = walkAnimationSetter(image, walkRight);
        }

        graphics2D.drawImage(image, screenX, screenY, playerWidth, playerHeight, null);
    }
    private BufferedImage walkAnimationSetter(BufferedImage image, List<BufferedImage> list){

        if(!keyHandler.isKeyPressed()){
            image = list.getFirst();
            return image;
        }
        switch (moveSprite){
            case 0 -> image = list.get(0);
            case 1 -> image = list.get(1);
            case 2 -> image = list.get(2);
            case 3 -> image = list.get(3);
            case 4 -> image = list.get(4);
            case 5 -> image = list.get(5);
        }
        return image;
    }

    public int getScreenX() {
        return screenX;
    }
    public int getScreenY() {
        return screenY;
    }
}
