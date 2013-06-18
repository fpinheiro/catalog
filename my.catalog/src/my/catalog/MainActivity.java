package my.catalog;

import java.util.ArrayList;

import resource.Catalog;
import resource.Item;
import resource.ResourceReader;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		ResourceReader rr = MyCatalogApp.getInstance().rr;
		Catalog catalog = rr.getCatalog();
		
		// Action Bar
		ActionBar ab = getActionBar();
		ab.setTitle(catalog.get_title());
		

		ArrayList<Item> items = catalog.getAllItems();
		
		ImageView iv = (ImageView) findViewById(R.id.imageView1);
		iv.setImageBitmap(items.get(1).getMainPhoto(getApplicationContext()));
	}
}
