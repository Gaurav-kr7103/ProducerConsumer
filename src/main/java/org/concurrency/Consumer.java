package org.concurrency;

public class Consumer implements Runnable{
    private BufferedQueue queue;
    public Consumer(BufferedQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            queue.pop();
//            System.out.println("Element popped : " + queue.pop());
        } catch (InterruptedException e) {
            System.exit(13);
        }
    }
}
