package cn.mycomunication;

import com.example.myvolly.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
//����ͼƬ�Ļ��Lrucache
public class Testpiccache extends Activity {

	public Testpiccache() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapsample);
		int maxsize = (int)Runtime.getRuntime().maxMemory();//����ڴ�ռ�
		Log.w("应用程序的最大内存空间",""+maxsize);
		Toast.makeText(Testpiccache.this,"最大内存空间为"+maxsize,Toast.LENGTH_LONG).show();
		
		//使用bitmapfactory 获取图像的尺寸信息。
		BitmapFactory.Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher,options);
		options.inSampleSize = caculatesample(options,40,40);
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher,options);
		ImageView imag1 = (ImageView)findViewById(R.id.img1);
		ImageView imag2 = (ImageView)findViewById(R.id.img2);
		imag1.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));//ԭʼͼ��
		
		imag2.setImageBitmap(bitmap);//设置显示的图像
		//
		
	}
	private int caculatesample(Options options,int width,int height)
	{
		int rawheight = options.outHeight;
		int rawwidth = options.outWidth;
		Toast.makeText(Testpiccache.this,"原图像的高度"+rawheight+"原图像的高度"+rawwidth,Toast.LENGTH_LONG).show();
		int sampleSize = 1;//计算压缩比例
		if(rawheight>height||rawwidth>width)
		{
			int temp1 = Math.round(((float)rawheight/height));
			int temp2 = Math.round(((float)rawwidth/width));
			sampleSize = temp1<temp2?temp1:temp2;
		}
		return sampleSize;
	}

}
