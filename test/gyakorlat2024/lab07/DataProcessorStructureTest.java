import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class DataProcessorStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("text.to.numbers.DataProcessor")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void constructor() {
        it.hasNoArgConstructor()
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodConvert() {
        it.hasMethodWithParams("convert", "array of String")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("array of int");
    }

    @Test
    public void methodConvertWithDefault() {
        it.hasMethodWithParams("convertWithDefault", "int", "array of String")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("array of int");
    }

    @Test
    public void methodCountFileLines() {
        it.hasMethodWithParams("countFileLines", "String")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("int");
    }

    @Test
    public void methodMain() {
        it.hasMethodWithParams("main", "array of String")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodSumFile() {
        it.hasMethodWithParams("sumFile", "String", "String")
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }
}

