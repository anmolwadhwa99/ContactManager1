package awad865.project.ContactManager1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EditContact extends Activity {
	EditText firstName;
	EditText lastName;
	EditText number;
	EditText address;
	EditText  date;
	EditText email;

	private int pos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);

		//Spinner for the phone number field
		Spinner spinner = (Spinner) findViewById(R.id.contact_number_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.number_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);


		//Spinner for the email address field

		spinner = (Spinner) findViewById(R.id.contact_email_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter = ArrayAdapter.createFromResource(this, 
				R.array.email_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);

		//Spinner for address field
		spinner = (Spinner) findViewById(R.id.contact_address_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter= ArrayAdapter.createFromResource(this,
				R.array.address_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);

		//Spinner for the date field
		spinner = (Spinner) findViewById(R.id.contact_date_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter=ArrayAdapter.createFromResource(this, 
				R.array.date_array, android.R.layout.simple_spinner_dropdown_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner


		spinner.setAdapter(adapter);
		firstName = (EditText)findViewById(R.id.edit_first_name);
		lastName = (EditText)findViewById(R.id.edit_last_name);
		number = (EditText)findViewById(R.id.edit_number);
		address = (EditText)findViewById(R.id.edit_address);
		date = (EditText)findViewById(R.id.edit_date);
		email =(EditText)findViewById(R.id.edit_email);

		Intent editContactIntent = getIntent();

		Bundle extras = editContactIntent.getExtras();
		String theFirstName=extras.getString("firstName");
		String thelastName=extras.getString("lastName");

		for(Contact contact : MainActivity.displayList){
			if((contact.get_firstName().equals(theFirstName)) && (contact.get_lastName().equals(thelastName))){
				firstName.setText(contact.get_firstName());
				lastName.setText(contact.get_lastName());
				number.setText(contact.get_number());
				address.setText(contact.get_address());
				date.setText(contact.get_date());
				email.setText(contact.get_email());
				pos = MainActivity.displayList.indexOf(contact);
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_contact, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item){

		switch(item.getItemId()){

		case R.id.action_edit_save:

			AlertDialog.Builder dialog = new AlertDialog.Builder(EditContact.this);

			dialog.setTitle("Edit");
			dialog.setMessage("Save changes?");

			dialog.setNegativeButton("Cancel", null);
			dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					Contact contact = new Contact(firstName.getText().toString(),lastName.getText().toString(),number.getText().toString(), address.getText().toString(), email.getText().toString(),date.getText().toString());
					MainActivity.displayList.add(contact);
					MainActivity.displayList.remove(pos);
					Intent intent_save = new Intent(getApplicationContext(),MainActivity.class);
					startActivity(intent_save);

				}
			});
			dialog.setCancelable(true);
			dialog.create().show();


			return true;
			
		case R.id.action_edit_delete:

			AlertDialog.Builder dialogDelete = new AlertDialog.Builder(EditContact.this);

			dialogDelete.setTitle("Delete Contact?");
			dialogDelete.setMessage("This cannot be undone!");

			dialogDelete.setNegativeButton("Cancel", null);
			dialogDelete.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					MainActivity.displayList.remove(pos);
					Intent intent_delete = new Intent(getApplicationContext(),MainActivity.class);
					startActivity(intent_delete);

				}
			});
			dialogDelete.setCancelable(true);
			dialogDelete.create().show();

			


		default:
			return super.onOptionsItemSelected(item);
		}
	}





	public void editContactView(View view){


		firstName = (EditText)findViewById(R.id.edit_first_name);
		lastName = (EditText)findViewById(R.id.edit_last_name);
		number = (EditText)findViewById(R.id.edit_number);
		address = (EditText)findViewById(R.id.edit_date);
		email =(EditText)findViewById(R.id.edit_email);


		Intent editContactIntent = getIntent();

		Bundle extras = editContactIntent.getExtras();
		String theFirstName=extras.getString("firstName");
		String thelastName=extras.getString("lastName");

		for(Contact contact : MainActivity.displayList){
			if((contact.get_firstName().equals(theFirstName)) && (contact.get_lastName().equals(thelastName))){
				contact.set_firstName(firstName.getText().toString());
				contact.set_lastName(lastName.getText().toString());
				contact.set_number(number.getText().toString());
				contact.set_address(address.getText().toString());
				contact.set_date(date.getText().toString());
				contact.set_email(email.getText().toString());

			}

		}

	}
}