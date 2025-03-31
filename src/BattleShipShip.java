public class BattleShipShip {
    private String name;
    private int length;
    private int hitCount;
    private int originRow;
    private int originCol;
    private boolean isHorizontal;

    public BattleShipShip(String name, int length, int originRow, int originCol, boolean isHorizontal) {
        this.name = name;
        this.length = length;
        this.hitCount = 0;
        this.originRow = originRow;
        this.originCol = originCol;
        this.isHorizontal = isHorizontal;
    }

    public void registerHit() {
        hitCount++;
    }

    public boolean isSunk() {
        return hitCount >= length;
    }

    // Getter methods.
    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getOriginRow() {
        return originRow;
    }

    public int getOriginCol() {
        return originCol;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }
}
