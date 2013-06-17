package resource;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

	private String _name;
	private String _photo;
	private ArrayList<Category> _categories = new ArrayList<Category>();
	private ArrayList<Item> _items = new ArrayList<Item>();
	
	public Category() {
	}

	private Category(Parcel in) {
		_name = in.readString();
		_photo = in.readString();
		 in.readTypedList(_categories, Category.CREATOR);
		 in.readTypedList(_items, Item.CREATOR);
	}

	public Collection<? extends Item> getAllItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		items.addAll(_items);
		for (Category cat : _categories) {
			items.addAll(cat.getAllItems());
		}
		return items;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(_name);
		out.writeString(_photo);
		out.writeTypedList(_categories);
		out.writeTypedList(_items);
	}

	public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
		public Category createFromParcel(Parcel in) {
			return new Category(in);
		}

		public Category[] newArray(int size) {
			return new Category[size];
		}
	};

	public Bitmap getPhoto(Context c){
		ResourceReader r = new ResourceReader(c);
		String p = r.getPhotoPath(_photo);
		File imgfile = new File(p);
		if (imgfile.exists()) {
			return BitmapFactory.decodeFile(imgfile.getAbsolutePath());
		}
		return null;
	}
	
	@Override
	public String toString() {
		return get_name();
	}

	public String get_name() {
		return _name;
	}

	public ArrayList<Category> get_categories() {
		return _categories;
	}

	public void set_categories(ArrayList<Category> _categories) {
		this._categories = _categories;
	}

	public ArrayList<Item> get_items() {
		return _items;
	}

	public void set_items(ArrayList<Item> _items) {
		this._items = _items;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_photo() {
		return _photo;
	}

	public void set_photo(String _photo) {
		this._photo = _photo;
	}
}
