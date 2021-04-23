import java.io.*;  
import java.util.Scanner;  
public class Naive2{
	public static void main(String args[]) throws Exception
	{ 
	float luxary,black,under30,black_luxary,under30_luxary;
	luxary=black=under30=black_luxary=under30_luxary=0;
	float p_luxary,p_black,p_under30,p_black_luxary,p_under30_luxary;
	p_luxary=p_black=p_under30=p_black_luxary=p_under30_luxary=0;
	float n= 0;
	Scanner sc=new Scanner(new File("C:\\Users\\Mohit peshwani\\Desktop\\cars.csv"));
	String s1,s2,s3,s4;
	float c1,c2;
	String line1=sc.next();

	while (sc.hasNext()) {
		String line=sc.next();
		String[] str = line.split(",");	
		s1=str[4];
		s3="luxury";
		if(s1.equals(s3))
		{
			luxary++;
		}
		s2=str[3];
		s4="black";
		if(s2.equals(s4))
		{
			black++;
		}
		if(s1.equals(s3) && s2.equals(s4)){
			black_luxary++;  
		}
		c1 = Float.parseFloat(str[5]);
		System.out.println(c1+1);
		if(c1<=30000){
			under30++;
		}
		if(s1.equals(s3) && c1<=30000){
			under30_luxary++;
		}
		n++;
	}
	
	p_luxary = luxary/n;
	p_black = black/n;
	p_under30 = under30/n;
	System.out.println("probability of customers of having luxary car: " + p_luxary);
	System.out.println("probability of customers having black car: " +p_black);
	System.out.println("probability of customers having car under 30k: " +p_under30);
	p_black_luxary = black_luxary/n;
	p_under30_luxary = under30_luxary/n;
	System.out.println("probability of customers having luxury black car: " +p_black_luxary);
	System.out.println("probability of customers having luxury car under 30k: " +p_under30_luxary);
	
	float p_luxary_black_under30 = (p_black_luxary*p_under30_luxary*p_luxary)/(p_black*p_under30);
	System.out.println("probability of black luxary car under 30k is less likely");
	sc.close();
	}
}