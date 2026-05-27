package org.concurrency;

/**
 * This queue is ciruclar queue
 * Also this queue is thread-safe
 */
public class BufferedQueue {
    private int size;
    private int front;
    private int back;
    private Object[] arr;
    BufferedQueue(int size) {
        this.size = size;
        front = back = 0;
        arr = new Object[size + 1];
    }
    private boolean isEmpty() {
        return front == back;
    }
    private boolean isFull() {
        return (back+1)%(size+1) == front;
    }
    public synchronized void put (Object val) throws InterruptedException {
        while (isFull()) {
            System.out.println("The queue is full");
            wait();//this releases the lock
        }
        arr[back] = val;
        back = (back + 1)%(size+1);
        notifyAll();//informs both producer and consumer, but for consumer to consume
        System.out.println(Thread.currentThread().getName() + " : ");
        System.out.println("Element Insert : " + val.toString());
    }
    public synchronized Object pop () throws InterruptedException {
        Object val = null;
        while (isEmpty()) {
            System.out.println("The queue is empty");
            wait();
        }
        val = arr[front];
        arr[front] = null;
        front = (front+1)%(size+1);
        notifyAll();//informs both producer and consumer, but for producer to produce
        System.out.println(Thread.currentThread().getName() + " : " + val);
        return val;
    }
    @Override
    public synchronized String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0; i<= this.size; i++) {
            if (arr[i] == null)
                stringBuffer.append("null");
            else
                stringBuffer.append(arr[i]);
            if (i != this.size)
                stringBuffer.append(", ");
            else
                stringBuffer.append(" <- queue");
        }
        return stringBuffer.toString();
    }
}
