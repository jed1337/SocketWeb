package socketwebserver;
//
//import java.io.Closeable;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.Arrays;
//import static java.lang.System.in;
//
//public class SocketWebServer {
//    private static final String FILE_NAME = "src/Assets/05_mb_file.txt";
//    private static final int THREAD_COUNT = 8;
//    
//   public static void main(String[] args) throws IOException {
//      System.out.println("The chat server is running.");
//      
//
//      try (ServerSocket ss = new ServerSocket(8080)) {
//         new Thread(new Handler(ss.accept(), FILE_NAME)).start();
//         
////        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
////         while (true) {
////            executor.submit(new Handler(ss.accept(), FILE_NAME));
////         }
//      }
//   }
//
//   private static class Handler extends Thread {
//      private final Socket client;
//      private final String fileName;
//
//      public Handler(Socket socket, String fileName) {
//         this.client   = socket;
//         this.fileName = fileName;
//
//         System.out.println("Client accepted");
//      }
//
//      @Override
//      public void run() {
//         try {
//            
//            OutputStream out     = client.getOutputStream();
//            FileInputStream file = new FileInputStream(fileName);
//            printHeaders(out);
//            
//            byte buffer[] = new byte[1024];
//            int LENGTH    = buffer.length;
//            int count;
//            
//            while ((count = file.read(buffer)) != -1){
//                out.write(buffer);
//            }
//            
//            out.flush();
//
//            safeClose(out);
//            safeClose(in);
//            safeClose(client);
//         } catch (IOException ex) {
//            printError(ex);
//         }
//      }
//
//      private void printHeaders(OutputStream out) throws IOException {
//         out.write("HTTP/1.1 200 OK\r\n".getBytes());
//         out.write("Content-Type: text/html\r\n".getBytes());
//         out.write("SocketWebServer: Bot\r\n".getBytes());
//         out.write("\r\n".getBytes());
//      }
//   }
//
//   private static void safeClose(Closeable c) {
//      if (c == null) {
//         return;
//      }
//      try {
//         c.close();
//      } catch (IOException ex) {
//         printError(ex);
//      }
//   }
//
//   private static void printError(IOException ex) {
//      System.err.println("ERROR" + ex.getMessage());
//      System.err.println(Arrays.toString(ex.getStackTrace()));
//   }
//}
//

import java.io.*;
import java.net.*;

public class SocketWebServer {
   public static void main(String args[]) {
// declaration section:
// declare a server socket and a client socket for the server
// declare an input and an output stream
      ServerSocket echoServer = null;
      String line;
      DataInputStream is;
      PrintStream os;
      Socket clientSocket = null;
// Try to open a server socket on port 9999
// Note that we can't choose a port less than 1023 if we are not
// privileged users (root)
      try {
         echoServer = new ServerSocket(2000);
      } catch (IOException e) {
         System.out.println(e);
      }
      System.out.println("Server up");
// Create a socket object from the ServerSocket to listen and accept 
// connections.
// Open input and output streams
      try {
         clientSocket = echoServer.accept();
         is = new DataInputStream(clientSocket.getInputStream());
         os = new PrintStream(clientSocket.getOutputStream());
// As long as we receive data, echo that data back to the client.
         while (true) {
            line = is.readLine();
            System.out.println("line = " + line);
            os.println(line);
         }
      } catch (IOException e) {
         System.out.println(e);
      }
   }
}
