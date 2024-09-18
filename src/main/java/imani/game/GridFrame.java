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

        JButton playButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();


                int x1 = (width - 30) / 4;
                int y1 = (height - 30) / 2;
                int x2 = x1 + 10;
                int y2 = y1 + 30 / 4;
                int x3 = x1;
                int y3 = y1;

                int[] xPoints = {x1, x2, x3};
                int[] yPoints = {y1, y2, y3};

                g2.setColor(Color.BLACK);
                g2.fillPolygon(xPoints, yPoints, 3);
            }
        };

        // Set button properties
        playButton.setPreferredSize(new Dimension(10, 50));
        playButton.setFocusPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorder(BorderFactory.createEmptyBorder());

        // Add an action listener to the play button
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call next generation and repaint when the button is clicked
                gridComponent.nextGeneration();
            }
        });

        // Add the button at the bottom (South of the BorderLayout)
        add(playButton, BorderLayout.SOUTH);


        // Set the frame size to fit the grid based on cell size
        setSize(grid.getGameBoard().length * 20, grid.getGameBoard()[0].length * 20);  // Adjust for cellSize

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Create the frame and make it visible
        new GridFrame().setVisible(true);
    }
}


//public class GridFrame extends JFrame {
//
//    private final GridComponent gridComponent;
//
//    public GridFrame() {
//        // Set title for the window
//        setTitle("Conway's Game of Life");
//
//        // Initialize a grid with a width and height (e.g., 40x40)
//        Grid grid = new Grid(40, 40);  // Adjust size based on how many cells you want
//
//        // Create the GridComponent to display the grid
//        gridComponent = new GridComponent(grid);
//
//        // Set the layout to BorderLayout to allow the button at the bottom
//        setLayout(new BorderLayout());
//
//        // Add the GridComponent to the center of the frame
//        add(gridComponent, BorderLayout.CENTER);
//
//        // Create the play button with a triangle symbol
//        JButton playButton = new JButton() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                // Draw a triangle play symbol
//                Graphics2D g2 = (Graphics2D) g;
//                int width = getWidth();
//                int height = getHeight();
//                int[] xPoints = { width / 4, width * 3 / 4, width / 4 };
//                int[] yPoints = { height / 4, height / 2, height * 3 / 4 };
//                g2.setColor(Color.BLACK);
//                g2.fillPolygon(xPoints, yPoints, 3);
//            }
//        };
//
//        // Set button properties
//        playButton.setPreferredSize(new Dimension(50, 50));
//        playButton.setFocusPainted(false);
//        playButton.setContentAreaFilled(false);
//        playButton.setBorder(BorderFactory.createEmptyBorder());
//
//        // Add an action listener to the play button
//        playButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Call next generation and repaint when the button is clicked
//                gridComponent.nextGeneration();
//            }
//        });
//
//        // Add the button at the bottom (South of the BorderLayout)
//        add(playButton, BorderLayout.SOUTH);
//
//        // Set the frame size to fit the grid based on cell size
//        setSize(grid.getGameBoard().length * 20, grid.getGameBoard()[0].length * 20 + 50);  // Extra space for button
//
//        // Set the default close operation
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//
//    public static void main(String[] args) {
//        // Create the frame and make it visible
//        new GridFrame().setVisible(true);
//    }
//}