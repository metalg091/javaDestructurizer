package test.beadando2024;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import walking.game.WalkingBoardWithPlayersTest;

@SelectClasses({
        WalkingBoardExtendedTestSuite.StructuralTests.class, WalkingBoardExtendedTestSuite.FunctionalTests.class
})
@Suite
public class WalkingBoardExtendedTestSuite {
    @SelectClasses({
            WalkingBoardWithPlayersStructureTest.class, PlayerStructureTest.class,
            MadlyRotatingBuccaneerStructureTest.class
    })
    @Suite
    public static class StructuralTests {
    }

    @SelectClasses({
            WalkingBoardWithPlayersTest.class
    })
    @Suite
    public static class FunctionalTests {
    }
}
