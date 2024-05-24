import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class CharacterStatisticsStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("text.util.CharacterStatistics")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatHas(TEXTUAL_REPRESENTATION);
    }

    @Test
    public void constructor() {
        it.hasConstructorWithParams("String")
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void fieldCharToCount() {
        it.hasFieldOfType("charToCount", "HashMap of Character to Integer")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void methodGetCount() {
        it.hasMethodWithParams("getCount", "char")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("int");
    }
}
