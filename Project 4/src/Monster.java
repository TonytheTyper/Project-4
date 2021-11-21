public class Monster {
    private String name;

    public int health;

    public int maxDamage;

    private int columnPosition;

    private int rowPosition;

    // My constructor method
    public Monster(String name, int health, int maxDamage, int columnPosition, int rowPosition) {
        this.name = name;
        this.health = health;
        this.maxDamage = maxDamage;
        this.columnPosition = columnPosition;
        this.rowPosition = rowPosition;
    }

    // Getters and setters
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

    // Hit method for combat.
    public void hit(Hero other) {
        int damageMultiplier = (int) (Math.random() * other.maxDamage) + 1;
        health = health - damageMultiplier;
        System.out.println(name + " gets hit for " + damageMultiplier + "!");
    }

    public String toString() {

        String nameWithHealth = name + " (" + health + ")";
        return nameWithHealth;
    }

    // public void dead() {
    // columnPosition = 0;
    // rowPosition = 0;
    // health = 0;
    // maxDamage = 0;
    // }

    // Method I was using to visualize monsters on my map.
    int monsterInRoom(Monster monster, int other) {

        return other;
    }

    String getName() {
        return name;
    }
}