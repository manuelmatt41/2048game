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
        exponencial = mainRandomNumbers[0];
        setFont(new Font("arial", Font.BOLD, 20));
        setForeground(Color.white);
        setText(exponencial + "");
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

    @Override
    public void setText(String text) {
        super.setText((1<<Integer.parseInt(text.equals("") ? "0" : text)) + "");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj.getClass() == Box.class) {
                Box b = (Box) obj;

                if (b.getText().equals(getText())) {
                    return true;
                }
            }
        }

        return false;
    }

    public void updateBox() {
        exponencial++;
        setText(exponencial + "");
    }

    int[] mainRandomNumbers = { 1, 2 };
    int exponencial;
}
