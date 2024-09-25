package imani.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void string() {
        //given
        Grid grid = new Grid(3, 3);

        //when
        String actual = grid.toString();

        //then
        assertEquals("000\n000\n000\n", actual);
    }

    @Test
    public void enliven() {
        //given
        Grid grid = new Grid(3, 3);

        //when
        grid.enliven(1, 0);

        //then
        assertEquals("010\n000\n000\n", grid.toString());
    }

    @Test
    public void importRle() {
        //given
        Grid grid = new Grid(7, 7);
        String rle = "#N 1 beacon\n"
                + "#C Approximately the 32nd-most common oscillator.\n"
                + "#C www.conwaylife.com/wiki/index.php?title=1_beacon\n"
                + "x = 7, y = 7, rule = b3/s23\n"
                + "2b2o3b$bobo3b$o2bob2o$2obo2bo$bobo3b$bo2bo2b$2b2o!";

        //when
        grid.importRle(rle);

        //then
        assertEquals("0011000\n0101000\n1001011\n1101001\n0101000\n0100100\n0011000\n", grid.toString());
    }

    @Test
    public void countLiveNeighbors() {
        //given
        Grid grid = new Grid(3, 3);

        //when
        grid.enliven(1, 0);
        grid.enliven(0, 1);
        grid.enliven(2, 1);
        grid.enliven(1, 2);

        //then
        assertEquals(4, grid.countLiveNeighbors(1, 1));
    }

    @Test
    public void nextGen() {
        //given
        Grid grid = new Grid(3, 3);
        grid.enliven(0, 1);
        grid.enliven(1, 1);
        grid.enliven(2, 1);

        //when
        grid.nextGen();

        //then
        assertEquals("010\n010\n010\n", grid.toString());
    }
}
