package awad865.project.ContactManager1;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class AddContact extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);

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

}
