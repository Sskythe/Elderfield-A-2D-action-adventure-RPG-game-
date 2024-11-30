package src.entity;

import src.main.CollisonChecker;
import src.main.GamePanel;
import src.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    final int baseSpeed = 4;
    public final int screenX;
    public final int screenY;
    public KeyHandler keyH;
    public GamePanel gamePanel;

    public Player(KeyHandler keyH, GamePanel gamePanel){
        this.keyH = keyH;
        this.gamePanel = gamePanel;
        screenX = (gamePanel.screenWidth/2) - (gamePanel.tileSize/2);
        screenY = (gamePanel.screenHeight/2) - (gamePanel.tileSize/2);

        hitbox = new Rectangle(10, 16, 30, 30);
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
        setDefaultParameters();
        loadPlayer();
        direction = "down";
    }

    public void setDefaultParameters(){
        worldY = gamePanel.tileSize * 21;
        worldX = gamePanel.tileSize * 23;

        speed = baseSpeed;
    }

//    READS ALL THE PLAYER IMAGES FOR MOVEMENT ANIMATIONssssssss
    public void loadPlayer(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/Walking_Sprites/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/Walking_Sprites/boy_up_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/Walking_Sprites/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/Walking_Sprites/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/Walking_Sprites/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/Walking_Sprites/boy_right_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/Walking_Sprites/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/Walking_Sprites/boy_down_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

//    CHANGING ALTERNATE MOVEMENT IMAGE TO GENERATE THE ANIMATION
    public BufferedImage playerMovermentAnimation(BufferedImage img1, BufferedImage img2){
        if(playerMovementNum == 1){
            return img2;

        }else if(playerMovementNum == 2){
            return img1;
        }
        return img1;
    }

    public boolean checkKeyIsPressed(){
        return (keyH.upPressed || keyH.downPressed ||
                keyH.rightPressed || keyH.leftPresssed);

    }

    public void interactWithObject(int index){
        if(index != 999){
            gamePanel.items[index] = null;
        }
    }

//    UPDATE METHOD
    public  void update(){
        if(!checkKeyIsPressed())
            return;

        if (keyH.upPressed) {
            direction = "up";
        } else if (keyH.downPressed) {
            direction = "down";
        } else if (keyH.leftPresssed) {
            direction = "left";
        } else if (keyH.rightPressed) {
            direction = "right";
        }
        //updates the player world position if the player entity is not colliding with other tiles
        collisionOn = false;
        gamePanel.collisonChecker.checkTile(this);

        //check collision with interactable objects
        int objectIndex = gamePanel.collisonChecker.checkObject(this, true);
        interactWithObject(objectIndex);
        if (!collisionOn) {

            switch (direction) {
                case "up":
                    worldY -= this.speed;
                    break;
                case "down":
                    worldY += this.speed;
                    break;
                case "left":
                    worldX -= this.speed;
                    break;
                case "right":
                    worldX += this.speed;
                    break;
            }
        }


        // ALTERNATE THE WALKING IMAGES TO GIVE A RUNNING ANIMATION
        spriteCounter++;
        if(spriteCounter > 15){
            if(playerMovementNum == 1)
                playerMovementNum = 2;
            else if(playerMovementNum == 2)
                playerMovementNum = 1;
            spriteCounter = 0;
        }
    }

//    DRAW METHOD
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                image = playerMovermentAnimation(up1,up2);
                break;
            case "down":
                image = playerMovermentAnimation(down1,down2);
                break;
            case "left":
                image = playerMovermentAnimation(left1,left2);
                break;
            case "right":
                image = playerMovermentAnimation(right1,right2);
                break;
            default:
                image = down1;
                break;
        }

        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

    }
    
}
