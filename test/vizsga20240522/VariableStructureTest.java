import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.condition.*;

import check.CheckThat;

@TestMethodOrder(OrderAnnotation.class)
public class VariableStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("linear.program.utils.Variable")
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    @Order(1_1)
    public void fieldLowerBound() {
        it.hasField("lowerBound: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    @Order(1_2)
    public void fieldUpperBound() {
        it.hasField("upperBound: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    @Order(1_3)
    public void fieldName() {
        it.hasField("name: String")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    @Order(1_4)
    public void fieldValue() {
        it.hasField("value: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    @Order(3_0)
    public void constructor() {
        it.hasConstructor(withArgsSimilarToFields("lowerBound", "upperBound", "name"))
            .thatIs(VISIBLE_TO_NONE);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetRange() {
        it.hasMethod("getRange", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("int");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodMakeVar() {
        it.hasMethod("makeVar", withArgsSimilarToFields("lowerBound", "upperBound", "name"))
          .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
          .thatReturns("Variable");
    }

    @Test
    @DisabledIf(notApplicable)
    public void text() {
        it.has(TEXTUAL_REPRESENTATION);
    }
}

