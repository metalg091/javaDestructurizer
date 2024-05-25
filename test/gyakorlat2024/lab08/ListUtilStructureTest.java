package test.gyakorlat2024.lab08;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class ListUtilStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("data.structure.ListUtil")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void methodDivisors() {
        it.hasMethodWithParams("divisors", "int")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("java.util.ArrayList");
    }

    @Test
    public void methodWithSameStartEnd() {
        it.hasMethodWithParams("withSameStartEnd", "ArrayList")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("ArrayList");
    }

    @Test
    public void methodMaxToFront() {
        it.hasMethodWithParams("maxToFront", "ArrayList")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }
}

