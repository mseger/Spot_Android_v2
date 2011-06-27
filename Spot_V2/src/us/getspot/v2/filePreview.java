package us.getspot.v2;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.markupartist.android.widget.ActionBar;

public class filePreview extends Activity{
	
	ActionBar actionBar;
	ImageView imageView = null;
	String filePath = null;
    int whereFrom;
    //private Uri srcImageUri = null;
    private Uri processedImageUri = null;
    private boolean imageReady = false;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_preview);
        
        // actionBar setup
        actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle("filePreview");
		
        imageView = (ImageView)findViewById(R.id.imageView);
        
        // unbundle + get extras
        Bundle extras = getIntent().getExtras();
        filePath = extras.getString("filePath");   
        whereFrom = extras.getInt("whereFrom");
       
    }
    
}