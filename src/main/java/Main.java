import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static Server server;
    public static ServerSocket serverSocket;
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//           server.newAccept(serverSocket);
//
//        }
//    };
public static Callable<String> myCallable = new Callable<String>() {
    @Override
    public String call() throws Exception {
       return server.newAccept(serverSocket);
    }
};



    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(64);
        
        server = new Server();
        serverSocket = server.returnSocket();

        while (true){

            System.out.println("Запускаем поток");


            Future<String> task = threadPool.submit(myCallable);

            System.out.println("Поток запущен переходим к следующему");
        }
        




//        /*
//        Это работающий код. Оставлен, чтобы помнить, что так можно
//         */
//        Server server = new Server();
//        for (int i = 0; i < 3; i++) {
//            System.out.println(i);
//            Thread thread = new Thread(){
//                @Override
//                public void run() {
//                    synchronized (server){
//                        new Server().startServer();
//                    }
//
//
//                }
//            };
//            thread.start();
//        }


    }
}
/*

 */