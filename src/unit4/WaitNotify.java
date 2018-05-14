package unit4;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/8 09:54
 * @Description:
 */
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread wait = new Thread(new Wait(),"WaitThread");
        wait.start();
        SleepUtils.second(1);
        Thread wait2 = new Thread(new Wait(),"WaitThread2");
        wait2.start();
        SleepUtils.second(1);
        Thread nofity = new Thread(new Notify(),"NotifyThread");
        nofity.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {


                while (flag) {
                    System.out.println(Thread.currentThread() + " flag is true.wait@" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " flag is false.wait@" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }



    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock. notify @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(1);
            }
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock again. notify @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
