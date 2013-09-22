package awad865.project.ContactManager1;

public class Contact {
	//private fields representating the information the contact can have
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _number;
	private String _email;
	private String _address;
	private String _date;

	//constructor to initialize the private fields

	public Contact(String firstName,String lastName,String number){

		_firstName=firstName;
		_lastName=lastName;
		_number=number;
	}
	//add getter methods to return the first name, last name and number for each contact
	public String getFirstName(){
		return _firstName;
	}

	public String getLastName(){
		return _lastName;
	}

	public String getNumber(){
		return _number;
	}






}
