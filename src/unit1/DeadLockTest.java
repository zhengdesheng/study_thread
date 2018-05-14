package unit1;

/**
 * @Author: zhengdesheng
 * @Date: 17/4/26 11:32
 * @Description:
 */
public class DeadLockTest {

     private static  String A="A";

     private static  String B = "B";

    public static void main(String[] args) {
            new DeadLockTest().deadLock();
    }

    private void deadLock(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("thread1");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    synchronized (A){
                        System.out.println("thread2");
                    }
                }
            }
        });
        thread1.start();
        thread2.start();

    }

}
