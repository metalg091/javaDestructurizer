package walking.game.tests;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class PlayerStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("walking.game.player.Player")
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldScore() {
        it.hasField("score: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldDirection() {
        it.hasField("direction: walking.game.util.Direction")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_SUBCLASSES)
            .thatHas(GETTER)
            .thatHasNo(SETTER)
            .withInitialValue("UP");
    }

    @Test
    @DisabledIf(notApplicable)
    public void constructor() {
        it.hasConstructor(withNoArgs())
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodAddToScore() {
        it.hasMethod("addToScore", withParams("score: int"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodTurn() {
        it.hasMethod("turn", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }
}

