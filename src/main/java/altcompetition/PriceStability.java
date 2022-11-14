package altcompetition;

import java.util.List;
import java.util.stream.Collectors;

public class PriceStability extends CompetitionTest {

    @Override
    protected String runTest(List<String> args) {
        int k = Integer.valueOf(args.get(0));
        List<Integer> prices = args.stream().skip(1).map(Integer::valueOf).collect(Collectors.toList());
        return checkRange(prices, k);
    }

    /**
     * Your task is to find periods of price stability within a given threshold
     *
     * Given a list of prices and a threshold k, find the longest period where
     * the minimum and maximum price do not differ by k or more.
     *
     * Your function should return the average of all the prices in this period.
     *
     * Number of prices <= 100,000
     * 1 <= k <= 1,500
     */
    public String checkRange(List<Integer> prices, int k) {
        return null;
    }

}
