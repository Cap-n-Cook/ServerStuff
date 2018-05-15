import java.net.*;
import java.io.*;


public class ServerTester {

    // Init stuff cause java.
    private Socket socket = null;
    // Not sure what this is. look into this.
    private ServerSocket server = null;
    private DataInputStream clientInput = null;

    public ServerTester(int port){

        String dataReceived = "";

        try {
            // Starts server.
            server = new ServerSocket(port);
            System.out.println("Server started.");
            System.out.println("Waiting on client...");

            // Program waits here until a client connects.
            socket = server.accept();
            System.out.println("Client connected.");

            clientInput = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));

            // Get data while "over" hasn't been received.
            while(!dataReceived.equals("Over")){

                try{
                    dataReceived = clientInput.readUTF();
                    System.out.println(dataReceived);
                }
                catch(IOException err){
                    System.out.println("server side error: " + err);
                }

                System.out.println(dataReceived);
            }

            System.out.println("Closing connection");
            
            // Close
            socket.close();
            clientInput.close();
        }
        catch(IOException err){
            System.out.println(err);
        }
    }

    public static void main(String[] args) {
        // Create server.
        ServerTester server = new ServerTester(5000);
    }
}