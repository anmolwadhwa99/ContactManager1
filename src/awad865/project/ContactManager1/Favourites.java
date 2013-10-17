package awad865.project.ContactManager1;

//The purpose of this activity is to display a list of frequently contacted/viewed contacts

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Favourites extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favourites);

		//code that disables the launcher icon and title from the action bar
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);
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
		case R.id.action_groups:
			Intent groupIntent = new Intent(this,Groups.class);
			groupIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(groupIntent);
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

}
