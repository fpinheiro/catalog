package my.catalog;

import resource.Item;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);

		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);

		Intent i = getIntent();
		Item item = null;
		item = (Item) i.getParcelableExtra("item");
		
		ab.setTitle(item.get_name());

		TextView description = (TextView) findViewById(R.id.textView1);
		description.setText(item.get_description());
		
		ImageView img = (ImageView) findViewById(R.id.imageView1);
		img.setImageBitmap(item.getMainPhoto(getApplicationContext()));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
