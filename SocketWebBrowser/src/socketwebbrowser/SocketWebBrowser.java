package socketwebbrowser;
//
//import java.io.BufferedInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.Socket;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class SocketWebBrowser extends Thread {
//   private final int number;
//   private final Socket socket;
//   private static final int THREAD_COUNT = 8;
//
//   public SocketWebBrowser(int number) throws IOException {
//      System.out.println("Number: " + number);
//      this.number = number;
//
//      socket = new Socket("localhost", 8080);
//      System.out.println(socket);
//   }
//
//   @Override
//   public void run() {
//      try {
//         BufferedInputStream b = new BufferedInputStream(socket.getInputStream());
//         OutputStream out      = socket.getOutputStream();
//         
//         int count;
//         byte buffer[] = new byte[1024];
//         
//         int i = 0;
//
//         while ((count = b.read(buffer)) != -1) {
//            out.write(buffer, 0, count);
//            System.out.println("i = " + (i++));
//         }
//
//         System.out.println("File received, number " + number);
//      } catch (IOException ex) {
//         System.err.println("ERROR File: " + ex + " Number: " + number);
//      } finally {
//         try {
//            socket.close();
//         } catch (IOException ex) {
//            System.err.println("Error closing socket :" + number);
//            Logger.getLogger(SocketWebBrowser.class.getName()).log(Level.SEVERE, null, ex);
//         }
//      }
//   }
//
//   public static void main(String ar[]) throws IOException {
//      int i = 0;
////        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
////        while(true){
////            executor.submit(new SocketWebBrowser(++i));
////        }
//      new Thread(new SocketWebBrowser(++i)).start();
//   }
//}


import java.io.*;
import java.net.*;

public class SocketWebBrowser {
   public static void main(String[] args) {
// declaration section:
// SocketWebBrowser: our client socket
// os: output stream
// is: input stream
      Socket smtpSocket = null;
      DataOutputStream os = null;
      DataInputStream is = null;
// Initialization section:
// Try to open a socket on port 25
// Try to open input and output streams
      try {
         smtpSocket = new Socket("localhost", 2000);
         System.out.println("Client received");
         os = new DataOutputStream(smtpSocket.getOutputStream());
         is = new DataInputStream(smtpSocket.getInputStream());
      } catch (UnknownHostException e) {
         System.err.println("Don't know about host: hostname");
      } catch (IOException e) {
         System.err.println("Couldn't get I/O for the connection to: hostname");
      }
// If everything has been initialized then we want to write some data
// to the socket we have opened a connection to on port 25
      if (smtpSocket != null && os != null && is != null) {
         try {
// The capital string before each colon has a special meaning to SMTP
// you may want to read the SMTP specification, RFC1822/3
            os.writeBytes("HELO\n");
            os.writeBytes("MAIL From: k3is@fundy.csd.unbsj.ca\n");
            os.writeBytes("RCPT To: k3is@fundy.csd.unbsj.ca\n");
            os.writeBytes("DATA\n");
            os.writeBytes("From: k3is@fundy.csd.unbsj.ca\n");
            os.writeBytes("Subject: testing\n");
            os.writeBytes("Hi there\n"); // message body
            os.writeBytes("\n.\n");
            os.writeBytes("QUIT");
// keep on reading from/to the socket till we receive the "Ok" from SMTP,
// once we received that then we want to break.
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
               System.out.println("Server: " + responseLine);
               if (responseLine.indexOf("Ok") != -1) {
                  break;
               }
            }
// clean up:
// close the output stream
// close the input stream
// close the socket
            os.close();
            is.close();
            smtpSocket.close();
         } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
         } catch (IOException e) {
            System.err.println("IOException:  " + e);
         }
      }
   }
}
