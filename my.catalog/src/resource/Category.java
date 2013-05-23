package resource;

import java.util.ArrayList;

public class Category {

	private String _name;
	private ArrayList<Category> _categories;
	private ArrayList<Item> _items;

	public Category() {
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
}
