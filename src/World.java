import java.util.*;

public class World {
    enum Location {
        CITY("Город"),
        TRADER("Торговец"),
        FOREST("Лес");

        private String description;
        Location(String description){
            this.description=description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    private static Location location = Location.CITY;


    private static Player player;

    public static void main(String[] args) {

        System.out.println("Введите имя персонажа:");

        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        player = new Player(playerName, 100, 30, 80, 0, 0);


        Map<String, Integer> tradersBag = new HashMap<>();
        tradersBag.put("potion", 5);
        Trader trader = new Trader("Trader", tradersBag);


        System.out.println(playerName + ", добро пожаловать!");

        while (true) {

            String answer = "0";
            switch (location) {
                case CITY, FOREST -> {
                    System.out.println("----------------------------------------");
                    System.out.println(location+": "+"""
                            Куда Вы хотите пойти?
                            1. К торговцу
                            2. В тёмный лес
                            3. На выход""");
                    String intermediateAnswer = scanner.nextLine();
                    switch (intermediateAnswer) {
                        case "1" -> answer = "2"; //торговец
                        case "2" -> answer = "3"; //темный лес
                        case "3" -> answer = "0"; //выход
                    }
                }
                case TRADER -> {
                    System.out.println("----------------------------------------");
                    System.out.println(location+": "+"""
                            Куда Вы хотите пойти?
                            1. Вернуться в город
                            2. Продолжить торговлю
                            3. В тёмный лес""");
                    String intermediateAnswer = scanner.nextLine();
                    switch (intermediateAnswer) {
                        case "1" -> answer = "1"; //город
                        case "2" -> answer = "2"; //торговец
                        case "3" -> answer = "3"; //темный лес
                    }
                }
            }


            switch (answer) {
                //город
                case "1" -> location = Location.CITY;
                //торговец
                case "2" -> performTrading(trader);
                //темный лес
                case "3" -> {
                    if (!isBattleWin()) {
                        scanner.close();
                        return;
                    }
                }
                //выход
                default -> {
                    System.out.println("Вы покидаете игру");
                    scanner.close();
                    return;
                }
            }
        }


    }

    private static boolean isBattleWin() {
        location = Location.FOREST;
        BattleThread battleThread = new BattleThread();
        battleThread.start();
        try {
            battleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (player.isDie()) {
            System.out.println("----------------------------------------");
            System.out.println(location+": "+"""
                    К сожалению, Вы проиграли.
                    Не расстраивайтесь!
                    Повезет в следующий раз!""");
            return false;
        } else {
            System.out.println("----------------------------------------");
            System.out.println(location+": "+"""
                    Поздравляем, Вы победили монстра!""");
            System.out.println("Ваше здоровье:" + player.getHealth());

        }
        return true;
    }

    private static void performTrading(Trader trader) {
        location = Location.TRADER;
        System.out.println("----------------------------------------");
        System.out.println("Добро пожаловать к " + trader.getName());
        System.out.println(location+": Что желаете?");
        int counter = 1;
        Map<String, Integer> products = trader.getProducts();
        for (Map.Entry<String, Integer> product : products.entrySet()) {
            System.out.println(counter++ + ". " + product.getKey() + ": " + product.getValue());
        }
        System.out.println("0. Вернуться");
        int productNum = new Scanner(System.in).nextInt();
        if (productNum == 0) return;
        String productName = products.keySet().toArray()[productNum - 1].toString();
        System.out.println(player.buyOne(trader, productName) ? "Ваши показатели: " + player : "Недостаточно зелья");
    }

    static class BattleThread extends Thread {
        @Override
        public void run() {
            ArrayList<AbstractCharacter> enemies = new ArrayList<>();
            enemies.add(new Goblin("Goblin", 300, 40, 10));
            enemies.add(new Skeleton("Skeleton", 150, 40, 15));

            AbstractCharacter enemy = enemies.get(new Random().nextInt(enemies.size()));

            while (true) {
                player.attack(enemy);
                if (enemy.isDie()) break;
                enemy.attack(player);
                if (player.isDie()) break;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
