package awad865.project.ContactManager1;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;

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
		//adding contact objects inside the public array list displayList if only 
		//size of this list is zero.

		/*if(displayList.size()==0){
			displayList.add(new Contact("Anmol","Wadhwa","5374363","","",""));
			displayList.add(new Contact("Juhi","Goswami","4234232","","",""));
			displayList.add(new Contact("Laurence","Baldwick","243232","","",""));	
		}*/
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

	//create a custom list adapter so that we can customize the list to suit 
	//our needs
	private class CustomListAdapter extends ArrayAdapter<Contact>{

		private Context _context;
		private List<Contact> _contacts;

		public CustomListAdapter(Context context, List<Contact> contacts){
			super(context,android.R.layout.simple_list_item_1,contacts);

			_context = context;
			_contacts = contacts;

		}


		public View getView(int position, View convertView,ViewGroup parent){
			//Create a layout inflater to inflate our xml layout for each item in the list
			LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			//Inflate the list item layout. Keep a reference to the inflated view.
			//No root view specified
			View listItemView = inflater.inflate(R.layout.custom_list_item_layout,null);

			//Access TextView elements inside the view (note we must specify the parent view
			//to look in)
			TextView firstName =  (TextView)listItemView.findViewById(R.id.list_item_firstname);
			TextView lastName =  (TextView)listItemView.findViewById(R.id.list_item_lastname);
			TextView number =  (TextView)listItemView.findViewById(R.id.list_item_number);

			//Set the text for each textview (use the position argument to find the appropriate element in the list)
			firstName.setText(_contacts.get(position).get_firstName());
			lastName.setText(_contacts.get(position).get_lastName());
			number.setText(_contacts.get(position).get_number());

			return listItemView;
		}

	}

	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		//if the group button is clicked on the action bar, then navigate 
		//to the group activity
		case R.id.action_groups:
			Intent groupIntent = new Intent(this,Groups.class);
			groupIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(groupIntent);
			return true;
			//if the favourites button is clicked on the action bar, then navigate
			//to the favourites activity
		case R.id.action_favourites:
			Intent favouriteIntent = new Intent(this,Favourites.class);
			favouriteIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(favouriteIntent);
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
		Intent intent = new Intent(this,AddContact.class);
		startActivity(intent);

	}
	//when the image button moreOptions is pressed on the MainActivity.java
	//then this method is called and the user is navigated to the OptionActivity activity
	public void moreOptions(View view){
		Intent intent = new Intent(this,OptionActivity.class);
		startActivity(intent);

	}


	//method that implements AdapterView.OnItemClickListener, so if the user clicks on a contact inside
	//list view then this method is invoked
	class listItemClickedListener implements AdapterView.OnItemClickListener{


		@Override
		public void onItemClick(AdapterView<?> parentView, View clickedView, int clickedViewPosition, long id) {
			//we get the first name and last name of the contact in the displayList
			String fname=displayList.get(clickedViewPosition).get_firstName();
			String lname=displayList.get(clickedViewPosition).get_lastName();

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