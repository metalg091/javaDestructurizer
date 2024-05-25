package test.vizsga20240524chess;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class PieceStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theInterface("chess.utils.Piece")
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodCheckMove() {
        it.hasMethod("checkMove", withParams("col: int", "row: int"))
            .thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodCheckPath() {
        it.hasMethod("checkPath", withParams("board: HashMap of String to Piece", "col: int", "row: int"))
            .thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodMoveTo() {
        it.hasMethod("moveTo", withParams("board: HashMap of String to Piece", "col: int", "row: int"))
            .thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("boolean");
    }
}

