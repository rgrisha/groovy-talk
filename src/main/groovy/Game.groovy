/**
 * Created by rolandas on 10/4/16.
 */

class Game {

  def rules = RulesDsl.make {
    when live with {neighbours == 2} then live
    when live with {neighbours == 3} then live
    when live with {neighbours < 2}  then dead
    when live with {neighbours > 3}  then dead
    when dead with {neighbours == 3} then live
  }

  def getNewGeneration(Generation generation) {
    generation.getNewOn(rules)
  }

}
