package imani.game;

public class Grid {
    private int[][] gameBoard;

    public Grid(int width, int height) {
        gameBoard = new int[width][height];
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard[y].length; x++) {
                builder.append(gameBoard[y][x]); // the one that prints sideways is x
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public Grid nextGen() {
        //write this method here
    }
}
