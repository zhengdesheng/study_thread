import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Author: zhengdesheng
 * @Date: 17/4/27 16:59
 * @Description:
 */
public class MultiThread {

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for (ThreadInfo info:threadInfos) {
            System.out.println("["+info.getThreadId()+"] "+info.getThreadName());
        }
    }
}
