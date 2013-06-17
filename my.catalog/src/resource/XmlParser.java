package resource;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class XmlParser {

	private static final String ns = null;

	public XmlParser() {
	}

	public Catalog parse(StringReader in) throws XmlPullParserException, IOException {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in);
			parser.nextTag();
			return readCatalog(parser);
		} finally {
			in.close();
		}
	}

	private Catalog readCatalog(XmlPullParser parser) throws XmlPullParserException, IOException {
		Catalog catalog = new Catalog();

		parser.require(XmlPullParser.START_TAG, ns, "catalog");
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();

			if (name.equals("title")) {
				catalog.set_title(readTitle(parser));
			} else if (name.equals("category")) {
				catalog.get_categories().add(readCategory(parser));
			} else {
				skip(parser);
			}
		}
		return catalog;
	}

	private Category readCategory(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "category");
		Category category = new Category();

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String tagName = parser.getName();
			if (tagName.equals("name")) {
				category.set_name(readName(parser));
			} else if (tagName.equals("item")) {
				category.get_items().add(readItem(parser));
			} else if (tagName.equals("category")) {
				category.get_categories().add(readCategory(parser));
			} else {
				skip(parser);
			}
		}
		return category;
	}

	private Item readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "item");
		Item item = new Item();
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String tagName = parser.getName();
			if (tagName.equals("name")) {
				item.set_name(readName(parser));
			} else if (tagName.equals("photo")) {
				item.set_photo(readPhoto(parser));
			} else if (tagName.equals("icon")) {
				item.set_thumb_photo(readIcon(parser));
			} else if (tagName.equals("description")) {
				item.set_description(readDescription(parser));
			} else {
				skip(parser);
			}
		}
		return item;
	}

	private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "title");
		String title = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "title");
		return title;
	}

	private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "name");
		String name = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "name");
		return name;
	}

	private String readPhoto(XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "photo");
		String photo = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "photo");
		return photo;
	}
	
	private String readIcon(XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "icon");
		String icon = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "icon");
		return icon;
	}

	private String readDescription(XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "description");
		String description = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "description");
		return description;
	}

	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			parser.nextTag();
		}
		return result;
	}

	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
		if (parser.getEventType() != XmlPullParser.START_TAG) {
			throw new IllegalStateException();
		}
		int depth = 1;
		while (depth != 0) {
			switch (parser.next()) {
			case XmlPullParser.END_TAG:
				depth--;
				break;
			case XmlPullParser.START_TAG:
				depth++;
				break;
			}
		}
	}
}
