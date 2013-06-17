package my.catalog;

import helper.LazyAdapter;

import java.util.ArrayList;

import resource.Catalog;
import resource.Category;
import resource.ResourceReader;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CatalogActivity extends ListActivity {

	ResourceReader rr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.catalog_activity);

		rr = new ResourceReader(getApplicationContext());
		rr.read();

		final Catalog catalog = rr.generateCatalog();
		ListView listview = getListView();

		ActionBar ab = getActionBar();
//		ab.setDisplayHomeAsUpEnabled(true);
		ab.setTitle(catalog.get_title());

		ArrayList<Object> categories = new ArrayList<Object>(); 
		categories.addAll(catalog.get_categories());
		
		LazyAdapter adapter = new LazyAdapter(this, categories);

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

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			// app icon in action bar clicked; go up
//			finish();
//			return true;
//		default:
//			return super.onOptionsItemSelected(item);
//		}
//	}

}
