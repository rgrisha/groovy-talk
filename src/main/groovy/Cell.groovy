import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

@EqualsAndHashCode
@TupleConstructor
class Cell {
  int x
  int y

  def surroundings = [
      [-1,-1],[-1,0],[-1,1],[0,-1],[0,1],[1,-1],[1,0],[1,1]
  ]

  def neighbours() {
    surroundings.collect{s->new Cell(s[0] + x, s[1] + y)}
  }

  public String toString() {
    "<$x,$y>"
  }

}

