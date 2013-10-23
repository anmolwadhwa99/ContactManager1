package awad865.project.ContactManager1;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import awad865.project.ContactManager1.MainActivity.listItemClickedListener;

/**
 * The purpose of this activity is for users to view a contact and mark that contact
 * as a favourite. By doing that, the contact appears in this activity. By having this functionality, 
 * the user doesn't have to search for contacts in the list and can go to 
 * this activity to look for them. This make it convenient for the user to 
 * view contacts that are regularly contacted.
 * @author Anmol Wadhwa (awad865, 5603097)
 */
public class Favourites extends Activity {
	//declare private fields
	private ListView listView;
	private DatabaseHandler databaseHandler;
	private List <Contact> displayList = new ArrayList<Contact>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favourites);
		databaseHandler = new DatabaseHandler(this);
		listView = (ListView) findViewById(R.id.main_favourite_listview);

		//code that disables the launcher icon and title from the action bar
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);
		setUpListView();

	}

	private void setUpListView(){

		try {
			//we open up the database and look for the contacts that are marked as favourite.
			databaseHandler.openDataBase();
			displayList = databaseHandler.getFavouriteContacts();
			databaseHandler.close();
		} catch(SQLException sqle) {
			throw sqle;
		}


		//creating list adapter to show the contacts from displayList to the ListView in activity_main.xml
		ListAdapter listAdapter = new CustomListAdapter(this,displayList);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new listItemClickedListener());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_actions, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		//if the list of contacts button is pressed (on the action bar) then navigate the user
		//to the MainActivity
		case R.id.action_list_of_contacts:
			Intent contactIntent = new Intent(this,MainActivity.class);
			contactIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(contactIntent);
			return true;


		default:
			return super.onOptionsItemSelected(item);

		}
	}

	//If the user presses the overflow button (the button on the bottom right) then this
	//method is triggered and the user is navigated to a new screen (the Option activity).

	public void moreOptions(View view){
		Intent option_intent = new Intent(this,OptionActivity.class);
		option_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(option_intent);

	}
	//if the user clicks on an item in the ListView, then the first name 
	//and last name are stored in variables and passed as a bundle 
	//to the ViewContact activity and the ViewContact activity is opened.
	class listItemClickedListener implements AdapterView.OnItemClickListener{


		@Override
		public void onItemClick(AdapterView<?> parentView, View clickedView, int clickedViewPosition, long id) {
			String fname=displayList.get(clickedViewPosition).getFirstName();
			String lname=displayList.get(clickedViewPosition).getLastName();

			// TODO Auto-generated method stub

			Intent viewContactIntent= new Intent(clickedView.getContext(), ViewContact.class);
			Bundle extras = new Bundle();

			extras.putString("firstName", fname);
			extras.putString("lastName", lname);
			viewContactIntent.putExtras(extras);
			startActivity(viewContactIntent);
		}



	}

}
