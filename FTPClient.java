import javax.swing.*;    //Makes the graphical things like buttons, windows, menu
import java.awt.*;       //Bit Older and basic version than swing
import java.awt.event.*; //Handles all the Buttons pressed or Armed
import java.net.*;       //Allows you to perform network task
import java.io.*;

class One extends JFrame implements ActionListener
 {
   public JButton b,b1; //Creating the buttons
   public JLabel l,l1,lmsg1,lmsg2; // Creating labels for the buttons

   One()
   {
    b = new JButton("Upload"); // assigning the button 'b'
    l = new JLabel("Upload a file"); //assigning the label to the button 'b'
    lmsg1 = new JLabel(" "); //assigning another label for future use 
    b1 = new JButton("Download"); //assigning the button 'b1'
    l1 = new JLabel("Download a file");//assging the label to the button 'b1'
    lmsg2 = new JLabel(" ");//assigning another label for future use 


    setLayout(new GridLayout(2,3,10,10)); //setting the grid layout 
    add(l);add(b);add(lmsg1);add(l1);add(b1);add(lmsg2); //adding the buttons and labels in a specific order onto to the layout
    b.addActionListener(this); //  'b' is repsonsible if the upload button is pressed
    b1.addActionListener(this); // 'b1' is reponsible if the download button is pressed
    setVisible(true); //setting the Visibility to True
    setSize(600,500); //setting the size

   }
   public void actionPerformed(ActionEvent e) //Action Event waits for the user to see what button is pressed then "Action Performed"
    {
         try
         {
             if(b.getModel().isArmed()) // if the 'b' is pressed then perfrom this action
             {
                 Socket s = new Socket("localhost",1010);  //Creating a new socket
                 System.out.println("Client is Connected to the Server"); 

                 JFileChooser j = new JFileChooser(); //Opening a Dialog box for the user to choose a file
                 int val;
                 val =j.showOpenDialog(One.this); // Displaying the Dialog box and waiting for the user input
                 String filename = j.getSelectedFile().getName(); //Getting the File name for the selected file
                 String path = j.getSelectedFile().getPath(); //Getting the path for the file selected file
                
                
                
                 /* The Important part of the code for uploading starts from here */
                 PrintStream out = new PrintStream(s.getOutputStream()); //Sending Upload command and file information to the server
                 out.println("upload");
                 out.println(filename);
                 FileInputStream fis = new FileInputStream(path); // Reading the contents of the file and sending them to the server
                 int n =fis.read();
                 while(n != -1)
                 {
                     out.print((char)n); //sending each byte of the file content and if reaches -1 then geting out from the loop
                     n = fis.read();
                 }
                 /* End of the important part */


                 fis.close(); out.close(); s.close(); //Closing the file input stream reader and the PrintStream
                 lmsg1.setText(filename +"   is Uploaded");
                 repaint();
             }
             
             if(b1.getModel().isArmed())
             {
                 Socket s = new Socket("localhost",1010);
                 System.out.println("Client is Connected to the server");
                 String remoteadd = s.getRemoteSocketAddress().toString();
                 System.out.println(remoteadd);
                 JFileChooser j1 = new JFileChooser(remoteadd);
                 int val = j1.showOpenDialog(One.this);
                 String filename =j1.getSelectedFile().getName();
                 String filepath = j1.getSelectedFile().getPath();
                 System.out.println("Filename : " +filename);
                 PrintStream out = new PrintStream(s.getOutputStream());
                 out.println("Download");
                 out.println(filepath);
                 /*2nd Important Part of the Code */

                 FileOutputStream fout = new FileOutputStream(filename);
                 DataInputStream fromserver = new DataInputStream(s.getInputStream());
                 int ch;
                 while((ch=fromserver.read())!= -1)
                 {
                     fout.write((char)ch);
                 }
                 fout.close(); s.close();
                 lmsg2.setText(filename + "   is Downloaded");
                 repaint();
             }
         }
         catch(Exception ee)
         {
             System.out.println(ee);
         }
    }
 }
    public class FTPClient
 {
     public static void main(String[] args) {
        new One();
     }
 }

 