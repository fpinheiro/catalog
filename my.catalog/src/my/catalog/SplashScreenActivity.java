package my.catalog;

import java.util.ArrayList;

import resource.Catalog;
import resource.Item;
import resource.ResourceReader;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends Activity {

	final ResourceReader rr = MyCatalogApp.getInstance().rr;
	private final  Catalog catalog = rr.generateCatalog();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		// Action Bar
		ActionBar ab = getActionBar();
		ab.setTitle(catalog.get_title());

		ArrayList<Item> items = catalog.getAllItems();
		Item item = items.get(0);
		ImageView iv = (ImageView) findViewById(R.id.imageView1);
		iv.setImageBitmap(item.getMainPhoto(getApplicationContext()));

		TextView title = (TextView) findViewById(R.id.textView1);
		title.setText(item.get_name());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_navigate:

			Intent i = new Intent(getApplicationContext(), CatalogActivity.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
