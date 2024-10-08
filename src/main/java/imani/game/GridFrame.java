package imani.game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFrame;

public class GridFrame extends JFrame {

    private final GridComponent gridComponent;

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
        add(buttons, BorderLayout.SOUTH);

        playButton.addActionListener(e -> gridComponent.play());
        pauseButton.addActionListener(e -> gridComponent.pause());
        nextButton.addActionListener(e -> gridComponent.nextGeneration());

        setSize(grid.getGameBoard().length * 20, grid.getGameBoard()[0].length * 20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        if (args.length > 0) {
            String filePath = args[0];
            File rleFile = new File(filePath);

            try {
                String rleData = new String(Files.readAllBytes(rleFile.toPath()));

                Grid grid = new Grid(40, 40);
                grid.importRle(rleData);

                SwingUtilities.invokeLater(() -> {
                    new GridFrame(grid).setVisible(true);
                });
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            Grid grid = new Grid(40, 40);
            new GridFrame(grid).setVisible(true);
        }

    }
}

