import java.util.ArrayList;
import java.util.Random;

public class BattleShipBoard {
    private BattleShipTile[][] board;
    private ArrayList<BattleShipShip> ships;
    private Random rand = new Random();

    public BattleShipBoard() {
        board = new BattleShipTile[10][10];
        ships = new ArrayList<>();
        createBoard();
        placeShips();
    }

    // Initialize the board with empty BattleShipTiles.
    private void createBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                board[row][col] = new BattleShipTile(row, col);
            }
        }
    }

    public BattleShipTile[][] getBoard() {
        return board;
    }

    // Resets the board for a new game.
    public void resetBoard() {
        ships.clear();
        createBoard();
        placeShips();
    }

    // Place ships in order: 5, 4, 3, 3, 2.
    private void placeShips() {
        int[] shipLengths = {5, 4, 3, 3, 2};
        String[] shipNames = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};

        for (int i = 0; i < shipLengths.length; i++) {
            boolean placed = false;
            int length = shipLengths[i];
            String name = shipNames[i];

            while (!placed) {
                // Randomly decide orientation.
                boolean isHorizontal = rand.nextBoolean();
                int row, col;

                if (isHorizontal) {
                    row = rand.nextInt(10);
                    col = rand.nextInt(10 - length + 1);
                } else {
                    row = rand.nextInt(10 - length + 1);
                    col = rand.nextInt(10);
                }

                if (canPlaceShip(row, col, length, isHorizontal)) {
                    // Place ship by setting the ship value in the board tiles.
                    for (int j = 0; j < length; j++) {
                        if (isHorizontal) {
                            board[row][col + j].setShipValue(length);
                        } else {
                            board[row + j][col].setShipValue(length);
                        }
                    }
                    // Add the ship using the external BattleShipShip class.
                    ships.add(new BattleShipShip(name, length, row, col, isHorizontal));
                    placed = true;
                }
            }
        }
    }

    // Checks if a ship can be placed at the given location.
    private boolean canPlaceShip(int row, int col, int length, boolean isHorizontal) {
        for (int i = 0; i < length; i++) {
            int r = isHorizontal ? row : row + i;
            int c = isHorizontal ? col + i : col;
            if (board[r][c].getShipValue() != 0) { // tile already occupied
                return false;
            }
        }
        return true;
    }

    // Returns the BattleShipShip object that occupies the given tile, if any.
    public BattleShipShip getShipAt(int row, int col) {
        for (BattleShipShip ship : ships) {
            if (ship.isHorizontal()) {
                if (row == ship.getOriginRow() && col >= ship.getOriginCol() && col < ship.getOriginCol() + ship.getLength()) {
                    return ship;
                }
            } else {
                if (col == ship.getOriginCol() && row >= ship.getOriginRow() && row < ship.getOriginRow() + ship.getLength()) {
                    return ship;
                }
            }
        }
        return null;
    }
}
