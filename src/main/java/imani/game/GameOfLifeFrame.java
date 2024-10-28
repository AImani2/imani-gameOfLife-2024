package imani.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

public class GameOfLifeFrame extends JFrame {

    //private final GameOfLifeComponent gridComponent;

    //private RleImporter rleImporter = new RleImporter();

    public GameOfLifeFrame() {

//        int gridWidth = Math.max(grid.getGameBoard().length, 100);
//        int gridHeight = Math.max(grid.getGameBoard()[0].length, 100);
//
//        pack();
//        Insets insets = getInsets();
//
//        int cellSize = 9;
//
//        int width = (gridWidth * cellSize) + insets.left + insets.right;
//        int height = (gridHeight * cellSize) + insets.top + insets.bottom + buttons.getPreferredSize().height;
        setSize(800, 600);
        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        GameOfLife grid = new GameOfLife(300, 300);
        GameOfLifeComponent gameOfLifeComponent = new GameOfLifeComponent(grid, 20);
        RleImporter importer = new RleImporter();
        GameOfLifeController controller = new GameOfLifeController(grid, gameOfLifeComponent, importer);

        gameOfLifeComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.toggleCell(e.getX(), e.getY());
            }
        });

        gameOfLifeComponent.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                controller.toggleCell(e.getX(), e.getY());
            }
        });

        add(gameOfLifeComponent, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        JButton playButton = new JButton("Play");
        buttons.add(playButton);
        JButton nextButton = new JButton("Next");
        buttons.add(nextButton);
        JButton pauseButton = new JButton("Pause");
        buttons.add(pauseButton);
        JButton pasteButton = new JButton("Paste");
        buttons.add(pasteButton);
        JButton speedButton = new JButton("Speed Mode");
        buttons.add(speedButton);
        add(buttons, BorderLayout.SOUTH);

        playButton.addActionListener(e -> controller.play());
        pauseButton.addActionListener(e -> controller.pause());
        nextButton.addActionListener(e -> controller.nextGeneration());
        pasteButton.addActionListener(e -> controller.paste());
        speedButton.addActionListener(e -> controller.speed());

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameOfLife grid = new GameOfLife(100, 100);
            GameOfLifeFrame frame = new GameOfLifeFrame();
            frame.setVisible(true);
        });
    }

}

