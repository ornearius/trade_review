package studio.coffeesocial;

import java.util.Collections;
import java.util.List;

public class Trader {

    public static void main(String[] args) {
        System.exit(0);
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
        return Collections.max(priceList.subList(1,priceList.size())) - priceList.get(0);
    }
}
