import java.util.ArrayList;
import java.util.Random;

public class BattleThread extends Thread {
    private final Player player;

    public BattleThread(Player player) {
        this.player = player;
    }

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