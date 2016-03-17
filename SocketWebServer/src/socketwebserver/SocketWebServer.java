package socketwebserver;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.util.Arrays;
import static java.lang.System.in;

public class SocketWebServer {
   public static void main(String[] args) throws IOException {
      System.out.println("The chat server is running.");
      
      String fileName = "src/Assets/05_mb_file.txt";
//      String fileName = "src/Assets/HighMufasa.png";
//      String fileName = "src\\Assets\\HighMufasa.png";

      try (ServerSocket ss = new ServerSocket(8080)) {
         while (true) {
            new Handler(ss.accept(), fileName).start();
         }
      }
   }

   private static class Handler extends Thread {
      private final Socket client;
      private final String fileName;

      public Handler(Socket socket, String fileName) {
         this.client = socket;
         this.fileName = fileName;

         System.out.println("Client accepted");
      }

      @Override
      public void run() {
         try {
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            File file       = new File(fileName);
            //printHeaders(out, file);
            
            String str;
            FileReader f = new FileReader(file);
            BufferedReader b = new BufferedReader(f);
            /*while((str = b.readLine()) != null){
                System.out.println(str);
             }*/
            while((str=b.readLine()) != null){
                out.println(str);
            }
            
            /*BufferedImage bufferedImage = ImageIO.read(file);
            boolean success             = ImageIO.write(bufferedImage, "png", client.getOutputStream());
            System.out.println("Success: " + success); //true*/
            System.out.println("Done printing output");

            out.flush();

            close(out);
            close(in);
            close(client);
         } catch (IOException ex) {
            printError(ex);
         }
      }

      private void printHeaders(PrintWriter out, File file) throws IOException {
         out.println("HTTP/1.1 200 OK");
         out.println("Content-Type: image/png");
         out.println("SocketWebServer: Bot");
         out.println("");

         ImageInputStream imageInputStream = ImageIO.createImageInputStream(file);
         long size = imageInputStream.length();
         out.write("Content-Length: " + size + "\r\n");
         out.write("\r\n");
      }
   }

   private static void close(Closeable c) {
      if (c == null) {
         return;
      }
      try {
         c.close();
      } catch (IOException ex) {
         printError(ex);
      }
   }

   private static void printError(IOException ex) {
      System.err.println("ERROR" + ex.getMessage());
      System.err.println(Arrays.toString(ex.getStackTrace()));
   }
}
