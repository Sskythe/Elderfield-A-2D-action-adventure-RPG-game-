package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import src.entity.Player;
import src.objects.InteractableObject;
import src.tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // base tile size 16 x 16 px
    public final int baseTileSize = 16;
    public final int scale = 3;
    public final int FPS = 60;
    // game window will consist of this tile size
    public final int tileSize = baseTileSize * scale;

    public final int gridRowSize = 12;
    public final int gridColSize = 16;
    public final int maxWorldRow = 50;
    public final int maxWorldCol = 50;
    public final int screenWidth = gridColSize * tileSize;
    public final int screenHeight = gridRowSize * tileSize;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    KeyHandler keyH  = new KeyHandler(this);;
    Thread gameThread;

    //This is for play/pause the game.
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 0;



    // position of the player
    public Player p1 = new Player(keyH, this);
    TileManager tileManager = new TileManager(this);
    AssetSetter assetSetter = new AssetSetter(this);
    public InteractableObject []items = new InteractableObject[10];
    public CollisonChecker collisonChecker = new CollisonChecker(this);
    public InGameUI UI = new InGameUI(this);
    // setting up game screen
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setItems(){
        assetSetter.setObjects();
        this.gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int i = 0;

        //it's made sure that the update and repaint method executed at most  60 times per second
        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                ++i;
            }
        }
    }

    //takes input from keyhandler class and updates the position of the character 
    public void update() {
        if(gameState == playState)
            p1.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
//        Drawing map
        tileManager.draw(g2);

//        Drawing interactable objects
        for(int i=0; i< items.length; i++){
            if (items[i] != null)
                items[i].draw(this,g2);
        }

//        Drawing player model
        p1.draw(g2);

    }
}
