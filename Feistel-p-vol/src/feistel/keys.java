package feistel;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.UUID;
class keys
{  
   
   static int generateRandomDigits() throws IOException
   {
    //Random r=new Random();
    FileOutputStream fos=new FileOutputStream("ke.txt");
    DataOutputStream dos=new DataOutputStream(fos);
    FileInputStream fis=new FileInputStream("ke.txt");
    DataInputStream dis=new DataInputStream(fis);
    for(int i=0;i<10;i++)
    {
     int m = (int) Math.pow(10,1);
     int x = m + new Random().nextInt(9 * m);
     //long x=Math.abs(UUID.randomUUID().getMostSignificantBits());
     dos.writeInt(x);
     int y=dis.readInt();
     System.out.println(y);
    }
    dis.close();    
    dos.close();
    return 0;
   }
   public static void main(String args[]) throws IOException
   {
    generateRandomDigits();
   }
}

