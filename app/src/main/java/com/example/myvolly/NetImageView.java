package com.example.myvolly;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

public class NetImageView extends Activity {

	public NetImageView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.netlayout);
		RequestQueue re = Volley.newRequestQueue(NetImageView.this);//��һ������RequestQueue
		ImageLoader imageloader = new ImageLoader(re,new ImageCache(){
			//
			
			@Override
			public Bitmap getBitmap(String url) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				// TODO Auto-generated method stub
				
			}});
		
		//
		NetworkImageView netview = (NetworkImageView)findViewById(R.id.net1);
		//
		netview.setDefaultImageResId(R.drawable.ic_launcher);
		netview.setErrorImageResId(R.drawable.ic_launcher);
		netview.setImageUrl("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",imageloader);
	}

}
