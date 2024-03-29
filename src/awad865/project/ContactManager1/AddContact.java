package awad865.project.ContactManager1;



import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
/**
 * The purpose of this class is for the user to add a new contact in the contact manager and this is stored
 * in the database.
 * @author Anmol Wadhwa, awad865, 5603097
 */

public class AddContact extends Activity {

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
	private ImageButton addPic;
	private final int IMAGE_SELECTION =1;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		//code that enables the title on the action bar and the up button
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		databaseHandler = new DatabaseHandler(this);

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

		addPic = (ImageButton) findViewById(R.id.addImage);
		//set default image
		int id = getResources().getIdentifier("awad865.project.ContactManager1:drawable/contacts_photo", null, null);

		addPic.setImageResource(id);

		addPic.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//if the user clicks on the image view then take him to the camera to take an image.
				Intent imageIntent = new Intent("android.media.action.IMAGE_CAPTURE");
				startActivityForResult(imageIntent, IMAGE_SELECTION);

			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent){
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

		//if the value in IMAGE_SELECTION is selected
		switch(requestCode){
		case IMAGE_SELECTION:
			if(resultCode == RESULT_OK){
				//get the image as a bitmap
				Bundle extras = imageReturnedIntent.getExtras();
				Bitmap bmp = (Bitmap) extras.get("data");
				Uri imUri = imageReturnedIntent.getData();
				addPic.setImageBitmap(bmp);
			}
		}

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
		//and ImageButton and then a new contact object is created and added to the 
		//database. 
		case R.id.action_save:
			//get the bitmap and convert it to a byte array
			BitmapDrawable bmd = ((BitmapDrawable) addPic.getDrawable());
			Bitmap photo = bmd.getBitmap();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			photo.compress(Bitmap.CompressFormat.PNG, 90, stream);
			byte[] byteArray = stream.toByteArray();
			//if the user does not add a first name, then a error 
			//is thrown telling the user that they need to add the first name.
			if(firstName.getText().toString().length() == 0) {
				firstName.setError("A first name for the contact must be provided");
			} else {
				Contact contact = new Contact(firstName.getText().toString(),lastName.getText().toString(),number.getText().toString(), numberSpinner.getSelectedItem().toString(), email.getText().toString(), emailSpinner.getSelectedItem().toString(), date.getText().toString(), dateSpinner.getSelectedItem().toString(), address.getText().toString(), addressSpinner.getSelectedItem().toString(), byteArray, "false");

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
			}
			return true;

		case R.id.action_cancel:
			Intent intentCancel = new Intent(this,MainActivity.class);
			intentCancel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentCancel);
			return true;
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}

	}



}