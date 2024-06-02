package test.vizsga20240524;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import aoe.game.GameTest;
import aoe.game.commander.CommanderTest;
import aoe.game.unit.util.ArmyTest;

@SelectClasses({
      SimpleAoeTestSuite.StructuralTests.class
    , SimpleAoeTestSuite.FunctionalTests.class
})
@Suite public class SimpleAoeTestSuite {
    @SelectClasses({
          NationalityStructureTest.class

        , AgedStructureTest.class
        , WorkerStructureTest.class
        , FighterStructureTest.class

        , VillagerStructureTest.class
        , SoldierStructureTest.class

        , ArmyStructureTest.class

        , CommanderStructureTest.class

        , GameStructureTest.class
    })
    @Suite public static class StructuralTests {}

    @SelectClasses({
          ArmyTest.class
        , CommanderTest.class
        , GameTest.class
    })
    @Suite public static class FunctionalTests {}
}

