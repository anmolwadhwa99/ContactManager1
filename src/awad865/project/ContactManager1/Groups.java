package awad865.project.ContactManager1;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Groups extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groups);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);
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


}

