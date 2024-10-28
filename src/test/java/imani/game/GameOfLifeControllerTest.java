package imani.game;

import org.junit.jupiter.api.Test;

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
        GameOfLifeController controller = new GameOfLifeController(model, view, importer);
        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(100).when(model).getHeight();

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
        GameOfLifeController controller = new GameOfLifeController(model, view, importer);
        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(100).when(model).getHeight();
        doReturn(1).when(model).getCell(5, 10);

        //when
        controller.toggleCell(50, 100);

        //then
        verify(model).setCell(5, 10, 0);
        verify(view).repaint();
    }

    @Test
    public void pasteRle() {
        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleImporter importer = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view, importer);

        //when
        controller.paste();

        //then
        //verify(model)
    }

    @Test
    public void pasteUrl() {
        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleImporter importer = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view, importer);
        String url = "";

        //when
        controller.paste();

        //then
        //verify(model)
    }

    @Test
    public void pasteFile() {
        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleImporter importer = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view, importer);
        String fileName = "1beacon.rle";

        //when
        controller.paste();

        //then
        //verify(model)
    }
}