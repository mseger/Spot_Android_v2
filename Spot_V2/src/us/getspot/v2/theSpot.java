package us.getspot.v2;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.markupartist.android.widget.ActionBar;

public class theSpot extends Activity{

	ActionBar actionBar;
	private Uri srcImageUri = null;
	String spotName;
	String spotDescription;
	TextView spotDescriptionLine;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.the_spot);

		// Unbundle Spot information
		Bundle extras = getIntent().getExtras();
		spotName = extras.getString("spotName");  
		spotDescription = extras.getString("spotDescription");

		// actionBar setup
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle(spotName);

		// add a new file to the Spot;
		actionBar.addAction(new newFileAction());

		// set the description of the Spot
		spotDescriptionLine = (TextView)findViewById(R.id.spotdescription);
		spotDescriptionLine.setText(spotDescription);
	}

	private class newFileAction implements ActionBar.Action {

		//@Override
		public void performAction(View view) {

			final CharSequence[] choices = {"Take Picture", "Choose from Gallery"};
			AlertDialog.Builder builder = new AlertDialog.Builder(theSpot.this);
			builder.setTitle("Choose your action");
			builder.setItems(choices, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					SharedPreferences sharedPreferences = getSharedPreferences(Utils.PREFS_NAME, MODE_PRIVATE);
					Boolean loginValid = sharedPreferences.getBoolean("loginValid",false);

					if(item == 0){
						//take picture
						Log.i(Utils.TAG, "Taking picture");
						File outputFile = new File(Environment.getExternalStorageDirectory(), "Spot" + "/" + "Spot.jpg");
						srcImageUri = Uri.fromFile(outputFile);
						Log.i(Utils.TAG, srcImageUri.toString());
						Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
						startActivityForResult(cameraIntent, Utils.UPLOAD_FROM_CAMERA); 

					}else{
						//Choose from gallery
						Intent intent = new Intent();
						intent.setType("image/*");
						intent.setAction(Intent.ACTION_GET_CONTENT);
						startActivityForResult(Intent.createChooser(intent,
								"Select Picture"), Utils.UPLOAD_FROM_GALLERY); 
					}
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		}

		@Override
		public int getDrawable() {
			return R.drawable.plus;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if( resultCode == Activity.RESULT_OK ) {
			switch( requestCode ) {
			case 1:
				
				// Launch into the Detailed Image Activity
				Intent i = new Intent(theSpot.this, filePreview.class);
				i.putExtra("filePath", srcImageUri.toString());
				i.putExtra("whereFrom", Utils.UPLOAD_FROM_CAMERA);
				startActivity(i);

				break;
			case 2:
				Log.i(Utils.TAG, "Gallery returned");
				Intent j = new Intent(theSpot.this, filePreview.class);
				j.putExtra("tester_tag", "GALLERY");
				startActivity(j);
			}
		}
	}
}