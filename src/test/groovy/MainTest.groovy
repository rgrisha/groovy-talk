
/**
 * Created by rolandas on 10/4/16.
 */


class MainTest extends spock.lang.Specification {

  def "two different instances of cell with same coords are equal"() {
    def cell1 = new Cell(1,1)
    def cell2 = new Cell(1,1)

    expect:
      cell1 == cell2
  }

  def "two cells with different coords are not equal"() {
    def cell1 = new Cell(2,1)
    def cell2 = new Cell(1,1)

    expect:
    !cell1.equals(cell2)
  }

  def "rule description gives correct rule"() {
    def rules = RulesDsl.make {
      when live with { neighbours == 2 } then live
    }

    expect:
      rules[0].cellKind == "live"
      rules[0].action == "live"
  }

}
