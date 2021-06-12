import java.util.HashMap;
import java.util.Map;

public class Player extends AbstractCharacter {


    public Player(String name, float health, int power, int dexterity, int experience, int coins) {
        super(name, health, power, dexterity, experience, coins);
    }

    public boolean buyOne(Trader trader, String product) {
        if (trader.trade(product)) {
            this.replenishHealth(20);
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return this.getName() + "(hp: " + this.getHealth() + "; pow:" + this.getPower() + "; coin: " + this.getCoins() + "; lvl: " +this.getLevel()+" ("+ this.getExperience() + ")"+ ")";
    }

}
