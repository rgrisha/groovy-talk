import org.junit.Assert
import org.testng.annotations.Test

/**
 * Created by rolandas on 10/4/16.
 */


@Test
class MainTest {

  public void testCellEquality() {
    Assert.assertEquals(new Cell(1,1), new Cell(1,1))
  }

  public void testCellInequality() {
    Assert.assertNotEquals(new Cell(2,1), new Cell(1,1))
  }

  public void testRuleDefinitionWorks() {

    def rules = RulesDsl.make {
      when live with { neighbours == 2 } then live
    }

    Assert.assertEquals(rules[0].cellKind, "live");
    Assert.assertEquals(rules[0].action, "live");

  }

}
