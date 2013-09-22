package awad865.project.ContactManager1;


import java.util.ArrayList;
import java.util.List;

import com.example.contactmanager1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;


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
		List <String> displayList = new ArrayList<String>();
		displayList.add("Display Item 1");
		displayList.add("Display Item 2");
		displayList.add("Display Item 3");


		ListAdapter listAdapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1,displayList);
		listView.setAdapter(listAdapter);

	}

	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.action_groups:
			Intent groupIntent = new Intent(this,Groups.class);
			startActivity(groupIntent);
			return true;

		case R.id.action_favourites:
			Intent favouriteIntent = new Intent(this,Favourites.class);
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


}