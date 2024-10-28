package imani.game;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class GameOfLifeController {

    private final GameOfLife model;
    private final GameOfLifeComponent view;
    private final RleImporter importer;

    public GameOfLifeController(
            GameOfLife model,
            GameOfLifeComponent view,
            RleImporter importer
    ) {
            this.model = model;
            this.view = view;
            this.importer = importer;
    }

    public void nextGeneration() {
        grid.nextGen();
        repaint();
    }

    public void play() {
        timer.setDelay(1000);
        timer.start();
    }

    public void pause() {
        timer.stop();
    }

    public void speed() {
        timer.stop();
        timer.setDelay(400);
        timer.start();
    }


    public void paste() {
        try {
            RleImporter.importFromClipboard();
            grid = RleImporter.getGrid();
            repaint();
        } catch (UnsupportedFlavorException | IOException e) {
            System.out.println("Error importing RLE: " + e.getMessage());
        }
    }

    public void toggleCell(int screenX, int screenY) {
        int x = screenX / view.getCellSize();
        int y = screenY / view.getCellSize();
        if (x < model.getWidth() && y < model.getHeight()) {
            int currentState = model.getCell(x, y);
            model.setCell(x, y, currentState == 1 ? 0 : 1);
            view.repaint();
        }
    }

}
