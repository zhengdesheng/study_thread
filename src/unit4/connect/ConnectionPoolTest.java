package unit4.connect;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/10 17:25
 * @Description:
 */
public class ConnectionPoolTest {

    static ConnectPool pool = new ConnectPool(10);
    //保证所有线程同时开始执行
    static CountDownLatch start = new CountDownLatch(1);
    //main线程将会等待所有其他线程结束才执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notgot = new AtomicInteger();
        for (int i=0;i<threadCount;i++){
            Thread thread = new Thread(new ConnectionRunner(count,got,notgot,start,end,pool),"ConnectionRunnerThread"+i);
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke ："+threadCount*count);
        System.out.println("got connection:"+got);
        System.out.println("not got connection:"+notgot);
    }

}
