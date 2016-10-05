
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
    cell1 != cell2
  }

  def "rule description gives correct rule"() {
    def rules = RulesDsl.make {
      when live with { neighbours == 2 } then dead
    }

    expect:
    rules[0].cellStateBefore == CellState.LIVE
    rules[0].cellStateAfter == CellState.DEAD
    rules[0].neighboursCheck(2) is true
  }

  def "cell has 8 neighbours"() {
    Cell c = new Cell(1,1)
    expect:
    c.neighbours() == [
        new Cell(0,0),new Cell(0,1),new Cell(0,2),new Cell(1,0),
        new Cell(1,2),new Cell(2,0),new Cell(2,1),new Cell(2,2)
    ]
  }

  def "survived cell calculated ok"() {
    Game game = new Game()
    Generation g1 = new Generation([new Cell(1,1), new Cell(2,1), new Cell(3,1)].toSet())
    expect:
    g1.survivedCells(game.defaultRules) == [new Cell(2,1)].toSet()
  }

  def "newborn cell calculated ok"() {
    Game game = new Game()
    Generation g1 = new Generation([new Cell(1,1), new Cell(2,1), new Cell(3,1)].toSet())
    expect:
    g1.newbornCells(game.defaultRules) == [new Cell(2,0), new Cell(2,2)].toSet()
  }

  def "spinner generation is working"() {
  Game game = new Game()
    Generation g1 = new Generation([new Cell(1,1), new Cell(2,1), new Cell(3,1)].toSet())

    expect:
    game.getNewGeneration(g1) == [new Cell(2,0), new Cell(2,1), new Cell(2,2)].toSet()
  }

  def "can read game-of-life resources file"() {
    String rulesStr = this.getClass().getResource( 'game-of-life.dsl' ).text
    expect:
    ((rulesStr =~ /then/).count) > 4
  }

  def "can parse game-of-life resources file"() {
    Game game = new Game()
    game.readRulesFromClasspath('game-of-life.dsl')
    Generation g1 = new Generation([new Cell(1,1), new Cell(2,1), new Cell(3,1)].toSet())
    expect:
    game.getNewGeneration(g1) == [new Cell(2,0), new Cell(2,1), new Cell(2,2)].toSet()
  }

}
