import java.util.Random;

public abstract class AbstractCharacter {
    private String name;
    private float health = 100.0f; //здоровье
    private int power = 0; //урон
    private int dexterity = 1; //ловкость
    private static final int maxDexterity = 100;
    private int experience = 0; //опыт
    private int coins; //очки
    private boolean isDie = false;

    public AbstractCharacter(String name, float health, int power, int dexterity, int experience, int coins) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.dexterity = dexterity;
        if (dexterity > maxDexterity) {
            this.dexterity = maxDexterity;
            System.out.println("Max dexterity value is " + maxDexterity +
                    "\nValue of dexterity decreased to " + maxDexterity);
        }
        this.experience = experience;
        this.coins = coins;
    }

    public AbstractCharacter(String name, float health, int power, int dexterity) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.dexterity = dexterity;
    }

    public void attack(AbstractCharacter p) {

        Random random = new Random();
        int calcPower = this.power;
        double r = Math.random();
        if (r < (1-(float)dexterity / maxDexterity)) { //hitting possibility
            System.err.println(this.name + " was missing on " + p.name);
            return;
        }

        if (dexterity + random.nextInt(3)+1 == dexterity + 3) //power x2
            calcPower *= 2;

        p.health -= calcPower;
        System.out.println(this.name+" hit the "+p.name+" -"+calcPower);

        if (p.health <= 0) {
            p.isDie = true;
            System.out.println(p.name + " was killed by " + this.name);
        }
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

    public boolean isDie() {
        return isDie;
    }

    @Override
    public String toString() {
        return name+"("+health+";"+power+";"+coins+")";
    }
}