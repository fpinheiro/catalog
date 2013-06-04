package my.catalog.unzip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.util.Log;

/**
 * Decompress _zipFile file at _location.
 * 
 * @author fpinheiro
 */
public class Unzip {
	private String _location;
	private InputStream _fin;

	public Unzip(InputStream fin, String location) {
		_fin = fin;
		_location = location;

		_dirChecker("");
	}

	public Boolean unzip() {
		try {
			ZipInputStream zin = new ZipInputStream(_fin);
			ZipEntry ze = null;
			while ((ze = zin.getNextEntry()) != null) {
				Log.v("Catalog", "Unzipping " + ze.getName());

				if (ze.isDirectory()) {
					_dirChecker(ze.getName());
				} else {
					FileOutputStream fout = new FileOutputStream(_location + ze.getName());
					for (int c = zin.read(); c != -1; c = zin.read()) {
						fout.write(c);
					}

					zin.closeEntry();
					fout.close();
				}

			}
			zin.close();
			return true;
		} catch (Exception e) {
			Log.e("Catalog", "Unzip error", e);
			return false;
		}

	}

	private void _dirChecker(String dir) {
		File f = new File(_location + dir);

		if (!f.isDirectory()) {
			f.mkdirs();
		}
	}
}