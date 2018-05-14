package unit4.connect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/10 16:55
 * @Description:
 */
public class ConnectionHandler implements InvocationHandler{
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("commit")){
            TimeUnit.MILLISECONDS.sleep(100);
        }
//        Class.forName("com.mysql.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:3306/riskdata";
//
//        String user = "root";
//
//        String password = "123456";
//
//        Connection conn = DriverManager.getConnection(url, user, password);
//        return conn;
        return null;
    }


}
