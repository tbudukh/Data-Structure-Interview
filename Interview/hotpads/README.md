We have a person class:

class Person{
  String ssn;
  Date dateOfBirth;
  String firstName;
  String lastName;
  Double heightIn;
  Double weightLb;
}

And we are building a webpage that can display people sorted by any of the Person fields.

Sample request: /people?sortField=dateOfBirth&ascending=false

Please implement this method to sort the results:

static List<Person> sort(Iterable<Person> people, String sortField, String ascending){
}