package main.java;

import main.java.model.AncientAncestry;
import java.util.concurrent.BlockingQueue;

/**
 * created by Smita Hasole on 02-03-2018
 */

public class ParsingThread implements Runnable {

    protected BlockingQueue<String> blockingQueue = null;

    public ParsingThread(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        AncientAncestry ancientAncestry;
        try {
            while (true) {

                String buffer = blockingQueue.take();
                String[] token = buffer.split("\t");
                System.out.println("array is : "+ token[0]);
                ancientAncestry = new AncientAncestry(token[0], token[1],Double.parseDouble( token[2]), Double.parseDouble(token[3]));

                if (buffer.equals("EOF")) {
                    break;
                }
                System.out.println("m in get string function : " + ancientAncestry.toString());
            }
        } catch (InterruptedException e) {

        }
    }
}

