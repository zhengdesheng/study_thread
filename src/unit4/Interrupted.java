package unit4;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/4 09:48
 * @Description:
 */
public class Interrupted {

    static  class  SleepRunner implements  Runnable{
        @Override
        public void run() {
            while (true){
                SleepUtils.second(10);
            }
        }
    }

    static class  BysyRunner implements Runnable{
        @Override
        public void run() {
            while (true){

            }
        }
    }

    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepRunner(),"sleeprunner");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BysyRunner(),"busyrunner");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        SleepUtils.second(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrrupted is:"+sleepThread.isInterrupted());
        System.out.println("busyThread interrrupted is:"+busyThread.isInterrupted());
    }
}
