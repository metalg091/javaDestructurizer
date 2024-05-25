
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class GameStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("aoe.game.Game")
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldTurnCount() {
        it.hasField("turnCount: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_ALL)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldCommanders() {
        it.hasField("commanders: List of Commander")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void constructor() {
        it.hasConstructor(withArgs("fileName: String"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodPlay() {
        it.hasMethod("play", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("array of String");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodDoAttacks() {
        it.hasMethod("doAttacks", withParams("turnIdx: int"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
          .thatReturnsNothing();
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetResults() {
        it.hasMethod("getResults", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
          .thatReturns("array of String");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodInitCommanders() {
        it.hasMethod("initCommanders", withParams("filename: String"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
          .thatCanRaise("java.io.IOException")
          .thatReturnsNothing();
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetVictoriousCommander() {
        it.hasMethod("getVictoriousCommander", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
          .thatReturns("aoe.game.commander.Commander");
    }

}

