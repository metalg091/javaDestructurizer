package test.vizsga20240522;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import linear.program.ILPSolverQueensTest;
import linear.program.ILPSolverStocksTest;
import linear.program.ILPSolverIntegerProgrammingTest;
import linear.program.utils.ConstraintTest;
import linear.program.utils.VariableTest;

@SelectClasses({
      LinprogTestSuite.StructuralTests.class
    , LinprogTestSuite.FunctionalTests.class
})
@Suite public class LinprogTestSuite {
    @SelectClasses({
          VariableStructureTest.class

        , ConstraintStructureTest.class
        , EqualityConstraintStructureTest.class
        , InequalityConstraintStructureTest.class

        , HeuristicStructureTest.class
        , RandomHeuristicStructureTest.class
        , BruteForceHeuristicStructureTest.class

        , ILPSolverStructureTest.class
    })
    @Suite public static class StructuralTests {}

    @SelectClasses({
    	  ConstraintTest.class
    	, VariableTest.class

    	, ILPSolverIntegerProgrammingTest.class
    	, ILPSolverQueensTest.class
    	, ILPSolverStocksTest.class
    })
    @Suite public static class FunctionalTests {}
}

