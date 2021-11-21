public class Hero {
    private String name;

    public int health;

    public int maxDamage;

    private int columnPosition;

    private int rowPosition;

    // My constructor method.
    public Hero(String name, int health, int maxDamage, int columnPosition, int rowPosition) {
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

    public int getHealth() {
        return health;
    }

    String getName() {
        return name;
    }

    // Hero's monster smell method for nearby monsters.
    public boolean monsterSmell(Monster other) {
        boolean monsterNearby = false;
        if ((other.getCol() == columnPosition + 1 || other.getCol() == columnPosition - 1)
                && other.getRow() == rowPosition) {
            monsterNearby = true;
        } else if ((other.getRow() == rowPosition + 1 || other.getRow() == rowPosition - 1)
                && other.getCol() == columnPosition) {
            monsterNearby = true;
        }
        return monsterNearby;
    }

    // Hit method for combat.
    public void hit(Monster other) {
        int damageMultiplier = (int) (Math.random() * other.maxDamage) + 1;
        health = health - damageMultiplier;
        System.out.println(name + " gets hit for " + damageMultiplier + "!");
    }

    public String toString() {

        String nameWithHealth = name + " (" + health + ")";
        return nameWithHealth;
    }

    // Checking if hero's health is above zero to see if they're alive.
    boolean isAlive() {
        if (health <= 0) {
            return false;
        }
        return true;
    }

    // Losing health as hero moves through rooms.
    public void moveRoomLoseHealth() {
        health -= 2;
    }
}