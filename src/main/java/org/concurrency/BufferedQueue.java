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
    public void put (Object val) {
        if (isFull()) {
            System.out.println("The queue is full");
            return;
        }
        arr[back] = val;
        back = (back + 1)%(size+1);
        System.out.println("Element Insert : " + val.toString());
    }
    public Object pop () {
        Object val = null;
        if (isEmpty()) {
            System.out.println("The queue is empty");
            return val;
        }
        val = arr[front];
        arr[front] = null;
        front = (front+1)%(size+1);
        return val;
    }
    @Override
    public String toString() {
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
