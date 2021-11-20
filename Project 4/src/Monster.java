public class Monster {
    private String name;

    public int health;

    public int maxDamage;

    private int columnPosition;

    private int rowPosition;

    public Monster(String name, int health, int maxDamage, int columnPosition, int rowPosition) {
        this.name = name;
        this.health = health;
        this.maxDamage = maxDamage;
        this.columnPosition = columnPosition;
        this.rowPosition = rowPosition;
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

    public void hit(Hero other) {
        // Making sure it uses other player's damage
        int damageMultiplier = (int) (Math.random() * other.maxDamage) + 1;
        // System.out.println("Iron Man's damage " + damageMultiplier);
        // was checking the damage
        health = health - damageMultiplier;
        System.out.println(name + " gets hit for " + damageMultiplier + "!");
    }

    public String toString() {

        String nameWithHealth = name + " (" + health + ")";
        return nameWithHealth;
    }

    public void dead() {
        columnPosition = 0;
        rowPosition = 0;
        health = 0;
        maxDamage = 0;
    }

    int monsterInRoom(Monster monster, int other) {

        return other;
    }

    String getName() {
        return name;
    }
}