package imani.game;

public class Grid {
    private int[][] gameBoard;

    public int[][] getGameBoard() {
        return gameBoard;
    }

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

    public void enliven(int x, int y) {
        gameBoard[y][x] = 1;
    }

    protected int countLiveNeighbors(int x, int y) {
        int liveNeighbors = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int neighborX = x + i;
                int neighborY = y + j;

                if (neighborX >= 0 && neighborX < gameBoard.length &&
                        neighborY >= 0 && neighborY < gameBoard[0].length) {
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

                if (gameBoard[x][y] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        newGameBoard[x][y] = 0;
                    } else {
                        newGameBoard[x][y] = 1;
                    }
                } else {
                    if (liveNeighbors == 3) {
                        newGameBoard[x][y] = 1;
                    }
                }
            }
        }

        gameBoard = newGameBoard;
    }
}
