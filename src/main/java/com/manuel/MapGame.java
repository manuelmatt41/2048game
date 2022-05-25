package com.manuel;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JPanel;

public class MapGame extends JPanel {
    public MapGame() {
        setLayout(null);
        setActualBox();
        setSpawnPoints();
        setBackground(Color.gray);
        setLocation(0, 0);
        setSize(500, 500);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                moveUp();
            }
        });
    }

    public void randomSpawn() {
        int randomPositionRow = (int) (Math.random() * 4);
        int randomPositionCol = (int) (Math.random() * 4);

        while (!isEmptyLocation(randomPositionRow, randomPositionCol)) {
            randomPositionRow = (int) (Math.random() * 4);
            randomPositionCol = (int) (Math.random() * 4);
        }

        actualBoxs[randomPositionRow][randomPositionCol] = new Box(spawnPoints[randomPositionRow][randomPositionCol]);
        add(actualBoxs[randomPositionRow][randomPositionCol]);
        actualBoxs[randomPositionRow][randomPositionCol].repaint();
    }

    public boolean isEmptyLocation(int row, int col) {
        return actualBoxs[row][col] == null;
    }

    public void moveUp() {
        Box[][] updateBoxs = new Box[4][4];

        for (int mainRow = 0; mainRow < actualBoxs.length; mainRow++) {
            if (numberEmptyRow(mainRow) > 0) {
                for (int mainCol = 0; mainCol < updateBoxs[mainRow].length; mainCol++) {
                    if (actualBoxs[mainRow][mainCol] == null) {
                        if (numberEmptyCol(mainCol, mainRow + 1) < 3) {
                            for (int i = mainRow + 1; i < updateBoxs.length; i++) {
                                if (actualBoxs[i][mainCol] != null) {
                                    moveBox(i, mainCol, mainRow, mainCol);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        updateMap();
    }

    public void moveBox(int oldRow, int oldCol, int newRow, int newCol) {
        Box[][] newMap = new Box[4][4];

        for (int i = 0; i < actualBoxs.length; i++) {
            for (int j = 0; j < actualBoxs[i].length; j++) {
                if (!(i == oldRow && j == oldCol)) {
                    newMap[i][j] = actualBoxs[i][j];
                }
            }
        }
        
        newMap[newRow][newCol] = actualBoxs[oldRow][oldCol];
        newMap[newRow][newCol].setLocation(spawnPoints[newRow][newCol]);

        actualBoxs = newMap;
    }

    public int numberEmptyRow(int row) {
        return numberEmptyRow(row, 0);
    }

    public int numberEmptyRow(int row, int position) {
        int contador = 0;
        
        for (int i = position; i < actualBoxs[row].length; i++) {
            if (actualBoxs[row][i] == null) {
                contador++;
            }
        }

        return contador;
    }

    public int numberEmptyCol(int col) {
        return numberEmptyCol(col, 0);
    }

    public int numberEmptyCol(int col, int position) {
        int contador = 0;

        for (int i = position; i < actualBoxs.length; i++) {
            if (actualBoxs[i][col] == null) {
                contador++;
            }
        }

        return contador;
    }

    private void updateMap() {
        System.out.println(getComponentCount());
        removeAll();
        System.out.println(getComponentCount());
        for (int i = 0; i < actualBoxs.length; i++) {
            for (int j = 0; j < actualBoxs[i].length; j++) {
                if (actualBoxs[i][j] != null) {
                    add(actualBoxs[i][j]);
                }
            }
        }
        System.out.println(getComponentCount());
        System.out.println(Arrays.deepToString(actualBoxs));
    }

    private void setSpawnPoints() {
        spawnPoints = new Point[4][4];

        int x = 0, y = 0;

        for (int i = 0; i < spawnPoints.length; i++) {
            for (int j = 0; j < spawnPoints[i].length; j++) {
                spawnPoints[i][j] = new Point(x, y);
                x += 125;
            }
            x = 0;
            y += 125;
        }

        initialSpawn();
    }

    private void initialSpawn() {
        int randomPositionRow;
        int randomPositionCol;
        for (int i = 0; i < 6; i++) {
            randomPositionRow = (int) (Math.random() * 4);
            randomPositionCol = (int) (Math.random() * 4);
            actualBoxs[randomPositionRow][randomPositionCol] = new Box(
                    spawnPoints[randomPositionRow][randomPositionCol]);
            add(actualBoxs[randomPositionRow][randomPositionCol]);
        }
    }

    private void setActualBox() {
        actualBoxs = new Box[4][4];
    }

    Point[][] spawnPoints;
    Box[][] actualBoxs;
}
