package test.gyakorlat2024.lab04-05;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class SoliloquyStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("magic.Soliloquy")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void constructor() {
        it.hasNoArgConstructor()
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodMain() {
        it.hasMethodWithParams("main", "array of String")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodPrintStatus() {
        it.hasMethodWithParams("printStatus", "boolean", "magic.library.Incantation", "magic.library.Incantation")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_NONE)
            .thatReturnsNothing();
    }

    @Test
    public void methodReciteIncantations() {
        it.hasMethodWithParams("reciteIncantations", "magic.library.Incantation", "magic.library.Incantation", "int", "boolean")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }
}

