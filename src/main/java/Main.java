import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    private static Server server;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static Callable<String> myCallable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            return server.newAccept(socket);
        }
    };

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(64);
        server = new Server();
        serverSocket = server.returnSocket();
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Запускаем поток");
            Future<String> task = threadPool.submit(myCallable);
            System.out.println("Поток запущен переходим к следующему");
        }
    }
}
