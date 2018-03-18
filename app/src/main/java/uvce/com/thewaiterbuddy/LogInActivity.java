package uvce.com.thewaiterbuddy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogInActivity extends AppCompatActivity {
    Spinner tableno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final Spinner tableno = (Spinner) findViewById(R.id.spinner_tableno);
        String[] plants = new String[]{
                "Select a Table no.",
                "Table no.1",
                "Table no.2",
                "Table no.3",
                "Table no.4",
                "Table no.5",
                "Table no.6",
                "Table no.7",
                "Table no.8",
                "Table no.9",
                "Table no.10",

        };

        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plantsList){
            @Override
            public boolean isEnabled(int position){

                //making first item non-clickable
                if(position == 0)
                    return false;

                else
                    return true;

            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLUE);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        tableno.setAdapter(spinnerArrayAdapter);

        tableno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText
                        (getApplicationContext(), "Select a Table no.", Toast.LENGTH_SHORT)
                        .show();

            }
        });
    }


    //This is the method to Bypass the log in screen. To be removed in production
    public void bypass(View V) {
        startActivity(new Intent(LogInActivity.this, MenuActivity.class));
    }
}