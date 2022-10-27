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
                        

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("Server has been shutdown!");
            }
        }






}
