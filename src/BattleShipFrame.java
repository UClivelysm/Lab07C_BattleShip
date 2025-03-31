import javax.swing.*;
import java.awt.*;

public class BattleShipFrame extends JFrame {
    private BattleShipBoard gameBoard;

    public BattleShipFrame() {
        gameBoard = new BattleShipBoard(); // Creates the Board object
        setLayout(new GridLayout(3, 3));
        createBoard();

        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createBoard() {
        BattleShipTile[][] boardTiles = gameBoard.getBoard();
        BattleShipActionListener listener = new BattleShipActionListener(this, gameBoard);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardTiles[row][col].addActionListener(listener);
                add(boardTiles[row][col]);
            }
        }
    }

    public void resetBoard() {
        gameBoard.resetBoard();
    }
}
