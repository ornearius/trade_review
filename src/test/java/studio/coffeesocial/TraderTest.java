package studio.coffeesocial;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;

class TraderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TraderTest.class);

    @Test
    void mapReduce() {
        var tradeData = Arrays.asList(10, 7, 5, 8, 11, 9);

        assertEquals(6, Trader.mapReduce(tradeData));
    }

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

    @Test
    void smallData() {
        var tradeDate = Collections.singletonList(5);

        assertEquals(Integer.MIN_VALUE, Trader.profit(tradeDate));
    }

    @Test
    void timeTest() {
        // Test with 8 hours of randomly generated data
        var testData = generateData(0,  60 * 8, 5, 55);


        var startRecursive = System.nanoTime();
        var recursive = Trader.recursive(testData);
        var endRecursive = System.nanoTime();

        var startMapReduce = System.nanoTime();
        var mapReduce = Trader.mapReduce(testData);
        var endMapReduce = System.nanoTime();

        assertEquals(recursive, mapReduce);

        var startStream = System.nanoTime();
        var stream = Trader.simpleStream(testData);
        var endStream = System.nanoTime();

        assertEquals(stream, mapReduce);

        LOGGER.info("\nRecursive: {}\nMapReduce: {}\nStream: {}", endRecursive - startRecursive, endMapReduce - startMapReduce, endStream - startStream);

    }

    List<Integer> generateData(int seed, int datalength, int minimum, int maximum) {
        return new Random(seed).ints(datalength, minimum, maximum).boxed().collect(Collectors.toList());
    }
}