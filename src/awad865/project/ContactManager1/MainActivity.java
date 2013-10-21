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

public class MainActivity extends Activity {

	//initialise private fields
	private ListView listView;
	private DatabaseHandler databaseHandler;
	public static  String order = "firstname";




	//create public array list which stores all the contact objects that are to be displayed
	public static List <Contact> displayList = new ArrayList<Contact>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//remove the title and the android icon from the actionbar
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);


		//link the image buttons to the .java file by using the findViewById() method
		listView = (ListView)findViewById(R.id.main_contact_listview);
		databaseHandler = new DatabaseHandler(this);
		try {
			databaseHandler.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}


		setUpListView();
	}

	private void setUpListView(){

		try {
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
		//if the group button is clicked on the action bar, then navigate 
		//to the group activity
		case R.id.action_groups:
			Intent intentGroup = new Intent(this,Groups.class);
			intentGroup.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentGroup);
			return true;
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
	//list view then this method is invoked
	class listItemClickedListener implements AdapterView.OnItemClickListener{


		@Override
		public void onItemClick(AdapterView<?> parentView, View clickedView, int clickedViewPosition, long id) {
			//we get the first name and last name of the contact in the displayList
			String fname=displayList.get(clickedViewPosition).getFirstName();
			String lname=displayList.get(clickedViewPosition).getLastName();

			// TODO Auto-generated method stub
			//Using the bundle, we pass this information on to a new activity called ViewContact
			//this is used for the view contact use case.
			Intent intentViewContact= new Intent(clickedView.getContext(), ViewContact.class);
			Bundle extras = new Bundle();

			extras.putString("firstName", fname);
			extras.putString("lastName", lname);
			intentViewContact.putExtras(extras);
			startActivity(intentViewContact);
		}



	}
}