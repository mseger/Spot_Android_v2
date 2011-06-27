package us.getspot.v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.markupartist.android.widget.ActionBar;

public class createSpot extends Activity{

	ActionBar actionBar;
	EditText spotName;
	EditText spotDescription;
	Button createSpot;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_spot);
        
        // actionBar setup
        actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle("createSpot");
		
		// EditTexts + Buttons
		spotName = (EditText)findViewById(R.id.spotName);
		spotDescription = (EditText)findViewById(R.id.spotDescription);
		createSpot = (Button)findViewById(R.id.createspot);
		
		createSpot.setOnClickListener(newSpotListener);
    }
    
    private View.OnClickListener newSpotListener=new View.OnClickListener() {
        public void onClick(View v) {
          Intent i = new Intent(createSpot.this, theSpot.class);
          i.putExtra("spotName", spotName.getText().toString());
          i.putExtra("spotDescription", spotDescription.getText().toString());
          startActivity(i);
        }    
    };  
}
