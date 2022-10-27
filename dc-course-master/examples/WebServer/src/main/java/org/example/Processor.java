package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Processor of HTTP request.
 */
public class Processor {
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws Exception {
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        PrintWriter output = new PrintWriter(socket.getOutputStream());

        String word = request.getRequestLine();
        String word2 = word.substring(5, 11);
        String word3 = word.substring(5, 9);
        if (word.equals("GET / HTTP/1.1")) {
            //Thread.sleep(5000);
            Processor proc = new Processor(socket, request);
            proc.mainPage();
        }

        if (word2.equals("create")) {
            Processor proc = new Processor(socket, request);
            proc.create();
        }


        if (word3.equals("exec")) {
            compute();
        }
        //}
        //inputStream.close();
        output.println("<body><p></p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
    }
    public void mainPage() throws Exception {
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        PrintWriter output = new PrintWriter(socket.getOutputStream());
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Distributing Computing</title></head>");
        output.println("<body><p>Made by Ulan Rassuly <br> Group - IT 2004</p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
    }
    public void create() throws IOException {
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<body><p>This is creating page</p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
    }



    public void compute() throws IOException {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        int n0 = 1;
        int n1 = 1;
        int n2;

        for(int i = 3; i <= 50; i++){
            n2=n0+n1;
            System.out.println(n2+" ");
            n0=n1;
            n1=n2;
        }

        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Distributing Computing</title></head>");


        output.println("<body><p>Computed fibonacci numbers (50)</p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
    }




}
