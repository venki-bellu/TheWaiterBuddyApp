package uvce.com.thewaiterbuddy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodListAdapter extends ArrayAdapter<FoodItem> {
    private final String TAG = "APPATHON";

    public FoodListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FoodItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.food_item, null);
        }

        final FoodItem foodItem = getItem(position);

        if (foodItem != null) {
            TextView foodName = view.findViewById(R.id.food_name);
            TextView foodPrice = view.findViewById(R.id.food_price);
            CheckBox checkBox = view.findViewById(R.id.food_check);

            foodName.setText(foodItem.getName());
            foodPrice.setText(String.valueOf(foodItem.getCost()));

            final LinearLayout quantityLayout = view.findViewById(R.id.food_quantity_view);
            Button upButton = view.findViewById(R.id.up_button);
            Button downButton = view.findViewById(R.id.down_button);
            final TextView foodQuantity = view.findViewById(R.id.food_quantity);

            upButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int quantity = getInt(foodQuantity);
                    if (quantity < 10) ++quantity;
                    foodQuantity.setText(String.valueOf(quantity));

                    Log.i(TAG, "Up button clicked, increasing: " + String.valueOf(position)
                            + " to: " + String.valueOf(quantity));

                    MenuActivity.updateQuantity(quantity, position);
                }
            });

            downButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int quantity = getInt(foodQuantity);
                    if (quantity > 1) --quantity;
                    foodQuantity.setText(String.valueOf(quantity));

                    Log.i(TAG, "Down button clicked, decreasing: " + String.valueOf(position)
                            + " to: " + String.valueOf(quantity));

                    MenuActivity.updateQuantity(quantity, position);
                }
            });

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        Log.i(TAG, "Checked, adding food item at position: "
                                + String.valueOf(position));

                        quantityLayout.setVisibility(View.VISIBLE);
                        MenuActivity.addFoodItem(foodItem, position);
                    } else {
                        Log.i(TAG, "Un Checked, deleting food item at position: "
                                + String.valueOf(position));

                        quantityLayout.setVisibility(View.GONE);
                        MenuActivity.deleteFoodItem(position);
                    }
                }
            });
        }

        return view;
    }

    private int getInt(TextView foodQuantity) {
        String quantity = foodQuantity.getText().toString();
        return Integer.parseInt(quantity);
    }
}
