package imani.game;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class GameOfLifeComponent extends JComponent {

    private final GameOfLife grid;

    private final int cellSize;

    private Timer timer;

    public GameOfLifeComponent(GameOfLife grid, int cellSize) {
        this.grid = grid;
        this.cellSize = cellSize;

        timer = new Timer(1000, e -> {
            grid.nextGen();
            repaint();
        });

    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[][] gameBoard = grid.getGameBoard();

        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard[y].length; x++) {
                if (gameBoard[y][x] == 1) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }

                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }

        g.setColor(Color.GRAY);
        for (int i = 0; i <= gameBoard.length; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, gameBoard[0].length * cellSize);
        }
        for (int i = 0; i <= gameBoard[0].length; i++) {
            g.drawLine(0, i * cellSize, gameBoard.length * cellSize, i * cellSize);
        }
    }

    public int getCellSize() {
        return cellSize;
    }
}


