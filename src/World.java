import java.util.*;

public class World {
    enum Location {
        CITY("Город"),
        TRADER("Торговец"),
        FOREST("Лес");

        private String description;

        Location(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    private static Location location = Location.CITY;
    private static Trader trader;

    private static Player player;

    public static void main(String[] args) {

        System.out.println("Введите имя персонажа:");

        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        player = new Player(playerName, 100, 30, 80, 0, 0);


        Map<String, Integer> tradersBag = new HashMap<>();
        tradersBag.put("potion", 5);
        trader = new Trader("Trader", tradersBag);


        System.out.println(playerName + ", добро пожаловать!");

        String answer = "0";
        while (!answer.equals("-"))
            answer = runAction(scanner);
    }

    private static String runAction(Scanner scanner) {
        String answer = "0";
        switch (location) {
            case CITY, FOREST -> {
                System.out.println("----------------------------------------");
                System.out.println(location + ": " + """
                        Куда Вы хотите пойти?
                        1. К торговцу
                        2. В тёмный лес
                        3. На выход""");
                String intermediateAnswer = scanner.nextLine();
                answer = switch (intermediateAnswer) {
                    case "1" -> "2"; //торговец
                    case "2" -> "3"; //темный лес
                    case "3" -> "0"; //выход
                    default -> "-";
                };
            }
            case TRADER -> {
                System.out.println("----------------------------------------");
                System.out.println(location + ": " + """
                        Куда Вы хотите пойти?
                        1. Вернуться в город
                        2. Продолжить торговлю
                        3. В тёмный лес""");
                String intermediateAnswer = scanner.nextLine();
                answer = switch (intermediateAnswer) {
                    case "1" -> "1"; //город
                    case "2" -> "2"; //торговец
                    case "3" -> "3"; //темный лес
                    default -> "-";
                };
            }
        }

        switch (answer) {
            //город
            case "1" -> location = Location.CITY;
            //торговец
            case "2" -> {
                location = Location.TRADER;
                String productName = trader.trade();
                if (!productName.equals("-"))
                    System.out.println(player.buyOne(trader, productName) ? "Ваши показатели: " + player : "Недостаточно зелья");
            }
            //темный лес
            case "3" -> {
                location = Location.FOREST;
                BattleThread battleThread = new BattleThread(player);
                battleThread.start();
                try {
                    battleThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (player.isDie()) {
                    scanner.close();
                    answer = "-";
                }
            }
            //выход
            default -> {
                System.out.println("Вы покидаете игру");
                scanner.close();
                answer = "-";
            }
        }

        return answer;
    }



}
