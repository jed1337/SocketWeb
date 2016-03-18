package socketwebbrowser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

class SocketWebBrowser extends Thread {
   private int number;
   private Socket socket;
   private static final int THREAD_COUNT = 8;

   public SocketWebBrowser(int number) throws IOException {
      System.out.println("Number: " + number);
      this.number = number;

      socket = new Socket("localhost", 8080);
      System.out.println(socket);
   }

   @Override
   public void run() {
      int i;

      try {
          BufferedInputStream b = new BufferedInputStream(socket.getInputStream());
          OutputStream out = socket.getOutputStream();
          
          int count;
            byte buffer[] = new byte[1024];

          while((count = b.read(buffer))!=-1){
              out.write(buffer, 0, count);
          }
          
         System.out.println("File received, number "+number);            
        } catch (IOException ex) {
           System.err.println("ERROR File: " + ex + " Number: "+number);
        } finally{
          try {
              socket.close();
          } catch (IOException ex) {
              System.err.println("Error closing socket :"+number);
              Logger.getLogger(SocketWebBrowser.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
   }

    public static void main(String ar[]) throws IOException {
        int i = 0;
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        while(true){
            executor.submit(new SocketWebBrowser(++i));
        }
            
    }
}
