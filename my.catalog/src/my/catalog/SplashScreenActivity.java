package my.catalog;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import resource.Catalog;
import resource.Item;
import resource.ResourceReader;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreenActivity extends Activity {

	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;

	final ResourceReader rr = MyCatalogApp.getInstance().rr;
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		Catalog catalog = rr.generateCatalog();

		// Action Bar
		ActionBar ab = getActionBar();
		ab.setTitle(catalog.get_title());

		ArrayList<Item> items = catalog.getAllItems();
		Item item = items.get(0);
		final ImageView iv = (ImageView) findViewById(R.id.imageView1);
		iv.setImageBitmap(item.getMainPhoto(getApplicationContext()));

		final TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText(item.get_name());

		Gallery gallery = (Gallery) findViewById(R.id.gallery1);
		gallery.setAdapter(new ImageAdapter(this, items));
		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ImageAdapter ad = (ImageAdapter) parent.getAdapter();

				Item i = (Item) ad.getItem(position);
				iv.setImageBitmap(i.getMainPhoto(getApplicationContext()));
				tv.setText(i.get_name());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_navigate:

			Intent i = new Intent(getApplicationContext(), CatalogActivity.class);
			startActivity(i);
			return true;

		case R.id.update_catalog:
			updateCatalog();
			return true;
		
		case R.id.use_old_catalog:
			useOldCatalog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}


	private void updateCatalog() {
		// execute this when the downloader must be fired
		String output = getApplicationContext().getFilesDir().getAbsolutePath() + "/data.zip";
		DownloadFile downloadFile = new DownloadFile(output);
		downloadFile.execute("http://m13.fvenancio.com/catalog/data.zip");
	}
	
	private void useOldCatalog(){
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		ResourceReader rr = MyCatalogApp.getInstance().rr;
		rr.catalog = null;
		rr.is = null;

		File unzipDir = new File(rr.getUnzipLocation());
		deleteRecursive(unzipDir);
		startActivity(i);
		finish();		
	}
	
	void deleteRecursive(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory())
			for (File child : fileOrDirectory.listFiles())
				deleteRecursive(child);
		fileOrDirectory.delete();
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_DOWNLOAD_PROGRESS:
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage("Baixando novo catálogo...");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
			return mProgressDialog;
		default:
			return null;
		}
	}

	public class DownloadFile extends AsyncTask<String, Integer, String> {

		public String output;
		private boolean connected = false;

		public DownloadFile(String otp) {
			output = otp;
		}

		private boolean isNetworkConnected() {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni = cm.getActiveNetworkInfo();
			if (ni == null) {
				// There are no active networks.
				return false;
			} else
				return true;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (isNetworkConnected()) {
				showDialog(DIALOG_DOWNLOAD_PROGRESS);
				connected = true;
			} else {
				Toast.makeText(getApplicationContext(), "Você deve estar conectado para atualizar o catálogo!",
						Toast.LENGTH_LONG).show();
			}
		}

		@Override
		protected String doInBackground(String... sUrl) {
			if (!connected) {
				return null;
			}
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
					Integer progress = (int) (total * 100 / fileLength);
					publishProgress(progress);
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

		@Override
		protected void onProgressUpdate(Integer... progress) {
			mProgressDialog.setProgress(progress[0]);
		}

		@Override
		protected void onPostExecute(String unused) {
			if (!connected) {
				return;
			}
			mProgressDialog.dismiss();
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			ResourceReader rr = MyCatalogApp.getInstance().rr;
			rr.catalog = null;
			rr.setInputStream(output);

			File unzipDir = new File(rr.getUnzipLocation());
			deleteRecursive(unzipDir);
			startActivity(i);
			finish();
		}
	}

}
