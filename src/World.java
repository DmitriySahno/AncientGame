import java.util.ArrayList;
import java.util.Iterator;

public class World {

    public static void main(String[] args) throws InterruptedException {

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Стрелок", 100, 30, 100, 0, 0));
        players.add(new Player("Танк", 150, 40, 45, 0, 0));
        players.add(new Player("Маг", 75, 50, 60, 0, 0));

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy("Ogr", 500, 50, 10));
        enemies.add(new Enemy("Dragon", 750, 75, 15));

        Iterator<Player> playerIterator = players.iterator();
        Iterator<Enemy> enemyIterator = enemies.iterator();

        Player player = playerIterator.next();
        Enemy enemy = enemyIterator.next();

        while (true) {
            player.attack(enemy);
            if (enemy.isDie()) {
                enemyIterator.remove();
                if (enemyIterator.hasNext()) enemy = enemyIterator.next();
                else break;
            }
            enemy.attack(player);
            if (player.isDie()) {
                playerIterator.remove();
                if (playerIterator.hasNext()) player = playerIterator.next();
                else break;
            }
            Thread.sleep(500);
        }

/*        System.out.print(""+
                ""+
                ""+
                "");
        Scanner scanner = new Scanner(System.in);
        String command;
        command = scanner.next();
        while (!command.equals("exit")) {

            command = scanner.next();
        }*/


    }

}
