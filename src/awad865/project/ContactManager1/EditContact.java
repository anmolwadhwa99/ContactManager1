package awad865.project.ContactManager1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EditContact extends Activity {
	EditText firstName;
	EditText lastName;
	EditText number;
	EditText address;
	EditText  date;
	EditText email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
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
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_contact, menu);
		return true;
	}




	public void editContactView(View view){


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