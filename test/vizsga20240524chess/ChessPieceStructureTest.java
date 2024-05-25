package test.vizsga20240524chess;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class ChessPieceStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("chess.pieces.ChessPiece", withInterfaces("chess.utils.Piece"))
            .thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void fieldColor() {
        it.hasField("color: chess.utils.PieceColor")
            .thatIs(INSTANCE_LEVEL, NOT_MODIFIABLE, VISIBLE_TO_SUBCLASSES)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    public void fieldCol() {
        it.hasField("col: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_SUBCLASSES)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void fieldRow() {
        it.hasField("row: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_SUBCLASSES)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void methodSetPos() {
        it.hasMethod("setPos", withParams( "col: int", "row: int" ))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodGetPos() {
        it.hasMethod("getPos", withNoParams())
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("array of int");
    }

    @Test
    public void constructor() {
        it.hasConstructor(withArgs("color: chess.utils.PieceColor", "col: int", "row: int"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodFieldName1() {
        it.hasMethod("fieldName", withParams("col: int", "row: int"))
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("String");
    }

    @Test
    public void methodFieldName2() {
        it.hasMethod("fieldName", withNoParams())
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
            .thatReturns("String");
    }
  
    @Test
    public void methodCheckMove() {
        it.hasMethod("checkMove", withParams("col: int", "row: int"))
            .thatIs(NOT_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodCheckPath() {
        it.implementsMethod("checkPath");
    }

    @Test
    public void methodMoveTo() {
        it.implementsMethod("moveTo");
    }

    @Test
    public void text() {
        it.has(TEXTUAL_REPRESENTATION);
    }
}
