package sdf.assessment.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.Collectors;

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
                    //System.out.println("Server response: " + serverResponse);
                    parseLine(serverResponse);
                    }
                    
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to parse server's response.
    //Servers response to be added into a list.
    //Loop and end it at prod_end.
    //Loop and add into a new list at prod_start

    private static void parseLine(String response) {
        String[] parts = response.split(":", 2); 
        if (parts.length <2 ) {
            //encounter prod_end or prod_start
        }
        else {
            String directive = parts[0];
            String description = parts[1];

            System.out.println(description);
            //this will print out relevant information needed.
            
            //Set int budget = parts[1] when parts[0] starts with budget
            //Set int items = parts[1] when parts[0] starts with item_count
            /* 
            if (parts[0].startsWith("budget")) {
                int budget = Integer.valueOf(parts[1]);
            } else if (parts[0].startsWith("item_count")) {
                int noOfItems = Integer.valueOf(parts[1]);
        
            }
        
            as we are using list, use collectors to later sort products in descending order.
            public static void sortIntegersDecrease(ArrayList<Integer> list) {
                Collections.sort(list, Collections.reverseOrder());
                }
*/
        }
    }
}
