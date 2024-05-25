
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class ArmyStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("aoe.game.unit.util.Army", withParent("ArrayList of Soldier"))
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void noArgConstructor() {
        it.hasConstructor(withNoArgs())
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void copyConstructor() {
        it.hasConstructor(withParams("army: Army"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetPower() {
        it.hasMethod("getPower", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("int");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodDecreasePowerBy() {
        it.hasMethod("decreasePowerBy", withParams("amount: int"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetOldestSoldier() {
        it.hasMethod("getOldestSoldier", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("aoe.game.unit.Soldier");
    }

    @Test
    @DisabledIf(notApplicable)
    public void eq() {
        it.has(EQUALITY_CHECK);
    }
}

