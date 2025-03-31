import javax.swing.JButton;

public class BattleShipTile extends JButton {
    private int row;
    private int col;
    private int shipValue; // 0 for water, otherwise the ship’s length (or identifier)

    public BattleShipTile(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.shipValue = 0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getShipValue() {
        return shipValue;
    }

    public void setShipValue(int value) {
        this.shipValue = value;
    }
}
