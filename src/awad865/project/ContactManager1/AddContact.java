package awad865.project.ContactManager1;



import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class AddContact extends Activity {
	//declare private fields
	private EditText firstName;
	private EditText lastName;
	private EditText number;
	private EditText address;
	private EditText date;
	private EditText email;
	private Spinner numberSpinner;
	private Spinner emailSpinner;
	private Spinner addressSpinner;
	private Spinner dateSpinner;
	private DatabaseHandler databaseHandler;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		//code that enables the title on the action bar
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		databaseHandler = new DatabaseHandler(this);

		//intialise private fields
		firstName = (EditText)findViewById(R.id.edit_first_name);
		lastName = (EditText)findViewById(R.id.edit_last_name);
		number = (EditText)findViewById(R.id.edit_number);
		address = (EditText)findViewById(R.id.edit_address);
		date = (EditText)findViewById(R.id.edit_date);
		email =(EditText)findViewById(R.id.edit_email);

		
		//Spinner for the phone number field
		numberSpinner = (Spinner) findViewById(R.id.contact_number_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.number_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		numberSpinner.setAdapter(adapter);


		//Spinner for the email address field
		emailSpinner = (Spinner) findViewById(R.id.contact_email_spinner);
		adapter = ArrayAdapter.createFromResource(this, 
				R.array.email_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		emailSpinner.setAdapter(adapter);
		
		//Spinner for address field
		addressSpinner = (Spinner) findViewById(R.id.contact_address_spinner);
		adapter= ArrayAdapter.createFromResource(this,
				R.array.address_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		addressSpinner.setAdapter(adapter);
		
		//Spinner for date
		dateSpinner = (Spinner) findViewById(R.id.contact_date_spinner);
		adapter=ArrayAdapter.createFromResource(this, 
				R.array.date_array, android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dateSpinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_contact, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){

		switch(item.getItemId()){
		//if the save button is pressed, then all the information is retrieved from the EditText fields
		//and stored in the private fields and then a new contact object is created and added to the 
		//database
		case R.id.action_save:
			Contact contact = new Contact(firstName.getText().toString(),lastName.getText().toString(),number.getText().toString(), numberSpinner.getSelectedItem().toString(), email.getText().toString(), emailSpinner.getSelectedItem().toString(), date.getText().toString(), dateSpinner.getSelectedItem().toString(), address.getText().toString(), addressSpinner.getSelectedItem().toString(), "false");
			//add to database
			try {
				databaseHandler.openDataBase();
				databaseHandler.addContact(contact);
				databaseHandler.close();
			} catch (SQLException sqle) {
				throw sqle;
			}
			//go back to list of contacts
			Intent intentMain = new Intent(getApplicationContext(),MainActivity.class);
			intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentMain);
			return true;

			//if the cancel button is pressed on the action bar then the user is navigate to MainActivity
		case R.id.action_cancel:
			Intent intentCancel = new Intent(this,MainActivity.class);
			intentCancel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentCancel);
			return true;
		//if the up button is pressed, then the user is taken back to the MainActivity
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}
	}
}