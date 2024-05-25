package test.vizsga20240524chess;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class BishopStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("chess.pieces.Bishop", withParent("chess.pieces.ChessPiece"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void constructor() {
        it.hasConstructor(withArgs("color: chess.utils.PieceColor", "col: int", "row: int"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodCheckMove() {
        it.implementsMethod("checkMove");
    }

}

