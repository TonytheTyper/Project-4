public class Hero {
    private String name;

    public int health;

    public int maxDamage;

    private int columnPosition;

    private int rowPosition;

    public Hero(String name, int health, int maxDamage, int columnPosition, int rowPosition) {
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

    public void hit(Monster other) {

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

    public int getHealth() {
        return health;
    }

    String getName() {
        return name;
    }

    boolean isAlive() {
        // this.health = health;
        if (health <= 0) {
            return false;
        }
        return true;
    }

    public void moveRoomLoseHealth() {
        health -= 2;
    }
}