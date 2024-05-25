
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class PrintedBookStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("printed.material.specific.PrintedBook")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatHas(TEXTUAL_REPRESENTATION);
    }

    @Test
    public void fieldCoverType() {
        it.hasField("coverType", ofType("printed.material.CoverType"))
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_SUBCLASSES)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void constructor01() {
        it.hasConstructor(withArgs("String", "String", "int", "printed.material.CoverType"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void constructor02() {
        it.hasConstructor(withArgs("printed.material.Book"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodDecode() {
        it.hasMethod("decode", withParams("String"))
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("PrintedBook");
    }

    @Test
    public void methodGetPrice() {
        it.hasMethod("getPrice", withNoParams())
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("int");
    }
}
