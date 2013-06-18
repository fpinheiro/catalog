package my.catalog;

import resource.ResourceReader;
import android.app.Application;

public class MyCatalogApp extends Application {
	private static MyCatalogApp singleton;

	public ResourceReader rr;

	public static MyCatalogApp getInstance() {
		return singleton;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;

		rr = new ResourceReader(getApplicationContext());
	}
}