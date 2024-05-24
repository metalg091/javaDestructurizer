import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class ElectionStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("election.Election")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test
    public void fieldVoteCounts() {
        it.hasFieldOfType("voteCounts", "array of int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test
    public void constructor() {
        it.hasNoArgConstructor()
            .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodAddVote() {
        it.hasMethodWithParams("addVote", "election.candidate.Candidate")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodAddVotes() {
        it.hasMethodWithParams("addVotes", "election.candidate.Candidate", "int")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test
    public void methodGetCandidateCountWithMoreVotesThan() {
        it.hasMethodWithParams("getCandidateCountWithMoreVotesThan", "int")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
            .thatReturns("int");
    }

    @Test
    public void methodGetCandidatesWithMoreVotesThan() {
        it.hasMethodWithParams("getCandidatesWithMoreVotesThan", "int")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("array of election.candidate.Candidate");
    }

    @Test
    public void methodGetWinner() {
        it.hasMethodWithNoParams("getWinner")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("election.candidate.Candidate");
    }

    @Test
    public void methodGetWinningIdx() {
        it.hasMethodWithNoParams("getWinningIdx")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
            .thatReturns("int");
    }
}

