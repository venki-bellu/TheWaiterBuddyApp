package uvce.com.thewaiterbuddy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuItemHolder> {
    private final static String TAG = "APPATHON";
    private ArrayList<FoodItem> foodItems;
    private Context context;

    MenuAdapter(ArrayList<FoodItem> _foodItems, Context c) {
        foodItems = _foodItems;
        context = c;
    }

    @Override
    public MenuAdapter.MenuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);

        return new MenuItemHolder(inflatedView);
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    @Override
    public void onBindViewHolder(MenuItemHolder holder, int position) {
        final FoodItem foodItem = foodItems.get(position);
        holder.bindNewsInfo(foodItem, position);
    }

    static class MenuItemHolder extends RecyclerView.ViewHolder {
        FoodItem foodItem;
        private TextView foodName, foodPrice, foodQuantity;
        private Button upButton, downButton;
        private CheckBox checkBox;
        private LinearLayout quantityLayout;
        private int position;
        private View.OnClickListener onUpButtonClicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = getInt(foodQuantity);
                if (quantity < 10) ++quantity;
                foodQuantity.setText(String.valueOf(quantity));

                Log.i(TAG, "Up button clicked, increasing: " + position
                        + " to: " + quantity);

                MenuActivity.updateQuantity(quantity, position);
            }
        };
        private View.OnClickListener onDownButtonClicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = getInt(foodQuantity);
                if (quantity > 1) --quantity;
                foodQuantity.setText(String.valueOf(quantity));

                Log.i(TAG, "Down button clicked, decreasing: " + position
                        + " to: " + quantity);

                MenuActivity.updateQuantity(quantity, position);
            }
        };
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener =
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        foodItem.setSelected(b);

                        if (b) {
                            Log.i(TAG, "Checked, adding food item at position: "
                                    + position);

                            quantityLayout.setVisibility(View.VISIBLE);
                            MenuActivity.addFoodItem(foodItem, position);
                        } else {
                            Log.i(TAG, "UnChecked, removing food item at position: "
                                    + position);

                            quantityLayout.setVisibility(View.GONE);
                            foodQuantity.setText("1");
                            MenuActivity.deleteFoodItem(position);
                        }
                    }
                };

        MenuItemHolder(View itemView) {
            super(itemView);


            foodName = itemView.findViewById(R.id.food_name);
            foodPrice = itemView.findViewById(R.id.food_price);
            foodQuantity = itemView.findViewById(R.id.food_quantity);
            checkBox = itemView.findViewById(R.id.food_check);
            upButton = itemView.findViewById(R.id.up_button);
            downButton = itemView.findViewById(R.id.down_button);
            quantityLayout = itemView.findViewById(R.id.food_quantity_view);
        }

        void bindNewsInfo(FoodItem _foodItem, int _position) {
            position = _position;
            foodItem = _foodItem;

            foodName.setText(foodItem.getName());
            foodPrice.setText(String.valueOf(foodItem.getCost()));

            checkBox.setOnCheckedChangeListener(null);
            checkBox.setSelected(foodItem.isSelected());

            checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
            downButton.setOnClickListener(onDownButtonClicked);
            upButton.setOnClickListener(onUpButtonClicked);
        }

        private int getInt(TextView foodQuantity) {
            String quantity = foodQuantity.getText().toString();
            return Integer.parseInt(quantity);
        }
    }
}
