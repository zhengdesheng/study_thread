package unit4;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/10 11:25
 * @Description:
 */
public class ThreadLocalTest {

    static class ResourceClass {
        public static  final java.lang.ThreadLocal<String> RESOUCE_1 = new java.lang.ThreadLocal<String>();

        public static  final java.lang.ThreadLocal<String> RESOUCE_2 = new java.lang.ThreadLocal<String>();
    }

    static class A {
        private void setOne(String value){
            ResourceClass.RESOUCE_1.set(value);
        }

        private void setTwo(String value){
            ResourceClass.RESOUCE_2.set(value);
        }
    }

    static class B{
        public  void display(){
            System.out.println(ResourceClass.RESOUCE_1.get()+":"+ResourceClass.RESOUCE_2.get());
        }
    }

    public static void main(String[] args) {
        final A a = new A();
        final B b = new B();

        for (int i=0;i<100;i++){
            final String resource1 = "线程-"+i;

            final String resource2 = "value-"+i;

            new Thread(){
                @Override
                public void run() {
                    try {
                        a.setOne(resource1);
                        a.setTwo(resource2);
                        b.display();
                    }finally {
                        ResourceClass.RESOUCE_1.remove();
                        ResourceClass.RESOUCE_2.remove();
                    }
                }
            }.start();
        }
    }
}
