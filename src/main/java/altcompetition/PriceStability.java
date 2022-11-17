package altcompetition;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.PriorityQueue;
import java.util.Collections;

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
     * Given a list of prices and a threshold k, find the longest period where the
     * price is "stable", i.e. the minimum and maximum price do not differ by k or more.
     *
     * Your function should return the arithmetic mean of the prices in this period,
     * formatted to 2 decimal places.
     *
     * Number of prices <= 100,000
     * 1 <= k <= 1,500
     */
    public String checkRange(List<Integer> prices, int k) {
        // IDEA: Use a sliding window to keep track of the min and max prices in the window
        // Keep 2 data structure, max heap and min heap to find current max and min in the window
        // If the window is stable, update the max length and keep track of first and last index
        // If the window is not stable, increment left pointer and repeat until the window is stable
        // Keep popping off from min heap and max heap until a suitable min and max is found (within the current window)
        // Time complexity: O(n) where n is the number of prices

        class Price implements Comparable<Price> {
            private final int index;
            private final int price;

            public Price(int index, int price) {
                this.index = index;
                this.price = price;
            }

            public int compareTo(Price o) {
                int res = this.price - o.price;
                return res > 0 ? -1 : res < 0 ? 1 : 0;
            }

            public int getIndex() {
                return this.index;
            }

            public int getPrice() {
                return this.price;
            }

            @Override
            public String toString() {
                return String.format("Index: %d Price: %d", this.index, this.price);
            }
        }

        // Initialize variables
        PriorityQueue<Price> maxHeap = new PriorityQueue<>();
        PriorityQueue<Price> minHeap = new PriorityQueue<>(Collections.reverseOrder());
        int rangeLow = 0;
        int rangeHigh = 0; //inclusive
        int leftPointer = 0;

        maxHeap.add(new Price(0, prices.get(0)));
        minHeap.add(new Price(0, prices.get(0)));

        for (int i = 1; i < prices.size(); i++) {
            int price = prices.get(i);
            maxHeap.add(new Price(i, price));
            minHeap.add(new Price(i, price));

            // System.out.println(rangeLow + ", " + rangeHigh);

            // Check if window is stable
            if (maxHeap.peek().getPrice() - minHeap.peek().getPrice() < k) {
                // Window is stable
                // System.out.println("Window is stable");
                if (i - leftPointer + 1 > rangeHigh - rangeLow + 1) {
                    rangeLow = leftPointer;
                    rangeHigh = i;
                }
            } else {
                // Window is not stable
                // Increment left pointer until window is stable
                // System.out.println("Window is not stable");
                // System.out.println(maxHeap);
                // System.out.println(minHeap);

                while (maxHeap.peek().getPrice() - minHeap.peek().getPrice() >= k) {
                    leftPointer++;

                    while (maxHeap.peek().getIndex() < leftPointer) {
                        maxHeap.poll();
                    }

                    while (minHeap.peek().getIndex() < leftPointer) {
                        minHeap.poll();
                    }
                }

                // System.out.println(maxHeap);
                // System.out.println(minHeap);
            }
        }
        
        System.out.println(rangeLow + ", " + rangeHigh);

        int total = 0;

        for (int i = rangeLow; i <= rangeHigh; i++) {
            total += prices.get(i);
        }

        float average = total / (float) (rangeHigh - rangeLow + 1);

        return Float.toString(average);
    }

    // public static void main(String[] args) {
    //     List<Integer> prices = Arrays.asList(new Integer[]{10, 2, 3, 5, 4});
    //     System.out.print(new PriceStability().checkRange(prices, 3));
    // }

}
