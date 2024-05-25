package test.vizsga20240522;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class ILPSolverStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("linear.program.ILPSolver")
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldMAX_ITERATION_COUNT() {
        it.hasField("MAX_ITERATION_COUNT: int")
            .thatIs(USABLE_WITHOUT_INSTANCE, NOT_MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER)
            .withInitialValue(10_000_000);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldVariables() {
        it.hasField("variables: List of Variable")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldConstraints() {
        it.hasField("constraints: List of Constraint")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldHeuristic() {
        it.hasField("heuristic: Heuristic")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void constructor() {
        it.hasConstructor(withArgs("heuristic: Heuristic"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodAddVar() {
        it.hasMethod("addVar", withParams("lowerBound: int", "upperBound: int", "name: String"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodAddConstraint() {
        it.hasMethod("addConstraint", withParams("constraint: linear.program.utils.Constraint"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodIsSatisfied() {
        it.hasMethod("isSatisfied", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("boolean");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetVariableValues() {
    	it.hasMethod("getVariableValues", withNoParams())
    	.thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
    	.thatReturns("array of int");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodSolve() {
        it.hasMethod("solve", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("boolean");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetSolution() {
        it.hasMethod("getSolution", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("array of int");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetVariableNames() {
        it.hasMethod("getVariableNames", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("List of String");
    }

    @Test
    @DisabledIf(notApplicable)
    public void text() {
        it.has(TEXTUAL_REPRESENTATION);
    }
}

