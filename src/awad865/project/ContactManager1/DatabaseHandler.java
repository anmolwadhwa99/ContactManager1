package awad865.project.ContactManager1;



import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

/**
 * The purpose of this class is to help manage the database. Activities in the application
 * call specific methods that are declared in this class. This class sets up the database,
 * gets contacts, gets contacts that are tagged as favourite and is used to change the sorting order of the
 * contacts.
 * @author Anmol Wadhwa (awad865, 5603097)

 *
 *This class was written in collaboration with JUHI GOSWAMI (jgos311)
 */
public class DatabaseHandler extends SQLiteOpenHelper {

	//declare and initialise private constants.
	private static String DB_NAME = "ContactsDb";
	private static final int DB_VERSION = 1;
	private static String DB_PATH = "/data/data/awad865.project.ContactManager1/databases/";
	private final Context myContext;
	private SQLiteDatabase myDataBase;
	private static final String TABLE_CONTACT = "Contact";
	private static final String FIRST_NAME = "firstname";
	private static final String LAST_NAME = "lastname";
	private static final String NUMBER = "number";
	private static final String NUMBER_TYPE = "numbertype";
	private static final String EMAIL = "email";
	private static final String EMAIL_TYPE = "emailtype";
	private static final String DATE = "date";
	private static final String DATE_TYPE = "datetype";
	private static final String ADDRESS = "address";
	private static final String ADDRESS_TYPE = "addresstype";
	private static final String IMAGE = "image";
	private static final String FAVOURITE = "favourite";


	//the constructor is set up
	public DatabaseHandler(Context context) {
		//the parent constructor is called
		super(context, DB_NAME, null, DB_VERSION);
		//myContext field is initialized
		this.myContext = context;
	}
	//method for creating the database for the first time
	public void createDataBase() throws IOException{
		boolean dbExist = checkDataBase();
		if(dbExist){
			//copyDataBase();
		}else{
			//By calling this method an empty database will be created into the default system path
			//of the application so we are gonna be able to overwrite that database with our database in the phone.
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;
		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
			//database does't exist yet.
		}
		if(checkDB != null){
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}

	private void copyDataBase() throws IOException{

		//Open your local database as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		// Path to the just created empty database
		String outFileName = DB_PATH + DB_NAME;
		//Open the empty database as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}
		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException{
		//Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	}

	@Override
	public synchronized void close() {
		//close the database
		if(myDataBase != null)
			myDataBase.close();
		super.close();
	}
	//method for adding a contact to the database
	public void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//put all the appropriate edit text fields in AddContact and store them in the database.
		values.put(FIRST_NAME, contact.getFirstName()); 
		values.put(LAST_NAME, contact.getLastName()); 
		values.put(NUMBER, contact.getNumber()); 
		values.put(NUMBER_TYPE, contact.getNumberType());
		values.put(EMAIL, contact.getEmail()); 
		values.put(EMAIL_TYPE, contact.getEmailType());
		values.put(ADDRESS, contact.getAddress()); 
		values.put(ADDRESS_TYPE, contact.getAddressType());
		values.put(DATE, contact.getDate()); 
		values.put(DATE_TYPE, contact.getDateType());
		values.put(IMAGE, contact.getImage());
		values.put(FAVOURITE, "false");
		db.insert(TABLE_CONTACT, null, values);
		db.close(); // Closing database connection
	}

	// Deleting single contact
	public void deleteContact(String firstName, String lastName) {
		SQLiteDatabase db = this.getWritableDatabase();
		//look for the first name and last name of that contact, and then
		//delete the whole row in the database
		db.delete(TABLE_CONTACT,
				FIRST_NAME + "=? AND " + LAST_NAME + "=?", 
				new String[] {firstName, lastName});
		db.close();
	}

	//this method is used for editing a contact
	public int updateContact(Contact contact, String firstName, String lastName) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//we first find the existing contact, and overwrite the old
		//values with the new values that the user has put
		values.put(FIRST_NAME, contact.getFirstName()); 
		values.put(LAST_NAME, contact.getLastName());
		values.put(NUMBER, contact.getNumber()); 
		values.put(NUMBER_TYPE, contact.getNumberType());
		values.put(EMAIL, contact.getEmail()); 
		values.put(EMAIL_TYPE, contact.getEmailType());
		values.put(ADDRESS, contact.getAddress()); 
		values.put(ADDRESS_TYPE, contact.getAddressType());
		values.put(DATE, contact.getDate()); 
		values.put(DATE_TYPE, contact.getDateType());
		values.put(IMAGE, contact.getImage());
		values.put(FAVOURITE, contact.getFavourite());

		// updating row
		return db.update(TABLE_CONTACT, values, FIRST_NAME + "=? AND " + LAST_NAME + "=?",
				new String[] {firstName, lastName});
	}
	//this method is used to find all the contacts that are selected as a favourite by the user
	//retrieve those contacts
	public List<Contact> getFavouriteContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
		String isFavourite = "true";
		// Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_CONTACT + " WHERE " + FAVOURITE + "='" + isFavourite + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(11));
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		return contactList;
	}


	public Contact getContact(String firstName, String lastName) {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_CONTACT + " WHERE " + FIRST_NAME + "='" + firstName + "' AND " + LAST_NAME + "='" + lastName + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(11));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		return contactList.get(0);
	}



	//this method is used to get all the contacts in the database. One argument is taken 
	//in this method and that is order in which the contacts will be displayed
	public List<Contact> getContacts(String order) {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		//this bottom line is used to change the sorting order of the contact list
		String selectQuery = "SELECT * FROM " + TABLE_CONTACT +" ORDER BY " + order;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(11));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		return contactList;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
