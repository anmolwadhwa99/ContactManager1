package awad865.project.ContactManager1;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
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
				dialogBuilder.setPositiveButton("Last name", null);
				dialogBuilder.setNeutralButton("First name", null);
				dialogBuilder.setNegativeButton("Phone number", null);

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.option, menu);
		return true;
	}


}
