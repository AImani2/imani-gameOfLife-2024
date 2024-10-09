package imani.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFrame;

public class GridFrame extends JFrame {

    private final GridComponent gridComponent;

    private RleImporter rleImporter = new RleImporter();

    public GridFrame(Grid grid) {

        setTitle("Conway's Game of Life");

        gridComponent = new GridComponent(grid);
        add(gridComponent);

        setLayout(new BorderLayout());

        add(gridComponent, BorderLayout.CENTER);
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

        playButton.addActionListener(e -> gridComponent.play());
        pauseButton.addActionListener(e -> gridComponent.pause());
        nextButton.addActionListener(e -> gridComponent.nextGeneration());
        pasteButton.addActionListener(e -> gridComponent.paste());
        speedButton.addActionListener( e -> gridComponent.speed());

        int gridWidth = Math.max(grid.getGameBoard().length, 100);
        int gridHeight = Math.max(grid.getGameBoard()[0].length, 100);

        pack();
        Insets insets = getInsets();

        int cellSize = 9;

        int width = (gridWidth * cellSize) + insets.left + insets.right;
        int height = (gridHeight * cellSize) + insets.top + insets.bottom + buttons.getPreferredSize().height;

        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Grid grid = new Grid(100, 100);
            GridFrame frame = new GridFrame(grid);
            frame.setVisible(true);
        });
    }

}

