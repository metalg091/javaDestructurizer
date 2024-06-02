package test.vizsga20240524chess;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import chess.pieces.ChessPieceTest;
import chess.tests.PieceStructureTest;
import chess.tests.BishopStructureTest;
import chess.tests.PieceColorStructureTest;
import chess.tests.ChessPieceStructureTest;
import chess.tests.IllegalMoveExceptionStructureTest;
import chess.tests.PawnStructureTest;
import chess.tests.QueenStructureTest;
import chess.tests.RookStructureTest;

@Suite
@SelectClasses({
	  SimpleChessSuite.ChessStructureTests.class
  , SimpleChessSuite.ChessFunctionalTests.class
})
public class SimpleChessSuite {
  @SelectClasses({
      PieceColorStructureTest.class
    , IllegalMoveExceptionStructureTest.class

    , PieceStructureTest.class
    , ChessPieceStructureTest.class

    , BishopStructureTest.class
    , PawnStructureTest.class
    , QueenStructureTest.class
    , RookStructureTest.class
    
    , MatchStructureTest.class
  })
  @Suite public static class ChessStructureTests {}

  @SelectClasses({
	    ChessPieceTest.class
	  , MatchTest.class
  })
  @Suite public static class ChessFunctionalTests {}
}
