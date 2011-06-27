package us.getspot.v2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
		Intent newSpotIntent = new Intent(chooseSpot.this, createSpot.class);
		final ActionBar.Action goToNewSpot = new ActionBar.IntentAction(this, newSpotIntent, R.drawable.plus);
		actionBar.addAction(goToNewSpot);
		
		// log in and out
		actionBar.addAction(new loginAction());
		

    }
    private class loginAction implements ActionBar.Action {

		//@Override
		public void performAction(View view) {

			final CharSequence[] choices = {"Login", "Logout"};
			AlertDialog.Builder builder = new AlertDialog.Builder(chooseSpot.this);
			builder.setTitle("Choose your action");
			builder.setItems(choices, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int item) {
					SharedPreferences sharedPreferences = getSharedPreferences(Utils.PREFS_NAME, MODE_PRIVATE);

					if(item == 0){
						// choosing to login
						final AlertDialog.Builder loginBuilder = new AlertDialog.Builder(chooseSpot.this);
						loginBuilder.setTitle("Login");
						
						loginBuilder.setTitle("Username");
									
						// set up an EditText to get the comment
						final EditText input_username = new EditText(chooseSpot.this);
						loginBuilder.setView(input_username);
									
						loginBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
										
						@Override
						public void onClick(DialogInterface dialog, int which) {
							String userName = input_username.getText().toString();										}
							});
									
						loginBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
										
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							}
						});
									
						loginBuilder.show(); 
					}else{
							Toast.makeText(chooseSpot.this, "Logout Successful", Toast.LENGTH_LONG).show();
					}
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		}
		@Override
		public int getDrawable() {
			return R.drawable.profile;
		}
    }
}