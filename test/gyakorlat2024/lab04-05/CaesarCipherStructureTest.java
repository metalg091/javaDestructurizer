package test.gyakorlat2024.lab04-05;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class CaesarCipherStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("cipher.CaesarCipher")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void fieldShift() {
        it.hasFieldOfType("shift", "int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void constructor() {
        it.hasConstructorWithParams("int")
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodDecrypt() {
        it.hasMethodWithParams("decrypt", "String")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("String");
    }

    @Test
    public void methodEncrypt() {
        it.hasMethodWithParams("encrypt", "String")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("String");
    }
}

