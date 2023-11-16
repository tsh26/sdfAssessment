package sdf.assessment.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_NAME = "localhost"; 
    private static final int PORT = 3000; // Same as server port

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_NAME, PORT);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                while (true) {
                    //Prompt client for input
                    System.out.print("Enter a message: ");
                    String message = userInput.readLine();

                    // Send input to server
                    out.println(message);

                    // Receive and display the server's response
                    while(true) {
                    String serverResponse = in.readLine();
                    System.out.println("Server response: " + serverResponse);
                    }

                    
                }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
