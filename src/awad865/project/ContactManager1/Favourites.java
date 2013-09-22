package awad865.project.ContactManager1;

import com.example.contactmanager1.R;

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
		case R.id.action_list_of_contacts:
			Intent contactIntent = new Intent(this,MainActivity.class);
			startActivity(contactIntent);
			return true;

		case R.id.action_groups:
			Intent groupIntent = new Intent(this,Groups.class);
			startActivity(groupIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}


	public void addContact(View view){
		Intent intent = new Intent(this,AddContact.class);
		startActivity(intent);

	}
}
