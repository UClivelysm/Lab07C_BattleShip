import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleShipFrame extends JFrame {
    private BattleShipBoard gameBoard;
    private BattleShipTile[][] boardTiles;
    private BattleShipActionListener listener;

    // Counters for game logic.
    private int missCounter = 0;
    private int strikeCounter = 0;
    private int totalMissCounter = 0;
    private int totalHitCounter = 0;

    // Labels for displaying counter values.
    private JLabel missLabel;
    private JLabel strikeLabel;
    private JLabel totalMissLabel;
    private JLabel totalHitLabel;

    public BattleShipFrame() {
        gameBoard = new BattleShipBoard();
        boardTiles = gameBoard.getBoard();
        listener = new BattleShipActionListener(this, gameBoard);

        setTitle("Battleship Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // North: Title label.
        JLabel titleLabel = new JLabel("Battleship Game", SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center: 10x10 grid of tiles.
        JPanel centerPanel = new JPanel(new GridLayout(10, 10));
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                boardTiles[row][col].addActionListener(listener);
                centerPanel.add(boardTiles[row][col]);
            }
        }
        add(centerPanel, BorderLayout.CENTER);

        // South: Counters + Reset and Quit buttons.
        JPanel southPanel = createSouthPanel();
        add(southPanel, BorderLayout.SOUTH);

        setSize(600, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Helper method to create the south panel.
    private JPanel createSouthPanel() {
        JPanel southPanel = new JPanel(new FlowLayout());

        missLabel = new JLabel("Misses (current): " + missCounter);
        strikeLabel = new JLabel("Strikes: " + strikeCounter);
        totalMissLabel = new JLabel("Total Misses: " + totalMissCounter);
        totalHitLabel = new JLabel("Total Hits: " + totalHitCounter);

        // Reset button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });

        // Quit button.
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        southPanel.add(missLabel);
        southPanel.add(strikeLabel);
        southPanel.add(totalMissLabel);
        southPanel.add(totalHitLabel);
        southPanel.add(resetButton);
        southPanel.add(quitButton);

        return southPanel;
    }

    // Methods to update counters.
    public void updateMissCounter(int value) {
        missCounter = value;
        missLabel.setText("Misses (current): " + missCounter);
    }

    public void updateStrikeCounter(int value) {
        strikeCounter = value;
        strikeLabel.setText("Strikes: " + strikeCounter);
    }

    public void updateTotalMissCounter(int value) {
        totalMissCounter = value;
        totalMissLabel.setText("Total Misses: " + totalMissCounter);
    }

    public void updateTotalHitCounter(int value) {
        totalHitCounter = value;
        totalHitLabel.setText("Total Hits: " + totalHitCounter);
    }

    public int getMissCounter() {
        return missCounter;
    }

    public int getStrikeCounter() {
        return strikeCounter;
    }

    public int getTotalMissCounter() {
        return totalMissCounter;
    }

    public int getTotalHitCounter() {
        return totalHitCounter;
    }

    // Methods to increment counters.
    public void incrementMiss() {
        updateMissCounter(missCounter + 1);
        updateTotalMissCounter(totalMissCounter + 1);
    }

    public void incrementStrike() {
        updateStrikeCounter(strikeCounter + 1);
    }

    public void incrementHit() {
        updateTotalHitCounter(totalHitCounter + 1);
    }

    // Reset game: reset board and all counters.
    public void resetBoard() {
        gameBoard.resetBoard();
        boardTiles = gameBoard.getBoard();
        // Remove all components and rebuild the frame.
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        // North: Title.
        JLabel titleLabel = new JLabel("Battleship Game", SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center: 10x10 grid.
        JPanel centerPanel = new JPanel(new GridLayout(10, 10));
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                boardTiles[row][col].setEnabled(true);
                boardTiles[row][col].setText("");
                boardTiles[row][col].addActionListener(listener);
                centerPanel.add(boardTiles[row][col]);
            }
        }
        add(centerPanel, BorderLayout.CENTER);

        // South: Re-create the panel with counters and buttons.
        missCounter = 0;
        strikeCounter = 0;
        totalMissCounter = 0;
        totalHitCounter = 0;
        JPanel southPanel = createSouthPanel();
        add(southPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }
}
