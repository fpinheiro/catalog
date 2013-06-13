package my.catalog;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		// IntentLauncher launcher = new IntentLauncher();
		// launcher.start();

//		try {
//			// Sleeping
//			Thread.sleep(1000);
//		} catch (Exception e) {
//			Log.e("Catalog", e.getMessage());
//		}

		// Start main activity
		Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
		startActivity(intent);
		finish();
	}
}
