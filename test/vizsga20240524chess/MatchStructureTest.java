package test.vizsga20240524chess;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;

import check.CheckThat;

public class MatchStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("chess.Match")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void fieldPieces() {
        it.hasField("pieces: HashMap of String to chess.pieces.Figure")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_ALL)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void fieldMoves() {
        it.hasField("moves: ArrayList of ArrayList of Integer")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_ALL)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void fieldSize() {
        it.hasField("size: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void constructor() {
        it.hasConstructor(withArgs("file: String"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodPlay() {
        it.hasMethod("play", withNoParams())
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }
  
    @Test
    @DisabledIf(notApplicable)
    public void methodMakeMove() {
        it.hasMethod("makeMove", withParams("parts: array of String"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
          .thatReturns("ArrayList of Integer");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodMakePiece() {
        it.hasMethod("makePiece", withParams("pieces: array of String"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
          .thatReturns("boolean");
    }

    @Test
    public void methodCheckCoord() {
        it.hasMethod("checkCoord", withParams("col: int"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns( "boolean" );
    }

}
