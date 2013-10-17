package awad865.project.ContactManager1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

//The purpose of this activity is that the user can add a new group inside the Contact Manager and he 
//can group contacts together
public class AddGroup extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_group);
		//code to enable the title on the action bar
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		//if the cancel button is pressed by the user, this means that the user does not want
		//to add a new group, and the user is returned to the list of groups.
		case R.id.group_cancel:
			Intent cancelIntent = new Intent(this,Groups.class);
			cancelIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(cancelIntent);

		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;



		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_group, menu);
		return true;
	}



}
