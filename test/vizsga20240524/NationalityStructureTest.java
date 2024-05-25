package test.vizsga20240524;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import check.CheckThat;

public class NationalityStructureTest {
    @Test
    public void testEnum() {
        CheckThat.theEnum("aoe.game.nationality.Nationality")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .hasEnumElements("BRITISH", "GERMAN", "FRENCH", "RUSSIAN");
    }
}

