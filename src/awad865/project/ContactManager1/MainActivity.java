package awad865.project.ContactManager1;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;

public class MainActivity extends Activity {

	private ListView listView;
	private ImageButton button1;
	private ImageButton button2;
	private ImageButton button3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);

		listView = (ListView)findViewById(R.id.main_contact_listview);
		button1= (ImageButton)findViewById(R.id.button_search);
		button2= (ImageButton)findViewById(R.id.button_addcontact);
		button3= (ImageButton)findViewById(R.id.button_options);



		setUpListView();

	}

	private void setUpListView(){
		List <Contact> displayList = new ArrayList<Contact>();
		displayList.add(new Contact("Anmol","Wadhwa","5374363"));
		displayList.add(new Contact("Juhi","Goswami","4234232"));
		displayList.add(new Contact("Laurence","Baldwick","243232"));



		ListAdapter listAdapter = new CustomListAdapter(MainActivity.this,displayList);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new listItemClickedListener());

	}

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

			//Set the text for each textview (use the position arugment to find the appropriate element in the list)
			firstName.setText(_contacts.get(position).getFirstName());
			lastName.setText(_contacts.get(position).getLastName());
			number.setText(_contacts.get(position).getNumber());

			return listItemView;
		}

	}

	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.action_groups:
			Intent groupIntent = new Intent(this,Groups.class);
			groupIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(groupIntent);
			return true;

		case R.id.action_favourites:
			Intent favouriteIntent = new Intent(this,Favourites.class);
			favouriteIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(favouriteIntent);
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



	public void addContact(View view){
		Intent intent = new Intent(this,AddContact.class);
		startActivity(intent);

	}

	public void moreOptions(View view){
		Intent intent = new Intent(this,OptionActivity.class);
		startActivity(intent);

	}



	class listItemClickedListener implements AdapterView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parentView, View clickedView, int clickedViewPosition, long id) {
			// TODO Auto-generated method stub
			Intent contactInfoIntent = new Intent(clickedView.getContext(), ContactInformation.class);
			clickedView.getContext().startActivity(contactInfoIntent);
		}



	}
}