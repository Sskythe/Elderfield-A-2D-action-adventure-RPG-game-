package src.entity;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX , worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int playerMovementNum = 1, spriteCounter = 0;

    public Rectangle hitbox;
    public int hitboxDefaultX, hitboxDefaultY;
    public boolean collisionOn = false;
    
}
