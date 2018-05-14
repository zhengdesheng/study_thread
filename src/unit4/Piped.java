package unit4;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Author: zhengdesheng
 * @Date: 17/5/10 09:50
 * @Description:
 */
public class Piped {

    static class Print implements Runnable{
        private PipedReader in;

        public  Print(PipedReader in) {
             this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                String s="";
                while ((receive=in.read()) !=-1){
                   s += (char)receive;
                    System.out.print((char)receive);
                }
                if(s.equals("0")){
                    System.exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);
        Thread print = new Thread(new Print(in),"PrintThread");
        print.start();
        int receive=0;
        try {
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        }finally {
            out.close();
        }
    }
}
