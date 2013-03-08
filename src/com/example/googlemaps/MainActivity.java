// Based on code from Shawn Van Every
// and https://developers.google.com/maps/documentation/android/v1/hello-mapview

package com.example.googlemaps;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;



public class MainActivity extends MapActivity implements LocationListener {

	LocationManager lm; 
	MapView mv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 6000l, 5.0f, this);
        
		mv = (MapView) this.findViewById(R.id.mapview);
		
		List<Overlay> mapOverlays = mv.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		ItemizedOverlay itemizedoverlay = new ItemizedOverlay(drawable, this);
		
		GeoPoint point = new GeoPoint(19240000,-99120000);
		OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
		
		GeoPoint point2 = new GeoPoint(35410000, 139460000);
		OverlayItem overlayitem2 = new OverlayItem(point2, "Sekai, konichiwa!", "I'm in Japan!");
		
		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
    }

    public void onPause() {
		super.onPause(); 
		lm.removeUpdates(this);
	}

	public void onLocationChanged(Location location) {
		MapController mc = mv.getController();
		
		GeoPoint p = new GeoPoint((int) (location.getLatitude() * 1000000), (int) (location.getLongitude() * 1000000));
		mc.animateTo(p);
		//http://code.google.com/android/add-ons/google-apis/reference/com/google/android/maps/MapController.html#setZoom(int)
		mc.setZoom(14);
	}

	public void onProviderDisabled(String provider) {
		
	}

	public void onProviderEnabled(String provider) {
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}