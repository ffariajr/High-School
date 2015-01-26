package musicTransfer_diff;
 
import java.io.*;
import java.net.*;
import java.util.Scanner;
 
public class ClientDownload
{
      public static void main(String[] args)
      {
           
            System.out.println("Input file path");
            System.err.println("MUST be perfect!!!");
            Scanner myscanner = new Scanner(System.in);
            String path = myscanner.next();
           
            ClientDownload me = new ClientDownload(path);
            me.begin();
      }
     
      public ClientDownload(String path)
      {
            this.path=path;
      }
     
      private String path;
     
      public void begin()
      {
            Socket s = null;
            try
            {
                  s = new Socket("10.0.1.3", 2812);
            }
            catch (UnknownHostException e)
            {
                  System.err.println("host is bad");
                  System.exit(0);
            }
            catch (IOException e)
            {
          	  e.printStackTrace();
          	  System.err.println("IOException");
          	  System.exit(0);
            }
           //       /Users/Shared/Songs/
            try
            {
                  InputStream in = s.getInputStream();
                  OutputStream out = s.getOutputStream();
                  PrintWriter pr = new PrintWriter(out);
                  
                  System.out.println("beginning");
                  
                  pr.print("size");
                  pr.flush();
                  int size = in.read();
                  
                  System.out.println("amount of files: "+size);
                 
                  FileOutputStream write = null; 
                 
                  for(int x = 0; x< size;x++)
                  {
                        while(true)
                        {
                              write = new FileOutputStream(path+"imported_file_"+x);
                       
                              int num = in.read();
                       
                              if(num == -1)
                                    break;
                       
                              write.write(num);
                        }
                        int xplus = x+1;
                        System.out.println(""+xplus+" files downloaded");
                  }
            }
            catch (IOException e)
            {
          	  System.out.println("Check shit");
            }
      }
}