package studio.coffeesocial;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TraderTest {

    @Test
    void recursive() {

        var tradeData = Arrays.asList(10, 7, 5, 8, 11, 9);

        assertEquals(6, Trader.recursive(tradeData));

    }

    @Test
    void profit() {
        var tradeDate = Arrays.asList(1,2,3,4);

        assertEquals(3, Trader.profit(tradeDate));
    }

    @Test
    void loss() {
        var tradeDate = Arrays.asList(10,8,6,2,1);

        assertEquals(-2, Trader.profit(tradeDate));
    }

    @Test
    void riseAndFall() {
        var tradeDate = Arrays.asList(1, 4, 8, 100, 60, 5);

        assertEquals(99, Trader.profit(tradeDate));
    }

    @Test
    void fallAndRise() {
        var tradeDate = Arrays.asList(50, 40, 30, 100, 5);

        assertEquals(50, Trader.profit(tradeDate));
    }
}