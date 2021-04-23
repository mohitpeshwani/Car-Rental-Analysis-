import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
public class Naive12 
{
public static void main(String args[]) 
{
 float luxury=0;
 float suv=0;
 float l_black=0;
 float s_black=0;
 float n=0;
 float above=0,below=0;
 float p_above;
 float p_below;
 String price;
 float p_luxury,p_suv,p_bsuv,p_bluxury;
 
 Scanner sc=new Scanner(new File("car.csv"));
 sc.useDelimiter(",");  
 String s1,s2,s3,s4;
 String c1,c2;
 
 
 while (sc.hasNext()) 
{
      String line=sc.next();
      String str[]=line.split(",");
      
			
 s1=str[4];
 s3="suv";
 if(s1.equals(s3))
   {
     suv++;
   }
  s2=str[4];
  s4="luxury";
  if(s2.equals(s4))
    {
    luxury++;
   }
 c1=str[3];
 c2="black";
 price=str[5];
 System.out.println(str[5]);
 if(c1.equals(s2)&&s2.equals(s4))
{
    l_black++;
    
    if(!price.equals("30000"))
       above++;
      
     else
      below++;
}
else{
s_black++;
}

 p_luxury=luxury/n;
 p_suv=suv/n;
 p_bluxury=l_black/n;
 p_bsuv=s_black/n;
 p_above=above/n;
 p_below=below/n;

 System.out.println("probability of customers having luxury car:" +p_luxury);
 System.out.println("probability of customers of having suv car:" +p_suv);

System.out.println("probability of customers of having luxury car with black color:" +p_bluxury);
System.out.println("probability of customers of having luxury car without black color:" +p_bsuv);

System.out.println("probability of customers of having luxury car with black color above 30k:" +p_above);
System.out.println("probability of customers of having luxury car with black color below 30k is less likely:" +p_below);

sc.close();
}


}
}

