package awad865.project.ContactManager1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static String DB_NAME = "ContactDb";
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
	
	public DatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.myContext = context;
	}
	
	public void createDataBase() throws IOException{
		boolean dbExist = checkDataBase();
		if(dbExist){
			//copyDataBase();
		}else{
			//By calling this method and empty database will be created into the default system path
			//of your application so we are gonna be able to overwrite that database with our database.
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

		//Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;
		//Open the empty db as the output stream
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
		if(myDataBase != null)
			myDataBase.close();
		super.close();
	}
	
	public void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FIRST_NAME, contact.get_firstName()); 
		values.put(LAST_NAME, contact.get_lastName()); 
		values.put(NUMBER, contact.get_number()); 
		values.put(NUMBER_TYPE, contact.get_numberType());
		values.put(EMAIL, contact.get_email()); 
		values.put(EMAIL_TYPE, contact.get_emailType());
		values.put(ADDRESS, contact.get_address()); 
		values.put(ADDRESS_TYPE, contact.get_addressType());
		values.put(DATE, contact.get_date()); 
		values.put(DATE_TYPE, contact.get_dateType());
		db.insert(TABLE_CONTACT, null, values);
		db.close(); // Closing database connection
	}
	
	// Deleting single item
		public void deleteContact(Contact contact) {
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_CONTACT,
					FIRST_NAME + "=? AND " + LAST_NAME + "=?", 
					new String[] {contact.get_firstName(), contact.get_lastName()});
			db.close();
		}
		
		public List<Contact> getContacts() {
			List<Contact> contactList = new ArrayList<Contact>();
			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_CONTACT;
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Contact contact = new Contact(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9));
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
