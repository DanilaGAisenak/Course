package server;

import java.sql.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static int countClients = 0;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;

    public static void main(String[] args) {
        String everything="";
        try(BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] val = everything.split(" ");
        String con = val[0];
        //String con = "jdbc:mysql://localhost:3306/KP";
        try (Connection connection = DriverManager.getConnection(con, "root", "Admin2001");) {
            System.out.println("Connected");

            ServerSocket sock = null;
            DataInputStream is = null;
            DataOutputStream os = null;
            try {
                sock = new ServerSocket(1024);
                while (true) {
                    Socket client = sock.accept();
                    countClients++;
                    System.out.println("==============================");
                    System.out.println("Client " + countClients + " connected");

                    //is = new DataInputStream( client.getInputStream());
                    //os = new DataOutputStream( client.getOutputStream());
                    ois = new ObjectInputStream(client.getInputStream());
                    oos = new ObjectOutputStream(client.getOutputStream());

                    ClientHandler clientSock = new ClientHandler(client, connection, oos, ois);
                    new Thread(clientSock).start();
                }
            } catch (IOException e) {
                System.out.println("Disconnected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("Disconnected");
        }
    }
}
