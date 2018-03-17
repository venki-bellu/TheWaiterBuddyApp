package uvce.com.thewaiterbuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {
    private final static String TAG = "APPATHON";
    private ArrayList<CartItem> checkoutItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkoutItems = getIntent().getParcelableArrayListExtra(CartItem.TYPE);

        for (CartItem item : checkoutItems) {
            Log.i(TAG, item.getName() + ", "
                    + String.valueOf(item.getQuantity()));
        }
    }
}
