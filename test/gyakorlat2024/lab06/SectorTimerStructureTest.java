package test.gyakorlat2024.lab06;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class SectorTimerStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("race.car.SectorTimer")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void fieldSectorTimes() {
        it.hasFieldOfType("sectorTimes", "array of int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    public void constructor01() {
        it.hasConstructorWithParams("array of int")
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void constructor02() {
        it.hasConstructorWithParams("boolean", "array of int")
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodGetSectorTime() {
        it.hasMethodWithParams("getSectorTime", "int")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("int");
    }

    @Test
    public void methodGetSectorTimesV2() {
        it.hasMethodWithNoParams("getSectorTimesV2")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("array of int");
    }

    @Test
    public void methodInitSectorTimes() {
        it.hasMethodWithParams("initSectorTimes", "array of int")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
            .thatReturnsNothing();
    }

    @Test
    public void methodSetLapTimes() {
        it.hasMethodWithParams("setLapTimes", "array of int")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodSetSectorTimesV2() {
        it.hasMethodWithParams("setSectorTimesV2", "array of int")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }
}

