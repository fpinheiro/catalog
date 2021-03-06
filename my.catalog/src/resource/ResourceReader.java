package resource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import my.catalog.R;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.util.Log;

/**
 * Reads the resources so that they can be showed as a catalog. Works as a layer
 * between the resource and the application as a whole.
 * 
 * @author fpinheiro
 * 
 */
public class ResourceReader {

	private String _unzipLocation;
	private Context _context;

	public String xml;
	public Catalog catalog;
	public InputStream is;

	public ResourceReader(Context context) {
		_context = context;
		_unzipLocation = _context.getFilesDir().getAbsolutePath() + "/";
	}

	public String getPhotoPath(String img) {
		return _unzipLocation + "img/" + img;
	}

	/**
	 * Parses the xml and generates the Catalog object.
	 * 
	 * @author fpinheiro
	 */
	public Catalog generateCatalog() {
		if (catalog != null)
			return catalog;
		if (xml == null)
			read();

		XmlParser xmlParser = new XmlParser();
		try {
			catalog = xmlParser.parse(new StringReader(xml));
		} catch (XmlPullParserException e) {
			Log.e("Catalog", "Could not parse xml.[1]");
			Log.e("Catalog", e.getMessage());
			return null;
		} catch (IOException e) {
			Log.e("Catalog", "Could not parse xml.[2]");
			Log.e("Catalog", e.getMessage());
			return null;
		}
		return catalog;
	}

	/**
	 * Reads the resources, load xml file.
	 * 
	 * @author fpinheiro
	 * @return true or false if it could read successfully or not.
	 */
	public Boolean read() {

		if (!_findResources()) {
			return false;
		}

		xml = _loadXmlFile();
		if (xml == null) {
			return false;
		}
		return true;
	}

	/**
	 * Finds the resources. If the files are not unzziped yet, unzip them first.
	 * 
	 * @author fpinheiro
	 * @return true or false whether the resources could be found.
	 */
	private Boolean _findResources() {
		// If unzipLocation is there.
		if ((new File(_unzipLocation)).isDirectory()) {
			if ((new File(_unzipLocation + "/structure.xml")).isFile()) {
				Log.i("Catalog", "The resource was already unzipped");
				return true;
			}
		}

		InputStream is = getInputStream();
		Unzip d = new Unzip(is, _unzipLocation);

		if (d.unzip()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Reads the xml file and returns it as a string.
	 * 
	 * @author fpinheiro
	 */
	private String _loadXmlFile() {
		String retXml = null;

		// Read xml file
		try {
			FileInputStream fis = new FileInputStream(_unzipLocation + "structure.xml");
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			byte buf[] = new byte[1024];
			int len;
			try {
				while ((len = fis.read(buf)) != -1) {
					outputStream.write(buf, 0, len);
				}
				outputStream.close();
				fis.close();
			} catch (IOException e) {

			}

			retXml = outputStream.toString();
		} catch (Exception e) {
			Log.e("Catalog", "Could not read catalog xml file.");
		}
		Log.i("Catalog", "Catalog xml file read with success.");
		return retXml;
	}
	
	public InputStream getInputStream() {
		if (is == null)
			return _context.getResources().openRawResource(R.raw.data);
		else
			return is;
	}
	
	public void setInputStream(String path){
		try {
			is = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			Log.e("Catalog", "InputStream change error.");
			e.printStackTrace();
		}
	};
	
	public String getUnzipLocation(){
		return _unzipLocation;
	}
}
