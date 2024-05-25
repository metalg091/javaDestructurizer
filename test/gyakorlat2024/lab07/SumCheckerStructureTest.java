package test.gyakorlat2024.lab07;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class SumCheckerStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("file.line.SumChecker")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void constructor() {
        it.hasNoArgConstructor()
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodGetNumbers() {
        it.hasMethodWithParams("getNumbers", "String")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_NONE)
            .thatReturns("array of int");
    }

    @Test
    public void methodMain() {
        it.hasMethodWithParams("main", "array of String")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }
}

