package game.entity;

import java.awt.image.BufferedImage;

public class Entity {

    protected float x;
    protected float y;
    protected int speed;

    protected BufferedImage standFront, standBack, standLeft, standRight;
    protected BufferedImage walkDown1, walkDown2, walkUp1, walkUp2, walkLeft1, walkLeft2, walkLeft3, walkLeft4, walkRight1, walkRight2;
    protected String direction;

    protected boolean isSideWalking = false;

    protected int spriteCounter = 0;
    protected int moveSprite = 1;
}
