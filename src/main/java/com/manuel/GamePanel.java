package com.manuel;

import java.awt.Color;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    public GamePanel() {
        setLayout(null);

        add(mapGame);

        setSize(500, 500);
        setLocation(50, 50);
        setBackground(Color.blue);
    }

    MapGame mapGame = new MapGame();;
}
