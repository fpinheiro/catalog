package my.catalog;

import java.util.ArrayList;

import resource.Catalog;
import resource.Category;
import resource.ResourceReader;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	ResourceReader rr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.catalog_activity);

		rr = new ResourceReader(getApplicationContext());
		rr.read();

		final Catalog catalog = rr.generateCatalog();
		ListView listview = getListView();

		ArrayList<Category> categories = catalog.get_categories();
		final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categories);

		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Object selected = getListView().getItemAtPosition(position);

				if (selected instanceof Category) {
					Intent i = new Intent(getApplicationContext(), CategoryListActivity.class);

					i.putExtra("category", (Category) selected);

					startActivity(i);
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
