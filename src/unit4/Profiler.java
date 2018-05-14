package unit4;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/10 11:06
 * @Description:
 */
public class Profiler {

    private static final ThreadLocal<Long> Time;

    static {
        Time = new ThreadLocal<Long>() {
            @Override
            protected Long initialValue() {
                return System.currentTimeMillis();
            }
        };
    }

    public static  final void begin(){
        Time.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis()-Time.get();
    }

    public static void main(String[] args) {
        //Profiler.begin();
        SleepUtils.second(1);
        System.out.println("Cost:"+Profiler.end()+ "mills");
    }
}
