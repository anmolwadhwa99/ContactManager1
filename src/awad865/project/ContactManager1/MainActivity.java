package awad865.project.ContactManager1;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
/**
 * This is the activity that opens by default when the application is run.
 * The purpose of this activity is to show the list of contacts the user 
 * has stored. The user can add a new contact, change the sorting order 
 * of the list by clicking on the addContact and moreOptions button in this 
 * activity.
 * 
 * @author Anmol Wadhwa (awad865, 5603097)
 *
 */
public class MainActivity extends Activity {

	//declare private fields
	private ListView listView;
	private DatabaseHandler databaseHandler;
	
	//this variable is used to manage the sorting order of the list. By default,
	//the contacts are sorted by first name.
	public static  String order = "firstname";

	//create public array list which stores all the contact objects that are to be displayed
	public static List <Contact> displayList = new ArrayList<Contact>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//remove the title and the android icon from the action bar
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);

		
		listView = (ListView)findViewById(R.id.main_contact_listview);
		databaseHandler = new DatabaseHandler(this);
		
		//when we first open the application, the database is created
		try {
			databaseHandler.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}


		setUpListView();
	}

	private void setUpListView(){

		try {
			//when the list view is opened, the database is opened
			//and the contacts are retrieved from the database and
			//are sorted by first name (by default).
			databaseHandler.openDataBase();
			displayList = databaseHandler.getContacts(order);
			databaseHandler.close();
		} catch(SQLException sqle) {
			throw sqle;
		}


		//creating list adapter to show the contacts from displayList to the ListView in activity_main.xml
		ListAdapter listAdapter = new CustomListAdapter(MainActivity.this,displayList);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new listItemClickedListener());

	}


	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			//if the favourites button is clicked on the action bar, then navigate
			//to the favourites activity
		case R.id.action_favourites:
			Intent intentFavourite = new Intent(this,Favourites.class);
			intentFavourite.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentFavourite);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_actions, menu);
		return true;
	}


	//when the image button addContact is pressed on the MainActivity.java
	//then this method is called and the user is navigated to the addContact activity
	public void addContact(View view){
		Intent intentAddContact = new Intent(this,AddContact.class);
		intentAddContact.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intentAddContact);

	}
	//when the image button moreOptions is pressed on the MainActivity.java
	//then this method is called and the user is navigated to the OptionActivity activity
	public void moreOptions(View view){
		Intent intentOptions = new Intent(this,OptionActivity.class);
		intentOptions.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intentOptions);

	}


	//method that implements AdapterView.OnItemClickListener, so if the user clicks on a contact inside
	//list view then the method inside this class is invoked.
	class listItemClickedListener implements AdapterView.OnItemClickListener{


		@Override
		public void onItemClick(AdapterView<?> parentView, View clickedView, int clickedViewPosition, long id) {
			//we get the first name and last name of the contact in the displayList
			String fname=displayList.get(clickedViewPosition).getFirstName();
			String lname=displayList.get(clickedViewPosition).getLastName();

			// TODO Auto-generated method stub
			//Using the bundle, we pass this information on to a new activity called ViewContact
			//so the user can view the contact (ViewContact displays all the information associated with
			//the contact they saved).
			Intent intentViewContact= new Intent(clickedView.getContext(), ViewContact.class);
			Bundle extras = new Bundle();
			//the first name and last name are passed to the ViewContact activity.
			extras.putString("firstName", fname);
			extras.putString("lastName", lname);
			intentViewContact.putExtras(extras);
			startActivity(intentViewContact);
		}



	}
}