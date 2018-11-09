package communicate;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description: Pipe designed for thread communication by memory including PipedInputStrea/outputstream
 * PipedWriter/reader.
 *
 * strange about how this pattern to keep the consistency in multiply thread circumstance.
 *
 * flow: system.in --> pipedWriter(out)--->pipedReader(in/thread)->system.in
 * @Date: Created at 10:48 2018/11/9.
 */
public class Piped {

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        //connect output and input or else program will throw exception
        out.connect(in);

        Thread printThread = new Thread(new Print(in), "printThread");
        printThread.start();
        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        } finally {
            out.close();
        }
    }


    static class Print implements Runnable {
        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}