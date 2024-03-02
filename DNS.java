import java.net.*;
import java.util.*;
import java.io.*;

  public class DNS 
  {
    public static void main(String[]args)
    {
      int n;
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         do
         {
            System.out.println("\nMenu\n1.DNS 2.Reverse DNS 3.Exit");
            System.out.println("Enter your choice");
            n = Integer.parseInt(System.console().readLine());
            
            if(n==1)
            {
               try
               {
                  System.out.println("Enter hostname :");
                  String hname = in.readLine();
                  InetAddress address;
                  address = InetAddress.getByName(hname);
                  System.out.println("Host Name: " +address.getHostName());
                  System.out.println("Ip: " +address.getHostAddress());
               }
               catch(IOException ioe)
               {
                  ioe.printStackTrace();
               }

            }
            if(n==2)
            {
               try
               {
                   System.out.println("Enter Ip :");
                   String ipstr = in.readLine();
                   InetAddress ia = InetAddress.getByName(ipstr);
                   System.out.println("Ip "+ipstr);
                   System.out.println("Host Name" +ia.getHostName());
               }
               catch(IOException ioe)
               {
                  ioe.printStackTrace();
               }
            }
         }
         while(!(n==3));
    }
 }
  