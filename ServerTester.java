import java.net.*;
import java.io.*;

public class ServerTester{

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream inputFromCLient = null;
    
    // Going to write data to a file.
    private PrintWriter writer = null;

    // Stores the data we will write to the file.
    private String dataFromClient;

    // Creates socket connection.
    public ServerTester(int port, String fileToWriteTo){

        // Socket Methods throw exceptions.
        // Try to establish a connection.
        try{
            server = new ServerSocket(port);
            System.out.println("Waiting on client...");

            // When a connection is accepted, server continues.
            socket = server.accept();
            System.out.println("Connection established.");

            // Take input from the client via the socket's input
            // stream.
            inputFromCLient = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));

            // Read data.
            getDataFromClient();
            // Close connection.
            closeConnection();

            
        }
        catch(IOException err){
            System.out.println(err);
        }
    }

    public static void main(String[] args) {
        ServerTester server = new ServerTester(5000, "poop");
    }

    private void getDataFromClient(){
        try{
            dataFromClient = inputFromCLient.readUTF();
            System.out.println(dataFromClient);
        }
        catch(IOException err){
            System.out.println(err);
        }
    }
    
    private void closeConnection(){
        
        try{
            socket.close();
            inputFromCLient.close();
        }
        catch(IOException err){
            System.out.println(err);
        }

        System.out.println("Connection terminated.");
    }

}