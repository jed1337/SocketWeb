package socketwebbrowser;
//<editor-fold defaultstate="collapsed" desc="OLD CODE">
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class SocketWebBrowser {
//
//   public SocketWebBrowser(){
//      try {
////         String host = "www.dlsu.edu.ph";
//         String host = "localhost";
//         Socket socket = new Socket(host, 8080);
//         BufferedReader in = new BufferedReader(new InputStreamReader(
//                 socket.getInputStream()));
//         PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
//
//         System.out.println("Entering while");
//         String message =
//            "GET / HTTP/1.1\n"+
//            "Host: "+ host+"\n"+
//            "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0\n"+
//            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n"+
//            "Accept-Language: en-US,en;q=0.5\n"+
//            "Accept-Encoding: gzip, deflate\n"+
//            "Connection: keep-alive\n\n";
//         System.out.println(message);
//         output.println(message);
//
//         System.out.println("Send output done");
//         while (true) {
//            String line = in.readLine();
//            if(line!=null){
//               System.out.println(line);
//            }
//         }
//      } catch (IOException ex) {
//         Logger.getLogger(SocketWebBrowser.class.getName()).log(Level.SEVERE, null, ex);
//      }
//
//   }
//
//   public static void main(String[] args) {
//      SocketWebBrowser sg = new SocketWebBrowser();
////      sg.
//   }
//}
//</editor-fold>

import java.io.*;
import java.net.*;

class SocketWebBrowser extends Thread{
    private int number;
    private Socket socket;

    public SocketWebBrowser(int number) {
        System.out.println("Number: "+number);
        this.number = number;
        
        try {
            socket = new Socket("localhost", 8080);
            System.out.println(socket);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run(){
        int i;
      
        try {
            DataInputStream din = new DataInputStream(socket.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            //long start = System.currentTimeMillis();
            while ((line = br.readLine()) != null) {
                
            }
            //long end = System.currentTimeMillis() - start;
            //System.out.println("Total Runtime: "+end+"\n ");
            System.out.println("File received, number "+number);            
        } catch (IOException ex) {
            System.out.println("File :: " + ex);
        }
   }

    public static void main(String ar[]) {
        int i = 0;

        while(true){
            new SocketWebBrowser(++i).start();
        }
    }
}
