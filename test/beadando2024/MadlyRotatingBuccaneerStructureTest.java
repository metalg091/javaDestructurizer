
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class MadlyRotatingBuccaneerStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("walking.game.player.MadlyRotatingBuccaneer", withParent("walking.game.player.Player"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldTurnCount() {
        it.hasField("turnCount: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void defaultConstructor() {
        it.hasConstructor(withNoArgs())
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodTurn() {
        it.hasMethod("turn", withNoArgs())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }
}
