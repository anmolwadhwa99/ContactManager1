package awad865.project.ContactManager1;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * The purpose of this class is to create a custom list adapter for the ListView to
 * be used in the Favourites and MainActivity activities.
 * @author Anmol Wadhwa (awad865, 5603097)
 *
 */

public class CustomListAdapter extends ArrayAdapter<Contact>{

	//declare private fields
	private Context context;
	private List<Contact> contacts;

	//set up constructor to initialize private fields
	public CustomListAdapter(Context context, List<Contact> contacts){
		super(context,android.R.layout.simple_list_item_1,contacts);

		this.context = context;
		this.contacts = contacts;

	}


	public View getView(int position, View convertView,ViewGroup parent){
		//Create a layout inflater to inflate our xml layout for each item in the list
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		//Inflate the list item layout. Keep a reference to the inflated view.
		View listItemView = inflater.inflate(R.layout.custom_list_item_layout,null);

		//Access TextView elements inside the view to display in the ListView
		TextView firstName =  (TextView)listItemView.findViewById(R.id.list_item_firstname);
		TextView lastName =  (TextView)listItemView.findViewById(R.id.list_item_lastname);
		TextView number =  (TextView)listItemView.findViewById(R.id.list_item_number);

		//Set the text for each textview using the contacts list of contacts to find the appropriate contact
		firstName.setText(contacts.get(position).getFirstName());
		lastName.setText(contacts.get(position).getLastName());
		number.setText(contacts.get(position).getNumber());

		//return the view
		return listItemView;
	}

}
