import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class TriangularNumbersStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("famous.sequence.TriangularNumbers")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void constructor() {
        it.hasNoArgConstructor()
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodGetTriangularNumber() {
        it.hasMethodWithParams("getTriangularNumber", "int")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("int");
    }

    @Test
    public void methodGetTriangularNumberAlternative() {
        it.hasMethodWithParams("getTriangularNumberAlternative", "int")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("int");
    }
}

