package com.manuel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Box extends JLabel {
    public Box(Point spawn) {
        super("", SwingConstants.CENTER);
        setText("" + mainRandomNumbers[0]);
        setFont(new Font("arial", Font.BOLD, 20));
        setForeground(Color.white);
        setSize(125, 125);
        setLocation(spawn);
        setOpaque(true);
        setBackground(Color.lightGray);
        setBorder(BorderFactory.createMatteBorder(12, 12, 12, 12, Color.gray));
    }

    @Override
    public String toString() {
        return getText();
    }

    int[] mainRandomNumbers = { 2, 4 };
}
