package resource;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a catalog item, with it's
 * 
 * @author fpinheiro
 * 
 */
public class Item implements Parcelable {

	private String _name;
	private String _photo;
	private String _thumb_photo;
	private String _description;

	public Item() {
	}

	public Item(String name, String photo, String description) {
		_name = name;
		_photo = photo;
		_description = description;
	}
	
	private Item(Parcel in) {
		_name = in.readString();
		_photo = in.readString();
		_thumb_photo = in.readString();
		_description = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(_name);
		out.writeString(_photo);
		out.writeString(_thumb_photo);
		out.writeString(_description);
	}

	public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
		public Item createFromParcel(Parcel in) {
			return new Item(in);
		}

		public Item[] newArray(int size) {
			return new Item[size];
		}
	};

	public Bitmap getThumbPhoto(Context c){
		return getBitMap(c, _thumb_photo);
	}
	
	public Bitmap getMainPhoto(Context c){
		return getBitMap(c, _photo);
	}
	
	public Bitmap getBitMap(Context c, String path){
		ResourceReader r = new ResourceReader(c);
		String p = r.getPhotoPath(path);
		File imgfile = new File(p);
		if (imgfile.exists()) {
			return BitmapFactory.decodeFile(imgfile.getAbsolutePath());
		}
		return null;
	}
	
	public String getShortDescription(){
		return _description.substring(0, _name.length() + 15) + "...";
	}
	
	@Override
	public String toString() {
		return get_name();
	}

	public String get_name() {
		return _name;
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

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public String get_thumb_photo() {
		return _thumb_photo;
	}

	public void set_thumb_photo(String _thumb_photo) {
		this._thumb_photo = _thumb_photo;
	}
}
