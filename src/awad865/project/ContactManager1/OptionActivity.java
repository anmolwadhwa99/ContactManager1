package awad865.project.ContactManager1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
		button3= (Button) findViewById(R.id.button_contactsToDisplay);

		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(OptionActivity.this);

				dialogBuilder.setTitle("Sort contact list by?");
				dialogBuilder.setPositiveButton("Last name", null);
				dialogBuilder.setNeutralButton("First name", null);
				dialogBuilder.setNegativeButton("Phone number", null);


				dialogBuilder.setCancelable(true);

				dialogBuilder.create().show();
			}
		});

		button3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(OptionActivity.this);

				dialog.setTitle("Display contact list by?");
				dialog.setPositiveButton("Last name", null);
				dialog.setNegativeButton("First name", null);

				dialog.setCancelable(true);
				dialog.create().show();

			}
		});

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
