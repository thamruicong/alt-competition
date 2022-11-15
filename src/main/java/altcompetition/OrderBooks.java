package altcompetition;

import java.util.List;

public class OrderBooks extends CompetitionTest {

    @Override
    protected String runTest(List<String> args) {
        return processOrderBooks(args);
    }

    /**
     * Your task is to process orders for a Security being traded on an exchange.
     * You receive a list of Buy and Sell orders that you need to process in order.
     *
     * When there are orders on opposite sides that cross (e.g. a bid to buy
     * which is higher than an offer to sell), the orders should be matched and a
     * transaction occurs at the closest sell price. Transactions should continue to
     * be made until the condition no longer holds true.
     *
     * Your function should return the total amount of money spent.
     *
     * The format of the transactions will be the order type, followed by the
     * ammount, followed by the price per unit (to two decimal places)
     * For example: "BUY-128-12600.25" or "SELL-1-44.56"
     *
     * Size of each order <= 500
     * Each price <= 10^5
     * Number of orders <= 5 * 10^6
     */
    public final String processOrderBooks(List<String> orders) {
        return null;
    }
}
