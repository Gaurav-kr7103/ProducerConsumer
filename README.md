
# Producer Consumer Problem

A deep dive into Java concurrency by implementing the classic **Producer Consumer Problem** using multiple synchronization techniques.

---

# Implementations

## 1. Synchronized

### Methods Used
- `wait()`
- `notify()`
- `notifyAll()`

### Key Idea

`synchronized` uses the intrinsic monitor lock of an object.

```java
synchronized(this) {
    // critical section
}
````

Only one thread can enter the critical section at a time for a particular object.

### Characteristics

* Easy to implement
* Built directly into Java
* Automatically acquires and releases locks
* Uses a **single waiting queue** for all waiting threads

### Limitation

When using `wait()`, every waiting thread goes into the same monitor queue, which provides less flexibility for advanced thread communication.

---

## 2. ReentrantLock + Condition

### Methods Used

* `lock()`
* `unlock()`
* `await()`
* `signal()`
* `signalAll()`

### Key Idea

`ReentrantLock` is an explicit locking mechanism that provides more control than `synchronized`.

```java
ReentrantLock lock = new ReentrantLock();
```

### Characteristics

* More flexible than `synchronized`
* Supports fairness policies
* Allows multiple condition queues
* Better suited for complex thread coordination

### Important Concept

With `Condition`, we can create **multiple waiting queues** for different groups of threads.

Example:

* Producer queue
* Consumer queue

This is a major improvement over the single monitor queue used by `synchronized`.

---

## 3. Semaphores

### Methods Used

* `acquire()`
* `release()`

### Key Idea

A `Semaphore` should be viewed as a **synchronized counter**.

```java
Semaphore semaphore = new Semaphore(5);
```

The number represents available permits.

### Important Understanding

People often associate semaphores with parallelism because multiple permits allow multiple threads to proceed simultaneously.

However:

* Semaphores are **not inherently parallelism tools**
* They are primarily **permit/count management mechanisms**
* They help coordinate access to resources

### Characteristics

* Controls how many threads may proceed
* Useful for bounded resources
* Commonly used alongside locks
* Excellent for resource counting problems

### Example Use Cases

* Parking lot systems
* Connection pools
* Producer Consumer buffers
* Rate limiting

---

# What This Project Focuses On

This repository focuses on understanding:

* Thread synchronization
* Critical sections
* Thread communication
* Blocking and waiting
* Race conditions
* Coordination between producer and consumer threads
* Differences between Java concurrency primitives

---

# Goal

The purpose of this project is not just to solve the Producer Consumer problem, but to deeply understand:

* How threads communicate
* How locks actually behave
* Why waiting queues matter
* When to use each synchronization primitive
* Tradeoffs between simplicity and flexibility

```
Goal is not to solve and learn rather learn while solving
```
