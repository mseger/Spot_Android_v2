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
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.markupartist.android.widget.ActionBar;

public class theSpot extends Activity{
	
	ActionBar actionBar;
	private Uri srcImageUri = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_spot);
        
        // actionBar setup
        actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle("theSpot");
		
		// add a new file to the Spot;
		actionBar.addAction(new newFileAction());
		
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
						//if (loginValid) {
							Log.i(Utils.TAG, "Taking picture");
							File outputFile = new File(Environment.getExternalStorageDirectory(), "Spot" + "/" + "Spot.jpg");
							srcImageUri = Uri.fromFile(outputFile);
							Log.i(Utils.TAG, srcImageUri.toString());
							Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
							cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, srcImageUri);
							startActivityForResult(cameraIntent, 1); //1 is Utils.CAMERA_PIC_REQUEST
						/*} else {
							Intent loginIntent = new Intent(theSpot.this, ActivityLogin.class);
							Toast.makeText(ActivityTheSpot.this, "Must be Logged in to Upload New Item", Toast.LENGTH_LONG).show();
							loginIntent.putExtra("whereNext", Utils.TAKE_PICTURE);
							loginIntent.putExtra("spotID", spotID);
							startActivity(loginIntent);
						}*/
					}else{
						//Choose from gallery
						//if(loginValid) {
							Intent intent = new Intent();
							intent.setType("image/*");
							intent.setAction(Intent.ACTION_GET_CONTENT);
							startActivityForResult(Intent.createChooser(intent,
							"Select Picture"), 2); //2 comes from Utils.SELECT_FROM_GALLERY
						/*} else {
							Intent loginIntent = new Intent(ActivityTheSpot.this, ActivityLogin.class);
							loginIntent.putExtra("whereNext", Utils.CHOOSE_FROM_GALLERY);
							loginIntent.putExtra("spotID", spotID);
							startActivity(loginIntent);
						}*/
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
}
