import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {
    private TTTBoard gameBoard;

    public TicTacToeFrame() {
        gameBoard = new TTTBoard(); // Creates the Board object
        setLayout(new GridLayout(3, 3));
        createBoard();

        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createBoard() {
        TicTacToeTile[][] boardTiles = gameBoard.getBoard();
        TicTacToeActionListener listener = new TicTacToeActionListener(this, gameBoard);

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
