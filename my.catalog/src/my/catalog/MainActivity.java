package my.catalog;

import resource.ResourceReader;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	ResourceReader rr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button shareButton = (Button) findViewById(R.id.unzipButton);
		shareButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				String root = Environment.getExternalStorageState();
				String root = "mnt/sdcard/";
				rr = new ResourceReader(root);
				rr.read();
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
