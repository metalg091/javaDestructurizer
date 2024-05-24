import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class ArtistStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("music.recording.Artist")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void fieldName() {
        it.hasFieldOfType("name", "String")
            .thatIs(INSTANCE_LEVEL, NOT_MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    public void fieldLabel() {
        it.hasFieldOfType("label", "RecordLabel")
            .thatIs(INSTANCE_LEVEL, NOT_MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    public void constructor() {
        it.hasConstructorWithParams("String", "RecordLabel")
            .thatIs(VISIBLE_TO_ALL);
    }
}

