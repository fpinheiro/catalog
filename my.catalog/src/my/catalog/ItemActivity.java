package my.catalog;

import resource.Item;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
		TextView description = (TextView) findViewById(R.id.textView2);
		description.setText(item.get_description());
	}
}
