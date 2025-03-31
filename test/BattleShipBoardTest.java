import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class BattleShipBoardTest {

    private BattleShipBoard board;

    @Before
    public void setup() {
        board = new BattleShipBoard();
    }

    @Test
    public void testBoardDimensions() {
        BattleShipTile[][] tiles = board.getBoard();
        // Ensure board has 10 rows.
        assertEquals("Board should have 10 rows", 10, tiles.length);
        // Ensure each row has 10 columns.
        for (int row = 0; row < 10; row++) {
            assertEquals("Row " + row + " should have 10 columns", 10, tiles[row].length);
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
        // Total ship parts should equal 5 + 4 + 3 + 3 + 2 = 17.
        assertEquals("There should be exactly 17 ship spots on the board", 17, shipSpotCount);
    }

    @Test
    public void testGetShipAtForShipTile() {
        BattleShipTile[][] tiles = board.getBoard();
        boolean foundShipTile = false;
        for (int row = 0; row < 10 && !foundShipTile; row++) {
            for (int col = 0; col < 10 && !foundShipTile; col++) {
                if (tiles[row][col].getShipValue() != 0) {
                    BattleShipShip ship = board.getShipAt(row, col);
                    assertNotNull("getShipAt should return a ship for a tile with a ship value", ship);
                    foundShipTile = true;
                }
            }
        }
        assertTrue("There should be at least one ship tile on the board", foundShipTile);
    }

    @Test
    public void testGetShipAtForEmptyTile() {
        BattleShipTile[][] tiles = board.getBoard();
        boolean foundEmptyTile = false;
        for (int row = 0; row < 10 && !foundEmptyTile; row++) {
            for (int col = 0; col < 10 && !foundEmptyTile; col++) {
                if (tiles[row][col].getShipValue() == 0) {
                    assertNull("getShipAt should return null for an empty tile", board.getShipAt(row, col));
                    foundEmptyTile = true;
                }
            }
        }
        assertTrue("There should be at least one empty tile on the board", foundEmptyTile);
    }

    @Test
    public void testResetBoard() {
        // Check the ship count before reset.
        BattleShipTile[][] tilesBefore = board.getBoard();
        int countBeforeReset = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (tilesBefore[row][col].getShipValue() != 0) {
                    countBeforeReset++;
                }
            }
        }
        assertEquals("Initial ship count should be 17", 17, countBeforeReset);

        // Reset the board.
        board.resetBoard();
        BattleShipTile[][] tilesAfter = board.getBoard();
        int countAfterReset = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (tilesAfter[row][col].getShipValue() != 0) {
                    countAfterReset++;
                }
            }
        }
        assertEquals("After reset, ship count should be 17", 17, countAfterReset);
    }
}
