package l7a1CountDownLatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutionThread extends Thread {
    int activity_min0, activity_max0, activity_min, activity_max, sleep;
    ReentrantLock l1, l2;
    CountDownLatch latch;

    public ExecutionThread(CountDownLatch latch, ReentrantLock l1, ReentrantLock l2, int act_min, int act_max,
            int activity_min, int activity_max, int sleep) {
        this.l1 = l1;
        this.l2 = l2;
        this.activity_min0 = act_min;
        this.activity_max0 = act_max;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.sleep = sleep;
        this.latch = latch;
    }

    public void run() {
        while (true) {
            activity();
            latch.countDown();
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void activity() {
        System.out.println(this.getName() + " - STATE 1");
        int k = (int) Math.round(Math.random() * (activity_max0 - activity_min0) + activity_min0);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        boolean locked1 = false;
        boolean locked2 = false;

        while (!locked1 || !locked2) {
            locked1 = l1.tryLock();
            locked2 = l2.tryLock();
            if (!locked1 || !locked2) {
                if (locked1) {
                    System.out.println(this.getName() + " - failed to acquire l2");
                    l1.unlock();
                    System.out.println(this.getName() + " - unlocked l1");
                }
                if (locked2) {
                    System.out.println(this.getName() + " - failed to acquire l1");
                    l2.unlock();
                    System.out.println(this.getName() + " - unlocked l2");
                }
            }
        }

        System.out.println(this.getName() + " - STATE 2");
        k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        System.out.println(this.getName() + " - STATE 3");

        try {
            Thread.sleep(sleep * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        l1.unlock();
        System.out.println(this.getName() + " - unlocked l1");
        l2.unlock();
        System.out.println(this.getName() + " - unlocked l2");

        System.out.println(this.getName() + " - STATE 4");
    }
}