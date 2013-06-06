package resource;

import java.util.ArrayList;
import java.util.Collection;

public class Category {

	private String _name;
	private ArrayList<Category> _categories = new ArrayList<Category>();
	private ArrayList<Item> _items = new ArrayList<Item>();

	public Category() {
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
	public String toString(){
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
}
