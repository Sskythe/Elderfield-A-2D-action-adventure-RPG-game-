package src.main;

import java.awt.*;

public class InGameUI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_80;
    public InGameUI(GamePanel gp){
        this.gp = gp;
        arial_80 = new Font("Arial", Font.BOLD, 80);
    }

    //Draws the UI on the game screen like score, health, interaction etc.
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_80);
        g2.setColor(Color.WHITE);
        //Pause screen
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }
    public void drawPauseScreen(){
        g2.setFont(arial_80);
        String text = "GAME PAUSED";
        int x = getXForCenterText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x,y);
    }

    public int getXForCenterText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
}
