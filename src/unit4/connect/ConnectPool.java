package unit4.connect;

import unit4.connect.test.*;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/10 16:52
 * @Description:
 */
public class ConnectPool {

    private LinkedList<Connection> pool = new LinkedList<Connection>();

    public ConnectPool(int init) {
       if(init >0){
           for (int i=0;i<init;i++){
               pool.addLast(ConnectionDriver.createConnection());
           }
       }
    }

    /**
     * 释放连接
     * @param connection
     */
    public void releaseConnection(Connection connection){
        if( connection !=null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 超时等待
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized(pool){
            if(mills <=0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis()+mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining >0){
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
