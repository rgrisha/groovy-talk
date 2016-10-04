import groovy.transform.AutoClone
import groovy.transform.ToString
import groovy.transform.TupleConstructor

/**
 * Created by rolandas on 10/4/16.
 */
public class RulesDsl {

  @AutoClone
  @TupleConstructor
  @ToString
  class RuleRecord {
    String cellKind
    Closure neighboursCheck
    String action
  }

  def static String live = "live"
  def static String dead = "dead"

  ArrayList<RuleRecord> rules = new ArrayList<RuleRecord>();

  def getRules = { rules }

  def static make(closure) {
    RulesDsl rulesDsl = new RulesDsl()
    // any method called in closure will be delegated to the memoDsl class
    closure.delegate = rulesDsl
    closure()
    rulesDsl.getRules()
  }

  RuleRecord tmpRecord = new RuleRecord()

  def when(String cellKind) {
    tmpRecord.cellKind = cellKind
    this
  }

  @TupleConstructor
  class NeighboursCheck {
    int neighbours
  }

  def with(Closure neighboursCheck) {
    tmpRecord.neighboursCheck = { neighbours ->
      neighboursCheck.delegate = new NeighboursCheck(neighbours)
      neighboursCheck()
    }
    this
  }

  def then(String action) {
    tmpRecord.action = action
    rules.add(tmpRecord.clone())
  }

}
