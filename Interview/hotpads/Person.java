import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class Person{
	String ssn;
	Date dateOfBirth;
	String firstName;
	String lastName;
	Double heightIn;
	Double weightLb;
	
	static final Map<String, Comparator<Person>> compMap;
	// map the field names in class Person to the comparators. This block will 
	// need to be modified. if the Person class adds new fields or removes existing fields.
	static{
		Map<String, Comparator<Person>> tempMap = new HashMap<String, Comparator<Person>>();
		tempMap.put("ssn",
				Comparator.comparing(Person::getSsn, Comparator.nullsLast(Comparator.naturalOrder())));
		tempMap.put("dateOfBirth",
				Comparator.comparing(Person::getDateOfBirth, Comparator.nullsLast(Comparator.naturalOrder())));
		tempMap.put("firstName",
				Comparator.comparing(Person::getFirstName, Comparator.nullsLast(Comparator.naturalOrder())));
		tempMap.put("lastName",
				Comparator.comparing(Person::getLastName, Comparator.nullsLast(Comparator.naturalOrder())));
		tempMap.put("heightIn",
				Comparator.comparing(Person::getHeightIn, Comparator.nullsLast(Comparator.naturalOrder())));
		tempMap.put("weightLb",
				Comparator.comparing(Person::getWeightLb, Comparator.nullsLast(Comparator.naturalOrder())));
		compMap = Collections.unmodifiableMap(tempMap);
	}
	
	public static final Map<String, Comparator<Person>> getcompMap() {
		return compMap;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

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

	public Double getHeightIn() {
		return heightIn;
	}

	public void setHeightIn(Double heightIn) {
		this.heightIn = heightIn;
	}

	public Double getWeightLb() {
		return weightLb;
	}

	public void setWeightLb(Double weightLb) {
		this.weightLb = weightLb;
	}

	Person(String ssn, Date dateOfBirth, String firstName, String lastName, Double heightIn, Double weightLb) {
		this.ssn         = ssn;
		this.dateOfBirth = dateOfBirth;
		this.firstName   = firstName;
		this.lastName    = lastName;
		this.heightIn    = heightIn;
		this.weightLb    = weightLb;
	}

	// default constructor, initializes everything to null.
	Person() {}

}