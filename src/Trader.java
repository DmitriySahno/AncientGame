import java.util.Map;

public class Trader {

    private final String name;
    private Map<String, Integer> bag;

    public Trader(String name, Map<String, Integer> bag) {
        this.name = name;
        this.bag = bag;
    }

    public boolean trade(String productName) {
        int count = getProductCount(productName);
        if (count > 0) {
            bag.put(productName, --count);
            return true;
        } else return false;
    }

    public String getName() {
        return name;
    }

    public int getProductCount(String productName) {
        return bag.get(productName);
    }

    public Map<String, Integer> getProducts() {
        return bag;
    }

}
