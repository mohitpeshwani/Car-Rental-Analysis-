import java.io.*;
import java.util.Scanner;
public class Dwm
{
public static void main(String[] args) throws Exception
{
try{
Scanner sc = new Scanner(new File("C:\\Users\\Mohit Peshwani\\Desktop\\car.csv"));
float count_FICTION = 0;
float n = 0;
float p_FI,p_age2_FI,p_age3_FI,p_age4_FI ;
int count_age2_FI=0;
int count_age3_FI=0;
int count_age4_FI=0;
int s1,s2;
while (sc.hasNext())
{
String temp = sc.next();
String[] str = temp.split(",");

s1 = Integer.parseInt(str[3]);
if(s1 == 3){

count_FICTION++;

s2 = Integer.parseInt(str[2]);
if(s2>20 && s2<30){
count_age2_FI++;
}
if(s2>30 && s2<40){
count_age3_FI++;
}
if(s2>40 && s2<50){
count_age4_FI++;
}
}
n++;
}
p_FI = count_FICTION/n;

p_age2_FI = count_age2_FI/count_FICTION;
p_age3_FI = count_age3_FI/count_FICTION;
p_age4_FI = count_age4_FI/count_FICTION;

System.out.print("Probablity ofHaving Luxury Car: ");
System.out.println(p_FI);
System.out.print("Probablity of Having SUV Car : ");
System.out.println(p_age2_FI);
System.out.print("Probablity of Sports Car: ");

System.out.println(p_age3_FI);
System.out.print("Probablity of Having Seden Car: ");
System.out.println(p_age4_FI);
sc.close();
}
catch(NumberFormatException e){}
}}