package imani.game;

import org.junit.jupiter.api.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.mockito.Mockito.*;

class GameOfLifeControllerTest {

    private static final String RLE = """
                #N 1 beacon
                #C Approximately the 32nd-most common oscillator.
                #C www.conwaylife.com/wiki/index.php?title=1_beacon
                x = 7, y = 7, rule = b3/s23
                2b2o3b$bobo3b$o2bob2o$2obo2bo$bobo3b$bo2bo2b$2b2o!
                """;

    @Test
    void toggleCellOn() {
        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleImporter importer = mock();
        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(100).when(model).getHeight();
        GameOfLifeController controller = new GameOfLifeController(model, view, importer);
        //when
        controller.toggleCell(50, 100);

        //then
        verify(model).setCell(5, 10, 1);
        verify(view).repaint();
    }

    @Test
    void toggleCellOff() {
        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleImporter importer = mock();
        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(100).when(model).getHeight();
        doReturn(1).when(model).getCell(5, 10);
        GameOfLifeController controller = new GameOfLifeController(model, view, importer);
        //when
        controller.toggleCell(50, 100);

        //then
        verify(model).setCell(5, 10, 0);
        verify(view).repaint();
    }

    @Test
    public void pasteRle() throws IOException, UnsupportedFlavorException {
        //given
        String rle = """
                #N 1 beacon
                #C Approximately the 32nd-most common oscillator.
                #C www.conwaylife.com/wiki/index.php?title=1_beacon
                x = 7, y = 7, rule = b3/s23
                2b2o3b$bobo3b$o2bob2o$2obo2bo$bobo3b$bo2bo2b$2b2o!
                """;
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleImporter importer = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view, importer);
        doNothing().when(importer).importFromClipboard();
        //when
        controller.paste();

        //then
        verify(importer).importFromClipboard();
        verify(importer).importRleFromText(rle);
        verify(importer).applyRleToGrid(rle);
        verify(importer).parseRle(rle);
        //verify(model).setCell();
        verify(view).repaint();
    }

    @Test
    public void pasteUrl() throws IOException, UnsupportedFlavorException {
        //given
        String url = "https://copy.sh/life/examples/rats_synth.rle";
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleImporter importer = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view, importer);
        doNothing().when(importer).importFromClipboard();
        //when
        controller.paste();

        //then
        verify(importer).importFromClipboard();
        verify(importer).importRleFromUrl(url);
        verify(importer).applyRleToGrid(url);
        verify(importer).parseRle(url);
        //verify(model)
        verify(view).repaint();
    }

//    @Test
//    public void pasteFile() throws IOException, UnsupportedFlavorException {
//        //given
//        GameOfLife model = mock();
//        GameOfLifeComponent view = mock();
//        RleImporter importer = mock();
//        GameOfLifeController controller = new GameOfLifeController(model, view, importer);
//        String fileName = "1beacon.rle";
//
//        doNothing().when(importer).importFromClipboard();
//
//        //when
//        controller.paste();
//
//        //then
//        //verify(model)
//    }
}