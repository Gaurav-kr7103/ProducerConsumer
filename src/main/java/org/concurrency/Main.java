package org.concurrency;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //making a queue of size 5
        // 1,2,3,4,5
        // popping 1
        // null, 2,3,4,5
        // inserting 6
        BufferedQueue queue = new BufferedQueue(5);
        for (int i=1; i<=5; i++) {
            queue.put(i);
        }
        System.out.println(queue.toString());
        System.out.println(queue.pop());
        queue.put(6);
        System.out.println(queue.toString());
    }
}