import java.util.Map;
import java.util.Scanner;

public class Trader {

    private final String name;
    private final Map<String, Integer> bag;

    public Trader(String name, Map<String, Integer> bag) {
        this.name = name;
        this.bag = bag;
    }

    public boolean sell(String productName) {
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

    public String trade() {
        System.out.println("----------------------------------------");
        System.out.println("Добро пожаловать к " + name);
        System.out.println(name + ": Что желаете?");
        int counter = 1;
        for (Map.Entry<String, Integer> product : bag.entrySet()) {
            System.out.println(counter++ + ". " + product.getKey() + ": " + product.getValue());
        }
        System.out.println("0. Вернуться");
        int productNum = new Scanner(System.in).nextInt();
        if (productNum == 0) return "-";

        return bag.keySet().toArray()[productNum - 1].toString();
    }
}
