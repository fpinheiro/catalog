package my.catalog;

import resource.ResourceReader;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	final ResourceReader rr = MyCatalogApp.getInstance().rr;

	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.start);

		IntentLauncher launcher = new IntentLauncher();
		launcher.start();
	}

	private class IntentLauncher extends Thread {
		@Override
		public void run() {
			try {
				rr.read();
				rr.generateCatalog();
			} catch (Exception e) {
				Log.e("Catalog", e.getMessage());
			}

			// Start main activity
			Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
			MainActivity.this.startActivity(intent);
			MainActivity.this.finish();
		}
	}
}
