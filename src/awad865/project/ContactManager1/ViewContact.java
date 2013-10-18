package awad865.project.ContactManager1;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

//The purpose of this activity is that when the user clicks on a contact 
//the user is navigated to another activity which displays that contact's
//information
public class ViewContact extends Activity {

	//declare private fields
	private EditText firstName;
	private EditText lastName;
	private EditText number;
	private EditText address;
	private EditText  date;
	private EditText email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_contact);

		//code to enable the title and the up button in the action bar
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);


		//initialise private fields and make them non-editable
		firstName = (EditText)findViewById(R.id.edit_first_name);
		firstName.setKeyListener(null);
		lastName = (EditText)findViewById(R.id.edit_last_name);
		lastName.setKeyListener(null);
		number = (EditText)findViewById(R.id.edit_number);
		number.setKeyListener(null);
		address = (EditText)findViewById(R.id.edit_address);
		address.setKeyListener(null);
		date = (EditText)findViewById(R.id.edit_date);
		date.setKeyListener(null);
		email =(EditText)findViewById(R.id.edit_email);
		email.setKeyListener(null);

		//get the intent
		Intent intent = getIntent();

		//retrieve the information from list of contacts
		Bundle extras = intent.getExtras();
		String theFirstName = extras.getString("firstName");
		String theLastName = extras.getString("lastName");

		//have an enhanced for loop that checks to see if the retrieved information, matches the information
		//in displayList (public array list in MainActivity.java). If it is then retrieve all that information
		//about that specific contact and display to the user.

		for(Contact contact : MainActivity.displayList){
			if((contact.get_firstName().equals(theFirstName)) && (contact.get_lastName().equals(theLastName))){
				firstName.setText(contact.get_firstName());
				lastName.setText(contact.get_lastName());
				number.setText(contact.get_number());
				address.setText(contact.get_address());
				date.setText(contact.get_date());
				email.setText(contact.get_email());
			}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_contact, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){

		switch(item.getItemId()){
		//if the user clicks on the edit button, then the private fields are initialised again
		//the first name and last name is retrieved from the edit text in the xml and then transferred
		//to the EditContact activity.
		case R.id.action_edit_contact:
			firstName = (EditText)findViewById(R.id.edit_first_name);
			lastName = (EditText)findViewById(R.id.edit_last_name);
			number = (EditText)findViewById(R.id.edit_number);
			address = (EditText)findViewById(R.id.edit_address);
			date = (EditText)findViewById(R.id.edit_date);
			email =(EditText)findViewById(R.id.edit_email);

			String fName = firstName.getText().toString();
			String lName = lastName.getText().toString();
			//new intent is created to start a new activity
			Intent edit_intent = new Intent(this,EditContact.class);
			edit_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			//the first name and last name is passed through to the next activity using a bundle
			Bundle extras = new Bundle();
			extras.putString("firstName", fName);
			extras.putString("lastName", lName);
			edit_intent.putExtras(extras);
			startActivity(edit_intent);
			return true;

		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.button_call:
			try {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:"+number.getText().toString()));
				startActivity(callIntent);
			} catch (ActivityNotFoundException activityException) {
				Log.e("Calling a Phone Number", "Call failed", activityException);
			}
			return true;
		case R.id.button_messaging:
			Intent intent = new Intent(Intent.ACTION_SENDTO, 
					Uri.fromParts("sms", number.getText().toString(), null));
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}



}

