package game.tiles;

import game.GamePanel;

import java.util.ArrayList;
import java.util.List;

public class TileLoader {

    private List<Tile> tileList;
    final private GamePanel GAME_PANEL;

    public TileLoader(GamePanel gamePanel){

        this.GAME_PANEL = gamePanel;
        getTiles();
    }
    private void getTiles(){

        tileList = new ArrayList<Tile>();
        tileList.add(null);
        try{
            tileList.add(new Tile("/tiles/Gras1.png", GAME_PANEL.getTileSize()));
            tileList.add(new Tile("/tiles/Dirt.png", GAME_PANEL.getTileSize()));
        }catch (Exception e){
            //
        }
    }

    public Tile getTile(int index){
        return tileList.get(index);
    }
}
