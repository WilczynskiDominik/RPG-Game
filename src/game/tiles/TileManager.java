package game.tiles;

import game.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileManager {

    final private GamePanel GAME_PANEL;
    final private String MAP_PATH;
    final private String[][] MAP = new String[12][20];;
    private List<Tile> tiles;

    public TileManager(GamePanel gamePanel, String mapPath){

        this.GAME_PANEL = gamePanel;
        this.MAP_PATH = mapPath;
        createTileMap();
        getTiles();

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
    private void getTiles(){

        tiles = new ArrayList<Tile>();
        tiles.add(null);
        try{
            tiles.add(new Tile("/tiles/Gras1.png", GAME_PANEL.getTileSize()));
            tiles.add(new Tile("/tiles/Dirt.png", GAME_PANEL.getTileSize()));
        }catch (Exception e){
            //
        }
    }

    //UPDATE
    public void update(){

    }

    //DRAW
    public void draw(Graphics2D graphics2D){

        for(int i = 0; i < GAME_PANEL.getScreenRows(); i++){
            for(int j = 0; j < GAME_PANEL.getScreenColumns(); j++){
                int index = Integer.parseInt(MAP[i][j]);
                if(index == 0){
                    continue;
                }
                int y = i * GAME_PANEL.getTileSize();
                int x = j * GAME_PANEL.getTileSize();
                graphics2D.drawImage(tiles.get(index).getImage(), x, y, tiles.get(index).getTileSize(), tiles.get(index).getTileSize(), null);
            }
        }
    }
}
