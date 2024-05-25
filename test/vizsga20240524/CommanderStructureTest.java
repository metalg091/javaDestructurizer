package aoe.game.tests;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

import aoe.game.nationality.Nationality;
import aoe.game.unit.util.Army;

public class CommanderStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("aoe.game.commander.Commander")
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldName() {
        it.hasField("name: String")
            .thatIs(INSTANCE_LEVEL, NOT_MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldNationality() {
        it.hasField("nationality: aoe.game.nationality.Nationality")
            .thatIs(INSTANCE_LEVEL, NOT_MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldTurnsToAttack() {
        it.hasField("turnsToAttack: int")
            .thatIs(INSTANCE_LEVEL, NOT_MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldGold() {
        it.hasField("gold: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldBattlesWon() {
        it.hasField("battlesWon: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER)
            .withInitialValue(0);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldVillagers() {
        it.hasField("villagers: List of Villager")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldArmy() {
        it.hasField("army: aoe.game.unit.util.Army")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void constructor() {
        it.hasConstructor(withArgs("name: String", "nationality: aoe.game.nationality.Nationality", "turnsToAttack: int", "gold: int"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodWinsBattle() {
        it.hasMethod("winsBattle", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodBuyVillagers() {
        it.hasMethod("buyVillagers", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodBuySoldiers() {
        it.hasMethod("buySoldiers", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGatherResources() {
        it.hasMethod("gatherResources", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("int");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodAttack() {
        it.hasMethod("attack", withParams("enemy: Commander"))
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturns("Commander");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodAgeUnits() {
        it.hasMethod("ageUnits", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }
}

