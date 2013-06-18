package my.catalog;

import java.util.ArrayList;

import resource.Item;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;

public class ImageAdapter extends BaseAdapter implements SpinnerAdapter {

	private Context _context;
	private ArrayList<Item> _items;

	public ImageAdapter(Context context, ArrayList<Item> it) {
		_context = context;
		_items = it;
	}

	@Override
	public int getCount() {
		return _items.size();
	}

	@Override
	public Object getItem(int arg0) {
		return _items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ImageView iv = null;
		if (convertView == null){
			iv = new ImageView(_context);
		}else{
			iv = (ImageView)convertView;
		}
		Item it = _items.get(position);
		
		iv.setImageBitmap(it.getThumbPhoto(_context));
		return iv;
	}

}
