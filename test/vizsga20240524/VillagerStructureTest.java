
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class VillagerStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("aoe.game.unit.Villager", withInterface("aoe.game.unit.util.Worker"))
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void fieldGATHER_AMOUNT() {
        it.hasField("GATHER_AMOUNT: int")
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
            .withInitialValue(100);
    }

    @Test
    @DisabledIf(notApplicable)
    public void constructor() {
        it.hasConstructor(withArgs("age: int"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    @DisabledIf(notApplicable)
    public void methodWork() {
        it.implementsMethod("work");
    }
}

