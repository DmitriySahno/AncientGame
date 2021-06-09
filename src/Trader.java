public class Trader {
    private final String name;
    private int potionCount;
    public Trader(String name, int potionCount){
        this.name = name;
        this.potionCount = potionCount;
    }

    public boolean trade() {
        if (potionCount>0) {
            potionCount--;
            return true;
        } else return false;
    }

    public String getName() {
        return name;
    }

    public int getPotionCount() {
        return potionCount;
    }
}
