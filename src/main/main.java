package src.main;

import javax.swing.JFrame;

public class main extends JFrame {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("2D - game");
        mainWindow.setSize(500, 500);

        GamePanel newGamePanel = new GamePanel();
        mainWindow.setContentPane(newGamePanel);
        mainWindow.pack();

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
        newGamePanel.setItems();
        newGamePanel.startGameThread(); 

    }

}
