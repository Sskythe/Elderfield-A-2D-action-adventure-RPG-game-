package src.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPresssed, rightPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            this.upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            this.downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            this.leftPresssed = true;
        }
        if (code == KeyEvent.VK_D) {
            this.rightPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if(gp.gameState == gp.pauseState)
                gp.gameState = gp.playState;
            else if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            this.upPressed = false;
        } else if (code == KeyEvent.VK_S) {
            this.downPressed = false;
        } else if (code == KeyEvent.VK_A) {
            this.leftPresssed = false;
        } else if (code == KeyEvent.VK_D) {
            this.rightPressed = false;
        }
    }

}
