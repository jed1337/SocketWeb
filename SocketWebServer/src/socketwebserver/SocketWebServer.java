package socketwebserver;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.util.Arrays;
import static java.lang.System.in;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketWebServer {
//      String fileName = "src/Assets/HighMufasa.png";
//      String fileName = "src\\Assets\\HighMufasa.png";
    
    private static final String FILE_NAME = "src/Assets/05_mb_file.txt";
    private static final int THREAD_COUNT = 8;
    
   public static void main(String[] args) throws IOException {
      System.out.println("The chat server is running.");      

      try (ServerSocket ss = new ServerSocket(8080)) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
         while (true) {
            executor.submit(new Handler(ss.accept(), FILE_NAME));
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
            
            OutputStream out = client.getOutputStream();
            FileInputStream file = new FileInputStream(fileName);
            //printHeaders(out);
            byte buffer[] = new byte[16384];
            int LENGTH = buffer.length;
            int count;
            
            while ((count = file.read(buffer)) != -1){
                out.write(buffer);
            }
            
            out.flush();
            System.out.println("Done!");
            close(out);
            close(in);
            close(client);
         } catch (IOException ex) {
            printError(ex);
         }
      }

      private void printHeaders(OutputStream out) throws IOException {
         out.write("HTTP/1.1 200 OK\r\n".getBytes());
         out.write("Content-Type: text/html\r\n".getBytes());
         out.write("SocketWebServer: Bot\r\n".getBytes());
         out.write("\r\n".getBytes());

         /*ImageInputStream imageInputStream = ImageIO.createImageInputStream(file);
         long size = imageInputStream.length();
         out.write("Content-Length: " + size + "\r\n");
         out.write("\r\n");*/
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
