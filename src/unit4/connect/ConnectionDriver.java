package unit4.connect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/10 16:55
 * @Description:
 */
public class ConnectionDriver {



    public static final Connection createConnection(){
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(), new Class<?>[] { Connection.class },
                new ConnectionHandler());
    }
}
