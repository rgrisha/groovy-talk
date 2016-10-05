/**
 * Created by rolandas on 10/4/16.
 */

class Game {

  def defaultRules = RulesDsl.make {
    when live with {neighbours == 2} then live
    when live with {neighbours == 3} then live
    when live with {neighbours < 2}  then dead
    when live with {neighbours > 3}  then dead
    when dead with {neighbours == 3} then live
  }

  def rules = defaultRules

  def readRulesFromClasspath(String fileName) {
    String rulesStr = this.getClass().getResource(fileName).text
    rules = RulesDsl.parseStringRules(rulesStr)
  }

  def getNewGeneration(Generation generation) {
    generation.getNewOn(rules)
  }


}
