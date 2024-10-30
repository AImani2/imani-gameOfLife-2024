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
        model.nextGen();
        view.repaint();
    }

    public void play() {
        view.getTimer().setDelay(1000);
        view.getTimer().start();
    }

    public void pause() {
        view.getTimer().stop();
    }

    public void speed() {
        view.getTimer().stop();
        view.getTimer().setDelay(400);
        view.getTimer().start();
    }


    public void paste() {
        try {
            RleImporter.importFromClipboard();
            GameOfLife importedGrid = RleImporter.getGrid();

            for (int y = 0; y < importedGrid.getGameBoard().length; y++) {
                for (int x = 0; x < importedGrid.getGameBoard()[y].length; x++) {
                    model.setCell(x, y, importedGrid.getCell(x, y));
                }
            }

            view.repaint();
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
