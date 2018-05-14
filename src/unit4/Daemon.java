package unit4;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/4 09:28
 * @Description:
 */
public class Daemon {

    static  class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println("i am come in");
                SleepUtils.second(10000);
            }finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(),"DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }
}
