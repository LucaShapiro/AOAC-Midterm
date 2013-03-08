// Based on code from Shawn Van Every
// and https://developers.google.com/maps/documentation/android/v1/hello-mapview

//should this package name be the same as in my main activity?
package com.example.googlemaps;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.VideoView;

import com.google.android.maps.OverlayItem;

public class ItemizedOverlay extends com.google.android.maps.ItemizedOverlay {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	Context mContext;
	
	public ItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
	  return mOverlays.size();
	}
	
	public ItemizedOverlay(Drawable defaultMarker, Context context) {
		  super(boundCenterBottom(defaultMarker));
		  mContext = context;
		}
	
	@Override
	protected boolean onTap(int index) {
		Intent i = new Intent(this, ViewTheVideo.class);
		i.putExtra("videofile", "nameofvideo.mp4");
		// add if statements here that rename the video file depending on which index is passed in
		startActivity(i);
		
		
	  OverlayItem item = mOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}

}
