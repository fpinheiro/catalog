package resource;

/**
 * Represents a catalog item, with it's
 * 
 * @author fpinheiro
 * 
 */
public class Item {

	private String _name;
	private String _photo;
	private String _description;

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
}
