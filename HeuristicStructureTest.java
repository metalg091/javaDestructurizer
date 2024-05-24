import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class HeuristicStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theInterface("linear.program.Heuristic")
                 .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetNextVariables() {
        it.hasMethod("getNextVariables", withParams("vars: List of Variable"))
          .thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("boolean");
    }
}
