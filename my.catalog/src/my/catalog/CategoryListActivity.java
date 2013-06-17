package my.catalog;

import helper.LazyAdapter;

import java.util.ArrayList;

import resource.Category;
import resource.Item;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class CategoryListActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_list);
		
		Intent i = getIntent();
		Category category = null;
		category = (Category) i.getParcelableExtra("category");
		
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setTitle(category.get_name());

		ArrayList<Category> categories = category.get_categories();
		ArrayList<Item> items = category.get_items();

		ArrayList<Object> list = new ArrayList<Object>();
		list.addAll(categories);
		list.addAll(items);

		ListView lv = getListView();
//		final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		
		LazyAdapter adapter = new LazyAdapter(this, list);
		
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Object selected = getListView().getItemAtPosition(position);

				if (selected instanceof Category) {
					Intent i = new Intent(getApplicationContext(), CategoryListActivity.class);

					i.putExtra("category", (Category) selected);

					startActivity(i);
				} else if (selected instanceof Item) {
					Intent i = new Intent(getApplicationContext(), ItemActivity.class);

					i.putExtra("item", (Item) selected);
					startActivity(i);
				}
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go up
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
