package com.manuel;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    public GameFrame() {
        super("2048");
        setLayout(null);

        add(gamePanel);

        setSize(600, 625);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(gamePanel.mapGame.gameController);
    }

    GamePanel gamePanel = new GamePanel();
}
