package chapter4;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: jet.xie
 * @Date: 2018/7/16
 * @Time: 20:15
 * @Description:
 * @version: 1.0.0
 */
public class SimpleHttpServer {
    static String basePath;

    static class HttpRequestHandler implements Runnable {

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;

            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    out.println("http/1.1 200 ok");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    socket.getOutputStream().write(array, 0, array.length);
                } else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("http/1.1 200 ok");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset =UTF-8");
                    out.println("");
                    while ((line = br.readLine()) != null) {
                        out.println(line);
                    }
                }
                out.flush();

            } catch (IOException e) {
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                close(br, in, reader, out, socket);
            }
        }

        private static void close(Closeable... closeables) {
            if (closeables != null) {
                for (Closeable closeable : closeables
                        ) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}