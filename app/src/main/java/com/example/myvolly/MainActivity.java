package com.example.myvolly;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn;
	private TextView te;
	private ImageView imge;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button)findViewById(R.id.button1);
		te = (TextView)findViewById(R.id.text1);
		imge = (ImageView)findViewById(R.id.an1);
		final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);//创建请求的队列
		//
		final StringRequest str = new StringRequest("http://www.baidu.com",new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.w("contetn",response);
				te.setText(response);
			}
			
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
		final StringRequest rt = new StringRequest(Method.POST,"http://1.testappheader.sinaapp.com/Myservlet", new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				Log.w("contetn",response);
				te.setText(response);
			}
		},new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		})
		{

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String,String> map = new HashMap<String,String>();
				map.put("name","wxt");
				map.put("age","24");
				return map;
			}
			
		};//
		
		//获取json数据
		final JsonObjectRequest js = new JsonObjectRequest("http://1.testappheader.sinaapp.com/JSONServlet", null,new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Log.w("contetn",response.toString());
				te.setText(response.toString());//JSON ����ת��
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,"wrong",Toast.LENGTH_SHORT).show();
			}
			
			
		});
		final ImageRequest img = new ImageRequest("http://localhost/Androidvolleypic/aw_dac.png",new Response.Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				// TODO Auto-generated method stub
				//
				Toast.makeText(MainActivity.this,"������Ӧ",Toast.LENGTH_SHORT).show();
				imge = (ImageView)(MainActivity.this.findViewById(R.id.an1));
				if(imge!=null) Toast.makeText(MainActivity.this,"imageviewerror",Toast.LENGTH_LONG).show();
				imge.setImageBitmap(response);//
				
			}
		},0,0,Config.RGB_565,new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,"����",Toast.LENGTH_SHORT).show();
				//����ʧ�ܵĻص�����
			}
		});
		
		
		ImageLoader imageLoader = new ImageLoader(queue,new ImageCache()
		{

			@Override
			public Bitmap getBitmap(String url) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		ImageListener listener = ImageLoader.getImageListener(imge,R.drawable.ic_launcher,R.drawable.ic_launcher);
		//��ȡlistener
		imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener);
		
		
		//����������imageview ����ʼ��drawable�����Լ�����ʧ�ܵ�drawable����
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				queue.add(img);//队列中添加请求。
				Toast.makeText(MainActivity.this,"��ջ���",Toast.LENGTH_SHORT).show();
			
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
