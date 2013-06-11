package my.catalog;

import java.io.File;

import resource.Item;
import resource.ResourceReader;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);

		Intent i = getIntent();
		Item item = null;
		item = (Item) i.getParcelableExtra("item");
		
		Log.i("Catalog", item.get_name());
		
		TextView title = (TextView) findViewById(R.id.textView1);
		title.setText(item.get_name());
		
		
		ResourceReader r = new ResourceReader(getApplicationContext());
		String path = r.getPhotoPath(item.get_photo());
		
		TextView description = (TextView) findViewById(R.id.textView2);
		description.setText(item.get_description());
		
		File imgfile = new File(path);
		if(imgfile.exists()){
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());

			ImageView img = (ImageView) findViewById(R.id.imageView1);
		    img.setImageBitmap(myBitmap);
		}
		
	}
}
