package awad865.project.ContactManager1;

import android.app.Activity;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class OptionActivity extends Activity {

	//create two private fields of type button
	private Button sortButton;
	private Button displayContactListButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option);
		//code to enable the launcher icon, heading and the up button on the action bar
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);


		//initialise a value to the private fields by using the findViewById() method.
		sortButton=(Button) findViewById(R.id.button_sort);
		displayContactListButton= (Button) findViewById(R.id.button_contactsToDisplay);


		//have a listener attached to button1
		sortButton.setOnClickListener(new View.OnClickListener() {

			// when the user clicks on this button, then this method (onClick()) is invoked.
			@Override
			public void onClick(View arg0) {
				//creating a new dialog box with a title, positive button, negative button and neutral button
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(OptionActivity.this);

				dialogBuilder.setTitle("Sort contact list by?");
				dialogBuilder.setMessage("Select your sorting order");
				dialogBuilder.setNegativeButton("Phone number", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						String number = "number";
						MainActivity.order= number;
						Intent main_intent= new Intent(OptionActivity.this, MainActivity.class);
						startActivity(main_intent);

					}
				});

				dialogBuilder.setPositiveButton("Last name", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String last = "lastname";
						MainActivity.order= last;
						Intent main_intent= new Intent(OptionActivity.this, MainActivity.class);
						startActivity(main_intent);

					}
				});

				dialogBuilder.setNeutralButton("First name", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String first = "firstname";
						MainActivity.order= first;
						Intent main_intent= new Intent(OptionActivity.this, MainActivity.class);
						startActivity(main_intent);
					}
				});


				//allow the user to cancel the dialog box
				dialogBuilder.setCancelable(true);

				//able to create and show the dialog box
				dialogBuilder.create().show();
			}
		});

		//have a listener attached to this button
		displayContactListButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//when the user clicks this button, a dialog box is generated
				AlertDialog.Builder dialog = new AlertDialog.Builder(OptionActivity.this);

				//set the title, the positive and negative button
				dialog.setTitle("Display contact list by?");
				dialog.setPositiveButton("Last name", null);
				dialog.setNegativeButton("First name", null);

				//create and show the dialog box
				dialog.setCancelable(true);
				dialog.create().show();

			}
		});

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.option, menu);
		return true;
	}


}
