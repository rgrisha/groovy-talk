import groovy.transform.TupleConstructor

/**
 * Created by rolandas on 10/4/16.
 */
public class RulesDsl {


  ArrayList<Rule> rules = new ArrayList<Rule>();

  def getRules = { rules }

  def static make(closure) {
    RulesDsl rulesDsl = new RulesDsl()
    // any method called in closure will be delegated to the memoDsl class
    closure.delegate = rulesDsl
    closure()
    rulesDsl.getRules()
  }

  Rule tmpRule = new Rule()

  public static final String live = "live"
  public static final String dead = "dead"

  def when(String cellKind) {
    tmpRule.cellStateBefore = cellKind.toUpperCase()
    this
  }

  @TupleConstructor
  class NeighboursCheck {
    int neighbours
  }

  def with(Closure neighboursCheck) {
    tmpRule.neighboursCheck = { neighbours ->
      neighboursCheck.delegate = new NeighboursCheck(neighbours)
      neighboursCheck()
    }
    this
  }

  def then(String action) {
    tmpRule.cellStateAfter = action.toUpperCase()
    rules.add(tmpRule.clone())
  }

}
