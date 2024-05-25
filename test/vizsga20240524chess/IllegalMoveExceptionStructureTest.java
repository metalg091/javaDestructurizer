package test.vizsga20240524chess;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class IllegalMoveExceptionStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theCheckedException("chess.utils.IllegalMoveException")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void constructor() {
        it.hasConstructor(withArgs("msg: String"))
            .thatIs(VISIBLE_TO_ALL)
            .thatCalls(theParent());
    }
}

