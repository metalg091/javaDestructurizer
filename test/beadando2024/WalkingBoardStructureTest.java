package test.beadando2024;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

import check.CheckThat;

public class WalkingBoardStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("walking.game.WalkingBoard")
                .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldTiles() {
        it.hasField("tiles: array of array of int")
                .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
                .thatHas(GETTER)
                .thatHasNo(SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldX() {
        it.hasField("x: int")
                .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
                .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldY() {
        it.hasField("y: int")
                .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
                .thatHasNo(GETTER, SETTER);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldBASE_TILE_SCORE() {
        it.hasField("BASE_TILE_SCORE: int")
                .thatIs(USABLE_WITHOUT_INSTANCE, NOT_MODIFIABLE, VISIBLE_TO_ALL)
                .thatHasNo(GETTER, SETTER)
                .withInitialValue(3);
    }

    @Test
    @DisabledIf(notApplicable)
    public void constructor01() {
        it.hasConstructor(withArgs("size: int"))
                .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void constructor02() {
        it.hasConstructor(withArgs("tiles: array of array of int"))
                .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetPosition() {
        it.hasMethod("getPosition", withNoParams())
                .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
                .thatReturns("array of int");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodIsValidPosition() {
        it.hasMethod("isValidPosition", withParams("x: int", "y: int"))
                .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
                .thatReturns("boolean");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetTile() {
        it.hasMethod("getTile", withParams("x: int", "y: int"))
                .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
                .thatReturns("int");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetX() {
        it.hasMethod("getXStep", withParams("direction: walking.game.util.Direction"))
                .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
                .thatReturns("int");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodGetY() {
        it.hasMethod("getYStep", withParams("direction: walking.game.util.Direction"))
                .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
                .thatReturns("int");
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodMoveAndSet() {
        it.hasMethod("moveAndSet", withParams("direction: walking.game.util.Direction", "value: int"))
                .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
                .thatReturns("int");
    }
}
