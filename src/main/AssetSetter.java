package src.main;

import src.objects.OBJ_Key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObjects(){
        System.out.println("called");
        gp.items[0] = new OBJ_Key();
        gp.items[0].worldX = 23 * gp.tileSize ;
        gp.items[0].worldY = 42 * gp.tileSize;

    }
}
