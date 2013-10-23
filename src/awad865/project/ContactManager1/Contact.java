package awad865.project.ContactManager1;

/**
 * The purpose of this class is that to store all the values associated with a single contact and 
 * an arrayList is created in MainActivity to contain all the Contact objects and this help manage 
 * all the contacts in the database.
 * @author Anmol Wadhwa, awad865, 5603097
 */




public class Contact {
	//private fields representing the information the contact can have
	private String firstName;
	private String lastName;
	private String number;
	private String numberType;
	private String email;
	private String emailType;
	private String address;
	private String addressType;
	private String date;
	private String dateType;
	private String favourite;
	private byte[] image;
	//constructor to initialize the private fields in the contact class 

	public Contact(String firstName, String lastName, String number, String numberType, String email, String emailType, String date, String dateType, String address, String addressType, byte[] image, String favourite){
		setFirstName(firstName);
		setLastName(lastName);
		setNumber(number);
		setNumberType(numberType);
		setEmail(email);
		setEmailType(emailType);	
		setDate(date);
		setDateType(dateType);
		setAddress(address);
		setAddressType(addressType);
		setImage(image);
		setFavourite(favourite);


	}
	//have getter and setter methods that set and return the value of all the private fields

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumberType() {
		return numberType;
	}

	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getFavourite() {
		return favourite;
	}

	public void setFavourite(String favourite) {
		this.favourite = favourite;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
