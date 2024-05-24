import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class FileContentStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("textfile.lookup.FileContent")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void constructor() {
        it.hasNoArgConstructor()
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodContentLineCount() {
        it.hasMethodWithParams("contentLineCount", "String", "String")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("int");
    }

    @Test
    public void methodContentLineNumbers() {
        it.hasMethodWithParams("contentLineNumbers", "String", "String")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("array of int");
    }
}

