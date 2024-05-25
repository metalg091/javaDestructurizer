package test.vizsga20240524;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class WorkerStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theInterface("aoe.game.unit.util.Worker")
                 .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodWork() {
        it.hasMethod("work", withNoParams())
          .thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("int");
    }
}

