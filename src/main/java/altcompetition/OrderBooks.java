package altcompetition;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.lang.Comparable;

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
     * transaction occurs at the lowest sell price. Transactions should continue to
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
        // IDEA: have 2 data structures, max heap and min heap for BUY and SELL respectively (TBC)
        // Iterate across orders, determine if it is BUY or SELL order
        // if BUY,
        // compare across SELL heap, if order matches (BUY > SELL), then add to total amount of money spent
        // and update data structure
        // repeat until SELL heap is empty(ie no more SELL orders to match) or if current order does not match
        // if SELL, similar
        // return total at the end
        // should be O(n), where n is the length of orders
        
        class Order implements Comparable<Order> {
            private final int amount;
            private final double price;

            public Order(int amount, double price) {
                this.amount = amount;
                this.price = price;
            }

            public int compareTo(Order o) {
                double res = this.price - o.price;
                return res > 0 ? -1 : res < 0 ? 1 : 0;
            }

            public int getAmount() {
                return this.amount;
            }

            public double getPrice() {
                return this.price;
            }
        }


        PriorityQueue<Order> BUYHeap = new PriorityQueue<>();
        PriorityQueue<Order> SELLHeap = new PriorityQueue<>(Collections.reverseOrder());
        float totalSpent = 0;

        for (int i = 0; i < orders.size(); i++) {
            String[] currOrder = orders.get(i).split("-");
            int currAmount = Integer.parseInt(currOrder[1]);
            double currPrice = Double.parseDouble(currOrder[2]);

            if (currOrder[0].equals("BUY")) {
                BUYHeap.add(new Order(currAmount, currPrice));
            } else {
                SELLHeap.add(new Order(currAmount, currPrice));
            }
        

            while (!BUYHeap.isEmpty() && !SELLHeap.isEmpty() && BUYHeap.peek().getPrice() > SELLHeap.peek().getPrice()) {
                Order BUYOrder = BUYHeap.poll();
                Order SELLOrder = SELLHeap.poll();
                int amount = Math.min(BUYOrder.getAmount(), SELLOrder.getAmount());
                int buyAmount = BUYOrder.getAmount() - amount;
                int sellAmount = SELLOrder.getAmount() - amount;

                totalSpent += amount * SELLOrder.getPrice();

                if (buyAmount > 0) {
                    BUYHeap.add(new Order(buyAmount, BUYOrder.getPrice()));
                }

                if (sellAmount > 0) {
                    SELLHeap.add(new Order(sellAmount, SELLOrder.getPrice()));
                }
            }
        }

        return String.format("%.2f", totalSpent);

        /*
        for (int i = 0; i < orders.size(); i++) {
            String[] currOrder = orders.get(i).split("-");
            int currAmount = Integer.parseInt(currOrder[1]);
            double currPrice = Double.parseDouble(currOrder[2]);

            if (currOrder[0].equals("BUY")) {
                while (currAmount > 0 && !SELLHeap.isEmpty() && SELLHeap.peek().getPrice() < currPrice) {
                    Order SELLorder = SELLHeap.poll();
                    int amountToBuy = Math.min(currAmount, SELLorder.getAmount());
                    totalSpent += amountToBuy * SELLorder.getPrice();
                    currAmount -= amountToBuy;
                    int sellAmount = SELLorder.getAmount() - amountToBuy;

                    if (sellAmount > 0) {
                        SELLHeap.add(new Order(sellAmount, SELLorder.getPrice()));
                    }
                }

                if (currAmount > 0) {
                    BUYHeap.add(new Order(currAmount, currPrice));
                }

            } else {
                // currOrder[0] == "SELL"
                while (currAmount > 0 && !BUYHeap.isEmpty() && BUYHeap.peek().getPrice() > currPrice) {
                    Order BUYorder = BUYHeap.poll();
                    int amountToBuy = Math.min(currAmount, BUYorder.getAmount());
                    totalSpent += amountToBuy * currPrice;
                    currAmount -= amountToBuy;
                    int buyAmount = BUYorder.getAmount() - amountToBuy;

                    if (buyAmount > 0) {
                        BUYHeap.add(new Order(buyAmount, BUYorder.getPrice()));
                    }
                }

                if (currAmount > 0) {
                    SELLHeap.add(new Order(currAmount, currPrice));
                }

            }
        }

        return String.format("%.2f", totalSpent);
        */
    }

    public static void main(String[] args) {
        List<String> orders = new ArrayList<String>();
        orders.add("SELL-10-100");
        orders.add("SELL-2-90.00");
        orders.add("BUY-11-140.00");
        
        System.out.println(new OrderBooks().processOrderBooks(orders));

        List<String> orders2 = new ArrayList<String>();
        orders2.add("BUY-11-9.00");
        orders2.add("SELL-10-9.00");
        orders2.add("SELL-1-1");
        
        System.out.println(new OrderBooks().processOrderBooks(orders2));
    }
}
