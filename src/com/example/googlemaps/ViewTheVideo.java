package com.example.googlemaps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.VideoView;

public class ViewTheVideo extends Activity 
{
    VideoView vv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent i = getIntent();
        String filename = i.getStringExtra("videofile");
        
        vv = (VideoView) this.findViewById(R.id.VideoView);
		Uri videoUri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/Test_Movie.m4v");
        vv.setVideoURI(videoUri);
        vv.start();
    }
}