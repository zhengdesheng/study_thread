package unit4;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/10 10:35
 * @Description:
 */
public class Join {

    static class Domio implements Runnable{
        private Thread thread;
        public  Domio(Thread thread){
            this.thread=thread;
        }


        @Override
        public void run() {
            try {
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() +"terminate1");
        }
    }

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i=0;i<10;i++){
            Thread thread = new Thread(new Domio(previous),String.valueOf(i));
            thread.start();
            previous=thread;
        }
        SleepUtils.second(5);
        System.out.println(Thread.currentThread().getName()+" terminate.");
    }
}
