package awad865.project.ContactManager1;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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
	private DatabaseHandler databaseHandler;
	private Contact currentContact;
	private String currentFavourite = "";
	private String theFirstName = "";
	private String theLastName = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_contact);



		//code to disable the title and enable the up button in the action bar
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		databaseHandler = new DatabaseHandler(this);

		//Initialize private fields and make them non-editable
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
		theFirstName = extras.getString("firstName");
		theLastName = extras.getString("lastName");

		try {
			databaseHandler.openDataBase();
			currentContact = databaseHandler.getContact(theFirstName, theLastName);
			databaseHandler.close();
		} catch (SQLException sqle) {
			throw sqle;
		}
		
		//have an enhanced for loop that checks to see if the retrieved information, matches the information
		//in displayList (public array list in MainActivity.java). If it is then retrieve all that information
		//about that specific contact and display to the user.
		
		
				firstName.setText(currentContact.getFirstName());
				lastName.setText(currentContact.getLastName());
				number.setText(currentContact.getNumber());
				address.setText(currentContact.getAddress());
				date.setText(currentContact.getDate());
				email.setText(currentContact.getEmail());

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
			//new intent is created to start a new activity
			Intent intentEdit = new Intent(this,EditContact.class);
			intentEdit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			//the first name and last name is passed through to the next activity using a bundle
			Bundle extras = new Bundle();
			extras.putString("firstName", theFirstName);
			extras.putString("lastName", theLastName);
			intentEdit.putExtras(extras);
			startActivity(intentEdit);
			return true;

		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		//if the user presses the call button in the view contact activity, then 
		//they are directed to the dialer and the number they saved is dialed
		case R.id.button_call:
			try {
				Intent intentCall = new Intent(Intent.ACTION_CALL);
				intentCall.setData(Uri.parse("tel:"+number.getText().toString()));
				intentCall.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intentCall);
			} catch (ActivityNotFoundException activityException) {
				Log.e("Calling a Phone Number", "Call failed", activityException);
			}
			return true;
		//if the messaging button is pressed, then the user 
		//is navigated to the messaging application and they can
		//compose a message to the number saved.
		case R.id.button_messaging:
			Intent intentMessage = new Intent(Intent.ACTION_SENDTO, 
					Uri.fromParts("sms", number.getText().toString(), null));
			intentMessage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentMessage);
			return true;
			
		case R.id.button_favourite:
			if(currentContact.getFavourite().equals("false")) {
				currentContact.setFavourite("true");
				Toast.makeText(ViewContact.this, "Contact has been added to Favourites", Toast.LENGTH_LONG).show();
			} else {
				currentContact.setFavourite("false");
				Toast.makeText(ViewContact.this, "Contact has been removed from Favourites", Toast.LENGTH_LONG).show();
			}
			try {
				databaseHandler.openDataBase();
				databaseHandler.updateContact(currentContact, currentContact.getFirstName(), currentContact.getLastName());
				databaseHandler.close();
			} catch (SQLException sqle) {
				throw sqle;
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}



}

