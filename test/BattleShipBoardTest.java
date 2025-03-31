import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {

    private BattleShipBoard board;

    @BeforeEach
    public void setUp() {
        board = new BattleShipBoard();
    }

    @Test
    public void testBoardDimensions() {
        BattleShipTile[][] tiles = board.getBoard();
        assertEquals(10, tiles.length, "Board should have 10 rows");
        for (int row = 0; row < 10; row++) {
            assertEquals(10, tiles[row].length, "Row " + row + " should have 10 columns");
        }
    }

    @Test
    public void testTotalShipSpots() {
        BattleShipTile[][] tiles = board.getBoard();
        int shipSpotCount = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (tiles[row][col].getShipValue() != 0) {
                    shipSpotCount++;
                }
            }
        }
        // The total ship parts should equal 5 + 4 + 3 + 3 + 2 = 17.
        assertEquals(17, shipSpotCount, "There should be exactly 17 ship spots on the board");
    }

    @Test
    public void testGetShipAtForShipTile() {
        BattleShipTile[][] tiles = board.getBoard();
        boolean foundShipTile = false;
        for (int row = 0; row < 10 && !foundShipTile; row++) {
            for (int col = 0; col < 10 && !foundShipTile; col++) {
                if (tiles[row][col].getShipValue() != 0) {
                    BattleShipShip ship = board.getShipAt(row, col);
                    assertNotNull(ship, "getShipAt should return a ship for a tile with a ship value");
                    foundShipTile = true;
                }
            }
        }
        assertTrue(foundShipTile, "There should be at least one ship tile on the board");
    }

    @Test
    public void testGetShipAtForEmptyTile() {
        BattleShipTile[][] tiles = board.getBoard();
        boolean foundEmptyTile = false;
        for (int row = 0; row < 10 && !foundEmptyTile; row++) {
            for (int col = 0; col < 10 && !foundEmptyTile; col++) {
                if (tiles[row][col].getShipValue() == 0) {
                    assertNull(board.getShipAt(row, col), "getShipAt should return null for an empty tile");
                    foundEmptyTile = true;
                }
            }
        }
        assertTrue(foundEmptyTile, "There should be at least one empty tile on the board");
    }

    @Test
    public void testResetBoard() {
        // Count ship parts before reset.
        BattleShipTile[][] tilesBefore = board.getBoard();
        int countBefore = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (tilesBefore[row][col].getShipValue() != 0) {
                    countBefore++;
                }
            }
        }
        assertEquals(17, countBefore, "Initial ship count should be 17");

        // Reset the board.
        board.resetBoard();
        BattleShipTile[][] tilesAfter = board.getBoard();
        int countAfter = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (tilesAfter[row][col].getShipValue() != 0) {
                    countAfter++;
                }
            }
        }
        assertEquals(17, countAfter, "After reset, ship count should be 17");
    }
}
