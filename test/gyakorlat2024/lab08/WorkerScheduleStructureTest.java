package test.gyakorlat2024.lab08;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class WorkerScheduleStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("worker.schedule.WorkerSchedule")
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
    public void methodAdd() {
        it.hasMethodWithParams("add", "int", "HashSet of String")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodAddV2() {
        it.hasMethodWithParams("add", "HashSet of Integer", "ArrayList of String")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodIsWorkingOnWeek() {
        it.hasMethodWithParams("isWorkingOnWeek", "String", "int")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("boolean");
    }

    @Test
    public void methodGetWorkWeeks() {
        it.hasMethodWithParams("getWorkWeeks", "String")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("HashSet of Integer");
    }
}
