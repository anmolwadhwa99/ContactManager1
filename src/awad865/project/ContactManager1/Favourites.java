package awad865.project.ContactManager1;

//The purpose of this activity is to display a list of frequently contacted/viewed contacts

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

public class Favourites extends Activity {

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
		//adding contact objects inside the public array list displayList if only 
		//size of this list is zero.

		/*if(displayList.size()==0){
			displayList.add(new Contact("Anmol","Wadhwa","5374363","","",""));
			displayList.add(new Contact("Juhi","Goswami","4234232","","",""));
			displayList.add(new Contact("Laurence","Baldwick","243232","","",""));	
		}*/
		try {
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
			//if the "Groups" button is pressed on the action bar, then navigate the user
			//to Groups activity.
		
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

	class listItemClickedListener implements AdapterView.OnItemClickListener{


		@Override
		public void onItemClick(AdapterView<?> parentView, View clickedView, int clickedViewPosition, long id) {
			//we get the first name and last name of the contact in the displayList
			String fname=displayList.get(clickedViewPosition).getFirstName();
			String lname=displayList.get(clickedViewPosition).getLastName();

			// TODO Auto-generated method stub
			//Using the bundle, we pass this information on to a new activity called ViewContact
			//this is used for the view contact use case.
			Intent viewContactIntent= new Intent(clickedView.getContext(), ViewContact.class);
			Bundle extras = new Bundle();

			extras.putString("firstName", fname);
			extras.putString("lastName", lname);
			viewContactIntent.putExtras(extras);
			startActivity(viewContactIntent);
		}



	}

}
