package src.tiles;

import src.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager{
    GamePanel gp;
    String tilesBasePath = "/Assets/Tiles/New_tiles";
    String mapPath = "/Assets/Map/world01.txt";
    public int[][] mapLayout;
    public BaseTile[] tiles;
    public TileManager(GamePanel gp){
        this.gp = gp;
        this.tiles = new BaseTile[10];
        loadTileImages();
        loadMapLayout(mapPath);
    }
    public void loadMapLayout(String mapPath){
        try{
            mapLayout = new int[gp.maxWorldRow][gp.maxWorldCol];
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int row = 0, col = 0;
            for (int i = 0; i < gp.maxWorldRow; i++) {
                String numbers[] = br.readLine().trim().split(" ");
                for (int j = 0; j < gp.maxWorldCol; j++) {
                    mapLayout[i][j] = Integer.parseInt(numbers[j]);
                }
            }
            br.close();


        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void loadTileImages(){
        try{

            tiles[0] = new BaseTile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream(tilesBasePath+"/grass00.png"));

            tiles[1] = new BaseTile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream(tilesBasePath+"/wall.png"));
            tiles[1].colllison = true;

            tiles[2] = new BaseTile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream(tilesBasePath+"/water00.png"));
            tiles[2].colllison = true;

            tiles[3] = new BaseTile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream(tilesBasePath+"/earth.png"));

            tiles[4] = new BaseTile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream(tilesBasePath+"/tree.png"));
            tiles[4].colllison = true;

            tiles[5] = new BaseTile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream(tilesBasePath+"/sand.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void update(){


    }

    public void draw(Graphics2D g2){
        int worldX =0, worldY=0;
        for(int i=0; i< gp.maxWorldRow;i++){
            worldY  = 0;

            for(int j=0; j<gp.maxWorldCol;j++){
                worldY = j * gp.tileSize;
                worldX = i * gp.tileSize;
                int screenX = worldX - gp.p1.worldX + gp.p1.screenX;
                int screenY = worldY - gp.p1.worldY + gp.p1.screenY;
                int currMapTileIndex = mapLayout[j][i];
                if( worldX + gp.tileSize >= gp.p1.worldX - gp.p1.screenX  &&
                        worldY + gp.tileSize >= gp.p1.worldY - gp.p1.screenY &&
                        worldX - gp.tileSize <= gp.p1.worldX + gp.p1.screenX &&
                        worldY - gp.tileSize <= gp.p1.worldY + gp.p1.screenY
                )
                    g2.drawImage(tiles[currMapTileIndex].image,screenX, screenY, gp.tileSize, gp.tileSize, null);
            }



        }
    }

}
