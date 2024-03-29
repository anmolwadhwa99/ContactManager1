package awad865.project.ContactManager1;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
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
	private ImageView image;


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
		image = (ImageView) findViewById(R.id.viewImage);
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
		Bitmap bitMapResource = BitmapFactory.decodeByteArray(currentContact.getImage(), 0, currentContact.getImage().length);
		if(bitMapResource != null) {
			image.setImageBitmap(bitMapResource);
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
			//they are directed to the dialer and the number is dialed. If the user does
			//provide a number, then a Toast message is thrown telling the use that
			//they have not provided a number
		case R.id.button_call:
			if(!number.getText().toString().equals("")){
				try {
					Intent intentCall = new Intent(Intent.ACTION_CALL);
					intentCall.setData(Uri.parse("tel:"+number.getText().toString()));
					intentCall.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intentCall);
				} catch (ActivityNotFoundException activityException) {
					Log.e("Calling a Phone Number", "Call failed", activityException);
				}
			}else{
				Toast.makeText(ViewContact.this, "No number provided", Toast.LENGTH_LONG).show();
			}
			return true;
			//if the messaging button is pressed, then the user 
			//is navigated to the messaging application and they can
			//compose a message to the number saved. If no number is 
			//provided, then a toast message is thrown telling 
			//the user a number is not provided.
		case R.id.button_messaging:
			if(!number.getText().toString().equals("")){
				Intent intentMessage = new Intent(Intent.ACTION_SENDTO, 
						Uri.fromParts("sms", number.getText().toString(), null));
				intentMessage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intentMessage);
			}else{
				Toast.makeText(ViewContact.this, "No number provided", Toast.LENGTH_LONG).show();

			}
			return true;

			//if the user clicks on the favourite icon, then a Toast is thrown 
			//telling the user the contact has been added to Favourites. If the user clicks
			// on the icon again, then another Toast message is thrown telling the user the
			//contact has been removed from favourites.
		case R.id.button_favourite:
			if(currentContact.getFavourite().equals("false")) {
				currentContact.setFavourite("true");
				Toast.makeText(ViewContact.this, "Contact has been added to Favourites", Toast.LENGTH_LONG).show();
			} else {
				currentContact.setFavourite("false");
				Toast.makeText(ViewContact.this, "Contact has been removed from Favourites", Toast.LENGTH_LONG).show();
			}
			//we add the value of variable favourite back into the database by updating the database
			try {
				databaseHandler.openDataBase();
				databaseHandler.updateContact(currentContact, currentContact.getFirstName(), currentContact.getLastName());
				databaseHandler.close();
			} catch (SQLException sqle) {
				throw sqle;
			}
			return true;


			//If the user presses the email icon, then they are taken to email application and
			//they can compose an email there. However, if the user does not provide an email address,
			//then a Toast message is thrown telling the user that they have not added an email address.
		case R.id.button_email:
			if(!email.getText().toString().equals("")){
				try{
					//the intent to send
					Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
					//setting the type to be plain text
					emailIntent.setType("message/rfc822");

					//Variables to contain receiver, and preset subject and message
					String receiver = email.getText().toString();
					String subject= "Subject";
					String body = "Message here";

					//Setting the receiver
					emailIntent.setData(Uri.parse("mailto:"+ receiver));
					//Putting in the preset-subject line into the intent
					emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
					//Putting the preset-message into the intent
					emailIntent.putExtra(Intent.EXTRA_TEXT, body);
					//Bringing up email composer or email chooser

					emailIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

					startActivity(emailIntent);


				}catch(ActivityNotFoundException ex){
					//Logging failure into log-cat
					Log.e("Creating email","Emailing failed",ex);
				}
			}else{
				Toast.makeText(ViewContact.this, "No email address provided", Toast.LENGTH_LONG).show();
			}


			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}



}

