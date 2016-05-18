package assignment.jorge.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import assignment.jorge.view.list.ItemRecyclerActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runApp();
    }

    private void runApp() {
        startActivity(new Intent(this, ItemRecyclerActivity.class));
        finish();
    }
}
