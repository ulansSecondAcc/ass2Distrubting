package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Simple web server.
 */
public class WebServer {
    public static void main(String[] args) {
        // Port number for http request
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 8080;
        // The maximum queue length for incoming connection
        int queueLength = args.length > 2 ? Integer.parseInt(args[2]) : 50;
        int numOfThreads = args.length > 1 ? Integer.parseInt(args[1]) : 4;
        int numOfItems = (args.length > 2 ? Integer.parseInt(args[2]) : 100);
        //

            try (ServerSocket serverSocket = new ServerSocket(port, queueLength)) {
                System.out.println("Web Server is starting up, listening at port " + port + ".");
                System.out.println("You can access http://localhost:" + port + " now.");
                while (true) {
                    // Make the server socket wait for the next client request
                        Socket socket = serverSocket.accept();

                        InputStream inputStream = socket.getInputStream();
                        // To read input from the client
                        BufferedReader input = new BufferedReader(
                                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                        ThreaderQueue<BufferedReader> queue = new ThreaderQueue<>();
                        for(int i = 0; i<numOfThreads; i++) {
                            Threader th = new Threader(i, socket, queue);
                            th.start();
                        }
                        //for(int i =0; i<numOfItems; i++) {
                            queue.add_element(input);
                        //}
                        //for(int i = 0; i<numOfThreads; i++) {
                            queue.add_element(null);
                        //}

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("Server has been shutdown!");
            }
        }






}
