public class Hero {
    private String name;

    public int health;

    public int maxDamage;

    public int originalPosition;
    public int newPositionX;
    public int newPositionY;

    public Hero(String name, int health, int maxDamage) {
        this.name = name;
        this.health = health;
        this.maxDamage = maxDamage;
    }

    // public String monsterSmell(int[][] map, int monster) {
    // monster = 1;
    // int adjascentSquares = map [0][0];
    // if(adjascentSquares.equals(monster)){

    // }
    // return "";
    // }

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