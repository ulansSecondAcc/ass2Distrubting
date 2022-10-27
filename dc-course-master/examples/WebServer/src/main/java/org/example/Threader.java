package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Threader extends Thread {
    private int id;
    private Socket socket;
    public ThreaderQueue<BufferedReader> queue;

    public Threader(int id, Socket socket, ThreaderQueue<BufferedReader> queue) {
        this.id = id;
        this.socket = socket;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            // Get request
            while (true) {
                BufferedReader input = queue.pop_element();
                if(input == null) return;
                HttpRequest request = HttpRequest.parse(input);
                // Process request
                long startTime = System.nanoTime();
                Processor proc = new Processor(socket, request);
                proc.process();
                long endTime = System.nanoTime();
                System.out.println("---------------------------------------");
                System.out.println("Thread " + id + " worked: " + ((float) (endTime - startTime) / 1000000000) + " second with request "+request);
                System.out.println("---------------------------------------");
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



