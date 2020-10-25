package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther: yanchengzhe
 * @Date: 2020/10/25 23:14
 * @Description:
 */
public class HttpServerDemo {

    public static void main(String[] args) {
        while (true) {

            try (ServerSocket serverSocket = new ServerSocket(8801)) {
                Thread.sleep(20);
                Socket socket = serverSocket.accept();
                doSomeThing(socket);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doSomeThing(Socket socket) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.write("hello world");

            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
