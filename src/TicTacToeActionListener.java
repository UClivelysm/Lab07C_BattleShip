import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeActionListener implements ActionListener {
    private TicTacToeFrame frame;
    private TTTBoard gameBoard;

    public TicTacToeActionListener(TicTacToeFrame frame, TTTBoard gameBoard) {
        this.frame = frame;
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TicTacToeTile tile = (TicTacToeTile) e.getSource();
        int row = tile.getRow();
        int col = tile.getCol();

        if (gameBoard.makeMove(row, col)) { // Move made successfully
            JOptionPane.showMessageDialog(frame, "Player " + gameBoard.getCurrentPlayer() + " wins!");
            if (JOptionPane.showConfirmDialog(frame, "Start a new game?", "Game Over", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                frame.resetBoard();
            }
        } else if (gameBoard.isTie()) {
            JOptionPane.showMessageDialog(frame, "It's a tie!");
            if (JOptionPane.showConfirmDialog(frame, "Start a new game?", "Game Over", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                frame.resetBoard();
            }
        }
    }
}
