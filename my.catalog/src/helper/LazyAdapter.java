package helper;

import java.util.ArrayList;

import my.catalog.R;
import resource.Category;
import resource.Item;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<Object> list;
	private static LayoutInflater inflater = null;

	// public ImageLoader imageLoader;

	public LazyAdapter(Activity a, ArrayList<Object> list) {
		activity = a;
		this.list = list;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// imageLoader=new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.item_row, null);

		TextView title = (TextView) vi.findViewById(R.id.title);
		TextView artist = (TextView) vi.findViewById(R.id.subtitle);
		// ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); //
		// thumb image

		Object obj = list.get(position);

		if (obj instanceof Item) {
			Item item = (Item) obj;
			// Setting all values in listview
			title.setText(item.get_name());
			artist.setText(item.get_description());
			// imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL),
			// thumb_image);
		} else if (obj instanceof Category) {
			Category cat = (Category) obj;
			// Setting all values in listview
			title.setText(cat.get_name());
			artist.setText(cat.get_items().size()+" produtos.");
			// imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL),
			// thumb_image);
		}
		return vi;
	}
}