package org.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This queue is ciruclar queue
 * Also this queue is thread-safe
 */
public class BufferedQueue {
    private int size;
    private int front;
    private int back;
    private Object[] arr;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition producerWaitingQueue = lock.newCondition();
    private final Condition consumerWaitingQueue = lock.newCondition();

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
    public void put (Object val) throws InterruptedException {
        lock.lock();
        try {
            while (isFull()) {
                System.out.println("The queue is full");
                producerWaitingQueue.await();//making the producer thread wait in the dedicated queue
            }
            arr[back] = val;
            back = (back + 1)%(size+1);
            consumerWaitingQueue.signal();//informs both consumer queue to come
            System.out.println(Thread.currentThread().getName() + " : pushed : " + val.toString());
        } finally {
            lock.unlock();
        }
    }
    public Object pop () throws InterruptedException {
        lock.lock();
        try {
            Object val = null;
            while (isEmpty()) {
                System.out.println("The queue is empty");
                consumerWaitingQueue.await();//making the consumer thread to wait in dedicated queue
            }
            val = arr[front];
            arr[front] = null;
            front = (front+1)%(size+1);
            producerWaitingQueue.signal();//informs producer thread to resume (again try for Critical Section lock)
            System.out.println(Thread.currentThread().getName() + " : popped :  " + val);
            return val;
        } finally {
            lock.unlock();
        }
    }
    @Override
    public String toString() {
        lock.lock();
        try {
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
        } finally {
            lock.unlock();
        }
    }
}
