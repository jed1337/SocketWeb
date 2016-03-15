package socketwebserver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class SocketWebServer {
   public static void main(String[] args) {
      
      try {
         ServerSocket ss = new ServerSocket(8080);
         System.out.println("SocketWebServer Made");

         while (true) {
            System.out.println("\nStart while loop");
            Socket client = ss.accept();
            System.out.println("End read buffered reader");

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: image/jpg");
            out.println("SocketWebServer: Bot");
            out.println("");

            int i;
            
//            String fileName = "C:\\Users\\Sammy Caychingco\\Documents\\NetBeansProjects\\SocketWeb\\SocketWebServer\\src\\Assets\\HighMufasa.png";
            String fileName = "C:\\Users\\Sammy Caychingco\\Desktop\\Reaction Photos\\Whoa.JPG";

            File file = new File(fileName);

            ImageInputStream imageInputStream = ImageIO.createImageInputStream(file);
            long size = imageInputStream.length();
            out.write("Content-Length: " + size + "\r\n");
            out.write("\r\n");

            BufferedImage bufferedImage = ImageIO.read(file);
            boolean success = ImageIO.write(bufferedImage, "jpeg", client.getOutputStream());
            System.out.println("Success: " + success); //true
            out.close();
            in.close();
            client.close();
            
            System.out.println("Done printing output");
            out.flush();
            client.close();
         }
      } catch (IOException ex) {
         System.out.println("ERROR");
         Logger.getLogger(SocketWebServer.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}
