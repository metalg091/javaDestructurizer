package test.vizsga20240524;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class AgedStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theInterface("aoe.game.unit.util.Aged")
                 .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetAge() {
        it.hasMethod("getAge", withNoParams())
          .thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("int");
    }
    
    @Test
    @DisabledIf(notApplicable)
    public void methodIncreaseAge() {
    	it.hasMethod("increaseAge", withNoParams())
    	.thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
    	.thatReturnsNothing();
    }
}

