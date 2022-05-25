package com.manuel;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    public GameFrame() {
        super("2048");
        setLayout(null);

        add(new GamePanel());

        setSize(600, 625);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    GamePanel gamePanel;
}
