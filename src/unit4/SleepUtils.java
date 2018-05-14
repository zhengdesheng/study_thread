package unit4;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/4 09:12
 * @Description:
 */
public class SleepUtils {

    public static final  void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
