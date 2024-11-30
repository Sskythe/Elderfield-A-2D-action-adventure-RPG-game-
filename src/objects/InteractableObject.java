package src.objects;

import src.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InteractableObject {
    public String name;
    public BufferedImage image;
    public int worldX, worldY;
    public Rectangle hitbox = new Rectangle(0, 0,48, 48);
    public int hitboxDefaultX = 0, hitboxDefaultY = 0;
    public boolean collision = false;

    public void draw(GamePanel gp, Graphics2D g2){
        int screenX = worldX - gp.p1.worldX + gp.p1.screenX;
        int screenY = worldY - gp.p1.worldY + gp.p1.screenY;
        if( worldX + gp.tileSize >= gp.p1.worldX - gp.p1.screenX  &&
                worldY + gp.tileSize >= gp.p1.worldY - gp.p1.screenY &&
                worldX - gp.tileSize <= gp.p1.worldX + gp.p1.screenX &&
                worldY - gp.tileSize <= gp.p1.worldY + gp.p1.screenY
        )
            g2.drawImage(image,screenX, screenY, gp.tileSize, gp.tileSize, null);
    }


}
