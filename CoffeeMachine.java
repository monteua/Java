package machine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CoffeeMachine {
    final static HashMap<String, HashMap<String, Integer>> resourcesCost = new HashMap<String, HashMap<String, Integer>>()
    {{
        put("espresso", new HashMap<String, Integer>(){{
            put("water", 250);
            put("milk", 0);
            put("coffeeBeans", 16);
            put("cost", 4);
        }});
        put("latte", new HashMap<String, Integer>(){{
            put("water", 350);
            put("milk", 75);
            put("coffeeBeans", 20);
            put("cost", 7);
        }});
        put("cappuccino", new HashMap<String, Integer>(){{
            put("water", 200);
            put("milk", 100);
            put("coffeeBeans", 12);
            put("cost", 6);
        }});
    }};

    static int water = 400;
    static int milk = 540;
    static int coffeeBeans = 120;
    static int money = 550;
    static int cups = 9;


    public static void getStatus() {
        System.out.println("The coffee machine has: ");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money");
    }

    public static int getMaxNumberOfCoffee(int waterCost, int milkCost, int coffeeBeansCost) {
        int[] resources = {
                waterCost > 0 ? water / waterCost : 999,
                milkCost > 0 ? milk / milkCost : 999,
                coffeeBeansCost > 0 ? coffeeBeans / coffeeBeansCost : 999,
                cups
        };

        if (resources[0] == 0) {
            System.out.println("Sorry, not enough water!");
        } else if (resources[1] == 0) {
            System.out.println("Sorry, not enough milk!");
        } else if (resources[2] == 0) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (resources[3] == 0) {
            System.out.println("Sorry, not enough disposable cups!");
        }

        Arrays.sort(resources);
        return resources[0];
    }

    public static void makeCoffee(String coffeeType) {

        coffeeType = coffeeType.toLowerCase();

        if (!resourcesCost.containsKey(coffeeType)) {
            throw new AssertionError("We don't sell this type of coffee!");
        }

        int waterCost = resourcesCost.get(coffeeType).get("water");
        int milkCost = resourcesCost.get(coffeeType).get("milk");
        int coffeeBeansCost = resourcesCost.get(coffeeType).get("coffeeBeans");
        int cost = resourcesCost.get(coffeeType).get("cost");

        if (getMaxNumberOfCoffee(waterCost, milkCost, coffeeBeansCost) >= 1) {
            System.out.println("I have enough resources, making you a coffee!");
            water -= waterCost;
            milk -= milkCost;
            coffeeBeans -= coffeeBeansCost;
            cups--;
            money += cost;
        }
    }

    public static void setResources(Scanner scanner) {
        System.out.println("Write how many ml of water do you want to add: ");
        System.out.print("> ");
        water += scanner.nextInt();

        System.out.println("Write how many ml of milk do you want to add: ");
        System.out.print("> ");
        milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans do you want to add: ");
        System.out.print("> ");
        coffeeBeans += scanner.nextInt();

        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        System.out.print("> ");
        cups += scanner.nextInt();
    }

    public static void withdrawMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }


    public static void main(String[] args) {

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            System.out.print("> ");
            String action = new Scanner(System.in).nextLine();

            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                    System.out.print("> ");
                    String coffeeType = new Scanner(System.in).nextLine();

                    switch (coffeeType) {
                        case "1":
                            makeCoffee("espresso");
                            break;
                        case "2":
                            makeCoffee("latte");
                            break;
                        case "3":
                            makeCoffee("cappuccino");
                            break;
                        case "back":
                            continue;
                    }
                    break;

                case "fill":
                    setResources(new Scanner(System.in));
                    break;

                case "take":
                    withdrawMoney();
                    break;

                case "remaining":
                    getStatus();
                    break;

                case "exit":
                    return;
            }
        }
    }
}
