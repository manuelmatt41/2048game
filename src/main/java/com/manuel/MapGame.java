package com.manuel;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
        addKeyListener(new GameController());
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
        boolean movimiento = false;
        for (int mainRow = 0; mainRow < actualBoxs.length; mainRow++) {
            if (emptyBoxRowCount(mainRow) > 0) {
                for (int mainCol = 0; mainCol < actualBoxs[mainRow].length; mainCol++) {
                    if (actualBoxs[mainRow][mainCol] == null) {
                        if (emptyBoxColCount(mainCol, mainRow + 1) < 3) {
                            for (int i = mainRow + 1; i < actualBoxs.length; i++) {
                                if (actualBoxs[i][mainCol] != null) {
                                    moveBox(i, mainCol, mainRow, mainCol, Direction.ARRIBA);
                                    movimiento = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        gameLogic(Direction.ARRIBA);

        if (movimiento) {
            randomSpawn();
        }
        movimientos[0] = movimiento;
    }

    public void moveDown() {
        boolean movimiento = false;
        for (int mainRow = actualBoxs.length - 1; mainRow >= 0; mainRow--) {
            if (emptyBoxRowCount(mainRow) > 0) {
                for (int mainCol = actualBoxs[mainRow].length - 1; mainCol >= 0; mainCol--) {
                    if (actualBoxs[mainRow][mainCol] == null) {
                        if (emptyBoxColCount(mainCol, 0, mainRow) < 3) {
                            for (int i = mainRow - 1; i >= 0; i--) {
                                if (actualBoxs[i][mainCol] != null) {
                                    moveBox(i, mainCol, mainRow, mainCol, Direction.ABAJO);
                                    movimiento = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        gameLogic(Direction.ABAJO);
        if (movimiento) {
            randomSpawn();
        }
        movimientos[1] = movimiento;
    }

    public void moveLeft() {
        boolean movimiento = false;
        for (int mainCol = 0; mainCol < actualBoxs.length; mainCol++) {
            if (emptyBoxColCount(mainCol) > 0) {
                for (int mainRow = 0; mainRow < actualBoxs[mainCol].length; mainRow++) {
                    if (actualBoxs[mainRow][mainCol] == null) {
                        if (emptyBoxRowCount(mainRow, mainCol + 1) < 3) {
                            for (int i = mainCol + 1; i < actualBoxs.length; i++) {
                                if (actualBoxs[mainRow][i] != null) {
                                    moveBox(mainRow, i, mainRow, mainCol, Direction.IZQUIERDA);
                                    movimiento = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        gameLogic(Direction.IZQUIERDA);
        if (movimiento) {
            randomSpawn();
        }
        movimientos[2] = movimiento;
    }

    public void moveRight() {
        boolean movimiento = false;
        for (int mainCol = actualBoxs.length - 1; mainCol >= 0; mainCol--) {
            if (emptyBoxColCount(mainCol) > 0) {
                for (int mainRow = actualBoxs[mainCol].length - 1; mainRow >= 0; mainRow--) {
                    if (actualBoxs[mainRow][mainCol] == null) {
                        if (emptyBoxRowCount(mainRow, 0, mainCol) < 3) {
                            for (int i = mainCol - 1; i >= 0; i--) {
                                if (actualBoxs[mainRow][i] != null) {
                                    moveBox(mainRow, i, mainRow, mainCol, Direction.DERECHA);
                                    movimiento = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        gameLogic(Direction.DERECHA);
        if (movimiento) {
            randomSpawn();
        }
        movimientos[3] = movimiento;
        ;
    }

    private void gameLogic(Direction direction) {
        switch (direction) {
            case ARRIBA:
                for (int i = 0; i < actualBoxs.length - 1; i++) {
                    for (int j = 0; j < actualBoxs[i].length; j++) {
                        if (actualBoxs[i][j] != null) {
                            if (actualBoxs[i + 1][j] != null) {
                                if (actualBoxs[i][j].equals(actualBoxs[i + 1][j])) {
                                    actualBoxs[i][j].updateBox();
                                    actualBoxs[i + 1][j].setVisible(false);
                                    actualBoxs[i + 1][j] = null;
                                }
                            }
                        }
                    }
                }
                break;
            case ABAJO:
                for (int i = actualBoxs.length - 1; i > 0; i--) {
                    for (int j = actualBoxs[i].length - 1; j >= 0; j--) {
                        if (actualBoxs[i][j] != null) {
                            if (actualBoxs[i - 1][j] != null) {
                                if (actualBoxs[i][j].equals(actualBoxs[i - 1][j])) {
                                    actualBoxs[i][j].updateBox();
                                    actualBoxs[i - 1][j].setVisible(false);
                                    actualBoxs[i - 1][j] = null;
                                }
                            }
                        }
                    }
                }
                break;
            case IZQUIERDA:
                for (int i = 0; i < actualBoxs.length - 1; i++) {
                    for (int j = 0; j < actualBoxs[i].length; j++) {
                        if (actualBoxs[j][i] != null) {
                            if (actualBoxs[j][i + 1] != null) {
                                if (actualBoxs[j][i].equals(actualBoxs[j][i + 1])) {
                                    actualBoxs[j][i].updateBox();
                                    actualBoxs[j][i + 1].setVisible(false);
                                    actualBoxs[j][i + 1] = null;
                                }
                            }
                        }
                    }
                }
                break;
            case DERECHA:
                for (int i = actualBoxs.length - 1; i > 0; i--) {
                    for (int j = actualBoxs[i].length - 1; j >= 0; j--) {
                        if (actualBoxs[j][i] != null) {
                            if (actualBoxs[j][i - 1] != null) {
                                if (actualBoxs[j][i].equals(actualBoxs[j][i - 1])) {
                                    actualBoxs[j][i].updateBox();
                                    actualBoxs[j][i - 1].setVisible(false);
                                    actualBoxs[j][i - 1] = null;
                                }
                            }
                        }
                    }
                }
                break;
        }
        recollector(direction);
        lost();
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void recollector(Direction direction) {
        switch (direction) {
            case ARRIBA:
                for (int i = 1; i < actualBoxs.length; i++) {
                    for (int j = 0; j < actualBoxs.length; j++) {
                        if (actualBoxs[i][j] != null) {
                            if (actualBoxs[i - 1][j] == null) {
                                moveBox(i, j, i - 1, j, direction);
                            }
                        }
                    }
                }
                break;
            case ABAJO:
                for (int i = actualBoxs.length - 2; i > 0; i--) {
                    for (int j = actualBoxs[i].length - 1; j >= 0; j--) {
                        if (actualBoxs[i][j] != null) {
                            if (actualBoxs[i + 1][j] == null) {
                                moveBox(i, j, i + 1, j, direction);
                            }
                        }
                    }
                }
                break;
            case IZQUIERDA:
                for (int i = 1; i < actualBoxs.length; i++) {
                    for (int j = 0; j < actualBoxs.length; j++) {
                        if (actualBoxs[j][i] != null) {
                            if (actualBoxs[j][i - 1] == null) {
                                moveBox(j, i, j, i - 1, direction);
                            }
                        }
                    }
                }
                break;
            case DERECHA:
                for (int i = actualBoxs.length - 2; i > 0; i--) {
                    for (int j = actualBoxs[i].length - 1; j >= 0; j--) {
                        if (actualBoxs[j][i] != null) {
                            if (actualBoxs[j][i + 1] == null) {
                                moveBox(j, i, j, i + 1, direction);
                            }
                        }
                    }
                }
                break;
        }
    }

    public void moveBox(int oldRow, int oldCol, int newRow, int newCol, Direction direction) {
        Box[][] newMap = new Box[4][4];

        for (int i = 0; i < actualBoxs.length; i++) {
            for (int j = 0; j < actualBoxs[i].length; j++) {
                if (!(i == oldRow && j == oldCol)) {
                    newMap[i][j] = actualBoxs[i][j];
                }
            }
        }

        newMap[newRow][newCol] = actualBoxs[oldRow][oldCol];
        newMap[newRow][newCol].moveAnimation(spawnPoints[oldRow][oldCol], spawnPoints[newRow][newCol], direction);

        actualBoxs = newMap;
    }

    public int emptyBoxRowCount(int row) {
        return emptyBoxRowCount(row, 0, actualBoxs.length);
    }

    public int emptyBoxRowCount(int row, int position) {
        return emptyBoxRowCount(row, position, actualBoxs.length);
    }

    public int emptyBoxRowCount(int row, int position, int limit) {
        int contador = 0;

        for (int i = position; i < limit; i++) {
            if (actualBoxs[row][i] == null) {
                contador++;
            }
        }

        return contador;
    }

    public int emptyBoxColCount(int col) {
        return emptyBoxColCount(col, 0, actualBoxs.length);
    }

    public int emptyBoxColCount(int col, int position) {
        return emptyBoxColCount(col, position, actualBoxs.length);
    }

    public int emptyBoxColCount(int col, int position, int limit) {
        int contador = 0;

        for (int i = position; i < limit; i++) {
            if (actualBoxs[i][col] == null) {
                contador++;
            }
        }

        return contador;
    }

    public void lost() {
        boolean[] p = { false, false, false, false };
        if (Arrays.equals(movimientos, p)) {
            // System.exit(0);
        }
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
        for (int i = 0; i < 2; i++) {
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

    private class GameController extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                moveUp();
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                moveDown();
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                moveLeft();
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                moveRight();
            }
        }
    }

    Point[][] spawnPoints;
    Box[][] actualBoxs;
    GameController gameController = new GameController();
    boolean[] movimientos = { true, true, true, true };
}
