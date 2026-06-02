package org.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        BufferedQueue queue = new BufferedQueue(5);
        //produce
        for (int i=0; i<10; i++) {
            executor.execute(new Producer(queue));
        }
        //consume
        for (int i=0; i<10; i++) {
            executor.execute(new Consumer(queue));
        }
        for (int i=0; i<4; i++) {
            executor.execute(() -> System.out.println("\n" + queue.toString()));
        }
    }
}