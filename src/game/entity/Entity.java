package game.entity;

import java.awt.image.BufferedImage;

public class Entity {

    protected float x;
    protected float y;
    protected int speed;

    protected BufferedImage standFront, standBack, standLeft, standRight;
    protected BufferedImage walkDown1, walkDown2, walkUp1, walkUp2, walkLeft1, walkLeft2, walkRight1, walkRight2;
    protected String direction;
}
