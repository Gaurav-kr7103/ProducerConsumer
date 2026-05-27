package org.concurrency;

public class Producer implements Runnable{
    private BufferedQueue queue;
    public Producer(BufferedQueue queue) {
        this.queue = queue;
    }
    private int randomNo() {
        return (int) (Math.random()*100 + 1);
    }
    @Override
    public void run() {
        try {
            queue.put(randomNo());
        } catch (InterruptedException e) {
            System.exit(12);
        }
    }
}
