package studio.coffeesocial;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Trader {

    public static void main(String[] args) {
        System.exit(0);
    }

    /**
     * Uses the constraint that the sale must be after the purchase. This means we only need to look at the first time
     * a value appears as all subsequent appearances can never return a higher value.
     *
     * @param priceList list of time ordered price values
     * @return highest trade profit
     */
    protected static Integer mapReduce(List<Integer> priceList) {
        var prices = new HashSet<>(priceList);

        return prices.parallelStream()
                .map(x -> profit(priceList.subList(priceList.indexOf(x), priceList.size())))
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException("Missing max value"));

    }

    /**
     * Uses the constraint that the sale must be after the purchase. This means we only need to look at the first time
     * a value appears as all subsequent appearances can never return a higher value.
     *
     * @param priceList list of time ordered price values
     * @return highest trade profit
     */
    protected static Integer simpleStream(List<Integer> priceList) {
        var prices = new HashSet<>(priceList);

        return prices.stream()
                .map(x -> profit(priceList.subList(priceList.indexOf(x), priceList.size())))
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException("Missing max value"));

    }

    /**
     * Recursively process the data to find the maximum profit
     * @param priceList list of time ordered price values
     * @return highest differential in trade values
     */
    protected static Integer recursive(List<Integer> priceList) {
        if( priceList.size() < 3 ) {
            return profit(priceList);
        } else {
            return Integer.max(profit(priceList), recursive(priceList.subList(1, priceList.size())));
        }
    }

    /**
     * Given a list of values it will return the greatest difference between the first value in the list and the highest value in the remaining list
     * @param priceList list of price values
     * @return maximum possible difference
     */
    protected static Integer profit(List<Integer> priceList ) {
        return priceList.size() > 1 ? Collections.max(priceList.subList(1,priceList.size())) - priceList.get(0) : Integer.MIN_VALUE;
    }
}
