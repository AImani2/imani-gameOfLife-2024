package imani.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfLifeTest {

    @Test
    public void string() {
        //given
        GameOfLife grid = new GameOfLife(3, 3);

        //when
        String actual = grid.toString();

        //then
        assertEquals("000\n000\n000\n", actual);
    }

    @Test
    public void enliven() {
        //given
        GameOfLife grid = new GameOfLife(3, 3);

        //when
        grid.setCell(1, 0, 1);

        //then
        assertEquals("010\n000\n000\n", grid.toString());
    }



    @Test
    public void countLiveNeighbors() {
        //given
        GameOfLife grid = new GameOfLife(3, 3);

        //when
        grid.setCell(1, 0, 1);
        grid.setCell(0, 1, 1);
        grid.setCell(2, 1, 1);
        grid.setCell(1, 2, 1);

        //then
        assertEquals(4, grid.countLiveNeighbors(1, 1));
    }

    @Test
    public void nextGen() {
        //given
        GameOfLife grid = new GameOfLife(3, 3);
        grid.setCell(0, 1, 1);
        grid.setCell(1, 1, 1);
        grid.setCell(2, 1, 1);

        //when
        grid.nextGen();

        //then
        assertEquals("010\n010\n010\n", grid.toString());
    }

}
