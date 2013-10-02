package awad865.project.ContactManager1;


//Contact class that creates contact objects which are stored in the public array list displayList
public class Contact {
	//private fields representing the information the contact can have
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _number;
	private String _email;
	private String _address;
	private String _date;
	//constructor to initialize the private fields in the contact class 

	public Contact(String firstName, String lastName, String number, String email,String address,String date){

		set_firstName(firstName);
		set_lastName(lastName);
		set_number(number);
		set_email(email);
		set_address(address);
		set_date(date);
		
		
	}
	//have getter and setter methods that set and return the value of all the private fields

	public String get_firstName() {
		return _firstName;
	}

	public void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}

	public String get_middleName() {
		return _middleName;
	}

	public void set_middleName(String _middleName) {
		this._middleName = _middleName;
	}

	public String get_lastName() {
		return _lastName;
	}

	public void set_lastName(String _lastName) {
		this._lastName = _lastName;
	}

	public String get_number() {
		return _number;
	}

	public void set_number(String _number) {
		this._number = _number;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_address() {
		return _address;
	}

	public void set_address(String _address) {
		this._address = _address;
	}

	public String get_date() {
		return _date;
	}

	public void set_date(String _date) {
		this._date = _date;
	}





}
