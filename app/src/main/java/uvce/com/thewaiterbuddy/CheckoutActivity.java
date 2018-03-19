package uvce.com.thewaiterbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {
    private final static String TAG = "APPATHON";
    private ArrayList<CartItem> checkoutItems;
    private ArrayList<String> foodItemNames=new ArrayList<>();
    private ArrayList<Double> foodItemsCost=new ArrayList<>();
    private ArrayList<Integer> foodItemsQuantity=new ArrayList<>();
    private ArrayList<Double> foodItemsTotalCost=new ArrayList<>();
    private double totalPayableAmount=0;

    TextView totalPayable;
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
            totalPayableAmount+=foodItemsTotalCost.get(i);
            Log.i(TAG, foodItemNames.get(i));
            Log.i(TAG,""+ foodItemsCost.get(i));  //Added to a string to parse into String from double
            Log.i(TAG,""+foodItemsQuantity.get(i));
            Log.i(TAG,""+foodItemsTotalCost.get(i));

        }

        MyListAdapter adapter=new MyListAdapter(this,foodItemNames,foodItemsQuantity,foodItemsTotalCost);
        list=findViewById(R.id.list);
        list.setAdapter(adapter);

        totalPayable=findViewById(R.id.totalPayable);
        totalPayable.setText("Rs."+totalPayableAmount);
    }

    public void confirmOrder(View v)
    {
        startActivity(new Intent(CheckoutActivity.this,OrderPlacedActivity.class));
        finish();
    }

    public void cancelOrder(View v)
    {
        Toast.makeText(this,"Order cancelled. You can modify your order",Toast.LENGTH_SHORT).show();
        finish();
    }
}
