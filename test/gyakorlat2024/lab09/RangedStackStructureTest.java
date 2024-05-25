package test.gyakorlat2024.lab09;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class RangedStackStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("data.structure.RangedStack")
            .withTypeParameter("T")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void fieldElems() {
        it.hasField("elems", ofType("LinkedList of T"))
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void constructor01() {
        it.hasConstructor(withNoArgs())
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void constructor02() {
        it.hasConstructorWithParams("RangedStack of T")
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodPop() {
        it.hasMethod("pop", withParams("int"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("ArrayList of T");
    }

    @Test
    public void methodPush() {
        it.hasMethod("push", withParams("vararg of T"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }
}

