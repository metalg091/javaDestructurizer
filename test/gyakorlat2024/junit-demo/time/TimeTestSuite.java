package time;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@SelectClasses({
      TimeTestSuite.StructuralTests.class
    , TimeTestSuite.FunctionalTests.class
})
@Suite public class TimeTestSuite {
    @SelectClasses({
        TimeStructureTest.class
    })
    @Suite public static class StructuralTests {}

    @SelectClasses({
        TimeTest.class
    })
    @Suite public static class FunctionalTests {}
}

