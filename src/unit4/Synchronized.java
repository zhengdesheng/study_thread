package unit4;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/4 10:05
 * @Description:
 */
public class Synchronized {

    public static void main(String[] args) {
        synchronized (Synchronized.class){
            m();
        }
    }

    public static synchronized void m(){

    }
}
