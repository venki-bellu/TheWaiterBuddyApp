package uvce.com.thewaiterbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    public static final String TAG = "APPATHON";
    private static ArrayList<CartItem> cartItems = new ArrayList<>();
    private ArrayList<FoodItem> foodItems;
    private FoodListAdapter listAdapter;

    public static void addFoodItem(FoodItem foodItem, int id) {
        cartItems.add(new CartItem(foodItem, 1, id));

        Log.i(TAG, "Added food item at position: " + String.valueOf(id));
    }

    public static void updateQuantity(int quantity, int id) {
        for (int i = 0; i < cartItems.size(); ++i) {
            if (cartItems.get(i).getId() == id) {
                cartItems.get(i).setQuantity(quantity);
                break;
            }
        }

        Log.i(TAG, "Updated cart item quantity at: " + String.valueOf(id)
                + " to: " + String.valueOf(quantity));
    }

    public static void deleteFoodItem(int id) {
        for (int i = 0; i < cartItems.size(); ++i) {
            if (cartItems.get(i).getId() == id) {
                cartItems.remove(i);
                break;
            }
        }

        Log.i(TAG, "Deleted cart item at: " + String.valueOf(id));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Log.i(TAG, "Menu activity started");

        cartItems.clear();

        foodItems = new ArrayList<>();
        populateFoodItems();

        Log.i(TAG, "Food item list populated");

        listAdapter = new FoodListAdapter(getApplicationContext(), R.layout.food_item, foodItems);
        ListView listView = findViewById(R.id.food_list);
        listView.setAdapter(listAdapter);

        Log.i(TAG, "List view adapter set");
    }

    private void populateFoodItems() {
        foodItems.add(new FoodItem("Name 1", 100.00));
        foodItems.add(new FoodItem("Name 2", 150.00));
        foodItems.add(new FoodItem("Name 3", 50.00));
        foodItems.add(new FoodItem("Name 4", 200.00));
        foodItems.add(new FoodItem("Name 5", 130.00));
        foodItems.add(new FoodItem("Name 6", 110.00));
        foodItems.add(new FoodItem("Name 7", 90.00));
        foodItems.add(new FoodItem("Name 8", 80.00));
        foodItems.add(new FoodItem("Name 9", 123.00));
        foodItems.add(new FoodItem("Name 10", 890.00));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.checkout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "Checking out");

        Intent checkoutIntent = new Intent(MenuActivity.this, CheckoutActivity.class);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(CartItem.TYPE, cartItems);
        checkoutIntent.putExtras(bundle);

        startActivity(checkoutIntent);

        return super.onOptionsItemSelected(item);
    }
}
