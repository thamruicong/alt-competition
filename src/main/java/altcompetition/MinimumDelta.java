package altcompetition;

import java.util.List;

public class MinimumDelta extends CompetitionTest {

    @Override
    protected String runTest(List<String> args) {
        return Integer.toString(minimumDelta(args.get(0), args.get(1)));
    }

    /**
     * Your task is to find the most efficient way of transforming one
     * string into another.
     *
     * Given two strings, your function should return the minimum number
     * of operations it would take to convert the first string into the
     * second.
     *
     * You have 3 operations at your disposal, all equal in cost:
     * Adding, removing and substituting characters
     *
     * Length of each string <= 20,000
     */
    public static int minimumDelta(String a, String b) {
        return -1;
    }
}
