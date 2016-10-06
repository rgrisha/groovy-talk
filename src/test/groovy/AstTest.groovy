import groovy.transform.Canonical
import groovy.transform.Sortable
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@Canonical
@ToString
class Person {
  String name
  String company
  String job
}

@Sortable(includes = ['manufacturer', 'cylinders'])
class Car {
  String manufacturer
  String model
  int horsePower
  int cylinders
}

class AstTest extends spock.lang.Specification {

  def "show builder interface in action"() {

    def person = new Person()
        .builder()
        .name("John Doe")
        .company("XYZ Co")
        .job("Developer")
        .build()

    expect:
    person.toString() == "Person(John Doe, XYZ Co, Developer)"
  }

  def "show sortable AST inaction"() {
    def cars = [
        new Car([manufacturer: "Jaguar Land Rover L", model: "XE AWD2", horsePower: 340, cylinders: 6]),
        new Car([manufacturer: "Jaguar Land Rover L", model: "XE AWD", horsePower: 180, cylinders: 4]),
        new Car([manufacturer: "Aston martin", model: "Vanquish", horsePower: 568, cylinders: 12]),
        new Car([manufacturer: "Ferrari",	model: "LaFerrari Aperta", horsePower: 946, cylinders: 12]),
        new Car([manufacturer: "FOMOCO",	model: "Mustang", horsePower: 420, cylinders: 8])
    ]

    expect:
    cars.sort().collect { it.model } == ["Vanquish", "Mustang", "LaFerrari Aperta", "XE AWD", "XE AWD2"]
  }

}
