package uvce.com.thewaiterbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LogInActivity extends AppCompatActivity {
    Spinner tableno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Spinner tableno = (Spinner) findViewById(R.id.spinner_tableno);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(LogInActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.table_no));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tableno.setAdapter(myAdapter);


    }

    //This is the method to Bypass the log in screen. To be removed in production
    public void bypass(View V) {
        startActivity(new Intent(LogInActivity.this, MenuActivity.class));
    }
}