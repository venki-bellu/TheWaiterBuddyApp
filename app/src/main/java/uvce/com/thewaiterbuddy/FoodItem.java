package uvce.com.thewaiterbuddy;

public class FoodItem {
    private String name;
    private double cost;

    public FoodItem(String _name, double _cost) {
        name = _name;
        cost = _cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
