package test.vizsga20240524chess;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class ActionsStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theInterface("chess.utils.Actions")
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodCheckMove() {
        it.hasMethod("checkMove", withParams("c: int", "r: int"))
            .thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodCheckPath() {
        it.hasMethod("checkPath", withParams("board: HashMap of String to Figure", "c: int", "r: int"))
            .thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodMove() {
        it.hasMethod("move", withParams("board: HashMap of String to Figure", "c: int", "r: int"))
            .thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns( "int" );
    }
}

