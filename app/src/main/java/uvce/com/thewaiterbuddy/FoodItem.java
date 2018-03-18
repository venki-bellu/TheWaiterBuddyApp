package uvce.com.thewaiterbuddy;

public class FoodItem {
    private String name;
    private double cost;
    private boolean selected;

    public FoodItem(String _name, double _cost) {
        name = _name;
        cost = _cost;
        selected = false;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }
}
