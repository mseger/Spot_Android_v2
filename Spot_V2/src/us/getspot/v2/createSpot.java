package us.getspot.v2;

import android.app.Activity;
import android.os.Bundle;

import com.markupartist.android.widget.ActionBar;

public class createSpot extends Activity{

	ActionBar actionBar;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_spot);
        
        // actionBar setup
        actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle("createSpot");
    }
}
