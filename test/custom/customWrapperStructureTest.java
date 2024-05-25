package test.custom;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class customWrapperStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("wrapper.multi.MultiWrapper")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void constructor() {
        it.hasNoArgConstructor()
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void fieldWeekToWorkers() {
        it.hasFieldOfType("weekToWorkers", "HashMap of Integer to HashSet of String")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }
    @Test
    public void fieldWeekToWorkerswtf() {
        it.hasFieldOfType("wtf", "List of ArrayList of HashSet of String")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }
    @Test
    public void fieldWeekToWorkersliststring() {
        it.hasFieldOfType("liststring", "List of String")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void methodAdd() {
        it.hasMethodWithParams("add", "int", "HashSet of String")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodAddV2() {
        it.hasMethodWithParams("add", "HashSet of List of Integer", "ArrayList of HashSet of String")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodIsWorkingOnWeek() {
        it.hasMethodWithParams("isWorkingOnWeek", "array of array of String", "int")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("boolean");
    }

    @Test
    public void methodGetWorkWeeks() {
        it.hasMethodWithParams("getWorkWeeks", "vararg of array of String")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("HashSet of Integer");
    }
}
