package uvce.com.thewaiterbuddy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogInActivity extends AppCompatActivity {
    Spinner tableno;
    Button login;
    EditText password, userid;
    String[] a, b;
    int count1,count2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final Spinner tableno = (Spinner) findViewById(R.id.spinner_tableno);
        final String[] plants = new String[]{
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
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, plantsList) {

            //making first item non-clickable
            @Override
            public boolean isEnabled(int position) {


                if (position == 0)
                    return false;

                else
                    return true;

            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLUE);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        tableno.setAdapter(spinnerArrayAdapter);



        //login
        login = (Button) findViewById(R.id.login_button);
        userid = (EditText) findViewById(R.id.waiter_id);
        password = (EditText) findViewById(R.id.password);
        a = new String[]{"12345", "23456"};
        b = new String[]{"wait12345", "wait23456"};

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count2=0;

                for (int i = 0; i < 2; i++)
                {
                    if (userid.getText().toString().equals(a[i]))
                    {
                        count2++;
                        for (int j = 0; j < 2; j++)
                        {
                            if (password.getText().toString().equals(b[j]))
                            {
                                count2++;
                            }
                        }
                    }
                }
                if (count2 == 2 )
                {
                    if(tableno.getSelectedItemPosition()>0)
                    {
                        Intent i = new Intent(LogInActivity.this, MenuActivity.class);
                        Bundle bundle = new Bundle();
                        i.putExtra("tableNum", plants[tableno.getSelectedItemPosition()]);
                        startActivity(i);
                        userid.setText("");
                        password.setText("");
                        tableno.setSelection(0);
                        finish();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Select a Table no.!!",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials!!", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }



    //show password
    public void showPass(View view) {
        password.getText().toString();
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId())
        {
            case R.id.show_password:
                if (checked)
                {
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                    password.setSelection(password.getText().length());
                }
                else
                    {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.getText().length());
                }
                break;
        }
    }

}

    //This is the method to Bypass the log in screen. To be removed in production
    /*public void logIN(View V) {
        startActivity(new Intent(LogInActivity.this, MenuActivity.class));
    }*/



