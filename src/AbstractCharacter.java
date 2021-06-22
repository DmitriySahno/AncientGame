import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class AbstractCharacter {
    private final String name;
    private float health; //здоровье
    private final float initHealth;
    private int power; //урон
    private int dexterity; //ловкость
    private static final int maxDexterity = 100;
    private int experience = 0; //опыт
    private int coins; //очки
    private boolean isDie = false;

    private static final Map<Integer, Integer> levels = new HashMap<>();
    private int currentLevel;

    static {
        levels.put(1, 50);
        levels.put(2, 100);
        levels.put(3, 200);
        levels.put(4, 400);
        levels.put(5, 800);
        levels.put(6, 1600);
        levels.put(7, 3200);
        levels.put(8, 6400);
        levels.put(9, 12800);
        levels.put(10, 25600);
    }

    public AbstractCharacter(String name, float health, int power, int dexterity, int experience, int coins) {
        this.name = name;
        this.health = health;
        this.initHealth = health;
        this.power = power;
        this.dexterity = dexterity;
        if (dexterity > maxDexterity) {
            this.dexterity = maxDexterity;
            System.out.println("Max dexterity value is " + maxDexterity +
                    "\nValue of dexterity decreased to " + maxDexterity);
        }
        this.experience = experience;
        this.coins = coins;
        this.currentLevel = 0;
    }

    public AbstractCharacter(String name, float health, int power, int dexterity) {
        this.name = name;
        this.health = health;
        this.initHealth = health;
        this.power = power;
        this.dexterity = dexterity;
    }

    public boolean attack(AbstractCharacter p) {

        Random random = new Random();
        int calcPower = this.power;
        double r = Math.random();
        if (r < (1 - (float) dexterity / maxDexterity)) { //hitting possibility
            System.err.println(this + " промахнулся по " + p);
            return false;
        }

        if (random.nextInt(3) + 1 == 3) //power x2
            calcPower *= 2;

        p.health -= calcPower;
        System.out.println(this + " ударил " + p + " -" + calcPower);

        if (p.health <= 0) {
            experience += p.initHealth+p.power;
            coins += 20;
            updateLevel();
            System.out.println(p + " был убит " + this);
            p.isDie = true;
        }
        return true;
    }

    public void die() {
        System.out.println("The " + name + " was die");
    }

    public String getName() {
        return this.name;
    }

    public float getHealth() {
        return this.health;
    }

    public int getPower() {
        return this.power;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getCoins() {
        return this.coins;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void replenishHealth(float health) {
        float restoredHealth = (health + this.health) > initHealth ? initHealth - this.health : health;
        this.health += restoredHealth;
        System.out.println("health: +"+restoredHealth);
    }

    public boolean isDie() {
        return isDie;
    }

    private void updateLevel() {
        for (Map.Entry<Integer, Integer> level : levels.entrySet()) {
            int currentExp = this.getExperience();
            if (level.getValue() < currentExp) currentLevel = level.getKey();
        }
    }

    @Override
    public String toString() {
        return this.getName() + "(hp: " + this.getHealth() + "; pow:" + this.getPower() + ")";
    }

    protected int getLevel(){
        return currentLevel;
    }
}