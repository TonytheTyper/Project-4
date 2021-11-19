import java.util.Scanner;
// Anthony Genovesi
// Section 7
// Project 4: Dungeon Escape 

public class Main {
    static int userInputDungeonSize() {
        Boolean dungeonSizeError = true;
        int dungeonSize = 0;
        while (dungeonSizeError == true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Between 5-10 inclusive...");
            System.out.print("What size dungeon would you like to explore? ");
            dungeonSize = sc.nextInt();
            if (dungeonSize > 10 || dungeonSize < 5) {
                System.out.println("That size is not compatible. Please try again.");
            } else {
                dungeonSizeError = false;
                System.out.println("Dungeon size is: " + dungeonSize + " X " + dungeonSize);
            }
        }
        return dungeonSize;
    }

    static String userInputName() {
        String userName = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("What is your hero's name? ");
        userName = sc.nextLine();

        // Adding a random adjective to the end of the name
        String[] randomAdjectives = new String[5];
        randomAdjectives[0] = "The Cunning";
        randomAdjectives[1] = "The Swift";
        randomAdjectives[2] = "The Wise";
        randomAdjectives[3] = "The Bold";
        randomAdjectives[4] = "The Brave";
        double randomChoice = Math.random() * 4;
        randomChoice = Math.round(randomChoice);
        int randomChoiceInt = (int) randomChoice;

        userName = userName + " " + randomAdjectives[randomChoiceInt];
        System.out.println("Your hero's name is: " + userName);
        return userName;
    }

    public static int[][] spawnMonsters(int[][] map) {
        int multipleMonsters = 1;
        for (int i = 6; i <= map[0].length * map[0].length; i += 6) {
            int amountOfMonsters = 1;
            // Random monster spawn
            double randomChoiceX = Math.random() * map[0].length;
            randomChoiceX = Math.floor(randomChoiceX);
            int randomChoiceIntX = (int) randomChoiceX;

            double randomChoiceY = Math.random() * map[0].length;
            randomChoiceX = Math.floor(randomChoiceY);
            int randomChoiceIntY = (int) randomChoiceY;

            // Creating my monsters
            Monster monster = new Monster("Monster " + i / 6, 25, 5);

            // If more than one monster is in the room
            // Not a perfect catch
            if (map[randomChoiceIntX][randomChoiceIntY] >= amountOfMonsters) {
                multipleMonsters += 1;
                amountOfMonsters = multipleMonsters;
            }

            // If monster would spawn with hero go back an iteration
            if (randomChoiceIntX == 0 && randomChoiceIntY == 0) {
                i -= 6;
            } else {
                map[randomChoiceIntX][randomChoiceIntY] = monster.monsterInRoom(monster, amountOfMonsters);
            }
            System.out.println("Monster " + monster + " spawned!");
        }
        return map;
    }

    public static void combat(Hero hero, Monster monster) throws Exception {
        while (hero.health > 0 && monster.health > 0) {
            System.out.print(hero.toString() + " ");
            System.out.println(monster.toString());

            // They were hitting the wrong guys.... Changed the inputs to include a
            // different
            // object and made the health public instead of private so each object could get
            // the other objects health

            hero.hit(monster);
            monster.hit(hero);
            Thread.sleep(1000); // Slowing down the loop to see the action!
        }
    }

    static void playerMovement(int[][] map, int playerLocation, int dungeonSize) {
        Scanner sc = new Scanner(System.in);
        boolean wayChosen = true;
        int originalPosition = 0;
        int newPositionY = 0;
        int newPositionX = 0;

        while (wayChosen == true) {
            System.out.println("Which way do you want to go (north, south, east, west)?");
            String direction = sc.nextLine();
            if (direction.equalsIgnoreCase("north")) {
                if (isMovePossible(map, playerLocation, direction) == true) {
                    System.out.println("You moved north!");
                    wayChosen = false;
                }
            } else if (direction.equalsIgnoreCase("south")) {
                if (isMovePossible(map, playerLocation, direction) == true) {
                    System.out.println("You moved south!");
                    wayChosen = false;
                    originalPosition = newPositionY;
                    map[originalPosition][originalPosition] = 0;
                    newPositionY++;
                    map[newPositionY][originalPosition] = 20;
                }
            } else if (direction.equalsIgnoreCase("east")) {
                if (isMovePossible(map, playerLocation, direction) == true) {
                    System.out.println("You moved east!");
                    wayChosen = false;
                }
            } else if (direction.equalsIgnoreCase("west")) {
                if (isMovePossible(map, playerLocation, direction) == true) {
                    System.out.println("You moved west!");
                    wayChosen = false;
                }
            } else {
                System.out.println("You can't move that way!");
            }
        }
    }

    static boolean isMovePossible(int[][] map, int playerLocation, String directionChosen) {
        boolean movePossibility = true;
        for (int i = 0; i < map.length; i++) {
            if (map[0][i] == playerLocation && directionChosen.equalsIgnoreCase("north")) {
                System.out.println("You cannot move north!");
                movePossibility = false;
            } else if (map[i][0] == playerLocation && directionChosen.equalsIgnoreCase("west")) {
                System.out.println("You cannot move west!");
                movePossibility = false;
            } else if (map[map.length - 1][map.length - 1] == playerLocation
                    && directionChosen.equalsIgnoreCase("south")) {
                System.out.println("You cannot move south!");
                movePossibility = false;
            } else if (map[i][map.length - 1] == playerLocation && directionChosen.equalsIgnoreCase("east")) {
                System.out.println("You cannot move east!");
                movePossibility = false;
            }
        }
        return movePossibility;
    }

    public static void playGame(Hero other, int[][] map) throws Exception {
        int numberOfMonsters = 0;
        int playerSpawn = 20; // Using 20 as a placeholder value for where the player is in the dungeon
        map[0][0] = playerSpawn; // Spawning player
        int[] playerLocation = { other.originalPosition, other.newPositionX, other.newPositionY };
        while (other.isAlive() == true) {
            printDungeon(map);
            playerMovement(map, playerSpawn, map.length);
            other.moveRoomLoseHealth();
            Monster monsterInRoom = new Monster("Monster", 25, 5);
            // combat(other, monsterInRoom);
            System.out.println(other.getName() + " has " + other.getHealth() + " health");
            if (other.isAlive() == true) {
                numberOfMonsters--;
            } else {
                numberOfMonsters = 0;
                System.out.println("You died.");
            }
        }
    }

    static void printDungeon(int[][] map) {
        // Printing my dungeon to help visualize while coding
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) throws Exception {
        int dungeonSize = userInputDungeonSize();
        // I think i just use the map to put monsters in rooms.
        // int[][] characterLocation = new int[dungeonSize][dungeonSize];
        int[][] map = new int[dungeonSize][dungeonSize];
        String heroName = userInputName();

        // Hero object with name, hit points, max damage
        Hero hero = new Hero(heroName, 100, 10);
        // Monster monster = new Monster("Monster", 25, 5);
        spawnMonsters(map);
        // int amountOfMonsters = dungeonSize * dungeonSize / 6;
        playGame(hero, map);
    }
}
