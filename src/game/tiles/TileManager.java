package game.tiles;

import game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;

public class TileManager{

    final private GamePanel GAME_PANEL;
    final private TileLoader TILE_LOADER;
    final private String MAP_PATH;
    final private String[][] MAP;
    private int playerColumn;
    private int playerRow;
    private int screenStartX;
    private int screenStartY;

    public TileManager(GamePanel gamePanel, TileLoader tileLoader, String mapPath){

        this.GAME_PANEL = gamePanel;
        this.TILE_LOADER = tileLoader;
        this.MAP_PATH = mapPath;
        MAP = new String[GAME_PANEL.getWorldRows()][GAME_PANEL.getWorldColumns()];
        createTileMap();

    }
    private void createTileMap(){

        try{
            BufferedReader mapReader = new BufferedReader(new FileReader(MAP_PATH));
            int i = 0;
            String currentLine;
            while((currentLine = mapReader.readLine()) != null){
                MAP[i] = currentLine.split(" ");
                i++;
            }
        }catch (Exception e){
            //System.out.println(e);
        }
    }

    //UPDATE
    public void update(){

    }

    //DRAW
    public void draw(Graphics2D graphics2D){

        for(int i = 0; i < GAME_PANEL.getWorldRows(); i++){
            for(int j = 0; j < GAME_PANEL.getWorldColumns(); j++){
                int index = Integer.parseInt(MAP[i][j]);
                if(index == 0){
                    continue;
                }
                Tile tile = TILE_LOADER.getTile(index);
                BufferedImage tileImage = tile.getImage();
                int y = i * GAME_PANEL.getTileSize() - (int)GAME_PANEL.getPlayer().getY() + GAME_PANEL.getPlayer().getScreenY();
                int x = j * GAME_PANEL.getTileSize() - (int)GAME_PANEL.getPlayer().getX() + GAME_PANEL.getPlayer().getScreenX();
                graphics2D.drawImage(tileImage, x, y, GAME_PANEL.getTileSize(), GAME_PANEL.getTileSize(), null);
            }
        }
    }
}
