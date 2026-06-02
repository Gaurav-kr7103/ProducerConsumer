package org.concurrency;

public class Consumer implements Runnable{
    private BufferedQueue queue;
    public Consumer(BufferedQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : ");
        try {
            System.out.println("Element popped : " + queue.pop());
        } catch (InterruptedException e) {
            System.exit(13);
        }
    }
}
