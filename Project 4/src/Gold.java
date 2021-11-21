public class Gold {
    String name;

    private int columnPosition;

    private int rowPosition;

    // Constructor method
    public Gold(String name, int columnPosition, int rowPosition) {
        this.name = name;
        this.columnPosition = columnPosition;
        this.rowPosition = rowPosition;
    }

    // Getters and setters
    String getName() {
        return name;
    }

    public void setCol(int newCol) {
        columnPosition = newCol;
    }

    public int getCol() {
        return columnPosition;
    }

    public void setRow(int newRow) {
        rowPosition = newRow;
    }

    public int getRow() {
        return rowPosition;
    }

    // Method I was using to visualize gold on my map.
    int goldInRoom(Gold gold, int other) {

        return other;
    }

}
