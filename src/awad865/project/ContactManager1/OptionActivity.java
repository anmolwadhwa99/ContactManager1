package awad865.project.ContactManager1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class OptionActivity extends Activity {

	private Button button1;
	private Button button2;
	private Button button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		button1=(Button) findViewById(R.id.button_sort);


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.option, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
