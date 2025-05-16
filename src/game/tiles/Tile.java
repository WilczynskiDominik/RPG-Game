package game.tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Tile {

    private BufferedImage image;

    public Tile(String path, int tileSize){

        imageInitialization(path);
    }
    private void imageInitialization(String path){

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        }catch (Exception e){
            //e.printStackTrace();
        }
    }
    public BufferedImage getImage() {
        return image;
    }
}
