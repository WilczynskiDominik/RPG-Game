package game.tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Tile {

    final private int tileSize;
    private BufferedImage image;

    public Tile(String path, int tileSize){

        this.tileSize = tileSize;
        imageInitialization(path);
    }
    private void imageInitialization(String path){

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        }catch (Exception e){
            //e.printStackTrace();
        }
    }

    public int getTileSize() {
        return tileSize;
    }
    public BufferedImage getImage() {
        return image;
    }
}
