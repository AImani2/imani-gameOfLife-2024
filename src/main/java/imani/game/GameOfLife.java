package imani.game;

public class GameOfLife {
    private int[][] gameBoard;

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public GameOfLife(int width, int height) {
        gameBoard = new int[width][height];
    }

    public void setCell(int x, int y, int state) {
        gameBoard[y][x] = state;
    }

    public int getHeight() {
        return gameBoard.length;
    }

    public int getWidth() {
        return gameBoard[0].length;
    }

    public int getCell(int x, int y) {
        return gameBoard[y][x]; //why y,x?
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

    public boolean isAlive(int x, int y) {
        return gameBoard[x][y] == 1;
    }

    protected int countLiveNeighbors(int x, int y) {
        int liveNeighbors = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int neighborX = x + i;
                int neighborY = y + j;

                if (neighborX >= 0
                        && neighborX < gameBoard.length
                        && neighborY >= 0
                        && neighborY < gameBoard[0].length) {
                    liveNeighbors += gameBoard[neighborX][neighborY];
                }

            }
        }

        return liveNeighbors;
    }

    public void nextGen() {
        int[][] newGameBoard = new int[gameBoard.length][gameBoard[0].length];

        for (int x = 0; x < gameBoard.length; x++) {
            for (int y = 0; y < gameBoard[x].length; y++) {

                int liveNeighbors = countLiveNeighbors(x, y);

                if (liveNeighbors == 3 || (gameBoard[x][y] == 1 && liveNeighbors == 2)) {
                    newGameBoard[x][y] = 1;
                }

            }
        }

        gameBoard = newGameBoard;
    }
}
