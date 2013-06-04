package resource;

import java.util.ArrayList;

public class Catalog {

	private String _title;
	private ArrayList<Category> _categories = new ArrayList<Category>();

	public Catalog() {
	}
	
	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public ArrayList<Category> get_categories() {
		return _categories;
	}

	public void set_categories(ArrayList<Category> _categories) {
		this._categories = _categories;
	}
}
