package socketwebserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketWebServer {
   public static void main(String[] args) {
      
      try {
         ServerSocket ss = new ServerSocket(8080);
         System.out.println("Server Made");

         while (true) {
            System.out.println("\nStart while loop");
            Socket s = ss.accept();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            PrintWriter output = new PrintWriter(s.getOutputStream(), true);

            String line;
            while (!(line = br.readLine()).equals("")) {
               System.out.println(line);
            }

            System.out.println("End read buffered reader");

            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html");
            output.println("Server: Bot");
            output.println("");
            //output.println("<H1>Hi World</H1>");
            
            String str;
            FileReader f = new FileReader("src/socketwebserver/test.html");
            BufferedReader b = new BufferedReader(f);
            /*while((str = b.readLine()) != null){
                System.out.println(str);
             }*/
            while((str=b.readLine()) != null){
                output.println(str);
            }
            
            System.out.println("Done printing output");
            output.flush();
            s.close();
         }
      } catch (IOException ex) {
         Logger.getLogger(SocketWebServer.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}
