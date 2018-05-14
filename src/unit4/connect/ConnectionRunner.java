package unit4.connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/10 17:32
 * @Description:
 */
public class ConnectionRunner implements Runnable{

    private int count;

    private AtomicInteger got;

    private AtomicInteger notgot;

    private CountDownLatch latch;

    private CountDownLatch end;

    private ConnectPool pool;

    public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notgot, CountDownLatch latch, CountDownLatch end, ConnectPool pool) {
        this.count = count;
        this.got = got;
        this.notgot = notgot;
        this.latch = latch;
        this.end = end;
        this.pool = pool;
    }

    @Override
    public void run() {

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (count>0){
            try {
                Connection connection = pool.fetchConnection(1000);
                if(connection != null){
                    try {
                        connection.createStatement();
                        connection.commit();
                    }finally {
                        pool.releaseConnection(connection);
                        got.incrementAndGet();
                    }
                }else{
                    notgot.incrementAndGet();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                count--;
            }

        }
        end.countDown();

    }
}
