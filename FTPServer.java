import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FTPServer
{
     @SuppressWarnings("deprecation")
    public static void main(String[]args)
     {
        try
        {
             while(true)
             {
                 ServerSocket ss = new ServerSocket(1010);
                 Socket sl = ss.accept();
                 System.out.println("Server Socket is created");
                 System.out.println("Test1");
                 DataInputStream fromserver = new DataInputStream(sl.getInputStream());
                 System.out.println("Test2");
                String option = fromserver.readLine();
                /*---------------------------------------------------------------------------------------- */
                 if(option.equalsIgnoreCase("Upload"))
                 {
                     System.out.println("UploadTest");
                    String filefromclient = fromserver.readLine();
                    File clientfile = new File(filefromclient);
                    FileOutputStream fout = new FileOutputStream(clientfile);
                    int ch;
                    while((ch=fromserver.read())!= -1)
                    {
                         fout.write((char)ch);
                    }
                    fout.close(); ss.close();
                 /*------------------------------------------------------------------------------------------ */   
                 }
                 if(option.equalsIgnoreCase("Download"))
                 {
                     System.out.println("DownloadTest");
                     String filefromclient = fromserver.readLine();
                     File clientFile = new File(filefromclient);
                     FileInputStream fis = new FileInputStream(clientFile);
                     PrintStream out = new PrintStream(sl.getOutputStream());
                     int n = fis.read();
                     while(n != 1)
                     {
                         out.print((char)n);
                         n=fis.read();
                     }
                     fis.close();
                     out.close();
                     ss.close();
                 }
             }
        }
        catch(Exception e)
        {
             System.out.println(e);
        }
     }
}