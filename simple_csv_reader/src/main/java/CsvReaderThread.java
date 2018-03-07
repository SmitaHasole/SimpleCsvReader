package main.java;

import java.io.*;
import java.util.concurrent.BlockingQueue;

/**
 * created by Smita Hasole on 02-03-2018
 */

public class CsvReaderThread implements Runnable {
    BlockingQueue<String> blockingQueue;
    boolean isHeader = true;

    public CsvReaderThread(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        try (BufferedReader br = new BufferedReader(new
                FileReader(new File("D:/simple_csv_reader/src/data/GA000001/GA000001.AncientAncestry.txt")))) {

            String line = null;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    blockingQueue.put(line);
                    System.out.println("m in reader : " + line);
                }
                isHeader = false;
            }
            // blockingQueue.put("EOF");  //When end of file has been reached

        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
