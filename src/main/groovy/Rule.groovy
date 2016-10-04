import groovy.transform.AutoClone
import groovy.transform.ToString
import groovy.transform.TupleConstructor

/**
 * Created by rolandas on 10/4/16.
 */

@AutoClone
@TupleConstructor
@ToString
class Rule {
  CellState cellStateBefore
  Closure neighboursCheck
  CellState cellStateAfter
}
