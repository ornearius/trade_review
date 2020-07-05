# Trade Review

Simple tool to process trading data and report the maximum possibe profit for a day.

## Requirements

### Given

Yesterday's stock prices as a list

### When

- The indices are the time in minutes past trade opening time, which was 10:00am local time.
- The values are the price in dollars of the stock at that time.
- You must buy before you sell. 
- You may not buy and sell in the same time step (at least 1 minute must pass).

### Then

Maximum possible profit or minimum loss is reported for the day

#### Notes

So if the stock cost $5 at 11:00am, stock_prices_yesterday[60] = 5.
Write an efficient function that takes an array of stock prices and returns the best profit could have been made from 1 purchase and 1 sale of 1 stock.

For example:

int[] stockPrices = {10, 7, 5, 8, 11, 9};

 

Assert.assertEquals (6, getMaxProfit(stockPrices)); // returns 6 (buy at $5 sell at $11)

## Design and Implementation

There are three alternative designs investigated as they have different trade offs when it comes to efficiency.

The recursive function is a simple stateless search over the whole data set and is fast for small amounts of data.

MapReduce and SimpleStream use a pre-processor step which removes many duplicates as you can safely ignore any after the
first instance as they can never return a higher result.

Quick testing showes the streamed implementation is faster than the map reduce one most likely due to the threading overhead.
Lightweight threads should improve this but generally multithreading is more helpful with long running tasks or distributed 
over a network.
```
Recursive: 10127642 ns
MapReduce:  5970417 ns
Stream:     1807245 ns
```
Note that these are one off tests and the times likely reflect this.


The choice of typed data structures verses primative arrays is to 
- leverage the standard library functions. These have had 25 years of optimization so likely to be faster and more 
reliable that handrolled implementations.
- make the code easier to read and test therefore less error prone
- javac and jit will optimise standard code patterns to improve efficiency

These may not hold true for other languages.
 

