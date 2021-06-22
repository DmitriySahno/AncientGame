public class Player extends AbstractCharacter {


    public Player(String name, float health, int power, int dexterity, int experience, int coins) {
        super(name, health, power, dexterity, experience, coins);
    }

    public boolean buyOne(Trader trader, String product) {
        if (trader.sell(product)) {
            this.replenishHealth(20);
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return this.getName() + "(hp: " + this.getHealth() + "; pow:" + this.getPower() + "; coin: " + this.getCoins() + "; lvl: " +this.getLevel()+" ("+ this.getExperience() + ")"+ ")";
    }

    @Override
    public boolean attack(AbstractCharacter p) {
        super.attack(p);
        if (!p.isDie()&&this.isDie()) {
            System.out.println("----------------------------------------");
            System.out.println("""
                    К сожалению, Вы проиграли.
                    Не расстраивайтесь!
                    Повезет в следующий раз!""");
            return false;
        } else if (!this.isDie()&&p.isDie()) {
            System.out.println("----------------------------------------");
            System.out.println("""
                    Поздравляем, Вы победили монстра!""");
            System.out.println("Ваше здоровье:" + this.getHealth());
        }
        return true;
    }
}
