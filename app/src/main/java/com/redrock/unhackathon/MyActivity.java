package com.redrock.unhackathon;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

/**
 * Primary activity for the application.
 */
public class MyActivity extends Activity {

    /**
     * Primary surface view for the game.
     */
    GameSurfaceView gameSurfaceView;

    /**
     * OnCreate method.
     *
     * @param savedInstanceState
     *          Bundle which initializes the game with a saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Call the superclass constructor.
        super.onCreate(savedInstanceState);

        // Remove the title and make the app fullscreen.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create the SurfaceView.
        gameSurfaceView = new GameSurfaceView(getApplicationContext());
        setContentView(R.layout.activity_my);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
