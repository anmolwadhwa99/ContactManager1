package awad865.project.ContactManager1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ViewContact extends Activity {

	EditText firstName;
	EditText lastName;
	EditText number;
	EditText address;
	EditText  date;
	EditText email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_contact);

		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

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

		Intent intent = getIntent();

		Bundle extras = intent.getExtras();
		String theFirstName = extras.getString("firstName");
		String theLastName = extras.getString("lastName");

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

		case R.id.action_edit_contact:
			firstName = (EditText)findViewById(R.id.edit_first_name);
			lastName = (EditText)findViewById(R.id.edit_last_name);
			number = (EditText)findViewById(R.id.edit_number);
			address = (EditText)findViewById(R.id.edit_address);
			date = (EditText)findViewById(R.id.edit_date);
			email =(EditText)findViewById(R.id.edit_email);

			String fName = firstName.getText().toString();
			String lName = lastName.getText().toString();

			Intent newIntent = new Intent(this,EditContact.class);

			Bundle extras = new Bundle();
			extras.putString("firstName", fName);
			extras.putString("lastName", lName);
			newIntent.putExtras(extras);
			startActivity(newIntent);
			return true;

		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;


		default:
			return super.onOptionsItemSelected(item);

		}
	}



}

