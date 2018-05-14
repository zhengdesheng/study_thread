package unit2;











import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhengdesheng
 * @Date: 17/4/27 10:00
 * @Description:
 */
public class CASTest {

    private int i=0;
    private AtomicInteger atomicI = new AtomicInteger(0);

    public static void main(String[] args) {
            final CASTest cas = new CASTest();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start =System.currentTimeMillis();
        for (int j=0;j<100;j++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int k=0;k<10000;k++){
                        cas.count();
                        cas.safecount();
                    }
                }
            });
            ts.add(t);

        }
        for (Thread t:ts) {
            t.start();
        }

        for (Thread t:ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis()-start);
    }

    private void safecount(){
        for (;;){
            int i = atomicI.get();
            boolean suc  = atomicI.compareAndSet(i,++i);
            if (suc)
                break;
        }
    }

    private void count(){
        i++;
    }


}
