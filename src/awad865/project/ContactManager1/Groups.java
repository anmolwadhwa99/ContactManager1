package awad865.project.ContactManager1;



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
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Groups extends Activity {

	private ListView listView;
	private ImageButton button1;
	private ImageButton button2;
	private ImageButton button3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groups);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);

		listView = (ListView) findViewById(R.id.main_group_listview);
		setUpListView();

	}

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
		case R.id.action_list_of_contacts:
			Intent contactIntent = new Intent(this,MainActivity.class);
			contactIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(contactIntent);
			return true;

		case R.id.action_favourites:
			Intent favouriteIntent = new Intent(this,Favourites.class);
			favouriteIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(favouriteIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}


	public void addGroup(View view){
		Intent intent = new Intent(this,AddGroup.class);
		startActivity(intent);
	}

	public void moreOptions(View view){
		Intent intent = new Intent(this,OptionActivity.class);
		startActivity(intent);

	}



}

