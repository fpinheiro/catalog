package helper;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadFile extends AsyncTask<String, Integer, String> {

	public String output;

	public DownloadFile(String otp) {
		output = otp;
	}

	@Override
	protected String doInBackground(String... sUrl) {
		if (output == null) {
			Log.w("Catalog", "Cannot download without a specified output path.");
			return null;
		}
		try {
			URL url = new URL(sUrl[0]);
			URLConnection connection = url.openConnection();
			connection.connect();
			// this will be useful so that you can show a typical 0-100%
			// progress bar
			int fileLength = connection.getContentLength();

			// download the file
			InputStream input = new BufferedInputStream(url.openStream());
			// OutputStream output = new
			// FileOutputStream("/sdcard/file_name.extension");
			OutputStream out = new FileOutputStream(output);

			byte data[] = new byte[1024];
			long total = 0;
			int count;
			while ((count = input.read(data)) != -1) {
				total += count;
				// publishing the progress....
				publishProgress((int) (total * 100 / fileLength));
				out.write(data, 0, count);
			}

			out.flush();
			out.close();
			input.close();
		} catch (Exception e) {
			Log.e("Catalog", "Erro de download...");
			Log.e("Catalog", e.getMessage());
		}
		Log.i("Catalog", "Downloaded with success.");
		return null;
	}

}
