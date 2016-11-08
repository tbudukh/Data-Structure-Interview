We have a person class:

````*.java
class Person{
  String ssn;
  Date dateOfBirth;
  String firstName;
  String lastName;
  Double heightIn;
  Double weightLb;
}
````
And we are building a webpage that can display people sorted by any of the Person fields.

Sample request: /people?sortField=dateOfBirth&ascending=false

Please implement this method to sort the results:
````*.java
static List sort(Iterable people, String sortField, String ascending){
}
````  