package cn.mycomunication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
//
public class Testnumberimage extends Activity {
	private LruCache<String,Bitmap> imagecache;
	public Testnumberimage() {
		// TODO Auto-generated constructor stub
	}
private int initsize()//
{
	int appMemory = (int) Runtime.getRuntime().maxMemory();//缓存用于存储图片，限制缓存的带下为8M
	imagecache = new LruCache<String,Bitmap>(appMemory/8)
			{

				@Override
				protected int sizeOf(String key, Bitmap value) {
					// TODO Auto-generated method stub
					return value.getByteCount();//重写sizeof 返回每个item的真实大小，使得给定缓存大小值的时候，给定的是总的大小而不是数量。
					//
				}
				
			};
	return appMemory/8;//
}

private void addImagetoCache(int id,Bitmap bitmap)
{
	//往cache中添加元素
	if(getImagefromCache(id)==null)
	{
		imagecache.put(String.valueOf(id),bitmap);
	}
}
private Bitmap getImagefromCache(int id)
{
	return imagecache.get(String.valueOf(id));//获取cache中的元素。
}




}
