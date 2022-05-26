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
        updateBox();
        spawnAnimation(spawn);
        setOpaque(true);
        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.gray));
    }

    @Override
    public String toString() {
        return getText();
    }

    @Override
    public void setText(String text) {
        super.setText((1 << Integer.parseInt(text.equals("") ? "0" : text)) + "");
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

    @Override
    public void setBackground(Color bg) {

        super.setBackground(bg);
    }

    public void updateBox() {
        exponencial++;
        setText(exponencial + "");
        setBackground(numberColors[exponencial]);
    }

    public void spawnAnimation(Point p) {
        setLocation(p);

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                int multiplo = i * 25;
                setSize(multiplo, multiplo);

                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }) {
        }.start();
    }

    public void moveAnimation(Point inicial, Point ultimo, Direction direction) {
        new Thread(() -> {
            switch (direction) {
                case ARRIBA:
                    for (int i = inicial.y; i >= ultimo.y; i -= 5) {
                        setLocation(ultimo.x, i);

                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                case ABAJO:
                    for (int i = inicial.y; i < ultimo.y; i += 5) {
                        setLocation(ultimo.x, i);

                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                case IZQUIERDA:
                    for (int i = inicial.x; i >= ultimo.x; i -= 5) {
                        setLocation(i, ultimo.y);

                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                case DERECHA:
                    for (int i = inicial.x; i < ultimo.x; i += 5) {
                        setLocation(i, ultimo.y);

                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }) {
        }.start();
    }

    int[] mainRandomNumbers = { 0, 1 };
    Color[] numberColors = { Color.white, Color.lightGray, Color.orange, Color.PINK, Color.magenta,Color.red, Color.blue,
            Color.cyan, Color.green, Color.yellow };
    int exponencial;
}
