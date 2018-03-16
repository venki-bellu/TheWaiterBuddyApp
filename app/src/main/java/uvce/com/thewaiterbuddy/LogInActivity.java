package uvce.com.thewaiterbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    //This is the method to Bypass the log in screen. To be removed in production
   public void bypass(View V)
    {
        startActivity(new Intent(LogInActivity.this, MenuActivity.class)); 
    }
}
