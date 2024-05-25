package test.beadando2024;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class WalkingBoardWithPlayersStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("walking.game.WalkingBoardWithPlayers", withParent("walking.game.WalkingBoard"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldPlayers() {
        it.hasField("players: array of walking.game.player.Player")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldRound() {
        it.hasField("round: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldSCORE_EACH_STEP() {
        it.hasField("SCORE_EACH_STEP: int")
            .thatIs(USABLE_WITHOUT_INSTANCE, NOT_MODIFIABLE, VISIBLE_TO_ALL)
            .thatHasNo(GETTER, SETTER)
            .withInitialValue(13);
    }

    @Test
    @DisabledIf(notApplicable)
    public void constructor01() {
        it.hasConstructor(withArgs("board: array of array of int", "playerCount: int"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void constructor02() {
        it.hasConstructor(withArgs("size: int", "playerCount: int"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodInitPlayers() {
        it.hasMethod("initPlayers", withParams("playerCount: int"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
          .thatReturnsNothing();
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodWalk() {
        it.hasMethod("walk", withParams("stepCounts: vararg of int"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("array of int");
    }
}

