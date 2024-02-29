package feistel;
import java.util.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class MainClass {
	
	public static void Encryption() throws Exception{
		System.out.println("\t\t*~*~*~*~*~*~*~*~*~*~ Encryption *~*~*~*~*~*~*~*~*~*~\n\n\n");
		   String s="";
		   String s1="";
		  try
		  {
		  FileReader fis = new FileReader("D:\\Feistel-p-vol\\src\\feistel\\inp");
		       
		  BufferedReader bis = new BufferedReader(fis);
		  
		  while ((s1 = bis.readLine())!= null)
		  {
		   s = s+s1+" "; bis.readLine();
		  }

		  bis.close();
		  }
		  catch(Exception e)
		  {
		   System.out.println(e);
		  }
		   s=s.toLowerCase();
		   System.out.println(s);
		   int x=s.length();;
		   char space =' ';
		   if(s.length()%2!=0)
		   {
		   s += space; 
		   x=s.length();
		   }
		   int ascii[] = new int[x];
		   System.out.print("ascii array: ");
		   for(int i=0;i<x;i++)
		     {
		       ascii[i]= (int) s.charAt(i);
		       System.out.print(ascii[i]+" ");
		     }
		   System.out.println();
		   int k[];
		   int l[];
		   int r[];
		    int n=(int) x/2;
		    l = new int[n];
		    r = new int[n];
		    for(int i=0;i<n;i++)
		    {
		     l[i] = ascii[i];
		    }
		    for(int i=n,j=0;i<x && j<n;i++,j++)
		    {
		     r[j] = ascii[i];
		    }
		   int nk = 10; 
		   k = new int[nk];
		   FileInputStream fs=new FileInputStream("ke.txt");
		    DataInputStream ds=new DataInputStream(fs);
		   for(int i=0;i<nk;i++)
		   {
		      k[i] = ds.readInt();   //h.generateRandomDigits(g) Math.abs(UUID.randomUUID().getMostSignificantBits());
		   }
		   ds.close();
		   int funct[] = new int[r.length];
		   int temp[] = new int[r.length];

		     for(int j=0;j<nk;j++) //exception
		     {
		        for (int i= 0,z=0; i<r.length && z<l.length; i++,z++)
		       {
		            temp[i] = r[i];
		            funct[i] =(int)(r[i]^k[j]);
		            r[i]= (int)(l[z]^funct[i]);
		            l[z]= temp[i];
		       }
		       for (int i= 0,z=0; i<r.length && z<l.length; i++,z++)
		       {
		            System.out.print((char)l[z]+""+(char)r[i]);
		       } 
		      System.out.println();
		     }
		     char lo[] = new char[l.length];
		     char ro[] = new char[r.length];
		     char o[] = new char[l.length+r.length];
		     for(int j=0,z=0;j<l.length && z<r.length;j++,z++)
		     {
		      lo[j] = (char)l[j];
		      ro[z] = (char)r[z];
		     }
		     for (int i = 0; i < l.length; i++)
		     {
		    	 o[i] = (char)l[i];
		     }
		     for (int i = 0; i < r.length; i++)
		     {  
		     	 o[l.length + i] = (char)r[i];
		     }
		     System.out.println();
		     System.out.print("Encrypted: ");
		     
		     //System.out.println(Arrays.toString(o));
		     for(int i=0;i<o.length;i++)
		     {
		      System.out.print(o[i]);
		     }
		    String e;
		    File fil = new File("D:\\Feistel-p-vol\\src\\feistel\\en");
		  BufferedReader bru = new BufferedReader(new FileReader(fil));
		  while ((e = bru.readLine()) != null)
		  {
		   new FileOutputStream("D:\\Feistel-p-vol\\src\\feistel\\en").close();
		  }
		    FileWriter fWriter = new FileWriter("D:\\Feistel-p-vol\\src\\feistel\\en");
		    fWriter.write("\n");
		    fWriter.write(o);
		    fWriter.close();
		    bru.close();
		  System.out.println();
		  System.out.print("\n\nWanna perform decryption too?(Y/N):");
		  String a=sc.next().toUpperCase();
		  if(a.equals("Y")) {
			  System.out.println("\n\n\n");
			  Decryption();
		  }
		  else {
			  System.out.println("\n\n\t\t\t\t\t *** END *** ");
			  System.exit(0);
		  }  
	}
	
	
	
	public static void Decryption() throws Exception{
		System.out.println("\t\t*~*~*~*~*~*~*~*~*~*~ Decryption *~*~*~*~*~*~*~*~*~*~\n\n\n");
		   int nk = 10; 
		   int k[];
		   k = new int[nk];
		   FileInputStream fs=new FileInputStream("ke.txt");
		    DataInputStream ds=new DataInputStream(fs);
		   for(int i=0;i<nk;i++)
		   {
		      k[i] = ds.readInt();  
		   }
		   ds.close();
		    File fi= new File("D:\\Feistel-p-vol\\src\\feistel\\en");
		    BufferedReader br = new BufferedReader(new FileReader(fi));
		    String doi ="";
		    String d="";
		    while ((d = br.readLine())!= null)
		    {
		     doi = doi+d; 
		    }
		    br.close();
		    System.out.println("Encrypted string:"+ doi);
		    new FileOutputStream("D:\\Feistel-p-vol\\src\\feistel\\en").close();
		     int dascii[] = new int[doi.length()]; 
		     System.out.print("dascii array: "); 
		     for(int i=0;i<doi.length();i++)
		     {
		    	 dascii[i] = (int) doi.charAt(i);
		    	 System.out.print(dascii[i]+" ");
		     }
		     System.out.println();
		     int dl[];
		     int dr[];
		     int ei=doi.length();
		      int dn=(int) ei/2;
		      dl = new int[dn];
		      dr = new int[dn];
		      for(int i=0;i<dn;i++)
		      {
		       dl[i] = dascii[i];
		      }
		      for(int i=dn,j=0;i<ei && j<dn;i++,j++)
		      {
		       dr[j] = dascii[i];
		      }
		   int dfunct[] = new int[dl.length];
		   int dtemp[] = new int[dl.length];
		     for(int j=nk-1;j>=0;--j)                                           //exception
		     {
		        for (int i= 0,z=0; i<dr.length && z<dl.length; i++,z++)
		       {
		            dtemp[z] = dl[z];
		            dfunct[z] =(int)(dl[z]^k[j]);
		            dl[z]= (int)(dr[i]^dfunct[i]);
		            dr[i]= dtemp[z];
		       }
		       for (int i= 0,z=0; i<dr.length && z<dl.length; i++,z++)
		       {
		        System.out.print((char)dl[z]+""+(char)dr[i]);
		       }
		      System.out.println();
		     }
		     System.out.println(); 
		     char dlo[] = new char[dl.length];
		     char dro[] = new char[dr.length];
		     char dou[] = new char[dl.length+dr.length];
		     for(int j=0,z=0;j<dl.length && z<dr.length;j++,z++)
		     {
		      dlo[j] = (char)dl[j];
		      dro[z] = (char)dr[z];
		     }
		     for (int i = 0; i < dl.length; i++)
		     {
		            dou[i] = (char)dl[i];
		     }
		     for (int i = 0; i < dr.length; i++)
		     {        
		            dou[dl.length + i] = (char)dr[i];
		     }
		     System.out.print("Decrypted: ");
		     for(int i=0;i<dl.length+dr.length;i++)
		     {
		      System.out.print(dou[i]);
		     }
		    System.out.println(); 
		    System.out.println("\n\n\t\t\t\t\t *** END *** ");
		    System.exit(0);
	}
	
	
	public static float SAMPLE_RATE = 8000f;
    public static void tone(int hz, int msecs) 
    throws LineUnavailableException 
    {
        tone(hz, msecs, 1.0);
    }

    
    
    public static void tone(int hz, int msecs, double vol)
    throws LineUnavailableException 
    {
        byte[] buf = new byte[1];
        AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,true,false);     
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        for (int i=0; i < msecs*8; i++)
        {
              double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
              buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
              sdl.write(buf,0,1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }
	
    
    
	public static void Sound() throws Exception{
		
		FileReader fis = new FileReader("D:\\Feistel-p-vol\\src\\feistel\\en");
        BufferedReader bis = new BufferedReader(fis);
        String s = bis.readLine();
        int x = s.length();
           tone(7000,(440*x));
        bis.close();
        
	}

	
	
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		  char a = (char)5;//leaf
		  char b = (char)3;//heart
		  char c = (char)6;//spade
		  char d = (char)4;//diamond
		    System.out.println(" \n\n\n\t\t\t------------------------------------------------------------------ \n");
		    System.out.println(" \t\t\t"+a+"                   "+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+"                    "+a+" \n\n");
		    System.out.println(" \t\t\t"+a+" *****************      Feistel - P - Vol    *****************  "+a+" \n\n");
		    System.out.println(" \t\t\t"+a+"                   "+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+d+""+"                    "+a+" \n");
		    System.out.println(" \t\t\t------------------------------------------------------------------ \n\n\n");
		    System.out.print("What do you wanna perform?\n\n\t\t1.Encryption\n\n\t\t2.Decryption\n\nEnter your choice >>:");
		    int x=sc.nextInt();
		    if(x==1) {
		    	System.out.print("\n\n\t*** Please place your data in the inp.txt file... ***\n\nDid you enter the data there?(Y/N):");
		    	String s=sc.next().toUpperCase();
		    	if(s.equals("Y")) {
		    		System.out.println("\n\n\n");
		    		Encryption();
		    	}
		    	else if(s.equals("N")) {
		    		System.out.print("The entered inout is a \"NO\" so hurry up and enter the data there!\n\nDid you enter the data now?(Y/N)");
		    		s=sc.next().toUpperCase();
		    		if(s.equals("Y"))Encryption();
		    		else System.exit(0);
		    	}
		    	else {
		    		System.out.println("Invalid Input !!! \n\n\t\t*** Program Terminates ***");
		    		System.exit(0);
		    	}
		    }
		    else if(x==2) {
		    	System.out.print("\n\n\t*** Please place your data in the en file... ***\n\n\n\t\tIf you just encrypted your data then your data is already written in en file \n\t\tso directly type \"y\" or else you will have to place the data there on your own! \n\n\nDid you enter the data there?(Y/N):");
		    	String s=sc.next().toUpperCase();
		    	if(s.equals("Y")) {
		    		System.out.println("\n\n\n");
		    		Decryption();
		    	}
		    	else if(s.equals("N")) {
		    		System.out.print("The entered inout is a \"NO\" so hurry up and enter the data there!\n\nDid you enter the data now?(Y/N)");
		    		s=sc.next().toUpperCase();
		    		if(s.equals("Y"))Decryption();
		    		else System.exit(0);
		    	}
		    	else {
		    		System.out.println("Invalid Input !!! \n\n\t\t*** Program Terminates ***");
		    		System.exit(0);
		    	}
		    }
		    else {
		    	System.out.println("Invalid Input !!! \n\n\t\t*** Program Terminates ***");
	    		System.exit(0);
		    }
		    
	}

}
