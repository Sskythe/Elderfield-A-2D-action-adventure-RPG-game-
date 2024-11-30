package src.main;

import src.entity.Entity;

public class CollisonChecker {
    GamePanel gp;

    public CollisonChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        // Calculate player's future position based on current direction and speed
        int entityLeftWorldX = entity.worldX + entity.hitbox.x;
        int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
        int entityTopWorldY = entity.worldY + entity.hitbox.y;
        int entityBottomWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        // Check for the direction the player is moving in
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapLayout[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.mapLayout[entityTopRow][entityRightCol];
                if (gp.tileManager.tiles[tileNum1].colllison || gp.tileManager.tiles[tileNum2].colllison) {
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapLayout[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileManager.mapLayout[entityBottomRow][entityRightCol];
                if (gp.tileManager.tiles[tileNum1].colllison || gp.tileManager.tiles[tileNum2].colllison) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapLayout[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.mapLayout[entityBottomRow][entityLeftCol];
                if (gp.tileManager.tiles[tileNum1].colllison || gp.tileManager.tiles[tileNum2].colllison) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapLayout[entityTopRow][entityRightCol];
                tileNum2 = gp.tileManager.mapLayout[entityBottomRow][entityRightCol];
                if (gp.tileManager.tiles[tileNum1].colllison || gp.tileManager.tiles[tileNum2].colllison) {
                    entity.collisionOn = true;
                }
                break;
        }


    }

    public int checkObject(Entity entity, boolean player){
        int index =  999;
        for(int i = 0; i < gp.items.length; i++){
           if(gp.items[i] != null){
               //get entity's hitbox area position
               entity.hitbox.x = entity.worldX + entity.hitbox.x;
               entity.hitbox.y = entity.worldY + entity.hitbox.y;

               //get the object's hitbox area position
               gp.items[i].hitbox.x = gp.items[i].worldX + gp.items[i].hitbox.x;
               gp.items[i].hitbox.y = gp.items[i].worldY + gp.items[i].hitbox.y;


               switch (entity.direction){
                   case "up":
                       entity.hitbox.y -= entity.speed;
                       if (entity.hitbox.intersects(gp.items[i].hitbox)){
                           System.out.println("up touch");
                            if(gp.items[i].collision){
                                entity.collisionOn = true;
                            }
                            if(!player){
                                index = i;
                            }
                       }
                       break;
                   case "down":
                       entity.hitbox.y += entity.speed;
                       if (entity.hitbox.intersects(gp.items[i].hitbox)){
                           System.out.println("down touch");
                           if(gp.items[i].collision){
                               entity.collisionOn = true;
                           }
                           if(player){
                               index = i;
                           }
                       }
                       break;
                   case "left":
                       entity.hitbox.x -= entity.speed;
                       if (entity.hitbox.intersects(gp.items[i].hitbox)){
                           System.out.println("left touch");
                           if(gp.items[i].collision){
                               entity.collisionOn = true;
                           }
                           if(player){
                               index = i;
                           }
                       }
                       break;
                   case "right":
                       entity.hitbox.x += entity.speed;
                       if (entity.hitbox.intersects(gp.items[i].hitbox)){
                           System.out.println("right touch");
                           if(gp.items[i].collision){
                               entity.collisionOn = true;
                           }
                           if(player){
                               index = i;
                           }
                       }
                       break;

               }

               entity.hitbox.x = entity.hitboxDefaultX;
               entity.hitbox.y = entity.hitboxDefaultY;
               gp.items[i].hitbox.x = gp.items[i].hitboxDefaultX;
               gp.items[i].hitbox.y = gp.items[i].hitboxDefaultY;
           }
        }

        return index;
    }
}
