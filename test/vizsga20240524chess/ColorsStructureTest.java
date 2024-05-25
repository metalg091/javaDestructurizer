package test.vizsga20240524chess;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class ColorsStructureTest {
    
    @BeforeAll
    public static void init() {
        CheckThat.theEnum("chess.utils.Colors")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void testEnumElements(){  
        it.hasEnumElements("LIGHT", "DARK");
    }

}

