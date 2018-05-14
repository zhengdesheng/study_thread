/**
 * @Author: zhengdesheng
 * @Date: 17/4/27 16:59
 * @Description:
 */
public class unit4 {

    public static void main(String[] args) {
        try {
            test1();
        } catch (Exception e) {
            System.out.println("我是main");
        }
    }

    private static void test1() throws Exception{
        try{
            int j= 2/1;
        }catch (Exception e){
            System.out.println("我是最外层的");
        }
            try {
                int i= 2/0;
            }catch (Exception e){
                System.out.println("我是最内层的");
                throw  e;

            }

    }
}
