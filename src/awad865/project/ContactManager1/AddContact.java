package awad865.project.ContactManager1;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class AddContact extends Activity {
	EditText firstName;
	EditText lastName;
	EditText number;
	EditText address;
	EditText date;
	EditText email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);

		getActionBar().setDisplayShowTitleEnabled(true);

		firstName = (EditText)findViewById(R.id.edit_first_name);
		lastName = (EditText)findViewById(R.id.edit_last_name);
		number = (EditText)findViewById(R.id.edit_number);
		address = (EditText)findViewById(R.id.edit_address);
		date = (EditText)findViewById(R.id.edit_date);
		email =(EditText)findViewById(R.id.edit_email);

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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_contact, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){

		switch(item.getItemId()){

		case R.id.action_save:
			Contact contact = new Contact(firstName.getText().toString(),lastName.getText().toString(),number.getText().toString(), address.getText().toString(), email.getText().toString(),date.getText().toString());
			MainActivity.displayList.add(contact);
			Intent intent_save = new Intent(getApplicationContext(),MainActivity.class);
			startActivity(intent_save);
			return true;

		case R.id.action_cancel:
			Intent intent_cancel = new Intent(this,MainActivity.class);
			startActivity(intent_cancel);
			return true;



		default:
			return super.onOptionsItemSelected(item);

		}
	}
}