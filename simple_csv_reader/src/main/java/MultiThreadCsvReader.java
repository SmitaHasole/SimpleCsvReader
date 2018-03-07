package main.java;

/**
 * created by Smita Hasole on 02-03-2018
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MultiThreadCsvReader {

    public static void main(String[] args) {

        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        System.out.println("capacity is : "+ queue.remainingCapacity());
        CsvReaderThread reader = new CsvReaderThread(queue);
        ParsingThread writer = new ParsingThread(queue);

        new Thread(reader).start();
        new Thread(writer).start();

    }

}