import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleShipActionListener implements ActionListener {
    private BattleShipFrame frame;
    private BattleShipBoard board;

    // For consecutive misses.
    private int consecutiveMisses = 0;

    public BattleShipActionListener(BattleShipFrame frame, BattleShipBoard board) {
        this.frame = frame;
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BattleShipTile tile = (BattleShipTile) e.getSource();
        int row = tile.getRow();
        int col = tile.getCol();

        // Prevent re-clicking an already disabled tile.
        if (!tile.isEnabled()) {
            return;
        }

        if (tile.getShipValue() == 0) { // MISS
            // Load the Miss icon directly from the classpath.
            ImageIcon missIcon = new ImageIcon(getClass().getResource("/Miss.png"));
            tile.setIcon(missIcon);
            tile.setDisabledIcon(missIcon);
            tile.setEnabled(false);
            consecutiveMisses++;
            frame.incrementMiss();

            if (consecutiveMisses >= 5) {
                frame.incrementStrike();
                consecutiveMisses = 0;
                JOptionPane.showMessageDialog(frame, "That's Strike " + frame.getStrikeCounter() + "!");
                if (frame.getStrikeCounter() >= 3) {
                    int response = JOptionPane.showConfirmDialog(frame, "That's 3 Strikes! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        frame.resetBoard();
                    } else {
                        System.exit(0);
                    }
                    return;
                }
            }

        } else { // HIT
            // Load the Hit icon directly from the classpath.
            ImageIcon hitIcon = new ImageIcon(getClass().getResource("/Hit.png"));
            tile.setIcon(hitIcon);
            tile.setDisabledIcon(hitIcon);
            tile.setEnabled(false);
            consecutiveMisses = 0;  // reset on hit
            frame.incrementHit();

            BattleShipShip ship = board.getShipAt(row, col);
            if (ship != null) {
                ship.registerHit();
                if (ship.isSunk()) {
                    JOptionPane.showMessageDialog(frame, "You sunk the " + ship.getName() + "!");
                }
            }

            if (frame.getTotalHitCounter() >= 17) {
                int response = JOptionPane.showConfirmDialog(frame, "You win! All ships sunk. Play again?", "Victory", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    frame.resetBoard();
                } else {
                    System.exit(0);
                }
                return;
            }
        }
    }
}
