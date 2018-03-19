package uvce.com.thewaiterbuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {
    private final static String TAG = "APPATHON";
    private ArrayList<CartItem> checkoutItems;
    private ArrayList<String> foodItemNames=new ArrayList<>();
    private ArrayList<Double> foodItemsCost=new ArrayList<>();
    private ArrayList<Integer> foodItemsQuantity=new ArrayList<>();
    private ArrayList<Double> foodItemsTotalCost=new ArrayList<>();

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkoutItems = getIntent().getParcelableArrayListExtra(CartItem.TYPE);

        //Getting the data of all the checkout items
        for(int i=0;i<checkoutItems.size();i++)
        {
            foodItemNames.add(checkoutItems.get(i).getName());
            foodItemsCost.add(checkoutItems.get(i).getCost());
            foodItemsQuantity.add(checkoutItems.get(i).getQuantity());
            foodItemsTotalCost.add(foodItemsCost.get(i)*foodItemsQuantity.get(i));
            Log.i(TAG, foodItemNames.get(i));
            Log.i(TAG,""+ foodItemsCost.get(i));  //Added to a string to parse into String from double
            Log.i(TAG,""+foodItemsQuantity.get(i));
            Log.i(TAG,""+foodItemsTotalCost.get(i));

        }

        MyListAdapter adapter=new MyListAdapter(this,foodItemNames,foodItemsQuantity,foodItemsTotalCost);
        list=findViewById(R.id.list);
        list.setAdapter(adapter);


    }
}
