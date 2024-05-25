package test.vizsga20240524;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class SoldierStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("aoe.game.unit.Soldier", withInterface("aoe.game.unit.util.Fighter"))
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldBASE_POWER() {
        it.hasField("BASE_POWER: int")
            .thatIs(USABLE_WITHOUT_INSTANCE, NOT_MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER)
            .withInitialValue(10);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldCOST() {
        it.hasField("COST: int")
            .thatIs(USABLE_WITHOUT_INSTANCE, NOT_MODIFIABLE, VISIBLE_TO_ALL)
            .thatHasNo(GETTER, SETTER)
            .withInitialValue(400);
    }

    @Test
    @DisabledIf(notApplicable)
    public void constructor() {
        it.hasConstructor(withArgs("age: int"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetPower() {
        it.implementsMethod("getPower");
    }
}

