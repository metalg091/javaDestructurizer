import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class BruteForceHeuristicStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("linear.program.BruteForceHeuristic", withInterface("linear.program.Heuristic"))
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void constructor() {
        it.has(DEFAULT_CONSTRUCTOR);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetNextVariables() {
        it.hasMethod("getNextVariables", withParams("vars: List of Variable"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("boolean");
    }
}

