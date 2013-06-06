package my.catalog;

import java.util.ArrayList;

import resource.Catalog;
import resource.Category;
import resource.Item;
import resource.ResourceReader;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	ResourceReader rr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		rr = new ResourceReader(getApplicationContext());
		rr.read();

		Catalog catalog = rr.generateCatalog();
		final ListView listview = (ListView) findViewById(R.id.listView1);

		ArrayList<Category> categories = catalog.get_categories();
		final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categories);
		
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Object selected = listview.getItemAtPosition(position);

				if (selected instanceof Category) {
					ArrayList<Category> categories = ((Category) selected).get_categories();
					ArrayList<Item> items = ((Category) selected).get_items();

					if (categories.size() > 0) {
						adapter.clear();
						for (Category cat : categories) {
							adapter.add(cat);
						}
					} else {
						adapter.clear();
						for (Item i : items) {
							adapter.add(i);
						}
					}
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
