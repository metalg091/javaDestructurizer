package book;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class BookStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("printed.material.Book")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatHas(TEXTUAL_REPRESENTATION);
    }

    @Test
    public void fieldDefaultAuthor() {
        it.hasField("defaultAuthor", ofType("String"))
            .thatIs(USABLE_WITHOUT_INSTANCE, NOT_MODIFIABLE, VISIBLE_TO_ALL)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void fieldDefaultTitle() {
        it.hasField("defaultTitle", ofType("String"))
            .thatIs(USABLE_WITHOUT_INSTANCE, NOT_MODIFIABLE, VISIBLE_TO_ALL)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void fieldDefaultPageCount() {
        it.hasField("defaultPageCount", ofType("int"))
            .thatIs(USABLE_WITHOUT_INSTANCE, NOT_MODIFIABLE, VISIBLE_TO_ALL)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void fieldAuthor() {
        it.hasField("author", ofType("String"))
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    public void fieldTitle() {
        it.hasField("title", ofType("String"))
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    public void fieldPageCount() {
        it.hasField("pageCount", ofType("int"))
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_SUBCLASSES)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test
    public void constructor01() {
        it.hasConstructor(withNoArgs())
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void constructor02() {
        it.hasConstructor(withArgs("String", "String", "int"))
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodCheckInitData() {
        it.hasMethod("checkInitData", withParams("String", "String", "int"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
            .thatReturnsNothing();
    }

    @Test
    public void methodCreateReference() {
        it.hasMethod("createReference", withParams("String", "int", "int"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("String");
    }

    @Test
    public void methodDecode() {
        it.hasMethod("decode", withParams("String"))
            .thatIs(FULLY_IMPLEMENTED, USABLE_WITHOUT_INSTANCE, VISIBLE_TO_ALL)
            .thatReturns("Book");
    }

    @Test
    public void methodGetAuthorWithInitials() {
        it.hasMethod("getAuthorWithInitials", withNoParams())
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_SUBCLASSES)
            .thatReturns("String");
    }

    @Test
    public void methodGetPrice() {
        it.hasMethod("getPrice", withNoParams())
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("int");
    }

    @Test
    public void methodGetShortInfo() {
        it.hasMethod("getShortInfo", withNoParams())
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("String");
    }

    @Test
    public void methodInitBook() {
        it.hasMethod("initBook", withParams("String", "String", "int"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_SUBCLASSES)
            .thatReturnsNothing();
    }
}

