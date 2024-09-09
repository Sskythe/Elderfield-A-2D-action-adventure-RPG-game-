package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPresssed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            this.upPressed = true;
        } else if (code == KeyEvent.VK_S) {
            this.downPressed = true;
        } else if (code == KeyEvent.VK_A) {
            this.leftPresssed = true;
        } else if (code == KeyEvent.VK_D) {
            this.rightPressed = true;
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
