import java.util.Scanner;
import java.util.ArrayList;
// Anthony Genovesi
// Section 7
// Project 4: Dungeon Escape 

public class Main {
    static ArrayList<Monster> allMyMonsters = new ArrayList<Monster>();
    private static Scanner sc = new Scanner(System.in);

    static int userInputDungeonSize() {
        Boolean dungeonSizeError = true;
        int dungeonSize = 0;
        while (dungeonSizeError == true) {
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
        sc.nextLine();
        return dungeonSize;
    }

    static String userInputName() {
        String userName = "";
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

    public static void spawnMonsters(int[][] map) {
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

            // If monster would spawn with hero go back an iteration
            if (randomChoiceIntX == 0 && randomChoiceIntY == 0) {
                i -= 6;
            } else {
                // Creating my monsters
                Monster monster = new Monster("Monster " + i / 6, 25, 5, randomChoiceIntY, randomChoiceIntX);
                allMyMonsters.add(monster);
                // System.out.println(allMyMonsters); checking my hashmap
                // If more than one monster is in the room
                // Not a perfect catch
                if (map[randomChoiceIntX][randomChoiceIntY] >= amountOfMonsters) {
                    multipleMonsters += 1;
                    amountOfMonsters = multipleMonsters;
                }
                map[randomChoiceIntX][randomChoiceIntY] = monster.monsterInRoom(monster, amountOfMonsters);
            }
            // System.out.println("Monster " + monster + " spawned!");
        }
    }

    public static void combat(Hero hero, Monster monster) throws Exception {
        while (hero.health > 0 && monster.health > 0) {
            System.out.print(hero.toString() + " ");
            System.out.println(monster.toString());

            hero.hit(monster);
            monster.hit(hero);
            Thread.sleep(1000); // Slowing down the loop to see the action!
        }
    }

    static void playerMovement(Hero other, int[][] map, int dungeonSize) {
        boolean wayChosen = false;
        while (wayChosen == false) {
            System.out.print("Which way do you want to go (north, south, east, west)? ");
            String direction = sc.nextLine();
            if (direction.equalsIgnoreCase("north")) {
                if (isMovePossible(other, map, direction) == true) {
                    System.out.println("You moved north!");
                    other.setRow(other.getRow() - 1);
                    wayChosen = true;
                }
            } else if (direction.equalsIgnoreCase("south")) {
                if (isMovePossible(other, map, direction) == true) {
                    System.out.println("You moved south!");
                    other.setRow(other.getRow() + 1);
                    wayChosen = true;
                }
            } else if (direction.equalsIgnoreCase("east")) {
                if (isMovePossible(other, map, direction) == true) {
                    System.out.println("You moved east!");
                    other.setCol(other.getCol() + 1);
                    wayChosen = true;
                }
            } else if (direction.equalsIgnoreCase("west")) {
                if (isMovePossible(other, map, direction) == true) {
                    System.out.println("You moved west!");
                    other.setCol(other.getCol() - 1);
                    wayChosen = true;
                }
            } else {
                System.out.println("You can't move that way!");
            }
        }
    }

    static boolean isMovePossible(Hero other, int[][] map, String directionChosen) {
        boolean movePossibility = true;
        if (other.getRow() == 0 && directionChosen.equalsIgnoreCase("north")) {
            System.out.println("You cannot move north!");
            movePossibility = false;
        } else if (other.getCol() == 0 && directionChosen.equalsIgnoreCase("west")) {
            System.out.println("You cannot move west!");
            movePossibility = false;
        } else if (other.getRow() == map.length - 1 && directionChosen.equalsIgnoreCase("south")) {
            System.out.println("You cannot move south!");
            movePossibility = false;
        } else if (other.getCol() == map.length - 1 && directionChosen.equalsIgnoreCase("east")) {
            System.out.println("You cannot move east!");
            movePossibility = false;
        }
        return movePossibility;
    }

    public static void playGame(Hero other, int[][] map) throws Exception {
        spawnMonsters(map);
        printDungeon(map);
        while (other.isAlive() == true && (other.getCol() < map.length - 1 || other.getRow() < map.length - 1)) {
            int monsterSmellerCounter = 0;
            // Checking for monsters at the beginning of each turn
            for (Monster m : allMyMonsters) {
                // This if statement is for smelling monsters
                if ((m.getCol() == other.getCol() - 1 || m.getCol() == other.getCol() + 1)
                        && m.getRow() == other.getRow()
                        || (m.getRow() == other.getRow() - 1 || m.getRow() == other.getRow() + 1)
                                && m.getCol() == other.getCol()) {
                    other.monsterSmell(m);
                    // This else if sees if the current monster being looked at is in the same room
                    // and if it is fight it.
                } else if (m.getCol() == other.getCol() && m.getRow() == other.getRow()) {
                    combat(other, m);
                    if (other.isAlive()) {
                        // Tried removing my monster from the ArrayList but was getting errors if I
                        // fought one of the monsters at the end of the ArrayList first. Because the for
                        // loop would be in a place that didn't exist. Or at least this is what I think
                        // was happening.

                        // Made the monster dead() method instead that just throws them at the beginning
                        // of the dungeon with zero health, and damage
                        m.dead();
                    }
                }
                if (other.monsterSmell(m) == true) {
                    monsterSmellerCounter++;
                }
            }
            System.out.println("You smell " + monsterSmellerCounter + " monster(s) nearby!");
            playerMovement(other, map, map.length);
            other.moveRoomLoseHealth();

            // Printing out location and health
            System.out.println(other.getName() + " is at " + other.getRow() + " " + other.getCol() + " and has "
                    + other.getHealth() + " health");

            if (other.isAlive() == true) {
                if (other.getCol() == map.length - 1 && other.getRow() == map.length - 1) {
                    System.out.println("You escaped!");
                }
            } else {
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
        int[][] map = new int[dungeonSize][dungeonSize];
        String heroName = userInputName();

        // Hero object with name, hit points, max damage, and spawn location with
        // column/row number.
        Hero hero = new Hero(heroName, 100, 10, 0, 0);
        playGame(hero, map);
        sc.close();
    }
}
