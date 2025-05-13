package game.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Entity {

    protected float x;
    protected float y;
    protected int speed;

    protected BufferedImage standFront, standBack, standLeft, standRight;
    protected List<BufferedImage> walkDown;
    protected List<BufferedImage> waldUp;
    protected List<BufferedImage> walkLeft;
    protected List<BufferedImage> walkRight;

    protected String direction;

    protected int spriteCounter = 0;
    protected int moveSprite = 0;

    protected void initializeWalkAnimationList(){

        walkDown = new ArrayList<>();
        waldUp = new ArrayList<>();
        walkLeft = new ArrayList<>();
        walkRight = new ArrayList<>();
    }
}
