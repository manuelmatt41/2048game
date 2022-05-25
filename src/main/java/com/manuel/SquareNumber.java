package com.manuel;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JLabel;

public class SquareNumber extends JLabel {
    public SquareNumber(Point location) {
        setSize(125, 125);
        setOpaque(true);
        setBackground(Color.GREEN);
        setLocation(location);
    }
}
