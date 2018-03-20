package uvce.com.thewaiterbuddy;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    public static final String TAG = "APPATHON";
    private String tableNum;
    private static ArrayList<CartItem> cartItems = new ArrayList<>();
    private ArrayList<FoodItem> foodItems;
    private MenuAdapter menuAdapter;

    public static void addFoodItem(FoodItem foodItem, int id) {
        cartItems.add(new CartItem(foodItem, 1, id));

        Log.i(TAG, "Added food item at position: " + id);
    }

    public static void updateQuantity(int quantity, int id) {
        for (int i = 0; i < cartItems.size(); ++i) {
            if (cartItems.get(i).getId() == id) {
                cartItems.get(i).setQuantity(quantity);
                break;
            }
        }

        Log.i(TAG, "Updated cart item quantity at: " + id + " to: " + quantity);
    }

    public static void deleteFoodItem(int id) {
        for (int i = 0; i < cartItems.size(); ++i) {
            if (cartItems.get(i).getId() == id) {
                cartItems.remove(i);
                break;
            }
        }

        Log.i(TAG, "Deleted cart item at: " + id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tableNum = getIntent().getExtras().getString("tableNum");
        Log.i(TAG, "Menu activity started");

        cartItems.clear();

        foodItems = new ArrayList<>();
        populateFoodItems();
        Log.i(TAG, "Food item list populated");

        menuAdapter = new MenuAdapter(foodItems, getApplicationContext());
        initRecyclerView();
        Log.i(TAG, "Recycler view adapter set");
    }

    private void initRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.food_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);

        int spacing = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacing));
        mRecyclerView.setAdapter(menuAdapter);
    }

    private void populateFoodItems() {
        foodItems.add(new FoodItem("Margherita", 100.00));
        foodItems.add(new FoodItem("Farm House", 150.00));
        foodItems.add(new FoodItem("Extravaganza", 150.00));
        foodItems.add(new FoodItem("Veg Paradise", 200.00));
        foodItems.add(new FoodItem("Fresh Veggie", 130.00));
        foodItems.add(new FoodItem("Paneer Makhni", 110.00));
        foodItems.add(new FoodItem("Cheese N Corn", 190.00));
        foodItems.add(new FoodItem("Peppy Paneer", 180.00));
        foodItems.add(new FoodItem("Mexican Wave", 120.00));
        foodItems.add(new FoodItem("5 Pepper", 290.00));
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
        checkoutIntent.putExtra("tableNum", tableNum);
        bundle.putParcelableArrayList(CartItem.TYPE, cartItems);
        checkoutIntent.putExtras(bundle);

        startActivity(checkoutIntent);

        return super.onOptionsItemSelected(item);
    }

    private class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        SpacesItemDecoration(int spacing) {
            this.space = spacing;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.bottom = space;

            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}
