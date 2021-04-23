import java.io.*;
import java.util.Scanner;
public class Naive3
{
public static void main(String[] args) throws Exception
{
Scanner sc = new Scanner(new File("C:\\Users\\Mohit Peshwani\\Desktop\\result\\customer.csv"));
float p_FI;
float count_FICTION = 0;
float n = 0;
int count_luxury=0;
int count_suv=0;
int count_blackluxury=0;
float p_suv,p_luxury,p_blackluxury;
String s1,s2,s3;
while (sc.hasNext())
count_FICTION++;
{
String temp = sc.next();
String[] str = temp.split(",");
s1 ="luxury";
if(s1.equals(str[4])){
count_luxury++;
}
s2="suv";
if(s2.equals(str[4])){
count_suv++;
}
s3="black";

if(s1.equals(str[4]) && s3.equals(str[3])){
count_blackluxury++;
}
n++;
}
p_FI = count_FICTION/n;

p_suv = count_luxury/count_FICTION;
p_luxury = count_suv/count_FICTION;
p_blackluxury = count_blackluxury/count_FICTION;

System.out.print("Probablity of Luxury Car: ");
System.out.println(p_luxury);
System.out.print("Probablity of SUV Car ");
System.out.println(p_suv);
System.out.print("Probablity of Luxury and SUV car: ");
System.out.println(p_blackluxury);
sc.close();
}
}