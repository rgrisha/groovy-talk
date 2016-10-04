import groovy.transform.TupleConstructor

/**
 * Created by rolandas on 10/4/16.
 */
@TupleConstructor
class Generation {
  Set<Cell> cells;

  def survivedCells(List<Rule> rules) {
    def liveCellRules = rules.grep{rule->rule.cellStateBefore == CellState.LIVE}

    cells.grep { c ->
      def neighbourCount = c.neighbours().grep{cc->cells.contains(cc)}.size()
      liveCellRules.any { rule ->
        rule.neighboursCheck(neighbourCount) &&
            rule.cellStateAfter == CellState.LIVE
      }
    }
  }

  def newbornCells(List<Rule> rules) {
    def deadCellRules = rules.grep{rule->rule.cellStateBefore == CellState.DEAD}

    def deadCells = cells
        .collect{c->c.neighbours()}
        .flatten()
        .toSet()
        .grep {c -> !cells.contains(c) }

    deadCells.grep { c->
      def neighbourCount = c.neighbours().grep{cc->cells.contains(cc)}.size()
      deadCellRules.any { rule ->
        rule.neighboursCheck(neighbourCount) &&
            rule.cellStateAfter == CellState.LIVE
      }
    }
  }

  def getNewOn(List<Rule> rules) {
    survivedCells(rules).plus(newbornCells(rules))
  }

}
