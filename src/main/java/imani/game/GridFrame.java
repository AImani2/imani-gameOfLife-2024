package imani.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class GridFrame extends JFrame {

    private final GridComponent gridComponent;

    public GridFrame() {

        setTitle("Conway's Game of Life");

        Grid grid = new Grid(40, 40);

        gridComponent = new GridComponent(grid);
        add(gridComponent);

        setLayout(new BorderLayout());

        add(gridComponent, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        JButton playButton = new JButton("Play");
        JButton nextButton = new JButton("Next");
        add(buttons, BorderLayout.SOUTH);
        buttons.add(playButton);
        buttons.add(nextButton);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridComponent.play();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridComponent.nextGeneration();
            }
        });

        setSize(grid.getGameBoard().length * 20, grid.getGameBoard()[0].length * 20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new GridFrame().setVisible(true);
    }
}

