import java.util.*;
import java.io.*;
import java.net.*;

public class DNS
{
     public static void main(String[]args)
     {
        int n;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));      
        do
        {
             System.out.println("\n Menu 1.DNS, 2.Reverse DNS, 3.Exit");
             System.out.println("Enter your choice");
             n = Integer.parseInt(System.console().readLine());
             try
             {
                if(n == 1)
                {
                    System.out.println("Enter the Hostname");
                    String hname = in.readLine();
                    InetAddress address;
                    address = InetAddress.getByName(hname);
                    System.out.println("Hostname" +address.getHostName());
                    System.out.println("IP" +address.getHostAddress());
                }
                if(n == 2)
                {
                     System.out.println("Enter Ip Address");
                     String ipstr = in.readLine();
                     InetAddress ia = InetAddress.getByName(ipstr);
                     System.out.println("IP" +ipstr);
                     System.out.println("HostName" +ia.getHostName());

                }
             }
             catch(IOException ioe)
             { 
                 ioe.printStackTrace();
             }          
        }
        while(n != 3);
     }
}
