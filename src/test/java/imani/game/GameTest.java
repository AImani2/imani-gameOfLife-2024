package imani.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void string() {
        //given
        Grid grid = new Grid(3,3);

        //when
        String actual = grid.toString();

        //then
        assertEquals("000\n000\n000\n", actual);
    }

    @Test
    public void enliven() {
        //given
        Grid grid = new Grid(3,3);

        //when
        grid.enliven(1, 0);

        //then
        assertEquals("010\n000\n000\n", grid.toString());
    }

    @Test
    public void nextGen() {
        //given
        Grid grid = new Grid(3,3);
        grid.enliven(0,1);
        grid.enliven(1,1);
        grid.enliven(2,1);

        //when
        grid.nextGen();

        //then
        assertEquals("010\n010\n010\n", grid.toString());
    }
}
