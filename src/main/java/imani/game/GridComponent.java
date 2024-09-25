package imani.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridComponent extends JComponent {

    private final Grid grid;
    private final int cellSize = 20;
    private Timer tima = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            grid.nextGen();
            repaint();
        }
    });

    public GridComponent(Grid grid) {
        this.grid = grid;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int x = e.getX() / cellSize;
                int y = e.getY() / cellSize;


                if (x < grid.getGameBoard().length && y < grid.getGameBoard()[0].length) {
                    if (grid.isAlive(x, y)) {
                        grid.kill(x, y);
                    } else {
                        grid.enliven(x, y);
                    }
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
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

    public void nextGeneration() {
        grid.nextGen();
        repaint();
    }

    public void play() {
        tima.start();
    }

    public void pause() {
        tima.stop();
    }
}


