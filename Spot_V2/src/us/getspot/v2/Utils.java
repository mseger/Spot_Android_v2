package us.getspot.v2;

import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
	
	//debugging + misc
	public static final String TAG = "SPOT_ERROR";
    public static final String PREFS_NAME = "Spot_prefs";
    
    // command keycodes
    public static final int UPLOAD_FROM_CAMERA = 1;
    public static final int UPLOAD_FROM_GALLERY = 2;
    
    // image-related 
    public static final String OUTPUT_DIR = "Spot";
    public static final String OUTPUT_FILE = "spotpic.jpg";
    public static final String OUTPUT_FILE_PROCESSED = "spotpic_processed.jpg";
    public static final int IMAGE_WIDTH = 612;
    public static final int IMAGE_HEIGHT = 612;
    public static final int IMAGE_BORDER = 24;
    public static final int IMAGE_CORNER_RADIUS = 35;
    public static final int IMAGE_JPEG_COMPRESSION_QUALITY = 75;
    
 // needed in the ImageLoader class 
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
    
}

