public class TTTBoard {
    private TicTacToeTile[][] board;
    private String currentPlayer;

    public TTTBoard() {
        board = new TicTacToeTile[3][3];
        createBoard();
        currentPlayer = "X";
    }

    private void createBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText(" ");
            }
        }
    }

    public TicTacToeTile[][] getBoard() {
        return board;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col].getText().equals(" ")) {
            board[row][col].setText(currentPlayer);
            if (isWin(currentPlayer)) {
                return true; // Move resulted in a win
            }
            switchPlayer();
        }
        return false;
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    public boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private boolean isRowWin(String player) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0].getText().equals(player) &&
                    board[row][1].getText().equals(player) &&
                    board[row][2].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isColWin(String player) {
        for (int col = 0; col < 3; col++) {
            if (board[0][col].getText().equals(player) &&
                    board[1][col].getText().equals(player) &&
                    board[2][col].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player) {
        return (board[0][0].getText().equals(player) && board[1][1].getText().equals(player) && board[2][2].getText().equals(player)) ||
                (board[0][2].getText().equals(player) && board[1][1].getText().equals(player) && board[2][0].getText().equals(player));
    }

    public boolean isTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setText(" ");
            }
        }
        currentPlayer = "X";
    }
}
