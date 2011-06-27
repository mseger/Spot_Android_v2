package us.getspot.v2;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.markupartist.android.widget.ActionBar;

public class history extends MapActivity{

	ActionBar actionBar;
	private MapView myMap;
	private MyLocationOverlay myLocOverlay;
 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);
		
		// actionBar setup
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle("history");
		
		initMap();
		initMyLocation();
	}
 
	/**
	 * Initialise the map and adds the zoomcontrols to the LinearLayout.
	 */
	private void initMap() {
		myMap = (MapView) findViewById(R.id.mapview);
 
		View zoomView = myMap.getZoomControls();
		LinearLayout myzoom = (LinearLayout) findViewById(R.id.zoom);
		myzoom.addView(zoomView);
		myMap.displayZoomControls(true);
 
	}
 
	/**
	 * Initialises the MyLocationOverlay and adds it to the overlays of the map
	 */
	private void initMyLocation() {
		myLocOverlay = new MyLocationOverlay(this, myMap);
		myLocOverlay.enableMyLocation();
		myMap.getOverlays().add(myLocOverlay);
 
	}
 
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}

