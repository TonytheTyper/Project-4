public class Monster {
    private String name;

    public int health;

    public int maxDamage;

    public Monster(String name, int health, int maxDamage) {
        this.name = name;
        this.health = health;
        this.maxDamage = maxDamage;
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

    int monsterInRoom(Monster monster, int other) {

        return other;
    }

    String getName() {
        return name;
    }
}