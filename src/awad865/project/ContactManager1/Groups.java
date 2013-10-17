package awad865.project.ContactManager1;


//The purpose of this activity is to display to the user groups in which contacts can be put in.
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Groups extends Activity {
	//declare private fields
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groups);
		//code that gets rid of the launcher icon and name of the activity in the action bar.
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);
		//initialise the private field
		listView = (ListView) findViewById(R.id.main_group_listview);
		setUpListView();

	}
	//add three groups into the array list, and the ListAdapter is used to display on them on ListView.
	private void setUpListView(){
		List<String> displayList = new ArrayList<String>();
		displayList.add("Coworkers");
		displayList.add("Family");
		displayList.add("Friends");

		ListAdapter listAdapter = new ArrayAdapter<String>(Groups.this,
				android.R.layout.simple_list_item_1,displayList);
		listView.setAdapter(listAdapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);

		return super.onCreateOptionsMenu(menu);
	}



	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		//if the list of contacts button is pressed on the action bar, the user is directed to the MainActivity.
		case R.id.action_list_of_contacts:
			Intent contactIntent = new Intent(this,MainActivity.class);
			contactIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(contactIntent);
			return true;
			//if the user presses the favourites button then they are directed to the favourites activity.
		case R.id.action_favourites:
			Intent favouriteIntent = new Intent(this,Favourites.class);
			favouriteIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(favouriteIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}

	//if the user presses the add group image button (bottom of the screen (center) in groups activity) then
	//this method is invoked and a new activity (called addGroup is started).
	public void addGroup(View view){
		Intent group_intent = new Intent(this,AddGroup.class);
		group_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(group_intent);
	}

	//when the image button moreOptions is pressed on the MainActivity.java
	//then this method is called and the user is navigated to the OptionActivity activity
	public void moreOptions(View view){
		Intent option_intent = new Intent(this,OptionActivity.class);
		option_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(option_intent);

	}



}

