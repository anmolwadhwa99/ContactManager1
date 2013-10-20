package awad865.project.ContactManager1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EditContact extends Activity {

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
	private Contact currentContact;

	private int pos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		databaseHandler = new DatabaseHandler(this);
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

		//Spinner for the date field
		dateSpinner = (Spinner) findViewById(R.id.contact_date_spinner);
		adapter=ArrayAdapter.createFromResource(this, 
				R.array.date_array, android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dateSpinner.setAdapter(adapter);

		//the private fields are now initialised using findViewById().

		firstName = (EditText)findViewById(R.id.edit_first_name);
		lastName = (EditText)findViewById(R.id.edit_last_name);
		number = (EditText)findViewById(R.id.edit_number);
		address = (EditText)findViewById(R.id.edit_address);
		date = (EditText)findViewById(R.id.edit_date);
		email =(EditText)findViewById(R.id.edit_email);

		// the intent and bundle from ViewContact is retrieved.
		Intent editContactIntent = getIntent();


		Bundle extras = editContactIntent.getExtras();
		// the first name and last name is retrieved from ViewContact
		String theFirstName=extras.getString("firstName");
		String theLastName=extras.getString("lastName");

		try {
			databaseHandler.openDataBase();
			currentContact = databaseHandler.getContact(theFirstName, theLastName);
			databaseHandler.close();
		} catch (SQLException sqle) {
			throw sqle;
		}

		//an enhanced for loop is created that runs through displayList and checks to see if the retrieved
		//first name and last name is the name in displayList. If it is, then it gets all the required information
		//from the EditText is retrieved.

		firstName.setText(currentContact.getFirstName());
		lastName.setText(currentContact.getLastName());
		number.setText(currentContact.getNumber());
		address.setText(currentContact.getAddress());
		date.setText(currentContact.getDate());
		email.setText(currentContact.getEmail());
		int numPos = ((ArrayAdapter<CharSequence>) numberSpinner.getAdapter()).getPosition(currentContact.getNumberType());
		numberSpinner.setSelection(numPos);
		int emailPos = ((ArrayAdapter<CharSequence>) emailSpinner.getAdapter()).getPosition(currentContact.getEmailType());
		emailSpinner.setSelection(emailPos);
		int addPos = ((ArrayAdapter<CharSequence>) addressSpinner.getAdapter()).getPosition(currentContact.getAddressType());
		addressSpinner.setSelection(addPos);
		int datePos = ((ArrayAdapter<CharSequence>) dateSpinner.getAdapter()).getPosition(currentContact.getDateType());
		dateSpinner.setSelection(datePos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_contact, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item){

		switch(item.getItemId()){
		//if the user presses the save button, then an dialog appears, asking if they are sure,
		//if the user clicks Save, then all the new changes are saved and they are directed back
		//to MainActivity. If they click cancel, then no changes take place
		case R.id.action_edit_save:

			AlertDialog.Builder dialog = new AlertDialog.Builder(EditContact.this);

			dialog.setTitle("Edit");
			dialog.setMessage("Do you wish to save your changes?");

			dialog.setNegativeButton("Cancel", null);
			dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					//Contact contact = new Contact(firstName.getText().toString(),lastName.getText().toString(),number.getText().toString(), address.getText().toString(), email.getText().toString(),date.getText().toString());
					//MainActivity.displayList.add(contact);
					//MainActivity.displayList.remove(pos);

					String retrieveFirstName = firstName.getText().toString();
					String retrieveLastName = lastName.getText().toString();
					String retrieveNumber = number.getText().toString();
					String retrieveEmail = email.getText().toString();
					String retrieveAddress = address.getText().toString();
					String retrieveDate = date.getText().toString();
					String retrieveNumberSpinner = numberSpinner.getSelectedItem().toString();
					String retrieveEmailSpinner = emailSpinner.getSelectedItem().toString();
					String retrieveAddressSpinner = addressSpinner.getSelectedItem().toString();
					String retrieveDateSpinner = dateSpinner.getSelectedItem().toString();
					Contact replaceContact = new Contact(retrieveFirstName, retrieveLastName, retrieveNumber, retrieveNumberSpinner, retrieveEmail, retrieveEmailSpinner, retrieveDate, retrieveDateSpinner, retrieveAddress, retrieveAddressSpinner,currentContact.getFavourite());

					try {
						databaseHandler.openDataBase();
						databaseHandler.updateContact(replaceContact, currentContact.getFirstName(), currentContact.getLastName());
						databaseHandler.close();
					} catch (SQLException sqle) {
						throw sqle;
					}
					Intent intentSave = new Intent(getApplicationContext(),MainActivity.class);
					intentSave.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intentSave);


				}
			});
			dialog.setCancelable(true);
			dialog.create().show();


			return true;
			//if the user clicks on the delete button, then a dialog appears asking the user if they
			//are sure. If the user clicks delete, the contact is deleted and they return back to MainActivity.
			//If they press cancel, then no changes take place.
		case R.id.action_edit_delete:

			AlertDialog.Builder dialogDelete = new AlertDialog.Builder(EditContact.this);

			dialogDelete.setTitle("Delete Contact?");
			dialogDelete.setMessage("Are you sure you would like to delete this contact ?");

			dialogDelete.setNegativeButton("Cancel", null);
			dialogDelete.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					try {
						databaseHandler.openDataBase();
						databaseHandler.deleteContact(firstName.getText().toString(), lastName.getText().toString());
						databaseHandler.close();
					} catch (SQLException sqle) {
						throw sqle;
					}
					MainActivity.displayList.remove(pos);
					Intent intentDelete = new Intent(getApplicationContext(),MainActivity.class);
					intentDelete.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intentDelete);

				}
			});
			dialogDelete.setCancelable(true);
			dialogDelete.create().show();
			return true;

			//if the user clicks on the cancel button, then they are taken back to MainActivity.

		default:
			return super.onOptionsItemSelected(item);

		case R.id.action_edit_cancel:
			Intent intentCancel = new Intent(this,MainActivity.class);
			intentCancel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentCancel);
			return true;
		}
	}



}