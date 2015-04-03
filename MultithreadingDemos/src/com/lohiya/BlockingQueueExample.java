package com.lohiya;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * BlockingQueue Implementations
 * 
 * Since BlockingQueue is an interface, you need to use one of its
 * implementations to use it. The java.util.concurrent package has the following
 * implementations of the BlockingQueue interface (in Java 6):
 * 
 * ArrayBlockingQueue 
 * DelayQueue 
 * LinkedBlockingQueue 
 * PriorityBlockingQueue
 * SynchronousQueue
 *
 */

class Producer implements Runnable{

    protected BlockingQueue<String> queue = null;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            queue.put("1");
            System.out.println("Produced 1");
            Thread.sleep(1000);
            queue.put("2");
            System.out.println("Produced 2");
            Thread.sleep(1000);
            queue.put("3");
            System.out.println("Produced 3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{

    protected BlockingQueue<String> queue = null;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println("Consumed "+queue.take());
            //Thread.sleep(5000);
            System.out.println("Consumed "+queue.take());
            System.out.println("Consumed "+queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class BlockingQueueExample {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
	}

}
