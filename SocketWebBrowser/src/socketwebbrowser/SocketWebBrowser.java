package socketwebbrowser;
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

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

class SocketWebBrowser implements ActionListener {

   Socket socket;

//*******************************Client1 GUI*********************************//
   TextField tf;
   TextArea ta;
   Label lb;
   Button b;

   public SocketWebBrowser() {

      Frame f = new Frame("Client");
      f.setLayout(new FlowLayout());
      f.setBackground(Color.orange);
      tf = new TextField(15);
      ta = new TextArea(12, 20);
      ta.setBackground(Color.white);
      lb = new Label("Enter File Name To Be Send");
      b = new Button("Send");
      f.add(lb);
      f.add(tf);
      f.add(b);
      f.add(ta);
      ta.setBounds(200, 200, 10, 10);
      f.addWindowListener(new W1());
      b.addActionListener(this);
      f.setSize(300, 400);
      f.setLocation(300, 300);
      f.setVisible(true);
      f.validate();

//*********************************GUI END*******************************//   
//********************************Creating Connection*********************//   
      try {
         socket = new Socket("localhost", 8080);
         System.out.println(socket);
      } catch (Exception e) {
         System.out.println(e);
      }

   }

   private class W1 extends WindowAdapter {
      public void windowClosing(WindowEvent we) {
         System.exit(0);
      }
   }

//********************************************************************//
   public void actionPerformed(ActionEvent ae) {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      String fileName = "C:\\Users\\Sammy Caychingco\\Documents\\NetBeansProjects\\SocketWeb\\SocketWebServer\\src\\Assets\\Untitled.png";
      System.out.println(fileName);

//*******************Coading for image transfer**********************//       
      int i;
      
      try {
         DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

         File file = new File(fileName);
         FileInputStream fin = new FileInputStream(file);
         
         dout.writeUTF(fileName);
         System.out.println("Sending image...");
         byte[] readData = new byte[1024];

         while ((i = fin.read(readData)) != -1) {
            dout.write(readData, 0, i);
         }
         System.out.println("Image sent");
         ta.appendText("\nImage Has Been Sent");
         fin.close();
      } catch (IOException ex) {
         System.out.println("Image ::" + ex);
      }

//*****************************Text File********************************//
   }

   public static void main(String ar[]) {
      SocketWebBrowser object = new SocketWebBrowser();
   }
}
