package us.getspot.v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.markupartist.android.widget.ActionBar;

public class chooseSpot extends Activity {
	
	ActionBar actionBar;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_spot);
                
        // actionBar setup
        actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle("chooseSpot");
		
		// view Spot history
		Intent historyIntent = new Intent(chooseSpot.this, history.class);
		final ActionBar.Action goToHistory = new ActionBar.IntentAction(this, historyIntent, R.drawable.history_40px);
		actionBar.addAction(goToHistory);
		
		// create new Spot
		Intent newSpotIntent = new Intent(chooseSpot.this, theSpot.class);
		final ActionBar.Action goToNewSpot = new ActionBar.IntentAction(this, newSpotIntent, R.drawable.plus);
		actionBar.addAction(goToNewSpot);
		
		// log in and out
		Intent loginIntent = new Intent(chooseSpot.this, login.class);
		final ActionBar.Action goToLogin = new ActionBar.IntentAction(this, loginIntent, R.drawable.profile);
		actionBar.addAction(goToLogin);
		

    }
}