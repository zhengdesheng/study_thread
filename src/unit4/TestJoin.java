package unit4;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/10 10:46
 * @Description:
 */
public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Test(),"test1");
        Thread t2 = new Thread(new Test(),"test2");

        t1.start();
        t1.join();
        t2.start();
        System.out.println("main");
    }

    static class Test implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
