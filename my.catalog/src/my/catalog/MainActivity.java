package my.catalog;

import my.catalog.unzip.Unzip;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button shareButton = (Button) findViewById(R.id.unzipButton);
		shareButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String zipFile = Environment.getExternalStorageDirectory() + "/data.zip"; 
				String unzipLocation = Environment.getExternalStorageDirectory() + "/unzipped/"; 
				 
				Unzip d = new Unzip(zipFile, unzipLocation); 
				d.unzip(); 
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
