package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // base tile size 16 x 16 px
    public final int baseTileSize = 16;
    public final int scale = 3;

    // game window will consist of this tilesize
    public final int tileSize = baseTileSize * scale;

    public final int gridRowSize = 10;
    public final int gridColSize = 12;
    public final int screenWidth = gridColSize * tileSize;
    public final int screenHeight = gridRowSize * tileSize;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // position of the player
    public int posX = 200, posY = 200;
    private int speed = 3;

    // setting up game screen
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update() {
        if (keyH.upPressed) {
            posY -= this.speed;
        } else if (keyH.downPressed) {
            posY += this.speed;
        } else if (keyH.leftPresssed) {
            posX -= this.speed;
        } else if (keyH.rightPressed) {
            posX += this.speed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(posX, posY, tileSize, tileSize);
        g.dispose();
    }
}
